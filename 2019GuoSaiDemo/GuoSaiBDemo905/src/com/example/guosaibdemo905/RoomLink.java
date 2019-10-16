package com.example.guosaibdemo905;

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
 * @文件名：RoomControl.java
 * @描述：完成联动管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
 */
public class RoomLink extends Activity {
	private TextView tv_link_room_number;
	private Spinner sp_1, sp_2, sp_3, sp_4;
	private EditText et_number_get;
	private Switch sw_open;
	private boolean link_state = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_linkstate);
		tv_link_room_number = (TextView) findViewById(R.id.tv_link_room_number);
		tv_link_room_number.setText(IndexActivity.room_getnumber);
		sw_open = (Switch) findViewById(R.id.switch1);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.Spinner01);
		sp_3 = (Spinner) findViewById(R.id.Spinner02);
		sp_4 = (Spinner) findViewById(R.id.Spinner03);
		sw_open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().equals("")) {
						link_state = false;
						sw_open.setChecked(false);
						DiyToast.showToast(getApplicationContext(), "数值不能为空");
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

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联动
	 * 
	 * @时 间：上午10:16:09
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (!et_number_get.getText().toString().equals("")) {
					String spinner_1;
					String spinner_2;
					String spinner_3;
					String spinner_4;
					int number = Integer.valueOf(et_number_get.getText()
							.toString());
					spinner_1 = sp_1.getSelectedItem().toString();
					spinner_2 = sp_2.getSelectedItem().toString();
					spinner_3 = sp_3.getSelectedItem().toString();
					spinner_4 = sp_4.getSelectedItem().toString();
					if (spinner_1.equals("光照")) {
						if (spinner_2.equals(">")) {
							if (spinner_3.equals("风扇")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("射灯")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("窗帘")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (spinner_2.equals("<")) {
							if (spinner_3.equals("风扇")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("射灯")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("窗帘")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.CLOSE);
								}
							}
						}
					}
					if (spinner_1.equals("温度")) {
						if (spinner_2.equals(">")) {
							if (spinner_3.equals("风扇")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("射灯")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("窗帘")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (spinner_2.equals("<")) {
							if (spinner_3.equals("风扇")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("射灯")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("窗帘")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.CLOSE);
								}
							}
						}
					}
					if (spinner_1.equals("湿度")) {
						if (spinner_2.equals(">")) {
							if (spinner_3.equals("风扇")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("射灯")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("窗帘")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (spinner_2.equals("<")) {
							if (spinner_3.equals("风扇")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("射灯")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner_3.equals("窗帘")) {
								if (spinner_4.equals("开")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (spinner_4.equals("关")) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.CLOSE);
								}
							}
						}
					}
				} else {
					link_state = false;
					sw_open.setChecked(false);
					DiyToast.showToast(RoomLink.this, "请输入数值");
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
