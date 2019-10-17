package com.example.guosaicdemo905;

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
 * @���������ģʽ���ƹ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-31
 */
public class ModeActivity extends Fragment {
	private TextView tv_mode_time;// ʱ��
	private ToggleButton tg_draw_state;// ����
	private RadioButton ra_night_mode, ra_day_mode, ra_lijia_mode,
			ra_fangdao_mode;// ҹ�����졢��ҡ�����ģʽ
	private int recLen = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		tv_mode_time = (TextView) view.findViewById(R.id.tv_mode_time);
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_lijia_mode = (RadioButton) view.findViewById(R.id.ra_lijia_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		tg_draw_state = (ToggleButton) view.findViewById(R.id.tg_mode_start);
		// �������
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_draw_state.isChecked()) {
				if (ra_day_mode.isChecked()) {
					// ���ȫ�أ�������������������400�����ȿ���
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					if (BaseActivity.smo > 400) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
				if (ra_fangdao_mode.isChecked()) {
					// �����������Ӧ�����ˣ��򱨾��ƿ������ȫ����
					if (BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
				if (ra_lijia_mode.isChecked()) {
					// ����ģʽ�£��յ���������ƴ�Լ2�����һ��ȫ����ȫ�صĽ�����˸��
					if (msg.what % 2 == 0) {// ��
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {// ��
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
				if (ra_night_mode.isChecked()) {
					// ҹ��ģʽ�£������أ��������С��200����һ����ơ��������500�����ȫ�أ�
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					if (BaseActivity.ill < 200) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (BaseActivity.ill > 500) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
			// ����ʱ��
			tv_mode_time.setText(BarActivity.bar_time);
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen++;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			handler.sendMessage(msg);
		}
	};

}
