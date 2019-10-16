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
 * @�ļ�����DrawActivity.java
 * @���������û�ʵ�ֻ�ͼ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-15
 */
public class DrawActivity extends Fragment {
	private RadioButton ra_draw_temp;// �¶�
	private RadioButton ra_draw_hum;// ʪ��
	private RadioButton ra_draw_gas;// ȼ��
	private RadioButton ra_draw_press;// ��ѹ
	private RadioGroup rg_draw_check;// ��ѡ��ť��
	private ToggleButton tg_draw_start;// ��ʼ��ͼ����
	public static float getdata = 0;// ����������
	public static String number;// ��ǰ����
	public static boolean draw_state = false;
	private ListView lv_data;// ListView������ʾ�ɼ���������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		rg_draw_check = (RadioGroup) view.findViewById(R.id.rg_draw_check);// ��ѡ��ť��
		ra_draw_gas = (RadioButton) view.findViewById(R.id.ra_draw_gas);// ȼ��
		ra_draw_hum = (RadioButton) view.findViewById(R.id.ra_draw_hum);// ʪ��
		ra_draw_press = (RadioButton) view.findViewById(R.id.ra_draw_press);// ��ѹ
		ra_draw_temp = (RadioButton) view.findViewById(R.id.ra_draw_temp);// �¶�
		tg_draw_start = (ToggleButton) view.findViewById(R.id.tg_draw_start);// ��ʼ��ť
		// �����ĸ���ѡ��ť�ֱ�ѡ��ʱ���¼�
		rg_draw_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (ra_draw_gas.getId() == checkedId) {// ȼ����ѡ��ʱ
					getdata = BaseActivity.gas;
					number = "--ȼ��--";
				}
				if (ra_draw_hum.getId() == checkedId) {// ʪ�ȱ�ѡ��ʱ
					getdata = BaseActivity.hum;
					number = "--ʪ��--";
				}
				if (ra_draw_press.getId() == checkedId) {// ��ѹ��ѡ��ʱ
					getdata = BaseActivity.press;
					number = "--��ѹ--";
				}
				if (ra_draw_temp.getId() == checkedId) {// �¶ȱ�ѡ��ʱ
					getdata = BaseActivity.temp;
					number = "--�¶�--";
				}
			}
		});
		// ���ذ�ť
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
	 * @��������handler
	 * 
	 * @�� �ܣ������̺߳��ȡ���ݺ����ListView
	 * 
	 * @�� ����float��String
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ���boolean����true״̬��getdata��ֵ��Ϊ0��������ֵ
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