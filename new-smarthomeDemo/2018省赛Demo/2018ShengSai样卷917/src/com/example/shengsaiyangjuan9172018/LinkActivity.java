package com.example.shengsaiyangjuan9172018;

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
import android.widget.RadioButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @文件名：LinkActivity.java
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-17
 */
public class LinkActivity extends Fragment {
	// RadioButton
	private RadioButton ra_temp;// 温度
	private RadioButton ra_smo;// 烟雾
	private RadioButton ra_ill;// 光照度
	private RadioButton ra_big;// 大
	private RadioButton ra_small;// 小
	private EditText et_number_get;// 文本框
	private RadioButton ra_huike_mode;// 会客模式
	private RadioButton ra_shuimian_mode;// 睡眠模式
	private RadioButton ra_fangdao_mode;// 防盗模式
	private CheckBox cb_fan;// 风扇
	private CheckBox cb_warm;// 报警灯
	private CheckBox cb_lamp;// 射灯
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		ra_big = (RadioButton) view.findViewById(R.id.ra_big);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_huike_mode = (RadioButton) view.findViewById(R.id.ra_hiuke_mode);
		ra_ill = (RadioButton) view.findViewById(R.id.ra_ill);
		ra_shuimian_mode = (RadioButton) view
				.findViewById(R.id.ra_shuimian_mode);
		ra_small = (RadioButton) view.findViewById(R.id.ra_small);
		ra_smo = (RadioButton) view.findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) view.findViewById(R.id.ra_temp);
		// 防盗模式关闭
		ra_fangdao_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
						} else {
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
				});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (cb_fan.isChecked() || cb_lamp.isChecked()
					|| cb_warm.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()) {
					et_number_get.setText("0");
					cb_fan.setChecked(false);
					cb_lamp.setChecked(false);
					cb_warm.setChecked(false);
					link_state = false;
				} else {
					link_state = true;
				}
			} else {
				link_state = false;
			}
			if (ra_huike_mode.isChecked()) {
				// 会客模式
				cb_fan.setChecked(false);
				cb_lamp.setChecked(false);
				cb_warm.setChecked(false);
				link_state = false;
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				if (BaseActivity.pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (ra_shuimian_mode.isChecked()) {
				// 睡眠模式
				cb_fan.setChecked(false);
				cb_lamp.setChecked(false);
				cb_warm.setChecked(false);
				link_state = false;
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				if (BaseActivity.co > 5) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else if (BaseActivity.co > 10) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (ra_fangdao_mode.isChecked()) {
				// 防盗模式
				cb_fan.setChecked(false);
				cb_lamp.setChecked(false);
				cb_warm.setChecked(false);
				link_state = false;
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
			}
			if (link_state) {
				// 联动模式
				ra_huike_mode.setChecked(false);
				ra_shuimian_mode.setChecked(false);
				ra_fangdao_mode.setChecked(false);
				int number_get = Integer.valueOf(et_number_get.getText()
						.toString());
				if (ra_temp.isChecked()) {
					if (ra_big.isChecked()) {
						if (BaseActivity.temp > number_get) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							link_state = false;
						}
					}
					if (ra_small.isChecked()) {
						if (BaseActivity.temp < number_get) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							link_state = false;
						}
					}
				}

				if (ra_smo.isChecked()) {
					if (ra_big.isChecked()) {
						if (BaseActivity.smo > number_get) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							link_state = false;
						}
					}
					if (ra_small.isChecked()) {
						if (BaseActivity.smo < number_get) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							link_state = false;
						}
					}
				}

				if (ra_ill.isChecked()) {
					if (ra_big.isChecked()) {
						if (BaseActivity.ill > number_get) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							link_state = false;
						}
					}
					if (ra_small.isChecked()) {
						if (BaseActivity.ill < number_get) {
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_fan.setChecked(false);
							cb_lamp.setChecked(false);
							cb_warm.setChecked(false);
							link_state = false;
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
