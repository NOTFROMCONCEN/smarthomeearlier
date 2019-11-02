package com.example.i082102;

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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class LianActivity extends Fragment {
	Spinner spws,spdx;
	CheckBox chdang,chcl,chfan,chlamp,chbjd;
	EditText etlian;
	Button btnxz,btnxuanze;
	boolean kg=false;
	AlertDialog builder;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_lian, container,false);
		spws=(Spinner)view.findViewById(R.id.spinner1);
		spdx=(Spinner)view.findViewById(R.id.Spinner2);;
		chdang=(CheckBox)view.findViewById(R.id.chdang);
		etlian=(EditText)view.findViewById(R.id.edlian);
		btnxz=(Button)view.findViewById(R.id.butxz);
		
		chdang.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (etlian.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "输入数据不能为空", Toast.LENGTH_LONG).show();
						chdang.setChecked(false);
					}else if (!etlian.getText().toString().matches("[0-9]+")) {
						Toast.makeText(getActivity(), "输入数据有误", Toast.LENGTH_LONG).show();
						chdang.setChecked(false);
					}else {
						String v1=spws.getSelectedItem().toString();
						String v2=spdx.getSelectedItem().toString();
						int v3=Integer.parseInt(etlian.getText().toString());
						if (v1.equals("温度")&&v2.equals("大于")&&JibenActivity.temp>v3) {
							kg=true;
						}else if (v1.equals("温度")&&v2.equals("小于")&&JibenActivity.temp<v3) {
							kg=true;
						}else if (v1.equals("湿度")&&v2.equals("大于")&&JibenActivity.hum>v3) {
							kg=true;
						}else if (v1.equals("湿度")&&v2.equals("小于")&&JibenActivity.hum<v3) {
							kg=true;
						}else if (v1.equals("光照")&&v2.equals("大于")&&JibenActivity.ill>v3) {
							kg=true;
						}else if (v1.equals("光照")&&v2.equals("小于")&&JibenActivity.ill<v3) {
							kg=true;
						}else if (v1.equals("气压")&&v2.equals("大于")&&JibenActivity.air>v3) {
							kg=true;
						}else if (v1.equals("气压")&&v2.equals("小于")&&JibenActivity.air<v3) {
							kg=true;
						}else if (!kg) {
							chcl.setEnabled(false);
							chbjd.setEnabled(false);
							chfan.setEnabled(false);
							chlamp.setEnabled(false);
						}
					}
				}
			}
		});
		btnxz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder=new AlertDialog.Builder(getActivity()).create();
				builder.setCancelable(false);
				builder.show();
				View view=LayoutInflater.from(getActivity()).inflate(R.layout.liandong,null);
				Window window=builder.getWindow();
				window.setContentView(view);
				
				chbjd=(CheckBox)view.findViewById(R.id.chbjd);
				chfan=(CheckBox)view.findViewById(R.id.chfan);
				chlamp=(CheckBox)view.findViewById(R.id.chlamp);
				chcl=(CheckBox)view.findViewById(R.id.chcl);
				btnxuanze=(Button)view.findViewById(R.id.butxuanze);
				
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
				btnxuanze.setOnClickListener(new OnClickListener() {
					
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
