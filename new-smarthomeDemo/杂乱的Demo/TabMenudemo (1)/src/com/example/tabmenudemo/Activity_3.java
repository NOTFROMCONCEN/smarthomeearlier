package com.example.tabmenudemo;

import org.json.JSONException;
import lib.Json_data;
import lib.SocketThread;
import lib.Updata_activity;
import lib.json_dispose;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Activity_3 extends Activity implements OnCheckedChangeListener {
	private TextView tv_temp, tv_hum, tv_gas, tv_press, tv_ill, tv_smo, tv_co,
			tv_pm, tv_per;
	private Switch sw_lamp, sw_door, sw_fan, sw_tv, sw_kongtiao, sw_dvd,
			sw_warm;
	private RadioButton ra_open, ra_cls, ra_stop;
	private RadioGroup rg_base_cur;
	private EditText et_send;
	private Button btn_send1, btn_send2;
	static json_dispose Js = new json_dispose();
	private Thread UpdataThread;
	private int count = 1;
	static float temp, hum, gas, press, ill, smo, co, pm, per;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		tv_co = (TextView) findViewById(R.id.tv_co);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		sw_door = (Switch) findViewById(R.id.sw_door);
		sw_dvd = (Switch) findViewById(R.id.sw_dvd);
		sw_fan = (Switch) findViewById(R.id.sw_fan);
		sw_kongtiao = (Switch) findViewById(R.id.sw_kongtiao);
		sw_lamp = (Switch) findViewById(R.id.sw_lamp);
		sw_tv = (Switch) findViewById(R.id.sw_tv);
		sw_warm = (Switch) findViewById(R.id.sw_warm);
		rg_base_cur = (RadioGroup) findViewById(R.id.radioGroup1);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_stop = (RadioButton) findViewById(R.id.ra_stop);
		et_send = (EditText) findViewById(R.id.et_send);
		btn_send1 = (Button) findViewById(R.id.btn_send1);
		btn_send2 = (Button) findViewById(R.id.btn_send2);
		sw_door.setOnCheckedChangeListener(this);
		sw_dvd.setOnCheckedChangeListener(this);
		sw_fan.setOnCheckedChangeListener(this);
		sw_kongtiao.setOnCheckedChangeListener(this);
		sw_lamp.setOnCheckedChangeListener(this);
		sw_tv.setOnCheckedChangeListener(this);
		sw_warm.setOnCheckedChangeListener(this);
		updata();
		network();
	}

	private void network() {
		SocketThread.mHandlerSocketState = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Bundle b = msg.getData();
				if (count == 1) {
					if (b.getString("SocketThread_State") == "error") {
						Toast.makeText(Activity_3.this, "网络连接失败，请检查网络连接",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(Activity_3.this, "网络连接成功",
								Toast.LENGTH_SHORT).show();
						count = 0;
					}
				}
			}
		};
	}

	private void updata() {
		UpdataThread = new Thread(new Updata_activity());
		UpdataThread.start();
		Updata_activity.updatahandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				try {
					Js.receive();
					tv_co.setText(Js.receive_data.get(Json_data.Co2).toString());
					tv_gas.setText(Js.receive_data.get(Json_data.Gas)
							.toString());
					tv_hum.setText(Js.receive_data.get(Json_data.Humidity)
							.toString());
					tv_ill.setText(Js.receive_data.get(Json_data.Illumination)
							.toString());
					tv_pm.setText(Js.receive_data.get(Json_data.PM25)
							.toString());
					tv_press.setText(Js.receive_data.get(Json_data.AirPressure)
							.toString());
					tv_smo.setText(Js.receive_data.get(Json_data.Smoke)
							.toString());
					tv_temp.setText(Js.receive_data.get(Json_data.Temp)
							.toString());
					if (Js.receive_data.get(Json_data.StateHumanInfrared)
							.toString().equals("1")) {
						tv_per.setText("有人");
						per = Float.parseFloat("1");
					} else {
						tv_per.setText("无人");
						per = Float.parseFloat("0");
					}

					temp = Float.parseFloat(tv_temp.getText().toString());
					hum = Float.parseFloat(tv_hum.getText().toString());
					gas = Float.parseFloat(tv_gas.getText().toString());
					press = Float.parseFloat(tv_press.getText().toString());
					ill = Float.parseFloat(tv_ill.getText().toString());
					smo = Float.parseFloat(tv_smo.getText().toString());
					co = Float.parseFloat(tv_co.getText().toString());
					pm = Float.parseFloat(tv_pm.getText().toString());

				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		switch (buttonView.getId()) {
		case R.id.sw_door:
			if (isChecked) {
				Js.control(Json_data.RFID_Open_Door, 0, 1);
			} else {
				Js.control(Json_data.RFID_Open_Door, 0, 0);
			}
			break;
		case R.id.sw_dvd:
			if (isChecked) {
				Js.control(Json_data.InfraredEmit, 0, 3);
			} else {
				Js.control(Json_data.InfraredEmit, 0, 3);
			}
			break;
		case R.id.sw_fan:
			if (isChecked) {
				Js.control(Json_data.Fan, 0, 1);
			} else {
				Js.control(Json_data.Fan, 0, 0);
			}
			break;
		case R.id.sw_kongtiao:
			if (isChecked) {
				Js.control(Json_data.InfraredEmit, 0, 2);
			} else {
				Js.control(Json_data.InfraredEmit, 0, 2);
			}
			break;
		case R.id.sw_lamp:
			if (isChecked) {
				Js.control(Json_data.Lamp, 0, 1);
			} else {
				Js.control(Json_data.Lamp, 0, 0);
			}
			break;
		case R.id.sw_tv:
			if (isChecked) {
				Js.control(Json_data.RFID_Open_Door, 0, 1);
			} else {
				Js.control(Json_data.RFID_Open_Door, 0, 1);
			}
			break;
		case R.id.sw_warm:
			if (isChecked) {
				Js.control(Json_data.WarningLight, 0, 1);
			} else {
				Js.control(Json_data.WarningLight, 0, 0);
			}
			break;
		default:
			break;
		}
	}
}
