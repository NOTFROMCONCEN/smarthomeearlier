package com.example.guosaicdemo923;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DrawActivity extends Fragment {
	private TextView tv_draw_time;
	public static boolean draw_state = false;
	public static float getdata_temp;
	public static float getdata_ill;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		tv_draw_time = (TextView) view.findViewById(R.id.tv_draw_time);
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：上午9:05:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata_ill = BaseActivity.ill;
			getdata_temp = BaseActivity.temp;
			// 时间
			tv_draw_time.setText(LoginActivity.get_time);
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
