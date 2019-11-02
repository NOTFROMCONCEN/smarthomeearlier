package com.example.tu;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btnzhe,btnzhu,btnjiu,btnbing,btndan,btnshu;
	View myzhe,myzhu,myjiu,mybing,mydanzhu,myshu;
	TextView tvtemp,tvhum,tvsmoke,tvair,tvco2,tvpm25,tvman,tvgas,tvill;
	public static float temp,hum,smoke,gas,co2,pm25,man,air,ill;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnzhe=(Button)findViewById(R.id.butzhe);
		btnzhu=(Button)findViewById(R.id.Butzhu);
		btnjiu=(Button)findViewById(R.id.Butjiu);
		btnbing=(Button)findViewById(R.id.Butbing);
		btndan=(Button)findViewById(R.id.Butdan);
		btnshu=(Button)findViewById(R.id.Butshu);
		mybing=(View)findViewById(R.id.mybing);
		myzhe=(View)findViewById(R.id.myzhe);
		myzhu=(View)findViewById(R.id.myZhu);
		myjiu=(View)findViewById(R.id.myjiu);
		myshu=(View)findViewById(R.id.myshuzhu);
		mydanzhu=(View)findViewById(R.id.mydanzhu);
		tvtemp=(TextView)findViewById(R.id.Text1);
		tvhum=(TextView)findViewById(R.id.Text2);
		tvsmoke=(TextView)findViewById(R.id.Text3);
		tvill=(TextView)findViewById(R.id.Text4);
		tvgas=(TextView)findViewById(R.id.Text5);
		tvair=(TextView)findViewById(R.id.Text6);
		tvpm25=(TextView)findViewById(R.id.Text7);
		tvco2=(TextView)findViewById(R.id.Text8);
		tvman=(TextView)findViewById(R.id.Text9);
		
		ControlUtils.setUser("bizideal","123456","18.1.10.7");
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String lj) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (lj.equals(ConstantUtil.SUCCESS)) {
							Toast.makeText(MainActivity.this, "网络连接成功 ", 0).show();
						}else if (lj.equals(ConstantUtil.FAILURE)) {
							Toast.makeText(MainActivity.this, "网络连接失败 ", 0).show();
						}
					}
				});
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
						if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
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
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		
		
		myzhu.setVisibility(View.INVISIBLE);
		myzhe.setVisibility(View.INVISIBLE);
		myjiu.setVisibility(View.INVISIBLE);
		mybing.setVisibility(View.INVISIBLE);
		mydanzhu.setVisibility(View.INVISIBLE);
		myshu.setVisibility(View.INVISIBLE);
		
		
		btnzhe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myzhu.setVisibility(View.INVISIBLE);
				myzhe.setVisibility(View.VISIBLE);
				myjiu.setVisibility(View.INVISIBLE);
				mybing.setVisibility(View.INVISIBLE);
				mydanzhu.setVisibility(View.INVISIBLE);
				myshu.setVisibility(View.INVISIBLE);
			}
		});
		btnzhu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myzhu.setVisibility(View.VISIBLE);
				myzhe.setVisibility(View.INVISIBLE);
				myjiu.setVisibility(View.INVISIBLE);
				mybing.setVisibility(View.INVISIBLE);
				mydanzhu.setVisibility(View.INVISIBLE);
				myshu.setVisibility(View.INVISIBLE);
			}
		});
		btnjiu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myzhu.setVisibility(View.INVISIBLE);
				myzhe.setVisibility(View.INVISIBLE);
				myjiu.setVisibility(View.VISIBLE);
				mybing.setVisibility(View.INVISIBLE);
				mydanzhu.setVisibility(View.INVISIBLE);
				myshu.setVisibility(View.INVISIBLE);
			}
		});
		btnbing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myzhu.setVisibility(View.INVISIBLE);
				myzhe.setVisibility(View.INVISIBLE);
				myjiu.setVisibility(View.INVISIBLE);
				mybing.setVisibility(View.VISIBLE);
				mydanzhu.setVisibility(View.INVISIBLE);
				myshu.setVisibility(View.INVISIBLE);
			}
		});
		btndan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myzhu.setVisibility(View.INVISIBLE);
				myzhe.setVisibility(View.INVISIBLE);
				myjiu.setVisibility(View.INVISIBLE);
				mybing.setVisibility(View.INVISIBLE);
				mydanzhu.setVisibility(View.VISIBLE);
				myshu.setVisibility(View.INVISIBLE);
			}
		});
		btnshu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myzhu.setVisibility(View.INVISIBLE);
				myzhe.setVisibility(View.INVISIBLE);
				myjiu.setVisibility(View.INVISIBLE);
				mybing.setVisibility(View.INVISIBLE);
				mydanzhu.setVisibility(View.INVISIBLE);
				myshu.setVisibility(View.VISIBLE);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
