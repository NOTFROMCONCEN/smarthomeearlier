package com.example.shengsaiyangjuan9172018;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @����������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-17
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;// ������
	private TextView tv_number_loading;// �ı�
	private int number = 0;// ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_number_loading = (TextView) findViewById(R.id.tv_loading_number);
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
	 * @�� �ܣ����ؽ���
	 * 
	 * @ʱ �䣺����2:43:31
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			tv_number_loading.setText(String.valueOf(msg.what) + "%");
			if (msg.what == 100) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 40);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (number > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
