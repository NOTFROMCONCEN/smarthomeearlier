package com.example.dataupdatanumber;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv_number;
	private Random random = new Random();
	private int max = 100;
	private int min = 1;
	private int number;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_number = (TextView) findViewById(R.id.tv_number);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 激活进程
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			number = random.nextInt(max) % (max - min + 1) + min;
			tv_number.setText(String.valueOf(number));
			DiyToast.showToast(MainActivity.this,
					"随机数已生成" + String.valueOf(number));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			String time = sdf.format(new java.util.Date());
			db.execSQL("insert into data (time,number)values(?,?)",
					new String[] { time, String.valueOf(number) });
			handler.postDelayed(timeRunnable, 100);
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
