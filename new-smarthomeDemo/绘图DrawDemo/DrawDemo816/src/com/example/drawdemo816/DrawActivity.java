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
 * @�ļ�����DrawActivity.java
 * @���������û�չʾ��ͼ�����Լ����û��л�����Դ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-16
 */
public class DrawActivity extends Fragment {
	private ListView list_view_1;
	private RadioButton ra_temp, ra_hum, ra_press;// �¶�ʪ����ѹ��ѡ��
	private ToggleButton tg_draw_start;// ���ť
	private RadioGroup rg_draw_check;// ��ѡ����
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
		// ���ð�ť����¼�
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
		// �����߳�
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ���ȡ���ݲ����뵽ListView��
	 * 
	 * @ʱ �䣺����3:01:53
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// ���õ�ѡ��ť�����¼�
			rg_draw_check
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group,
								int checkedId) {
							// TODO Auto-generated method stub
							if (ra_hum.getId() == checkedId) {
								getdata = 90;
								number = "ʪ��";
							}
							if (ra_press.getId() == checkedId) {
								getdata = 180;
								number = "��ѹ";
							}
							if (ra_temp.getId() == checkedId) {
								getdata = 30;
								number = "�¶�";
							}
						}
					});
			System.out.println(getdata + number);
			// ��ȡ���ݿ⣬����ֵ���뵽ListView
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
