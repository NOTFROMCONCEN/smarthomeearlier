package com.example.drawdemo824;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DeviceBean;

/*
 * @�ļ�����LinkActivity.java
 * @����������豸������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-23
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1;// Spinner�����˵�
	private Spinner spinner2;// Spinner�����˵�
	private Spinner spinner3;// Spinner�����˵�
	private EditText et_get_number;// �ı���
	private RadioButton ra_temp_mode, ra_fangdao_mode, ra_night_mode;// �¶�ģʽ������ģʽ��ҹ��ģʽ
	private RadioGroup rg_mode_check;// ��ѡ��ť��
	private CheckBox cb_start_mode;// ��ѡ��
	private boolean link_state = false;
	private ArrayAdapter<String> mArrayAdapter;
	private String[] mStringArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		spinner3 = (Spinner) view.findViewById(R.id.spinner3);
		et_get_number = (EditText) view.findViewById(R.id.et_get_number);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		ra_temp_mode = (RadioButton) view.findViewById(R.id.ra_temp_mode);
		rg_mode_check = (RadioGroup) view.findViewById(R.id.rg_mode_check);
		cb_start_mode = (CheckBox) view.findViewById(R.id.cb_start_mode);
		// ����Spinner�����˵�XML�ļ�
		mStringArray = getResources().getStringArray(R.array.big_small);// ��С
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner2.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.temp_hum);// �¶ȡ�ʪ��
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner1.setAdapter(mArrayAdapter);
		mStringArray = getResources().getStringArray(R.array.lamp_fan);// ��ơ�����
		mArrayAdapter = new AdaHelper(getActivity(), mStringArray);
		spinner3.setAdapter(mArrayAdapter);
		// ���ø�ѡ�����¼�
		cb_start_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_get_number.getText().toString().equals("")) {
						DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
						link_state = false;
						cb_start_mode.setChecked(false);
					} else {
						link_state = true;
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

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				if (!et_get_number.getText().toString().equals("")) {
					String sp_1, sp_2, sp_3;
					int number_get;
					sp_1 = spinner1.getSelectedItem().toString();// ����sp1
					sp_2 = spinner2.getSelectedItem().toString();// ����sp2
					sp_3 = spinner3.getSelectedItem().toString();// ����sp3
					number_get = Integer.valueOf(et_get_number.getText()
							.toString());
					if (sp_1.equals("�¶�")) {
						// ���spinner1Ϊ�¶�
						if (sp_2.equals(">")) {
							// ���spinner2Ϊ����
							if (BaseActivity.temp > number_get) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "���������㣡");
								link_state = false;
								cb_start_mode.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							// ���spinner2ΪС��
							if (BaseActivity.temp < number_get) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "���������㣡");
								link_state = false;
								cb_start_mode.setChecked(false);
							}

						}
					}
					if (sp_1.equals("ʪ��")) {
						// ���spinner1Ϊʪ��
						if (sp_2.equals(">")) {
							// ���spinner2Ϊ����
							if (BaseActivity.hum > number_get) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "���������㣡");
								link_state = false;
								cb_start_mode.setChecked(false);
							}
						}
						if (sp_2.equals("<")) {
							// ���spinner2ΪС��
							if (BaseActivity.hum < number_get) {
								if (sp_3.equals("���")) {
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
								if (sp_3.equals("����")) {
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
								}
							} else {
								DiyToast.showToast(getActivity(), "���������㣡");
								link_state = false;
								cb_start_mode.setChecked(false);
							}

						}
					}
				} else {
					DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
					link_state = false;
					cb_start_mode.setChecked(false);
				}
			}
			if (ra_fangdao_mode.isChecked()) {
				// ����ģʽ��ѡ��
				if (!TextUtils.isEmpty(DeviceBean.getStateHumanInfrared())
						&& DeviceBean.getStateHumanInfrared().equals(
								ConstantUtil.CLOSE)) {// �����������ʱ�ر�
					if (!TextUtils.isEmpty(DeviceBean.getWarningLight())
							&& !DeviceBean.getWarningLight().equals(
									ConstantUtil.CLOSE))
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				} else if (!TextUtils.isEmpty(DeviceBean
						.getStateHumanInfrared())
						&& !DeviceBean.getStateHumanInfrared().equals(
								ConstantUtil.CLOSE)) {// ����ʱ�򿪱�����
					if (!TextUtils.isEmpty(DeviceBean.getWarningLight())
							&& DeviceBean.getWarningLight().equals(
									ConstantUtil.CLOSE))
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (ra_night_mode.isChecked()) {
				// ҹ��ģʽ��ѡ��
			}
			if (ra_temp_mode.isChecked()) {
				// �¶�ģʽ��ѡ��
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