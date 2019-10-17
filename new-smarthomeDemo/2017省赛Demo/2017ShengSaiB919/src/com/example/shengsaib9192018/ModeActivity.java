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
 * @文件名：ModeActivity.java
 * @描述：模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class ModeActivity extends Fragment {
	private RadioButton ra_day_mode;// 白天模式
	private RadioButton ra_night_mode;// 夜晚模式
	private RadioButton ra_dance_mode;// 歌舞模式
	private RadioButton ra_fangdao_mode;// 防盗模式
	private ToggleButton tg_mode_state;// 按钮
	private int number = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：模式
	 * 
	 * @时 间：上午9:41:49
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				if (ra_dance_mode.isChecked()) {
					// 歌舞模式
					// 空调开，两射灯每5秒切换亮与灭的状态；
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,
							ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);// 开空调
					if (msg.what % 2 == 0) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开射灯
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开射灯
					}
				}
				if (ra_dance_mode.isChecked()) {
					// 白天模式
					// 射灯全关，如果光照值大于200则换气扇开，否则关闭换气扇；
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开射灯
					if (BaseActivity.ill > 200) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开射灯
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 开射灯
					}
				}
				if (ra_dance_mode.isChecked()) {
					// 夜晚模式
					// 打开一个射灯，如果烟雾值大于500时则换气扇开，否则关闭换气扇；
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开射灯
					if (BaseActivity.smo > 500) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开风扇
					} else {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 关风扇
					}
				}
				if (ra_dance_mode.isChecked()) {
					// 防盗模式
					// 如果人体红外感应出有人，则报警灯开，射灯全开，否则关闭报警灯和射灯。
					if (BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 报警灯
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);// 开射灯
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 关射灯
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);// 关报警灯
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