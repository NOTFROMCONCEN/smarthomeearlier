package com.example.guosaiedemo22019;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import lib.Json_data;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class ModeActivity extends Activity {
	private CheckBox cb_mode, cb_link, cb_op_state;
	private EditText et_shebeiming, et_dongzuo, et_getEditText, et_num;
	private Button btn_save, btn_get;
	private String shebeiming, dongzuo, getEditText;
	private SQLiteDatabase db;
	private MyDataBaseHelper dbHelper;
	private boolean link_state = false, mode_state = false;
	private Spinner sp_big_small, sp_open_cls, sp_1;
	private Switch sw_mode_state;
	private boolean lijia_state = false, zaijia_state = false;
	private int recLen = 0;
	private int et_get_num;
	private String spinner1, spinner2, spinner3, time;
	private ListView lv_1;
	private Button btn_del;
	static float getdata;
	LinearLayout line_intent2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode);
		btn_get = (Button) findViewById(R.id.btn_get);
		btn_save = (Button) findViewById(R.id.btn_save);
		et_dongzuo = (EditText) findViewById(R.id.et_dongzuo);
		et_getEditText = (EditText) findViewById(R.id.et_getEditText);
		et_shebeiming = (EditText) findViewById(R.id.et_shebeiming);
		cb_link = (CheckBox) findViewById(R.id.cb_link);
		cb_mode = (CheckBox) findViewById(R.id.cb_mode);
		cb_op_state = (CheckBox) findViewById(R.id.cb_op_state);
		et_num = (EditText) findViewById(R.id.et_num);
		sp_1 = (Spinner) findViewById(R.id.sp_1);
		sp_big_small = (Spinner) findViewById(R.id.sp_big_small);
		sp_open_cls = (Spinner) findViewById(R.id.sp_open_cls);
		sw_mode_state = (Switch) findViewById(R.id.sw_mode_state);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) findViewById(R.id.listView1);
		btn_del = (Button) findViewById(R.id.btn_del);
		handler.post(timeRunnable);
		line_intent2 = (LinearLayout) findViewById(R.id.line_intent2);
		line_intent2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		sw_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lijia_state = true;
					zaijia_state = false;
				} else {
					lijia_state = false;
					zaijia_state = true;
				}
			}
		});
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("delete  from log");
			}
		});
		cb_link.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_mode.setChecked(false);
					cb_op_state.setChecked(false);
					if (et_num.getText().toString().equals("")) {
						link_state = false;
						cb_link.setChecked(false);
						Toast.makeText(ModeActivity.this, "请输入数值！",
								Toast.LENGTH_SHORT).show();
					} else {
						link_state = true;
						Toast.makeText(ModeActivity.this, "联动模式开启",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					link_state = false;
					BaseActivity.Js.control(Json_data.Fan, 0, 0);
					Toast.makeText(ModeActivity.this, "联动模式关闭",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		cb_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					mode_state = true;
					cb_link.setChecked(false);
					cb_mode.setChecked(true);
					cb_op_state.setChecked(false);
					Toast.makeText(ModeActivity.this, "模式功能开启",
							Toast.LENGTH_SHORT).show();
				} else {
					mode_state = false;
					Toast.makeText(ModeActivity.this, "模式功能关闭",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		cb_op_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_link.setChecked(false);
					cb_mode.setChecked(false);
					cb_op_state.setChecked(true);
					Toast.makeText(
							ModeActivity.this,
							"请操作：可预先存入指令，设备名与基本界面相同。指令为开、关、停。输入无误则存入数据库，取出需输入用户名",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(ModeActivity.this, "指令控制模式关闭",
							Toast.LENGTH_SHORT).show();
				}
			}
		});
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ModeActivity.this, "已存入", Toast.LENGTH_SHORT)
						.show();
			}
		});
		btn_get.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(ModeActivity.this, "已取出", Toast.LENGTH_SHORT)
						.show();
			}
		});
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			Log.e("子Handler进程", "进程激活");
			SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			time = formater.format(new java.util.Date());
			Cursor cursor = db.rawQuery("select * from log", null);
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					ModeActivity.this, R.layout.activity_text, cursor,
					new String[] { "num", "shebei", "state", "time" },
					new int[] { R.id.tv_num, R.id.tv_shebei, R.id.tv_state,
							R.id.tv_time });
			lv_1.setAdapter(adapter);

			if (mode_state) {
				db.execSQL(
						"insert into log (num,shebei,state,time)values(?,?,?,?)",
						new String[] { "tips", "模式功能", "开", time });
				if (lijia_state) {
					db.execSQL(
							"insert into log (num,shebei,state,time)values(?,?,?,?)",
							new String[] { "tips", "离家模式", "开", time });
					if (BaseActivity.smo > 600) {
						BaseActivity.Js.control(Json_data.WarningLight, 0, 1);
						BaseActivity.Js.control(Json_data.Fan, 0, 1);
						BaseActivity.Js.control(Json_data.Curtain, 0, 1);
						db.execSQL(
								"insert into log (num,shebei,state,time)values(?,?,?,?)",
								new String[] { "1", "报警灯", "开", time });
						db.execSQL(
								"insert into log (num,shebei,state,time)values(?,?,?,?)",
								new String[] { "2", "风扇", "开", time });
						db.execSQL(
								"insert into log (num,shebei,state,time)values(?,?,?,?)",
								new String[] { "3", "窗帘", "开", time });
					}
					if (BaseActivity.per == 1) {
						BaseActivity.Js.control(Json_data.WarningLight, 0, 1);
						BaseActivity.Js.control(Json_data.Fan, 0, 1);
						BaseActivity.Js.control(Json_data.Curtain, 0, 1);
						db.execSQL(
								"insert into log (num,shebei,state,time)values(?,?,?,?)",
								new String[] { "1", "报警灯", "开", time });
						db.execSQL(
								"insert into log (num,shebei,state,time)values(?,?,?,?)",
								new String[] { "2", "风扇", "开", time });
						db.execSQL(
								"insert into log (num,shebei,state,time)values(?,?,?,?)",
								new String[] { "3", "窗帘", "开", time });
					}
				}
				if (zaijia_state) {
					db.execSQL(
							"insert into log (num,shebei,state,time)values(?,?,?,?)",
							new String[] { "tips", "在家模式", "开", time });
					BaseActivity.Js.control(Json_data.InfraredEmit, 0, 2);
					BaseActivity.Js.control(Json_data.Lamp, 0, 1);
					BaseActivity.Js.control(Json_data.Curtain, 0, 2);
					db.execSQL(
							"insert into log (num,shebei,state,time)values(?,?,?,?)",
							new String[] { "1", "空调", "开", time });
					db.execSQL(
							"insert into log (num,shebei,state,time)values(?,?,?,?)",
							new String[] { "2", "射灯", "开", time });
					db.execSQL(
							"insert into log (num,shebei,state,time)values(?,?,?,?)",
							new String[] { "3", "窗帘", "开", time });
				}
			}
			if (link_state) {
				db.execSQL(
						"insert into log (num,shebei,state,time)values(?,?,?,?)",
						new String[] { "1", "联动模式", "开", time });
				if (et_num.getText().toString().equals("")) {
					Toast.makeText(ModeActivity.this, "请输入数值！",
							Toast.LENGTH_SHORT).show();
					db.execSQL(
							"insert into log (num,shebei,state,time)values(?,?,?,?)",
							new String[] { "error", "错误", "条件不满足", time });
					link_state = false;
					cb_link.setChecked(false);
					Log.e("联动", "条件不满足！");
				} else {
					et_get_num = Integer.valueOf(et_num.getText().toString());
					spinner1 = sp_1.getSelectedItem().toString();
					spinner2 = sp_big_small.getSelectedItem().toString();
					spinner3 = sp_open_cls.getSelectedItem().toString();
					if (spinner1.equals("温度")) {
						Log.e("联动", "温度");
						if (spinner2.equals(">")) {
							Log.e("联动", "大于");
							if (BaseActivity.temp > et_get_num) {
								if (spinner3.equals("开")) {
									Log.e("联动", "开");
									BaseActivity.Js
											.control(Json_data.Fan, 0, 1);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "开", time });
								}
								if (spinner3.equals("关")) {
									Log.e("联动", "关");
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "关", time });
									BaseActivity.Js
											.control(Json_data.Fan, 0, 0);
								}
							} else {
								Toast.makeText(ModeActivity.this, "条件不满足！",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								cb_link.setChecked(false);
								Log.e("联动", "条件不满足！");
								db.execSQL(
										"insert into log (num,shebei,state,time)values(?,?,?,?)",
										new String[] { "error", "错误", "条件不满足",
												time });

							}
						}
						if (spinner2.equals("<")) {
							Log.e("联动", "小于");

							if (BaseActivity.temp < et_get_num) {
								if (spinner3.equals("开")) {
									Log.e("联动", "开");

									BaseActivity.Js
											.control(Json_data.Fan, 0, 1);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "开", time });

								}
								if (spinner3.equals("关")) {
									Log.e("联动", "关");

									BaseActivity.Js
											.control(Json_data.Fan, 0, 0);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "关", time });
								}
							} else {
								Toast.makeText(ModeActivity.this, "条件不满足！",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								cb_link.setChecked(false);
								Log.e("联动", "条件不满足！");
								db.execSQL(
										"insert into log (num,shebei,state,time)values(?,?,?,?)",
										new String[] { "error", "错误", "条件不满足",
												time });

							}
						}
					}
					if (spinner1.equals("湿度")) {
						Log.e("联动", "湿度");

						if (spinner2.equals(">")) {
							Log.e("联动", "大于");

							if (BaseActivity.hum > et_get_num) {
								if (spinner3.equals("开")) {
									Log.e("联动", "开");

									BaseActivity.Js
											.control(Json_data.Fan, 0, 1);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "开", time });
								}
								if (spinner3.equals("关")) {
									Log.e("联动", "关");

									BaseActivity.Js
											.control(Json_data.Fan, 0, 0);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "关", time });
								}
							} else {
								Toast.makeText(ModeActivity.this, "条件不满足！",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								cb_link.setChecked(false);
								Log.e("联动", "条件不满足！");
								db.execSQL(
										"insert into log (num,shebei,state,time)values(?,?,?,?)",
										new String[] { "error", "错误", "条件不满足",
												time });
							}
						}
						if (spinner2.equals("<")) {
							Log.e("联动", "小于");
							if (BaseActivity.hum < et_get_num) {
								if (spinner3.equals("开")) {
									Log.e("联动", "开");
									BaseActivity.Js
											.control(Json_data.Fan, 0, 1);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "开", time });
								}
								if (spinner3.equals("关")) {
									Log.e("联动", "关");
									BaseActivity.Js
											.control(Json_data.Fan, 0, 0);
									db.execSQL(
											"insert into log (num,shebei,state,time)values(?,?,?,?)",
											new String[] { "4", "风扇", "关", time });
								}
							} else {
								Toast.makeText(ModeActivity.this, "条件不满足！",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								db.execSQL(
										"insert into log (num,shebei,state,time)values(?,?,?,?)",
										new String[] { "error", "错误", "条件不满足",
												time });
								cb_link.setChecked(false);
								Log.e("联动", "条件不满足！");
							}
						}
					}
				}
			}
			getdata = BaseActivity.ill;
			// getdata = Float.parseFloat("70");
			handler.postDelayed(timeRunnable, 2000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen++;
			Message msg = handler.obtainMessage();
			recLen++;
			handler.sendMessage(msg);
		}
	};
}
