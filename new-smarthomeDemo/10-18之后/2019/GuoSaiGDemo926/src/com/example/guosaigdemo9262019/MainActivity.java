package com.example.guosaigdemo9262019;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract.CommonDataKinds.Im;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @����������������ʱ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-26
 */
public class MainActivity extends Activity {
	private ImageView iv_logo;
	private TextView tv_loading_text;
	private int number = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_loading_text = (TextView) findViewById(R.id.tv_number);
		iv_logo = (ImageView) findViewById(R.id.iv_logo);
		LoginActivity.sharedPreferences = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (LoginActivity.sharedPreferences.getBoolean("autologin", false) == true) {
			LoginActivity.IP_NUMBER = LoginActivity.sharedPreferences
					.getString("ip", null);
			Intent intent = new Intent(MainActivity.this, BarActivity.class);
			startActivity(intent);
			finish();
		} else {
			// �������
			handler.post(timeRunnable);
		}
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
	 * @�� �ܣ�����ʱ
	 * 
	 * @ʱ �䣺����2:55:49
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_loading_text.setText(String.valueOf(msg.what) + "�������¼���档����");
			if (msg.what == 0) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number--;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what < 0) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
