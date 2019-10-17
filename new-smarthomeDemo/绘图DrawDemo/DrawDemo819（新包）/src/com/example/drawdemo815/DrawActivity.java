package com.example.drawdemo815;

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
 * @描述：对用户实现绘图功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-15
 */
public class DrawActivity extends Fragment {
	private RadioButton ra_draw_temp;// 温度
	private RadioButton ra_draw_hum;// 湿度
	private RadioButton ra_draw_gas;// 燃气
	private RadioButton ra_draw_press;// 气压
	private RadioGroup rg_draw_check;// 单选按钮组
	private ToggleButton tg_draw_start;// 开始绘图开关
	public static float getdata = 0;// 传感器数据
	public static String number;// 当前数量
	public static boolean draw_state = false;
	private ListView lv_data;// ListView用于显示采集到的数据

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);// 单选框按钮组
		ra_draw_gas = (RadioButton) view.findViewById(R.id.ra_draw_gas);// 燃气
		ra_draw_hum = (RadioButton) view.findViewById(R.id.ra_draw_hum);// 湿度
		ra_draw_press = (RadioButton) view.findViewById(R.id.ra_draw_press);// 气压
		ra_draw_temp = (RadioButton) view.findViewById(R.id.ra_draw_temp);// 温度
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);// 开始按钮
		// 设置四个单选按钮分别被选中时的事件
		rg_draw_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (ra_draw_gas.getId() == checkedId) {// 燃气被选中时
					getdata = BaseActivity.gas;
					number = "--燃气--";
				}
				if (ra_draw_hum.getId() == checkedId) {// 湿度被选中时
					getdata = BaseActivity.hum;
					number = "--湿度--";
				}
				if (ra_draw_press.getId() == checkedId) {// 气压被选中时
					getdata = BaseActivity.press;
					number = "--气压--";
				}
				if (ra_draw_temp.getId() == checkedId) {// 温度被选中时
					getdata = BaseActivity.temp;
					number = "--温度--";
				}
			}
		});
		// 开关按钮
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
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：激活线程后获取数据和填充ListView
	 * 
	 * @参 数：float、String
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 如果boolean处于true状态和getdata数值不为0，插入数值
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.listview_text, new String[] {
								"number", "data" }, new int[] { R.id.tv_num,
								R.id.tv_data });
				lv_data.setAdapter(adapter);
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