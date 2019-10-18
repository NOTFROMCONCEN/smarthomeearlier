package com.example.shengsaisaixiangshitiDemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @文件名：LinkActivity.java
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class LinkActivity extends Fragment {
	private CheckBox cb_lamp;// 射灯
	private CheckBox cb_fan;// 风扇
	private CheckBox cb_warm;// 报警灯
	private CheckBox cb_door;// 门禁
	private CheckBox cb_td_1;// 通道1
	private CheckBox cb_td_2;// 通道2
	private CheckBox cb_td_3;// 通道3
	private CheckBox cb_cur;// 窗帘
	private CheckBox cb_anfang_mode;// 安防
	private CheckBox cb_lijia_mode;// 离家
	private CheckBox cb_diy_mode;// 自定义
	private Spinner sp_1;// spinner
	private EditText et_number_get;// 文本框
	private Switch sw_link_state;// 开关
	private boolean link_state = false, diy_mode_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		cb_anfang_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_lijia_mode = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		et_number_get = (EditText) view.findViewById(R.id.et_link_number_get);
		sp_1 = (Spinner) view.findViewById(R.id.spinner_link);
		sw_link_state = (Switch) view.findViewById(R.id.sw_link_state);
		// 设置只能勾选一个
		cb_anfang_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_anfang_mode.setChecked(false);
							cb_diy_mode.setChecked(false);
							cb_lijia_mode.setChecked(false);
						}
					}
				});
		// 设置只能勾选一个
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					diy_mode_state = true;
					cb_anfang_mode.setChecked(false);
					// cb_diy_mode.setChecked(false);
					cb_lijia_mode.setChecked(false);
				} else {
					diy_mode_state = false;
				}
			}
		});
		// 设置只能勾选一个
		cb_lijia_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					// cb_lijia_mode.setChecked(false);
				}
			}
		});
		// 设置开关
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						link_state = false;
						DiyToast.showToast(getActivity(), "请输入数值");
						sw_link_state.setChecked(false);
					} else {
						if (cb_diy_mode.isChecked()) {
							link_state = true;
						} else {
							DiyToast.showToast(getActivity(), "请勾选自定义模式");
							link_state = false;
							sw_link_state.setChecked(false);
						}
					}
				} else {
					link_state = false;
				}
			}
		});
		// 窗帘
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 射灯
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 风扇
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 报警灯
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 门禁
		cb_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 通道1
		cb_td_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 通道2
		cb_td_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 通道3
		cb_td_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state && link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请勾选自定义模式并开启开关");
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (cb_diy_mode.isChecked()) {
					// 自定义模式
					String spinner1 = sp_1.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals(">")) {
						if (BaseActivity.temp > number_get) {
							diy_mode_state = true;// 激活
						} else {
							diy_mode_state = false;// 关闭
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_cur.setChecked(false);
							cb_door.setChecked(false);
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							cb_td_1.setChecked(false);
							cb_td_2.setChecked(false);
							cb_td_3.setChecked(false);
							sw_link_state.setChecked(false);
						}
					}
					if (spinner1.equals("<")) {
						if (BaseActivity.temp < number_get) {
							diy_mode_state = true;// 激活
						} else {
							diy_mode_state = false;// 关闭
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_cur.setChecked(false);
							cb_door.setChecked(false);
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							cb_td_1.setChecked(false);
							cb_td_2.setChecked(false);
							cb_td_3.setChecked(false);
							sw_link_state.setChecked(false);
						}
					}
					if (spinner1.equals("=")) {
						if (BaseActivity.temp == number_get) {
							diy_mode_state = true;// 激活
						} else {
							diy_mode_state = false;// 关闭
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_cur.setChecked(false);
							cb_door.setChecked(false);
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							cb_td_1.setChecked(false);
							cb_td_2.setChecked(false);
							cb_td_3.setChecked(false);
							sw_link_state.setChecked(false);
						}
					}
				}
			}
			if (cb_anfang_mode.isChecked()) {
				// 离家模式当模式按钮打开时，开始监控人体红外，当显示有人时，打开报警灯；模式关闭时则不触发。
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_lijia_mode.isChecked()) {
				// 安防模式当模式按钮打开时，开始监控人体红外
				// 当显示有人时或者燃气值达到800时，打开报警灯；模式关闭时则不触发。
				if (BaseActivity.per == 1 || BaseActivity.gas > 800) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
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