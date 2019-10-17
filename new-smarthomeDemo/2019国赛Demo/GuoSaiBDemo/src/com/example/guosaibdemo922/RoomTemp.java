package com.example.guosaibdemo922;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

/*
 * @文件名：RoomTemp.java
 * @描述：绘制折线图
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
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
				.setText("房间号：" + Index_Room_Activity.NUMBER_FOR_ROOM);
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：传输参数
	 * 
	 * @时 间：上午9:45:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata = Index_Room_Activity.temp;
			Log.e("温度", String.valueOf(getdata));
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
