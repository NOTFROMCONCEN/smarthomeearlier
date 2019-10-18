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
 * @文件名：ModeActivity.java
 * @描述：模式功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class ModeActivity extends Fragment {
	private TextView tv_mode_time;
	private RadioButton ra_day_mode;// 白天模式
	private RadioButton ra_night_mode;// 夜晚模式
	private RadioButton ra_dance_mode;// 歌舞模式
	private RadioButton ra_fandgao_mode;// 防盗模式
	private ToggleButton tg_mode_state;// 模式开关
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
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载时间、模式功能
	 * 
	 * @时 间：下午4:07:30
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				if (ra_dance_mode.isChecked()) {
					// 歌舞模式
					// 空调开，两射灯以每 5 秒切换射灯的开与关状态。
					if (msg.what % 5 == 0) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
				if (ra_day_mode.isChecked()) {
					// 白天模式
					// 射灯全关，窗帘开，如果光照值大于 200 时则换气扇开，否则关闭换气扇。
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
					// 防盗模式
					// 如果人体红外感应出有人，则报警灯开，射灯全开，如果此时开关按钮置为 OFF 则报警灯关
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
					// 夜晚模式
					// 窗帘关，射灯全开，如果烟雾值浓度大于 230 则换气扇开，否则关闭换气扇。
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
					"yyyy年MM月dd日 HH:mm:ss");
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
