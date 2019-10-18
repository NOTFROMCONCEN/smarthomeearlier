package com.example.shengsaiademo10052018.fragment;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaiademo10052018.R;
import com.example.shengsaiademo10052018.toast.DiyToast;

import android.app.AlertDialog;
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
import android.widget.ToggleButton;

/*
 * @�ļ�����LinkActivity.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-5
 */
public class LinkActivity extends Fragment {
	private CheckBox cb_lamp;// ���
	private CheckBox cb_fan;// ����
	private CheckBox cb_warm;// ������
	private CheckBox cb_door;// �Ž�
	private CheckBox cb_tv;// ����
	private CheckBox cb_dvd;// DVD
	private CheckBox cb_kongtiao;// �յ�
	private CheckBox cb_cur;// ����
	private Spinner sp_1, sp_2;// spinner
	private EditText et_number_get;
	private ToggleButton tg_link_state;
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_dvd = (CheckBox) view.findViewById(R.id.cb_dvd);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_kongtiao = (CheckBox) view.findViewById(R.id.cb_kongtiao);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_tv = (CheckBox) view.findViewById(R.id.cb_tv);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		tg_link_state = (ToggleButton) view.findViewById(R.id.tg_link_state);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		tg_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						link_state = false;
						new AlertDialog.Builder(getActivity()).setTitle("��ʾ")
								.setMessage("��������ȷ����ֵ")
								.setPositiveButton("ȷ��", null).show();
						tg_link_state.setChecked(false);
					} else {
						if (cb_cur.isChecked() || cb_door.isChecked()
								|| cb_dvd.isChecked() || cb_fan.isChecked()
								|| cb_kongtiao.isChecked()
								|| cb_lamp.isChecked() || cb_tv.isChecked()
								|| cb_warm.isChecked()) {
							link_state = true;
						} else {
							new AlertDialog.Builder(getActivity())
									.setTitle("��ʾ").setMessage("��ѡ����Ӧ������")
									.setPositiveButton("ȷ��", null).show();
							link_state = false;
							tg_link_state.setChecked(false);
						}
					}
				} else {
					link_state = false;
				}
			}
		});
		// �������
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ���������ģʽ
	 * 
	 * @ʱ �䣺����9:23:15
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				String spinner1 = sp_1.getSelectedItem().toString();
				String spinner2 = sp_2.getSelectedItem().toString();
				int number_get = Integer.valueOf(et_number_get.getText()
						.toString());
				if (spinner1.equals("�¶�")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.temp > number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "���������㣡");
							link_state = false;
							tg_link_state.setChecked(false);
						}
					}
					if (spinner2.equals("<")) {
						if (BaseActivity.temp < number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							DiyToast.showToast(getActivity(), "���������㣡");
							link_state = false;
							tg_link_state.setChecked(false);
						}
					}
					if (spinner2.equals("=")) {
						if (BaseActivity.temp == number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
				}
				if (spinner1.equals("ʪ��")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.hum > number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
					if (spinner2.equals("<")) {
						if (BaseActivity.hum < number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
					if (spinner2.equals("=")) {
						if (BaseActivity.hum == number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
				}

				if (spinner1.equals("����")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.ill > number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
					if (spinner2.equals("<")) {
						if (BaseActivity.ill < number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
					if (spinner2.equals("=")) {
						if (BaseActivity.ill == number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
				}
				if (spinner1.equals("pm2.5")) {
					if (spinner2.equals(">")) {
						if (BaseActivity.pm > number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
					if (spinner2.equals("<")) {
						if (BaseActivity.pm < number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
						}
					}
					if (spinner2.equals("=")) {
						if (BaseActivity.pm == number_get) {
							if (cb_cur.isChecked()) {
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_door.isChecked()) {
								ControlUtils.control(
										ConstantUtil.RFID_Open_Door,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_dvd.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_3,
										ConstantUtil.OPEN);
							}
							if (cb_fan.isChecked()) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_kongtiao.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_2,
										ConstantUtil.OPEN);
							}
							if (cb_lamp.isChecked()) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
							if (cb_tv.isChecked()) {
								ControlUtils.control(
										ConstantUtil.INFRARED_1_SERVE,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);
							}
							if (cb_warm.isChecked()) {
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						} else {
							link_state = false;
							tg_link_state.setChecked(false);
							DiyToast.showToast(getActivity(), "���������㣡");
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