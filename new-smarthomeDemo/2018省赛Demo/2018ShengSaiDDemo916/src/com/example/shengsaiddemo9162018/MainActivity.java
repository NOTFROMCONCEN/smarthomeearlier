package com.example.shengsaiddemo9162018;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：完成进度加载功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class MainActivity extends Activity {
	private TextView tv_number_loading, tv_main_text;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_number_loading = (TextView) findViewById(R.id.tv_loading_number);
		tv_main_text = (TextView) findViewById(R.id.tv_main_text);
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
	 * @时 间：下午2:44:22
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_number_loading.setText("Loading..." + String.valueOf(msg.what)
					+ "%");// 设置文字
			switch (msg.what) {
			case 2:
				tv_main_text.setText("正在加载，请稍后.");
				break;
			case 33:
				tv_main_text.setText("正在加载，请稍后..");
				break;
			case 66:
				tv_main_text.setText("正在加载，请稍后...");
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 50);
		}
	};
	Runnable timeRunnable = new Runnable() {
		public void run() {
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				// 跳转并停止进程
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
