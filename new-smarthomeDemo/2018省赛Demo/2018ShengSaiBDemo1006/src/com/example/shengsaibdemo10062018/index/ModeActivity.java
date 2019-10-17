package com.example.shengsaibdemo10062018.index;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.example.shengsaibdemo10062018.R;

/*
 * @�ļ�����ModeActivity.java
 * @������ģʽ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-6
 */
public class ModeActivity extends Activity {
	private ToggleButton tg_mode_state;
	private RadioButton ra_anfang_mode;// ����ģʽ
	private RadioButton ra_lijia_mode;// ���ģʽ
	private RadioButton ra_diy_mode;// �Զ���ģʽ
	int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode);
		setTitle("ModeActivity");
		ra_anfang_mode = (RadioButton) findViewById(R.id.ra_anfang_mode);
		ra_diy_mode = (RadioButton) findViewById(R.id.ra_diy_mode);
		ra_lijia_mode = (RadioButton) findViewById(R.id.ra_lijia_mode);
		tg_mode_state = (ToggleButton) findViewById(R.id.tg_mode_state);
		ra_anfang_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		ra_diy_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		ra_lijia_mode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				number = 0;
			}
		});
		// ����
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				// ���ش�
				if (ra_anfang_mode.isChecked()) {
					// ����ģʽ
					// �������Ž��������Щ�豸���ء�
					number++;
					if (number == 1) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
					}
					if (number == 2) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					if (number == 3) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
				if (ra_diy_mode.isChecked()) {
					// �Զ���ģʽ

				}
				if (ra_lijia_mode.isChecked()) {
					// ���ģʽ
					// �����������Ӧ�����ˣ��򱨾��ƿ�����ƿ����Ž��ء�
					if (BaseActivity.per == 1) {
						number++;
						if (number == 1) {
							ControlUtils
									.control(ConstantUtil.WarningLight,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (number == 2) {
							ControlUtils
									.control(ConstantUtil.Lamp,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
						}
						if (number == 3) {
							ControlUtils
									.control(ConstantUtil.RFID_Open_Door,
											ConstantUtil.CHANNEL_ALL,
											ConstantUtil.OPEN);
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