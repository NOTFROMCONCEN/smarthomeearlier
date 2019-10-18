package com.example.shengsaiyangtidemo9282019;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothClass.Device;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����BaseActivity.java
 * @���������ݲɼ����豸����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-28
 */
public class BaseActivity extends Activity {
	private Random random = new Random();
	public static float temp, hum, ill, gas, smo, co, pm, per, press;
	private EditText tv_temp, tv_hum, tv_ill, tv_smo, tv_gas, tv_pm, tv_co,
			tv_per, tv_press;
	private ToggleButton tg_lamp, tg_cur, tg_fan, tg_warm, tg_door;
	private Button btn_tongdao_1;
	private Button btn_tongdao_2;
	private Button btn_tongdao_3;
	private LinearLayout line_intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		// �����������Ӳ���
		web_link("bizideal", "123456", MainActivity.ip);
		// ��
		initView();
		// ���ó�����ת
		line_intent.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(getApplicationContext(),);
				// startActivity(intent);
				// finish();
				DiyToast.showToast(getApplicationContext(), "����");
				Intent intent = new Intent(getApplicationContext(),
						LinkActivity.class);
				startActivity(intent);
				return false;
			}
		});
		// �豸����
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
		// ���ⷢ��
		// ͨ��1
		btn_tongdao_1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// ͨ��2
		btn_tongdao_2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// ͨ��3
		btn_tongdao_3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// ���ݲɼ�
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							tv_temp.setText(getdata.getTemperature());
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(getdata.getHumidity());
						}
						if (TextUtils.isEmpty(getdata.getSmoke())) {// ��ζ
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(getdata.getGas());
						}
						if (TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							tv_press.setText(getdata.getAirPressure());
							press = Float.valueOf(getdata.getAirPressure());
						}
						if (TextUtils.isEmpty(getdata.getIllumination())) {// ����
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(getdata.getIllumination());
						}
						if (TextUtils.isEmpty(getdata.getCo2())) {// Co2
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(getdata.getCo2());
						}
						if (TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(getdata.getPM25());
						}
						if (TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// �������
							if (getdata.getStateHumanInfrared().toString()
									.equals(ConstantUtil.OPEN)) {
								tv_per.setText("����");
								per = 1;
							} else {
								tv_per.setText("����");
								per = 0;
							}
						}
					}
				});
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������initView();
	 * 
	 * @���ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:55:42
	 */
	private void initView() {
		btn_tongdao_1 = (Button) findViewById(R.id.btn_tongdao_1);
		btn_tongdao_2 = (Button) findViewById(R.id.btn_tongdao_2);
		btn_tongdao_3 = (Button) findViewById(R.id.btn_tongdao_3);
		tv_co = (EditText) findViewById(R.id.et_co);
		tv_gas = (EditText) findViewById(R.id.et_gas);
		tv_hum = (EditText) findViewById(R.id.et_hum);
		tv_ill = (EditText) findViewById(R.id.et_ill);
		tv_per = (EditText) findViewById(R.id.et_per);
		tv_pm = (EditText) findViewById(R.id.et_pm);
		tv_press = (EditText) findViewById(R.id.et_press);
		tv_smo = (EditText) findViewById(R.id.et_smo);
		tv_temp = (EditText) findViewById(R.id.et_temp);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_cur = (ToggleButton) findViewById(R.id.tg_cur);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		line_intent = (LinearLayout) findViewById(R.id.line_intent);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ������������ģ�����ݲɼ�
	 * 
	 * @ʱ �䣺����8:38:08
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

			handler.postDelayed(timeRunnable, 3000);
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
	 * @��������web_link
	 * 
	 * @�� �ܣ���������
	 * 
	 * @ʱ �䣺����8:28:42
	 */
	private void web_link(String username, String passward, String ip) {
		ControlUtils.setUser(username, passward, ip);
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {

			@Override
			public void onEvent(final String link_state) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (link_state.equals("Success")) {
							DiyToast.showToast(getApplicationContext(),
									"С��ʿ�����ӳɹ�");
						} else {
							new AlertDialog.Builder(BaseActivity.this)
									.setTitle("��¼ʧ��")
									.setMessage("��������ʧ�ܣ��Ƿ񷵻ص�¼���棿")
									.setPositiveButton(
											"����",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													Intent intent = new Intent(
															BaseActivity.this,
															MainActivity.class);
													startActivity(intent);
													finish();
												}
											})
									.setNegativeButton(
											"�ر�",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub

												}
											}).show();
						}
					}
				});
			}
		});
	}
}
