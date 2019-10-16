package com.example.drawdemo818;

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
 * @描述：对用户完成进度条加载功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-18
 */
public class MainActivity extends Activity {
	private TextView tv_number, tv_state;// 两个显示的文本
	private ProgressBar pg_1;// 进度条
	private int number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_number = (TextView) findViewById(R.id.tv_number);
		tv_state = (TextView) findViewById(R.id.tv_state);
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
	 * @功 能：加载进度条
	 * 
	 * @时 间：上午8:11:04
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);// 设置进度条进度
			String loginString = String.valueOf(msg.what);// 设置文本
			if (msg.what < 100) {
				tv_number.setText(loginString + "%");
			} else {
				tv_number.setText("100%");
			}
			switch (msg.what) {
			case 1:
				tv_state.setText("构建数据库......");
				break;
			case 10:
				tv_state.setText("初始化设备......");
				break;
			case 20:
				tv_state.setText("检查网络连接......");
				break;
			case 30:
				tv_state.setText("与服务器通讯......");
				break;
			case 50:
				tv_state.setText("初始化界面......");
				break;
			case 80:
				tv_state.setText("界面初始化完成......");
				break;
			case 90:
				tv_state.setText("初始化登录功能......");
				break;
			case 100:
				tv_state.setText("加载完成......");
				break;
			case 101:
				// 加载完成
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
			if (number > 101) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
