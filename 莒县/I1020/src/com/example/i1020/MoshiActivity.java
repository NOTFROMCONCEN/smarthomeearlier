package com.example.i1020;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
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
	boolean bt=false,yw=false,fd=false;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_moshi, container,false);
		rabt=(RadioButton)view.findViewById(R.id.radio0);
		rayw=(RadioButton)view.findViewById(R.id.radio1);
		rafd=(RadioButton)view.findViewById(R.id.radio2);
		tgbtnmoshi=(ToggleButton)view.findViewById(R.id.togglemoshi);
		
		rabt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					bt=true;
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (bt) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (JibenActivity.ill>150) {
									ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
								}else {
									ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				}else {
					bt=false;
				}
			}
		});
		rayw.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					yw=true;
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (yw) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (JibenActivity.ill<50) {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					yw=false;
				}
			}
		});
		rafd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					fd=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (fd) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (JibenActivity.man==1) {
									ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN); 
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN); 
								}else {
									ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE); 
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE); 
								}
							}
						}
					}).start();
				}else {
					fd=false;
				}
			}
		});
		return view;
	}


}
