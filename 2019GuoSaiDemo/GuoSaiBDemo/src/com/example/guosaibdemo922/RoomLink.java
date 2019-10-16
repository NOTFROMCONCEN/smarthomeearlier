package com.example.guosaibdemo922;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/*
 * @文件名：RoomLink.java
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class RoomLink extends Activity {
	private TextView tv_link_room_number;
	private Spinner sp_1;// spinner1
	private Spinner sp_2;// spinner2
	private Spinner sp_3;// spinner3
	private Spinner sp_4;// spinner4
	private Switch sw_link_state;// 开关
	private EditText et_number_get;// 文本框

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_linkstate);
		tv_link_room_number = (TextView) findViewById(R.id.tv_link_room_number);
		tv_link_room_number.setText("房间号："
				+ Index_Room_Activity.NUMBER_FOR_ROOM);
		sw_link_state = (Switch) findViewById(R.id.switch_link_state);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showTasot(getApplicationContext(), "请输入数值");
						sw_link_state.setChecked(false);
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联动功能实现
	 * 
	 * @时 间：上午9:33:18
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (sw_link_state.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()) {
					sw_link_state.setChecked(false);
					DiyToast.showTasot(getApplicationContext(), "请输入数值");
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					String spinner3 = sp_3.getSelectedItem().toString();
					String spinner4 = sp_4.getSelectedItem().toString();
					int numebr_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("光照")) {
						if (spinner2.equals(">")) {
							if (Index_Room_Activity.ill > numebr_get) {
								if (spinner3.equals("风扇")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("射灯")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("窗帘")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"条件不满足");
							}
						}
						if (spinner2.equals("<")) {
							if (Index_Room_Activity.ill < numebr_get) {
								if (spinner3.equals("风扇")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("射灯")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("窗帘")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"条件不满足");
							}
						}
					}

					if (spinner1.equals("温度")) {
						if (spinner2.equals(">")) {
							if (Index_Room_Activity.temp > numebr_get) {
								if (spinner3.equals("风扇")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("射灯")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("窗帘")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"条件不满足");
							}
						}
						if (spinner2.equals("<")) {
							if (Index_Room_Activity.temp < numebr_get) {
								if (spinner3.equals("风扇")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("射灯")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("窗帘")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"条件不满足");
							}
						}
					}

					if (spinner1.equals("湿度")) {
						if (spinner2.equals(">")) {
							if (Index_Room_Activity.hum > numebr_get) {
								if (spinner3.equals("风扇")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("射灯")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("窗帘")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"条件不满足");
							}
						}
						if (spinner2.equals("<")) {
							if (Index_Room_Activity.hum < numebr_get) {
								if (spinner3.equals("风扇")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("射灯")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("窗帘")) {
									if (spinner4.equals("开")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("关")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"条件不满足");
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
