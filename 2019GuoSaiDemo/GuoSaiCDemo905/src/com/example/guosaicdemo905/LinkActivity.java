package com.example.guosaicdemo905;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @文件名：LinkActivity.java
 * @描述：设备联动、倒计时功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-4
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2;
	private EditText et_number, et_time;
	private Button btn_link_start;
	private Switch sw_warm, sw_fan, sw_lamp, sw_door;
	private TextView tv_link_time, tv_link_get_time;
	private long num, min, sec;
	private CountDownTimer timer;
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		btn_link_start = (Button) view.findViewById(R.id.btn_start);
		tv_link_get_time = (TextView) view.findViewById(R.id.tv_link_get_time);
		et_number = (EditText) view.findViewById(R.id.et_number_get);
		et_time = (EditText) view.findViewById(R.id.et_time_get);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		tv_link_time = (TextView) view.findViewById(R.id.tv_link_time);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		// 门禁
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {

					}
				}
			}
		});
		// 报警灯
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// 风扇
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// 射灯
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "请开启联动");
					sw_lamp.setChecked(false);
				}
			}
		});

		btn_link_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
				}
				if (et_number.getText().toString().equals("")) {
					DiyToast.showToast(getActivity(), "数值不能为空");
				} else if (et_time.getText().toString().equals("")) {
					DiyToast.showToast(getActivity(), "数值不能为空");
				} else {
					if (btn_link_start.getText().toString().equals("开启联动")) {
						String spinner1, spinner2;
						spinner1 = sp_1.getSelectedItem().toString();
						spinner2 = sp_2.getSelectedItem().toString();
						int get_number;
						get_number = Integer.valueOf(et_number.getText()
								.toString());
						if (spinner1.equals("温度")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.temp > get_number) {
									link_state = true;// 激活
									btn_link_start.setText("关闭联动");
								} else {
									link_state = true;// 取消激活
									btn_link_start.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
							if (spinner2.equals("<=")) {

							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.temp < get_number) {
									link_state = true;// 激活
									btn_link_start.setText("关闭联动");
								} else {
									link_state = true;// 取消激活
									btn_link_start.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
						}
						if (spinner1.equals("湿度")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.hum > get_number) {
									link_state = true;// 激活
									btn_link_start.setText("关闭联动");
								} else {
									link_state = true;// 取消激活
									btn_link_start.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.hum < get_number) {
									link_state = true;// 激活
									btn_link_start.setText("关闭联动");
								} else {
									link_state = true;// 取消激活
									btn_link_start.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
						}
						if (spinner1.equals("光照")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.ill > get_number) {
									link_state = true;// 激活
									btn_link_start.setText("关闭联动");
								} else {
									link_state = true;// 取消激活
									btn_link_start.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.ill < get_number) {
									link_state = true;// 激活
									btn_link_start.setText("关闭联动");
								} else {
									link_state = true;// 取消激活
									btn_link_start.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
						}
					} else if (btn_link_start.getText().toString()
							.equals("关闭联动")) {
						timer.cancel();
						tv_link_time.setText("联动模式还有X分X秒");
						link_state = false;
						btn_link_start.setText("开启联动");
					}
					// 倒计时
					if (link_state) {
						num = Integer.parseInt(et_time.getText().toString()) * 60 * 1000;
						timer = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								// TODO Auto-generated method stub
								// 分
								min = millisUntilFinished / 1000 / 60;
								// 秒
								sec = millisUntilFinished / 1000 % 60;
								// 设置文本
								tv_link_time.setText("联动模式还有" + min + "分" + sec
										+ "秒");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								link_state = false;
								btn_link_start.setText("开启联动");
								tv_link_time.setText("联动模式还有X分X秒");
								// 计时完毕后关闭所有被打开的设备和开关
								if (sw_door.isChecked()) {
									sw_door.setChecked(false);
								}
								if (sw_fan.isChecked()) {
									sw_fan.setChecked(false);
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sw_lamp.isChecked()) {
									sw_lamp.setChecked(false);
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sw_warm.isChecked()) {
									sw_warm.setChecked(false);
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}.start();
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	// 设定时间进程
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_link_get_time.setText(BarActivity.bar_time);
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
