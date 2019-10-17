package com.example.drawdemo820shanxingtu;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.R.bool;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

/*
 * @文件名：LinkActivity.java
 * @描述：完成设备联动、模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1, spinner2, spinner3;
	private EditText et_number_get;
	private CheckBox cb_link_start;
	private RadioButton ra_fangdao_mode;// 防盗模式
	private RadioButton ra_temp_mode;// 温度模式
	private RadioButton ra_night_mode;// 夜晚模式
	private RadioGroup rg_mode_check;
	private boolean link_state = false;
	private String[] mStringArray;
	private int number_get;
	private ArrayAdapter<String> mArrayAdaHelper;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		cb_link_start = (CheckBox) view.findViewById(R.id.cb_link_start);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		ra_temp_mode = (RadioButton) view.findViewById(R.id.ra_temp_mode);
		rg_mode_check = (RadioGroup) view.findViewById(R.id.rg_mode_check);
		// 设置Spinner下拉菜单样式
		mStringArray = getResources().getStringArray(R.array.temp_ill);
		mArrayAdaHelper = new AdaHelper(getActivity(), mStringArray);
		spinner1.setAdapter(mArrayAdaHelper);
		mStringArray = getResources().getStringArray(R.array.big_small);
		mArrayAdaHelper = new AdaHelper(getActivity(), mStringArray);
		spinner2.setAdapter(mArrayAdaHelper);
		mStringArray = getResources().getStringArray(R.array.lamp_fan);
		mArrayAdaHelper = new AdaHelper(getActivity(), mStringArray);
		spinner3.setAdapter(mArrayAdaHelper);
		// 设置开关点击事件
		cb_link_start
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							if (et_number_get.getText().toString().equals("")) {
								Toast.makeText(getActivity(), "数值不能为空",
										Toast.LENGTH_SHORT).show();
								link_state = false;
								cb_link_start.setChecked(false);
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

	/*
	 * @方法名：handler
	 * 
	 * @功 能：监听所有控件完成联动、模式功能
	 * 
	 * @时 间：下午3:25:00
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				String sp_1, sp_2, sp_3;
				sp_1 = spinner1.getSelectedItem().toString();
				sp_2 = spinner2.getSelectedItem().toString();
				sp_3 = spinner3.getSelectedItem().toString();
				number_get = Integer
						.valueOf(et_number_get.getText().toString());
				if (sp_1.equals("温度")) {
					if (sp_2.equals(">")) {
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
							Toast.makeText(getActivity(), "条件不满足！",
									Toast.LENGTH_SHORT).show();
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
							if (sp_3.equals("风扇")) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							Toast.makeText(getActivity(), "条件不满足！",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							cb_link_start.setChecked(false);
						}
					}
				}
				if (sp_1.equals("光照")) {
					if (sp_2.equals(">")) {
						if (BaseActivity.ill > number_get) {
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
							Toast.makeText(getActivity(), "条件不满足！",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							cb_link_start.setChecked(false);
						}
					}
					if (sp_2.equals("<")) {
						if (BaseActivity.ill < number_get) {
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
							Toast.makeText(getActivity(), "条件不满足！",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							cb_link_start.setChecked(false);
						}
					}
				}
			}
			if (ra_fangdao_mode.isChecked()) {
				// 防盗模式
				if (BaseActivity.per == 1) {
					// 有人时打开报警灯
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (ra_night_mode.isChecked()) {
				// 夜晚模式
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 关闭射灯
				if (BaseActivity.co > 75) {
					// Co2大于75打开风扇
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (ra_temp_mode.isChecked()) {
				if (BaseActivity.temp > 30) {
					// 温度大于30打开风扇
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
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
