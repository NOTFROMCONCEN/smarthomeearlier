package com.example.shengsaiyangtidemo9282019;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-28
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;// ��������
	private Button btn_login;// ��¼��ť
	private EditText et_ip;// IP��ַ
	private EditText et_port;// �˿ں�
	static String ip, port;
	private TextView tv_login_number;// ��¼����
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// ��ס����
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_login_number = (TextView) findViewById(R.id.tv_login_number);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��ס����Ĳ���
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_port.setText(sharedPreferences.getString("port", null));
		}
		// ��������ʱ��ȡ��¼����
		get_login_number();
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();
				port = et_port.getText().toString();
				if (ip.isEmpty() || port.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP��˿ںŲ���Ϊ��");
				} else {
					set_login_number();// ����
					get_login_number();// ��ȡ
					set_last_number(ip, port);// ���ü�ס����
					// ��ת
					Intent intent = new Intent(getApplicationContext(),
							BaseActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// ���û���������
		sk_1.setProgress(0);
		sk_1.setMax(100);
		// ����������ʱ�¼�
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();
				port = et_port.getText().toString();
				if (seekBar.getProgress() > 68 && seekBar.getProgress() < 74) {
					if (et_ip.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(),
								"IP��˿ںŲ���Ϊ��");
						sk_1.setProgress(0);
						btn_login.setVisibility(View.VISIBLE);
					} else {
						set_login_number();// ����
						get_login_number();// ��ȡ
						set_last_number(et_ip.getText().toString(), et_port
								.getText().toString());// ���ü�ס����
						// ��ת
						Intent intent = new Intent(getApplicationContext(),
								BaseActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "�뻬�������֤");
					btn_login.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// �������ı�
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(),
						String.valueOf(seekBar.getProgress()));
				if (seekBar.getProgress() > 2) {
					btn_login.setVisibility(View.INVISIBLE);
				} else {
					btn_login.setVisibility(View.VISIBLE);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������set_login_number
	 * 
	 * @�� �ܣ���¼ʱ�����¼����
	 * 
	 * @ʱ �䣺����8:10:45
	 */
	private void set_login_number() {
		Cursor cursor = db.rawQuery("select * from login_number", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			int number = Integer.valueOf(cursor.getString(cursor
					.getColumnIndex("number")));
			number++;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			db.execSQL(
					"insert into login_number (number,time)values(?,?)",
					new String[] {
							String.valueOf(number),
							String.valueOf(simpleDateFormat
									.format(new java.util.Date())) });
		}
	}

	/*
	 * @��������get_login_number
	 * 
	 * @�� �ܣ���ȡ��¼����
	 * 
	 * @ʱ �䣺����8:05:10
	 */
	private void get_login_number() {
		Cursor cursor = db.rawQuery("select * from login_number", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			if (cursor.getString(cursor.getColumnIndex("number")).toString()
					.equals("0")) {
				tv_login_number.setVisibility(View.INVISIBLE);
			} else {
				int number = Integer.valueOf(cursor.getString(cursor
						.getColumnIndex("number")));
				String time = cursor.getString(cursor.getColumnIndex("time"));
				tv_login_number.setText("֮ǰ����" + String.valueOf(number)
						+ "�ε�¼���ϴε�¼ʱ��Ϊ" + time);
			}
		}
	}

	/*
	 * @��������set_last_number
	 * 
	 * @�� �ܣ����ü�ס���빦��
	 * 
	 * @ʱ �䣺����8:24:16
	 */
	private void set_last_number(String ip, String port) {
		sharedPreferences.edit().putString("ip", ip).putString("port", port)
				.commit();
	}
}
