package com.example.shengsaiddemo9162018;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

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
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @�ļ�����ModeActivity.java
 * @������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-16
 */
public class ModeActivity extends Fragment {
	private TextView tv_mode_time;
	private RadioButton ra_day_mode;// ����ģʽ
	private RadioButton ra_night_mode;// ҹ��ģʽ
	private RadioButton ra_dance_mode;// ����ģʽ
	private RadioButton ra_fandgao_mode;// ����ģʽ
	private ToggleButton tg_mode_state;// ģʽ����
	private int number = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		tv_mode_time = (TextView) view.findViewById(R.id.tv_mode_time);
		ra_fandgao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_dance_mode = (RadioButton) view.findViewById(R.id.ra_dance_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		// �������
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ�䡢ģʽ����
	 * 
	 * @ʱ �䣺����4:07:30
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				if (ra_dance_mode.isChecked()) {
					// ����ģʽ
					// �յ������������ÿ 5 ���л���ƵĿ����״̬��
					if (msg.what % 5 == 0) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
				if (ra_day_mode.isChecked()) {
					// ����ģʽ
					// ���ȫ�أ����������������ֵ���� 200 ʱ�����ȿ�������رջ����ȡ�
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					if (BaseActivity.ill > 200) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
				if (ra_fandgao_mode.isChecked()) {
					// ����ģʽ
					// �����������Ӧ�����ˣ��򱨾��ƿ������ȫ���������ʱ���ذ�ť��Ϊ OFF �򱨾��ƹ�
					if (BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					if (tg_mode_state.isChecked() == false) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
				if (ra_night_mode.isChecked()) {
					// ҹ��ģʽ
					// �����أ����ȫ�����������ֵŨ�ȴ��� 230 �����ȿ�������رջ����ȡ�
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					if (BaseActivity.smo > 230) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy��MM��dd�� HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_mode_time.setText(simpleDateFormat.format(new java.util.Date()));
			handler.postDelayed(timeRunnable, 500);
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
