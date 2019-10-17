package com.example.shengsaiyangtidemo9282019;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;

public class LinkActivity extends Activity {
	private CheckBox cb_mode_state;// 模式控制
	private CheckBox cb_link_state;// 联动控制
	private CheckBox cb_op_state;// 指令控制
	private ListView lv_1;// ListView
	private Switch sw_zaijia_lijia;
	private Spinner sp_1, sp_2, sp_3;
	private EditText et_number_get;
	private EditText et_set_name, et_set_state;
	private EditText et_get_name;
	private Button btn_get, btn_save;
	private boolean mode_state = false, link_state = false, op_state = false;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		initView();
		get_log();
		// 激活进程
		handler.post(timeRunnable);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 存按钮
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				set_state();
			}
		});
		// 设置“只能选择一个” 效果
		// 模式
		cb_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_link_state.setChecked(false);
					cb_op_state.setChecked(false);
					// cb_mode_state.setChecked(false);
					mode_state = true;
					link_state = false;
					op_state = false;
					set_log("模式功能", "激活");
				}
			}
		});
		// 联动
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// cb_link_state.setChecked(false);
					cb_op_state.setChecked(false);
					cb_mode_state.setChecked(false);
					mode_state = false;
					link_state = true;
					op_state = false;
					set_log("联动模式", "激活");
				}
			}
		});
		// 指令
		cb_op_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_link_state.setChecked(false);
					// cb_op_state.setChecked(false);
					cb_mode_state.setChecked(false);
					mode_state = false;
					link_state = false;
					op_state = true;
					set_log("指令模式", "激活");
				}
			}
		});
	}

	/*
	 * @方法名：set_state
	 * 
	 * @功 能：指令控制存
	 * 
	 * @时 间：上午9:25:24
	 */
	private void set_state() {
		if (op_state) {
			if (et_set_name.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入设备名");
			} else if (et_set_state.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入动作");
			} else {
				String get = et_set_name.getText().toString();
				if (get.equals("射灯") || get.equals("窗帘") || get.equals("风扇")
						|| get.equals("报警灯") || get.equals("门禁")
						|| get.equals("通道1") || get.equals("通道2")
						|| get.equals("通道3")) {
					String get2 = et_set_state.getText().toString();
					if (get2.equals("开") || get2.equals("关")) {
						new AlertDialog.Builder(LinkActivity.this)
								.setTitle("请记住你你输入的参数")
								.setMessage(
										"设备名："
												+ et_set_name.getText()
														.toString()
												+ "\n"
												+ "动作："
												+ et_set_state.getText()
														.toString())
								.setNegativeButton("我记住了",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												db.execSQL(
														"insert into base_state (name,state)values(?,?)",
														new String[] {
																et_set_name
																		.getText()
																		.toString(),
																et_set_state
																		.getText()
																		.toString() });
												DiyToast.showToast(
														getApplicationContext(),
														"成功");
												set_log(et_set_name.getText()
														.toString(),
														et_set_state.getText()
																.toString());
											}
										}).show();
						DiyToast.showToast(getApplicationContext(), "请确认");
					} else {
						DiyToast.showToast(getApplicationContext(),
								"状态只有“开”“关”");
					}
				} else {
					DiyToast.showToast(getApplicationContext(),
							"请输入“基本界面”中的设备名");
				}
			}
		} else {
			DiyToast.showToast(getApplicationContext(), "未激活");
		}
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午9:25:14
	 */
	private void initView() {
		cb_link_state = (CheckBox) findViewById(R.id.cb_link_state);
		cb_mode_state = (CheckBox) findViewById(R.id.cb_mode_state);
		cb_op_state = (CheckBox) findViewById(R.id.cb_op_state);
		lv_1 = (ListView) findViewById(R.id.listView1);
		sw_zaijia_lijia = (Switch) findViewById(R.id.sw_zaijia_lijia);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		et_get_name = (EditText) findViewById(R.id.et_get_name);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		et_set_name = (EditText) findViewById(R.id.et_set_name);
		et_set_state = (EditText) findViewById(R.id.et_set_state);
		btn_get = (Button) findViewById(R.id.btn_get);
		btn_save = (Button) findViewById(R.id.btn_save);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联动、模式、ListView读写
	 * 
	 * @时 间：上午9:37:51
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			get_log();
			// 联动模式
			if (link_state) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
					link_state = false;
					cb_link_state.setChecked(false);
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					String spinner3 = sp_3.getSelectedItem().toString();
					if (spinner1.equals("温度")) {
						if (spinner2.equals("≥")) {
							if (BaseActivity.temp > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (spinner3.equals("开")) {
									set_log("风扇", "开");
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner3.equals("关")) {
									set_log("风扇", "关");
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								link_state = false;
								cb_link_state.setChecked(false);
							}
						}
						if (spinner1.equals("<")) {
							if (BaseActivity.temp < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (spinner3.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner3.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								link_state = false;
								cb_link_state.setChecked(false);
							}
						}
					}
					if (spinner1.equals("湿度")) {
						if (spinner2.equals("≥")) {
							if (BaseActivity.hum > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (spinner3.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner3.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								link_state = false;
								cb_link_state.setChecked(false);
							}
						}
						if (spinner1.equals("<")) {
							if (BaseActivity.hum < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (spinner3.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner3.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"条件不满足");
								link_state = false;
								cb_link_state.setChecked(false);
							}
						}
					}
				}
			}

			// 模式控制
			if (mode_state) {
				if (sw_zaijia_lijia.getText().toString().equals("在家")) {
					for (int i = 0; i < 6; i++) {
						if (i == 1) {
							ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
						if (i == 3) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (i == 5) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						}
					}
				} else if (sw_zaijia_lijia.getText().toString().equals("离家")) {
					if (BaseActivity.smo > 600 || BaseActivity.per == 1) {
						for (int i = 0; i < 6; i++) {
							if (i == 1) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (i == 3) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (i == 5) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	/*
	 * @方法名：set_log
	 * 
	 * @功 能：设置运行日志
	 * 
	 * @时 间：上午10:06:31
	 */
	private void set_log(String name, String state) {
		Cursor cursor = db.rawQuery("select * from link_state", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			int number = Integer.valueOf(cursor.getString(cursor
					.getColumnIndex("number")));
			number++;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			db.execSQL(
					"insert into link_state(number,name,state,time)values(?,?,?,?)",
					new String[] {
							String.valueOf(number),
							name,
							state,
							String.valueOf(simpleDateFormat
									.format(new java.util.Date())) });
			get_log();
		}
	}

	private void get_log() {
		Cursor cursor = db.rawQuery("select * from link_state", null);
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				LinkActivity.this, R.layout.activity_link_text, cursor,
				new String[] { "number", "name", "state", "time" }, new int[] {
						R.id.tv_number, R.id.tv_name, R.id.tv_state,
						R.id.tv_time });
		lv_1.setAdapter(simpleCursorAdapter);
	}
}
