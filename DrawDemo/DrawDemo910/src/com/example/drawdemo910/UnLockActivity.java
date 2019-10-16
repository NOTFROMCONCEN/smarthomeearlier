package com.example.drawdemo910;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class UnLockActivity extends Activity {
	int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_onunlock);
		// ¼¤»î½ø³Ì
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 100) {
				Intent intent = new Intent(UnLockActivity.this,
						BarActivity.class);
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
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}
