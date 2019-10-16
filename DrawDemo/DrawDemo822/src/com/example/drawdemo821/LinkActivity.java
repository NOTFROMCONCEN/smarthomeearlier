package com.example.drawdemo821;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * @文件名：LinkActivity.java
 * @描述：完成设备联动、模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-20
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1, spinner2, spinner3;
	private EditText et_number;
	private CheckBox check_mode_start;
	private RadioButton ra_temp_mode, ra_fangdao_mode, ra_lijia_mode;
	private RadioGroup rg_mode_check;
	private boolean link_state = false;// 联动模式
	private boolean temp_mode = false;// 温度模式
	private boolean fangdao_mode = false;// 防盗模式
	private boolean lijia_mode = false;// 离家模式
	private ArrayAdapter<String> mArrayAdapter;// 辅助类
	private String[] mStringArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		et_number = (EditText) view.findViewById(R.id.et_number);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_lijia_mode = (RadioButton) view.findViewById(R.id.ra_lijia_mode);
		ra_temp_mode = (RadioButton) view.findViewById(R.id.ra_temp_mode);
		check_mode_start = (CheckBox) view.findViewById(R.id.check_start);
		// 获取XML文件里的内容
		mStringArray = getResources().getStringArray(R.array.temp_hum);
		mArrayAdapter = new AdHelper(getActivity(), mStringArray);
		spinner1.setAdapter(mArrayAdapter);// spinner1
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdHelper(getActivity(), mStringArray);
		spinner2.setAdapter(mArrayAdapter);// spinner2
		mStringArray = getResources().getStringArray(R.array.fan_lamp);
		mArrayAdapter = new AdHelper(getActivity(), mStringArray);
		spinner3.setAdapter(mArrayAdapter);// spinner3
		// 设置“当”按钮点击事件
		check_mode_start
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							if (et_number.getText().toString().equals("")) {
								// 如果数值为空
								Toast.makeText(getActivity(), "数值不能为空",
										Toast.LENGTH_SHORT).show();
								check_mode_start.setChecked(false);
								link_state = false;
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
	 * @功 能：监听link_state方法是否被激活
	 * 
	 * @时 间：上午11:31:59
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (et_number.getText().toString().equals("")) {
					// 条件不满足取消link的激活，取消复选框的勾选
					link_state = false;
					Toast.makeText(getActivity(), "数值不能为空", Toast.LENGTH_SHORT)
							.show();
					check_mode_start.setChecked(false);
				} else {
					int get_number;
					String sp_1, sp_2, sp_3;
					sp_1 = spinner1.getSelectedItem().toString();
					sp_2 = spinner2.getSelectedItem().toString();
					sp_3 = spinner3.getSelectedItem().toString();
					get_number = Integer
							.valueOf(et_number.getText().toString());
					if (sp_1.equals("温度")) {
						if (sp_2.equals(">")) {
							if (BaseActivity.temp > get_number) {
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
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							if (BaseActivity.temp < get_number) {
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
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
							}
						}
					}
					if (sp_1.equals("湿度")) {
						if (sp_2.equals(">")) {
							if (BaseActivity.hum > get_number) {
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
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							if (BaseActivity.hum > get_number) {
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
								Toast.makeText(getActivity(), "条件不满足",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								check_mode_start.setChecked(false);
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
}