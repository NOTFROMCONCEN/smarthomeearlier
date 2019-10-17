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
 * @�ļ�����DrawActivity.java
 * @���������û���ɻ�ͼ����UI��ʾ�Լ����ݴ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-21
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
		// ���̼���
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����listView�ʹ�������
	 * 
	 * @ʱ �䣺����4:02:31
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_hum.isChecked()) {
				getdata = 80;
				num = "ʪ��";
			}
			if (ra_ill.isChecked()) {
				getdata = 178;
				num = "����";
			}
			if (ra_temp.isChecked()) {
				getdata = 30;
				num = "�¶�";
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