package com.example.guosaiedemo909;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.format.Time;
import android.view.View;
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

/*
 * @�ļ�����LinkActivity.java
 * @��������������ͼ�����ݿ�洢
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-9
 */
public class LinkActivity extends Activity {
	// ����
	private LinearLayout line_back_1;
	private LinearLayout line_back_2;
	// ʱ��
	private String link_time;
	static float getdata;

	// ��ѡ��
	private CheckBox cb_mode_control;// ģʽ����
	private CheckBox cb_link_control;// ��������
	private CheckBox cb_op_control;// ָ�����
	// ����
	private Switch sw_mode_state;// ģʽ�л�
	private boolean lijia_mode = false;// ���ģʽ
	private boolean zaijia_mode = false;// �ڼ�ģʽ
	private boolean link_state = false;
	// Spinner�����˵�
	private Spinner sp_1, sp_2, sp_3;
	// �ı���
	private EditText et_number_get;// ����������ֵ
	private EditText et_shebei;// ָ������豸��
	private EditText et_dongzuo;// ָ����ƶ���
	private EditText et_get_shebei;// ��ȡ�豸��
	// ��ť
	private Button btn_sqlite_insert;// �������ݿ�
	private Button btn_sqlite_get;// ��ȡ���ݿ�
	// ListView
	private ListView lv_1;
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// ���
	private int numbet_lamp = 1;// ���
	private int numbet_cur = 2;// ����
	private int numbet_fan = 3;// ����
	private int numbet_warm = 4;// ������
	private int numbet_door = 5;// �Ž�
	private int numbet_tongdao1 = 6;// ͨ��1
	private int numbet_tongdao2 = 7;// ͨ��2
	private int numbet_tongdao3 = 8;// ͨ��3

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// �󶨿ؼ�
		line_back_1 = (LinearLayout) findViewById(R.id.link_back1);
		line_back_2 = (LinearLayout) findViewById(R.id.link_back2);
		btn_sqlite_get = (Button) findViewById(R.id.btn_sqlite_get);
		btn_sqlite_insert = (Button) findViewById(R.id.btn_sqlite_insert);
		et_dongzuo = (EditText) findViewById(R.id.et_dongzuo);
		et_get_shebei = (EditText) findViewById(R.id.et_get_shebei);
		et_number_get = (EditText) findViewById(R.id.et_number_gte);
		et_shebei = (EditText) findViewById(R.id.et_shebei);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		cb_link_control = (CheckBox) findViewById(R.id.cb_link_control);
		cb_mode_control = (CheckBox) findViewById(R.id.cb_mode_control);
		cb_op_control = (CheckBox) findViewById(R.id.cb_op_control);
		sw_mode_state = (Switch) findViewById(R.id.sw_mode_state);
		lv_1 = (ListView) findViewById(R.id.listView1);
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
								DiyToast.showToast(getApplicationContext(),
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
		line_back_2.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				finish();
				return false;
			}
		});
		// ָ�����
		// �������
		handler.post(tiemRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			db.execSQL(
					"insert into data (number,shebei,dongzuo,time)values(?,?,?,?)",
					new String[] { String.valueOf(numbet_tongdao2), "ͨ��2", "��",
							link_time });
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
							"insert into data (number,shebei,dongzuo,time)values(?,?,?,?)",
							new String[] { String.valueOf(numbet_tongdao2),
									"ͨ��2", "��", link_time });
				}
				if (zaijia_mode) {// �ڼ�
					if (BaseActivity.smo > 600 || BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
			}
			if (link_state) {
				if (et_number_get.getText().toString().equals("")) {
					DiyToast.showToast(getApplicationContext(), "��������ֵ");
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
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
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
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
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
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
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
								}
								if (spinner3.equals("��")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"����������");
								link_state = false;
								cb_link_control.setChecked(false);
							}
						}
					}
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			link_time = simpleDateFormat.format(new java.util.Date());
			Cursor cursor = db.rawQuery("select * from data", null);
			if (cursor.getCount() != 0) {
				cursor.moveToLast();
				SimpleCursorAdapter adapter = new SimpleCursorAdapter(
						LinkActivity.this, R.layout.activity_link_text, cursor,
						new String[] { "number", "shebei", "dongzuo", "time" },
						new int[] { R.id.tv_number, R.id.tv_shebei,
								R.id.tv_state, R.id.tv_time });
			}
			getdata = BaseActivity.ill;
			handler.postDelayed(tiemRunnable, 1000);
		}
	};
	Runnable tiemRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
