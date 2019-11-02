package com.example.e0926;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Activity {
	RelativeLayout jb;
	EditText ettemp,ethum,etsmoke,etgas,etair,etill,etco2,etpm25,etman;
	ImageView imbjd,imlamp,imfan,imcl,imdoor,imtd1,imtd2,imtd3;
	boolean kg=false;
	public static float temp,hum,smoke,ill,gas,air,co2,pm25,man;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiben);
		ettemp=(EditText)findViewById(R.id.edi1);
		ethum=(EditText)findViewById(R.id.edi2);
		etsmoke=(EditText)findViewById(R.id.edi3);
		etgas=(EditText)findViewById(R.id.edi4);
		etill=(EditText)findViewById(R.id.edi5);
		etair=(EditText)findViewById(R.id.edi6);
		etco2=(EditText)findViewById(R.id.edi7);
		etpm25=(EditText)findViewById(R.id.edi8);
		etman=(EditText)findViewById(R.id.edi9);
		imbjd=(ImageView)findViewById(R.id.Imbjd);
		imlamp=(ImageView)findViewById(R.id.imlamp);
		imcl=(ImageView)findViewById(R.id.Imcl);
		imfan=(ImageView)findViewById(R.id.Imfan);
		imdoor=(ImageView)findViewById(R.id.Imdoor);
		imtd1=(ImageView)findViewById(R.id.imtd1);
		imtd2=(ImageView)findViewById(R.id.Imtd2);
		imtd3=(ImageView)findViewById(R.id.Imtd3);
		jb=(RelativeLayout)findViewById(R.id.jb);
		
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(bean.getTemperature())) {
							ettemp.setText(bean.getTemperature());
						}
						if (!TextUtils.isEmpty(bean.getSmoke())) {
							etsmoke.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getGas())) {
							etgas.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							etill.setText(bean.getIllumination());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							etair.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getCo2())) {
							etco2.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							etpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getHumidity())) {
							ethum.setText(bean.getHumidity());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							etman.setText("无人");
						}else {
							etman.setText("有人");
						}
						temp=Float.parseFloat(ettemp.getText().toString());
						hum=Float.parseFloat(ethum.getText().toString());
						smoke=Float.parseFloat(etsmoke.getText().toString());
						air=Float.parseFloat(etair.getText().toString());
						gas=Float.parseFloat(etgas.getText().toString());
						co2=Float.parseFloat(etco2.getText().toString());
						ill=Float.parseFloat(etill.getText().toString());
						pm25=Float.parseFloat(etpm25.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		imlamp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imlamp.setImageResource(R.drawable.lamp_g);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imlamp.setImageResource(R.drawable.lamp_k);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imcl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imcl.setImageResource(R.drawable.cl_k);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}else {
					imcl.setImageResource(R.drawable.cl_g);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
				}
			}
		});
		imfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imfan.setImageResource(R.drawable.fan_g);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imfan.setImageResource(R.drawable.fan_k);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imbjd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imbjd.setImageResource(R.drawable.bjd_g);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imbjd.setImageResource(R.drawable.bjd_k);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imdoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imdoor.setImageResource(R.drawable.mj_g);
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					imdoor.setImageResource(R.drawable.mj_k);
				}
			}
		});
		imtd1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
			}
		});
		imtd2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
			}
		});
		imtd3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
			}
		});
		jb.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(JibenActivity.this,LianActivity.class));
				finish();
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jiben, menu);
		return true;
	}

}
