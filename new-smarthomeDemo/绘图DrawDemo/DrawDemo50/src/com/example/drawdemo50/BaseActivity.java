package com.example.drawdemo50;

import org.json.JSONException;

import lib.Json_data;
import lib.SocketThread;
import lib.Updata_activity;
import lib.json_dispose;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class BaseActivity extends Fragment implements OnCheckedChangeListener {
	private TextView tv_temp, tv_hum, tv_gas, tv_press, tv_smo, tv_ill, tv_co,
			tv_pm, tv_per;
	private Switch sw_lamp, sw_door, sw_fan, sw_tv, sw_kongtiao, sw_dvd,
			sw_warm;
	private RadioGroup rg_base_check;
	private RadioButton ra_open, ra_cls, ra_stop;
	private EditText et_send;
	private Button btn_send1, btn_send2;
	private Thread UpdataThread;
	private int count;
	static json_dispose Js = new json_dispose();
	static float temp, hum, gas, press, smo, ill, co, pm, per;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_base, container, false);
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_dvd = (Switch) view.findViewById(R.id.sw_dvd);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_kongtiao = (Switch) view.findViewById(R.id.sw_kongtiao);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_tv = (Switch) view.findViewById(R.id.sw_tv);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		rg_base_check = (RadioGroup) view.findViewById(R.id.rg_base_cur);
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		et_send = (EditText) view.findViewById(R.id.et_send);
		btn_send1 = (Button) view.findViewById(R.id.btn_send1);
		btn_send2 = (Button) view.findViewById(R.id.btn_send2);
		sw_door.setOnCheckedChangeListener(this);
		sw_dvd.setOnCheckedChangeListener(this);
		sw_fan.setOnCheckedChangeListener(this);
		sw_kongtiao.setOnCheckedChangeListener(this);
		sw_lamp.setOnCheckedChangeListener(this);
		sw_tv.setOnCheckedChangeListener(this);
		sw_warm.setOnCheckedChangeListener(this);
		count = 1;
		network();
		updata();
		curchekc();
		redsend();
		return view;
	}

	private void redsend() {
		btn_send1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "请输入数值！", Toast.LENGTH_SHORT)
							.show();
				} else {
					int sendtext = Integer
							.valueOf(et_send.getText().toString());
					if (sendtext > 10) {
						Toast.makeText(getActivity(), "数值不能超过10",
								Toast.LENGTH_SHORT).show();
					} else if (sendtext < 1) {
						Toast.makeText(getActivity(), "数值不能小于1",
								Toast.LENGTH_SHORT).show();
					} else {
						Js.control(Json_data.InfraredEmit, 0, sendtext);
						Toast.makeText(getActivity(), "已发送" + sendtext,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	}

	private void curchekc() {
		ra_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Curtain, 0, 2);
			}
		});
		ra_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Curtain, 0, 1);
			}
		});
		ra_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Curtain, 0, 3);
			}
		});
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
							.toString().equals("")) {
						tv_per.setText("有人");
					} else {
						tv_per.setText("无人");
					}
				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				network();
			}
		};
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
						Toast.makeText(getActivity(), "网络连接失败，请检查网络连接",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getActivity(), "网络连接成功",
								Toast.LENGTH_SHORT).show();
						count = 0;
					}
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
				Js.control(Json_data.InfraredEmit, 0, 1);
			} else {
				Js.control(Json_data.InfraredEmit, 0, 1);
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
