package com.example.b1008;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class Lian1Activity extends Fragment {
	public static TextView tvlianf;
	Spinner spws,spdx,spkz,spkg;
	EditText etlian;
	Switch swkg;
	boolean kg=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_lian1, container,false);
		tvlianf=(TextView)view.findViewById(R.id.telianf);
		spws=(Spinner)view.findViewById(R.id.spws);
		spdx=(Spinner)view.findViewById(R.id.Spdx);
		spkz=(Spinner)view.findViewById(R.id.Spkz);
		spkg=(Spinner)view.findViewById(R.id.Spkg);
		etlian=(EditText)view.findViewById(R.id.edlian);
		swkg=(Switch)view.findViewById(R.id.switch1);

		swkg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				String v1=spws.getSelectedItem().toString();
				String v2=spdx.getSelectedItem().toString();
				String v3=spkz.getSelectedItem().toString();
				String v4=spkg.getSelectedItem().toString();
				int fazhi=Integer.parseInt(etlian.getText().toString());
				if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>fazhi&&v3.equals("风扇")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>fazhi&&v3.equals("风扇")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>fazhi&&v3.equals("射灯")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>fazhi&&v3.equals("射灯")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>fazhi&&v3.equals("窗帘")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>fazhi&&v3.equals("窗帘")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("风扇")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("风扇")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("射灯")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("射灯")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("窗帘")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("窗帘")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
					
				}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum<fazhi&&v3.equals("风扇")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum>fazhi&&v3.equals("风扇")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum>fazhi&&v3.equals("射灯")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum>fazhi&&v3.equals("射灯")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum>fazhi&&v3.equals("窗帘")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum>fazhi&&v3.equals("窗帘")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("风扇")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("风扇")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("射灯")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("射灯")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("窗帘")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("窗帘")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
				else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill<fazhi&&v3.equals("风扇")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill>fazhi&&v3.equals("风扇")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill>fazhi&&v3.equals("射灯")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill>fazhi&&v3.equals("射灯")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill>fazhi&&v3.equals("窗帘")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill>fazhi&&v3.equals("窗帘")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals("<")&&JibenActivity.ill<fazhi&&v3.equals("风扇")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else if (v1.equals("光照")&&v2.equals("<")&&JibenActivity.ill<fazhi&&v3.equals("风扇")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals("<")&&JibenActivity.ill<fazhi&&v3.equals("射灯")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals("<")&&JibenActivity.ill<fazhi&&v3.equals("射灯")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals("<")&&JibenActivity.ill<fazhi&&v3.equals("窗帘")&&v4.equals("开")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
				}else if (v1.equals("光照")&&v2.equals("<")&&JibenActivity.ill<fazhi&&v3.equals("窗帘")&&v4.equals("关")) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
			}
		});


		return view;
	}

}
