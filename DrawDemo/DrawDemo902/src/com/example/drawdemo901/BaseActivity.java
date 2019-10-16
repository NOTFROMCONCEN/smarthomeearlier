package com.example.drawdemo901;

import java.text.DecimalFormat;
import java.util.Random;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @�ļ�����BaseActivity.java
 * @������������ݲɼ����豸���ơ����ݴ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-2
 */
public class BaseActivity extends Fragment {
	private TextView tv_temp;// �¶�
	private TextView tv_hum;// ʪ��
	private TextView tv_gas;// ȼ��
	private TextView tv_press;// ��ѹ
	private TextView tv_smo;// ����
	private TextView tv_ill;// ����
	private TextView tv_co;// Co2
	private TextView tv_pm;// pm2.5
	private TextView tv_per;// �������
	private ToggleButton tg_lamp;// ���
	private ToggleButton tg_fan;// ����
	private ToggleButton tg_door;// �Ž�
	private ToggleButton tg_tv;// ����
	private ToggleButton tg_kongtiao;// �յ�
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_warm;// ������
	private RadioButton ra_open, ra_cls, ra_stop;// ��������ͣ
	public static float temp, hum, gas, press, smo, ill, co, pm, per;
	private Random random = new Random();// �����

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) view.findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) view.findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) view.findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) view.findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		// ���ݲɼ�
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							tv_press.setText(getdata.getAirPressure());
							press = Float
									.valueOf(tv_press.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// pm2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							tv_temp.setText(getdata.getTemperature());
							temp = Float.valueOf(tv_temp.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// �������
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
		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20));// �¶�
			hum = Float.valueOf(random.nextInt(40) % (40 - 20));// ʪ��
			gas = Float.valueOf(random.nextInt(40) % (40 - 20));// ȼ��
			press = Float.valueOf(random.nextInt(40) % (40 - 20));// ��ѹ
			smo = Float.valueOf(random.nextInt(40) % (40 - 20));// ����
			ill = Float.valueOf(random.nextInt(40) % (40 - 20));// ����
			co = Float.valueOf(random.nextInt(40) % (40 - 20));// Co2
			pm = Float.valueOf(random.nextInt(40) % (40 - 20));// PM2.5
			DecimalFormat decimalFormat = new DecimalFormat("0");
			String ran_temp = decimalFormat.format(temp);
			String ran_hum = decimalFormat.format(hum);
			String ran_gas = decimalFormat.format(gas);
			String ran_press = decimalFormat.format(press);
			String ran_smo = decimalFormat.format(smo);
			String ran_ill = decimalFormat.format(ill);
			String ran_co = decimalFormat.format(co);
			String ran_pm = decimalFormat.format(pm);
			if (random.nextInt(10) % (10 - 1 + 1) > 5) {
				tv_per.setText("����");
				per = 1;
			} else {
				tv_per.setText("����");
				per = 0;
			}
			tv_co.setText(ran_co);
			tv_gas.setText(ran_gas);
			tv_hum.setText(ran_hum);
			tv_ill.setText(ran_ill);
			tv_pm.setText(ran_pm);
			tv_press.setText(ran_press);
			tv_smo.setText(ran_smo);
			tv_temp.setText(ran_temp);
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