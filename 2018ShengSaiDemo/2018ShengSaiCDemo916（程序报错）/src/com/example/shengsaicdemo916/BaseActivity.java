package com.example.shengsaicdemo916;

import java.text.DecimalFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݲɼ����豸����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-16
 */
public class BaseActivity extends Fragment {
	private TextView tv_temp;// �¶�
	private TextView tv_hum;// ʪ��
	private TextView tv_gas;// ȼ��
	private TextView tv_press;// ��ѹ
	private TextView tv_ill;// ����
	private TextView tv_co;// Co2
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// �������
	private TextView tv_smo;// ����
	private Switch sw_warm;// ������
	private Switch sw_door;// �ſ���
	private Switch sw_fan;// ����
	private Switch sw_lamp;// ���
	private Switch sw_cur;// ����
	public static float temp, hum, ill, pm, co, press, per, smo, gas;
	private Random random = new Random();
	private LinearLayout index_data;// ���ݲɼ�
	private LinearLayout index_control;// �豸����
	private RadioButton ra_base_open;// �ɼ�
	private RadioButton ra_control_open;// ����
	private Button btn_sned;// ���ⷢ��
	private EditText et_send;// ���ⷢ��

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base_index, container,
				false);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		btn_sned = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		sw_cur = (Switch) view.findViewById(R.id.sw_cur);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		index_data = (LinearLayout) view.findViewById(R.id.index_data);
		index_control = (LinearLayout) view.findViewById(R.id.index_control);
		ra_base_open = (RadioButton) view.findViewById(R.id.ra_base_open);
		ra_control_open = (RadioButton) view.findViewById(R.id.ra_control_open);
		index_control.setVisibility(View.GONE);
		index_data.setVisibility(View.VISIBLE);
		// ���ⷢ��
		btn_sned.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
				} else {
					DiyToast.showToast(getActivity(), "�ѷ��ͣ�"
							+ et_send.getText().toString());
				}
			}
		});
		ra_base_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index_control.setVisibility(View.GONE);
				index_data.setVisibility(View.VISIBLE);
			}
		});
		ra_control_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				index_control.setVisibility(View.VISIBLE);
				index_data.setVisibility(View.GONE);
			}
		});

		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// �¶�
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// ʪ��
			gas = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// ȼ��
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// ����
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// ����
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// ��ѹ
			DecimalFormat df = new DecimalFormat("0");
			String get_co = df.format(co);
			String get_gas = df.format(gas);
			String get_hum = df.format(hum);
			String get_ill = df.format(ill);
			String get_pm = df.format(pm);
			String get_press = df.format(press);
			String get_smo = df.format(smo);
			String get_temp = df.format(temp);
			tv_co.setText(String.valueOf(get_co));
			tv_gas.setText(String.valueOf(get_gas));
			tv_hum.setText(String.valueOf(get_hum));
			tv_ill.setText(String.valueOf(get_ill));
			tv_pm.setText(String.valueOf(get_pm));
			tv_press.setText(String.valueOf(get_press));
			tv_smo.setText(String.valueOf(get_smo));
			tv_temp.setText(String.valueOf(get_temp));
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				tv_per.setText("����");
				per = 1;
			} else {
				tv_per.setText("����");
				per = 0;
			}
			handler.postDelayed(timeRunnable, 500);
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
