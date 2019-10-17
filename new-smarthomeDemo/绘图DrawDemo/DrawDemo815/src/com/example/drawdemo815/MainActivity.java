package com.example.drawdemo815;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;

/*
 * @�ļ�����MainActivity.java
 * @���������û���������ʱ�Ľ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-15
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;// ������
	private int recLen = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/**
		 * �󶨿ؼ�
		 */
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		pg_1.setProgress(0);
		pg_1.setMax(1000);
		handler.post(tiemRunnable);
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
	 * @�� �ܣ����½��������ȵĽ���
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			if (msg.what == 1000) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(tiemRunnable, 1);
		}
	};
	Runnable tiemRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen++;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			if (recLen > 1000) {
				handler.removeCallbacks(tiemRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
