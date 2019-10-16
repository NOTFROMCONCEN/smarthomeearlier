package com.example.guosaicdemo831;

import java.text.DecimalFormat;
import java.util.Random;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݲɼ����豸���ơ������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-31
 * @author Administrator
 */
public class BaseActivity extends Fragment {
	private TextView tv_base_time;
	static float temp, hum, gas, smo, ill, co, pm, per, press;// ����float��ֵ
	private TextView tv_temp, tv_hum, tv_smo, tv_gas, tv_ill, tv_co, tv_pm,
			tv_press, tv_per;// �ı�
	private ToggleButton tg_cur, tg_warm, tg_lamp1, tg_lamp2, tg_fan, tg_door;// ����
	private EditText et_red_send;// ���ⷢ���
	private Button btn_red_send;// ���ⷢ�䰴ť
	private Random random = new Random();// �����

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_base_time = (TextView) view.findViewById(R.id.tv_base_time);
		tv_co = (TextView) view.findViewById(R.id.tv_co2);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tg_door = (ToggleButton) view.findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) view.findViewById(R.id.tg_fan);
		tg_lamp1 = (ToggleButton) view.findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) view.findViewById(R.id.tg_lamp2);
		tg_warm = (ToggleButton) view.findViewById(R.id.tg_warm);
		tg_cur = (ToggleButton) view.findViewById(R.id.tg_cur);
		btn_red_send = (Button) view.findViewById(R.id.btn_send);
		et_red_send = (EditText) view.findViewById(R.id.et_send);
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
							// tv_press.setText(getdata.getAirPressure());
							// press = Float.parseFloat(tv_press.getText()
							// .toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							// tv_co.setText(getdata.getCo2());
							// co =
							// Float.parseFloat(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							// tv_gas.setText(getdata.getCo2());
							// gas =
							// Float.parseFloat(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							// tv_hum.setText(getdata.getHumidity());
							// hum =
							// Float.parseFloat(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
							// tv_ill.setText(getdata.getIllumination());
							// ill =
							// Float.parseFloat(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							// tv_pm.setText(getdata.getPM25());
							// pm =
							// Float.parseFloat(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
							// tv_smo.setText(getdata.getSmoke());
							// smo =
							// Float.parseFloat(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							// tv_temp.setText(getdata.getTemperature());
							// temp = Float.parseFloat(tv_temp.getText()
							// .toString());
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
		// �豸����
		// ����
		tg_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
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
				} else {
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
					ControlUtils.control(ConstantUtil.Curtain,
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
					ControlUtils.control(ConstantUtil.Curtain,
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
		// ���ⷢ��
		btn_red_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_red_send.getText().toString().equals("")) {
					DiyToast.showTaost(getActivity(), "��ֵ����Ϊ��");
				} else {
					if (et_red_send.getText().toString().equals("1")) {
						DiyToast.showTaost(getActivity(), "�ѷ���1");
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else if (et_red_send.getText().toString().equals("2")) {
						DiyToast.showTaost(getActivity(), "�ѷ���2");
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					} else if (et_red_send.getText().toString().equals("3")) {
						DiyToast.showTaost(getActivity(), "�ѷ���3");
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					} else {
						DiyToast.showTaost(getActivity(), "��ֵNull ������1~3");
					}
				}
			}
		});
		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// �����
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// �¶�
			hum = Float.valueOf(random.nextInt(120) % (120 - 10 + 1));// ʪ��
			gas = Float.valueOf(random.nextInt(100) % (100 - 10 + 1));// ȼ��
			smo = Float.valueOf(random.nextInt(100) % (100 - 10 + 1));// ����
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// ����
			co = Float.valueOf(random.nextInt(100) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(100) % (100 - 40 + 1));// pm2.5
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
			// ��ȡʱ��
			tv_base_time.setText(BarActivity.get_time);
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