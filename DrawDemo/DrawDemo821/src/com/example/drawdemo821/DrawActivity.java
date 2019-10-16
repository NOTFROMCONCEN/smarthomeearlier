package com.example.drawdemo821;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：完成绘图功能数据传输、UI显示
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-20
 */
public class DrawActivity extends Fragment {
	private ListView lv_1;
	private RadioButton ra_temp, ra_hum, ra_press;
	private RadioGroup rg_draw_check;
	private ToggleButton tg_draw_start;
	static float getdata;
	static String num;
	static boolean draw_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_press = (RadioButton) view.findViewById(R.id.ra_press);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);
		ra_temp.setChecked(true);
		tg_draw_start.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			System.out.println("进程启动");
			if (ra_hum.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = 80;
				num = "湿度";
			}
			if (ra_press.isChecked()) {
				// getdata = BaseActivity.press;
				getdata = 1800;
				num = "气压";
			}
			if (ra_temp.isChecked()) {
				// getdata = BaseActivity.temp;
				getdata = 35;
				num = "温度";
			}
			if (draw_state && getdata != 0) {
				System.out.println(Float.toString(getdata));
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_text, new String[] {
								"number", "data" }, new int[] { R.id.tv_num,
								R.id.tv_data });
				lv_1.setAdapter(adapter);
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
