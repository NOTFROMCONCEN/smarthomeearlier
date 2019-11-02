package com.example.d0924;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MoshiActivity extends Activity {
	ImageView immoshi;
	Button btntemp,btnsmoke,btnhum,btnair,btnpm25,btnco2,btnill,btnman,btngas,btnda,btnxiao,btnk,btng,
	btnlamp,btndoor,btnfan,btncha,btnbjd,btntv,btndvd,btnjiashi,btnkt;
	EditText etmoshi;
	String str;
	String sssj="temp-hum-gas-ill-pm25-air-smoke-co2-man";
	String ssdx="da,xiao";
	String sskg="kq,guanb";
	boolean kg=false,qjk=false,qjg=false,dq=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moshi);
		immoshi=(ImageView)findViewById(R.id.imqingjing);
		etmoshi=(EditText)findViewById(R.id.edqj);

		btntemp=(Button)findViewById(R.id.but1);
		btnhum=(Button)findViewById(R.id.But2);
		btngas=(Button)findViewById(R.id.But3);
		btnill=(Button)findViewById(R.id.But4);
		btnpm25=(Button)findViewById(R.id.But5);
		btnair=(Button)findViewById(R.id.But6);
		btnsmoke=(Button)findViewById(R.id.But7);
		btnco2=(Button)findViewById(R.id.But8);
		btnman=(Button)findViewById(R.id.But9);
		btnlamp=(Button)findViewById(R.id.Butt1);
		btndoor=(Button)findViewById(R.id.Butt2);
		btnfan=(Button)findViewById(R.id.Butt3);
		btncha=(Button)findViewById(R.id.Butt4);
		btnbjd=(Button)findViewById(R.id.Butt5);
		btntv=(Button)findViewById(R.id.Butt6);
		btndvd=(Button)findViewById(R.id.Butt7);
		btnjiashi=(Button)findViewById(R.id.Butt8);
		btnkt=(Button)findViewById(R.id.Butt9);

		btnda=(Button)findViewById(R.id.Butdayu);
		btnxiao=(Button)findViewById(R.id.Butxiaoyu);
		btnk=(Button)findViewById(R.id.Butqjk);
		btng=(Button)findViewById(R.id.Butqjg);


				immoshi.setOnClickListener(new OnClickListener() {
		
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						kg=!kg;
						if (kg) {
							if (etmoshi.getText().toString().equals("")) {
								Toast.makeText(MoshiActivity.this, "输入数据不能为空", Toast.LENGTH_LONG).show();
								kg=false;
							}else if (!etmoshi.getText().toString().matches("[0-9]+")) {
								Toast.makeText(MoshiActivity.this, "请输入正确的阈值", Toast.LENGTH_LONG).show();
								kg=false;
							}else {
								immoshi.setImageResource(R.drawable.d_k);
								int fazhi=Integer.parseInt(etmoshi.getText().toString());
								if (sssj=="temp"&&ssdx=="da"&&JibenActivity.temp>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="temp"&&ssdx=="xiao"&&JibenActivity.temp<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="temp"&&ssdx=="da"&&JibenActivity.temp>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="temp"&&ssdx=="xiao"&&JibenActivity.temp<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="hum"&&ssdx=="da"&&JibenActivity.hum>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="hum"&&ssdx=="xiao"&&JibenActivity.hum<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="hum"&&ssdx=="da"&&JibenActivity.hum>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="hum"&&ssdx=="xiao"&&JibenActivity.hum<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="gas"&&ssdx=="da"&&JibenActivity.gas>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="gas"&&ssdx=="xiao"&&JibenActivity.gas<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="gas"&&ssdx=="da"&&JibenActivity.gas>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="gas"&&ssdx=="xiao"&&JibenActivity.gas<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="ill"&&ssdx=="da"&&JibenActivity.ill>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="ill"&&ssdx=="xiao"&&JibenActivity.ill<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="ill"&&ssdx=="da"&&JibenActivity.ill>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="ill"&&ssdx=="xiao"&&JibenActivity.ill<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="pm25"&&ssdx=="da"&&JibenActivity.pm25>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="pm25"&&ssdx=="xiao"&&JibenActivity.pm25<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="pm25"&&ssdx=="da"&&JibenActivity.pm25>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="pm25"&&ssdx=="xiao"&&JibenActivity.pm25<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="air"&&ssdx=="da"&&JibenActivity.air>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="air"&&ssdx=="xiao"&&JibenActivity.air<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="air"&&ssdx=="da"&&JibenActivity.air>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="air"&&ssdx=="xiao"&&JibenActivity.air<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="smoke"&&ssdx=="da"&&JibenActivity.smoke>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="smoke"&&ssdx=="xiao"&&JibenActivity.smoke<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="smoke"&&ssdx=="da"&&JibenActivity.smoke>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="smoke"&&ssdx=="xiao"&&JibenActivity.smoke<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="co2"&&ssdx=="da"&&JibenActivity.co2>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="co2"&&ssdx=="xiao"&&JibenActivity.co2<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="co2"&&ssdx=="da"&&JibenActivity.co2>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="co2"&&ssdx=="xiao"&&JibenActivity.co2<fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="man"&&ssdx=="da"&&JibenActivity.man>fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="man"&&ssdx=="xiao"&&JibenActivity.man<fazhi&&sskg=="kq") {
									qjk=true;
								}else if (sssj=="man"&&ssdx=="da"&&JibenActivity.man>fazhi&&sskg=="guanb") {
									qjg=true;
								}else if (sssj=="man"&&ssdx=="xiao"&&JibenActivity.man<fazhi&&sskg=="guanb") {
									qjg=true;
								}
							}
						}else {
							immoshi.setImageResource(R.drawable.d_g);
							qjk=false;
							qjg=false;
							
						}
					}
				});
//		immoshi.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				kg=!kg;
//				if (kg) {
//					if (etmoshi.getText().toString().equals("")) {
//						Toast.makeText(MoshiActivity.this, "输入数据为空", Toast.LENGTH_LONG).show();
//					}else if (!etmoshi.getText().toString().matches("[0-9]+")) {
//						Toast.makeText(MoshiActivity.this, "输入的数据有误", Toast.LENGTH_LONG).show();
//					}else {
//						immoshi.setBackgroundResource(R.drawable.d_k);
//						int fazhi=Integer.parseInt(etmoshi.getText().toString());
//						if (btntemp.isClickable()&&btnda.isClickable()&&JibenActivity.temp>fazhi&&btnk.isClickable()) {
//							qjk=true;
//						}else if (btntemp.isClickable()&&btnda.isClickable()&&JibenActivity.temp>fazhi&&btng.isClickable()) {
//							qjg=true;
//						}
//					}
//				}else {
//					immoshi.setImageResource(R.drawable.d_g);
//					qjg=false;
//					qjk=false;
//				}
//			}
//		});
		btntemp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjx);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("temp"));
			}
		});
		btnhum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjx);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("hum"));
			}
		});
		btngas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjx);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("gas"));
			}
		});
		btnill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjx);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);		
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("ill"));
			}
		});
		btnpm25.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjx);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("pm25"));
			}
		});
		btnair.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjx);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("air"));
			}
		});
		btnsmoke.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjx);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("smoke"));
			}
		});
		btnco2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjx);
				btnman.setBackgroundResource(R.drawable.d_qjm);
				sssj.substring(sssj.indexOf("co2"));
			}
		});
		btnman.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btntemp.setBackgroundResource(R.drawable.d_qjm);
				btnhum.setBackgroundResource(R.drawable.d_qjm);
				btngas.setBackgroundResource(R.drawable.d_qjm);
				btnill.setBackgroundResource(R.drawable.d_qjm);
				btnpm25.setBackgroundResource(R.drawable.d_qjm);
				btnair.setBackgroundResource(R.drawable.d_qjm);
				btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				btnco2.setBackgroundResource(R.drawable.d_qjm);
				btnman.setBackgroundResource(R.drawable.d_qjx);
				sssj.substring(sssj.indexOf("man"));
			}
		});
		btnda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnda.setBackgroundResource(R.drawable.d_qjx);
				btnxiao.setBackgroundResource(R.drawable.d_qjm);
				ssdx.substring(ssdx.indexOf("da"));

			}
		});
		btnxiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnda.setBackgroundResource(R.drawable.d_qjm);
				btnxiao.setBackgroundResource(R.drawable.d_qjx);
				ssdx.substring(ssdx.indexOf("xiao"));

			}
		});
		btnk.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnk.setBackgroundResource(R.drawable.d_qjx);
				btng.setBackgroundResource(R.drawable.d_qjm);
				sskg.substring(sskg.indexOf("kq"));

			}
		});
		btng.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnk.setBackgroundResource(R.drawable.d_qjm);
				btng.setBackgroundResource(R.drawable.d_qjx);
				sskg.substring(sskg.indexOf("guanb"));

			}
		});
		btnlamp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btnlamp.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
					}	
				}else {
					btnlamp.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btndoor.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btndoor.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
					}	
				}else {
					btndoor.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnfan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btnfan.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
					}	
				}else {
					btnfan.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnbjd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btnbjd.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
					}	
				}else {
					btnbjd.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btntv.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btntv.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"电视开",ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"电视关",ConstantUtil.CLOSE);
					}	
				}else {
					btntv.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btndvd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btndvd.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"dvd开",ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"dvd关",ConstantUtil.CLOSE);
					}	
				}else {
					btndvd.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnkt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dq=!dq;
				if (dq) {
					btnkt.setBackgroundResource(R.drawable.d_qjx);
					if (qjk) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"空调开",ConstantUtil.OPEN);
					}else if (qjg) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"空调关",ConstantUtil.CLOSE);
					}	
				}else {
					btnkt.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_moshi, menu);
		return true;
	}

}
