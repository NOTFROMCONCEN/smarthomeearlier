package com.example.shengsaiddemo2017921;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：加载
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-21
 */
public class MainActivity extends Activity {
	private TextView tv_loading_number;// 加载百分比
	private int number = 0, RecLen = 0;
	private String point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载进度
	 * 
	 * @时 间：下午2:45:14
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_loading_number.setText("Loading" + point + msg.what + "%");
			if (msg.what == 100) {
				Intent intent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 10);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			RecLen++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			switch (RecLen) {
			case 1:
				point = ".";
				break;
			case 10:
				point = "..";
				break;
			case 20:
				point = "...";
				break;
			case 30:
				point = ".";
				break;
			case 40:
				point = "..";
				break;
			case 50:
				point = "...";
				break;
			case 60:
				point = ".";
				break;
			case 70:
				point = "..";
				break;
			case 80:
				point = "...";
				break;
			case 90:
				point = ".";
				break;
			case 99:
				point = "..";
				break;

			default:
				break;
			}
		}
	};

}
