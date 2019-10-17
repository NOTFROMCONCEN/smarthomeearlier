package com.example.guosaibdemo2018915;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����LinkActivity.java
 * @���������ݲɼ��豸����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-15
 */
public class BaseActivity extends Activity {
	private EditText tv_temp;// �¶�
	private EditText tv_hum;// ʪ��
	private EditText tv_press;// ��ѹ
	private EditText tv_gas;// ȼ��
	private EditText tv_smo;// ����
	private EditText tv_ill;// ����
	private EditText tv_co;// Co2
	private EditText tv_pm;// PM2.5
	private EditText tv_per;// �������
	private ToggleButton tg_lamp1;// ���1
	private ToggleButton tg_lamp2;// ���2
	private ToggleButton tg_cur;// ����
	private ToggleButton tg_tv;// ���ӻ�
	private ToggleButton tg_kongtiao;// �յ�
	private ToggleButton tg_dvd;// DVD
	private ToggleButton tg_fan;// ������
	private ToggleButton tg_warm;// ������
	private ToggleButton tg_door;// �Ž�
	boolean web_state = true;
	private Random random = new Random();// �����
	static float temp, hum, co, pm, per, press, ill, smo, gas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		tv_co = (EditText) findViewById(R.id.et_co);
		tv_gas = (EditText) findViewById(R.id.et_gas);
		tv_hum = (EditText) findViewById(R.id.et_hum);
		tv_ill = (EditText) findViewById(R.id.et_ill);
		tv_per = (EditText) findViewById(R.id.et_per);
		tv_pm = (EditText) findViewById(R.id.et_pm);
		tv_press = (EditText) findViewById(R.id.et_press);
		tv_smo = (EditText) findViewById(R.id.et_smo);
		tv_temp = (EditText) findViewById(R.id.et_temp);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) findViewById(R.id.tg_kongtiao);
		tg_lamp1 = (ToggleButton) findViewById(R.id.tg_lamp1);
		tg_lamp2 = (ToggleButton) findViewById(R.id.tg_lamp2);
		tg_tv = (ToggleButton) findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
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
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
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
		// ���ݲɼ�
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

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
		// �������
		handler.post(timeRunnable);
	}

	// ��������
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			ControlUtils.setUser("bizideal", "123456", MainActivity.ip_number);
			SocketClient.getInstance().creatConnect();
			SocketClient.getInstance().login(new LoginCallback() {

				@Override
				public void onEvent(final String arg0) {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (arg0.equals("Success")) {
								DiyToast.showToast(getApplicationContext(),
										"�������ӳɹ�");
								web_state = false;
							} else {
								DiyToast.showToast(getApplicationContext(),
										"��������ʧ��");
							}
						}
					});
				}
			});
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
