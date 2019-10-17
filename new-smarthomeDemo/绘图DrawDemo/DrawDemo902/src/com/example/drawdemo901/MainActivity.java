package com.example.drawdemo901;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @��������ɽ���������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-1
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;
	private TextView tv_loading_number;
	private int recLen = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sk_1 = (SeekBar) findViewById(R.id.sk_1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		sk_1.setProgress(0);
		sk_1.setMax(1000);
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����ؽ�����
	 * 
	 * @ʱ �䣺����7:23:27
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			sk_1.setProgress(msg.what);
			tv_loading_number.setText(String.valueOf(msg.what / 10) + "%");
			if (msg.what == 1000) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen += 2;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			if (msg.what > 1000) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
