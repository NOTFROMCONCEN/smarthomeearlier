package com.example.shengsaicdemo10072018.fragment;

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
import com.example.shengsaicdemo10072018.R;
import com.example.shengsaicdemo10072018.toast.DiyToast;

/*
 * @文件名：LinkFragment.java
 * @描述：联动
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-7
 */
public class LinkFragment extends Fragment {
	private Switch sw_link_state;
	private EditText et_number_get;
	private Spinner sp_1;
	private CheckBox cb_mode_anfang;// 安防模式
	private CheckBox cb_mode_lijia;// 离家模式
	private CheckBox cb_mode_diy;// 自定义模式
	private CheckBox cb_lamp;// 射灯
	private CheckBox cb_fan;// 风扇
	private CheckBox cb_warm;// 报警灯
	private CheckBox cb_door;// 门禁
	private CheckBox cb_td_1;// 通道1
	private CheckBox cb_td_2;// 通道2
	private CheckBox cb_td_3;// 通道3
	private CheckBox cb_cur;// 窗帘

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);
		// 只能选择一个效果
		// 安防模式
		cb_mode_anfang
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_mode_anfang.setChecked(false);
							cb_mode_diy.setChecked(false);
							cb_mode_lijia.setChecked(false);
						}
					}
				});
		// 离家模式
		cb_mode_lijia.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_mode_anfang.setChecked(false);
					cb_mode_diy.setChecked(false);
					// cb_mode_lijia.setChecked(false);
				}
			}
		});
		// 自定义模式
		cb_mode_diy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_mode_anfang.setChecked(false);
					// cb_mode_diy.setChecked(false);
					cb_mode_lijia.setChecked(false);
				}
			}
		});
		// 联动模式开关
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						sw_link_state.setChecked(false);
					} else {
						if (cb_mode_diy.isChecked()) {

						} else {
							DiyToast.showToast(getActivity(), "请勾选自定义模式");
							sw_link_state.setChecked(false);
						}
					}
				} else {
					if (cb_cur.isChecked()) {
						// 窗帘
						cb_cur.setChecked(false);
					}
					if (cb_door.isChecked()) {
						// 门禁
						cb_door.setChecked(false);
					}
					if (cb_fan.isChecked()) {
						// 风扇
						cb_fan.setChecked(false);
					}
					if (cb_lamp.isChecked()) {
						// 射灯
						cb_lamp.setChecked(false);
					}
					if (cb_warm.isChecked()) {
						// 报警灯
						cb_warm.setChecked(false);
					}
					if (cb_td_1.isChecked()) {
						cb_td_1.setChecked(false);
					}
					if (cb_td_2.isChecked()) {
						cb_td_2.setChecked(false);
					}
					if (cb_td_3.isChecked()) {
						cb_td_3.setChecked(false);
					}
				}
			}
		});
		// 激活
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// 模式控制
			if (cb_mode_anfang.isChecked()) {
				// 安防模式
				// 当模式按钮打开时，开始监控人体红外，当显示有人时，打开报警灯；模式
				// 关闭时则不触发。
				if (BaseFragment.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_mode_lijia.isChecked()) {
				// 当模式按钮打开时，开始监控人体红外，当显示有人时或者燃气值达到 800
				// 时，打开报警灯；模式关闭时则不触发门禁控制功能通过点击界面中门禁开关控
				// 制门禁的开启人体感应状态的采集，并将状态实时显示（有人或无人）。
				if (BaseFragment.per == 1 || BaseFragment.gas > 800) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			// 联动
			if (sw_link_state.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()
						|| cb_mode_diy.isChecked()) {
					if (sp_1.getSelectedItem().toString().equals(">")) {
						if (BaseFragment.temp > Integer.valueOf(et_number_get
								.getText().toString())) {
							if (cb_cur.isChecked()) {
								// 窗帘
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								// 门禁
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								// 风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								// 射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								// 报警灯
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_td_1.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_td_2.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_td_3.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							sw_link_state.setChecked(false);
						}
					}
					if (sp_1.getSelectedItem().toString().equals("<")) {
						if (BaseFragment.temp < Integer.valueOf(et_number_get
								.getText().toString())) {
							if (cb_cur.isChecked()) {
								// 窗帘
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								// 门禁
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								// 风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								// 射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								// 报警灯
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_td_1.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_td_2.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_td_3.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							sw_link_state.setChecked(false);
						}
					}
					if (sp_1.getSelectedItem().toString().equals("=")) {
						if (BaseFragment.temp == Integer.valueOf(et_number_get
								.getText().toString())) {
							if (cb_cur.isChecked()) {
								// 窗帘
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								// 门禁
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								// 风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								// 射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								// 报警灯
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_td_1.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_td_2.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_td_3.isChecked()) {
								// 报警灯
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							sw_link_state.setChecked(false);
						}
					}
				} else {
					DiyToast.showToast(getActivity(), "自定义模式未勾选或者未输入数值");
					sw_link_state.setChecked(false);
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

	private void initView(View view) {
		// TODO Auto-generated method stub
		sw_link_state = (Switch) view.findViewById(R.id.sw_link_state);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_mode_anfang = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_mode_diy = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_mode_lijia = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
	}
}
