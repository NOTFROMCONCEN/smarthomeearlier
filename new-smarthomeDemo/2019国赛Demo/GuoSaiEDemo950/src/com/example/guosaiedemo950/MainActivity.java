package com.example.guosaiedemo950;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @��������ɵ�¼����ס��¼ʱ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-25
 */
public class MainActivity extends Activity {
	private EditText et_port, et_ip;
	private Button btn_login;
	private SeekBar sk_1;
	private TextView tv_number_text;
	private String time;
	public static String IP_NUMBER;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		tv_number_text = (TextView) findViewById(R.id.tv_number_text);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		get_login_number();
		// SeekBar��������
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				if (seekBar.getProgress() > 69 && seekBar.getProgress() < 74) {
					if (et_ip.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()) {// ���пհ���Ŀ
						DiyToast.showTaost(getApplicationContext(),
								"IP��ַ�Ͷ˿ںŲ���Ϊ��");
						sk_1.setProgress(0);
						btn_login.setVisibility(View.VISIBLE);
					} else {
						Cursor cursor = db.rawQuery(
								"select * from login_rember", null);// �½����ݿ��α�
						if (cursor.getCount() != 0) {
							// �α��ƶ������
							cursor.moveToLast();
							int string = Integer.valueOf(cursor
									.getString(cursor
											.getColumnIndex("login_number")));// ȡ����ֵת��Ϊint��ֵ
							System.out.println(string);
							string++;// �����1
							// �������ݿ�
							db.execSQL(
									"insert into login_rember (login_time,login_number)values(?,?)",
									new String[] { time, String.valueOf(string) });
							// ����IP��ַ
							IP_NUMBER = et_ip.getText().toString();
							// ��½�ɹ�����ת
							Intent intent = new Intent(getApplicationContext(),
									BaseActivity.class);
							startActivity(intent);
							finish();
						}
					}
				} else {
					sk_1.setProgress(0);
					btn_login.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// �������ı�
				// TODO Auto-generated method stub
				// �������ı�||ʹ�ý�������ɵ�¼ʱ���ص�¼��ť
				if (sk_1.getProgress() > 2) {
					btn_login.setVisibility(View.INVISIBLE);
				}
			}
		});

		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// ��¼��ť���ʱ����SeekBar
				sk_1.setVisibility(View.INVISIBLE);
				if (et_port.getText().toString().isEmpty()) {// ����˿ں�Ϊ��
					DiyToast.showTaost(getApplicationContext(), "������˿ں�");
				} else if (et_ip.getText().toString().isEmpty()) {// ���IP��ַΪ��
					DiyToast.showTaost(getApplicationContext(), "������IP��ַ");
				} else {
					Cursor cursor = db.rawQuery("select * from login_rember",
							null);// �½����ݿ��α�
					if (cursor.getCount() != 0) {
						// �α��ƶ������
						cursor.moveToLast();
						int string = Integer.valueOf(cursor.getString(cursor
								.getColumnIndex("login_number")));// ȡ����ֵת��Ϊint��ֵ
						System.out.println(string);
						string++;// �����1
						// �������ݿ�
						db.execSQL(
								"insert into login_rember (login_time,login_number)values(?,?)",
								new String[] { time, String.valueOf(string) });
						// ����IP��ַ
						IP_NUMBER = et_ip.getText().toString();
						// ��½�ɹ�����ת
						Intent intent = new Intent(getApplicationContext(),
								BaseActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������get_login_number
	 * 
	 * @�� �ܣ���ȡ�ϴε�¼������ʱ��
	 * 
	 * @ʱ �䣺����8:08:05
	 */
	private void get_login_number() {
		Cursor cursor = db.rawQuery("select * from login_rember", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			if (cursor.getString(cursor.getColumnIndex("login_number"))
					.toString().equals("0")) {
				DiyToast.showTaost(getApplicationContext(), "�״ο�������");
				tv_number_text.setText("");
			} else {
				tv_number_text
						.setText("֮ǰ����"
								+ cursor.getString(cursor
										.getColumnIndex("login_number"))
								+ "�ε�¼"
								+ "���ϴε�¼ʱ��Ϊ"
								+ cursor.getString(cursor
										.getColumnIndex("login_time")));
			}
		}
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����8:08:27
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
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
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
