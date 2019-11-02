package com.example.g1014;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Fragment {
	TextView tvtemp,tvhum,tvsmoke,tvill,tvgas,tvco2,tvpm25,tvair,tvman;
	Switch swbjd,swdoor,swfan,swlamp;
	Button btnclk,btnclt,btnclg,btnfashe;
	EditText etfashe;
	TextView tvcj,tvkz;
	GridLayout gridcj,gridkz;
	public static float temp,hum,smoke,ill,gas,co2,pm25,air,man;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_jiben, container,false);
		tvtemp=(TextView)view.findViewById(R.id.Tex1);
		tvhum=(TextView)view.findViewById(R.id.Tex2);
		tvco2=(TextView)view.findViewById(R.id.Tex3);
		tvill=(TextView)view.findViewById(R.id.Tex4);
		tvsmoke=(TextView)view.findViewById(R.id.Tex5);
		tvair=(TextView)view.findViewById(R.id.Tex6);
		tvpm25=(TextView)view.findViewById(R.id.Tex7);
		tvgas=(TextView)view.findViewById(R.id.Tex8);
		tvman=(TextView)view.findViewById(R.id.Tex9);
		
		swbjd=(Switch)view.findViewById(R.id.swbjd);
		swdoor=(Switch)view.findViewById(R.id.Swdoor);
		swfan=(Switch)view.findViewById(R.id.Swfan);
		swlamp=(Switch)view.findViewById(R.id.Swlamp);
		btnclk=(Button)view.findViewById(R.id.butclk);
		btnclt=(Button)view.findViewById(R.id.Butclt);
		btnclg=(Button)view.findViewById(R.id.Butclg);
		btnfashe=(Button)view.findViewById(R.id.butfashe);
		etfashe=(EditText)view.findViewById(R.id.edfashe);
		tvcj=(TextView)view.findViewById(R.id.textcj);
		tvkz=(TextView)view.findViewById(R.id.Textkz);
		gridcj=(GridLayout)view.findViewById(R.id.gridcj);
		gridkz=(GridLayout)view.findViewById(R.id.gridkz);
		
		gridcj.setVisibility(View.VISIBLE);
		gridkz.setVisibility(View.INVISIBLE);
		tvcj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvcj.setBackgroundColor(Color.BLUE);
				tvkz.setBackgroundResource(R.drawable.qianlian);
				gridcj.setVisibility(View.VISIBLE);
				gridkz.setVisibility(View.INVISIBLE);
			}
		});
		tvkz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvkz.setBackgroundColor(Color.BLUE);
				tvcj.setBackgroundResource(R.drawable.qianlian);
				gridkz.setVisibility(View.VISIBLE);
				gridcj.setVisibility(View.INVISIBLE);
			}
		});
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(bean.getTemperature())) {
							tvtemp.setText(bean.getTemperature());
						}
						if (!TextUtils.isEmpty(bean.getHumidity())) {
							tvhum.setText(bean.getHumidity());
						}
						if (!TextUtils.isEmpty(bean.getGas())) {
							tvgas.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getSmoke())) {
							tvsmoke.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							tvair.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							tvill.setText(bean.getIllumination());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvman.setText("无人");
						}else {
							tvman.setText("有人");
						}
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		swbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
				}
			}
		});
		swlamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
				}
			}
		});
		swfan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
				}
			}
		});
		swdoor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
			}
		});
		btnclk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3,ConstantUtil.OPEN);
			}
		});
		btnclt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2,ConstantUtil.OPEN);
			}
		});
		btnclg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
			}
		});
		btnfashe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String fashe=etfashe.getText().toString();
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, fashe, ConstantUtil.OPEN);
			}
		});
		return view;
	}

}
