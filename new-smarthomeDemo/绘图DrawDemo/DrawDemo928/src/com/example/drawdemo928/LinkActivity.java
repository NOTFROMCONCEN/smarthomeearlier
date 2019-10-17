package com.example.drawdemo928;

import java.util.ArrayList;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @�ļ�����LinkActivity.java
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-28
 */
public class LinkActivity extends Fragment {
	String[] mStringArrayList;
	ArrayAdapter<String> mArrayAdapter;
	private Spinner sp_1;// spinner1
	private Spinner sp_2;// spinner2
	private EditText et_number_get;// �ı���
	private CheckBox cb_day_mode;// ����ģʽ
	private CheckBox cb_night_mode;// ҹ��ģʽ
	private CheckBox cb_diy_mode;// �Զ���ģʽ
	private CheckBox cb_fan;// ����
	private CheckBox cb_lamp;// ���
	private CheckBox cb_door;// �Ž�
	private CheckBox cb_warm;// ������
	private CheckBox cb_td_1;// ͨ��1
	private CheckBox cb_td_2;// ͨ��2
	private CheckBox cb_td_3;// ͨ��3
	private CheckBox cb_cur;// ����
	private boolean link_state = false;
	private boolean day_state = false;
	private boolean night_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);// ��
		// ����Spinner��������ʽ
		// spinner2
		mStringArrayList = getResources().getStringArray(R.array.big_small);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArrayList);
		sp_2.setAdapter(mArrayAdapter);
		// spinner1
		mStringArrayList = getResources().getStringArray(R.array.temp_hum);
		mArrayAdapter = new AdaHelper(getActivity(), mStringArrayList);
		sp_1.setAdapter(mArrayAdapter);

		// �Զ���ģʽ
		cb_diy_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_day_mode.setChecked(false);
					// cb_diy_mode.setChecked(false);
					cb_night_mode.setChecked(false);
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showTaost(getActivity(), "��������ֵ");
						cb_diy_mode.setChecked(false);
						link_state = false;
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});
		// ����ģʽ
		cb_day_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// cb_day_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					cb_night_mode.setChecked(false);
					day_state = true;
				} else {
					day_state = false;
				}
			}
		});
		// ҹ��ģʽ
		cb_night_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_day_mode.setChecked(false);
					cb_diy_mode.setChecked(false);
					// cb_night_mode.setChecked(false);
					night_state = true;
				} else {
					night_state = false;
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
	 * @�� �ܣ�����
	 * 
	 * @ʱ �䣺����7:08:41
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (day_state) {
				System.out.println("����ģʽ");
			}
			if (night_state) {
				System.out.println("ҹ��ģʽ");
			}
			if (link_state) {
				System.out.println("�Զ���ģʽ");
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showTaost(getActivity(), "��������ֵ");
				} else {
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
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "����������");
								cb_diy_mode.setChecked(false);
								link_state = false;
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
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "����������");
								cb_diy_mode.setChecked(false);
								link_state = false;
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
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "����������");
								cb_diy_mode.setChecked(false);
								link_state = false;
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
								if (cb_lamp.isChecked()) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showTaost(getActivity(), "����������");
								cb_diy_mode.setChecked(false);
								link_state = false;
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

	/*
	 * @��������initView()
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����7:07:28
	 */
	private void initView(View view) {
		cb_cur = (CheckBox) view.findViewById(R.id.cb_cur);
		cb_day_mode = (CheckBox) view.findViewById(R.id.cb_day_mode);
		cb_diy_mode = (CheckBox) view.findViewById(R.id.cb_diy_mode);
		cb_door = (CheckBox) view.findViewById(R.id.cb_door);
		cb_fan = (CheckBox) view.findViewById(R.id.cb_fan);
		cb_lamp = (CheckBox) view.findViewById(R.id.cb_lamp);
		cb_night_mode = (CheckBox) view.findViewById(R.id.cb_night_mode);
		cb_td_1 = (CheckBox) view.findViewById(R.id.cb_td_1);
		cb_td_2 = (CheckBox) view.findViewById(R.id.cb_td_2);
		cb_td_3 = (CheckBox) view.findViewById(R.id.cb_td_3);
		cb_warm = (CheckBox) view.findViewById(R.id.cb_warm);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_get = (EditText) view.findViewById(R.id.et_number);
	}
}
