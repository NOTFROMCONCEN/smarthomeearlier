package com.example.guosaiademo2017;

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
 * @描述：完成进度条加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;// 进度条
	private TextView tv_number;// 文本
	private int number = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_number = (TextView) findViewById(R.id.tv_number);
		pg_1.setMax(100);
		pg_1.setProgress(0);
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
	 * @功 能：加载进度条
	 * 
	 * @时 间：上午9:24:34
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 1:
				DiyToast.showToast(MainActivity.this, "正在加载串口配置......");
				tv_number.setText("正在加载串口配置......");
				break;
			case 20:
				DiyToast.showToast(MainActivity.this, "串口配置加载完成......");
				tv_number.setText("串口配置加载完成......");
				break;
			case 30:
				DiyToast.showToast(MainActivity.this, "正在加载界面配置......");
				tv_number.setText("正在加载界面配置......");
				break;
			case 50:
				DiyToast.showToast(MainActivity.this, "正在加载网络配置......");
				tv_number.setText("正在加载网络配置......");
				break;
			case 60:
				DiyToast.showToast(MainActivity.this, "网络配置加载完成......");
				tv_number.setText("网络配置加载完成......");
				break;
			case 80:
				DiyToast.showToast(MainActivity.this, "正在初始化界面......");
				tv_number.setText("正在初始化界面......");
				break;
			case 100:
				DiyToast.showToast(MainActivity.this, "界面初始化完成......");
				tv_number.setText("界面初始化完成......");
				break;
			case 101:
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
			handler.postDelayed(timeRunnable, 100);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 101) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
