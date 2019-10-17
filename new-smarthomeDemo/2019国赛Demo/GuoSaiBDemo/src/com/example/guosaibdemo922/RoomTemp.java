package com.example.guosaibdemo922;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/*
 * @�ļ�����RoomTemp.java
 * @��������������ͼ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-22
 */
public class RoomTemp extends Activity {
	private TextView tv_temp_roomnumber;
	public static boolean draw_state = true;
	public static float getdata;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_tempstate);
		tv_temp_roomnumber = (TextView) findViewById(R.id.tv_temp_roomnumber);
		tv_temp_roomnumber
				.setText("����ţ�" + Index_Room_Activity.NUMBER_FOR_ROOM);
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ��������
	 * 
	 * @ʱ �䣺����9:45:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata = Index_Room_Activity.temp;
			Log.e("�¶�", String.valueOf(getdata));
			handler.postDelayed(timeRunnable, 1000);
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
