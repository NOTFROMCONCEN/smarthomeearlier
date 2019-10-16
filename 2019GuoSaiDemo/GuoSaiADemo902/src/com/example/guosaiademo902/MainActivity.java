package com.example.guosaiademo902;

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
 * @��������ɽ���������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-2
 */
public class MainActivity extends Activity {
	private ProgressBar progressBar;
	private TextView tv_loading_text;
	private int recLen = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		progressBar.setMax(1000);
		progressBar.setProgress(0);
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
	 * @ʱ �䣺����8:41:17
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressBar.setProgress(msg.what);
			int number = msg.what;
			switch (msg.what) {
			case 2:
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
			case 1000:
				tv_loading_text.setText("����ϵͳ��......");
				break;
			default:
				break;
			}
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
