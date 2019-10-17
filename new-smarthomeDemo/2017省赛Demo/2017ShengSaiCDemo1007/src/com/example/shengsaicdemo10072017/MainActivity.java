package com.example.shengsaicdemo10072017;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：跑马灯、加载、文字变化
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-7
 */
public class MainActivity extends Activity {
	private TextView tv_center_loading;// 中心文本
	private TextView tv_loading_number;// 右下角加载文本
	private int number;// 计数

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_center_loading = (TextView) findViewById(R.id.tv_center_tips);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
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
			tv_loading_number.setText("Loading..." + String.valueOf(msg.what)
					+ "%");
			if (msg.what == 100) {
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
			handler.postDelayed(timeRunnable, 8);
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
			switch (msg.what) {
			case 1:
				tv_center_loading.setText("正在加载，请稍后.");
				break;
			case 10:
				tv_center_loading.setText("正在加载，请稍后..");
				break;
			case 20:
				tv_center_loading.setText("正在加载，请稍后...");
				break;
			case 30:
				tv_center_loading.setText("正在加载，请稍后.");
				break;
			case 40:
				tv_center_loading.setText("正在加载，请稍后..");
				break;
			case 50:
				tv_center_loading.setText("正在加载，请稍后...");
				break;
			case 60:
				tv_center_loading.setText("正在加载，请稍后.");
				break;
			case 70:
				tv_center_loading.setText("正在加载，请稍后..");
				break;
			case 80:
				tv_center_loading.setText("正在加载，请稍后...");
				break;
			case 90:
				tv_center_loading.setText("正在加载，请稍后.");
				break;
			case 99:
				tv_center_loading.setText("正在加载，请稍后..");
				break;
			default:
				break;
			}
		}
	};

}
