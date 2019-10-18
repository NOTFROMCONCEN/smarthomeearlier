package com.example.guosaicdemo923;

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
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-23
 */
public class LinkActivity extends Fragment {
	private Spinner sp_1, sp_2;
	private EditText et_number_getnumber;// ��ȡ����
	private EditText et_number_gettime;// ��ȡʱ��
	private Button btn_open;// ��������
	private Switch sw_warm;// ������
	private Switch sw_fan;// ����
	private Switch sw_lamp;// ���
	private Switch sw_door;// �Ž�
	private TextView tv_link_time;// ʱ��
	private TextView tv_link_retime;// ����ʱ
	private CountDownTimer timers;
	private boolean link_state = false;
	private long num, min, sec;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_getnumber = (EditText) view.findViewById(R.id.et_number_get);
		et_number_gettime = (EditText) view.findViewById(R.id.et_time_get);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		btn_open = (Button) view.findViewById(R.id.btn_start);
		tv_link_retime = (TextView) view.findViewById(R.id.tv_link_time);
		tv_link_time = (TextView) view.findViewById(R.id.tv_link_get_time);
		// �������
		handler.post(timeRunnable);
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

		// ����ʱ��ʼ��ť
		btn_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (timers != null) {
					timers.cancel();
				}
				if (et_number_gettime.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "������ʱ��");
				} else if (et_number_getnumber.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
				} else {
					if (btn_open.getText().toString().equals("��������")) {
						String spinner1 = sp_1.getSelectedItem().toString();
						String spinner2 = sp_2.getSelectedItem().toString();
						int numebr_get = Integer.valueOf(et_number_getnumber
								.getText().toString());
						if (spinner1.equals("�¶�")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.temp > numebr_get) {
									link_state = true;
									btn_open.setText("�ر�����");
								} else {
									link_state = false;
									btn_open.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
							if (spinner2.equals("<")) {
								if (BaseActivity.temp < numebr_get) {
									link_state = true;
									btn_open.setText("�ر�����");
								} else {
									link_state = false;
									btn_open.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
						}
						if (spinner1.equals("ʪ��")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.hum > numebr_get) {
									link_state = true;
									btn_open.setText("�ر�����");
								} else {
									link_state = false;
									btn_open.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
							if (spinner2.equals("<")) {
								if (BaseActivity.hum < numebr_get) {
									link_state = true;
									btn_open.setText("�ر�����");
								} else {
									link_state = false;
									btn_open.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
						}
						if (spinner1.equals("����")) {
							if (spinner2.equals(">")) {
								if (BaseActivity.ill > numebr_get) {
									link_state = true;
									btn_open.setText("�ر�����");
								} else {
									link_state = false;
									btn_open.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
							if (spinner2.equals("<")) {
								if (BaseActivity.ill < numebr_get) {
									link_state = true;
									btn_open.setText("�ر�����");
								} else {
									link_state = false;
									btn_open.setText("��������");
									DiyToast.showToast(getActivity(), "����������");
								}
							}
						}
					} else if (btn_open.getText().toString().equals("�ر�����")) {
						timers.cancel();
						tv_link_retime.setText("����ģʽ����X��X��");
						link_state = false;
						btn_open.setText("��������");
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
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
					if (link_state) {
						num = Integer.parseInt(et_number_gettime.getText()
								.toString()) * 60 * 1000;
						timers = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								min = millisUntilFinished / 1000 / 60;
								sec = millisUntilFinished / 1000 % 60;
								tv_link_retime.setText("����ģʽ����" + min + "��"
										+ sec + "��");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								link_state = false;
								tv_link_retime.setText("����ģʽ����X��X��");
								btn_open.setText("��������");
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
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����9:05:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ʱ��
			tv_link_time.setText(LoginActivity.get_time);
			handler.postDelayed(timeRunnable, 500);
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