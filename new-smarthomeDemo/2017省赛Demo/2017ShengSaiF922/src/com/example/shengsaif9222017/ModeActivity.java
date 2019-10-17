package com.example.shengsaif9222017;

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

/*
 * @�ļ�����ModeActivity.java
 * @������ģʽ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-22
 */
public class ModeActivity extends Fragment {
	private CheckBox cb_sleep;// ˯��ģʽ
	private CheckBox cb_onclass;// �ϰ�ģʽ
	private CheckBox cb_outclass;// �°�ģʽ

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		cb_onclass = (CheckBox) view.findViewById(R.id.cb_onclass);
		cb_outclass = (CheckBox) view.findViewById(R.id.cb_outclass);
		cb_sleep = (CheckBox) view.findViewById(R.id.cb_sleep);
		// ���á�ֻ��ѡ��һ��ģʽ��Ч��
		// �ϰ�ģʽ
		cb_onclass.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// cb_onclass.setChecked(false);
					cb_outclass.setChecked(false);
					cb_sleep.setChecked(false);
				}
			}
		});
		// �°�ģʽ
		cb_outclass.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_onclass.setChecked(false);
					// cb_outclass.setChecked(false);
					cb_sleep.setChecked(false);
				}
			}
		});
		// ˯��ģʽ
		cb_sleep.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_onclass.setChecked(false);
					cb_outclass.setChecked(false);
					// cb_sleep.setChecked(false);
				}
			}
		});
		// �������
		hand.post(timeRunnable);
		return view;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�ģʽ����ʵ��
	 * 
	 * @ʱ �䣺����4:08:09
	 */
	Handler hand = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_onclass.isChecked()) {
				// �ϰ�ģʽ
				// ���������⣬����ʾ����ʱ�����������ƣ�
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_outclass.isChecked()) {
				// �°�ģʽ
				// ��ͨ��һ��ͨ��������ʼ���������⣬����ʾ����ʱ�����¶ȴ���35�ȣ��򿪷��ȣ�
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
						ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				if (BaseActivity.per == 1 && BaseActivity.temp > 35) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_sleep.isChecked()) {
				// ˯��ģʽ
				// ���������������˴�������ʱ�򱨾���û���ˣ��򲻴�����
				// ͬʱ��ʼ������ڹ��նȣ������նȴ���100ʱ��
				// ��ִ�йصƣ��ش������
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				if (BaseActivity.ill > 100) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_2, ConstantUtil.CLOSE);
				}
			}

			hand.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = hand.obtainMessage();
			hand.sendMessage(msg);
		}
	};
}
