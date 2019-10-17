package com.example.guosaiddemo907;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ControulActivity extends Activity {
	private TextView tv_time;
	private ImageView iv_back_shebeikongzhi_text;
	private ImageView iv_back_shebeikongzhi_button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_controul);
		tv_time = (TextView) findViewById(R.id.tv_shebeikongzhi_time);
		iv_back_shebeikongzhi_button = (ImageView) findViewById(R.id.iv_back_shebeikongzhi_button);
		iv_back_shebeikongzhi_text = (ImageView) findViewById(R.id.iv_back_shebeikongzhi_text);
		// ¼¤»î½ø³Ì
		handler.post(timeRunnable);
		iv_back_shebeikongzhi_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ControulActivity.this,
						ChoseActivity.class);
				startActivity(intent);
			}
		});
		iv_back_shebeikongzhi_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ControulActivity.this,
						ChoseActivity.class);
				startActivity(intent);
			}
		});
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_time.setText(simpleDateFormat.format(new java.util.Date())
					.toString());
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	};
}
