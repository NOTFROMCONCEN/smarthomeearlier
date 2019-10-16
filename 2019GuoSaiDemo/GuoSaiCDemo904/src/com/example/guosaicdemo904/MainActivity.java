package com.example.guosaicdemo904;

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
 * @���������ؽ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-4
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		// �������
		handler.post(timeRunnable);
		// ���ý���������
		pg_1.setMax(1000);
		pg_1.setProgress(0);
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
	 * @ʱ �䣺����8:07:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);// ���ý���
			switch (msg.what) {// ����������ʾ
			case 10:
				tv_loading_text.setText("���ڼ��ش�������......");
				break;
			case 200:
				tv_loading_text.setText("�������ü������......");
				break;
			case 300:
				tv_loading_text.setText("���ڼ��ؽ�������......");
				break;
			case 500:
				tv_loading_text.setText("�������ü������......");
				break;
			case 600:
				tv_loading_text.setText("���ڳ�ʼ������......");
				break;
			case 800:
				tv_loading_text.setText("�����ʼ�����......");
				break;
			case 990:
				tv_loading_text.setText("����ϵͳ��......");
				break;
			case 1000:// ������Ϊ100ʱ��ת
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number += 5;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 1000) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
