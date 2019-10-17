package com.example.shengsaiedemo20181009.fragment;

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
import com.example.shengsaiedemo20181009.R;
import com.example.shengsaiedemo20181009.toast.DiyToast;

/*
 * @�ļ�����LinkFragment.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-9
 */
public class LinkFragment extends Fragment {
	private CheckBox cb_anfang_mode;// ����ģʽ
	private CheckBox cb_lijia_mode;// ���ģʽ
	private CheckBox cb_diy_mode;// �Զ���ģʽ
	private EditText et_number_get;
	private Spinner sp_1;
	private Switch sw_link_state;
	private CheckBox cb_lamp;// ���
	private CheckBox cb_fan;// ����
	private CheckBox cb_warm;// ������
	private CheckBox cb_door;// �Ž�
	private CheckBox cb_td_1;// ͨ��1
	private CheckBox cb_td_2;// ͨ��2
	private CheckBox cb_td_3;// ͨ��3
	private CheckBox cb_cur;// ����
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "��������ֵ");
						link_state = false;
						sw_link_state.setChecked(false);
					} else {
						if (cb_diy_mode.isChecked()) {
							link_state = true;
						} else {
							DiyToast.showToast(getActivity(), "�빴ѡ�Զ���ģʽ");
							link_state = false;
							sw_link_state.setChecked(false);
						}
					}
				} else {
					link_state = false;
				}
			}
		});
		// ����
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����
	 * 
	 * @ʱ �䣺����9:42:19
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ģʽ����
			if (cb_anfang_mode.isChecked()) {
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
			if (cb_lijia_mode.isChecked()) {
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
			// ����ģʽ
			if (link_state) {
				if (cb_diy_mode.isChecked()) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "��������ֵ");
						link_state = false;
						sw_link_state.setChecked(false);
					} else {
						String spinner1 = sp_1.getSelectedItem().toString();
						int number_get = Integer.valueOf(et_number_get
								.getText().toString());
						if (spinner1.equals(">")) {
							if (BaseFragment.temp > number_get) {
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_fan.isChecked()) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_warm.isChecked()) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_door.isChecked()) {
									ControlUtils.control(
											ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_td_1.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_td_2.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
								if (cb_td_3.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_3,
											ConstantUtil.OPEN);
								}
								if (cb_cur.isChecked()) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "�쳣������������");
								link_state = false;
								sw_link_state.setChecked(false);
							}
						}
						if (spinner1.equals("<=")) {
							if (BaseFragment.temp <= number_get) {
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_fan.isChecked()) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_warm.isChecked()) {
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_door.isChecked()) {
									ControlUtils.control(
											ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (cb_td_1.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
								if (cb_td_2.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_2,
											ConstantUtil.OPEN);
								}
								if (cb_td_3.isChecked()) {
									ControlUtils.control(
											ConstantUtil.INFRARED_1_SERVE,
											ConstantUtil.CHANNEL_3,
											ConstantUtil.OPEN);
								}
								if (cb_cur.isChecked()) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "�쳣������������");
								link_state = false;
								sw_link_state.setChecked(false);
							}
						}
					}
				} else {
					DiyToast.showToast(getActivity(), "�쳣���Զ���ģʽδѡ��");
					link_state = false;
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
		cb_anfang_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_lijia_mode = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		et_number_get = (EditText) view.findViewById(R.id.eet_number_get);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sw_link_state = (Switch) view.findViewById(R.id.sw_link_state);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
	}
}
