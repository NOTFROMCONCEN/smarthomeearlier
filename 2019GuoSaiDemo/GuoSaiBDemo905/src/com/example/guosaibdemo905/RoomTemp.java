package com.example.guosaibdemo905;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/*
 * @�ļ�����RoomControl.java
 * @����������¶�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-6
 */
public class RoomTemp extends Activity {
	public static float getdata;
	public static boolean draw_state = false;
	private TextView tv_temp_roomnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_tempstate);
		tv_temp_roomnumber = (TextView) findViewById(R.id.tv_temp_roomnumber);
		tv_temp_roomnumber.setText(IndexActivity.room_getnumber);
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ���������
	 * 
	 * @ʱ �䣺����10:22:55
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata = IndexActivity.temp;
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msgs = handler.obtainMessage();
			handler.sendMessage(msgs);
		}
	};
}