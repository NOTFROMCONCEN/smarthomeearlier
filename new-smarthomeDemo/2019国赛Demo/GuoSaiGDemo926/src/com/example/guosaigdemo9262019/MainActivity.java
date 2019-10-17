package com.example.guosaigdemo9262019;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：动画、倒计时
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class MainActivity extends Activity {
	private ImageView iv_logo;
	private TextView tv_loading_text;
	private int number = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_text = (TextView) findViewById(R.id.tv_number);
		iv_logo = (ImageView) findViewById(R.id.iv_logo);
		LoginActivity.sharedPreferences = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (LoginActivity.sharedPreferences.getBoolean("autologin", false) == true) {
			LoginActivity.IP_NUMBER = LoginActivity.sharedPreferences
					.getString("ip", null);
			Intent intent = new Intent(MainActivity.this, BarActivity.class);
			startActivity(intent);
			finish();
		} else {
			// 激活进程
			handler.post(timeRunnable);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：倒计时
	 * 
	 * @时 间：下午2:55:49
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_loading_text.setText(String.valueOf(msg.what) + "秒后进入登录界面。。。");
			if (msg.what == 0) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number--;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what < 0) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
