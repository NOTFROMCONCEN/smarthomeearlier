package com.example.shengsaiddemo10082018.timepicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.os.Handler;
import android.os.Message;

public class TimeGetClass {
	public static String Time;
	static Handler handler = null;
	static Runnable timeRunnable = null;

	public static void startThread() {
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyyƒÍMM‘¬dd»’   HH:mm:ss");
				simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GTM+8"));
				Time = simpleDateFormat.format(new java.util.Date());
				System.out.println(Time);
				handler.postDelayed(timeRunnable, 1000);
			}
		};
		timeRunnable = new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message msg = handler.obtainMessage();
				handler.sendMessage(msg);
			}
		};
		handler.post(timeRunnable);
	}
}
