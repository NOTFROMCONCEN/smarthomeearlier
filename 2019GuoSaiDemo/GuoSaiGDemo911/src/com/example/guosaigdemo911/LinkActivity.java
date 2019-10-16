package com.example.guosaigdemo911;

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

/*
 * @�ļ�����LinkActivity.java
 * @�����������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-11
 */
public class LinkActivity extends Fragment {
	// �����豸
	private CheckBox cb_lamp;// ���
	private CheckBox cb_fan;// ����
	private CheckBox cb_warm;// ������
	private CheckBox cb_door;// �Ž�
	private CheckBox cb_td_1;// ͨ��1
	private CheckBox cb_td_2;// ͨ��2
	private CheckBox cb_td_3;// ͨ��3
	private CheckBox cb_cur;// ����

	// ģʽ����
	private CheckBox cb_anfang_mode;// ����ģʽ
	private CheckBox cb_lijai_mode;// ���ģʽ
	private CheckBox cb_diy_mode;// �Զ���ģʽ

	// ��������
	private Spinner sp_link;
	private EditText et_number_get;
	private boolean diy_mode_state = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		cb_anfang_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_lijai_mode = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		sp_link = (Spinner) view.findViewById(R.id.spinner_link);
		et_number_get = (EditText) view.findViewById(R.id.et_link_number_get);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		// �������
		handler.post(timeRunnable);
		// �Զ���ģʽ����
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					DiyToast.showToast(getActivity(), "�Զ���ģʽ����");
				} else {
					cb_cur.setChecked(false);
					cb_door.setChecked(false);
					cb_fan.setChecked(false);
					cb_lamp.setChecked(false);
					cb_warm.setChecked(false);
					cb_td_1.setChecked(false);
					cb_td_2.setChecked(false);
					cb_td_3.setChecked(false);
					diy_mode_state = false;
				}
			}
		});
		// �趨�豸����
		// ����
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_cur.setChecked(false);
				}
			}
		});
		// �Ž�
		cb_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_door.setChecked(false);
				}
			}
		});
		// ����
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_fan.setChecked(false);
				}
			}
		});
		// ���
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_lamp.setChecked(false);
				}
			}
		});
		// ������
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_warm.setChecked(false);
				}
			}
		});
		// ͨ��1
		cb_td_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_td_1.setChecked(false);
				}
			}
		});
		// ͨ��2
		cb_td_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_td_2.setChecked(false);
				}
			}
		});
		// ͨ��3
		cb_td_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (diy_mode_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뼤���Զ���ģʽ");
					cb_td_3.setChecked(false);
				}
			}
		});
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_diy_mode.isChecked()) {
				// �Զ���ģʽ
				String spinner1 = sp_link.getSelectedItem().toString();
				int number_get = Integer.valueOf(et_number_get.getText()
						.toString());
				if (spinner1.equals(">")) {
					if (BaseActivity.temp > number_get) {
						diy_mode_state = true;// ����
					} else {
						diy_mode_state = false;// �ر�
						DiyToast.showToast(getActivity(), "����������");
						cb_cur.setChecked(false);
						cb_door.setChecked(false);
						cb_fan.setChecked(false);
						cb_lamp.setChecked(false);
						cb_warm.setChecked(false);
						cb_td_1.setChecked(false);
						cb_td_2.setChecked(false);
						cb_td_3.setChecked(false);
					}
				}
				if (spinner1.equals("<")) {
					if (BaseActivity.temp < number_get) {
						diy_mode_state = true;// ����
					} else {
						diy_mode_state = false;// �ر�
						DiyToast.showToast(getActivity(), "����������");
						cb_cur.setChecked(false);
						cb_door.setChecked(false);
						cb_fan.setChecked(false);
						cb_lamp.setChecked(false);
						cb_warm.setChecked(false);
						cb_td_1.setChecked(false);
						cb_td_2.setChecked(false);
						cb_td_3.setChecked(false);
					}
				}
			}
			if (cb_anfang_mode.isChecked()) {
				// ���ģʽ��ģʽ��ť��ʱ����ʼ���������⣬����ʾ����ʱ���򿪱����ƣ�ģʽ�ر�ʱ�򲻴�����
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_lijai_mode.isChecked()) {
				// ����ģʽ��ģʽ��ť��ʱ����ʼ����������
				// ����ʾ����ʱ����ȼ��ֵ�ﵽ800ʱ���򿪱����ƣ�ģʽ�ر�ʱ�򲻴�����
				if (BaseActivity.per == 1 || BaseActivity.gas > 800) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
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
