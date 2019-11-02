package com.example.a0827;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Activity {
	ImageView imlamp,imdoor,imfan,imbjd;
	Button btnkt,btntv,btndvd,btnclk,btnclg,btnclt,btnsb;
	TextView tvtemp,tvhum,tvsmoke,tvpm25,tvill,tvair,tvco2,tvman,tvgas;
	CheckBox chly,chwd,chzdy,chaf;
	EditText etlian;
	Spinner spws,spdx,spkz;
	Handler handler;
	Runnable lyRunnable,wdRunnable,afRunnable,zdyRunnable;
	boolean kg=false;
	public static float temp,hum,smoke,ill,gas,air,co2,pm25,man;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jiben);
		imlamp=(ImageView)findViewById(R.id.imlamp);
		imdoor=(ImageView)findViewById(R.id.Imdoor);
		imfan=(ImageView)findViewById(R.id.Imfan);
		imbjd=(ImageView)findViewById(R.id.Imbjd);

		btntv=(Button)findViewById(R.id.Buttv);
		btndvd=(Button)findViewById(R.id.Butdvd);
		btnkt=(Button)findViewById(R.id.butkt);
		btnclk=(Button)findViewById(R.id.Butclk);
		btnclt=(Button)findViewById(R.id.Butclt);
		btnclg=(Button)findViewById(R.id.Butclg);
		btnsb=(Button)findViewById(R.id.butzt);

		tvtemp=(TextView)findViewById(R.id.text1);
		tvhum=(TextView)findViewById(R.id.text2);
		tvgas=(TextView)findViewById(R.id.text3);
		tvill=(TextView)findViewById(R.id.text4);
		tvpm25=(TextView)findViewById(R.id.text5);
		tvair=(TextView)findViewById(R.id.text6);
		tvsmoke=(TextView)findViewById(R.id.text7);
		tvco2=(TextView)findViewById(R.id.text8);
		tvman=(TextView)findViewById(R.id.text9);

		chly=(CheckBox)findViewById(R.id.chly);
		chwd=(CheckBox)findViewById(R.id.Chwd);
		chaf=(CheckBox)findViewById(R.id.CheckBox01);
		chzdy=(CheckBox)findViewById(R.id.Chzdy);
		etlian=(EditText)findViewById(R.id.edshu);
		spdx=(Spinner)findViewById(R.id.Spdx);
		spws=(Spinner)findViewById(R.id.spws);
		spkz=(Spinner)findViewById(R.id.Spdq);

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
						if (!TextUtils.isEmpty(bean.getGas())) {
							tvgas.setText(bean.getGas());
						}
						if (!TextUtils.isEmpty(bean.getIllumination())) {
							tvill.setText(bean.getIllumination());
						}
						if (!TextUtils.isEmpty(bean.getPM25())) {
							tvpm25.setText(bean.getPM25());
						}
						if (!TextUtils.isEmpty(bean.getAirPressure())) {
							tvair.setText(bean.getAirPressure());
						}
						if (!TextUtils.isEmpty(bean.getSmoke())) {
							tvsmoke.setText(bean.getSmoke());
						}
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
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
		imlamp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imlamp.setImageResource(R.drawable.lamp_unpressed);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imlamp.setImageResource(R.drawable.lamp);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imdoor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imdoor.setImageResource(R.drawable.mj_y);
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					imdoor.setImageResource(R.drawable.mj_l);

				}
			}
		});
		imfan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imfan.setImageResource(R.drawable.fan_y);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imfan.setImageResource(R.drawable.fan_l);
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
					imbjd.setImageResource(R.drawable.bjd_y);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imbjd.setImageResource(R.drawable.bjd_l);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		btnkt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
				}
			}
		});
		btndvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
				}
			}
		});
		btntv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
				}else {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
				}
			}
		});
		btnclk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		btnclt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		btnclg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		chly.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.CLOSE);
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (true) {
								try {
									Thread.sleep(2000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (pm25>75) {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				}
			}
		});
		chwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"", ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"", ConstantUtil.OPEN);
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (true) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"", ConstantUtil.OPEN);
								ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}
							}
					}).start();
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							try {
								Thread.sleep(2000);
								
							} catch (Exception e) {
								// TODO: handle exception
							}
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}
					}).start();
				}
			}
		});
		chaf.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (true) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (man==1) {
									ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
									ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				}
			}
		});
		chzdy.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					if (etlian.getText().toString().equals("")) {
						Toast.makeText(JibenActivity.this, "输入数据为空", Toast.LENGTH_LONG).show();
						chzdy.setChecked(false);
					}else if (!etlian.getText().toString().matches("[0-9]+")) {
						Toast.makeText(JibenActivity.this, "输入数据有误", Toast.LENGTH_LONG).show();
						chzdy.setChecked(false);
					}else {
						String v1=spws.getSelectedItem().toString();
						String v2=spdx.getSelectedItem().toString();
						String v3=spkz.getSelectedItem().toString();
						int v4=Integer.parseInt(etlian.getText().toString());
						if (v1.equals("温度")&&v2.equals(">")&&temp>v4&&v3.equals("风扇打开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals("<=")&&temp<=v4&&v3.equals("风扇打开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals(">")&&temp>v4&&v3.equals("射灯打开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("温度")&&v2.equals("<=")&&temp<=v4&&v3.equals("射灯打开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals(">")&&ill>v4&&v3.equals("风扇打开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals("<=")&&ill<=v4&&v3.equals("风扇打开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals(">")&&ill>v4&&v3.equals("射灯打开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}else if (v1.equals("光照")&&v2.equals("<=")&&ill<=v4&&v3.equals("射灯打开")) {
							ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						}
					}
				}
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
