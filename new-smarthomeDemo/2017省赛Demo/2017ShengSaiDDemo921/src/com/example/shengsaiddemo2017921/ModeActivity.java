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
 * @文件名：ModeActivity.java
 * @描述：模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-21
 */
public class ModeActivity extends Fragment {
	private RadioButton ra_night_mode;// 夜晚模式
	private RadioButton ra_day_mode;// 白天模式
	private RadioButton ra_dance_mode;// 歌舞模式
	private RadioButton ra_fangdao_mode;// 防盗模式
	private ToggleButton tg_mode_state;// 按钮
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
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：模式
	 * 
	 * @时 间：下午3:57:55
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				// 打开开关
				if (ra_dance_mode.isChecked()) {
					// 歌舞模式
					// 空调开，两射灯以5秒的频率交替闪烁
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
						// 白天模式
						// 射灯全关，窗帘开，如果PM2.5大于75则换气扇开
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
						// 夜晚模式
						// 窗帘关，如果CO2浓度大于5则换气扇开，如果大于10则射灯全亮
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
						// 防盗模式
						// 如果人体红外感应出有人，则报警灯开，射灯全开，窗帘开
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