package com.example.d1014;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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

public class JibenActivity extends Activity {
	ImageView imfan;
	TextView tvtemp,tvhum,tvill,tvsmoke,tvgas,tvair,tvco2,tvpm25,tvman;
	public static float temp,hum,smoke,ill,gas,air,co2,pm25,man;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiben);
		imfan=(ImageView)findViewById(R.id.imjibenf);
		tvgas=(TextView)findViewById(R.id.Text1);
		tvill=(TextView)findViewById(R.id.Text2);
		tvair=(TextView)findViewById(R.id.Text3);
		tvhum=(TextView)findViewById(R.id.Text4);
		tvpm25=(TextView)findViewById(R.id.Text5);
		tvsmoke=(TextView)findViewById(R.id.Text6);
		tvtemp=(TextView)findViewById(R.id.Text7);
		tvco2=(TextView)findViewById(R.id.Text8);
		tvman=(TextView)findViewById(R.id.Text9);
//		ControlUtils.setUser("bizideal", "123456", "19.1.10.2");
//		SocketClient.getInstance().creatConnect();
//		SocketClient.getInstance().login(new LoginCallback() {
//			
//			@Override
//			public void onEvent(final String lj) {
//				// TODO Auto-generated method stub
//				runOnUiThread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						if (lj.equals(ConstantUtil.SUCCESS)) {
//							Toast.makeText(JibenActivity.this, "网络连接成功 ", Toast.LENGTH_LONG).show();
//						}else if (lj.equals(ConstantUtil.FAILURE)) {
//							Toast.makeText(JibenActivity.this, "网络连接失败 ", Toast.LENGTH_LONG).show();
//						}
//					}
//				});
//			}
//		});
		
		imfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(JibenActivity.this,ZhuActivity.class));
			}
		});
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
						if (!TextUtils.isEmpty(bean.getHumidity())) {
							tvhum.setText(bean.getHumidity());
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
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							tvair.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvman.setText("无人");
						}else {
							tvman.setText("有人");
						}
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
					 air=Float.parseFloat(tvair.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
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
