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
 * @文件名：ModeActivity.java
 * @描述：模式
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-6
 */
public class ModeActivity extends Activity {
	private ToggleButton tg_mode_state;
	private RadioButton ra_anfang_mode;// 安防模式
	private RadioButton ra_lijia_mode;// 离家模式
	private RadioButton ra_diy_mode;// 自定义模式
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
		// 激活
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (tg_mode_state.isChecked()) {
				// 开关打开
				if (ra_anfang_mode.isChecked()) {
					// 安防模式
					// 窗帘、门禁、射灯这些设备都关。
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
					// 自定义模式

				}
				if (ra_lijia_mode.isChecked()) {
					// 离家模式
					// 如果人体红外感应出有人，则报警灯开，射灯开，门禁关。
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