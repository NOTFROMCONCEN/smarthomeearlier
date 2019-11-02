package com.example.c1017;

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
	RadioButton rabt,rayw,ragw,rafd;
	ToggleButton tgbtnmoshi;
	boolean bt=false,yw=false,gw=false,fd=false,kg=false;
	Handler handler;
	Runnable runnable;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_moshi, container,false);
		rabt=(RadioButton)view.findViewById(R.id.radio0);
		rayw=(RadioButton)view.findViewById(R.id.radio1);
		ragw=(RadioButton)view.findViewById(R.id.radio2);
		rafd=(RadioButton)view.findViewById(R.id.radio3);
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
						bt=true;
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,ConstantUtil.OPEN);
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
									if (JibenActivity.smoke>400) {
										ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
									}
								}
							}
						}).start();
					}else {
						bt=false;
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
						yw=true;
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
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
									if (JibenActivity.ill<200) {
										ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
									}else if (JibenActivity.ill>500) {
										ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
									}
								}
							}
						}).start();
					}else {
						yw=false;
					}
				}
			}
		});
		rafd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
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
										ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
										ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
									}
								}
							}
						}).start();
					}else {
						fd=false;
					}
				}
			}
		});
		ragw.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1",ConstantUtil.OPEN);
						gw=true;
					}
				}
			}
		});
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (kg) {
					if (gw) {
						if (msg.what%2==0) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}
						handler.postDelayed(runnable, 2000);
					}
				}else {
					kg=false;
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
