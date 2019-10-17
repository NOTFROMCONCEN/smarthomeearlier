package com.example.guosaicdemo923;

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
 * @时间：2019-9-23
 */
public class MainActivity extends Activity {
	private TextView tv_loading_number;
	private ProgressBar pg_1;
	private int number = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		// 激活进程
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
	 * @功 能：加载进度
	 * 
	 * @时 间：上午8:05:19
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 10:
				tv_loading_number.setText("正在加载串口配置...........");
				break;
			case 20:
				tv_loading_number.setText("串口配置加载完成...........");
				break;
			case 30:
				tv_loading_number.setText("正在加载界面配置...........");
				break;
			case 50:
				tv_loading_number.setText("界面配置加载完成...........");
				break;
			case 60:
				tv_loading_number.setText("正在初始化界面..........");
				break;
			case 80:
				tv_loading_number.setText("界面初始化完成..........");
				break;
			case 99:
				tv_loading_number.setText("进入系统中......");
				break;
			case 100:
				Intent intent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 50);
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
