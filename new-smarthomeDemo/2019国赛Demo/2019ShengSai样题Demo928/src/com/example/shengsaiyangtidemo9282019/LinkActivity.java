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
	private CheckBox cb_mode_state;// ģʽ����
	private CheckBox cb_link_state;// ��������
	private CheckBox cb_op_state;// ָ�����
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
		// �������
		handler.post(timeRunnable);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// �水ť
		btn_save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				set_state();
			}
		});
		// ���á�ֻ��ѡ��һ���� Ч��
		// ģʽ
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
					set_log("ģʽ����", "����");
				}
			}
		});
		// ����
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
					set_log("����ģʽ", "����");
				}
			}
		});
		// ָ��
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
					set_log("ָ��ģʽ", "����");
				}
			}
		});
	}

	/*
	 * @��������set_state
	 * 
	 * @�� �ܣ�ָ����ƴ�
	 * 
	 * @ʱ �䣺����9:25:24
	 */
	private void set_state() {
		if (op_state) {
			if (et_set_name.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "�������豸��");
			} else if (et_set_state.getText().toString().isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "�����붯��");
			} else {
				String get = et_set_name.getText().toString();
				if (get.equals("���") || get.equals("����") || get.equals("����")
						|| get.equals("������") || get.equals("�Ž�")
						|| get.equals("ͨ��1") || get.equals("ͨ��2")
						|| get.equals("ͨ��3")) {
					String get2 = et_set_state.getText().toString();
					if (get2.equals("��") || get2.equals("��")) {
						new AlertDialog.Builder(LinkActivity.this)
								.setTitle("���ס��������Ĳ���")
								.setMessage(
										"�豸����"
												+ et_set_name.getText()
														.toString()
												+ "\n"
												+ "������"
												+ et_set_state.getText()
														.toString())
								.setNegativeButton("�Ҽ�ס��",
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
														"�ɹ�");
												set_log(et_set_name.getText()
														.toString(),
														et_set_state.getText()
																.toString());
											}
										}).show();
						DiyToast.showToast(getApplicationContext(), "��ȷ��");
					} else {
						DiyToast.showToast(getApplicationContext(),
								"״ֻ̬�С��������ء�");
					}
				} else {
					DiyToast.showToast(getApplicationContext(),
							"�����롰�������桱�е��豸��");
				}
			}
		} else {
			DiyToast.showToast(getApplicationContext(), "δ����");
		}
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����9:25:14
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
	 * @��������handler
	 * 
	 * @�� �ܣ�������ģʽ��ListView��д
	 * 
	 * @ʱ �䣺����9:37:51
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			get_log();
			// ����ģʽ
			if (link_state) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "��������ֵ");
					link_state = false;
					cb_link_state.setChecked(false);
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					String spinner3 = sp_3.getSelectedItem().toString();
					if (spinner1.equals("�¶�")) {
						if (spinner2.equals("��")) {
							if (BaseActivity.temp > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (spinner3.equals("��")) {
									set_log("����", "��");
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner3.equals("��")) {
									set_log("����", "��");
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"����������");
								link_state = false;
								cb_link_state.setChecked(false);
							}
						}
						if (spinner1.equals("<")) {
							if (BaseActivity.temp < Integer
									.valueOf(et_number_get.getText().toString())) {
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
								cb_link_state.setChecked(false);
							}
						}
					}
					if (spinner1.equals("ʪ��")) {
						if (spinner2.equals("��")) {
							if (BaseActivity.hum > Integer
									.valueOf(et_number_get.getText().toString())) {
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
								cb_link_state.setChecked(false);
							}
						}
						if (spinner1.equals("<")) {
							if (BaseActivity.hum < Integer
									.valueOf(et_number_get.getText().toString())) {
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
								cb_link_state.setChecked(false);
							}
						}
					}
				}
			}

			// ģʽ����
			if (mode_state) {
				if (sw_zaijia_lijia.getText().toString().equals("�ڼ�")) {
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
				} else if (sw_zaijia_lijia.getText().toString().equals("���")) {
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
	 * @��������set_log
	 * 
	 * @�� �ܣ�����������־
	 * 
	 * @ʱ �䣺����10:06:31
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
