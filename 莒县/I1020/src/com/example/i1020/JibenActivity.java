package com.example.i1020;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Fragment {
	TextView tvtemp,tvhum,tvill,tvpm25,tvco2,tvsmoke,tvgas,tvair,tvman;
	ImageView imtemp,imhum;
	android.graphics.Matrix matrix;
	
	public static float temp,hum,smoke,gas,ill,pm25,man,co2,air;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_jiben, container,false);
		tvtemp=(TextView)view.findViewById(R.id.text1);
		tvhum=(TextView)view.findViewById(R.id.text2);
		tvsmoke=(TextView)view.findViewById(R.id.text3);
		tvgas=(TextView)view.findViewById(R.id.text4);
		tvill=(TextView)view.findViewById(R.id.text5);
		tvco2=(TextView)view.findViewById(R.id.text6);
		tvair=(TextView)view.findViewById(R.id.text7);
		tvpm25=(TextView)view.findViewById(R.id.text8);
		tvman=(TextView)view.findViewById(R.id.text9);
		imtemp=(ImageView)view.findViewById(R.id.imtemp);
		imhum=(ImageView)view.findViewById(R.id.Imhum);
		
		//仪表
		matrix=new android.graphics.Matrix();
		matrix.setRotate(0);
		BitmapDrawable bitmapDrawable=(BitmapDrawable)(getResources()).getDrawable(R.drawable.zz);
		Bitmap bitmap=bitmapDrawable.getBitmap();
		Bitmap bitmap2=bitmapDrawable.getBitmap().createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,false);
		imtemp.setImageBitmap(bitmap2);
		imhum.setImageBitmap(bitmap2);
		
		//获取数据
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {
					
					@Override()
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
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getGas())) {
							tvgas.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							if (bean.getStateHumanInfrared().equals("1500")) {
								getActivity().finish();
							}else {
								tvill.setText(bean.getIllumination());
							}
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							tvair.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvman.setText("无人");
						}else {
							tvman.setText("有人");
						}
						//将数据转换为float
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
						imtemp.setRotation(temp *1.5f);
						imhum.setRotation(hum *1.5f);
					}
				});
			}
		});
		return view;
	}

}
