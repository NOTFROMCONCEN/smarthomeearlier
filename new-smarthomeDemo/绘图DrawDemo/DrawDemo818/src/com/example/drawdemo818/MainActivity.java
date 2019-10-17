package com.example.drawdemo818;

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
 * @���������û���ɽ��������ع���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-18
 */
public class MainActivity extends Activity {
	private TextView tv_number, tv_state;// ������ʾ���ı�
	private ProgressBar pg_1;// ������
	private int number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_number = (TextView) findViewById(R.id.tv_number);
		tv_state = (TextView) findViewById(R.id.tv_state);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
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
	 * @ʱ �䣺����8:11:04
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);// ���ý���������
			String loginString = String.valueOf(msg.what);// �����ı�
			if (msg.what < 100) {
				tv_number.setText(loginString + "%");
			} else {
				tv_number.setText("100%");
			}
			switch (msg.what) {
			case 1:
				tv_state.setText("�������ݿ�......");
				break;
			case 10:
				tv_state.setText("��ʼ���豸......");
				break;
			case 20:
				tv_state.setText("�����������......");
				break;
			case 30:
				tv_state.setText("�������ͨѶ......");
				break;
			case 50:
				tv_state.setText("��ʼ������......");
				break;
			case 80:
				tv_state.setText("�����ʼ�����......");
				break;
			case 90:
				tv_state.setText("��ʼ����¼����......");
				break;
			case 100:
				tv_state.setText("�������......");
				break;
			case 101:
				// �������
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
			if (number > 101) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
