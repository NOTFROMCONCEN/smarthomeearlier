package com.example.drawdemo818;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CheckBox;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private TextView tv_time;// 时间
	private String time;
	private CheckBox cb_autologin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_time = (TextView) findViewById(R.id.tv_time);
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：上午8:42:39
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy.MM.dd HH:mm:ss");
			format.setTimeZone(TimeZone.getTimeZone("GMT+8"));// GMT+8时间
			time = format.format(new java.util.Date());
			tv_time.setText(time);
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
}
