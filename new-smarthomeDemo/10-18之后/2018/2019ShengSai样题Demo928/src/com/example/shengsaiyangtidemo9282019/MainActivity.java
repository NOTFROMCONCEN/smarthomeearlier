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
 * @文件名：MainActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-28
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;// 滑动解锁
	private Button btn_login;// 登录按钮
	private EditText et_ip;// IP地址
	private EditText et_port;// 端口号
	static String ip, port;
	private TextView tv_login_number;// 登录次数
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// 记住密码
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
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 记住输入的参数
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_port.setText(sharedPreferences.getString("port", null));
		}
		// 程序启动时获取登录次数
		get_login_number();
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();
				port = et_port.getText().toString();
				if (ip.isEmpty() || port.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP或端口号不能为空");
				} else {
					set_login_number();// 插入
					get_login_number();// 读取
					set_last_number(ip, port);// 设置记住密码
					// 跳转
					Intent intent = new Intent(getApplicationContext(),
							BaseActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 设置滑动栏参数
		sk_1.setProgress(0);
		sk_1.setMax(100);
		// 滑动栏滑动时事件
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();
				port = et_port.getText().toString();
				if (seekBar.getProgress() > 68 && seekBar.getProgress() < 74) {
					if (et_ip.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(),
								"IP或端口号不能为空");
						sk_1.setProgress(0);
						btn_login.setVisibility(View.VISIBLE);
					} else {
						set_login_number();// 插入
						get_login_number();// 读取
						set_last_number(et_ip.getText().toString(), et_port
								.getText().toString());// 设置记住密码
						// 跳转
						Intent intent = new Intent(getApplicationContext(),
								BaseActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					sk_1.setProgress(0);
					DiyToast.showToast(getApplicationContext(), "请滑动完成验证");
					btn_login.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度条改变
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
	 * @方法名：set_login_number
	 * 
	 * @功 能：登录时插入登录次数
	 * 
	 * @时 间：上午8:10:45
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
	 * @方法名：get_login_number
	 * 
	 * @功 能：获取登录次数
	 * 
	 * @时 间：上午8:05:10
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
				tv_login_number.setText("之前已有" + String.valueOf(number)
						+ "次登录，上次登录时间为" + time);
			}
		}
	}

	/*
	 * @方法名：set_last_number
	 * 
	 * @功 能：设置记住密码功能
	 * 
	 * @时 间：上午8:24:16
	 */
	private void set_last_number(String ip, String port) {
		sharedPreferences.edit().putString("ip", ip).putString("port", port)
				.commit();
	}
}
