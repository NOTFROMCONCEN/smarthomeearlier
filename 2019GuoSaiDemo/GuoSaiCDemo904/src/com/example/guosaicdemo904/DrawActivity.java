package com.example.guosaicdemo904;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：完成数据库读取、ListView设置
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-24
 */
public class DrawActivity extends Fragment {
	static float getdata_ill, getdata_temp;
	static String num_ill, num_temp;
	static boolean draw_state = true;
	private TextView tv_draw_time;

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
	 * @功 能：检测数据库插入ListView、传输数据
	 * 
	 * @时 间：下午3:48:53
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata_ill = BaseActivity.ill;
			num_ill = "光照";
			getdata_temp = BaseActivity.temp;
			num_temp = "温度";
			tv_draw_time.setText(BarActivity.bar_time);
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