package com.example.drawdemo830;

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
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

/*
 * @�ļ�����DrawActivity.java
 * @��������ɻ�ͼ��ʾ�����ݴ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-30
 */
public class DrawActivity extends Fragment {
	private ListView lv_1;
	private ToggleButton tg_draw_start;
	private RadioButton ra_temp_draw;
	private RadioButton ra_hum_draw;
	private RadioButton ra_gas_draw;
	public static float getdata;
	public static String num;
	public static boolean draw_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		lv_1 = (ListView) view.findViewById(R.id.lv_1);
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);
		ra_gas_draw = (RadioButton) view.findViewById(R.id.ra_gas_draw);
		ra_hum_draw = (RadioButton) view.findViewById(R.id.ra_hum_draw);
		ra_temp_draw = (RadioButton) view.findViewById(R.id.ra_temp_draw);
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
		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (ra_gas_draw.isChecked()) {
				num = "ȼ��";
				getdata = BaseActivity.press;
			}
			if (ra_hum_draw.isChecked()) {
				num = "ʪ��";
				getdata = BaseActivity.hum;
			}
			if (ra_temp_draw.isChecked()) {
				num = "�¶�";
				getdata = BaseActivity.temp;
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_text, new String[] {
								"num", "data" }, new int[] { R.id.tv_number,
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