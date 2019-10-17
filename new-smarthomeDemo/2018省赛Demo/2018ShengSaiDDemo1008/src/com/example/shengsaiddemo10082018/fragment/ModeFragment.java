package com.example.shengsaiddemo10082018.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaiddemo10082018.R;
import com.example.shengsaiddemo10082018.timepicker.TimeGetClass;

/*
 * @�ļ�����ModeActivity.java
 * @������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class ModeFragment extends Fragment {
	private TextView tv_mode_time;
	private RadioButton ra_day_mode;// ����ģʽ
	private RadioButton ra_night_mode;// ҹ��ģʽ
	private RadioButton ra_dance_mode;// ����ģʽ
	private RadioButton ra_fandgao_mode;// ����ģʽ
	private ToggleButton tg_mode_state;// ģʽ����
	private int number = 0;
	private int recLen = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_mode, container, false);
		tv_mode_time = (TextView) view.findViewById(R.id.tv_mode_time);
		ra_fandgao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_dance_mode = (RadioButton) view.findViewById(R.id.ra_dance_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_state);
		// �������
		handler.post(timeRunnable);
		/**
		 * ���ü���
		 */
		ra_night_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				recLen = 0;
			}
		});
		ra_day_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				recLen = 0;
			}
		});
		ra_fandgao_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				recLen = 0;
			}
		});
		ra_dance_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				recLen = 0;
			}
		});
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ�䡢ģʽ����
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
					recLen++;
					switch (recLen) {
					case 2:
						System.out.println("2");
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						break;
					case 4:
						System.out.println("4");
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						break;
					default:
						break;
					}
					if (BaseFragment.ill > 200) {
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
					if (BaseFragment.per == 1) {
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
					recLen++;
					switch (recLen) {
					case 2:
						System.out.println("2");
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						break;
					case 4:
						System.out.println("4");
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
						break;
					default:
						break;
					}
					if (BaseFragment.smo > 230) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
			tv_mode_time.setText(TimeGetClass.Time);
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
