package com.example.guosaibdemo826;

import org.json.JSONException;

import lib.Json_data;
import lib.Updata_activity;
import lib.json_dispose;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LinkActivity extends Activity {
	private TextView tv_room_num;
	private json_dispose Js = new json_dispose();
	private Switch sw_1;
	private Spinner sp_1, sp_2, sp_3, sp_4;
	private String spinner1, spinner2, spinner3, spinner4;
	private EditText et_num;
	private int et_num_data;
	private boolean link_state = false;
	private int count;
	private String temp, ill, hum;
	private float float_hum, float_temp, float_ill;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		tv_room_num = (TextView) findViewById(R.id.tv_link_room_num);
		tv_room_num.setText("房间号：" + MenuActivity.room_number);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_num = (EditText) findViewById(R.id.et_number_link);
		sw_1 = (Switch) findViewById(R.id.sw_link_open);
		count = 1;
		sw_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_num.getText().toString().equals("")) {
						sw_1.setChecked(false);
						Toast.makeText(LinkActivity.this, "请输入数值",
								Toast.LENGTH_SHORT).show();
						link_state = false;
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});
		handler.post(timeRunnable);
	}

	// 联动模式主代码
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				// 赋值
				spinner1 = sp_1.getSelectedItem().toString();
				spinner2 = sp_2.getSelectedItem().toString();
				spinner3 = sp_3.getSelectedItem().toString();
				spinner4 = sp_4.getSelectedItem().toString();
				et_num_data = Integer.valueOf(et_num.getText().toString());
				Log.e("线程激活", "联动模式开启");
				// 照度、大于、开风扇
				if (spinner1.equals("照度")) {
					if (spinner2.equals(">") && spinner3.equals("风扇")
							&& spinner4.equals("开")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Fan, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("风扇")
							&& spinner4.equals("关")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Fan, 0, 0);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("风扇")
							&& spinner4.equals("开")) {
						if (MenuActivity.ill < et_num_data) {
							Js.control(Json_data.Fan, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("风扇")
							&& spinner4.equals("关")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Fan, 0, 0);
						}
					}

					if (spinner2.equals(">") && spinner3.equals("射灯")
							&& spinner4.equals("开")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Lamp, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("射灯")
							&& spinner4.equals("关")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Lamp, 0, 0);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("射灯")
							&& spinner4.equals("开")) {
						if (MenuActivity.ill < et_num_data) {
							Js.control(Json_data.Lamp, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("射灯")
							&& spinner4.equals("关")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Lamp, 0, 0);
						}
					}

					if (spinner2.equals(">") && spinner3.equals("窗帘")
							&& spinner4.equals("开")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Curtain, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("窗帘")
							&& spinner4.equals("关")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Curtain, 0, 2);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("窗帘")
							&& spinner4.equals("开")) {
						if (MenuActivity.ill < et_num_data) {
							Js.control(Json_data.Curtain, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("窗帘")
							&& spinner4.equals("关")) {
						if (MenuActivity.ill > et_num_data) {
							Js.control(Json_data.Curtain, 0, 2);
						}
					}

				}

				// 温度、大于、开风扇
				if (spinner1.equals("温度")) {
					if (spinner2.equals(">") && spinner3.equals("风扇")
							&& spinner4.equals("开")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Fan, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("风扇")
							&& spinner4.equals("关")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Fan, 0, 0);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("风扇")
							&& spinner4.equals("开")) {
						if (MenuActivity.temp < et_num_data) {
							Js.control(Json_data.Fan, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("风扇")
							&& spinner4.equals("关")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Fan, 0, 0);
						}
					}

					if (spinner2.equals(">") && spinner3.equals("射灯")
							&& spinner4.equals("开")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Lamp, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("射灯")
							&& spinner4.equals("关")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Lamp, 0, 0);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("射灯")
							&& spinner4.equals("开")) {
						if (MenuActivity.temp < et_num_data) {
							Js.control(Json_data.Lamp, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("射灯")
							&& spinner4.equals("关")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Lamp, 0, 0);
						}
					}

					if (spinner2.equals(">") && spinner3.equals("窗帘")
							&& spinner4.equals("开")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Curtain, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("窗帘")
							&& spinner4.equals("关")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Curtain, 0, 2);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("窗帘")
							&& spinner4.equals("开")) {
						if (MenuActivity.temp < et_num_data) {
							Js.control(Json_data.Curtain, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("窗帘")
							&& spinner4.equals("关")) {
						if (MenuActivity.temp > et_num_data) {
							Js.control(Json_data.Curtain, 0, 2);
						}
					}
				}

				// 湿度、大于、开风扇
				if (spinner1.equals("湿度")) {
					if (spinner2.equals(">") && spinner3.equals("风扇")
							&& spinner4.equals("开")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Fan, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("风扇")
							&& spinner4.equals("关")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Fan, 0, 0);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("风扇")
							&& spinner4.equals("开")) {
						if (MenuActivity.hum < et_num_data) {
							Js.control(Json_data.Fan, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("风扇")
							&& spinner4.equals("关")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Fan, 0, 0);
						}
					}

					if (spinner2.equals(">") && spinner3.equals("射灯")
							&& spinner4.equals("开")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Lamp, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("射灯")
							&& spinner4.equals("关")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Lamp, 0, 0);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("射灯")
							&& spinner4.equals("开")) {
						if (MenuActivity.hum < et_num_data) {
							Js.control(Json_data.Lamp, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("射灯")
							&& spinner4.equals("关")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Lamp, 0, 0);
						}
					}

					if (spinner2.equals(">") && spinner3.equals("窗帘")
							&& spinner4.equals("开")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Curtain, 0, 1);
						}
					}
					if (spinner2.equals(">") && spinner3.equals("窗帘")
							&& spinner4.equals("关")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Curtain, 0, 2);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("窗帘")
							&& spinner4.equals("开")) {
						if (MenuActivity.hum < et_num_data) {
							Js.control(Json_data.Curtain, 0, 1);
						}
					}
					if (spinner2.equals("<") && spinner3.equals("窗帘")
							&& spinner4.equals("关")) {
						if (MenuActivity.hum > et_num_data) {
							Js.control(Json_data.Curtain, 0, 2);
						}
					}
				}
			}
			handler.postDelayed(timeRunnable, 10);
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