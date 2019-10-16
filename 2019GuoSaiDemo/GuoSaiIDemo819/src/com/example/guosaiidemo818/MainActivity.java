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
 * @�ļ�����MainActivity.java
 * @���������û����ؽ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-18
 */
public class MainActivity extends Activity {
	private ProgressBar progressBar;// ������
	private TextView tv_text_loading;// �ı�
	private int number = 0;
	private String loading_text, loading_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		tv_text_loading = (TextView) findViewById(R.id.tv_text_loading);
		handler.post(timeRunnable);// �������
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
	 * @ʱ �䣺����10:10:35
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			progressBar.setProgress(msg.what);
			loading_number = String.valueOf(msg.what);
			// ������ʾ����
			if (msg.what > 0 && msg.what < 20) {
				loading_text = "���ڳ�ʼ��";
			}
			if (msg.what > 20 && msg.what < 50) {
				loading_text = "��������ϵͳ";
			}
			if (msg.what > 50 && msg.what < 99) {
				loading_text = "�������ӷ�����";
			}
			if (msg.what == 100) {
				loading_text = "�������ӷ�����";
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
			Log.e("����", String.valueOf(number));
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
