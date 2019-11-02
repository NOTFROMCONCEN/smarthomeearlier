package com.example.c1005;

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
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Fragment {
	TextView tvtemp,tvhum,tvsmoke,tvair,tvgas,tvill,tvco2,tvpm25,tvman;
	ToggleButton tgbtncl,tgbtnbjd,tgbtnlamp1,tgbtnlamp2,tgbtnfan,tgbtndoor;
	EditText etfashe;
	Button btnfashe;
	public static float temp,hum,smoke,ill,gas,air,pm25,co2,man;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_jiben, container,false);
		tvtemp=(TextView)view.findViewById(R.id.Text1);
		tvhum=(TextView)view.findViewById(R.id.Text2);
		tvsmoke=(TextView)view.findViewById(R.id.Text3);
		tvgas=(TextView)view.findViewById(R.id.Text4);
		tvill=(TextView)view.findViewById(R.id.Text5);
		tvco2=(TextView)view.findViewById(R.id.Text6);
		tvair=(TextView)view.findViewById(R.id.Text7);
		tvpm25=(TextView)view.findViewById(R.id.Text8);
		tvman=(TextView)view.findViewById(R.id.Text9);
		
		tgbtncl=(ToggleButton)view.findViewById(R.id.toggleButton1);
		tgbtnbjd=(ToggleButton)view.findViewById(R.id.toggleButton2);
		tgbtnlamp1=(ToggleButton)view.findViewById(R.id.toggleButton3);
		tgbtnlamp2=(ToggleButton)view.findViewById(R.id.toggleButton4);
		tgbtnfan=(ToggleButton)view.findViewById(R.id.toggleButton5);
		tgbtndoor=(ToggleButton)view.findViewById(R.id.toggleButton6);
		
		etfashe=(EditText)view.findViewById(R.id.edfashe);
		btnfashe=(Button)view.findViewById(R.id.butfashe);
		
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
						if (!TextUtils.isEmpty(bean.getSmoke())) {
							tvsmoke.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							tvill.setText(bean.getIllumination());
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
						if (!TextUtils.isEmpty(bean.getGas())) {
							tvgas.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvman.setText("无人");
						}else {
							tvman.setText("有人");
						}
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		tgbtncl.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			}
		});
		tgbtnbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tgbtnlamp1.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tgbtnfan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tgbtnlamp2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		tgbtndoor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
		
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			
		});
		btnfashe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int fashe=Integer.parseInt(etfashe.getText().toString());
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"fashe", ConstantUtil.OPEN);
			}
		});
		return view;
	}

}
