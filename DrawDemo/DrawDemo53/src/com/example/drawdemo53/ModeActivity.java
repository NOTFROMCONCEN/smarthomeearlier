package com.example.drawdemo53;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ToggleButton;

public class ModeActivity extends Fragment {
	private RadioButton ra_day, ra_night, ra_dance, ra_fangdao;
	private RadioGroup rg_mode_check;
	private ToggleButton tg_mode_start;
	private boolean day_mode = false, night_mode = false, dance_mode = false,
			fangdao_mode = false, mode_state = false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub.
		View view = inflater.inflate(R.layout.activity_mode, container, false);
		ra_dance = (RadioButton) view.findViewById(R.id.ra_dance);
		ra_day = (RadioButton) view.findViewById(R.id.ra_day);
		ra_fangdao = (RadioButton) view.findViewById(R.id.ra_people);
		ra_night = (RadioButton) view.findViewById(R.id.ra_night);
		rg_mode_check = (RadioGroup) view.findViewById(R.id.rg_mode_check);
		tg_mode_start = (ToggleButton) view.findViewById(R.id.tg_mode_start);
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
		rg_mode_check.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if (ra_dance.isChecked()) {
					Log.e("ģʽ", "����ģʽ");
					dance_mode = true;
				} else {
					dance_mode = false;
				}
				if (ra_day.isChecked()) {
					Log.e("ģʽ", "����ģʽ");
					day_mode = true;
				} else {
					day_mode = false;
				}
				if (ra_fangdao.isChecked()) {
					Log.e("ģʽ", "����ģʽ");
					fangdao_mode = true;
				} else {
					fangdao_mode = false;
				}
				if (ra_night.isChecked()) {
					Log.e("ģʽ", "ҹ��ģʽ");
					night_mode = true;
				} else {
					night_mode = false;
				}
			}
		});
		handler.post(timeRunnable);
		return view;
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (mode_state) {
				if (dance_mode) {
					Log.e("����ģʽ", "����");
				}
				if (day_mode) {
					Log.e("����ģʽ", "����");
				}
				if (fangdao_mode) {
					Log.e("����ģʽ", "����");
				}
				if (night_mode) {
					Log.e("ҹ��ģʽ", "����");
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
