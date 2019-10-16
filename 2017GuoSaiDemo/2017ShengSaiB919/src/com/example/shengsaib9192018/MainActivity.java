package com.example.shengsaib9192018;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：完成进度加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class MainActivity extends Activity {
	private TextView tv_loading_text;
	private int number = 0, point = 0;
	private String text_poit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);

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
			tv_loading_text.setText("正在加载，请稍后" + text_poit + "    "
					+ String.valueOf(msg.what) + "%");
			if (msg.what == 100) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			point++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			if (point == 1) {
				text_poit = ".";
			}
			if (point == 20) {
				text_poit = "..";
			}
			if (point == 30) {
				text_poit = "...";
			}
			if (point == 40) {
				text_poit = ".";
			}
			if (point == 50) {
				text_poit = "..";
			}
			if (point == 60) {
				text_poit = "...";
			}
			if (point == 70) {
				text_poit = ".";
			}
			if (point == 80) {
				text_poit = "..";
			}
			if (point == 90) {
				text_poit = "...";
			}
			if (point == 100) {
				text_poit = ".";
			}
		}
	};

}
