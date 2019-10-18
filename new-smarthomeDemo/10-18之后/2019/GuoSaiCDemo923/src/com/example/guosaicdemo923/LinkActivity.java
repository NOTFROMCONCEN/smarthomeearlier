package com.example.guosaicdemo923;

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
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-23
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2;
	private EditText et_number_getnumber;// 获取参数
	private EditText et_number_gettime;// 获取时间
	private Button btn_open;// 开启联动
	private Switch sw_warm;// 报警灯
	private Switch sw_fan;// 风扇
	private Switch sw_lamp;// 射灯
	private Switch sw_door;// 门禁
	private TextView tv_link_time;// 时间
	private TextView tv_link_retime;// 倒计时
	private CountDownTimer timers;
	private boolean link_state = false;
	private long num, min, sec;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_getnumber = (EditText) view.findViewById(R.id.et_number_get);
		et_number_gettime = (EditText) view.findViewById(R.id.et_time_get);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		btn_open = (Button) view.findViewById(R.id.btn_start);
		tv_link_retime = (TextView) view.findViewById(R.id.tv_link_time);
		tv_link_time = (TextView) view.findViewById(R.id.tv_link_get_time);
		// 激活进程
		handler.post(timeRunnable);
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

		// 倒计时开始按钮
		btn_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timers != null) {
					timers.cancel();
				}
				if (et_number_gettime.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入时间");
				} else if (et_number_getnumber.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
				} else {
					if (btn_open.getText().toString().equals("开启联动")) {
						String spinner1 = sp_1.getSelectedItem().toString();
						String spinner2 = sp_2.getSelectedItem().toString();
						int numebr_get = Integer.valueOf(et_number_getnumber
								.getText().toString());
						if (spinner1.equals("温度")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.temp > numebr_get) {
									link_state = true;
									btn_open.setText("关闭联动");
								} else {
									link_state = false;
									btn_open.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
							if (spinner2.equals("<")) {
								if (BaseActivity.temp < numebr_get) {
									link_state = true;
									btn_open.setText("关闭联动");
								} else {
									link_state = false;
									btn_open.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
						}
						if (spinner1.equals("湿度")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.hum > numebr_get) {
									link_state = true;
									btn_open.setText("关闭联动");
								} else {
									link_state = false;
									btn_open.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
							if (spinner2.equals("<")) {
								if (BaseActivity.hum < numebr_get) {
									link_state = true;
									btn_open.setText("关闭联动");
								} else {
									link_state = false;
									btn_open.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
						}
						if (spinner1.equals("光照")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.ill > numebr_get) {
									link_state = true;
									btn_open.setText("关闭联动");
								} else {
									link_state = false;
									btn_open.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
							if (spinner2.equals("<")) {
								if (BaseActivity.ill < numebr_get) {
									link_state = true;
									btn_open.setText("关闭联动");
								} else {
									link_state = false;
									btn_open.setText("开启联动");
									DiyToast.showToast(getActivity(), "条件不满足");
								}
							}
						}
					} else if (btn_open.getText().toString().equals("关闭联动")) {
						timers.cancel();
						tv_link_retime.setText("联动模式还有X分X秒");
						link_state = false;
						btn_open.setText("开启联动");
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
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
					if (link_state) {
						num = Integer.parseInt(et_number_gettime.getText()
								.toString()) * 60 * 1000;
						timers = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								min = millisUntilFinished / 1000 / 60;
								sec = millisUntilFinished / 1000 % 60;
								tv_link_retime.setText("联动模式还有" + min + "分"
										+ sec + "秒");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								link_state = false;
								tv_link_retime.setText("联动模式还有X分X秒");
								btn_open.setText("开启联动");
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
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：上午9:05:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 时间
			tv_link_time.setText(LoginActivity.get_time);
			handler.postDelayed(timeRunnable, 500);
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