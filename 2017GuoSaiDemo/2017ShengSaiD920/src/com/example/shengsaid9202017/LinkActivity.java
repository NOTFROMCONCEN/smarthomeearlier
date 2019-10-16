package com.example.shengsaid9202017;

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
import android.widget.Spinner;

/*
 * @文件名：BaseActivity.java
 * @描述：完成联动
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2, sp_3, sp_4;
	private EditText et_number_get_1;
	private EditText et_number_get_2;
	private CheckBox cb_link_1;
	private CheckBox cb_link_2;
	private boolean link_1 = false;
	private boolean link_2 = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		sp_3 = (Spinner) view.findViewById(R.id.spinner3);
		sp_4 = (Spinner) view.findViewById(R.id.spinner4);
		et_number_get_1 = (EditText) view.findViewById(R.id.et_number_get_1);
		et_number_get_2 = (EditText) view.findViewById(R.id.et_number_get_2);
		cb_link_1 = (CheckBox) view.findViewById(R.id.cb_link_1);
		cb_link_2 = (CheckBox) view.findViewById(R.id.cb_link_2);
		cb_link_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get_1.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_link_1.setChecked(false);
						link_1 = false;
					} else {
						link_1 = true;
					}
				} else {
					link_1 = false;
				}
			}
		});
		cb_link_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get_2.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_link_2.setChecked(false);
						link_2 = false;
					} else {
						link_2 = true;
					}
				} else {
					link_2 = false;
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
			if (link_2) {
				String spinner3 = sp_3.getSelectedItem().toString();
				String spinner4 = sp_4.getSelectedItem().toString();
				int number_get = Integer.parseInt(et_number_get_2.getText()
						.toString());
				if (spinner3.equals(">")) {
					if (BaseActivity.ill > number_get) {
						if (spinner4.equals("窗帘开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (spinner4.equals("射灯全开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					} else {
						DiyToast.showToast(getActivity(), "条件不满足");
						cb_link_2.setChecked(false);
						link_2 = false;
					}
				}
				if (spinner3.equals("<")) {
					if (BaseActivity.ill < number_get) {
						if (spinner4.equals("窗帘开")) {
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						if (spinner4.equals("射灯全开")) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					} else {
						DiyToast.showToast(getActivity(), "条件不满足");
						cb_link_2.setChecked(false);
						link_2 = false;
					}
				}
			}
			if (link_1) {
				String spinner1 = sp_1.getSelectedItem().toString();
				String spinner2 = sp_2.getSelectedItem().toString();
				int number_get = Integer.parseInt(et_number_get_1.getText()
						.toString());
				if (spinner1.equals("温度")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.temp > number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_link_1.setChecked(false);
							link_1 = false;
						}
					}
					if (spinner2.equals("<")) {
						if (BaseActivity.temp < number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_link_1.setChecked(false);
							link_1 = false;
						}
					}
				}
				if (spinner1.equals("湿度")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.hum > number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_link_1.setChecked(false);
							link_1 = false;
						}
					}
					if (spinner2.equals("<")) {
						if (BaseActivity.hum < number_get) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							DiyToast.showToast(getActivity(), "条件不满足");
							cb_link_1.setChecked(false);
							link_1 = false;
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
