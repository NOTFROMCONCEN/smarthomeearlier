package com.example.guosaicdemo905;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

/*
 * @�ļ�����LinkActivity.java
 * @�������豸����������ʱ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-4
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2;
	private EditText et_number, et_time;
	private Button btn_link_start;
	private Switch sw_warm, sw_fan, sw_lamp, sw_door;
	private TextView tv_link_time, tv_link_get_time;
	private long num, min, sec;
	private CountDownTimer timer;
	private boolean link_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		btn_link_start = (Button) view.findViewById(R.id.btn_start);
		tv_link_get_time = (TextView) view.findViewById(R.id.tv_link_get_time);
		et_number = (EditText) view.findViewById(R.id.et_number_get);
		et_time = (EditText) view.findViewById(R.id.et_time_get);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		tv_link_time = (TextView) view.findViewById(R.id.tv_link_time);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		// �Ž�
		sw_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {

					}
				}
			}
		});
		// ������
		sw_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
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
		// ����
		sw_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
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
		// ���
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				} else {
					DiyToast.showToast(getActivity(), "�뿪������");
					sw_lamp.setChecked(false);
				}
			}
		});

		btn_link_start.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timer != null) {
					timer.cancel();
				}
				if (et_number.getText().toString().equals("")) {
					DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
				} else if (et_time.getText().toString().equals("")) {
					DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
				} else {
					if (btn_link_start.getText().toString().equals("��������")) {
						String spinner1, spinner2;
						spinner1 = sp_1.getSelectedItem().toString();
						spinner2 = sp_2.getSelectedItem().toString();
						int get_number;
						get_number = Integer.valueOf(et_number.getText()
								.toString());
						if (spinner1.equals("�¶�")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.temp > get_number) {
									link_state = true;// ����
									btn_link_start.setText("�ر�����");
								} else {
									link_state = true;// ȡ������
									btn_link_start.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
							if (spinner2.equals("<=")) {

							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.temp < get_number) {
									link_state = true;// ����
									btn_link_start.setText("�ر�����");
								} else {
									link_state = true;// ȡ������
									btn_link_start.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
						}
						if (spinner1.equals("ʪ��")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.hum > get_number) {
									link_state = true;// ����
									btn_link_start.setText("�ر�����");
								} else {
									link_state = true;// ȡ������
									btn_link_start.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.hum < get_number) {
									link_state = true;// ����
									btn_link_start.setText("�ر�����");
								} else {
									link_state = true;// ȡ������
									btn_link_start.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
						}
						if (spinner1.equals("����")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.ill > get_number) {
									link_state = true;// ����
									btn_link_start.setText("�ر�����");
								} else {
									link_state = true;// ȡ������
									btn_link_start.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
							if (spinner2.equals("<=")) {
								if (BaseActivity.ill < get_number) {
									link_state = true;// ����
									btn_link_start.setText("�ر�����");
								} else {
									link_state = true;// ȡ������
									btn_link_start.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
						}
					} else if (btn_link_start.getText().toString()
							.equals("�ر�����")) {
						timer.cancel();
						tv_link_time.setText("����ģʽ����X��X��");
						link_state = false;
						btn_link_start.setText("��������");
					}
					// ����ʱ
					if (link_state) {
						num = Integer.parseInt(et_time.getText().toString()) * 60 * 1000;
						timer = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								// TODO Auto-generated method stub
								// ��
								min = millisUntilFinished / 1000 / 60;
								// ��
								sec = millisUntilFinished / 1000 % 60;
								// �����ı�
								tv_link_time.setText("����ģʽ����" + min + "��" + sec
										+ "��");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								link_state = false;
								btn_link_start.setText("��������");
								tv_link_time.setText("����ģʽ����X��X��");
								// ��ʱ��Ϻ�ر����б��򿪵��豸�Ϳ���
								if (sw_door.isChecked()) {
									sw_door.setChecked(false);
								}
								if (sw_fan.isChecked()) {
									sw_fan.setChecked(false);
									ControlUtils.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sw_lamp.isChecked()) {
									sw_lamp.setChecked(false);
									ControlUtils.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
								if (sw_warm.isChecked()) {
									sw_warm.setChecked(false);
									ControlUtils.control(
											ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.CLOSE);
								}
							}
						}.start();
					}
				}
			}
		});
		// �������
		handler.post(timeRunnable);
		return view;
	}

	// �趨ʱ�����
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_link_get_time.setText(BarActivity.bar_time);
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
