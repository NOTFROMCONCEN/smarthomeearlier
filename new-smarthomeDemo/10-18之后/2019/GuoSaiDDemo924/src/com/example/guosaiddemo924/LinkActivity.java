package com.example.guosaiddemo924;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @�ļ�����LinkActivity.java
 * @��������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-24
 */
public class LinkActivity extends Activity {
	private ImageView iv_back_button;// ���ذ�ť
	private ImageView iv_back_text;// �����ı�
	private TextView tv_time;
	private ToggleButton tg_link_state;
	private RadioButton ra_temp;// �¶�
	private RadioButton ra_hum;// ʪ��
	private RadioButton ra_gas;// ȼ��
	private RadioButton ra_ill;// ����
	private RadioButton ra_pm;// PM
	private RadioButton ra_press;// ��ѹ
	private RadioButton ra_smo;// ����
	private RadioButton ra_co;// Co2
	private RadioButton ra_per;// �������
	private RadioButton ra_big;// ����
	private RadioButton ra_small;// С��
	private EditText et_number_get;// �ı���
	private RadioButton ra_open;// ��
	private RadioButton ra_cls;// �ر�

	private RadioButton ra_lamp;// ���
	private RadioButton ra_door;// �Ž�
	private RadioButton ra_fan;// ����
	private RadioButton ra_warm;// ������
	private RadioButton ra_tv;// ����
	private RadioButton ra_dvd;// DVD
	private RadioButton ra_kongtiao;// �յ�

	private boolean link_state = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		iv_back_button = (ImageView) findViewById(R.id.iv_back_link_button);
		iv_back_text = (ImageView) findViewById(R.id.iv_back_link_text);
		tg_link_state = (ToggleButton) findViewById(R.id.tg_link_start);
		ra_big = (RadioButton) findViewById(R.id.radio_big);
		ra_small = (RadioButton) findViewById(R.id.radio_small);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);

		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_ill = (RadioButton) findViewById(R.id.ra_ill);
		ra_pm = (RadioButton) findViewById(R.id.ra_pm);
		ra_press = (RadioButton) findViewById(R.id.ra_press);
		ra_smo = (RadioButton) findViewById(R.id.ra_smo);
		ra_co = (RadioButton) findViewById(R.id.ra_co);
		ra_per = (RadioButton) findViewById(R.id.ra_per);

		ra_lamp = (RadioButton) findViewById(R.id.ra_lamp);
		ra_door = (RadioButton) findViewById(R.id.ra_door);
		ra_fan = (RadioButton) findViewById(R.id.ra_fan);
		ra_warm = (RadioButton) findViewById(R.id.ra_warm);
		ra_tv = (RadioButton) findViewById(R.id.ra_tv);
		ra_dvd = (RadioButton) findViewById(R.id.ra_dvd);
		ra_kongtiao = (RadioButton) findViewById(R.id.ra_kongtiao);
		et_number_get = (EditText) findViewById(R.id.et_number_get);

		// ����
		tg_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showTaost(getApplicationContext(), "��������ֵ");
						link_state = false;
						tg_link_state.setChecked(false);
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});

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
			System.out.println(BaseActivity.ill);
			if (link_state) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showTaost(getApplicationContext(), "��������ֵ");
					link_state = false;
					tg_link_state.setChecked(false);
				} else {
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (ra_temp.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.temp > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.temp > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_hum.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.hum > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.hum < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_gas.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.gas > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.gas < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_ill.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.ill > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.ill < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_pm.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.pm > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.pm < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_pm.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.pm > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.pm < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_press.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.press > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.press < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_smo.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.smo > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.smo < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_co.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.co > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.co > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

					if (ra_per.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.per > number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.per < number_get) {
								if (ra_open.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}

								if (ra_cls.isChecked()) {
									if (ra_lamp.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_door.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_fan.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_warm.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (ra_tv.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.CLOSE);
									}
									if (ra_dvd.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.CLOSE);
									}
									if (ra_kongtiao.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								DiyToast.showTaost(getApplicationContext(),
										"����������");
								link_state = false;
								tg_link_state.setChecked(false);
							}
						}
					}

				}

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
