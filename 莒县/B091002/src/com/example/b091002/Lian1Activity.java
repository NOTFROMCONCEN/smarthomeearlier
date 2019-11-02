package com.example.b091002;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Lian1Activity extends Fragment {
	public static TextView tvlianfang;
	Spinner spws,spdx,spkz,spkg;
	EditText etlian;
	Switch swkg;
	boolean kg=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_lian1, container,false);
		tvlianfang=(TextView)view.findViewById(R.id.liandongfangjianhao);
		spws=(Spinner)view.findViewById(R.id.SPws);
		spdx=(Spinner)view.findViewById(R.id.SPdx);
		spkz=(Spinner)view.findViewById(R.id.SPkz);
		spkg=(Spinner)view.findViewById(R.id.SPkg);
		etlian=(EditText)view.findViewById(R.id.edliandong);
		swkg=(Switch)view.findViewById(R.id.SWlian);
		
		swkg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (etlian.getText().toString().equals("")) {
						Toast.makeText(getActivity(), "输入数据为空", Toast.LENGTH_LONG).show();
					}else if (!etlian.getText().toString().matches("[0-9]+")) {
						Toast.makeText(getActivity(), "输入数据有误", Toast.LENGTH_LONG).show();
					}else {
						String v1=spws.getSelectedItem().toString();
						String v2=spdx.getSelectedItem().toString();
						String v3=spkz.getSelectedItem().toString();
						String v4=spkg.getSelectedItem().toString();
						int v5=Integer.parseInt(etlian.getText().toString());
						if (v1.equals("温度")&&v2.equals(">")&&FangjianActivity.temp>v5&&v3.equals("风扇")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals(">")&&FangjianActivity.temp>v5&&v3.equals("风扇")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("温度")&&v2.equals(">")&&FangjianActivity.temp>v5&&v3.equals("射灯")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals(">")&&FangjianActivity.temp>v5&&v3.equals("射灯")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("温度")&&v2.equals(">")&&FangjianActivity.temp>v5&&v3.equals("窗帘")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals(">")&&FangjianActivity.temp>v5&&v3.equals("窗帘")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals("<")&&FangjianActivity.temp<v5&&v3.equals("风扇")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals("<")&&FangjianActivity.temp<v5&&v3.equals("风扇")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("温度")&&v2.equals("<")&&FangjianActivity.temp<v5&&v3.equals("射灯")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals("<")&&FangjianActivity.temp<v5&&v3.equals("射灯")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("温度")&&v2.equals("<")&&FangjianActivity.temp<v5&&v3.equals("窗帘")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals("<")&&FangjianActivity.temp<v5&&v3.equals("窗帘")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						else if (v1.equals("湿度")&&v2.equals(">")&&FangjianActivity.hum>v5&&v3.equals("风扇")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals(">")&&FangjianActivity.hum>v5&&v3.equals("风扇")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("湿度")&&v2.equals(">")&&FangjianActivity.hum>v5&&v3.equals("射灯")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals(">")&&FangjianActivity.hum>v5&&v3.equals("射灯")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("湿度")&&v2.equals(">")&&FangjianActivity.hum>v5&&v3.equals("窗帘")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals(">")&&FangjianActivity.hum>v5&&v3.equals("窗帘")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals("<")&&FangjianActivity.hum<v5&&v3.equals("风扇")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals("<")&&FangjianActivity.hum<v5&&v3.equals("风扇")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("湿度")&&v2.equals("<")&&FangjianActivity.hum<v5&&v3.equals("射灯")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals("<")&&FangjianActivity.hum<v5&&v3.equals("射灯")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("湿度")&&v2.equals("<")&&FangjianActivity.hum<v5&&v3.equals("窗帘")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}else if (v1.equals("湿度")&&v2.equals("<")&&FangjianActivity.hum<v5&&v3.equals("窗帘")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
						else if (v1.equals("光照")&&v2.equals(">")&&FangjianActivity.ill>v5&&v3.equals("风扇")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals(">")&&FangjianActivity.ill>v5&&v3.equals("风扇")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("光照")&&v2.equals(">")&&FangjianActivity.ill>v5&&v3.equals("射灯")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals(">")&&FangjianActivity.ill>v5&&v3.equals("射灯")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("光照")&&v2.equals(">")&&FangjianActivity.ill>v5&&v3.equals("窗帘")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals(">")&&FangjianActivity.ill>v5&&v3.equals("窗帘")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals("<")&&FangjianActivity.ill<v5&&v3.equals("风扇")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals("<")&&FangjianActivity.ill<v5&&v3.equals("风扇")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("光照")&&v2.equals("<")&&FangjianActivity.ill<v5&&v3.equals("射灯")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals("<")&&FangjianActivity.ill<v5&&v3.equals("射灯")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
						}else if (v1.equals("光照")&&v2.equals("<")&&FangjianActivity.ill<v5&&v3.equals("窗帘")&&v4.equals("开")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals("<")&&FangjianActivity.ill<v5&&v3.equals("窗帘")&&v4.equals("关")) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						}
					}
				}else {
					kg=false;
				}
			}
		});
		
		return view;
	}
}
