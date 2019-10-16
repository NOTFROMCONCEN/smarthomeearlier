package com.example.shengsaib9192018;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.ToggleButton;

/*
 * @�ļ�����ModeActivity.java
 * @������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class ModeActivity extends Fragment {
	private RadioButton ra_day_mode;// ����ģʽ
	private RadioButton ra_night_mode;// ҹ��ģʽ
	private RadioButton ra_dance_mode;// ����ģʽ
	private RadioButton ra_fangdao_mode;// ����ģʽ
	private ToggleButton tg_mode_state;// ��ť
	private int number = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�ģʽ
	 * 
	 * @ʱ �䣺����9:41:49
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				if (ra_dance_mode.isChecked()) {
					// ����ģʽ
					// �յ����������ÿ5���л��������״̬��
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);// ���յ�
					if (msg.what % 2 == 0) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����
					}
				}
				if (ra_dance_mode.isChecked()) {
					// ����ģʽ
					// ���ȫ�أ��������ֵ����200�����ȿ�������رջ����ȣ�
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����
					if (BaseActivity.ill > 200) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// �����
					}
				}
				if (ra_dance_mode.isChecked()) {
					// ҹ��ģʽ
					// ��һ����ƣ��������ֵ����500ʱ�����ȿ�������رջ����ȣ�
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����
					if (BaseActivity.smo > 500) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// ������
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// �ط���
					}
				}
				if (ra_dance_mode.isChecked()) {
					// ����ģʽ
					// �����������Ӧ�����ˣ��򱨾��ƿ������ȫ��������رձ����ƺ���ơ�
					if (BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// ������
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// �����
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// �����
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// �ر�����
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
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}