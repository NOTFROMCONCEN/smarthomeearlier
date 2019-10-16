package com.example.drawdemo824;

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
import android.widget.ToggleButton;

/*
 * @文件名：DrawActivity.java
 * @描述：完成数据库读取、ListView设置
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-24
 */
public class DrawActivity extends Fragment {
	private ListView lv_1;
	private RadioButton ra_draw_temp;// 温度
	private RadioButton ra_draw_hum;// 湿度
	private RadioButton ra_draw_press;// 气压
	private ToggleButton tg_draw_start;// 开关
	static float getdata;
	static String num;
	static boolean draw_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		ra_draw_hum = (RadioButton) view.findViewById(R.id.ra_draw_hum);
		ra_draw_press = (RadioButton) view.findViewById(R.id.ra_draw_press);
		ra_draw_temp = (RadioButton) view.findViewById(R.id.ra_draw_temp);
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);
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
			if (ra_draw_hum.isChecked()) {
				getdata = BaseActivity.hum;
				num = "湿度";
			}
			if (ra_draw_press.isChecked()) {
				getdata = BaseActivity.press;
				num = "气压";
			}
			if (ra_draw_temp.isChecked()) {
				getdata = BaseActivity.temp;
				num = "温度";
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_draw_text, new String[] {
								"num", "data" }, new int[] { R.id.tv_num,
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