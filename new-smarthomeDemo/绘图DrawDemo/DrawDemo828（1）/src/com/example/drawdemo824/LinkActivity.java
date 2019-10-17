package com.example.drawdemo824;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;

/*
 * @文件名：LinkActivity.java
 * @描述：完成设备联动、模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-23
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1;// Spinner下拉菜单
	private Spinner spinner2;// Spinner下拉菜单
	private Spinner spinner3;// Spinner下拉菜单
	private EditText et_get_number;// 文本框
	private RadioButton ra_temp_mode, ra_fangdao_mode, ra_night_mode;// 温度模式、防盗模式、夜晚模式
	private RadioGroup rg_mode_check;// 单选按钮组
	private CheckBox cb_start_mode;// 复选框
	private boolean link_state = false;
	private ArrayAdapter<String> mArrayAdapter;
	private String[] mStringArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		et_get_number = (EditText) view.findViewById(R.id.et_get_number);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		ra_temp_mode = (RadioButton) view.findViewById(R.id.ra_temp_mode);
		rg_mode_check = (RadioGroup) view.findViewById(R.id.rg_mode_check);
		cb_start_mode = (CheckBox) view.findViewById(R.id.cb_start_mode);
		// 设置Spinner下拉菜单XML文件
		mStringArray = getResources().getStringArray(R.array.big_small);// 大小
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner2.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.temp_hum);// 温度、湿度
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner1.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.lamp_fan);// 射灯、风扇
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner3.setAdapter(mArrayAdapter);
		// 设置复选框点击事件
		cb_start_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_get_number.getText().toString().equals("")) {
						DiyToast.showToast(getActivity(), "数值不能为空");
						link_state = false;
						cb_start_mode.setChecked(false);
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
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
				if (!et_get_number.getText().toString().equals("")) {
					String sp_1, sp_2, sp_3;
					int number_get;
					sp_1 = spinner1.getSelectedItem().toString();// 设置sp1
					sp_2 = spinner2.getSelectedItem().toString();// 设置sp2
					sp_3 = spinner3.getSelectedItem().toString();// 设置sp3
					number_get = Integer.valueOf(et_get_number.getText()
							.toString());
					if (sp_1.equals("温度")) {
						// 如果spinner1为温度
						if (sp_2.equals(">")) {
							// 如果spinner2为大于
							if (BaseActivity.temp > number_get) {
								if (sp_3.equals("射灯")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("风扇")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足！");
								link_state = false;
								cb_start_mode.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							// 如果spinner2为小于
							if (BaseActivity.temp < number_get) {
								if (sp_3.equals("射灯")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("风扇")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足！");
								link_state = false;
								cb_start_mode.setChecked(false);
							}

						}
					}
					if (sp_1.equals("湿度")) {
						// 如果spinner1为湿度
						if (sp_2.equals(">")) {
							// 如果spinner2为大于
							if (BaseActivity.hum > number_get) {
								if (sp_3.equals("射灯")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("风扇")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足！");
								link_state = false;
								cb_start_mode.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							// 如果spinner2为小于
							if (BaseActivity.hum < number_get) {
								if (sp_3.equals("射灯")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("风扇")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "条件不满足！");
								link_state = false;
								cb_start_mode.setChecked(false);
							}

						}
					}
				} else {
					DiyToast.showToast(getActivity(), "数值不能为空");
					link_state = false;
					cb_start_mode.setChecked(false);
				}
			}
			if (ra_fangdao_mode.isChecked()) {
				// 防盗模式被选中
				if (!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())
						&& DeviceBean.getStateHumanInfrared().equals(
								ConstantUtil.CLOSE)) {// 人体红外无人时关闭
					if (!TextUtils.isEmpty(DeviceBean.getWarningLight())
							&& !DeviceBean.getWarningLight().equals(
									ConstantUtil.CLOSE))
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				} else if (!TextUtils.isEmpty(DeviceBean
						.getStateHumanInfrared())
						&& !DeviceBean.getStateHumanInfrared().equals(
								ConstantUtil.CLOSE)) {// 有人时打开报警灯
					if (!TextUtils.isEmpty(DeviceBean.getWarningLight())
							&& DeviceBean.getWarningLight().equals(
									ConstantUtil.CLOSE))
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (ra_night_mode.isChecked()) {
				// 夜晚模式被选中
			}
			if (ra_temp_mode.isChecked()) {
				// 温度模式被选中
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