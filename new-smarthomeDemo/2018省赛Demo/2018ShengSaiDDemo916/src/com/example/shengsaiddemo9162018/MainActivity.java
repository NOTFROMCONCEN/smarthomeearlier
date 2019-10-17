package com.example.shengsaiddemo9162018;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @��������ɽ��ȼ��ع���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-16
 */
public class MainActivity extends Activity {
	private TextView tv_number_loading, tv_main_text;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_number_loading = (TextView) findViewById(R.id.tv_loading_number);
		tv_main_text = (TextView) findViewById(R.id.tv_main_text);
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
	 * @ʱ �䣺����2:44:22
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_number_loading.setText("Loading..." + String.valueOf(msg.what)
					+ "%");// ��������
			switch (msg.what) {
			case 2:
				tv_main_text.setText("���ڼ��أ����Ժ�.");
				break;
			case 33:
				tv_main_text.setText("���ڼ��أ����Ժ�..");
				break;
			case 66:
				tv_main_text.setText("���ڼ��أ����Ժ�...");
				break;
			default:
				break;
			}
			handler.postDelayed(timeRunnable, 50);
		}
	};
	Runnable timeRunnable = new Runnable() {
		public void run() {
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				// ��ת��ֹͣ����
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}
