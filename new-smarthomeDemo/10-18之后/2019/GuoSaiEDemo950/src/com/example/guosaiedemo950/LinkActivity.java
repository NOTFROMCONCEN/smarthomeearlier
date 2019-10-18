package com.example.guosaiedemo950;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @�ļ�����LinkActivity.java
 * @�������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-25
 */
public class LinkActivity extends Activity {
	public static float getdata;
	private CheckBox cb_link_control;// ģʽ����
	private CheckBox cb_mode_control;// ��������
	private CheckBox cb_op_control;// ָ�����
	private Switch sw_mode_state;// ����ڼҿ���
	private Spinner sp_1;// spinner1
	private Spinner sp_2;// spinner2
	private Spinner sp_3;// spinner3
	private EditText et_number_get;// �ı���
	private EditText et_zhiling_name;// �豸��
	private EditText et_zhiling_info;// ����
	private EditText et_zhiling_get;// �豸ȡ��
	private Button btn_save;// ��
	private Button btn_get;// ȡ
	private ListView lv_log;// ��־ListView
	private String time;
	private MyDataBaseHelper dbHelper;
	private LinearLayout line_back_1;
	private SQLiteDatabase db;
	private boolean lijia_mode = false, zaijia_mode = false,
			link_state = false;
	private int num = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		cb_link_control = (CheckBox) findViewById(R.id.cb_link_control);
		cb_mode_control = (CheckBox) findViewById(R.id.cb_mode_state);
		cb_op_control = (CheckBox) findViewById(R.id.cb_zhiling_state);
		sw_mode_state = (Switch) findViewById(R.id.sw_mode_state);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		et_zhiling_name = (EditText) findViewById(R.id.et_zhiling_name);
		et_zhiling_info = (EditText) findViewById(R.id.et_zhiling_state);
		et_zhiling_get = (EditText) findViewById(R.id.et_zhiling_get);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		btn_save = (Button) findViewById(R.id.btn_save);
		btn_get = (Button) findViewById(R.id.btn_get);
		lv_log = (ListView) findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		line_back_1 = (LinearLayout) findViewById(R.id.line_line);
		db = dbHelper.getWritableDatabase();

		// ���水ť
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (cb_op_control.isChecked()) {
					if (et_zhiling_name.getText().toString().isEmpty()
							|| et_zhiling_info.getText().toString().isEmpty()) {
						DiyToast.showTaost(getApplicationContext(), "���ܿհ�");
					} else {
						db.execSQL(
								"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
								new String[] { String.valueOf(num),
										et_zhiling_name.getText().toString(),
										et_zhiling_info.getText().toString(),
										time });
					}
				} else {
					DiyToast.showTaost(getApplicationContext(), "δ����");
				}
			}
		});

		// ���ÿ��ص���¼�
		sw_mode_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					lijia_mode = false;
					zaijia_mode = true;
				} else {
					zaijia_mode = false;
					lijia_mode = true;
				}
			}
		});
		// ���ø�ѡ�����
		cb_link_control
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_link_control.setChecked(false);
							if (et_number_get.getText().toString().equals("")) {
								link_state = false;
								DiyToast.showTaost(getApplicationContext(),
										"��������ֵ");
								cb_link_control.setChecked(false);
							} else {
								link_state = true;
							}
							cb_mode_control.setChecked(false);
							cb_op_control.setChecked(false);
						} else {
							link_state = false;
						}
					}
				});
		cb_mode_control
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							cb_link_control.setChecked(false);
							link_state = false;
							// cb_mode_control.setChecked(false);
							cb_op_control.setChecked(false);
						} else {

						}
					}
				});
		cb_op_control.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_link_control.setChecked(false);
					link_state = false;
					cb_mode_control.setChecked(false);
					// cb_op_control.setChecked(false);
				} else {

				}
			}
		});

		// ���ֳ����¼�
		line_back_1.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				finish();
				return false;
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ���������ͼ���ݴ��䡢���ݿ��ȡ��ģʽ��ʱ��
	 * 
	 * @ʱ �䣺����9:33:24
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ģʽ����
			if (cb_mode_control.isChecked()) {
				if (lijia_mode) {// ���
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					db.execSQL(
							"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
							new String[] { String.valueOf(num), "ͨ��2", "��",
									time });
					db.execSQL(
							"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
							new String[] { String.valueOf(num + 1), "���", "��",
									time });
					db.execSQL(
							"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
							new String[] { String.valueOf(num + 2), "����", "��",
									time });
				}
				if (zaijia_mode) {// �ڼ�
					if (BaseActivity.smo > 600 || BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						db.execSQL(
								"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
								new String[] { String.valueOf(num), "���", "��",
										time });
						db.execSQL(
								"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
								new String[] { String.valueOf(num + 1), "����",
										"��", time });
						db.execSQL(
								"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
								new String[] { String.valueOf(num + 2), "������",
										"��", time });
					}
				}
			}
			// ����ģʽ
			if (link_state) {
				if (et_number_get.getText().toString().equals("")) {
					DiyToast.showTaost(getApplicationContext(), "��������ֵ");
					link_state = false;
					cb_link_control.setChecked(false);
				} else {
					String spinner1, spinner2, spinner3;
					spinner1 = sp_1.getSelectedItem().toString();
					spinner2 = sp_2.getSelectedItem().toString();
					spinner3 = sp_3.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("�¶�")) {
						if (spinner2.equals("��")) {
							if (BaseActivity.temp > number_get) {
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								cb_link_control.setChecked(false);

							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.temp < number_get) {
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								cb_link_control.setChecked(false);

							}
						}
					}
					if (spinner1.equals("ʪ��")) {
						if (spinner2.equals("��")) {
							if (BaseActivity.hum > number_get) {
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								cb_link_control.setChecked(false);

							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.hum < number_get) {
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									db.execSQL(
											"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
											new String[] { String.valueOf(num),
													"����", "��", time });
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								cb_link_control.setChecked(false);

							}
						}
					}
				}
			}
			// ��ͼ���ݴ���
			getdata = BaseActivity.ill;
			// ListView��ȡ
			get_listView();
			// ʱ��
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			time = simpleDateFormat.format(new java.util.Date());
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Cursor cursor = db.rawQuery("select * from log", null);
			cursor.moveToLast();
			String string = cursor.getString(cursor.getColumnIndex("number"));
			num = Integer.valueOf(string);
			num++;
			Message msg = handler.obtainMessage();
			msg.what = num;
			handler.sendMessage(msg);
		}
	};

	private void get_listView() {
		Cursor cursor = db.rawQuery("select * from log", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					LinkActivity.this, R.layout.activity_text, cursor,
					new String[] { "number", "shebei", "state", "time" },
					new int[] { R.id.tv_number, R.id.tv_shebei, R.id.tv_state,
							R.id.tv_time });
			lv_log.setAdapter(adapter);
		}
	}
}
