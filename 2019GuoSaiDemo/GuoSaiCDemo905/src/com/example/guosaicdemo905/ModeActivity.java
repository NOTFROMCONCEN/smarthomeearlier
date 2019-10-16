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
 * @文件名：ModeActivity.java
 * @描述：完成模式控制功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-31
 */
public class ModeActivity extends Fragment {
	private TextView tv_mode_time;// 时间
	private ToggleButton tg_draw_state;// 开关
	private RadioButton ra_night_mode, ra_day_mode, ra_lijia_mode,
			ra_fangdao_mode;// 夜晚、白天、离家、防盗模式
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
		// 激活进程
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_draw_state.isChecked()) {
				if (ra_day_mode.isChecked()) {
					// 射灯全关，窗帘开，如果烟雾大于400则换气扇开；
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
					// 如果人体红外感应出有人，则报警灯开，射灯全开。
					if (BaseActivity.per == 1) {
						ControlUtils.control(ConstantUtil.WarningLight,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
				if (ra_lijia_mode.isChecked()) {
					// 歌舞模式下，空调开，两射灯大约2秒进行一次全开和全关的交替闪烁；
					if (msg.what % 2 == 0) {// 开
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else {// 关
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
				if (ra_night_mode.isChecked()) {
					// 夜晚模式下，窗帘关，如果光照小于200则开启一个射灯。如果大于500则射灯全关；
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
			// 设置时间
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
