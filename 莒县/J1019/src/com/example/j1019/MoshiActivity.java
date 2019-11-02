package com.example.j1019;

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
	TextView tvsb,tvxb,tvsm;
	CheckBox chsb,chxb,chsm;
	boolean sb=false,xb=false,sm=false;
	Handler handler;
	Runnable runnable;
	View myview;


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.activity_moshi, container,false);
	tvsb=(TextView)view.findViewById(R.id.tesb);
	tvxb=(TextView)view.findViewById(R.id.Texb);
	tvsm=(TextView)view.findViewById(R.id.Tesm);
	chsb=(CheckBox)view.findViewById(R.id.chsb);
	chxb=(CheckBox)view.findViewById(R.id.Chxb);
	chsm=(CheckBox)view.findViewById(R.id.Chsm);
	myview=(View)view.findViewById(R.id.Myview);
	
	tvsb.setVisibility(View.INVISIBLE);
	tvxb.setVisibility(View.INVISIBLE);
	tvsm.setVisibility(View.INVISIBLE);
	myview.setVisibility(View.VISIBLE);
	
	chsb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked) {
				sb=true;
				chxb.setChecked(false);
				chsm.setChecked(false);
				tvsb.setVisibility(View.VISIBLE);
				myview.setVisibility(View.INVISIBLE);
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (sb) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (KzActivity.man==1) {
								ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
								handler.post(runnable);
							}
						}
					}
				}).start();
			}else {
				sb=false;
				tvsb.setVisibility(View.INVISIBLE);
				myview.setVisibility(View.VISIBLE);
			}
		}
	});
	chxb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked) {
				xb=true;
				chsb.setChecked(false);
				tvxb.setVisibility(View.VISIBLE);
				myview.setVisibility(View.INVISIBLE);
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1", ConstantUtil.OPEN);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (xb) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (KzActivity.smoke>600) {
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}
						}
					}
				}).start();
			}else {
				xb=false;
				tvxb.setVisibility(View.INVISIBLE);
				myview.setVisibility(View.VISIBLE);
			}
		}
	});
	chsm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			// TODO Auto-generated method stub
			if (isChecked) {
				sm=true;
				chsb.setChecked(false);
				tvsm.setVisibility(View.VISIBLE);
				myview.setVisibility(View.INVISIBLE);
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				new Thread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (sm) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								// TODO: handle exception
							}
							if (KzActivity.man==1) {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}
						}
					}
				}).start();
			}else {
				sm=false;
				tvsm.setVisibility(View.INVISIBLE);
				myview.setVisibility(View.VISIBLE);
			}
		}
	});
	handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (chsb.isChecked()) {
				if (msg.what%2==0) {
					ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL	,ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL	,ConstantUtil.CLOSE);
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
	
	return view;
}

}
