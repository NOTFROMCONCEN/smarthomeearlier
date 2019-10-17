package com.example.drawdemo817;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：对用户完成数据传输、绘图显示、数据源切换
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-17
 */
public class DrawActivity extends Fragment {
	private ToggleButton tg_draw_start;// 开始按钮
	private ListView lv_1;// ListView
	private RadioButton ra_temp, ra_hum, ra_press;// 单选框
	private RadioGroup rg_draw_check;// 复选按钮组
	public static float getdata;
	public static String num;
	public static boolean draw_state;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);
		lv_1 = (ListView) view.findViewById(R.id.listView);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_press = (RadioButton) view.findViewById(R.id.ra_press);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);
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
	 * @功 能：激活数据源切换、插入ListView
	 * 
	 * @参 数：String、Float
	 * 
	 * @时 间：上午9:59:12
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			System.out.println("进程激活");
			rg_draw_check
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							// TODO Auto-generated method stub
							if (ra_hum.isChecked()) {
								getdata = Float.valueOf("60");
								num = "湿度";
							}
							if (ra_press.isChecked()) {
								getdata = Float.valueOf("1800");
								num = "气压";
							}
							if (ra_temp.isChecked()) {
								getdata = Float.valueOf("24");
								num = "温度";
							}
						}
					});
			System.out.println(num + getdata);
			if (draw_state && getdata != 0) {
				// 读取数据库，将数值插入到ListView
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
