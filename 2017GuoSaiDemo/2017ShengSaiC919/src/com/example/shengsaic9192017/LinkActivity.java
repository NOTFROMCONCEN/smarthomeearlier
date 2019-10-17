package com.example.shengsaic9192017;

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
 * @�ļ�����LinkActivity.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1;// spinner1
	private Spinner sp_2;// spinner2
	private Spinner sp_3;// spinner3
	private Spinner sp_4;// spinner4
	private EditText et_number_get_1;
	private EditText et_number_get_2;
	private CheckBox cb_link_1;
	private CheckBox cb_link_2;
	private boolean link_1_state = false, link_2_state = false;

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
						DiyToast.showToast(getActivity(), "��������ֵ");
						cb_link_1.setChecked(false);
						link_1_state = false;
					} else {
						link_1_state = true;
					}
				} else {
					link_1_state = false;
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
						DiyToast.showToast(getActivity(), "��������ֵ");
						cb_link_2.setChecked(false);
						link_2_state = false;
					} else {
						link_2_state = true;
					}
				} else {
					link_2_state = false;
				}
			}
		});
		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_2_state) {

				if (et_number_get_2.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
					link_2_state = false;
					cb_link_2.setChecked(false);
				} else {
					if (link_2_state) {
						String spinner3 = sp_3.getSelectedItem().toString();
						String spinner4 = sp_4.getSelectedItem().toString();
						int number_get = Integer.valueOf(et_number_get_2
								.getText().toString());
						if (spinner3.equals(">")) {
							if (BaseActivity.ill > number_get) {
								if (spinner4.equals("�����ƿ�")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner4.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_2.setChecked(false);
								link_2_state = false;
								if (spinner4.equals("�����ƿ�")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (spinner4.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (spinner3.equals("<=")) {
							if (BaseActivity.ill < number_get) {
								if (spinner4.equals("�����ƿ�")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (spinner4.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_2.setChecked(false);
								link_2_state = false;
								if (spinner4.equals("�����ƿ�")) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (spinner4.equals("���ȫ��")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
					}
				}
			}
			if (link_1_state) {

				if (et_number_get_1.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
					link_1_state = false;
					cb_link_1.setChecked(false);
				} else {
					if (link_1_state) {
						String spinner1 = sp_1.getSelectedItem().toString();
						String spinner2 = sp_2.getSelectedItem().toString();
						int number_get = Integer.valueOf(et_number_get_1
								.getText().toString());
						if (spinner1.equals("�¶�")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.temp > number_get) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									DiyToast.showToast(getActivity(), "����������");
									link_1_state = false;
									cb_link_1.setChecked(false);
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.temp < number_get) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									DiyToast.showToast(getActivity(), "����������");
									link_1_state = false;
									cb_link_1.setChecked(false);
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}
						if (spinner1.equals("ʪ��")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.hum > number_get) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									DiyToast.showToast(getActivity(), "����������");
									link_1_state = false;
									cb_link_1.setChecked(false);
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.hum < number_get) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								} else {
									DiyToast.showToast(getActivity(), "����������");
									link_1_state = false;
									cb_link_1.setChecked(false);
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
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