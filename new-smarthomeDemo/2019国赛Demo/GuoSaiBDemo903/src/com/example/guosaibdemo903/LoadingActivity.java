package com.example.guosaibdemo903;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

/*
 * @�ļ�����LoadingActivity.java
 * @��������ɽ���������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-3
 */
public class LoadingActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_number;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		tv_loading_number = (TextView) findViewById(R.id.tv_loading_number);
		// �������
		handler.post(timeRunnable);
		// ���ý���������
		pg_1.setProgress(0);
		pg_1.setMax(1000);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����ؽ�����
	 * 
	 * @ʱ �䣺����8:20:53
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);// ���ý���������
			tv_loading_number.setText(String.valueOf(msg.what / 10) + "%");// ������ʾ����
			if (msg.what == 1000) {
				// ����ӭ��������ʾ��
				new AlertDialog.Builder(LoadingActivity.this)
						.setTitle("��¼�ɹ�")
						.setMessage("��ӭ����")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub
										Intent intent = new Intent(
												LoadingActivity.this,
												IndexActivity.class);
										startActivity(intent);
										finish();
									}
								}).show();
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
