package com.example.shengsaicdemo10072017.timeget;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Handler;
import android.os.Message;
import android.text.format.Time;

/*
 * @�ļ�����TimeGet.java
 * @��������ȡʱ�䡢����ʱ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
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
						"yyyy��MM��dd�� HH:mm:ss");
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
