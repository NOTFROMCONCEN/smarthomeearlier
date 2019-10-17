package com.example.guosaibdemo905;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @文件名：LoadingActivity.java
 * @描述：完成进度条加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
 */
public class LoadingActivity extends Activity {
	private TextView tv_loading_text;
	private ProgressBar pg_1;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("载入中......");
		setContentView(R.layout.activity_loading);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		// 设置进度条参数
		pg_1.setMax(1000);
		pg_1.setProgress(0);
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载进度条
	 * 
	 * @时 间：上午8:16:08
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);// 设置进度
			tv_loading_text.setText(String.valueOf(msg.what / 10) + "%");
			if (msg.what == 1000) {
				new AlertDialog.Builder(LoadingActivity.this)
						.setTitle("登陆成功")
						.setMessage("欢迎回来")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												LoadingActivity.this,
												IndexActivity.class);
										startActivity(intent);
										finish();
									}
								}).show();
			}
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number += 10;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 1000) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
