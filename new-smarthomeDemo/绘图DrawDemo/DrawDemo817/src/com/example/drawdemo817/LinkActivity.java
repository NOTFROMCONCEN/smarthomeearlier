package com.example.drawdemo817;

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
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

/*
 * @文件名：LinkActivity.java
 * @描述：对用户完成界面滑动、联动模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-17
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1;// Spinner
	private Spinner sp_2;
	private Spinner sp_3;
	private ArrayAdapter<String> mArrayAdapter;// 辅助类
	private String[] mStringArray;
	private CheckBox check_start_link;// 复选框开关
	private boolean link_state;
	private EditText et_number_get;// 文本框
	private int number_gte;// 获取设置的数值

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		// 联动
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		sp_3 = (Spinner) view.findViewById(R.id.spinner3);
		check_start_link = (CheckBox) view.findViewById(R.id.cehck_start_link);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		// 获取XML文件里面的内容-spinner1
		mStringArray = getResources().getStringArray(R.array.temp_ill);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_1.setAdapter(mArrayAdapter);
		// 获取XML文件里面的内容-spinner2
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_2.setAdapter(mArrayAdapter);
		// 获取XML文件里面的内容-spinner3
		mStringArray = getResources().getStringArray(R.array.fan_lamp_warm);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		sp_3.setAdapter(mArrayAdapter);
		// 复选框开关点击事件
		check_start_link
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							if (et_number_get.getText().toString().equals("")) {
								Toast.makeText(getActivity(), "数值不能为空",
										Toast.LENGTH_SHORT).show();
								check_start_link.setChecked(false);
								link_state = false;
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
	 * @功 能：检测联动模式进程
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				String spinner_1 = sp_1.getSelectedItem().toString();
				String spinner_2 = sp_2.getSelectedItem().toString();
				String spinner_3 = sp_3.getSelectedItem().toString();
				number_gte = Integer
						.valueOf(et_number_get.getText().toString());
				if (spinner_1.equals("温度")) {
					// 如果Spinner是温度
					if (spinner_2.equals(">")) {
						// 如果Spinner2是大于
						if (BaseActivity.temp > number_gte) {
							// 如果传感器参数大于设定数值
							if (spinner_3.equals("风扇")) {
								// 如果Spinner3是风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("射灯")) {
								// 如果Spinner3是射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("报警灯")) {
								// 如果Spinner3是报警灯
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// 如果条件不满足给出提示
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						// 如果Spinner2是小于
						if (BaseActivity.temp < number_gte) {
							// 如果传感器参数小于设定数值
							if (spinner_3.equals("风扇")) {
								// 如果Spinner3是风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("射灯")) {
								// 如果Spinner3是射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("报警灯")) {
								// 如果Spinner3是报警灯
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// 如果条件不满足给出提示
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
						}
					}
				}
				if (spinner_1.equals("光照")) {
					// 如果Spinner是温度
					if (spinner_2.equals(">")) {
						// 如果Spinner2是大于
						if (BaseActivity.ill > number_gte) {
							// 如果传感器参数大于设定数值
							if (spinner_3.equals("风扇")) {
								// 如果Spinner3是风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("射灯")) {
								// 如果Spinner3是射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("报警灯")) {
								// 如果Spinner3是报警灯
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// 如果条件不满足给出提示
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
						}
					}
					if (spinner_2.equals("<")) {
						// 如果Spinner2是小于
						if (BaseActivity.ill < number_gte) {
							// 如果传感器参数小于设定数值
							if (spinner_3.equals("风扇")) {
								// 如果Spinner3是风扇
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("射灯")) {
								// 如果Spinner3是射灯
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (spinner_3.equals("报警灯")) {
								// 如果Spinner3是报警灯
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							// 如果条件不满足给出提示
							Toast.makeText(getActivity(), "条件不满足",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_start_link.setChecked(false);
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
