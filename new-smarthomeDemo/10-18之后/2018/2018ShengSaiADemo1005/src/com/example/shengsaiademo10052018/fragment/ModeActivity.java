package com.example.shengsaiademo10052018.fragment;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaiademo10052018.R;

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
 * @�ļ�����SettingActivity.java
 * @������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-15
 */
public class ModeActivity extends Fragment {
	private CheckBox cb_dance_mode;// ����ģʽ
	private CheckBox cb_fangdao_mode;// ����ģʽ
	private CheckBox cb_day_mode;// ����ģʽ
	private int number = 0;
	private int recLen = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		cb_dance_mode = (CheckBox) view.findViewById(R.id.cb_dance_mode);
		cb_day_mode = (CheckBox) view.findViewById(R.id.cb_day_mode);
		cb_fangdao_mode = (CheckBox) view.findViewById(R.id.cb_fangdao_mode);
		// ����ģʽ��ѡ�У�ȡ��ѡ����������ģʽ
		cb_dance_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					// cb_dance_mode.setChecked(false);
					cb_day_mode.setChecked(false);
					cb_fangdao_mode.setChecked(false);
					recLen = 0;
				}
			}
		});
		// ����ģʽ��ѡ�У�ȡ��ѡ����������ģʽ
		cb_day_mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_dance_mode.setChecked(false);
					// cb_day_mode.setChecked(false);
					cb_fangdao_mode.setChecked(false);
					recLen = 0;
				}
			}
		});
		// ����ģʽ��ѡ�У�ȡ��ѡ����������ģʽ
		cb_fangdao_mode
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							cb_dance_mode.setChecked(false);
							cb_day_mode.setChecked(false);
							// cb_fangdao_mode.setChecked(false);
							recLen = 0;
						}
					}
				});
		// �������
		handler.post(tiemRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_dance_mode.isChecked()) {
				// ����ģʽ
				// ���յ��������
				recLen++;
				if (recLen == 2) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
				}
				if (msg.what % 2 == 0) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				} else {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (cb_day_mode.isChecked()) {
				// ����ģʽ
				// ���ȫ�أ���������pm����75�����ȿ�
				recLen++;
				if (recLen == 2) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				if (recLen == 4) {
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
				if (BaseActivity.pm > 75) {
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}
			}
			if (cb_fangdao_mode.isChecked()) {
				// ����ģʽ
				// ���˿���
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
			}
			handler.postDelayed(tiemRunnable, 1000);
		}
	};
	Runnable tiemRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stu
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}
