package com.example.drawdemo830;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.R.raw;
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
import android.widget.RadioButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：完成设备联动功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-30
 */
public class LinkActivity extends Fragment {
	private ArrayAdapter<String> mArrayAdapter;
	private String[] mStringArray;
	private Spinner spinner1;
	private Spinner spinner2;
	private Spinner spinner3;
	private EditText et_get_number;
	private CheckBox cb_link_start;
	private boolean link_state = false;
	private RadioButton ra_temp_mode, ra_night_mode, ra_fangdao_mode;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		et_get_number = (EditText) view.findViewById(R.id.et_number_get);
		cb_link_start = (CheckBox) view.findViewById(R.id.cb_link_start);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		ra_temp_mode = (RadioButton) view.findViewById(R.id.ra_temp_mode);
		// 设置Spinner下拉菜单XML文件
		mStringArray = getResources().getStringArray(R.array.big_small);// 大小
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner2.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.temp_hum);// 温度、湿度
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner1.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.lamp_warm);// 射灯、报警灯
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner3.setAdapter(mArrayAdapter);
		// 激活进程
		handler.post(timeRunnable);
		// 复选框
		cb_link_start.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_get_number.getText().toString().equals("")) {
						link_state = false;
						cb_link_start.setChecked(false);
						DiyToast.showToast(getActivity(), "请输入数值");
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 模式功能
			if (ra_fangdao_mode.isChecked()) {
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (ra_night_mode.isChecked()) {
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			if (ra_temp_mode.isChecked()) {
				if (BaseActivity.temp > 20) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			// 联动模式
			if (link_state) {
				String sp_1, sp_2, sp_3;
				int number_get;
				sp_1 = spinner1.getSelectedItem().toString();
				sp_2 = spinner2.getSelectedItem().toString();
				sp_3 = spinner3.getSelectedItem().toString();
				number_get = Integer
						.valueOf(et_get_number.getText().toString());
				if (sp_1.equals("温度")) {
					if (sp_2.equals(">")) {
						if (BaseActivity.temp > number_get) {
							if (sp_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (sp_3.equals("报警灯")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							link_state = false;
							cb_link_start.setChecked(false);
						}
					}
					if (sp_2.equals("<")) {
						if (BaseActivity.temp < number_get) {
							if (sp_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (sp_3.equals("报警灯")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							link_state = false;
							cb_link_start.setChecked(false);
						}
					}
				}
				if (sp_1.equals("湿度")) {
					if (sp_2.equals(">")) {
						if (BaseActivity.hum > number_get) {
							if (sp_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (sp_3.equals("报警灯")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							link_state = false;
							cb_link_start.setChecked(false);
						}
					}
					if (sp_2.equals("<")) {
						if (BaseActivity.hum < number_get) {
							if (sp_3.equals("射灯")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (sp_3.equals("报警灯")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							link_state = false;
							cb_link_start.setChecked(false);
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