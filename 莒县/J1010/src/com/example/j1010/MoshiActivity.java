package com.example.j1010;

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
	Runnable sbRunnable,xbRunnable,smRunnable;
	Handler handler;
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
				    handler.removeCallbacks(xbRunnable);
				    handler.removeCallbacks(smRunnable);
				}
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
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1", ConstantUtil.OPEN);
				}else {
					myview.setVisibility(View.VISIBLE);
					tvxb.setVisibility(View.INVISIBLE);
					handler.removeCallbacks(sbRunnable);
					handler.removeCallbacks(smRunnable);
				}
			}
		});
		chsm.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chsb.setChecked(false);
					tvsm.setVisibility(View.VISIBLE);
					myview.setVisibility(View.INVISIBLE);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					tvsm.setVisibility(View.INVISIBLE);
					myview.setVisibility(View.VISIBLE);
					handler.removeCallbacks(xbRunnable);
					handler.removeCallbacks(sbRunnable);
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
					if (JibenActivity.man==1) {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
					handler.postDelayed(sbRunnable, 2000);
					break;
				case 0x0002:
					if (JibenActivity.smoke>600) {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					handler.postDelayed(xbRunnable, 2000);
					break;
				case 0x0003:
					if (JibenActivity.man==1) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
					handler.postDelayed(smRunnable, 2000);
					break;

				default:
					break;
				}
			}
		};
		sbRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (chsb.isChecked()) {
					handler.sendEmptyMessage(0x0001);
				}
			}
		};
		xbRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (chxb.isChecked()) {
					handler.sendEmptyMessage(0x0002);
				}
			}
		};
		smRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (chsm.isChecked()) {
					handler.sendEmptyMessage(0x0003);
				}
			}
		};
		return view;
	}

}
