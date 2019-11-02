package com.example.xuanba2019;

import org.json.JSONException;

import com.bizideal.smarthometest.lib.Json_data;
import com.bizideal.smarthometest.lib.SocketThread;
import com.bizideal.smarthometest.lib.Updata_activity;
import com.bizideal.smarthometest.lib.json_dispose;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CenterActivity extends Activity {
	private TextView tv_co2, tv_ill, tv_hum, tv_temp, tv_gas, tv_press, tv_pm,
			tv_smo, tv_per;
	private Switch sw_warm, sw_fan, sw_lamp, sw_door;
	private RadioGroup rg_1;
	private RadioButton ra_open, ra_cls, ra_stop;
	private EditText et_send;
	private Button btn_send;
	private CheckBox cb_anfang, cb_zhineng;
	private Thread UpdataThread;
	private int count;
	private TextView back_tv;
	private int sendtext;
	private json_dispose Js = new json_dispose();
	private boolean anfang_state = false, zhineng_state = false;
	private int ill_mode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_center);
		cb_anfang = (CheckBox) findViewById(R.id.cb_anfang);
		cb_zhineng = (CheckBox) findViewById(R.id.cb_zhineng);
		tv_co2 = (TextView) findViewById(R.id.tv_co2);
		tv_gas = (TextView) findViewById(R.id.tv_gas);
		tv_hum = (TextView) findViewById(R.id.tv_hum);
		tv_ill = (TextView) findViewById(R.id.tv_ill);
		tv_per = (TextView) findViewById(R.id.tv_per);
		tv_pm = (TextView) findViewById(R.id.tv_pm);
		tv_press = (TextView) findViewById(R.id.tv_press);
		tv_smo = (TextView) findViewById(R.id.tv_smo);
		tv_temp = (TextView) findViewById(R.id.tv_temp);
		sw_door = (Switch) findViewById(R.id.sw_door);
		sw_fan = (Switch) findViewById(R.id.sw_fan);
		sw_lamp = (Switch) findViewById(R.id.sw_lamp);
		sw_warm = (Switch) findViewById(R.id.sw_warm);
		ra_cls = (RadioButton) findViewById(R.id.ra_cls);
		ra_open = (RadioButton) findViewById(R.id.ra_open);
		ra_stop = (RadioButton) findViewById(R.id.ra_stop);
		rg_1 = (RadioGroup) findViewById(R.id.radioGroup1);
		et_send = (EditText) findViewById(R.id.et_send);
		btn_send = (Button) findViewById(R.id.btn_send);

		count = 1;

		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_send.getText().toString().equals("")) {
					Toast.makeText(CenterActivity.this, "请输入数值",
							Toast.LENGTH_SHORT).show();
				} else {
					sendtext = Integer.valueOf(et_send.getText().toString());
					if (sendtext > 4) {
						Toast.makeText(CenterActivity.this, "无法发射（最高为3）",
								Toast.LENGTH_SHORT).show();
					} else {
						Js.control(Json_data.InfraredLaunch, 0, sendtext);
						Toast.makeText(CenterActivity.this, "已发送" + sendtext,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		sw_door.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.RFID_Open_Door, 0, 1);
				} else {

				}
			}
		});
		sw_fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.Fan, 0, 1);
				} else {
					Js.control(Json_data.Fan, 0, 0);
				}
			}
		});
		sw_lamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.Lamp, 0, 1);
				} else {
					Js.control(Json_data.Lamp, 0, 0);
				}
			}
		});
		sw_warm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.WarningLight, 0, 1);
				} else {
					Js.control(Json_data.WarningLight, 0, 0);
				}
			}
		});
		rg_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (ra_cls.isChecked()) {
					Js.control(Json_data.Curtain, 0, 3);
				} else if (ra_open.isChecked()) {
					Js.control(Json_data.Curtain, 0, 1);
				} else if (ra_stop.isChecked()) {
					Js.control(Json_data.Curtain, 0, 2);
				}
			}
		});
		SocketThread.mHandlerSocketState = new Handler() {
			public void handleMessage(android.os.Message msg) {
				super.handleMessage(msg);
				Bundle bundle = msg.getData();
				if (count == 1) {
					if (bundle.getString("SocketThread_State") == "error") {
						Toast.makeText(CenterActivity.this, "网络连接失败，请重试",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(CenterActivity.this, "网络连接成功",
								Toast.LENGTH_SHORT).show();
						count = 0;
					}
				}
			}
		};
		UpdataThread = new Thread(new Updata_activity());
		UpdataThread.start();
		Updata_activity.updatahandler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				try {
					Js.receive();
					tv_co2.setText(Js.receive_data.get(Json_data.Co2)
							.toString());
					tv_gas.setText(Js.receive_data.get(Json_data.Gas)
							.toString());
					tv_hum.setText(Js.receive_data.get(Json_data.Humidity)
							.toString());
					tv_ill.setText(Js.receive_data.get(Json_data.Illumination)
							.toString());
					tv_temp.setText(Js.receive_data.get(Json_data.Temp)
							.toString());
					tv_pm.setText(Js.receive_data.get(Json_data.PM25)
							.toString());
					tv_press.setText(Js.receive_data.get(Json_data.AirPressure)
							.toString());
					tv_smo.setText(Js.receive_data.get(Json_data.Smoke)
							.toString());
					if (Js.receive_data.get(Json_data.StateHumanInfrared)
							.toString().equals("1")) {
						tv_per.setText("有人");
					} else {
						tv_per.setText("无人");
					}

				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};

		back_tv = (TextView) findViewById(R.id.back_tv);
		back_tv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						CenterActivity.this);
				builder.setMessage("提示框");
				builder.setTitle("确定退出吗？");
				builder.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								System.exit(0);
							}
						});
				builder.setNegativeButton("取消",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub

							}
						});
				builder.show();
			}
		});
		cb_anfang
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							anfang_state = true;
						} else {
							anfang_state = false;
						}
					}
				});
		cb_zhineng
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							zhineng_state = true;
						} else {
							zhineng_state = false;
						}
					}
				});
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (anfang_state) {
				if (tv_per.getText().toString().equals("有人")) {
					Js.control(Json_data.WarningLight, 0, 1);
					Js.control(Json_data.Lamp, 0, 1);
				} else {
					Js.control(Json_data.WarningLight, 0, 0);
					Js.control(Json_data.Lamp, 0, 0);
				}
			}
			if (zhineng_state) {
				ill_mode = Integer.valueOf(tv_ill.getText().toString());
				if (ill_mode > 50) {
					Js.control(Json_data.Curtain, 0, 1);
					Js.control(Json_data.Lamp, 0, 0);
				} else {
					Js.control(Json_data.Curtain, 0, 2);
					Js.control(Json_data.Lamp, 0, 1);
				}
			}
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
