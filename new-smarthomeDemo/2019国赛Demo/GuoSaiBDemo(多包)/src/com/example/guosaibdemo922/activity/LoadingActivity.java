package com.example.guosaibdemo922.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.guosaibdemo922.R;
import com.example.guosaibdemo922.helpclass.DiyToast;
import com.example.guosaibdemo922.helpclass.MyDataBaseHelper;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @文件名：LoadingActivity.java
 * @描述：进度条加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class LoadingActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载进度
	 * 
	 * @时 间：上午7:56:40
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			tv_loading_number.setText(String.valueOf(msg.what) + "%");
			if (msg.what == 100) {
				new AlertDialog.Builder(LoadingActivity.this)
						.setTitle("登录成功")
						.setMessage("欢迎回来")
						.setPositiveButton("OK",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												LoadingActivity.this,
												Index_Room_Activity.class);
										startActivity(intent);
										finish();
									}
								}).show();
			}
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
