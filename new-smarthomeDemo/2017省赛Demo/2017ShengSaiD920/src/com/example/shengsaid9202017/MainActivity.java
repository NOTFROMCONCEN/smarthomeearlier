package com.example.shengsaid9202017;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：进度加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class MainActivity extends Activity {
	private TextView tv_loading_number;
	private TextView tv_loading_tips;
	private int number = 0;
	private int tips_number = 0;
	private String tips_point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		tv_loading_tips = (TextView) findViewById(R.id.tv_loading_tips);
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
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			tv_loading_number.setText("Loading" + tips_point
					+ String.valueOf(msg.what));
			if (msg.what == 100) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 50);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			tips_number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			if (tips_number == 10) {
				tips_point = ".";

			}
			if (tips_number == 20) {
				tips_point = "..";

			}
			if (tips_number == 30) {
				tips_point = "...";

			}
			if (tips_number == 40) {
				tips_point = ".";

			}
			if (tips_number == 50) {
				tips_point = "..";

			}
			if (tips_number == 60) {
				tips_point = "...";

			}
			if (tips_number == 70) {
				tips_point = ".";

			}
			if (tips_number == 80) {
				tips_point = "..";

			}
			if (tips_number == 90) {
				tips_point = "...";

			}
			if (tips_number == 100) {
				tips_point = ".";

			}
		}
	};

}
