package com.example.drawdemo820shanxingtu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
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
 * @描述：对用户完成绘图界面UI显示以及数据传输
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class DrawActivity extends Fragment {
	private ToggleButton tg_draw_satrt;
	private RadioButton ra_temp, ra_hum, ra_ill;
	private RadioGroup rg_draw_check;
	private ListView lv_1;
	static float getdata;
	static String num;
	static boolean draw_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		tg_draw_satrt = (ToggleButton) view.findViewById(R.id.tg_start);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		tg_draw_satrt.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// 进程激活
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新listView和传输数据
	 * 
	 * @时 间：下午4:02:31
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_hum.isChecked()) {
				getdata = 80;
				num = "湿度";
			}
			if (ra_ill.isChecked()) {
				getdata = 178;
				num = "光照";
			}
			if (ra_temp.isChecked()) {
				getdata = 30;
				num = "温度";
			}
			System.out.println(num + Float.toString(getdata));
			if (draw_state && getdata != 0) {
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