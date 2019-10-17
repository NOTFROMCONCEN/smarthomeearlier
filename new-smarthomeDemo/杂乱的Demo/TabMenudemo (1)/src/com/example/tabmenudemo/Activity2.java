package com.example.tabmenudemo;

import lib.Json_data;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

public class Activity2 extends Activity {
	private RadioButton ra_night, ra_day, ra_info;
	private RadioGroup rg_mode_check;
	private ToggleButton tg_mode_start;
	private boolean night_state = false, day_state = false, info_state = false,
			mode_state = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_2);
		ra_day = (RadioButton) findViewById(R.id.ra_day);
		ra_info = (RadioButton) findViewById(R.id.ra_info);
		ra_night = (RadioButton) findViewById(R.id.ra_night);
		rg_mode_check = (RadioGroup) findViewById(R.id.rg_base_cur);
		tg_mode_start = (ToggleButton) findViewById(R.id.tg_mode_start);
		tg_mode_start
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							mode_state = true;
						} else {
							mode_state = false;
						}
					}
				});
		ra_day.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					day_state = true;
				} else {
					day_state = false;
				}
			}
		});
		ra_info.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					info_state = true;
				} else {
					info_state = false;
				}
			}
		});
		ra_night.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					night_state = true;
				} else {
					night_state = false;
				}
			}
		});
		handler.post(timeRunnable);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (mode_state) {
				if (day_state) {
					Log.e("白天模式", "激活");
					Activity_3.Js.control(Json_data.Fan, 0, 1);
				}
				if (info_state) {
					Log.e("防盗模式", "激活");
					if (Activity_3.per == 1) {
						Log.e("防盗模式", "有人");
						Activity_3.Js.control(Json_data.WarningLight, 0, 1);
					} else {
						Log.e("防盗模式", "无人");
						Activity_3.Js.control(Json_data.WarningLight, 0, 0);
					}
				}
				if (night_state) {
					Log.e("夜晚模式", "激活");
					Activity_3.Js.control(Json_data.Lamp, 0, 1);
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
