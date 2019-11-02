package com.example.j1009;

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
import android.widget.TextView;

public class MoshiActivity extends Fragment {
	CheckBox chsb,chxb,chsm;
	TextView tvsb,tvxb,tvsm;
	Handler handler;
	Runnable runnable;
	View myview;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_moshi, container,false);
		chsb=(CheckBox)view.findViewById(R.id.chsb);
		chxb=(CheckBox)view.findViewById(R.id.Chxb);
		chsm=(CheckBox)view.findViewById(R.id.Chsm);
		tvsb=(TextView)view.findViewById(R.id.tesb);
		tvxb=(TextView)view.findViewById(R.id.Texb);
		tvsm=(TextView)view.findViewById(R.id.Tesm);
		myview=(View)view.findViewById(R.id.myview);
		
		myview.setVisibility(View.VISIBLE);
		tvsb.setVisibility(View.INVISIBLE);
		tvxb.setVisibility(View.INVISIBLE);
		tvsm.setVisibility(View.INVISIBLE);
	    
		chsb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chxb.setChecked(false);
					chsm.setChecked(false);
					tvsb.setVisibility(View.VISIBLE);
					myview.setVisibility(View.INVISIBLE);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					myview.setVisibility(View.VISIBLE);
					tvsb.setVisibility(View.INVISIBLE);
				}
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (true) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (JibenActivity.man==1) {
								ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
							}
						}
					}
				});
			}
		});
		chxb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chsb.setChecked(false);
					tvxb.setVisibility(View.VISIBLE);
					myview.setVisibility(View.INVISIBLE);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"8", ConstantUtil.OPEN);
				}else {
					tvxb.setVisibility(View.INVISIBLE);
					myview.setVisibility(View.VISIBLE);
				}
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (true) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (JibenActivity.smoke>600) {
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}
						}
					}
				});
			}
		});
		chsm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chsb.setChecked(false);
					myview.setVisibility(View.INVISIBLE);
					tvsm.setVisibility(View.VISIBLE);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					myview.setVisibility(View.VISIBLE);
					tvsm.setVisibility(View.INVISIBLE);
				}
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (true) {
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
				});
			}
		});
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (chsb.isChecked()) {
					if (msg.what%2==0) {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
					}
					handler.postDelayed(runnable, 2000);
				}
				
			}
		};
		runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			handler.sendMessage(msg);
			}
		};
		handler.post(runnable);
		return view;
	}

}
