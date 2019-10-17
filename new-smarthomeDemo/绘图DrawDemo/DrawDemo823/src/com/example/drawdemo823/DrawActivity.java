package com.example.drawdemo823;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：完成数值传输、绘图显示功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-23
 */
public class DrawActivity extends Fragment {

	private ToggleButton tg_draw_open;
	private RadioButton ra_temp, ra_hum, ra_press;
	private ListView lv_draw_1;
	static float getdata;
	static String num;
	static boolean draw_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		tg_draw_open = (ToggleButton) view.findViewById(R.id.tg_draw_open);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_press = (RadioButton) view.findViewById(R.id.ra_press);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		lv_draw_1 = (ListView) view.findViewById(R.id.lv_draw_1);
		tg_draw_open
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							draw_state = true;
						} else {
							draw_state = false;
						}
					}
				});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_hum.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = 80;
				num = "湿度";
			}
			if (ra_press.isChecked()) {
				// getdata = BaseActivity.press;
				getdata = Float.parseFloat("80");
				num = "气压";
			}
			if (ra_temp.isChecked()) {
				// getdata = BaseActivity.temp;
				getdata = 30;
				num = "温度";
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_text, new String[] {
								"num", "data" }, new int[] { R.id.tv_num,
								R.id.tv_data });
				lv_draw_1.setAdapter(adapter);
			}
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