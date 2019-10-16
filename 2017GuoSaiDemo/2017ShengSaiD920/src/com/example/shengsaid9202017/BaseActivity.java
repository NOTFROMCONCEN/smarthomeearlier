package com.example.shengsaid9202017;

import java.text.DecimalFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����BaseActivity.java
 * @������������ݲɼ��豸����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-29
 */
public class BaseActivity extends Fragment {
	static float temp, hum, gas, smo, ill, co, pm, per, press;// ����float��ֵ
	private Random random = new Random();// �����
	private EditText tv_temp;// �¶�
	private EditText tv_hum;// ʪ��
	private EditText tv_smo;// ����
	private EditText tv_gas;// ȼ��
	private EditText tv_ill;// ����
	private EditText tv_co;// Co2
	private EditText tv_press;// ��ѹ
	private EditText tv_pm;// PM2.5
	private EditText tv_per;// �������
	private ToggleButton tg_cur;// ����
	private ToggleButton tg_warm;// ������
	private ToggleButton tg_lamp1;// ���1
	private ToggleButton tg_lamp2;// ���2
	private ToggleButton tg_fan;// ����
	private ToggleButton tg_door;// �Ž�

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_co = (EditText) view.findViewById(R.id.tv_co);
		tv_gas = (EditText) view.findViewById(R.id.tv_gas);
		tv_hum = (EditText) view.findViewById(R.id.tv_hum);
		tv_ill = (EditText) view.findViewById(R.id.tv_ill);
		tv_per = (EditText) view.findViewById(R.id.tv_per);
		tv_pm = (EditText) view.findViewById(R.id.tv_pm);
		tv_press = (EditText) view.findViewById(R.id.tv_press);
		tv_smo = (EditText) view.findViewById(R.id.tv_smo);
		tv_temp = (EditText) view.findViewById(R.id.tv_temp);
		tg_cur = (ToggleButton) view.findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_lamp1 = (ToggleButton) view.findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) view.findViewById(R.id.tg_lamp2);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		// �豸����
		// ����
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);// ������
				} else {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);// �ش���
				}
			}
		});
		// �Ž�
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// ���1
		tg_lamp1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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
		// ���2
		tg_lamp2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

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

		// ���ݲɼ�
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							// tv_per.setText(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// ������̼
							// tv_co.setText(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							// tv_gas.setText(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							// tv_hum.setText(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
							// tv_ill.setText(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							// tv_pm.setText(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
							// tv_smo.setText(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							// tv_temp.setText(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// �������
							// tv_per.setText(getdata.getStateHumanInfrared());
						}
					}
				});
			}
		});
		// �������
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����������
	 * 
	 * @ʱ �䣺����3:49:11
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20));// �¶�
			hum = Float.valueOf(random.nextInt(120) % (120 - 10));// ʪ��
			gas = Float.valueOf(random.nextInt(100) % (100 - 10));// ȼ��
			smo = Float.valueOf(random.nextInt(400) % (400 - 10));// ����
			ill = Float.valueOf(random.nextInt(3000) % (3000 - 1));// ����
			co = Float.valueOf(random.nextInt(300) % (300 - 10));// Co2
			pm = Float.valueOf(random.nextInt(500) % (500 - 40));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10));// ��ѹ
			tv_co.setText(String.valueOf(co));
			tv_gas.setText(String.valueOf(gas));
			tv_hum.setText(String.valueOf(hum));
			tv_ill.setText(String.valueOf(ill));
			tv_pm.setText(String.valueOf(pm));
			tv_press.setText(String.valueOf(press));
			tv_smo.setText(String.valueOf(smo));
			tv_temp.setText(String.valueOf(temp));
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				tv_per.setText("����");
				per = 1;
			} else {
				tv_per.setText("����");
				per = 0;
			}
			handler.postDelayed(timeRunnable, 10);
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