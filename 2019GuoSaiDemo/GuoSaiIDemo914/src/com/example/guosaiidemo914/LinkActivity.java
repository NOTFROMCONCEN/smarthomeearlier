package com.example.guosaiidemo914;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;

/*
 * @�ļ�����LinkActivity.java
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-14
 */
public class LinkActivity extends Fragment {
	private Button btn_link_chose;
	private Spinner sp_1, sp_2;
	private CheckBox cb_link_state;
	private EditText et_number_get;
	public static boolean link_state = false;
	public static boolean fan_state = false;
	public static boolean lamp_state = false;
	public static boolean cur_state = false;
	public static boolean warm_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		btn_link_chose = (Button) view.findViewById(R.id.btn_link_chose);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		cb_link_state = (CheckBox) view.findViewById(R.id.cb_link_state);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
		btn_link_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(
						getActivity());
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				View view = layoutInflater.inflate(
						R.layout.activity_link_check, null, false);
				builder.setView(view);
				final CheckBox cb_fan, cb_warm, cb_lamp, cb_cur;
				cb_cur = (CheckBox) view.findViewById(R.id.cb_link_cur);
				cb_fan = (CheckBox) view.findViewById(R.id.cb_link_fan);
				cb_lamp = (CheckBox) view.findViewById(R.id.cb_link_lamp);
				cb_warm = (CheckBox) view.findViewById(R.id.cb_link_warm);
				cb_cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							cur_state = true;
						} else {
							cur_state = false;
						}
					}
				});
				cb_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							fan_state = true;
						} else {
							fan_state = false;
						}
					}
				});
				cb_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							lamp_state = true;
						} else {
							lamp_state = false;
						}
					}
				});
				cb_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							warm_state = true;
						} else {
							warm_state = false;
						}
					}
				});
				if (cur_state) {
					cb_cur.setChecked(true);
				}
				if (fan_state) {
					cb_fan.setChecked(true);
				}
				if (lamp_state) {
					cb_lamp.setChecked(true);
				}
				if (warm_state) {
					cb_warm.setChecked(true);
				}
				builder.show();
			}
		});
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "��ֵ����Ϊ��");
						link_state = false;
						cb_link_state.setChecked(false);
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
				String spinner1 = sp_1.getSelectedItem().toString();
				String spinner2 = sp_2.getSelectedItem().toString();
				int number_get = Integer.valueOf(et_number_get.getText()
						.toString());
				if (spinner1.equals("�¶�")) {
					if (spinner2.equals("����")) {
						if (BaseActivity.temp > number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}
					if (spinner2.equals("С��")) {
						if (BaseActivity.temp < number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}
				}
				if (spinner1.equals("ʪ��")) {

					if (spinner2.equals("����")) {
						if (BaseActivity.hum > number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}
					if (spinner2.equals("С��")) {
						if (BaseActivity.hum < number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}

				}
				if (spinner1.equals("����")) {

					if (spinner2.equals("����")) {
						if (BaseActivity.ill > number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}
					if (spinner2.equals("С��")) {
						if (BaseActivity.ill < number_get) {
							if (cur_state) {
								System.out.println("������");
ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}

				}
				if (spinner1.equals("��ѹ")) {

					if (spinner2.equals("����")) {
						if (BaseActivity.press > number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
							}
						}
					}
					if (spinner2.equals("С��")) {
						if (BaseActivity.press < number_get) {
							if (cur_state) {
								System.out.println("������");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (fan_state) {
								System.out.println("���ȿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ������
							}
							if (lamp_state) {
								System.out.println("��ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// �����
							}
							if (warm_state) {
								System.out.println("�����ƿ�");
								ControlUtils.control(ConstantUtil.Curtain,
										ConstantUtil.CHANNEL_1,
										ConstantUtil.OPEN);// ��������
							}
						} else {
							DiyToast.showToast(getActivity(), "����������");
							cb_link_state.setChecked(false);
							link_state = false;
							if (cur_state) {
								cur_state = false;
							}
							if (fan_state) {
								fan_state = false;
							}
							if (lamp_state) {
								lamp_state = false;
							}
							if (warm_state) {
								warm_state = false;
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
			Message msgMessage = handler.obtainMessage();
			handler.sendMessage(msgMessage);
		}
	};
}
