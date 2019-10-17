package com.example.drawdemo1003;

import com.example.drawdemo1003.fragment.BarActivity;
import com.example.drawdemo1003.helpclass.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

/*
 * @�ļ�����LoadingActivity.java
 * @���������ؽ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-3
 */
public class LoadingActivity extends Activity {
	private ProgressBar pg_1;
	private int number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		pg_1 = (ProgressBar) findViewById(R.id.progressBar1);
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����ؽ���
	 * 
	 * @ʱ �䣺����7:33:32
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			handler.postDelayed(timeRunnable, 50);
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
				DiyToast.showToast(getApplicationContext(), "�������");
				// ��ת
				Intent intent = new Intent(LoadingActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}