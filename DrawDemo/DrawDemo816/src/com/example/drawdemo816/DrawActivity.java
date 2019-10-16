package com.example.drawdemo816;

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
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：对用户展示绘图功能以及供用户切换数据源
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-16
 */
public class DrawActivity extends Fragment {
	private ListView list_view_1;
	private RadioButton ra_temp, ra_hum, ra_press;// 温度湿度气压单选框
	private ToggleButton tg_draw_start;// 激活按钮
	private RadioGroup rg_draw_check;// 单选框组
	public static float getdata;
	public static String number;
	public static boolean draw_state = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		list_view_1 = (ListView) view.findViewById(R.id.list_view_1);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_press = (RadioButton) view.findViewById(R.id.ra_press);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);
		// 设置按钮点击事件
		tg_draw_start
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
		// 激活线程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：获取数据并插入到ListView中
	 * 
	 * @时 间：下午3:01:53
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// 设置单选按钮组点击事件
			rg_draw_check
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							// TODO Auto-generated method stub
							if (ra_hum.getId() == checkedId) {
								getdata = 90;
								number = "湿度";
							}
							if (ra_press.getId() == checkedId) {
								getdata = 180;
								number = "气压";
							}
							if (ra_temp.getId() == checkedId) {
								getdata = 30;
								number = "温度";
							}
						}
					});
			System.out.println(getdata + number);
			// 读取数据库，将数值插入到ListView
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_text, new String[] {
								"number", "data" }, new int[] { R.id.tv_number,
								R.id.tv_data });
				list_view_1.setAdapter(adapter);
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
