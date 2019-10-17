package com.example.drawdemo928;

import java.util.ArrayList;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：联动模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-28
 */
public class LinkActivity extends Fragment {
	String[] mStringArrayList;
	ArrayAdapter<String> mArrayAdapter;
	private Spinner sp_1;// spinner1
	private Spinner sp_2;// spinner2
	private EditText et_number_get;// 文本框
	private CheckBox cb_day_mode;// 白天模式
	private CheckBox cb_night_mode;// 夜晚模式
	private CheckBox cb_diy_mode;// 自定义模式
	private CheckBox cb_fan;// 风扇
	private CheckBox cb_lamp;// 射灯
	private CheckBox cb_door;// 门禁
	private CheckBox cb_warm;// 报警灯
	private CheckBox cb_td_1;// 通道1
	private CheckBox cb_td_2;// 通道2
	private CheckBox cb_td_3;// 通道3
	private CheckBox cb_cur;// 窗帘
	private boolean link_state = false;
	private boolean day_state = false;
	private boolean night_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);// 绑定
		// 设置Spinner下拉框样式
		// spinner2
		mStringArrayList = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArrayList);
		sp_2.setAdapter(mArrayAdapter);
		// spinner1
		mStringArrayList = getResources().getStringArray(R.array.temp_hum);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArrayList);
		sp_1.setAdapter(mArrayAdapter);

		// 自定义模式
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_day_mode.setChecked(false);
					// cb_diy_mode.setChecked(false);
					cb_night_mode.setChecked(false);
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showTaost(getActivity(), "请输入数值");
						cb_diy_mode.setChecked(false);
						link_state = false;
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});
		// 白天模式
		cb_day_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// cb_day_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					cb_night_mode.setChecked(false);
					day_state = true;
				} else {
					day_state = false;
				}
			}
		});
		// 夜晚模式
		cb_night_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_day_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					// cb_night_mode.setChecked(false);
					night_state = true;
				} else {
					night_state = false;
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：联动
	 * 
	 * @时 间：下午7:08:41
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (day_state) {
				System.out.println("白天模式");
			}
			if (night_state) {
				System.out.println("夜晚模式");
			}
			if (link_state) {
				System.out.println("自定义模式");
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showTaost(getActivity(), "请输入数值");
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("温度")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.temp > number_get) {
								if (cb_cur.isChecked()) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_door.isChecked()) {
									ControlUtils.control(
											ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_fan.isChecked()) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_warm.isChecked()) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_td_1.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_td_2.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
								if (cb_td_3.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_3,
											ConstantUtil.OPEN);
								}
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "条件不满足");
								cb_diy_mode.setChecked(false);
								link_state = false;
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.temp < number_get) {
								if (cb_cur.isChecked()) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_door.isChecked()) {
									ControlUtils.control(
											ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_fan.isChecked()) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_warm.isChecked()) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_td_1.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_td_2.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
								if (cb_td_3.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_3,
											ConstantUtil.OPEN);
								}
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "条件不满足");
								cb_diy_mode.setChecked(false);
								link_state = false;
							}
						}
					}
					if (spinner1.equals("湿度")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.hum > number_get) {
								if (cb_cur.isChecked()) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_door.isChecked()) {
									ControlUtils.control(
											ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_fan.isChecked()) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_warm.isChecked()) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_td_1.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_td_2.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
								if (cb_td_3.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_3,
											ConstantUtil.OPEN);
								}
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "条件不满足");
								cb_diy_mode.setChecked(false);
								link_state = false;
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.hum < number_get) {
								if (cb_cur.isChecked()) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_door.isChecked()) {
									ControlUtils.control(
											ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_fan.isChecked()) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_warm.isChecked()) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_td_1.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_td_2.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
								if (cb_td_3.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_3,
											ConstantUtil.OPEN);
								}
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "条件不满足");
								cb_diy_mode.setChecked(false);
								link_state = false;
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

	/*
	 * @方法名：initView()
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：下午7:07:28
	 */
	private void initView(View view) {
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_day_mode = (CheckBox) view.findViewById(R.id.cb_day_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_night_mode = (CheckBox) view.findViewById(R.id.cb_night_mode);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_get = (EditText) view.findViewById(R.id.et_number);
	}
}
