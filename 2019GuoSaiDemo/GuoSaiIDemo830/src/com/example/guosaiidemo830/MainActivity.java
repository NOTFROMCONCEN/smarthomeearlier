package com.example.guosaiidemo830;

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
 * @时间：2019-8-30
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private int number = 0;
	private String loading;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("智能家居");
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
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
	 * @时 间：上午8:13:16
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 1:
				loading = ("正在初始化");
				break;
			case 21:
				loading = ("正在载入系统");
				break;
			case 51:
				loading = ("正在连接服务器");
				break;
			case 100:
				// 跳转到登录
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}
			tv_loading_text.setText(loading + "......"
					+ String.valueOf(msg.what) + "%");
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
