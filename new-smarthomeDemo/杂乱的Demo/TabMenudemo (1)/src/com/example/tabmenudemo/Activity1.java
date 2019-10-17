package com.example.tabmenudemo;

import lib.Json_data;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class Activity1 extends Activity {
	private RadioButton ra_temp, ra_hum, ra_gas, ra_press, ra_smo, ra_ill,
			ra_co, ra_pm, ra_open, ra_cls, ra_big, ra_small;
	private CheckBox cb_lamp, cb_door, cb_fan, cb_warm, cb_cur;
	private EditText et_num;
	private Switch sw_open_state;
	private boolean cxknmsl = false;
	private int et_num_getnum;
	private RadioGroup rg_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_1);
		ra_big = (RadioButton) findViewById(R.id.ra_big);
		ra_cls = (RadioButton) findViewById(R.id.ra_big);
		ra_co = (RadioButton) findViewById(R.id.ra_big);
		ra_gas = (RadioButton) findViewById(R.id.ra_big);
		ra_hum = (RadioButton) findViewById(R.id.ra_big);
		ra_ill = (RadioButton) findViewById(R.id.ra_big);
		ra_open = (RadioButton) findViewById(R.id.ra_big);
		ra_pm = (RadioButton) findViewById(R.id.ra_big);
		ra_press = (RadioButton) findViewById(R.id.ra_big);
		ra_small = (RadioButton) findViewById(R.id.ra_big);
		ra_smo = (RadioButton) findViewById(R.id.ra_big);
		ra_temp = (RadioButton) findViewById(R.id.ra_big);
		cb_cur = (CheckBox) findViewById(R.id.cb_cur);
		cb_door = (CheckBox) findViewById(R.id.cb_door);
		cb_fan = (CheckBox) findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) findViewById(R.id.cb_wram);
		et_num = (EditText) findViewById(R.id.et_num);
		sw_open_state = (Switch) findViewById(R.id.sw_link_state);
		rg_1 = (RadioGroup) findViewById(R.id.radioGroup1);
		sw_open_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_num.getText().toString().equals("")) {
						Toast.makeText(Activity1.this, "数值不能为空！",
								Toast.LENGTH_SHORT).show();
						sw_open_state.setChecked(false);
						cxknmsl = false;
					} else {
						cxknmsl = true;
					}
				} else {
					cxknmsl = false;
				}
			}
		});
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (cxknmsl) {
				et_num_getnum = Integer.valueOf(et_num.getText().toString());
				Log.e("联动模式", "激活");
				if (ra_temp.isChecked()) {
					if (ra_big.isChecked()) {
						if (Activity_3.temp > et_num_getnum) {
							if (ra_open.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											1);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 1);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 1);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 1);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 1);
								}
							}
							if (ra_cls.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											3);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 0);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 0);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 1);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 0);
								}
							}
						}
					}
					if (ra_small.isChecked()) {
						if (Activity_3.temp < et_num_getnum) {
							if (ra_open.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											1);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 1);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 1);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 1);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 1);
								}
							}
							if (ra_cls.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											3);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 0);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 0);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 0);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 0);
								}
							}
						}
					}
				}

				if (ra_hum.isChecked()) {
					if (ra_big.isChecked()) {
						if (Activity_3.hum > et_num_getnum) {
							if (ra_open.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											1);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 1);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 1);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 1);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 1);
								}
							}
							if (ra_cls.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											3);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 0);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 0);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 1);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 0);
								}
							}
						}
					}

					if (ra_small.isChecked()) {
						if (Activity_3.hum < et_num_getnum) {
							if (ra_open.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											1);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 1);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 1);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 1);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 1);
								}
							}
							if (ra_cls.isChecked()) {
								if (cb_cur.isChecked()) {
									Activity_3.Js.control(Json_data.Curtain, 0,
											3);
								}
								if (cb_door.isChecked()) {
									Activity_3.Js.control(
											Json_data.RFID_Open_Door, 0, 0);
								}
								if (cb_fan.isChecked()) {
									Activity_3.Js.control(Json_data.Fan, 0, 0);
								}
								if (cb_warm.isChecked()) {
									Activity_3.Js.control(
											Json_data.WarningLight, 0, 0);
								}
								if (cb_lamp.isChecked()) {
									Activity_3.Js.control(Json_data.Lamp, 0, 0);
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
