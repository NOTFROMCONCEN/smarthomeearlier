package com.example.guosaicdemo904;

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
 * @描述：加载进度条
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-4
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		// 激活进程
		handler.post(timeRunnable);
		// 设置进度条参数
		pg_1.setMax(1000);
		pg_1.setProgress(0);
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
	 * @功 能：加载进度条
	 * 
	 * @时 间：上午8:07:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);// 设置进度
			switch (msg.what) {// 设置文字显示
			case 10:
				tv_loading_text.setText("正在加载串口配置......");
				break;
			case 200:
				tv_loading_text.setText("串口配置加载完成......");
				break;
			case 300:
				tv_loading_text.setText("正在加载界面配置......");
				break;
			case 500:
				tv_loading_text.setText("界面配置加载完成......");
				break;
			case 600:
				tv_loading_text.setText("正在初始化界面......");
				break;
			case 800:
				tv_loading_text.setText("界面初始化完成......");
				break;
			case 990:
				tv_loading_text.setText("进入系统中......");
				break;
			case 1000:// 进度条为100时跳转
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number += 5;
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
