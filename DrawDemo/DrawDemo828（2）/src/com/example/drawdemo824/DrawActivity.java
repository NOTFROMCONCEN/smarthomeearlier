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
 * @�ļ�����DrawActivity.java
 * @������������ݿ��ȡ��ListView����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-24
 */
public class DrawActivity extends Fragment {
	private ListView lv_1;
	private RadioButton ra_draw_temp;// �¶�
	private RadioButton ra_draw_hum;// ʪ��
	private RadioButton ra_draw_press;// ��ѹ
	private ToggleButton tg_draw_start;// ����
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
		// �������
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�������ݿ����ListView����������
	 * 
	 * @ʱ �䣺����3:48:53
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_draw_hum.isChecked()) {
				getdata = BaseActivity.hum;
				num = "ʪ��";
			}
			if (ra_draw_press.isChecked()) {
				getdata = BaseActivity.press;
				num = "��ѹ";
			}
			if (ra_draw_temp.isChecked()) {
				getdata = BaseActivity.temp;
				num = "�¶�";
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