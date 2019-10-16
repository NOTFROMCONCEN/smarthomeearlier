package com.example.shengsaiademo2017918;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：加载进度条
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-18
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_text);
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
	 * @时 间：下午2:55:34
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			sk_1.setProgress(msg.what);
			switch (msg.what) {
			case 10:
				tv_loading_number.setText("正在加载串口配置......");
				break;
			case 20:
				tv_loading_number.setText("串口配置加载完成......");
				break;
			case 30:
				tv_loading_number.setText("正在加载界面配置......");
				break;
			case 50:
				tv_loading_number.setText("界面配置加载完成......");
				break;
			case 60:
				tv_loading_number.setText("正在初始化界面......");
				break;
			case 80:
				tv_loading_number.setText("界面初始化完成......");
				break;
			case 99:
				tv_loading_number.setText("进入系统中......");
				break;
			case 100:
				Intent intent = new Intent(MainActivity.this,
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
