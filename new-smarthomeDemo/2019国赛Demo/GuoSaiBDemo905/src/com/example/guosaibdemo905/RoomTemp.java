package com.example.guosaibdemo905;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/*
 * @文件名：RoomControl.java
 * @描述：完成温度趋势
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
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
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：传输数据
	 * 
	 * @时 间：上午10:22:55
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