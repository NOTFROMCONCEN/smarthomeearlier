package com.example.drawdemo1008.fargment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;
import com.example.drawdemo1008.R;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݲɼ����豸����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class BaseActivity extends Fragment {
	private Button btn_send;// ���ⷢ���ı���
	private EditText et_send;// ���ⷢ���ı���
	private TextView tv_temp;// �¶�
	private TextView tv_hum;// ʪ��
	private TextView tv_gas;// ȼ��
	private TextView tv_press;// ��ѹ
	private TextView tv_ill;// ����
	private TextView tv_smo;// ����
	private TextView tv_co;// Co2
	private TextView tv_pm;// PM2.5
	private TextView tv_per;// �������
	private ToggleButton tg_lamp;// ���
	private ToggleButton tg_door;// �Ž�
	private ToggleButton tg_fan;// ����
	private ToggleButton tg_kongtiao;// �յ�
	private ToggleButton tg_tv;// ����
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_warm;// ������
	private RadioButton ra_open, ra_cls, ra_stop;// ���� �����أ�ͣ
	static float temp, hum, ill, press, smo, pm, co, per, gas;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		initView(view);// �󶨿ؼ�
		/**
		 * �豸����
		 */
		// �Ž�
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {

				}
			}
		});
		// DVD
		tg_dvd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
		// ����
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// ������
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		tg_tv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			}
		});
		// �յ�
		tg_kongtiao.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
			}
		});
		/**
		 * ��������
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
		 * ���ݲɼ�
		 */
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(DeviceBean.getTemperature())) {// �¶�
							tv_temp.setText(DeviceBean.getTemperature());
							temp = Float.valueOf(DeviceBean.getTemperature());
						}
						if (!TextUtils.isEmpty(DeviceBean.getHumidity())) {// ʪ��
							tv_hum.setText(DeviceBean.getHumidity());
							hum = Float.valueOf(DeviceBean.getHumidity());
						}
						if (!TextUtils.isEmpty(DeviceBean.getGas())) {// ȼ��
							tv_gas.setText(DeviceBean.getGas());
							gas = Float.valueOf(DeviceBean.getGas());
						}
						if (!TextUtils.isEmpty(DeviceBean.getAirPressure())) {// ��ѹ
							tv_press.setText(DeviceBean.getAirPressure());
							press = Float.valueOf(DeviceBean.getAirPressure());
						}
						if (!TextUtils.isEmpty(DeviceBean.getIllumination())) {// ����
							tv_ill.setText(DeviceBean.getIllumination());
							ill = Float.valueOf(DeviceBean.getIllumination());
						}
						if (!TextUtils.isEmpty(DeviceBean.getSmoke())) {// ����
							tv_smo.setText(DeviceBean.getSmoke());
							smo = Float.valueOf(DeviceBean.getSmoke());
						}
						if (!TextUtils.isEmpty(DeviceBean.getCo2())) {// Co2
							tv_co.setText(DeviceBean.getCo2());
							co = Float.valueOf(DeviceBean.getCo2());
						}
						if (!TextUtils.isEmpty(DeviceBean.getPM25())) {// PM2.5
							tv_pm.setText(DeviceBean.getPM25());
							pm = Float.valueOf(DeviceBean.getPM25());
						}
						if (!TextUtils.isEmpty(DeviceBean
								.getStateHumanInfrared())) {// �������
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
								tv_per.setText("����");
								per = 0;
							} else {
								tv_per.setText("����");
								per = 1;
							}
						}
					}
				});
			}
		});
		return view;
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:05:13
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		btn_send = (Button) view.findViewById(R.id.btn_send);
		et_send = (EditText) view.findViewById(R.id.et_send);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_som);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
	}
}