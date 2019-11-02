package com.example.i1020;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class LianActivity extends Fragment {
	Spinner spws,spdx;
	CheckBox chdang,chcl,chbjd,chfan,chlamp;
	Button btnxz,btnxuan;
	EditText etlian;
	boolean kg=false;
	AlertDialog builder;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_lian, container,false);
		spws=(Spinner)view.findViewById(R.id.spws);
		spdx=(Spinner)view.findViewById(R.id.Spdx);
		chdang=(CheckBox)view.findViewById(R.id.checkBox1);
		btnxz=(Button)view.findViewById(R.id.butxz);
		etlian=(EditText)view.findViewById(R.id.edlian);
		
		chdang.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (etlian.getText().toString().isEmpty()) {
					Toast.makeText(getActivity(), "输入数据为空",0).show();
					chdang.setChecked(false);
				}else if (!etlian.getText().toString().matches("[0-9]+")) {
					Toast.makeText(getActivity(), "输入数据有误",0).show();
					chdang.setChecked(false);
				}else {
					String v1=spws.getSelectedItem().toString();
					String v2=spdx.getSelectedItem().toString();
					int fazhi=Integer.parseInt(etlian.getText().toString());
					if (v1.equals("温度")&&v2.equals("大于")&&JibenActivity.temp>fazhi) {
						kg=true;
					}else if (v1.equals("温度")&&v2.equals("小于")&&JibenActivity.temp<fazhi) {
						kg=true;
					}else if (v1.equals("湿度")&&v2.equals("大于")&&JibenActivity.hum>fazhi) {
						kg=true;
					}else if (v1.equals("湿度")&&v2.equals("小于")&&JibenActivity.hum<fazhi) {
						kg=true;
					}else if (v1.equals("光照")&&v2.equals("大于")&&JibenActivity.ill>fazhi) {
						kg=true;
					}else if (v1.equals("光照")&&v2.equals("小于")&&JibenActivity.ill<fazhi) {
						kg=true;
					}else if (v1.equals("气压")&&v2.equals("大于")&&JibenActivity.air>fazhi) {
						kg=true;
					}else if (v1.equals("气压")&&v2.equals("小于")&&JibenActivity.air<fazhi) {
						kg=true;
					}
					
				}
			}
		});
		btnxz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder=new AlertDialog.Builder(getActivity()).create();
				builder.show();
				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listview,null);
				Window window=builder.getWindow();
				window.setContentView(view);
				
				chbjd=(CheckBox)window.findViewById(R.id.chbjd);
				chfan=(CheckBox)window.findViewById(R.id.chfan);
				chcl=(CheckBox)window.findViewById(R.id.chcl);
				chlamp=(CheckBox)window.findViewById(R.id.chlamp);
				btnxuan=(Button)window.findViewById(R.id.butxuan);
				
				chcl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (kg) {
							if (isChecked) {
								ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
							}
						}
					}
				});
				chbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (kg) {
							if (isChecked) {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}
						}
					}
				});
				chlamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (kg) {
							if (isChecked) {
								ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}
						}
					}
				});
				chfan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						if (kg) {
							if (isChecked) {
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else {
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}
						}
					}
				});
				btnxuan.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					builder.dismiss();	
					}
				});
			}
		});
		return view;
	}


}
