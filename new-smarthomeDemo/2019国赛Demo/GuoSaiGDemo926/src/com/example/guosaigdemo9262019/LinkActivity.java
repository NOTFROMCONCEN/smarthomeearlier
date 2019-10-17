package com.example.guosaigdemo9262019;

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

/*
 * @�ļ�����LinkActivity.java
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-26
 */
public class LinkActivity extends Fragment {
	private CheckBox cb_anfang_mode;// ����ģʽ
	private CheckBox cb_lijia_mode;// ���ģʽ
	private CheckBox cb_diy_mode;// �Զ���ģʽ
	private Spinner sp_1;
	private EditText et_number_get;
	private CheckBox cb_lamp;// ���
	private CheckBox cb_fan;// ����
	private CheckBox cb_warm;// ������
	private CheckBox cb_door;// �Ž�
	private CheckBox cb_td_1;// ͨ��1
	private CheckBox cb_td_2;// ͨ��2
	private CheckBox cb_td_3;// ͨ��3
	private CheckBox cb_cur;// ����
	private Switch sw_link_state;// ����ģʽ����
	// ģʽ�Ƿ񼤻�
	private boolean anfang_mode = false;
	private boolean lijia_mode = false;
	private boolean diy_mode = false;
	private boolean link_state = false;
	private boolean control_state = false;

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
					if (diy_mode) {
						if (et_number_get.getText().toString().isEmpty()) {
							DiyToast.showToast(getActivity(), "��������ֵ");
							sw_link_state.setChecked(false);
						} else {
							link_state = true;
						}
					} else {
						DiyToast.showToast(getActivity(), "�빴ѡ�Զ���ģʽ");
						sw_link_state.setChecked(false);
						link_state = false;
					}
				} else {
					link_state = false;
				}
			}
		});
		// ����ģʽ
		cb_anfang_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							// cb_anfang_mode.setChecked(false);
							cb_diy_mode.setChecked(false);
							cb_lijia_mode.setChecked(false);
							anfang_mode = true;
						} else {
							anfang_mode = false;
						}
					}
				});
		// ���ģʽ
		cb_lijia_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					// cb_lijia_mode.setChecked(false);
					lijia_mode = true;
				} else {
					lijia_mode = false;
				}
			}
		});
		// �Զ���ģʽ
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_anfang_mode.setChecked(false);
					// cb_diy_mode.setChecked(false);
					cb_lijia_mode.setChecked(false);
					diy_mode = true;
				} else {
					diy_mode = false;
					cb_cur.setChecked(false);
					cb_door.setChecked(false);
					cb_fan.setChecked(false);
					cb_lamp.setChecked(false);
					cb_td_1.setChecked(false);
					cb_td_2.setChecked(false);
					cb_td_3.setChecked(false);
					cb_warm.setChecked(false);
				}
			}
		});
		// �������
		handler.post(timeRunnable);
		// �豸����
		// ����
		cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
				}
			}
		});
		// ���
		cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// ����
		cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// ������
		cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// �Ž�
		cb_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
			}
		});
		// ͨ��1
		cb_td_1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// ͨ��2
		cb_td_2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
					}
				}
			}
		});
		// ͨ��3
		cb_td_3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (control_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
								ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
					}
				}
			}
		});
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�������ģʽ
	 * 
	 * @ʱ �䣺����3:52:30
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (anfang_mode) {
				// ����ģʽ
				// ��ʼ���������⣬����ʾ����ʱ���򿪱����ƣ�ģʽ�ر�ʱ�򲻴�����
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (diy_mode) {
				if (link_state) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "��������ֵ");
						sw_link_state.setChecked(false);
						link_state = false;
					} else {
						String spinner = sp_1.getSelectedItem().toString();
						int number_get = Integer.valueOf(et_number_get
								.getText().toString());
						if (spinner.equals(">")) {
							if (BaseActivity.temp > number_get) {
								control_state = true;
							} else {
								DiyToast.showToast(getActivity(), "����������");
								link_state = false;
								sw_link_state.setChecked(false);
							}
						}
						if (spinner.equals("<")) {
							if (BaseActivity.temp < number_get) {
								control_state = true;
							} else {
								DiyToast.showToast(getActivity(), "����������");
								link_state = false;
								sw_link_state.setChecked(false);
							}
						}
					}
				}
			}
			if (lijia_mode) {
				// ���ģʽ
				// ����ʾ����ʱ����ȼ��ֵ�ﵽ800ʱ���򿪱����ƣ�ģʽ�ر�ʱ�򲻴�����
				if (BaseActivity.per == 1 || BaseActivity.gas >= 800) {
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

	/*
	 * @��������initView()
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����3:51:16
	 */
	private void initView(View view) {
		sw_link_state = (Switch) view.findViewById(R.id.sw_link_state);
		cb_anfang_mode = (CheckBox) view.findViewById(R.id.cb_anfang_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_lijia_mode = (CheckBox) view.findViewById(R.id.cb_lijia_mode);
		sp_1 = (Spinner) view.findViewById(R.id.spinner_link);
		et_number_get = (EditText) view.findViewById(R.id.et_link_number_get);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
	}
}