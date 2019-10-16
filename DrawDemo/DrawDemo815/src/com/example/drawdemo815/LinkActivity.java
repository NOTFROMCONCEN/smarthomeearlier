package com.example.drawdemo815;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：对用户完成联动功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-14
 */
public class LinkActivity extends Fragment {
	/**
	 * 定义控件
	 */
	private CheckBox check_open;// 复选框
	private Spinner sp_1, sp_2;// spinner下拉菜单
	private EditText et_number;// 文本框
	private boolean link_state = false;
	private RadioButton ra_day_mode, ra_lijia_mode, ra_night_mode;// 单选按钮
	private RadioGroup rg_mode_check;// 单选按钮组

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		/**
		 * 绑定控件
		 */
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_lijia_mode = (RadioButton) view.findViewById(R.id.ra_lijia_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		check_open = (CheckBox) view.findViewById(R.id.check_open);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number = (EditText) view.findViewById(R.id.et_number);

		// 设置复选框勾选事件
		check_open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "数值不能为空",
								Toast.LENGTH_SHORT).show();
						link_state = false;
						check_open.setChecked(false);
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：监测link_state状态并控制设备
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// 联动模式功能
			if (link_state) {
				String spinner_1 = sp_1.getSelectedItem().toString();// 获取下拉菜单内容并赋值
				String spinner_2 = sp_2.getSelectedItem().toString();// 获取下拉菜单内容并赋值
				int get_number = Integer
						.valueOf(et_number.getText().toString());
				if (spinner_1.equals("温度")) {
					System.out.println("温度模式");
					if (spinner_2.equals(">")) {
						System.out.println(">");
						if (BaseActivity.temp > get_number) {
							// 当温度大于设定值，打开风扇
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("风扇开");
						} else {
							// 当条件不满足，给出提示并关闭风扇
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("条件不满足");
						}
					}
					if (spinner_2.equals("<")) {
						System.out.println("<");
						if (BaseActivity.temp < get_number) {
							// 当温度小于设定值，打开风扇
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("风扇开");
						} else {
							// 当条件不满足，给出提示并关闭风扇
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("条件不满足");
						}
					}
				}
				if (spinner_1.equals("湿度")) {
					System.out.println("湿度模式");
					if (spinner_2.equals(">")) {
						System.out.println(">");
						if (BaseActivity.hum > get_number) {
							// 当湿度大于设定值，打开风扇
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("风扇开");
						} else {
							// 当条件不满足，给出提示并关闭风扇
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("条件不满足");
						}
					}
					if (spinner_2.equals("<")) {
						System.out.println("<");
						if (BaseActivity.hum < get_number) {
							// 当湿度小于设定值，打开风扇
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("风扇开");
						} else {
							// 当条件不满足，给出提示并关闭风扇
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("条件不满足");
						}
					}
				}
			}
			// 模式选择功能
			if (ra_day_mode.isChecked()) {
				// 白天模式
				System.out.println("白天模式");
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			if (ra_lijia_mode.isChecked()) {
				// 离家模式
				System.out.println("离家模式");
				ControlUtils.control(ConstantUtil.RFID_Open_Door,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			if (ra_night_mode.isChecked()) {
				// 夜晚模式
				System.out.println("夜晚模式");
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
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
	//刷新界面
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}