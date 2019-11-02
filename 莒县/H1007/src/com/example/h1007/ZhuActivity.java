package com.example.h1007;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class ZhuActivity extends Activity {
	TextView tvtemp,tvhum,tvsmoke,tvill,tvair,tvco2,tvpm25,tvgas,tvman;
	ImageView imbjd,imlamp,imfan,imcl,imdoor;
	EditText etfashe;
	Button btnfashe;
	RadioButton rabt,rafd,ralj;
	public static float temp,hum,smoke,ill,gas,pm25,co2,man,air;
	boolean kg=false;
	Handler handler;
	Runnable btrunnable,fdrunRunnable,ljRunnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhu);
		tvtemp=(TextView)findViewById(R.id.text1);
		tvhum=(TextView)findViewById(R.id.text2);
		tvco2=(TextView)findViewById(R.id.text3);
		tvill=(TextView)findViewById(R.id.text4);
		tvsmoke=(TextView)findViewById(R.id.text5);
		tvair=(TextView)findViewById(R.id.text6);
		tvpm25=(TextView)findViewById(R.id.text7);
		tvgas=(TextView)findViewById(R.id.text8);
		tvman=(TextView)findViewById(R.id.text9);
		
		imbjd=(ImageView)findViewById(R.id.imbjd);
		imlamp=(ImageView)findViewById(R.id.Imlamp);
		imfan=(ImageView)findViewById(R.id.Imfan);
		imcl=(ImageView)findViewById(R.id.Imcl);
		imdoor=(ImageView)findViewById(R.id.Imdoor);
		etfashe=(EditText)findViewById(R.id.edfashe);
		btnfashe=(Button)findViewById(R.id.butfashe);
		
		rabt=(RadioButton)findViewById(R.id.Rabt);
		rafd=(RadioButton)findViewById(R.id.rafd);
		ralj=(RadioButton)findViewById(R.id.Ralj);
		
		ControlUtils.setUser("bizideal", "123456", "18.1.10.2");
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
							Toast.makeText(ZhuActivity.this,"网络连接成功", Toast.LENGTH_LONG).show();
						}else if (lj.equals(ConstantUtil.FAILURE)) {
							Toast.makeText(ZhuActivity.this,"网络连接失败", Toast.LENGTH_LONG).show();
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
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		imbjd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imbjd.setImageResource(R.drawable.bjdk);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imbjd.setImageResource(R.drawable.bjd);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imlamp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imlamp.setImageResource(R.drawable.lampk);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imlamp.setImageResource(R.drawable.lamp);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imfan.setImageResource(R.drawable.fank);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imfan.setImageResource(R.drawable.fan);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imcl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imcl.setImageResource(R.drawable.clk);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}else {
					imcl.setImageResource(R.drawable.cl);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}
			}
		});
		imdoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imdoor.setImageResource(R.drawable.doork);
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					imdoor.setImageResource(R.drawable.door);
				}
			}
		});
		btnfashe.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int fashe=Integer.parseInt(etfashe.getText().toString());
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "fashe", ConstantUtil.OPEN);
			}
		});
		rafd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				rabt.setChecked(false);
				ralj.setChecked(false);
			}
		});
		ralj.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				rabt.setChecked(false);
				rafd.setChecked(false);
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		rabt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				rafd.setChecked(false);
				ralj.setChecked(false);
			}
		});
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 0x0001:
					if (rabt.isChecked()) {
						if (ill>1200) {
					    ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
						}
						if (ill<300) {
							ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}
						handler.postDelayed(btrunnable, 2000);
					}
					break;
				case 0x0002:
					if (rafd.isChecked()) {
						if (man==1) {
							ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}
						handler.postDelayed(fdrunRunnable, 2000);
					}
					break;
				case 0x0003:
					if (ralj.isChecked()) {
						if (pm25>75) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}
						handler.postDelayed(ljRunnable, 2000);
					}
					break;

				default:
					break;
				}
			}
		};
		btrunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (rabt.isChecked()) {
					handler.sendEmptyMessage(0x0001);
				}
			}
		};
		fdrunRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (rafd.isChecked()) {
					handler.sendEmptyMessage(0x0002);
				}
			}
		};
		ljRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (ralj.isChecked()) {
					handler.sendEmptyMessage(0x0003);
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_zhu, menu);
		return true;
	}

}
