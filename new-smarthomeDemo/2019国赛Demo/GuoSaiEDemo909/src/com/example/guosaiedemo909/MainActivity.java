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
 * @文件名：MainActivity.java
 * @描述：登录、登录次数统计、SeekBar
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-9
 */
public class MainActivity extends Activity {
	private Button btn_login;// 登录按钮
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private TextView tv_login_number;// 登录次数
	private SeekBar sk_1;// SeekBar
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	public static String ip_number;// String IP地址
	// 时间
	private String login_time;
	// 次数
	private String login_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("SmartHome");
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		initview();
		// 设置按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_port.getText().toString().equals("")) {// 端口为空
					DiyToast.showToast(getApplicationContext(), "请输入端口");
				} else if (et_ip.getText().toString().equals("")) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					// 获取登录次数获取时间并插入数据库
					// 从数据库取出上次登录数据
					Cursor cursor = db.rawQuery("select * from login", null);
					if (cursor.getCount() != 0) {
						cursor.moveToLast();// 数据库移动至最后
						int last_login_time = cursor.getInt(cursor
								.getColumnIndex("login_number"));// 取出数据
						last_login_time++;// 登录时记录上次登录次数+1
						// 插入数据
						db.execSQL(
								"insert into login(login_number,login_time)values(?,?)",
								new String[] { String.valueOf(last_login_time),
										login_time });
					}
					ip_number = et_ip.getText().toString();// 获取IP地址
					Intent intent = new Intent(MainActivity.this,
							BaseActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 设置SeekBar滑动事件
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				// 如果进度大于68小于70
				if (seekBar.getProgress() >= 67 && seekBar.getProgress() <= 72) {
					if (et_port.getText().toString().equals("")) {// 端口为空
						DiyToast.showToast(getApplicationContext(), "请输入端口");
						sk_1.setProgress(0);
					} else if (et_ip.getText().toString().equals("")) {// IP地址为空
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);
					} else {
						// 获取登录次数获取时间并插入数据库
						// 从数据库取出上次登录数据
						Cursor cursor = db
								.rawQuery("select * from login", null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();// 数据库移动至最后
							int last_login_time = cursor.getInt(cursor
									.getColumnIndex("login_number"));// 取出数据
							last_login_time++;// 登录时记录上次登录次数+1
							// 插入数据
							db.execSQL(
									"insert into login(login_number,login_time)values(?,?)",
									new String[] {
											String.valueOf(last_login_time),
											login_time });
						}
						ip_number = et_ip.getText().toString();// 获取IP地址
						Intent intent = new Intent(MainActivity.this,
								BaseActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "请滑动完成验证");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub
				// 如果开始滑动，隐藏登录按钮
				btn_login.setVisibility(View.INVISIBLE);
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度改变
				// TODO Auto-generated method stub
				System.out.println(seekBar.getProgress());
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
	 * @方法名：initview
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:44:55
	 */
	private void initview() {
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		tv_login_number = (TextView) findViewById(R.id.tv_number_text);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// 获取登录次数获取时间并插入数据库
		// 从数据库取出上次登录数据
		Cursor cursor = db.rawQuery("select * from login", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();// 数据库移动至最后
			login_time = cursor.getString(cursor.getColumnIndex("login_time"));// 取出数据
			login_number = cursor.getString(cursor
					.getColumnIndex("login_number"));
			tv_login_number.setText("之前已有" + login_number + "次登录，上次登录时间为"
					+ login_time);
		}
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @参 数：String
	 * 
	 * @时 间：上午8:48:49
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
