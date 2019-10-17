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
 * @文件名：MainActivity.java
 * @描述：完成登录、记住登录时间
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-25
 */
public class MainActivity extends Activity {
	private EditText et_port, et_ip;
	private Button btn_login;
	private SeekBar sk_1;
	private TextView tv_number_text;
	private String time;
	public static String IP_NUMBER;
	// 数据库
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
		// SeekBar滑动功能
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				if (seekBar.getProgress() > 69 && seekBar.getProgress() < 74) {
					if (et_ip.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()) {// 如有空白项目
						DiyToast.showTaost(getApplicationContext(),
								"IP地址和端口号不能为空");
						sk_1.setProgress(0);
						btn_login.setVisibility(View.VISIBLE);
					} else {
						Cursor cursor = db.rawQuery(
								"select * from login_rember", null);// 新建数据库游标
						if (cursor.getCount() != 0) {
							// 游标移动到最后
							cursor.moveToLast();
							int string = Integer.valueOf(cursor
									.getString(cursor
											.getColumnIndex("login_number")));// 取出数值转换为int数值
							System.out.println(string);
							string++;// 自身加1
							// 插入数据库
							db.execSQL(
									"insert into login_rember (login_time,login_number)values(?,?)",
									new String[] { time, String.valueOf(string) });
							// 传输IP地址
							IP_NUMBER = et_ip.getText().toString();
							// 登陆成功，跳转
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
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度条改变
				// TODO Auto-generated method stub
				// 进度条改变||使用进度条完成登录时隐藏登录按钮
				if (sk_1.getProgress() > 2) {
					btn_login.setVisibility(View.INVISIBLE);
				}
			}
		});

		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 登录按钮点击时隐藏SeekBar
				sk_1.setVisibility(View.INVISIBLE);
				if (et_port.getText().toString().isEmpty()) {// 如果端口号为空
					DiyToast.showTaost(getApplicationContext(), "请输入端口号");
				} else if (et_ip.getText().toString().isEmpty()) {// 如果IP地址为空
					DiyToast.showTaost(getApplicationContext(), "请输入IP地址");
				} else {
					Cursor cursor = db.rawQuery("select * from login_rember",
							null);// 新建数据库游标
					if (cursor.getCount() != 0) {
						// 游标移动到最后
						cursor.moveToLast();
						int string = Integer.valueOf(cursor.getString(cursor
								.getColumnIndex("login_number")));// 取出数值转换为int数值
						System.out.println(string);
						string++;// 自身加1
						// 插入数据库
						db.execSQL(
								"insert into login_rember (login_time,login_number)values(?,?)",
								new String[] { time, String.valueOf(string) });
						// 传输IP地址
						IP_NUMBER = et_ip.getText().toString();
						// 登陆成功，跳转
						Intent intent = new Intent(getApplicationContext(),
								BaseActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：get_login_number
	 * 
	 * @功 能：获取上次登录次数、时间
	 * 
	 * @时 间：上午8:08:05
	 */
	private void get_login_number() {
		Cursor cursor = db.rawQuery("select * from login_rember", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			if (cursor.getString(cursor.getColumnIndex("login_number"))
					.toString().equals("0")) {
				DiyToast.showTaost(getApplicationContext(), "首次开启程序");
				tv_number_text.setText("");
			} else {
				tv_number_text
						.setText("之前已有"
								+ cursor.getString(cursor
										.getColumnIndex("login_number"))
								+ "次登录"
								+ "，上次登录时间为"
								+ cursor.getString(cursor
										.getColumnIndex("login_time")));
			}
		}
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：上午8:08:27
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
