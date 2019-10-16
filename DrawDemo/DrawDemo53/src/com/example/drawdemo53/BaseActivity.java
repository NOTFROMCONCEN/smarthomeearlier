package com.example.drawdemo53;

import org.json.JSONException;

import lib.Json_data;
import lib.SocketThread;
import lib.Updata_activity;
import lib.json_dispose;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class BaseActivity extends Fragment {
	private TextView tv_temp, tv_hum, tv_gas, tv_press, tv_smo, tv_ill, tv_co,
			tv_pm, tv_per;
	private Switch sw_lamp, sw_door, sw_fan, sw_tv, sw_kongtiao, sw_dvd,
			sw_warm;
	private RadioButton ra_open, ra_cls, ra_stop;
	private RadioGroup rg_cur_check;
	private EditText et_send;
	private Button btn_send1, btn_send2;
	private Thread UpdataThread;
	private int count = 1;
	static float temp, hum, gas, press, smo, ill, co, pm, per;
	private Spinner sp_1;
	static json_dispose Js = new json_dispose();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub.
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
		ra_cls = (RadioButton) view.findViewById(R.id.ra_cls);
		ra_open = (RadioButton) view.findViewById(R.id.ra_open);
		ra_stop = (RadioButton) view.findViewById(R.id.ra_stop);
		rg_cur_check = (RadioGroup) view.findViewById(R.id.rg_base_cur);
		et_send = (EditText) view.findViewById(R.id.et_send);
		btn_send1 = (Button) view.findViewById(R.id.btn_send1);
		btn_send2 = (Button) view.findViewById(R.id.btn_send1);
		updata();
		network();
		redsend();
		toggbutton();
		return view;
	}

	private void toggbutton() {
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
						Toast.makeText(getActivity(), "数值不能大于10！",
								Toast.LENGTH_SHORT).show();
					} else if (sendtext < 1) {
						Toast.makeText(getActivity(), "数值不能小于1！",
								Toast.LENGTH_SHORT).show();
					} else {
						Js.control(Json_data.InfraredEmit, 0, sendtext);
						Toast.makeText(getActivity(), "已发送" + sendtext,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		btn_send2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().equals("")) {
					Toast.makeText(getActivity(), "请输入数值", Toast.LENGTH_SHORT)
							.show();
				} else {
					int sendtext = Integer
							.valueOf(et_send.getText().toString());
					if (sendtext > 10) {
						Toast.makeText(getActivity(), "数值不能大于10！",
								Toast.LENGTH_SHORT).show();
					} else if (sendtext < 1) {
						Toast.makeText(getActivity(), "数值不能小于1！",
								Toast.LENGTH_SHORT).show();
					} else {
						Js.control(Json_data.InfraredLaunch, 0, sendtext);
						Toast.makeText(getActivity(), "已发送", Toast.LENGTH_SHORT)
								.show();
					}
				}
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
					tv_gas.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					tv_hum.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					tv_ill.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					tv_smo.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					tv_pm.setText(Js.receive_data.get(Json_data.Co2).toString());
					tv_press.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					tv_temp.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					if (Js.receive_data.get(Json_data.StateHumanInfrared)
							.toString().equals("")) {
						tv_per.setText("有人");
					} else {
						tv_per.setText("无人");
					}
					temp = Float.parseFloat(tv_temp.getText().toString());
					hum = Float.parseFloat(tv_hum.getText().toString());
					gas = Float.parseFloat(tv_gas.getText().toString());
					press = Float.parseFloat(tv_press.getText().toString());
					smo = Float.parseFloat(tv_smo.getText().toString());
					ill = Float.parseFloat(tv_ill.getText().toString());
					co = Float.parseFloat(tv_co.getText().toString());
					pm = Float.parseFloat(tv_pm.getText().toString());
				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
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
						// Toast.makeText(getActivity(), "网络连接错误，请检查网络连接",
						// Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getActivity(), "网络连接成功",
								Toast.LENGTH_SHORT).show();
						count = 0;
					}
				}
			}
		};
	}
}
