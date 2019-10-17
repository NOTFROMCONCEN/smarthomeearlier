package com.example.guosaiademo2017;

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
 * @ʱ�䣺2019-8-29
 */
public class MainActivity extends Activity {
	private ProgressBar pg_1;// ������
	private TextView tv_number;// �ı�
	private int number = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_number = (TextView) findViewById(R.id.tv_number);
		pg_1.setMax(100);
		pg_1.setProgress(0);
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
	 * @ʱ �䣺����9:24:34
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 1:
				DiyToast.showToast(MainActivity.this, "���ڼ��ش�������......");
				tv_number.setText("���ڼ��ش�������......");
				break;
			case 20:
				DiyToast.showToast(MainActivity.this, "�������ü������......");
				tv_number.setText("�������ü������......");
				break;
			case 30:
				DiyToast.showToast(MainActivity.this, "���ڼ��ؽ�������......");
				tv_number.setText("���ڼ��ؽ�������......");
				break;
			case 50:
				DiyToast.showToast(MainActivity.this, "���ڼ�����������......");
				tv_number.setText("���ڼ�����������......");
				break;
			case 60:
				DiyToast.showToast(MainActivity.this, "�������ü������......");
				tv_number.setText("�������ü������......");
				break;
			case 80:
				DiyToast.showToast(MainActivity.this, "���ڳ�ʼ������......");
				tv_number.setText("���ڳ�ʼ������......");
				break;
			case 100:
				DiyToast.showToast(MainActivity.this, "�����ʼ�����......");
				tv_number.setText("�����ʼ�����......");
				break;
			case 101:
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
			handler.postDelayed(timeRunnable, 100);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what > 101) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
