package com.example.shengsaicdemo10072017;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @����������ơ����ء����ֱ仯
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
 */
public class MainActivity extends Activity {
	private TextView tv_center_loading;// �����ı�
	private TextView tv_loading_number;// ���½Ǽ����ı�
	private int number;// ����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_center_loading = (TextView) findViewById(R.id.tv_center_tips);
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

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			tv_loading_number.setText("Loading..." + String.valueOf(msg.what)
					+ "%");
			if (msg.what == 100) {
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
				finish();
			}
			handler.postDelayed(timeRunnable, 8);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 100) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
			switch (msg.what) {
			case 1:
				tv_center_loading.setText("���ڼ��أ����Ժ�.");
				break;
			case 10:
				tv_center_loading.setText("���ڼ��أ����Ժ�..");
				break;
			case 20:
				tv_center_loading.setText("���ڼ��أ����Ժ�...");
				break;
			case 30:
				tv_center_loading.setText("���ڼ��أ����Ժ�.");
				break;
			case 40:
				tv_center_loading.setText("���ڼ��أ����Ժ�..");
				break;
			case 50:
				tv_center_loading.setText("���ڼ��أ����Ժ�...");
				break;
			case 60:
				tv_center_loading.setText("���ڼ��أ����Ժ�.");
				break;
			case 70:
				tv_center_loading.setText("���ڼ��أ����Ժ�..");
				break;
			case 80:
				tv_center_loading.setText("���ڼ��أ����Ժ�...");
				break;
			case 90:
				tv_center_loading.setText("���ڼ��أ����Ժ�.");
				break;
			case 99:
				tv_center_loading.setText("���ڼ��أ����Ժ�..");
				break;
			default:
				break;
			}
		}
	};

}
