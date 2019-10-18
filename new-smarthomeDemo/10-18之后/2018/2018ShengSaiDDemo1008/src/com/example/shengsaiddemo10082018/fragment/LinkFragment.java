package com.example.shengsaiddemo10082018.fragment;

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

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaiddemo10082018.R;
import com.example.shengsaiddemo10082018.toast.DiyToast;

/*
 * @文件名：BaseFragment.java
 * @描述：联动控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-8
 */
public class LinkFragment extends Fragment {
	private Spinner sp_1;
	private Spinner sp_2;
	private Spinner sp_3;
	private Spinner sp_4;
	private EditText et_get_number_1;
	private EditText et_get_number_2;
	private CheckBox cb_1;
	private CheckBox cb_2;
	private boolean link_1 = false, link_2 = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_link, container, false);
		initView(view);
		// CheckButton1
		cb_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_get_number_1.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_1.setChecked(false);
						link_1 = false;
					} else {
						link_1 = true;
					}
				} else {
					link_1 = false;
				}
			}
		});
		// CheckButton2
		cb_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_get_number_2.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "请输入数值");
						cb_2.setChecked(false);
						link_2 = false;
					} else {
						link_2 = true;
					}
				} else {
					link_2 = false;
				}
			}
		});
		// 激活
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_2) {
				if (et_get_number_2.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					link_2 = false;
					cb_2.setChecked(false);
				} else {
					String spinner_3 = sp_3.getSelectedItem().toString();
					String spinner_4 = sp_4.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_get_number_2.getText()
							.toString());
					if (spinner_3.equals(">")) {
						if (BaseFragment.ill > number_get) {
							if (spinner_4.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_warm.setChecked(true);
							}
							if (spinner_4.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_lamp1.setChecked(true);
								BaseFragment.tg_lamp2.setChecked(true);
							}
						} else {
							link_2 = false;
							cb_2.setChecked(false);
							DiyToast.showToast(getActivity(), "条件不满足");
							if (spinner_4.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_warm.setChecked(false);
							}
							if (spinner_4.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_lamp1.setChecked(false);
								BaseFragment.tg_lamp2.setChecked(false);
							}
						}
					}
					if (spinner_3.equals("<=")) {
						if (BaseFragment.ill <= number_get) {
							if (spinner_4.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_warm.setChecked(true);
							}
							if (spinner_4.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_lamp1.setChecked(true);
								BaseFragment.tg_lamp2.setChecked(true);
							}
						} else {
							link_2 = false;
							cb_2.setChecked(false);
							DiyToast.showToast(getActivity(), "条件不满足");
							if (spinner_4.equals("报警灯开")) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_warm.setChecked(false);
							}
							if (spinner_4.equals("射灯全开")) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_lamp1.setChecked(false);
								BaseFragment.tg_lamp2.setChecked(false);
							}
						}
					}
				}
			}
			if (link_1) {
				if (et_get_number_1.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "请输入数值");
					link_1 = false;
					cb_1.setChecked(false);
				} else {
					String spinner_1 = sp_1.getSelectedItem().toString();
					String spinner_2 = sp_2.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_get_number_1.getText()
							.toString());
					if (spinner_1.equals("温度")) {
						if (spinner_1.equals(">")) {
							if (BaseFragment.temp > number_get) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_fan.setChecked(true);
							} else {
								link_1 = false;
								cb_1.setChecked(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_fan.setChecked(false);
							}
						}
						if (spinner_2.equals("<=")) {
							if (BaseFragment.temp <= number_get) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_fan.setChecked(true);
							} else {
								link_1 = false;
								cb_1.setChecked(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_fan.setChecked(false);
							}
						}
					}
					if (spinner_1.equals("光照")) {

						if (spinner_1.equals(">")) {
							if (BaseFragment.ill > number_get) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_fan.setChecked(true);
							} else {
								link_1 = false;
								cb_1.setChecked(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_fan.setChecked(false);
							}
						}
						if (spinner_2.equals("<=")) {
							if (BaseFragment.ill <= number_get) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								BaseFragment.tg_fan.setChecked(true);
							} else {
								link_1 = false;
								cb_1.setChecked(false);
								DiyToast.showToast(getActivity(), "条件不满足");
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								BaseFragment.tg_fan.setChecked(false);
							}
						}

					}
				}
			}
			handler.postDelayed(timeRunnable, 500);
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

	private void initView(View view) {
		// TODO Auto-generated method stub
		sp_1 = (Spinner) view.findViewById(R.id.sp_1);
		sp_2 = (Spinner) view.findViewById(R.id.sp_2);
		sp_3 = (Spinner) view.findViewById(R.id.sp_3);
		sp_4 = (Spinner) view.findViewById(R.id.sp_4);
		et_get_number_1 = (EditText) view.findViewById(R.id.et_number_get_1);
		et_get_number_2 = (EditText) view.findViewById(R.id.et_number_get_2);
		cb_1 = (CheckBox) view.findViewById(R.id.cb_link_1);
		cb_2 = (CheckBox) view.findViewById(R.id.cb_link_2);
	}
}
