package com.example.shengsaicdemo10072018.fragment;

import java.nio.channels.SocketChannel;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaicdemo10072018.R;
import com.example.shengsaicdemo10072018.toast.DiyToast;

/*
 * @�ļ�����BaseFragment.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
 */
public class BaseFragment extends Fragment {
	private RadioButton ra_base_getdata, ra_base_control;// �л���ť
	private LinearLayout line_control, line_data;
	private Switch sw_warm;// ������
	private Switch sw_door;// �ſ���
	private Switch sw_fan;// ����
	private Switch sw_lamp;// ���
	private RadioButton ra_open, ra_cls, ra_stop;// ��������ͣ
	private EditText et_send;// ���ⷢ���ı���
	private Button btn_send;// ���ⷢ�䰴ť
	private TextView tv_temp;// �¶�
	private TextView tv_hum;// ʪ��
	private TextView tv_gas;// ȼ��
	private TextView tv_press;// ��ѹ
	private TextView tv_pm;// PM
	private TextView tv_co;// CO
	private TextView tv_smo;// ����
	private TextView tv_ill;// ����
	private TextView tv_per;// �������
	public static float temp, hum, ill, gas, press, smo, per, pm, co;
	private Random random = new Random();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base_index, container,
				false);
		initView(view);// ��
		// �������
		handler.post(timeRunnable);
		// ���ö�����ť�л�
		// ���ݲɼ�
		ra_base_control
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							line_control.setVisibility(View.VISIBLE);
							line_data.setVisibility(View.GONE);
						}
					}
				});
		// �豸����
		ra_base_getdata
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							line_control.setVisibility(View.GONE);
							line_data.setVisibility(View.VISIBLE);
						}
					}
				});
		/**
		 * �豸����
		 */
		// �Ž�
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
		});
		// ������
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ����
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		// ���
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		/**
		 * ����
		 */
		// ��
		ra_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// ��
		ra_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// ͣ
		ra_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		/**
		 * 9 ����
		 */
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
				} else {
					DiyToast.showToast(getActivity(), "�ѷ��ͣ�"
							+ et_send.getText().toString());
					if (et_send.getText().toString().equals("1")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
					if (et_send.getText().toString().equals("2")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
					if (et_send.getText().toString().equals("3")) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				}
			}
		});

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

			handler.postDelayed(timeRunnable, 1500);
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

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����9:16:48
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		ra_base_control = (RadioButton) view.findViewById(R.id.ra_control);
		ra_base_getdata = (RadioButton) view.findViewById(R.id.ra_getdata);
		line_control = (LinearLayout) view.findViewById(R.id.line_base_control);
		line_data = (LinearLayout) view.findViewById(R.id.line_base_getdata);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
	}
}
