package com.example.drawdemo53;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
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

public class DrawActivity extends Fragment {
	// 定义控件
	private RadioButton ra_temp, ra_hum, ra_gas, ra_press, ra_smo, ra_ill,
			ra_pm, ra_co, ra_test1, ra_test2;
	private ToggleButton tg_draw_start;
	private ListView lv_1;
	private RadioGroup rg_draw_check;
	static float getdata;
	static String num;
	static boolean draw_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		ra_co = (RadioButton) view.findViewById(R.id.ra_co);
		ra_gas = (RadioButton) view.findViewById(R.id.ra_gas);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_pm = (RadioButton) view.findViewById(R.id.ra_pm);
		ra_press = (RadioButton) view.findViewById(R.id.ra_press);
		ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		ra_test1 = (RadioButton) view.findViewById(R.id.ra_test1);
		ra_test2 = (RadioButton) view.findViewById(R.id.ra_test2);
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
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

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			//获得每一个数据
			if (ra_co.isChecked()) {
				num = "Co2";
				getdata = BaseActivity.co;
			}
			if (ra_gas.isChecked()) {
				num = "燃气";
				getdata = BaseActivity.gas;
			}
			if (ra_hum.isChecked()) {
				num = "湿度";
				getdata = BaseActivity.hum;
			}
			if (ra_ill.isChecked()) {
				num = "光照";
				getdata = BaseActivity.ill;
			}
			if (ra_pm.isChecked()) {
				num = "PM2.5";
				getdata = BaseActivity.pm;
			}
			if (ra_press.isChecked()) {
				num = "气压";
				getdata = BaseActivity.press;
			}
			if (ra_smo.isChecked()) {
				num = "烟雾";
				getdata = BaseActivity.smo;
			}
			if (ra_temp.isChecked()) {
				num = "温度";
				getdata = BaseActivity.temp;
			}
			if (ra_test1.isChecked()) {
				num = "测试1";
				getdata = Float.parseFloat("50");
			}
			if (ra_test2.isChecked()) {
				num = "测试2";
				getdata = Float.parseFloat("150");
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_text, new String[] {
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