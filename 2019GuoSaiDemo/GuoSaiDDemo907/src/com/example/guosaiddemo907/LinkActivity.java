package com.example.guosaiddemo907;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ToggleButton;

/*
 * @文件名：LinkActivity.java
 * @描述：联动功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-7
 */
public class LinkActivity extends Activity {
	private RadioButton radio01;
	private RadioButton radio02;
	private RadioButton radio03;
	private RadioButton radio04;
	private RadioButton radio05;
	private RadioButton radio06;
	private RadioButton radio07;
	private RadioButton radio08;
	private RadioButton radio09;
	private RadioButton RadioButton01;
	private RadioButton RadioButton02;
	private RadioButton RadioButton03;
	private RadioButton RadioButton04;
	private RadioButton RadioButton05;
	private RadioButton RadioButton06;
	private RadioButton RadioButton07;
	private RadioButton RadioButton08;
	private RadioButton RadioButton09;
	private RadioButton ra_open, ra_cls;
	private RadioButton ra_big, ra_small;
	private ToggleButton tg_link_start;
	private EditText et_number_get;
	private boolean link_state = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		ra_big = (RadioButton) findViewById(R.id.radio_big);
		ra_small = (RadioButton) findViewById(R.id.radio_small);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);
		radio01 = (RadioButton) findViewById(R.id.radio0);
		radio02 = (RadioButton) findViewById(R.id.radio1);
		radio03 = (RadioButton) findViewById(R.id.radio2);
		radio04 = (RadioButton) findViewById(R.id.radio3);
		radio05 = (RadioButton) findViewById(R.id.radio4);
		radio06 = (RadioButton) findViewById(R.id.radio5);
		radio07 = (RadioButton) findViewById(R.id.radio6);
		radio08 = (RadioButton) findViewById(R.id.radio7);
		radio09 = (RadioButton) findViewById(R.id.radio8);
		RadioButton01 = (RadioButton) findViewById(R.id.RadioButton01);
		RadioButton02 = (RadioButton) findViewById(R.id.RadioButton02);
		RadioButton03 = (RadioButton) findViewById(R.id.RadioButton03);
		RadioButton04 = (RadioButton) findViewById(R.id.RadioButton04);
		RadioButton05 = (RadioButton) findViewById(R.id.RadioButton05);
		RadioButton06 = (RadioButton) findViewById(R.id.RadioButton06);
		RadioButton07 = (RadioButton) findViewById(R.id.RadioButton07);
		RadioButton08 = (RadioButton) findViewById(R.id.RadioButton08);
		RadioButton09 = (RadioButton) findViewById(R.id.RadioButton09);
		tg_link_start = (ToggleButton) findViewById(R.id.tg_link_start);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		tg_link_start
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							if (et_number_get.getText().toString().equals("")) {
								tg_link_start.setChecked(false);
								link_state = false;
								DiyToast.showToast(getApplicationContext(),
										"请输入数值");
							} else {
								link_state = true;
							}
						} else {
							link_state = false;
						}
					}
				});

		// 激活进程
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (et_number_get.getText().toString().equals("")) {
					DiyToast.showToast(getApplicationContext(), "数值不能为空");
					link_state = false;
					tg_link_start.setChecked(false);
				} else {
					if (radio01.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.temp > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.temp < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
					}
					if (radio02.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.hum > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.hum < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
					}

					if (radio03.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.gas > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.gas < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
					}

					if (radio04.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.ill > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.ill < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
					}

					if (radio05.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.pm > Integer.valueOf(et_number_get
									.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.pm < Integer.valueOf(et_number_get
									.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}

					}

					if (radio06.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.press > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.press < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}

					}

					if (radio07.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.smo > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.smo < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}

					}

					if (radio08.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.co > Integer.valueOf(et_number_get
									.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.co < Integer.valueOf(et_number_get
									.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}

					}

					if (radio09.isChecked()) {
						if (ra_big.isChecked()) {
							if (BaseActivity.per > Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}

								}
							}
						}
						if (ra_small.isChecked()) {
							if (BaseActivity.per < Integer
									.valueOf(et_number_get.getText().toString())) {
								if (ra_open.isChecked()) {
									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
								if (ra_cls.isChecked()) {

									if (RadioButton01.isChecked()) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton02.isChecked()) {
										ControlUtils.control(
												ConstantUtil.RFID_Open_Door,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton03.isChecked()) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
									if (RadioButton04.isChecked()) {
									}
									if (RadioButton05.isChecked()) {
										ControlUtils.control(
												ConstantUtil.WarningLight,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (RadioButton06.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (RadioButton07.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_3,
												ConstantUtil.OPEN);
									}
									if (RadioButton08.isChecked()) {
									}
									if (RadioButton09.isChecked()) {
										ControlUtils.control(
												ConstantUtil.INFRARED_1_SERVE,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.OPEN);
									}
								}
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