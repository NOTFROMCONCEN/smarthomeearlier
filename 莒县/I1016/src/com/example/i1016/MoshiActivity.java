package com.example.i1016;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.ToggleButton;

public class MoshiActivity extends Fragment {
	RadioButton rabt,rayw,rafd;
	ToggleButton tgbtnmoshi;
	Handler handler;
	Runnable btRunnable,ywRunnable,fdRunnable;
	boolean kg=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_moshi, container,false);
		rabt=(RadioButton)view.findViewById(R.id.radio0);
		rayw=(RadioButton)view.findViewById(R.id.radio1);
		rafd=(RadioButton)view.findViewById(R.id.radio2);
		tgbtnmoshi=(ToggleButton)view.findViewById(R.id.toggmoshi);
		
		tgbtnmoshi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					kg=true;
				}else {
					kg=false;
				}
			}
		});
		
		rabt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					handler.removeCallbacks(btRunnable);
				}else {
					kg=false;
				}
				}
			}
		});
		rayw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					handler.removeCallbacks(ywRunnable);
				}else {
					kg=false;
				}
				}
			}
		});
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 0x0001:
					if (JibenActivity.ill>150) {
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
					handler.postDelayed(btRunnable, 2000);
					break;
				case 0x0002:
					if (JibenActivity.ill<50) {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					handler.postDelayed(ywRunnable, 2000);
					break;
				case 0x0003:
					if (JibenActivity.man==1) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					handler.postDelayed(fdRunnable, 2000);
					break;

				default:
					break;
				}
			}
		};
		btRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (rabt.isChecked()) {
					handler.sendEmptyMessage(0x0001);
				}
			}
		};
		ywRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (rayw.isChecked()) {
					handler.sendEmptyMessage(0x0002);
				}
			}
		};
		fdRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (rafd.isChecked()) {
					handler.sendEmptyMessage(0x0003);
				}
			}
		};
		
		return view;
	}

}
