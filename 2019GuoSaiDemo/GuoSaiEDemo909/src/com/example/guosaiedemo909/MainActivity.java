package com.example.guosaiedemo909;

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
import android.widget.SimpleAdapter;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @��������¼����¼����ͳ�ơ�SeekBar
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-9
 */
public class MainActivity extends Activity {
	private Button btn_login;// ��¼��ť
	private EditText et_port;// �˿ں�
	private EditText et_ip;// IP��ַ
	private TextView tv_login_number;// ��¼����
	private SeekBar sk_1;// SeekBar
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	public static String ip_number;// String IP��ַ
	// ʱ��
	private String login_time;
	// ����
	private String login_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("SmartHome");
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		initview();
		// ���ð�ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_port.getText().toString().equals("")) {// �˿�Ϊ��
					DiyToast.showToast(getApplicationContext(), "������˿�");
				} else if (et_ip.getText().toString().equals("")) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					// ��ȡ��¼������ȡʱ�䲢�������ݿ�
					// �����ݿ�ȡ���ϴε�¼����
					Cursor cursor = db.rawQuery("select * from login", null);
					if (cursor.getCount() != 0) {
						cursor.moveToLast();// ���ݿ��ƶ������
						int last_login_time = cursor.getInt(cursor
								.getColumnIndex("login_number"));// ȡ������
						last_login_time++;// ��¼ʱ��¼�ϴε�¼����+1
						// ��������
						db.execSQL(
								"insert into login(login_number,login_time)values(?,?)",
								new String[] { String.valueOf(last_login_time),
										login_time });
					}
					ip_number = et_ip.getText().toString();// ��ȡIP��ַ
					Intent intent = new Intent(MainActivity.this,
							BaseActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// ����SeekBar�����¼�
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				// ������ȴ���68С��70
				if (seekBar.getProgress() >= 67 && seekBar.getProgress() <= 72) {
					if (et_port.getText().toString().equals("")) {// �˿�Ϊ��
						DiyToast.showToast(getApplicationContext(), "������˿�");
						sk_1.setProgress(0);
					} else if (et_ip.getText().toString().equals("")) {// IP��ַΪ��
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
						sk_1.setProgress(0);
					} else {
						// ��ȡ��¼������ȡʱ�䲢�������ݿ�
						// �����ݿ�ȡ���ϴε�¼����
						Cursor cursor = db
								.rawQuery("select * from login", null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();// ���ݿ��ƶ������
							int last_login_time = cursor.getInt(cursor
									.getColumnIndex("login_number"));// ȡ������
							last_login_time++;// ��¼ʱ��¼�ϴε�¼����+1
							// ��������
							db.execSQL(
									"insert into login(login_number,login_time)values(?,?)",
									new String[] {
											String.valueOf(last_login_time),
											login_time });
						}
						ip_number = et_ip.getText().toString();// ��ȡIP��ַ
						Intent intent = new Intent(MainActivity.this,
								BaseActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "�뻬�������֤");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub
				// �����ʼ���������ص�¼��ť
				btn_login.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// ���ȸı�
				// TODO Auto-generated method stub
				System.out.println(seekBar.getProgress());
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
	 * @��������initview
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:44:55
	 */
	private void initview() {
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		tv_login_number = (TextView) findViewById(R.id.tv_number_text);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// ��ȡ��¼������ȡʱ�䲢�������ݿ�
		// �����ݿ�ȡ���ϴε�¼����
		Cursor cursor = db.rawQuery("select * from login", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();// ���ݿ��ƶ������
			login_time = cursor.getString(cursor.getColumnIndex("login_time"));// ȡ������
			login_number = cursor.getString(cursor
					.getColumnIndex("login_number"));
			tv_login_number.setText("֮ǰ����" + login_number + "�ε�¼���ϴε�¼ʱ��Ϊ"
					+ login_time);
		}
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @�� ����String
	 * 
	 * @ʱ �䣺����8:48:49
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			login_time = simpleDateFormat.format(new java.util.Date());
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
}
