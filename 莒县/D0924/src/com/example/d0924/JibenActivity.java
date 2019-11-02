package com.example.d0924;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Activity {
	TextView tvtemp,tvhum,tvsmoke,tvair,tvpm25,tvco2,tvman,tvill,tvgas;
	ImageView imfh;
	public static float temp,hum,smoke,gas,air,pm25,co2,man,ill;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiben);
		tvgas=(TextView)findViewById(R.id.text1);
		tvill=(TextView)findViewById(R.id.text2);
		tvair=(TextView)findViewById(R.id.text3);
		tvhum=(TextView)findViewById(R.id.text4);
		tvpm25=(TextView)findViewById(R.id.text5);
		tvsmoke=(TextView)findViewById(R.id.text6);
		tvtemp=(TextView)findViewById(R.id.text7);
		tvco2=(TextView)findViewById(R.id.text8);
		tvman=(TextView)findViewById(R.id.text9);
		imfh=(ImageView)findViewById(R.id.imfh);
		
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
							tvtemp.setText(bean.getTemperature());
						}
						if (!TextUtils.isEmpty(bean.getSmoke())) {
							tvsmoke.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getGas())) {
							tvgas.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							tvill.setText(bean.getIllumination());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							tvair.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getHumidity())) {
							tvhum.setText(bean.getHumidity());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvtemp.setText("无人");
						}else {
							tvman.setText("有人");
						}
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		imfh.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(JibenActivity.this,ZhuActivity.class));
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
