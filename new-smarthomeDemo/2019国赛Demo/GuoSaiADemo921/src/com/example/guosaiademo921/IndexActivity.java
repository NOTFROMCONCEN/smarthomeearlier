package com.example.guosaiademo921;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @�ļ�����IndexActivity.java
 * @������������ҳ�����������ģʽ�����ݲɼ�������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-21
 */
public class IndexActivity extends Activity {
	private ArrayAdapter<String> mAdaHelper;
	private String[] mStringArray;
	static boolean web_state = true;// �������ӱ�ʶ
	public static float temp, hum, ill, pm, per, smo, gas, press, co;
	// �����
	private Random random = new Random();

	// ��
	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private TextView tv_temp;// �¶�
	private TextView tv_hum;// ʪ��
	private TextView tv_gas;// ȼ��
	private TextView tv_press;// ��ѹ
	private TextView tv_ill;// ����
	private TextView tv_pm;// PM
	private TextView tv_co;// Co
	private TextView tv_smo;// ����
	private TextView tv_per;// �������
	private Button btn_to_state;// �豸����״̬
	private Button btn_kongtiao;// �յ�
	private Button btn_tv;// ����
	private Button btn_dvd;// DVD
	private Button btn_cur_open;// ������
	private Button btn_cur_cls;// ������
	private Button btn_cur_stop;// ����ͣ
	private ToggleButton tg_lamp;// ���
	private ToggleButton tg_door;// �Ž�
	private ToggleButton tg_fan;// ����
	private ToggleButton tg_warm;// ������
	private EditText et_number_get;
	private CheckBox cb_lvyou_mode;
	private CheckBox cb_temp_mode;
	private CheckBox cb_anfang_mode;
	private CheckBox cb_diy_mode;
	private boolean diy_mode_state = false;// �Զ���ģʽ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		btn_to_state = (Button) findViewById(R.id.btn_moveto_state);
		btn_cur_cls = (Button) findViewById(R.id.btn_cls);
		btn_cur_open = (Button) findViewById(R.id.btn_open);
		btn_cur_stop = (Button) findViewById(R.id.btn_stop);
		btn_kongtiao = (Button) findViewById(R.id.btn_kongtiao);
		btn_dvd = (Button) findViewById(R.id.btn_dvd);
		btn_tv = (Button) findViewById(R.id.btn_tv);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		cb_anfang_mode = (CheckBox) findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) findViewById(R.id.cb_diy_mode);
		cb_lvyou_mode = (CheckBox) findViewById(R.id.cb_lvyou_mode);
		cb_temp_mode = (CheckBox) findViewById(R.id.cb_temp_mode);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		// ��ת���豸����״̬
		btn_to_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						LinkStateActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// .�豸����
		// �Ž�
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
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
		// ����ң��
		// ����
		btn_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// �յ�
		btn_kongtiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// DVD
		btn_dvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		// ��������
		// ��
		btn_cur_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		// ��
		btn_cur_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		// ͣ
		btn_cur_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});

		// ���ø��֡�ֻ��ѡ��һ�����
		// ����ģʽ
		cb_anfang_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_anfang_mode.setChecked(false);
							cb_diy_mode.setChecked(false);
							cb_lvyou_mode.setChecked(false);
							cb_temp_mode.setChecked(false);
							System.out.println("����");
						}
					}
				});
		// ����ģʽ
		cb_lvyou_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					// cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(false);
				}
			}
		});
		// �¶�ģʽ
		cb_temp_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					cb_lvyou_mode.setChecked(false);
					// cb_temp_mode.setChecked(false);
				}
			}
		});
		// �Զ���ģʽ
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					// cb_diy_mode.setChecked(false);
					cb_lvyou_mode.setChecked(false);
					cb_temp_mode.setChecked(false);
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "��ֵ����Ϊ��");
						diy_mode_state = false;
						cb_diy_mode.setChecked(false);
					} else {
						diy_mode_state = true;
					}
				} else {
					diy_mode_state = false;
				}
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
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// ��ѹ
							tv_press.setText(getdata.getAirPressure());
							press = Float
									.valueOf(tv_press.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// �¶�
							tv_temp.setText(getdata.getTemperature());
							temp = Float.valueOf(tv_temp.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// ʪ��
							tv_hum.setText(getdata.getHumidity());
							hum = Float.valueOf(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// ȼ��
							tv_gas.setText(getdata.getGas());
							gas = Float.valueOf(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// ���ն�
							tv_ill.setText(getdata.getIllumination());
							ill = Float.valueOf(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							tv_pm.setText(getdata.getPM25());
							pm = Float.valueOf(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// ����
							tv_smo.setText(getdata.getSmoke());
							smo = Float.valueOf(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							tv_co.setText(getdata.getCo2());
							co = Float.valueOf(tv_co.getText().toString());
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
			}
		});
		// �������
		handler.post(timeRunnable);
		// ����Spinner1���¶ȡ����գ�
		mStringArray = getResources().getStringArray(R.array.temp_ill);
		mAdaHelper = new AdaHelper(getApplicationContext(), mStringArray);
		sp_1.setAdapter(mAdaHelper);
		// ����Spinner2����С��
		mStringArray = getResources().getStringArray(R.array.big_small);
		mAdaHelper = new AdaHelper(getApplicationContext(), mStringArray);
		sp_2.setAdapter(mAdaHelper);
		// ����Spinner3�����ȡ���ƣ�
		mStringArray = getResources().getStringArray(R.array.fan_lamp);
		mAdaHelper = new AdaHelper(getApplicationContext(), mStringArray);
		sp_3.setAdapter(mAdaHelper);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�������������������ӡ�����ģʽ
	 * 
	 * @ʱ �䣺����9:00:25
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			/**
			 * ģʽ����
			 */
			if (cb_temp_mode.isChecked()) {
				// �¶�ģʽ
				// �����¶�ģʽ���򿪵��ӻ���DVD���յ��������ơ������ȡ���ơ�
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.WarningLight,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				tg_warm.setChecked(true);
				ControlUtils.control(ConstantUtil.Fan,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				tg_fan.setChecked(true);
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				tg_lamp.setChecked(true);
			}
			if (cb_lvyou_mode.isChecked()) {
				// ����ģʽ
				// ��������ģʽ���ر���ơ��򿪴�������PM2.5����75ʱ�����������ȡ�
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				tg_lamp.setChecked(false);
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				if (pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					tg_fan.setChecked(true);
				}
			}
			if (cb_anfang_mode.isChecked()) {
				// ����ģʽ
				// ��������ģʽ�������������Ӧ�����ˣ��򱨾��ƿ������ȫ����
				if (per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					tg_warm.setChecked(true);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					tg_lamp.setChecked(true);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					tg_warm.setChecked(false);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					tg_lamp.setChecked(false);
				}
			}
			/**
			 * ����
			 */
			if (diy_mode_state) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "��������ֵ");
					diy_mode_state = false;
					cb_diy_mode.setChecked(false);
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					String spinner3 = sp_3.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("�¶�")) {
						if (spinner2.equals(">")) {
							if (temp > number_get) {
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_fan.setChecked(true);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_lamp.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"����������");
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_fan.setChecked(false);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_lamp.setChecked(false);
								}

							}
						}
						if (spinner2.equals("<=")) {
							if (temp < number_get) {
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_fan.setChecked(true);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_lamp.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"����������");
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_fan.setChecked(false);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_lamp.setChecked(false);
								}

							}
						}
					}
					if (spinner1.equals("����")) {
						if (spinner2.equals(">")) {
							if (ill > number_get) {
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_fan.setChecked(true);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_lamp.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"����������");
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_fan.setChecked(false);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_lamp.setChecked(false);
								}

							}
						}
						if (spinner2.equals("<=")) {
							if (ill < number_get) {
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_fan.setChecked(true);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
									tg_lamp.setChecked(true);
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"����������");
								if (spinner3.equals("���ȴ�")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_fan.setChecked(false);
								}
								if (spinner3.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
									tg_lamp.setChecked(false);
								}

							}
						}
					}
				}
			}
			/**
			 * �����
			 */
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

			if (web_state) {
				ControlUtils.setUser("bizideal", "123456",
						LoginActivity.IP_NUMBER);
				SocketClient.getInstance().creatConnect();
				SocketClient.getInstance().login(new LoginCallback() {

					@Override
					public void onEvent(final String web_state) {
						// TODO Auto-generated method stub
						runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								if (web_state.equals("Success")) {
									// DiyToast.showToast(BarActivity.this,
									// "�������ӳɹ�");
									IndexActivity.this.web_state = false;
								} else {
									// DiyToast.showToast(BarActivity.this,
									// "��������ʧ��");
									IndexActivity.this.web_state = true;
								}
							}
						});
					}
				});
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