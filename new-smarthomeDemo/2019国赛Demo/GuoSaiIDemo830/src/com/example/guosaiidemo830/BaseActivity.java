package com.example.guosaiidemo830;

import java.text.DecimalFormat;
import java.util.Random;

import com.bizideal.smarthome.socket.ControlUtils;
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
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݲɼ�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-30
 */
public class BaseActivity extends Fragment {
	private ImageView IV_TEMP_ZHIZHEN, IV_HUM_ZHIZHEN;
	private TextView tv_temp, tv_hum, tv_smo, tv_gas, tv_ill, tv_co, tv_press,
			tv_pm, tv_per;
	public static float temp, hum, smo, gas, ill, co, press, pm, per;
	private Random random = new Random();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		IV_HUM_ZHIZHEN = (ImageView) view.findViewById(R.id.iv_hum_zhizhen);
		IV_TEMP_ZHIZHEN = (ImageView) view.findViewById(R.id.iv_temp_zhizhen);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
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
							tv_press.setText(getdata.getAirPressure());
						}
						// ����
						// ...
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
			tv_hum.setText(String.valueOf(get_hum) + "��");
			tv_ill.setText(String.valueOf(get_ill));
			tv_pm.setText(String.valueOf(get_pm));
			tv_press.setText(String.valueOf(get_press));
			tv_smo.setText(String.valueOf(get_smo));
			tv_temp.setText(String.valueOf(get_temp) + "��");
			if (Float.valueOf(random.nextInt(10) % (10 - 1 + 1)) > 5) {
				tv_per.setText("����");
				per = 1;
			} else {
				tv_per.setText("����");
				per = 0;
			}
			IV_HUM_ZHIZHEN.setRotation(hum % 180);
			IV_TEMP_ZHIZHEN.setRotation(temp % 180);
			handler.postDelayed(timeRunnable, 2000);
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
