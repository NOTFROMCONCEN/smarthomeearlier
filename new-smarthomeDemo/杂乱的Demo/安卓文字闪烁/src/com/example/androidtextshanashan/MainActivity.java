package com.example.androidtextshanashan;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @文件名：MainActivity.java
 * @描述：完成文字切换
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 * @author Administrator
 */
public class MainActivity extends Activity {
	int number = 0;// int数值
	TextView tv_tips_text;// 文本
	ToggleButton tg_start;// 按钮

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_tips_text = (TextView) findViewById(R.id.tv_tips_text);
		tg_start = (ToggleButton) findViewById(R.id.tg_start);
		// 设置开关点击事件
		tg_start.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					handler.post(timeRunnable);
				} else {
					handler.removeCallbacks(timeRunnable);
					tv_tips_text.setText("你好世界");
				}
			}
		});
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
	 * @功 能：切换文字显示内容
	 * 
	 * @时 间：上午10:59:01
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what % 2 == 0) {
				tv_tips_text.setText("你好世界");
			} else {
				tv_tips_text.setText("Hello world!");
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};

}
