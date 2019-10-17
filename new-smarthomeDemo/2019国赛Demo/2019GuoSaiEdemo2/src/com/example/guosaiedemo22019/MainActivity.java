package com.example.guosaiedemo22019;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lib.SocketThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private Button btn_login;
	private EditText et_port, et_ip;
	private String port, ip;
	private TextView tv_num_state;
	private SeekBar sk_1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private int recLen = 0;
	Double num;
	private String time, gettime, getnum;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 绑定控件
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_port = (EditText) findViewById(R.id.et_port);
		tv_num_state = (TextView) findViewById(R.id.tv_state);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setOnSeekBarChangeListener(this);
		// 绑定数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 新建数据库指针用于记录登录次数功能
		Cursor cursor = db.rawQuery("select * from user", null);
		DecimalFormat df = new DecimalFormat("##0");

		if (cursor.getCount() != 0) {
			// 如果数据库有数据，指针上移
			cursor.moveToLast();
			// 得到数据库内部数据并转换为String数值
			getnum = cursor.getString(cursor.getColumnIndex("loginnum"));
			gettime = cursor.getString(cursor.getColumnIndex("logintime"));
			// 将登录次数String数值转换为整数
			num = Double.valueOf(getnum);
			// 设置文本
			tv_num_state.setText("之前已有" + df.format(num) + "次登录，上次登录时间为"
					+ gettime);
		} else {
			// 如果数据为空，防止报错将文本设置为固定值
			tv_num_state.setText("之前已有null次登录，上次登录时间为HH:mm");
		}
		// 新建SharedPreferences,用于记住上次输入的数值
		final SharedPreferences rember = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (rember != null) {
			String autoip, autoport;
			autoip = rember.getString("ip", null);
			autoport = rember.getString("port", null);
			// 比较boolean值
			if (rember.getBoolean("rember", false) == true) {
				et_ip.setText(autoip);
				et_port.setText(autoport);
			} else {
				et_ip.setText("");
				et_port.setText("");
			}
		} else {
			et_ip.setText("");
			et_port.setText("");
		}
		// 登陆功能
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				if (port.equals("")) {
					Toast.makeText(MainActivity.this, "请输入服务器端口号",
							Toast.LENGTH_SHORT).show();
				} else if (ip.equals("")) {
					Toast.makeText(MainActivity.this, "请输入服务器IP地址",
							Toast.LENGTH_SHORT).show();
				} else {
					if (sk_1.getProgress() == sk_1.getMax()) {
						num++;
						String setnumString = Double.toString(num);
						db.execSQL(
								"insert into user (loginnum,logintime)values(?,?)",
								new String[] { setnumString, time });
						Toast.makeText(MainActivity.this, "登录完成",
								Toast.LENGTH_SHORT).show();
						rember.edit().putBoolean("rember", true)
								.putString("port", port).putString("ip", ip)
								.commit();
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf(port);
						Intent intent = new Intent(MainActivity.this,
								BaseActivity.class);
						startActivity(intent);
						finish();
					} else {
						Toast.makeText(MainActivity.this, "请滑动完成验证",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// 获取当前时间的Handler进程
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			time = formater.format(new java.util.Date());
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

	// SeekBar功能的实现
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		if (seekBar.getProgress() != seekBar.getMax()) {
			Toast.makeText(MainActivity.this, "请滑动完成验证", Toast.LENGTH_SHORT)
					.show();
			sk_1.setProgress(0);
		}
	}
}
