package com.example.guosaibdemo826newlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;

/*
 * @文件名：LoadingActivity.java
 * @描述：
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-26
 */
public class LoadingActivity extends Activity {
	private int number = 0;
	private ProgressBar pg_1;
	private TextView tv_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		tv_number = (TextView) findViewById(R.id.tv_loading_text);
		pg_1 = (ProgressBar) findViewById(R.id.pg_1_loading);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.arg1);
			tv_number.setText(String.valueOf(msg.arg1));
			if (msg.arg1 > 100) {
				Intent intent = new Intent(LoadingActivity.this,
						IndexActivity.class);
				startActivity(intent);
				finish();
				handler.removeCallbacks(timeRunnable);
			}
			handler.postDelayed(timeRunnable, 100);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			msg.arg1 = number;
			number++;
			handler.sendMessage(msg);
		}
	};
}
