package com.example.guosaiidemo927;

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
import android.widget.RadioButton;
import android.widget.ToggleButton;

/*
 * @文件名：ModeActivity.java
 * @描述：模式控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-14
 */
public class ModeActivity extends Fragment {
	private RadioButton ra_day_mode;// 白天模式
	private RadioButton ra_night_mode;// 夜晚模式
	private RadioButton ra_fangdao_mode;// 防盗模式
	private ToggleButton tg_mode_state;// 按钮

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		ra_day_mode = (RadioButton) view.findViewById(R.id.ra_day_mode);
		ra_fangdao_mode = (RadioButton) view.findViewById(R.id.ra_fangdao_mode);
		ra_night_mode = (RadioButton) view.findViewById(R.id.ra_night_mode);
		tg_mode_state = (ToggleButton) view.findViewById(R.id.tg_mode_start);
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				System.err.println(tg_mode_state);
				if (ra_day_mode.isChecked()) {
					// 白天模式
					// 射灯全关，如果光照值大于150则窗帘开，否则就关闭窗帘；
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					System.out.println("open");
					if (BaseActivity.ill > 150) {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Curtain,
								ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}
				}
				if (ra_fangdao_mode.isChecked()) {
					// 防盗模式
					// 如果人体红外感应出有人，则报警灯开，射灯全开，否则关闭报警灯和射灯。
					if (BaseActivity.per == 1) {
						System.err.println("有人");
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
				if (ra_night_mode.isChecked()) {
					// 夜晚模式
					// 打开射灯，关闭窗帘，如果光照度小于50时则换气扇开，否则就关闭换气扇；
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					if (BaseActivity.ill < 50) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
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
