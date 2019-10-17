package com.example.seekbackground;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity {

	private SeekBar seekBar1;
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		seekBar1 = (SeekBar) findViewById(R.id.seekBar1);
		seekBar1.setMax(1000000);
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(), "ֹͣ����");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(), "��ʼ����");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				DiyToast.showToast(getApplicationContext(),
						"��ǰ���ȣ�" + seekBar.getProgress() / 10000);
				number = seekBar.getProgress();
			}
		});
		// �������
		// handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			seekBar1.setProgress(msg.what);
			System.out.println(number);
			if (msg.what > 1000000) {
				number = 0;
				seekBar1.setProgress(0);
				System.out.println("���");
			}
			handler.postDelayed(timeRunnable, 1);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number += 1000;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}