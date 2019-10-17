package com.example.guosaiidemo927;

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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;

/*
 * @�ļ�����LinkActivity.java
 * @����������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-27
 */
public class LinkActivity extends Fragment {
	private Button btn_show_chose;// ִ��ѡ��������ť
	private CheckBox cb_link_state;// ����ģʽ
	private Spinner sp_1, sp_2;
	private EditText et_number_get;// ��ȡ����
	public static boolean cur_state;// ����
	public static boolean warm_state;// ������
	public static boolean lamp_state;// ���
	public static boolean fan_state;// ������

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		initView(view);
		// ѡ���豸
		btn_show_chose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_Dialog();
			}
		});
		// ��������
		cb_link_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (et_number_get.getText().toString().isEmpty()) {
						DiyToast.showToast(getActivity(), "��������ֵ");
						cb_link_state.setChecked(false);
					}
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
	 * @�� �ܣ���������
	 * 
	 * @ʱ �䣺����4:22:42
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_link_state.isChecked()) {
				if (et_number_get.getText().toString().isEmpty()) {
					DiyToast.showToast(getActivity(), "��������ֵ");
					cb_link_state.setChecked(false);
				} else {
					String spinner1 = sp_1.getSelectedItem().toString();
					String spinner2 = sp_2.getSelectedItem().toString();
					int number_get = Integer.valueOf(et_number_get.getText()
							.toString());
					if (spinner1.equals("�¶�")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.temp > number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// �����
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ��������
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_state.setChecked(false);
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.temp < number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// �����
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ��������
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_state.setChecked(false);
							}
						}
					}
					if (spinner1.equals("ʪ��")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.hum > number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// �����
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ��������
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_state.setChecked(false);
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.hum < number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// �����
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ��������
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_state.setChecked(false);
							}
						}
					}
					if (spinner1.equals("��ѹ")) {
						if (spinner2.equals(">")) {
							if (BaseActivity.press > number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// �����
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ��������
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_state.setChecked(false);
							}
						}
						if (spinner2.equals("<")) {
							if (BaseActivity.press < number_get) {
								if (cur_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (fan_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ������
								}
								if (lamp_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// �����
								}
								if (warm_state) {
									ControlUtils.control(ConstantUtil.Curtain,
											ConstantUtil.CHANNEL_1,
											ConstantUtil.OPEN);// ��������
								}
							} else {
								DiyToast.showToast(getActivity(), "����������");
								cb_link_state.setChecked(false);
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
	 * @ʱ �䣺����4:21:33
	 */
	private void initView(View view) {
		btn_show_chose = (Button) view.findViewById(R.id.btn_link_chose);
		cb_link_state = (CheckBox) view.findViewById(R.id.cb_link_state);
		sp_1 = (Spinner) view.findViewById(R.id.spinner1);
		sp_2 = (Spinner) view.findViewById(R.id.spinner2);
		et_number_get = (EditText) view.findViewById(R.id.et_number_get);
	}

	/*
	 * @��������show_Dialog
	 * 
	 * @�� �ܣ���ʾ�Ի���
	 * 
	 * @ʱ �䣺����4:21:15
	 */
	private void show_Dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layout = LayoutInflater.from(getActivity());
		View view = layout.inflate(R.layout.activity_link_check, null, false);
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
}
