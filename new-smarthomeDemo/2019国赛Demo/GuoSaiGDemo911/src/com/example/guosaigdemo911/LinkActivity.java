package com.example.guosaigdemo911;

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

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @文件名：LinkActivity.java
 * @描述：完成联动功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-11
 */
public class LinkActivity extends Fragment {
	// 控制设备
	private CheckBox cb_lamp;// 射灯
	private CheckBox cb_fan;// 风扇
	private CheckBox cb_warm;// 报警灯
	private CheckBox cb_door;// 门禁
	private CheckBox cb_td_1;// 通道1
	private CheckBox cb_td_2;// 通道2
	private CheckBox cb_td_3;// 通道3
	private CheckBox cb_cur;// 窗帘

	// 模式功能
	private CheckBox cb_anfang_mode;// 安防模式
	private CheckBox cb_lijai_mode;// 离家模式
	private CheckBox cb_diy_mode;// 自定义模式

	// 联动功能
	private Spinner sp_link;
	private EditText et_number_get;
	private boolean diy_mode_state = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		cb_anfang_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_lijai_mode = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		sp_link = (Spinner) view.findViewById(R.id.spinner_link);
		et_number_get = (EditText) view.findViewById(R.id.et_link_number_get);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		// 激活进程
		handler.post(timeRunnable);
		// 自定义模式控制
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					DiyToast.showToast(getActivity(), "自定义模式激活");
				} else {
					cb_cur.setChecked(false);
					cb_door.setChecked(false);
					cb_fan.setChecked(false);
					cb_lamp.setChecked(false);
					cb_warm.setChecked(false);
					cb_td_1.setChecked(false);
					cb_td_2.setChecked(false);
					cb_td_3.setChecked(false);
					diy_mode_state = false;
				}
			}
		});
		// 设定设备控制
		// 窗帘
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_cur.setChecked(false);
				}
			}
		});
		// 门禁
		cb_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_door.setChecked(false);
				}
			}
		});
		// 风扇
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_fan.setChecked(false);
				}
			}
		});
		// 射灯
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_lamp.setChecked(false);
				}
			}
		});
		// 报警灯
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_warm.setChecked(false);
				}
			}
		});
		// 通道1
		cb_td_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_td_1.setChecked(false);
				}
			}
		});
		// 通道2
		cb_td_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_td_2.setChecked(false);
				}
			}
		});
		// 通道3
		cb_td_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "请激活自定义模式");
					cb_td_3.setChecked(false);
				}
			}
		});
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_diy_mode.isChecked()) {
				// 自定义模式
				String spinner1 = sp_link.getSelectedItem().toString();
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
			if (cb_lijai_mode.isChecked()) {
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
