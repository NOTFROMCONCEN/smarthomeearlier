package com.example.shengsaic9192017;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

/*
 * @�ļ�����DrawActivity.java
 * @��������ͼ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class DrawActivity extends Fragment {
	private ListView lv_1;
	private RadioButton ra_gas;// ȼ��
	private RadioButton ra_ill;// ����
	private RadioButton ra_smo;// ����
	private RadioButton ra_hum;// ʪ��
	private RadioButton ra_temp;// �¶�
	private Button btn_start;
	public static boolean draw_state = false;
	public static float getdata;
	public static String num;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_draw, container, false);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		ra_gas = (RadioButton) view.findViewById(R.id.ra_gas);
		ra_hum = (RadioButton) view.findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		btn_start = (Button) view.findViewById(R.id.btn_start);
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (btn_start.getText().toString().equals("ON")) {
					btn_start.setText("OFF");
					draw_state = true;
				} else if (btn_start.getText().toString().equals("OFF")) {
					btn_start.setText("ON");
					draw_state = false;
				}
			}
		});
		ra_temp.setChecked(true);
		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			System.out.println("��������");
			if (ra_hum.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = BaseActivity.hum;
				num = "04-";
			}
			if (ra_temp.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = BaseActivity.temp;
				num = "05-";
			}
			if (ra_ill.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = BaseActivity.ill;
				num = "03-";
			}
			if (ra_smo.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = BaseActivity.smo;
				num = "02-";
			}
			if (ra_gas.isChecked()) {
				// getdata = BaseActivity.hum;
				getdata = BaseActivity.gas;
				num = "01-";
			}
			if (draw_state && getdata != 0) {
				System.out.println(Float.toString(getdata));
				SimpleAdapter adapter = new SimpleAdapter(getActivity(),
						MyView.list, R.layout.activity_text, new String[] {
								"number", "data" }, new int[] { R.id.tv_number,
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
