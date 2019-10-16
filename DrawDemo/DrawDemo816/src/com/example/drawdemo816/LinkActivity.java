package com.example.drawdemo816;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

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
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * @文件名：LinkActivity.java
 * @描述：对用户完成界面的滑动、设备联动、模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-16
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1, spinner2;// 用于显示菜单的两个下拉菜单
	private CheckBox check_open;// 用于激活联动模式的复选框
	private EditText et_number;// 文本框
	private RadioButton ra_day;// 白天模式
	private RadioButton ra_night;// 夜晚模式
	private RadioButton ra_fangdao;// 防盗模式
	private ToggleButton tg_mode_start;// 模式功能的开关
	private boolean link_state = false;// 用于比较联动是否激活的布尔数值
	private boolean mode_state = false;// 用于比较模式是否激活的布尔数值
	private int number;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		check_open = (CheckBox) view.findViewById(R.id.check_open);
		et_number = (EditText) view.findViewById(R.id.et_number);
		ra_day = (RadioButton) view.findViewById(R.id.ra_day);
		ra_fangdao = (RadioButton) view.findViewById(R.id.ra_fangdao);
		ra_night = (RadioButton) view.findViewById(R.id.ra_night);
		tg_mode_start = (ToggleButton) view.findViewById(R.id.tg_mode_start);
		// 设置开关按钮点击事件
		tg_mode_start.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						Toast.makeText(getActivity(), "请先关闭联动功能",
								Toast.LENGTH_SHORT).show();
						mode_state = false;
						tg_mode_start.setChecked(false);
					} else {
						mode_state = true;
					}
				} else {
					mode_state = false;
				}
			}
		});
		// 复选框选中事件
		check_open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// 检查文本款格式是否为空，并给出提示
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
		// 激活线程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：检测控件状态改变的进程，用于联动和模式
	 * 
	 * @时 间：上午11:00:02
	 */
	// 联动模式优先级大于模式功能
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				// 检测文本框是否为空
				if (et_number.getText().toString().equals("")) {
					check_open.setChecked(false);
					link_state = false;
				} else {
					// 联动模式：检测布尔值是否为true
					String sp_1 = spinner1.getSelectedItem().toString();// 获取下拉菜单的选项
					String sp_2 = spinner2.getSelectedItem().toString();// 获取下拉菜单的选项
					number = Integer.valueOf(et_number.getText().toString());// 获取文本款输入的数值
					if (sp_1.equals("温度")) {
						if (sp_2.equals(">")) {
							// 温度，大于，之后比对设定数值和传感器数值带下
							if (BaseActivity.temp > number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// 如果条件不满足，给出提示并关闭风扇
								Toast.makeText(getActivity(), "条件不满足！",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.equals("<")) {
							// 温度，小于，之后比对设定数值和传感器数值带下
							if (BaseActivity.temp < number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// 如果条件不满足，给出提示并关闭风扇
								Toast.makeText(getActivity(), "条件不满足！",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
					}
					if (sp_1.equals("湿度")) {
						if (sp_2.equals(">")) {
							// 湿度，大于，之后比对设定数值和传感器数值带下
							if (BaseActivity.hum > number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// 如果条件不满足，给出提示并关闭风扇
								Toast.makeText(getActivity(), "条件不满足！",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.equals("<")) {
							// 湿度，小于，之后比对设定数值和传感器数值带下
							if (BaseActivity.hum < number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// 如果条件不满足，给出提示并关闭风扇
								Toast.makeText(getActivity(), "条件不满足！",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
					}
				}
			}
			// 模式功能
			if (mode_state) {
				if (link_state) {
					// 检测联动模式是否被激活，。如果被激活，关闭模式功能
					Toast.makeText(getActivity(), "请先关闭联动功能",
							Toast.LENGTH_SHORT).show();
					mode_state = false;
					tg_mode_start.setChecked(false);
				} else {
					if (ra_day.isChecked()) {// 如果白天模式被选中
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);// 窗帘打开
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 射灯关闭
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 打开风扇
					}
					if (ra_fangdao.isChecked()) {// 如果防盗模式被激活
						if (BaseActivity.per == 1) {
							// 如果传送过来的per数值为1，则为有人，激活报警灯
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
					if (ra_night.isChecked()) {// 如果夜晚模式被激活
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);// 窗帘打开
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 射灯关闭
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 打开风扇
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
