package com.example.guosaiddemo924;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݲɼ�����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-24
 */
public class BaseActivity extends Activity {
	private TextView tv_gas;// ȼ��
	private TextView tv_ill;// ����
	private TextView tv_press;// ��ѹ
	private TextView tv_hum;// ʪ��
	private TextView tv_pm;// PM2.5
	private TextView tv_smo;// ����
	private TextView tv_temp;// �¶�
	private TextView tv_co;// Co2
	private TextView tv_per;// �������
	private ImageView iv_back_button;// ���ذ�ť
	private ImageView iv_back_text;// �����ı�
	private TextView tv_time;
	private Random random = new Random();
	public static float temp, hum, ill, pm, press, co, per, gas, smo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		tv_time = (TextView) findViewById(R.id.tv_chuanganqi_time);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		iv_back_button = (ImageView) findViewById(R.id.iv_back_chuanganqi_button);
		iv_back_text = (ImageView) findViewById(R.id.iv_back_chuanganqi_text);
		// �رս���
		iv_back_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		iv_back_text.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		// ����������ɫ
		tv_time.setTextColor(Color.CYAN);
		// ���ݲɼ�
		ControlUtils.setUser("bizideal", "123456", MainActivity.IP_NUMBER);
		SocketClient.getInstance().creatConnect();
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
					tv_temp.setText(getdata.getTemperature());
					temp = Float.parseFloat(getdata.getTemperature());
				}
				if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
					tv_hum.setText(getdata.getHumidity());
					hum = Float.parseFloat(getdata.getHumidity());
				}
				if (!TextUtils.isEmpty(getdata.getIllumination())) {// ����
					tv_ill.setText(getdata.getIllumination());
					ill = Float.parseFloat(getdata.getIllumination());
				}
				if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
					tv_smo.setText(getdata.getSmoke());
					smo = Float.parseFloat(getdata.getSmoke());
				}
				if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
					tv_gas.setText(getdata.getGas());
					gas = Float.parseFloat(getdata.getGas());
				}
				if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
					tv_press.setText(getdata.getAirPressure());
					press = Float.parseFloat(getdata.getAirPressure());
				}
				if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
					tv_co.setText(getdata.getGas());
					co = Float.parseFloat(getdata.getGas());
				}
				if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
					tv_pm.setText(getdata.getPM25());
					pm = Float.parseFloat(getdata.getPM25());
				}
				if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// �������
					if (getdata.getStateHumanInfrared().toString()
							.equals(ConstantUtil.CLOSE)) {
						tv_per.setText("����");
						per = 0;
					} else {
						tv_per.setText("����");
						per = 1;
					}
				}
			}
		});
		// �������
		handler.post(timeRunnable);

	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ���ȡʱ��
	 * 
	 * @ʱ �䣺����8:51:13
	 */
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
			tv_time.setText(MenuActivity.TIME);
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
