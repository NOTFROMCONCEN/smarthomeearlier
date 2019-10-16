package com.example.drawdemo816;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

/*
 * @�ļ�����LinkActivity.java
 * @���������û���ɽ���Ļ������豸������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-16
 */
public class LinkActivity extends Fragment {
	private Spinner spinner1, spinner2;// ������ʾ�˵������������˵�
	private CheckBox check_open;// ���ڼ�������ģʽ�ĸ�ѡ��
	private EditText et_number;// �ı���
	private RadioButton ra_day;// ����ģʽ
	private RadioButton ra_night;// ҹ��ģʽ
	private RadioButton ra_fangdao;// ����ģʽ
	private ToggleButton tg_mode_start;// ģʽ���ܵĿ���
	private boolean link_state = false;// ���ڱȽ������Ƿ񼤻�Ĳ�����ֵ
	private boolean mode_state = false;// ���ڱȽ�ģʽ�Ƿ񼤻�Ĳ�����ֵ
	private int number;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_link, container, false);
		spinner1 = (Spinner) view.findViewById(R.id.spinner1);
		spinner2 = (Spinner) view.findViewById(R.id.spinner2);
		check_open = (CheckBox) view.findViewById(R.id.check_open);
		et_number = (EditText) view.findViewById(R.id.et_number);
		ra_day = (RadioButton) view.findViewById(R.id.ra_day);
		ra_fangdao = (RadioButton) view.findViewById(R.id.ra_fangdao);
		ra_night = (RadioButton) view.findViewById(R.id.ra_night);
		tg_mode_start = (ToggleButton) view.findViewById(R.id.tg_mode_start);
		// ���ÿ��ذ�ť����¼�
		tg_mode_start.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (link_state) {
						Toast.makeText(getActivity(), "���ȹر���������",
								Toast.LENGTH_SHORT).show();
						mode_state = false;
						tg_mode_start.setChecked(false);
					} else {
						mode_state = true;
					}
				} else {
					mode_state = false;
				}
			}
		});
		// ��ѡ��ѡ���¼�
		check_open.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// ����ı����ʽ�Ƿ�Ϊ�գ���������ʾ
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
		// �����߳�
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����ؼ�״̬�ı�Ľ��̣�����������ģʽ
	 * 
	 * @ʱ �䣺����11:00:02
	 */
	// ����ģʽ���ȼ�����ģʽ����
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (link_state) {
				// ����ı����Ƿ�Ϊ��
				if (et_number.getText().toString().equals("")) {
					check_open.setChecked(false);
					link_state = false;
				} else {
					// ����ģʽ����Ⲽ��ֵ�Ƿ�Ϊtrue
					String sp_1 = spinner1.getSelectedItem().toString();// ��ȡ�����˵���ѡ��
					String sp_2 = spinner2.getSelectedItem().toString();// ��ȡ�����˵���ѡ��
					number = Integer.valueOf(et_number.getText().toString());// ��ȡ�ı����������ֵ
					if (sp_1.equals("�¶�")) {
						if (sp_2.equals(">")) {
							// �¶ȣ����ڣ�֮��ȶ��趨��ֵ�ʹ�������ֵ����
							if (BaseActivity.temp > number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// ������������㣬������ʾ���رշ���
								Toast.makeText(getActivity(), "���������㣡",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.equals("<")) {
							// �¶ȣ�С�ڣ�֮��ȶ��趨��ֵ�ʹ�������ֵ����
							if (BaseActivity.temp < number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// ������������㣬������ʾ���رշ���
								Toast.makeText(getActivity(), "���������㣡",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
					}
					if (sp_1.equals("ʪ��")) {
						if (sp_2.equals(">")) {
							// ʪ�ȣ����ڣ�֮��ȶ��趨��ֵ�ʹ�������ֵ����
							if (BaseActivity.hum > number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// ������������㣬������ʾ���رշ���
								Toast.makeText(getActivity(), "���������㣡",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
						if (sp_2.equals("<")) {
							// ʪ�ȣ�С�ڣ�֮��ȶ��趨��ֵ�ʹ�������ֵ����
							if (BaseActivity.hum < number) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							} else {
								// ������������㣬������ʾ���رշ���
								Toast.makeText(getActivity(), "���������㣡",
										Toast.LENGTH_SHORT).show();
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.CLOSE);
								check_open.setChecked(false);
								link_state = false;
							}
						}
					}
				}
			}
			// ģʽ����
			if (mode_state) {
				if (link_state) {
					// �������ģʽ�Ƿ񱻼�������������ر�ģʽ����
					Toast.makeText(getActivity(), "���ȹر���������",
							Toast.LENGTH_SHORT).show();
					mode_state = false;
					tg_mode_start.setChecked(false);
				} else {
					if (ra_day.isChecked()) {// �������ģʽ��ѡ��
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);// ������
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// ��ƹر�
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �򿪷���
					}
					if (ra_fangdao.isChecked()) {// �������ģʽ������
						if (BaseActivity.per == 1) {
							// ������͹�����per��ֵΪ1����Ϊ���ˣ��������
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						} else {
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
						}
					}
					if (ra_night.isChecked()) {// ���ҹ��ģʽ������
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);// ������
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// ��ƹر�
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// �򿪷���
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
