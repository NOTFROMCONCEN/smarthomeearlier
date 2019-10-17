package com.example.guosaigdemo9262019;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：饼状图绘制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class DrawActivity extends Fragment {
	private ToggleButton tg_ill_mode, tg_temp_mode;
	static float getdata;
	static float num;
	static boolean draw_state = false;
	private TextView tv_time_draw, tv_ill_temp_state;
	private String time;
	private TextView tv_get_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		tv_get_time = (TextView) view.findViewById(R.id.tv_get_time);
		tg_ill_mode = (ToggleButton) view.findViewById(R.id.tg_ill_data);
		tg_temp_mode = (ToggleButton) view.findViewById(R.id.tg_temp_data);
		tv_time_draw = (TextView) view.findViewById(R.id.tv_draw_time);
		tv_ill_temp_state = (TextView) view
				.findViewById(R.id.tv_ill_temp_state);
		tg_ill_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					draw_state = true;
					tg_temp_mode.setChecked(false);
					tv_ill_temp_state.setText("光照一小时统计图");
				} else {
					tg_temp_mode.setChecked(true);
				}
			}
		});
		tg_temp_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					draw_state = true;
					tg_ill_mode.setChecked(false);
					tv_ill_temp_state.setText("温度一小时统计图");
				} else {
					tg_ill_mode.setChecked(true);
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (tg_ill_mode.isChecked()) {
				getdata = BaseActivity.ill;
				// getdata = Float.parseFloat("100");
			}
			if (tg_temp_mode.isChecked()) {
				getdata = BaseActivity.temp;
				// getdata = Float.parseFloat("200");
			}
			SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			time = formater.format(new java.util.Date());
			tv_time_draw.setText(time);
			
			handler.postDelayed(timeRunnable, 100);
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
