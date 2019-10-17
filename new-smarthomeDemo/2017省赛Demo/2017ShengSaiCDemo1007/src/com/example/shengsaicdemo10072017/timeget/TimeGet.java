package com.example.shengsaicdemo10072017.timeget;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Handler;
import android.os.Message;
import android.text.format.Time;

/*
 * @文件名：TimeGet.java
 * @描述：获取时间、传输时间
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-7
 */
public class TimeGet {
	public static String TIME;
	public static Handler handler;
	public static Runnable timeRunnable;

	public static void startThread() {
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"yyyy年MM月dd日 HH:mm:ss");
				simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
				TIME = simpleDateFormat.format(new java.util.Date());
				handler.postDelayed(timeRunnable, 500);
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
