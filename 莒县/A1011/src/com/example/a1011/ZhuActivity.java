package com.example.a1011;

import android.app.Activity;
import android.os.Bundle;
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

public class ZhuActivity extends Activity {
	ImageView imbjd, imfan, imlamp, imdoor;
	Button btntv, btndvd, btnkt, btnclk, btnclg, btnclt;
	CheckBox chly, chwd, chzdy, chaf;
	Spinner spws, spdx, spkz;
	TextView tvtemp, tvhum, tvsmoke, tvgas, tvco2, tvill, tvpm25, tvair, tvman;
	public static float temp, hum, smoke, gas, ill, co2, pm25, man, air;
	boolean kg = false;
	EditText etlian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhu);
		imbjd = (ImageView) findViewById(R.id.Imbjd);
		imfan = (ImageView) findViewById(R.id.Imfan);
		imlamp = (ImageView) findViewById(R.id.imlamp);
		imdoor = (ImageView) findViewById(R.id.Imdoor);
		etlian = (EditText) findViewById(R.id.edlian);

		btntv = (Button) findViewById(R.id.Buttv);
		btndvd = (Button) findViewById(R.id.Butdvd);
		btnkt = (Button) findViewById(R.id.butkt);
		btnclk = (Button) findViewById(R.id.Butclk);
		btnclt = (Button) findViewById(R.id.Butclt);
		btnclg = (Button) findViewById(R.id.Butclg);

		chaf = (CheckBox) findViewById(R.id.Chaf);
		chzdy = (CheckBox) findViewById(R.id.Chzdy);
		chly = (CheckBox) findViewById(R.id.chly);
		chwd = (CheckBox) findViewById(R.id.Chwd);

		spdx = (Spinner) findViewById(R.id.Spdx);
		spws = (Spinner) findViewById(R.id.spws);
		spkz = (Spinner) findViewById(R.id.Spkz);
		tvtemp = (TextView) findViewById(R.id.Text1);
		tvhum = (TextView) findViewById(R.id.Text2);
		tvgas = (TextView) findViewById(R.id.Text3);
		tvill = (TextView) findViewById(R.id.Text4);
		tvpm25 = (TextView) findViewById(R.id.Text5);
		tvair = (TextView) findViewById(R.id.Text6);
		tvsmoke = (TextView) findViewById(R.id.Text7);
		tvco2 = (TextView) findViewById(R.id.Text8);
		tvman = (TextView) findViewById(R.id.Text9);

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
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())
								&& bean.getStateHumanInfrared().equals(
										ConstantUtil.CLOSE)) {
							tvman.setText("����");
						} else {
							tvman.setText("����");
						}
						temp = Float.parseFloat(tvtemp.getText().toString());
						hum = Float.parseFloat(tvhum.getText().toString());
						smoke = Float.parseFloat(tvsmoke.getText().toString());
						gas = Float.parseFloat(tvgas.getText().toString());
						ill = Float.parseFloat(tvill.getText().toString());
						air = Float.parseFloat(tvair.getText().toString());
						co2 = Float.parseFloat(tvco2.getText().toString());
						pm25 = Float.parseFloat(tvpm25.getText().toString());
						man = Float.parseFloat(bean.getStateHumanInfrared());
					}
				});
			}
		});
		btnkt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
						ConstantUtil.OPEN);
			}
		});
		btndvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
						ConstantUtil.OPEN);
			}
		});
		btntv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
						ConstantUtil.OPEN);
			}
		});
		btnclg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		btnclk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		btnclt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain,
						ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		imlamp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg = !kg;
				if (kg) {
					imlamp.setImageResource(R.drawable.lamp);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					imlamp.setImageResource(R.drawable.lamp_unpressed);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imdoor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg = !kg;
				if (kg) {
					imdoor.setImageResource(R.drawable.mj_l);
					ControlUtils.control(ConstantUtil.RFID_Open_Door,
							ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				} else {
					imdoor.setImageResource(R.drawable.mj_y);
				}
			}
		});
		imfan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg = !kg;
				if (kg) {
					imfan.setImageResource(R.drawable.fan_l);
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					imfan.setImageResource(R.drawable.fan_y);
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imbjd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg = !kg;
				if (kg) {
					imbjd.setImageResource(R.drawable.bjd_l);
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				} else {
					imbjd.setImageResource(R.drawable.bjd_y);
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		chly.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.Curtain,
							ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
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
							if (pm25 > 75) {
								ControlUtils.control(ConstantUtil.Fan,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						}
					}
				});
			}
		});
		chwd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1",
							ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "2",
							ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "3",
							ConstantUtil.CLOSE);
					ControlUtils.control(ConstantUtil.WarningLight,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Fan,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp,
							ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}

			}
		});
		chaf.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
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
							if (man == 1) {
								ControlUtils.control(ConstantUtil.Lamp,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
								ControlUtils.control(ConstantUtil.WarningLight,
										ConstantUtil.CHANNEL_ALL,
										ConstantUtil.OPEN);
							}
						}
					}
				});
			}
		});
		chzdy.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (etlian.getText().toString().isEmpty()) {
					Toast.makeText(ZhuActivity.this, "��������Ϊ��",
							Toast.LENGTH_LONG).show();
					chzdy.setChecked(false);
				} else if (!etlian.getText().toString().matches("[0-9]+")) {
					Toast.makeText(ZhuActivity.this, "������������",
							Toast.LENGTH_LONG).show();
					chzdy.setChecked(false);
				} else {
					String v1 = spws.getSelectedItem().toString();
					String v2 = spdx.getSelectedItem().toString();
					String v3 = spkz.getSelectedItem().toString();
					int fazhi = Integer.parseInt(etlian.getText().toString());
					if (v1.equals("�¶�") && v2.equals(">") && temp > fazhi
							&& v3.equals("��ƴ�")) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("�¶�") && v2.equals(">")
							&& temp > fazhi && v3.equals("���ȴ�")) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("�¶�") && v2.equals("<=")
							&& temp <= fazhi && v3.equals("��ƴ�")) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("�¶�") && v2.equals("<=")
							&& temp <= fazhi && v3.equals("���ȴ�")) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("����") && v2.equals(">") && ill > fazhi
							&& v3.equals("��ƴ�")) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("����") && v2.equals(">") && ill > fazhi
							&& v3.equals("���ȴ�")) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("����") && v2.equals("<=")
							&& ill <= fazhi && v3.equals("��ƴ�")) {
						ControlUtils.control(ConstantUtil.Lamp,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					} else if (v1.equals("����") && v2.equals("<=")
							&& ill <= fazhi && v3.equals("���ȴ�")) {
						ControlUtils.control(ConstantUtil.Fan,
								ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_zhu, menu);
		return true;
	}

}
