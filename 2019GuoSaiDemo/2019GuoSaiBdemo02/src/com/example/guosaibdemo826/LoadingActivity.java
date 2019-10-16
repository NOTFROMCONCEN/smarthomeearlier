package com.example.guosaibdemo826;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LoadingActivity extends Activity {
	private ProgressBar pg_1;
	private TextView tv_loading_text;
	private String loading_text;
	private int recLen = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		pg_1 = (ProgressBar) findViewById(R.id.pg_1_loading);
		tv_loading_text = (TextView) findViewById(R.id.tv_loading_text);
		pg_1.setMax(100);
		pg_1.setProgress(0);
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			pg_1.setProgress(msg.what);
			switch (msg.what) {
			case 100:
				AlertDialog.Builder builder = new AlertDialog.Builder(
						LoadingActivity.this);
				builder.setTitle("登入成功");
				builder.setMessage("欢迎回来");
				builder.setPositiveButton("Ok",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								Intent intent = new Intent(
										LoadingActivity.this,
										MenuActivity.class);
								startActivity(intent);
								finish();
							}
						});
				builder.show();
				break;
			default:
				break;
			}
			tv_loading_text.setText(msg.what * 1 + "%");
			handler.postDelayed(timeRunnable, 20);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen++;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			if (recLen <= 100) {
				handler.sendMessage(msg);
			} else {
				handler.removeCallbacks(timeRunnable);
			}
		}
	};
}