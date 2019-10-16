package com.example.drawdemo816;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：进度条加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-16
 */
public class MainActivity extends Activity {
	private TextView tv_number;// 文本
	private ProgressBar pg_1;// 进度条
	private String number;// String数值
	private int i = 0;// int数值

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_number = (TextView) findViewById(R.id.tv_number);
		pg_1 = (ProgressBar) findViewById(R.id.pg_1);
		handler.post(timeRunnable);
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
	 * @功 能：加载进度条进度
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			number = String.valueOf(msg.what);
			tv_number.setText(number + "%");
			handler.postDelayed(timeRunnable, 100);
			if (msg.what == 100) {
				// 当进度达到100时，进入下一个界面
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
			}
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			i++;
			Message msg = handler.obtainMessage();
			msg.what = i;
			if (i > 101) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
