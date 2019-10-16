package com.example.shengsaib9192018;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @��������ɽ��ȼ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class MainActivity extends Activity {
	private TextView tv_loading_text;
	private int number = 0, point = 0;
	private String text_poit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);

		// �������
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
			tv_loading_text.setText("���ڼ��أ����Ժ�" + text_poit + "    "
					+ String.valueOf(msg.what) + "%");
			if (msg.what == 100) {
				Intent intent = new Intent(MainActivity.this,
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
			point++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			if (point == 1) {
				text_poit = ".";
			}
			if (point == 20) {
				text_poit = "..";
			}
			if (point == 30) {
				text_poit = "...";
			}
			if (point == 40) {
				text_poit = ".";
			}
			if (point == 50) {
				text_poit = "..";
			}
			if (point == 60) {
				text_poit = "...";
			}
			if (point == 70) {
				text_poit = ".";
			}
			if (point == 80) {
				text_poit = "..";
			}
			if (point == 90) {
				text_poit = "...";
			}
			if (point == 100) {
				text_poit = ".";
			}
		}
	};

}
