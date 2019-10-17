package com.example.shengsaiddemo2017921;

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
 * @������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-21
 */
public class ModeActivity extends Fragment {
	private RadioButton ra_night_mode;// ҹ��ģʽ
	private RadioButton ra_day_mode;// ����ģʽ
	private RadioButton ra_dance_mode;// ����ģʽ
	private RadioButton ra_fangdao_mode;// ����ģʽ
	private ToggleButton tg_mode_state;// ��ť
	int number = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		ra_dance_mode = (RadioButton) view.findViewById(R.id.ra_dance_mode);
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		// �������
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�ģʽ
	 * 
	 * @ʱ �䣺����3:57:55
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				// �򿪿���
				if (ra_dance_mode.isChecked()) {
					// ����ģʽ
					// �յ������������5���Ƶ�ʽ�����˸
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					if (msg.what % 5 == 0) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					if (ra_day_mode.isChecked()) {
						// ����ģʽ
						// ���ȫ�أ������������PM2.5����75�����ȿ�
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						if (BaseActivity.pm > 75) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					}
					if (ra_night_mode.isChecked()) {
						// ҹ��ģʽ
						// �����أ����CO2Ũ�ȴ���5�����ȿ����������10�����ȫ��
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						if (BaseActivity.co > 5) {
							ControlUtils
									.control(ConstantUtil.Fan,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (BaseActivity.co > 10) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
					}
					if (ra_fangdao_mode.isChecked()) {
						// ����ģʽ
						// �����������Ӧ�����ˣ��򱨾��ƿ������ȫ����������
						if (BaseActivity.per == 1) {
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						} else {
							ControlUtils.control(ConstantUtil.WarningLight,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
							ControlUtils.control(ConstantUtil.Lamp,
									ConstantUtil.CHANNEL_ALL,
									ConstantUtil.CLOSE);
							ControlUtils.control(ConstantUtil.Curtain,
									ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
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
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}