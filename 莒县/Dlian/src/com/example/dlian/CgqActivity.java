package com.example.dlian;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class CgqActivity extends Activity {
	ImageView ivfan;
	TextView tvtemp,tvhum,tvco2,tvill,tvsmoke,tvair,tvpm25,tvgas,tvman;
    public static float temp,hum,co2,ill,smoke,air,pm25,gas,man;
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cgq);
		ivfan=(ImageView)findViewById(R.id.ivfan1);
		tvtemp=(TextView)findViewById(R.id.tvtemp);
		tvhum=(TextView)findViewById(R.id.tvhum);
		tvco2=(TextView)findViewById(R.id.tvco2);
		tvill=(TextView)findViewById(R.id.tvill);
		tvsmoke=(TextView)findViewById(R.id.tvsmoke);
		tvair=(TextView)findViewById(R.id.tvair);
		tvpm25=(TextView)findViewById(R.id.tvpm25);
		tvgas=(TextView)findViewById(R.id.tvgas);
		tvman=(TextView)findViewById(R.id.tvman);
		
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
						}if (!TextUtils.isEmpty(bean.getHumidity())) {
							tvhum.setText(bean.getHumidity());
						}if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
						}if (!TextUtils.isEmpty(bean.getIllumination())) {
							tvill.setText(bean.getIllumination());
						}if (!TextUtils.isEmpty(bean.getSmoke())) {
							tvsmoke.setText(bean.getSmoke());
						}if (!TextUtils.isEmpty(bean.getAirPressure())) {
							tvair.setText(bean.getAirPressure());
						}if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}if (!TextUtils.isEmpty(bean.getGas())) {
							tvgas.setText(bean.getGas());
						}if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvman.setText("无人");
						}else {
							tvman.setText("有人");
						}
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						smoke=Float.parseFloat(tvman.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		ivfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(CgqActivity.this,ZongActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_cgq, menu);
		return true;
	}

}
