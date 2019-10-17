package com.example.shengsaid9202017;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @���������ȼ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class MainActivity extends Activity {
	private TextView tv_loading_number;
	private TextView tv_loading_tips;
	private int number = 0;
	private int tips_number = 0;
	private String tips_point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		tv_loading_tips = (TextView) findViewById(R.id.tv_loading_tips);
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
			tv_loading_number.setText("Loading" + tips_point
					+ String.valueOf(msg.what));
			if (msg.what == 100) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 50);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			tips_number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			if (tips_number == 10) {
				tips_point = ".";

			}
			if (tips_number == 20) {
				tips_point = "..";

			}
			if (tips_number == 30) {
				tips_point = "...";

			}
			if (tips_number == 40) {
				tips_point = ".";

			}
			if (tips_number == 50) {
				tips_point = "..";

			}
			if (tips_number == 60) {
				tips_point = "...";

			}
			if (tips_number == 70) {
				tips_point = ".";

			}
			if (tips_number == 80) {
				tips_point = "..";

			}
			if (tips_number == 90) {
				tips_point = "...";

			}
			if (tips_number == 100) {
				tips_point = ".";

			}
		}
	};

}
