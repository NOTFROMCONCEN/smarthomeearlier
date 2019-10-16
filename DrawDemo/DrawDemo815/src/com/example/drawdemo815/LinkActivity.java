package com.example.drawdemo815;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @�ļ�����LinkActivity.java
 * @���������û������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-14
 */
public class LinkActivity extends Fragment {
	/**
	 * ����ؼ�
	 */
	private CheckBox check_open;// ��ѡ��
	private Spinner sp_1, sp_2;// spinner�����˵�
	private EditText et_number;// �ı���
	private boolean link_state = false;
	private RadioButton ra_day_mode, ra_lijia_mode, ra_night_mode;// ��ѡ��ť
	private RadioGroup rg_mode_check;// ��ѡ��ť��

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		/**
		 * �󶨿ؼ�
		 */
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_lijia_mode = (RadioButton) view.findViewById(R.id.ra_lijia_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		check_open = (CheckBox) view.findViewById(R.id.check_open);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number = (EditText) view.findViewById(R.id.et_number);

		// ���ø�ѡ��ѡ�¼�
		check_open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "��ֵ����Ϊ��",
								Toast.LENGTH_SHORT).show();
						link_state = false;
						check_open.setChecked(false);
					} else {
						link_state = true;
					}
				} else {
					link_state = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����link_state״̬�������豸
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// ����ģʽ����
			if (link_state) {
				String spinner_1 = sp_1.getSelectedItem().toString();// ��ȡ�����˵����ݲ���ֵ
				String spinner_2 = sp_2.getSelectedItem().toString();// ��ȡ�����˵����ݲ���ֵ
				int get_number = Integer
						.valueOf(et_number.getText().toString());
				if (spinner_1.equals("�¶�")) {
					System.out.println("�¶�ģʽ");
					if (spinner_2.equals(">")) {
						System.out.println(">");
						if (BaseActivity.temp > get_number) {
							// ���¶ȴ����趨ֵ���򿪷���
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("���ȿ�");
						} else {
							// �����������㣬������ʾ���رշ���
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("����������");
						}
					}
					if (spinner_2.equals("<")) {
						System.out.println("<");
						if (BaseActivity.temp < get_number) {
							// ���¶�С���趨ֵ���򿪷���
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("���ȿ�");
						} else {
							// �����������㣬������ʾ���رշ���
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("����������");
						}
					}
				}
				if (spinner_1.equals("ʪ��")) {
					System.out.println("ʪ��ģʽ");
					if (spinner_2.equals(">")) {
						System.out.println(">");
						if (BaseActivity.hum > get_number) {
							// ��ʪ�ȴ����趨ֵ���򿪷���
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("���ȿ�");
						} else {
							// �����������㣬������ʾ���رշ���
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("����������");
						}
					}
					if (spinner_2.equals("<")) {
						System.out.println("<");
						if (BaseActivity.hum < get_number) {
							// ��ʪ��С���趨ֵ���򿪷���
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							System.out.println("���ȿ�");
						} else {
							// �����������㣬������ʾ���رշ���
							Toast.makeText(getActivity(), "����������",
									Toast.LENGTH_SHORT).show();
							link_state = false;
							check_open.setChecked(false);
							System.out.println("����������");
						}
					}
				}
			}
			// ģʽѡ����
			if (ra_day_mode.isChecked()) {
				// ����ģʽ
				System.out.println("����ģʽ");
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			if (ra_lijia_mode.isChecked()) {
				// ���ģʽ
				System.out.println("���ģʽ");
				ControlUtils.control(ConstantUtil.RFID_Open_Door,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}
			if (ra_night_mode.isChecked()) {
				// ҹ��ģʽ
				System.out.println("ҹ��ģʽ");
				ControlUtils.control(ConstantUtil.Lamp,
						ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
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
	//ˢ�½���
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}