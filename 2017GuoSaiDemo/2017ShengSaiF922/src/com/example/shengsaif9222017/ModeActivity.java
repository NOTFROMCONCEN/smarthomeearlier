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
 * @文件名：ModeActivity.java
 * @描述：模式控制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class ModeActivity extends Fragment {
	private CheckBox cb_sleep;// 睡眠模式
	private CheckBox cb_onclass;// 上班模式
	private CheckBox cb_outclass;// 下班模式

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		cb_onclass = (CheckBox) view.findViewById(R.id.cb_onclass);
		cb_outclass = (CheckBox) view.findViewById(R.id.cb_outclass);
		cb_sleep = (CheckBox) view.findViewById(R.id.cb_sleep);
		// 设置”只能选择一个模式“效果
		// 上班模式
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
		// 下班模式
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
		// 睡眠模式
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
		// 激活进程
		hand.post(timeRunnable);
		return view;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：模式功能实现
	 * 
	 * @时 间：下午4:08:09
	 */
	Handler hand = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (cb_onclass.isChecked()) {
				// 上班模式
				// 监控人体红外，当显示有人时，开启报警灯；
				if (BaseActivity.per == 1) {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
			if (cb_outclass.isChecked()) {
				// 下班模式
				// 打开通道一，通道三。开始监控人体红外，当显示有人时，且温度大于35度，打开风扇；
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
				// 睡眠模式
				// 开启警报，当有人闯入室内时候报警，没有人，则不触发。
				// 同时开始检测室内光照度，当光照度大于100时，
				// 则执行关灯，关窗帘命令。
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
