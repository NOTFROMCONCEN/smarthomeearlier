package com.example.guosaicdemo831;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/*
 * @文件名：LinkActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2;// Spinner
	private EditText et_number_get;// 数值文本框
	private EditText et_time_get;// 时间文本框
	private Button btn_start;// 开启联动按钮
	private Switch sw_warm, sw_fan, sw_lamp, sw_door;// 开关
	private TextView tv_link_time;// 倒计时
	private ArrayAdapter<String> mArrayAdapter;
	private String[] mStringArray;
	private boolean link_state = false;
	private String spinner1, spinner2;
	private int number_get;
	private long num, min, sec;
	private CountDownTimer timer;
	private TextView tv_link_get_time;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		btn_start = (Button) view.findViewById(R.id.btn_start);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		et_time_get = (EditText) view.findViewById(R.id.et_time_get);
		tv_link_time = (TextView) view.findViewById(R.id.tv_link_time);
		tv_link_get_time = (TextView) view.findViewById(R.id.tv_link_get_time);
		// 设置Spinner下拉菜单XML文件
		mStringArray = getResources().getStringArray(R.array.big_small);// 大小
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_2.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.temp_hum_ill);// 温度、湿度
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// 设备控制
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
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
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
		// 设置开关
		btn_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				spinner1 = sp_1.getSelectedItem().toString();
				spinner2 = sp_2.getSelectedItem().toString();
				number_get = Integer
						.valueOf(et_number_get.getText().toString());
				if (btn_start.getText().toString().equals("开启联动")) {
					if (timer != null) {
						timer.cancel();
					}
					link_state = true;
					if (spinner1.equals("温度") && spinner2.equals(">")) {
						if (BaseActivity.temp > number_get) {
							System.out.println("open");
							link_state = true;
							btn_start.setText("停止联动");
						} else {
							DiyToast.showTaost(getActivity(), "条件不满足！");
							link_state = false;
							btn_start.setText("开启联动");
						}
					}
					if (spinner1.equals("温度") && spinner2.equals("<=")) {
						if (BaseActivity.temp < number_get) {
							link_state = true;
							btn_start.setText("停止联动");
						} else {
							DiyToast.showTaost(getActivity(), "条件不满足！");
							link_state = false;
							btn_start.setText("开启联动");
						}
					}
					if (spinner1.equals("湿度") && spinner2.equals(">")) {
						if (BaseActivity.hum > number_get) {
							link_state = true;
							btn_start.setText("停止联动");
						} else {
							DiyToast.showTaost(getActivity(), "条件不满足！");
							link_state = false;
							btn_start.setText("开启联动");
						}
					}
					if (spinner1.equals("湿度") && spinner2.equals("<=")) {
						if (BaseActivity.hum < number_get) {
							link_state = true;
							btn_start.setText("停止联动");
						} else {
							DiyToast.showTaost(getActivity(), "条件不满足！");
							link_state = false;
							btn_start.setText("开启联动");
						}
					}
					if (spinner1.equals("光照") && spinner2.equals(">")) {
						if (BaseActivity.ill > number_get) {
							link_state = true;
							btn_start.setText("停止联动");
						} else {
							DiyToast.showTaost(getActivity(), "条件不满足！");
							link_state = false;
							btn_start.setText("开启联动");
						}
					}
					if (spinner1.equals("光照") && spinner2.equals("<=")) {
						if (BaseActivity.ill < number_get) {
							link_state = true;
							btn_start.setText("停止联动");
						}
					}
					if (link_state) {
						// 总时间输出的值等于分钟
						num = Integer
								.parseInt(et_time_get.getText().toString()) * 60 * 1000;
						timer = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
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
								btn_start.setText("开启联动");
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
						}.start();// 开启倒计时
					}
				} else if (btn_start.getText().toString().equals("停止联动")) {
					link_state = false;
					btn_start.setText("开启联动");
					tv_link_time.setText("联动模式还有X分X秒");
					timer.cancel();
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
			tv_link_get_time.setText(BarActivity.get_time);
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