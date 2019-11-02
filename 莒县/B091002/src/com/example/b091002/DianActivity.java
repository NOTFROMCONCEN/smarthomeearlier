package com.example.b091002;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

public class DianActivity extends Fragment {
public static TextView tvfjh;
Button btnkt,btndvd,btntv,btnclk,btnclt,btnclg,btndoor;
ToggleButton tgbtnlamp,tgbtnbjd,tgbtnfan;
boolean kg=false;
@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_dian, container,false);
		tvfjh=(TextView)view.findViewById(R.id.tvfjh);
		btntv=(Button)view.findViewById(R.id.Buttv);
		btndvd=(Button)view.findViewById(R.id.Butdvd);
		btnkt=(Button)view.findViewById(R.id.butkt);
		btnclk=(Button)view.findViewById(R.id.butclk);
		btnclt=(Button)view.findViewById(R.id.Butclt);
		btnclg=(Button)view.findViewById(R.id.Butclg);
		btndoor=(Button)view.findViewById(R.id.Butdoor);
		
		tgbtnbjd=(ToggleButton)view.findViewById(R.id.togglebjd);
		tgbtnlamp=(ToggleButton)view.findViewById(R.id.Togglelamp);
		tgbtnfan=(ToggleButton)view.findViewById(R.id.Togglefan);
		
		tgbtnbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tgbtnlamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tgbtnfan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		btntv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (kg) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
				}
			}
		});
		btnkt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (kg) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
				}
			}
		});
		btndvd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (kg) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
				}
			}
		});
		btndoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.RFID_Open_Door,ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		return view;
	}

}
