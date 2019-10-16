package com.example.guosaibdemo826;

import lib.Json_data;
import lib.json_dispose;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class KongzhiActivity extends Activity {
	private TextView tv_room_num;
	private ToggleButton tg_warm, tg_lamp, tg_fan, tg_tv, tg_dvd, tg_door,
			tg_kongtiao;
	private Button btn_open, btn_cls, btn_stop;
	private json_dispose Js = new json_dispose();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kongzhi);
		tv_room_num = (TextView) findViewById(R.id.tv_kongzhi_room_num);
		tv_room_num.setText("·¿¼äºÅ£º" + MenuActivity.room_number);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_open = (Button) findViewById(R.id.btn_open);
		btn_stop = (Button) findViewById(R.id.btn_stop);
		tg_door = (ToggleButton) findViewById(R.id.tg_door);
		tg_dvd = (ToggleButton) findViewById(R.id.tg_dvd);
		tg_fan = (ToggleButton) findViewById(R.id.tg_fan);
		tg_kongtiao = (ToggleButton) findViewById(R.id.tg_kongtiao);
		tg_lamp = (ToggleButton) findViewById(R.id.tg_lamp);
		tg_tv = (ToggleButton) findViewById(R.id.tg_tv);
		tg_warm = (ToggleButton) findViewById(R.id.tg_warm);
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Curtain, 0, 2);
			}
		});
		btn_open.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Curtain, 0, 1);
			}
		});
		btn_stop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Js.control(Json_data.Curtain, 0, 3);
			}
		});
		tg_door.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.RFID_Open_Door, 0, 1);
				} else {
					Js.control(Json_data.RFID_Open_Door, 0, 0);
				}
			}
		});
		tg_dvd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.InfraredEmit, 0, 3);
				} else {
					Js.control(Json_data.InfraredEmit, 0, 3);
				}
			}
		});
		tg_fan.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.Fan, 0, 1);
				} else {
					Js.control(Json_data.Fan, 0, 0);
				}
			}
		});
		tg_kongtiao.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.InfraredEmit, 0, 2);
				} else {
					Js.control(Json_data.InfraredEmit, 0, 2);
				}
			}
		});
		tg_lamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.Lamp, 0, 1);
				} else {
					Js.control(Json_data.Lamp, 0, 0);
				}
			}
		});
		tg_tv.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.InfraredEmit, 0, 1);
				} else {
					Js.control(Json_data.InfraredEmit, 0, 1);
				}
			}
		});
		tg_warm.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					Js.control(Json_data.WarningLight, 0, 1);
				} else {
					Js.control(Json_data.WarningLight, 0, 0);
				}
			}
		});
	}
}