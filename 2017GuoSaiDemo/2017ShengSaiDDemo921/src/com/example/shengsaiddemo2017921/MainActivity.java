package com.example.shengsaiddemo2017921;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-21
 */
public class MainActivity extends Activity {
	private TextView tv_loading_number;// ���ذٷֱ�
	private int number = 0, RecLen = 0;
	private String point;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
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
	 * @ʱ �䣺����2:45:14
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_loading_number.setText("Loading" + point + msg.what + "%");
			if (msg.what == 100) {
				Intent intent = new Intent(getApplicationContext(),
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
			RecLen++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			switch (RecLen) {
			case 1:
				point = ".";
				break;
			case 10:
				point = "..";
				break;
			case 20:
				point = "...";
				break;
			case 30:
				point = ".";
				break;
			case 40:
				point = "..";
				break;
			case 50:
				point = "...";
				break;
			case 60:
				point = ".";
				break;
			case 70:
				point = "..";
				break;
			case 80:
				point = "...";
				break;
			case 90:
				point = ".";
				break;
			case 99:
				point = "..";
				break;

			default:
				break;
			}
		}
	};

}
