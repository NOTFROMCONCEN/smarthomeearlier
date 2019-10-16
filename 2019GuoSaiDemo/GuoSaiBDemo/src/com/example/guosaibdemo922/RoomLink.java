package com.example.guosaibdemo922;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

/*
 * @�ļ�����RoomLink.java
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-22
 */
public class RoomLink extends Activity {
	private TextView tv_link_room_number;
	private Spinner sp_1;// spinner1
	private Spinner sp_2;// spinner2
	private Spinner sp_3;// spinner3
	private Spinner sp_4;// spinner4
	private Switch sw_link_state;// ����
	private EditText et_number_get;// �ı���

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_linkstate);
		tv_link_room_number = (TextView) findViewById(R.id.tv_link_room_number);
		tv_link_room_number.setText("����ţ�"
				+ Index_Room_Activity.NUMBER_FOR_ROOM);
		sw_link_state = (Switch) findViewById(R.id.switch_link_state);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		sp_2 = (Spinner) findViewById(R.id.spinner2);
		sp_3 = (Spinner) findViewById(R.id.spinner3);
		sp_4 = (Spinner) findViewById(R.id.spinner4);
		et_number_get = (EditText) findViewById(R.id.et_number_get);
		sw_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showTasot(getApplicationContext(), "��������ֵ");
						sw_link_state.setChecked(false);
					}
				}
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ���������ʵ��
	 * 
	 * @ʱ �䣺����9:33:18
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (sw_link_state.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()) {
					sw_link_state.setChecked(false);
					DiyToast.showTasot(getApplicationContext(), "��������ֵ");
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					String spinner3 = sp_3.getSelectedItem().toString();
					String spinner4 = sp_4.getSelectedItem().toString();
					int numebr_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("����")) {
						if (spinner2.equals(">")) {
							if (Index_Room_Activity.ill > numebr_get) {
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("���")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"����������");
							}
						}
						if (spinner2.equals("<")) {
							if (Index_Room_Activity.ill < numebr_get) {
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("���")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"����������");
							}
						}
					}

					if (spinner1.equals("�¶�")) {
						if (spinner2.equals(">")) {
							if (Index_Room_Activity.temp > numebr_get) {
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("���")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"����������");
							}
						}
						if (spinner2.equals("<")) {
							if (Index_Room_Activity.temp < numebr_get) {
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("���")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"����������");
							}
						}
					}

					if (spinner1.equals("ʪ��")) {
						if (spinner2.equals(">")) {
							if (Index_Room_Activity.hum > numebr_get) {
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("���")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"����������");
							}
						}
						if (spinner2.equals("<")) {
							if (Index_Room_Activity.hum < numebr_get) {
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Fan,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("���")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(ConstantUtil.Lamp,
												ConstantUtil.CHANNEL_ALL,
												ConstantUtil.CLOSE);
									}
								}
								if (spinner3.equals("����")) {
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_1,
												ConstantUtil.OPEN);
									}
									if (spinner4.equals("��")) {
										ControlUtils.control(
												ConstantUtil.Curtain,
												ConstantUtil.CHANNEL_2,
												ConstantUtil.CLOSE);
									}
								}
							} else {
								sw_link_state.setChecked(false);
								DiyToast.showTasot(getApplicationContext(),
										"����������");
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
}
