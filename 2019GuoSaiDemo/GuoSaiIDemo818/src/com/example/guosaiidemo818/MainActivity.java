package com.example.guosaiidemo818;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：对用户加载进度条
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-18
 */
public class MainActivity extends Activity {
	private ProgressBar progressBar;// 进度条
	private TextView tv_text_loading;// 文本
	private int number = 0;
	private String loading_text, loading_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		tv_text_loading = (TextView) findViewById(R.id.tv_text_loading);
		handler.post(timeRunnable);// 激活进程
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
	 * @时 间：上午10:10:35
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressBar.setProgress(msg.what);
			loading_number = String.valueOf(msg.what);
			// 设置提示文字
			if (msg.what > 0 && msg.what < 20) {
				loading_text = "正在初始化";
			}
			if (msg.what > 20 && msg.what < 50) {
				loading_text = "正在载入系统";
			}
			if (msg.what > 50 && msg.what < 99) {
				loading_text = "正在连接服务器";
			}
			if (msg.what == 100) {
				loading_text = "正在连接服务器";
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			tv_text_loading.setText(loading_text + "......" + loading_number
					+ "%");
			handler.postDelayed(timeRunnable, 100);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Log.e("进度", String.valueOf(number));
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what < 102) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(timeRunnable);
			}
		}
	};
}
