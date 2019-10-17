package com.example.shengsaicdemo10072018.fragment;

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
import android.widget.Switch;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaicdemo10072018.R;
import com.example.shengsaicdemo10072018.toast.DiyToast;

/*
 * @�ļ�����LinkFragment.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
 */
public class LinkFragment extends Fragment {
	private Switch sw_link_state;
	private EditText et_number_get;
	private Spinner sp_1;
	private CheckBox cb_mode_anfang;// ����ģʽ
	private CheckBox cb_mode_lijia;// ���ģʽ
	private CheckBox cb_mode_diy;// �Զ���ģʽ
	private CheckBox cb_lamp;// ���
	private CheckBox cb_fan;// ����
	private CheckBox cb_warm;// ������
	private CheckBox cb_door;// �Ž�
	private CheckBox cb_td_1;// ͨ��1
	private CheckBox cb_td_2;// ͨ��2
	private CheckBox cb_td_3;// ͨ��3
	private CheckBox cb_cur;// ����

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);
		// ֻ��ѡ��һ��Ч��
		// ����ģʽ
		cb_mode_anfang
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_mode_anfang.setChecked(false);
							cb_mode_diy.setChecked(false);
							cb_mode_lijia.setChecked(false);
						}
					}
				});
		// ���ģʽ
		cb_mode_lijia.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_mode_anfang.setChecked(false);
					cb_mode_diy.setChecked(false);
					// cb_mode_lijia.setChecked(false);
				}
			}
		});
		// �Զ���ģʽ
		cb_mode_diy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_mode_anfang.setChecked(false);
					// cb_mode_diy.setChecked(false);
					cb_mode_lijia.setChecked(false);
				}
			}
		});
		// ����ģʽ����
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "��������ֵ");
						sw_link_state.setChecked(false);
					} else {
						if (cb_mode_diy.isChecked()) {

						} else {
							DiyToast.showToast(getActivity(), "�빴ѡ�Զ���ģʽ");
							sw_link_state.setChecked(false);
						}
					}
				} else {
					if (cb_cur.isChecked()) {
						// ����
						cb_cur.setChecked(false);
					}
					if (cb_door.isChecked()) {
						// �Ž�
						cb_door.setChecked(false);
					}
					if (cb_fan.isChecked()) {
						// ����
						cb_fan.setChecked(false);
					}
					if (cb_lamp.isChecked()) {
						// ���
						cb_lamp.setChecked(false);
					}
					if (cb_warm.isChecked()) {
						// ������
						cb_warm.setChecked(false);
					}
					if (cb_td_1.isChecked()) {
						cb_td_1.setChecked(false);
					}
					if (cb_td_2.isChecked()) {
						cb_td_2.setChecked(false);
					}
					if (cb_td_3.isChecked()) {
						cb_td_3.setChecked(false);
					}
				}
			}
		});
		// ����
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// ģʽ����
			if (cb_mode_anfang.isChecked()) {
				// ����ģʽ
				// ��ģʽ��ť��ʱ����ʼ���������⣬����ʾ����ʱ���򿪱����ƣ�ģʽ
				// �ر�ʱ�򲻴�����
				if (BaseFragment.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_mode_lijia.isChecked()) {
				// ��ģʽ��ť��ʱ����ʼ���������⣬����ʾ����ʱ����ȼ��ֵ�ﵽ 800
				// ʱ���򿪱����ƣ�ģʽ�ر�ʱ�򲻴����Ž����ƹ���ͨ������������Ž����ؿ�
				// ���Ž��Ŀ��������Ӧ״̬�Ĳɼ�������״̬ʵʱ��ʾ�����˻����ˣ���
				if (BaseFragment.per == 1 || BaseFragment.gas > 800) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			// ����
			if (sw_link_state.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()
						|| cb_mode_diy.isChecked()) {
					if (sp_1.getSelectedItem().toString().equals(">")) {
						if (BaseFragment.temp > Integer.valueOf(et_number_get
								.getText().toString())) {
							if (cb_cur.isChecked()) {
								// ����
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								// �Ž�
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								// ����
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								// ���
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								// ������
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_td_1.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_td_2.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_td_3.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							sw_link_state.setChecked(false);
						}
					}
					if (sp_1.getSelectedItem().toString().equals("<")) {
						if (BaseFragment.temp < Integer.valueOf(et_number_get
								.getText().toString())) {
							if (cb_cur.isChecked()) {
								// ����
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								// �Ž�
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								// ����
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								// ���
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								// ������
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_td_1.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_td_2.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_td_3.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							sw_link_state.setChecked(false);
						}
					}
					if (sp_1.getSelectedItem().toString().equals("=")) {
						if (BaseFragment.temp == Integer.valueOf(et_number_get
								.getText().toString())) {
							if (cb_cur.isChecked()) {
								// ����
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								// �Ž�
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								// ����
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								// ���
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								// ������
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_td_1.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_td_2.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_td_3.isChecked()) {
								// ������
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							sw_link_state.setChecked(false);
						}
					}
				} else {
					DiyToast.showToast(getActivity(), "�Զ���ģʽδ��ѡ����δ������ֵ");
					sw_link_state.setChecked(false);
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

	private void initView(View view) {
		// TODO Auto-generated method stub
		sw_link_state = (Switch) view.findViewById(R.id.sw_link_state);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_mode_anfang = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_mode_diy = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_mode_lijia = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
	}
}
