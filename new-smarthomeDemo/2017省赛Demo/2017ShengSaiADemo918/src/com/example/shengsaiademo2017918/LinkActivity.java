package com.example.shengsaiademo2017918;

import java.text.DecimalFormat;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

/*
 * @�ļ�����LinkActivity.java
 * @����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-18
 */
public class LinkActivity extends Fragment {
	private String spinner1, spinner2;
	private boolean link_state;
	private Switch sw_warm;// ������
	private Switch sw_fan;// ����
	private Switch sw_lamp;// ���
	private Switch sw_door;// �Ž�
	private CountDownTimer timer;// ����ʱ
	private Spinner sp_1, sp_2;// spinner
	private EditText et_number;// ��ֵ
	private EditText et_time;// ʱ��
	int number_get = 0, num = 0;
	private Button btn_open;// ��ť����
	private TextView tv_time;// ʱ��
	private TextView tv_time_retime;// ����ʱ
	private long min, sec;// ���ӡ���

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		tv_time = (TextView) view.findViewById(R.id.tv_time_link_1);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		btn_open = (Button) view.findViewById(R.id.btn_start);
		sw_door = (Switch) view.findViewById(R.id.sw_door);
		sw_fan = (Switch) view.findViewById(R.id.sw_fan);
		sw_lamp = (Switch) view.findViewById(R.id.sw_lamp);
		sw_warm = (Switch) view.findViewById(R.id.sw_warm);
		tv_time_retime = (TextView) view.findViewById(R.id.tv_link_time);
		et_number = (EditText) view.findViewById(R.id.et_number_get);
		et_time = (EditText) view.findViewById(R.id.et_time_get);
		// �豸����
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
		sw_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (link_state) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
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
		// ���ÿ���
		btn_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				spinner1 = sp_1.getSelectedItem().toString();
				spinner2 = sp_2.getSelectedItem().toString();
				number_get = Integer.valueOf(et_number.getText().toString());
				if (btn_open.getText().toString().equals("��������")) {
					if (timer != null) {
						timer.cancel();
					}
					link_state = true;
					System.out.println(BaseActivity.temp);
					System.out.println("1");
					// if (sp_1.equals("�¶�") && sp_2.equals(">")) {
					// if (BaseActivity.temp > number_get) {
					// System.out.println("open");
					// link_state = true;
					// btn_open.setText("ֹͣ����");
					// }
					// }
					// if (sp_1.equals("�¶�") && sp_2.equals("<=")) {
					// if (BaseActivity.temp < number_get) {
					// link_state = true;
					// btn_open.setText("ֹͣ����");
					// }
					// }
					// if (sp_1.equals("ʪ��") && sp_2.equals(">")) {
					// if (BaseActivity.hum > number_get) {
					// link_state = true;
					// btn_open.setText("ֹͣ����");
					// }
					// }
					// if (sp_1.equals("ʪ��") && sp_2.equals("<=")) {
					// if (BaseActivity.hum < number_get) {
					// link_state = true;
					// btn_open.setText("ֹͣ����");
					// }
					// }
					// if (sp_1.equals("����") && sp_2.equals(">")) {
					// if (BaseActivity.ill > number_get) {
					// link_state = true;
					// btn_open.setText("ֹͣ����");
					// }
					// }
					// if (sp_1.equals("����") && sp_2.equals("<=")) {
					// if (BaseActivity.ill < number_get) {
					// link_state = true;
					// btn_open.setText("ֹͣ����");
					// }
					// }
					if (link_state) {
						// ��ʱ�������ֵ���ڷ���
						num = Integer.parseInt(et_time.getText().toString()) * 60 * 1000;
						timer = new CountDownTimer(num, 1000) {

							@Override
							public void onTick(long millisUntilFinished) {
								// TODO Auto-generated method stub
								// ��
								min = millisUntilFinished / 1000 / 60;
								// ��
								sec = millisUntilFinished / 1000 % 60;
								// �����ı�
								tv_time_retime.setText("����ģʽ����" + min + "��"
										+ sec + "��");
								btn_open.setText("�ر�����");
							}

							@Override
							public void onFinish() {
								// TODO Auto-generated method stub
								link_state = false;
								btn_open.setText("��������");
								tv_time_retime.setText("����ģʽ����X��X��");
							}
						}.start();// ��������ʱ
					}
				} else if (btn_open.getText().toString().equals("ֹͣ����")) {
					link_state = false;
					btn_open.setText("��������");
					tv_time_retime.setText("����ģʽ����X��X��");
					timer.cancel();
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
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����4:20:59
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_time.setText(BarActivity.time);
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
