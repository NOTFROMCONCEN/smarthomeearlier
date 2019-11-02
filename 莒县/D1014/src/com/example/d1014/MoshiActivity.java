package com.example.d1014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

public class MoshiActivity extends Activity {
	Button btntemp,btnhum,btnsmoke,btnill,btngas,btnco2,btnpm25,btnair,btnman;
	Button btnda,btnxiao,btnkai,btnguan;
	Button btnlamp,btndoor,btnfan,btncha,btnbjd,btntv,btndvd,btnkt,btnjias;
	ImageView imfan,immoshikg;
	EditText etmoshi;
	String stringshuju="temp,hum,smoke,ill,gas,co2,pm25,air,man";
	String stringdx="da,xiao";
	String stringkg="kai,guan";
	boolean kg=false,qjk=false,qjg=false,bjd=false,door=false,fan=false,cha=false,tv=false,dvd=false,kt=false,jiashi=false,lamp=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moshi);
		btntemp=(Button)findViewById(R.id.buttemp);
		btnhum=(Button)findViewById(R.id.Buthum);
		btnsmoke=(Button)findViewById(R.id.Butsmoke);
		btngas=(Button)findViewById(R.id.Butgas);
		btnill=(Button)findViewById(R.id.Butill);
		btnco2=(Button)findViewById(R.id.Butco2);
		btnpm25=(Button)findViewById(R.id.Butpm25);
		btnair=(Button)findViewById(R.id.Butair);
		btnman=(Button)findViewById(R.id.Butman);

		btnda=(Button)findViewById(R.id.Butda);
		btnxiao=(Button)findViewById(R.id.Butxiao);
		btnkai=(Button)findViewById(R.id.Butdakai);
		btnguan=(Button)findViewById(R.id.Butguanbi);

		btnlamp=(Button)findViewById(R.id.Butlamp);
		btndoor=(Button)findViewById(R.id.Butdoor);
		btnfan=(Button)findViewById(R.id.Butfan);
		btncha=(Button)findViewById(R.id.Butcha);
		btnbjd=(Button)findViewById(R.id.Butbjd);
		btntv=(Button)findViewById(R.id.Buttv);
		btndvd=(Button)findViewById(R.id.Butdvd);
		btnjias=(Button)findViewById(R.id.Butjias);
		btnkt=(Button)findViewById(R.id.Butkt);
		imfan=(ImageView)findViewById(R.id.imqjf);
		immoshikg=(ImageView)findViewById(R.id.immoshikg);
		etmoshi=(EditText)findViewById(R.id.edlian);

		immoshikg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					if (etmoshi.getText().toString().isEmpty()) {
						Toast.makeText(MoshiActivity.this, "输入数据为空", Toast.LENGTH_LONG).show();
						kg=false;
					}else if (!etmoshi.getText().toString().matches("[0-9]+")) {
						Toast.makeText(MoshiActivity.this, "输入数据有误", Toast.LENGTH_LONG).show();
						kg=false;
					}else {
						immoshikg.setImageResource(R.drawable.d_k);
						int fazhi=Integer.parseInt(etmoshi.getText().toString());
						if (stringshuju.equals("temp")&&stringdx=="da"&&JibenActivity.temp>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("temp")&&stringdx=="xiao"&&JibenActivity.temp<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("temp")&&stringdx=="da"&&JibenActivity.temp>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("temp")&&stringdx=="xiao"&&JibenActivity.temp<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("hum")&&stringdx=="da"&&JibenActivity.hum>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("hum")&&stringdx=="xiao"&&JibenActivity.hum<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("hum")&&stringdx=="da"&&JibenActivity.hum>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("hum")&&stringdx=="xiao"&&JibenActivity.hum<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("gas")&&stringdx=="da"&&JibenActivity.gas>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("gas")&&stringdx=="xiao"&&JibenActivity.gas<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("gas")&&stringdx=="da"&&JibenActivity.gas>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("gas")&&stringdx=="xiao"&&JibenActivity.gas<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("ill")&&stringdx=="da"&&JibenActivity.ill>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("ill")&&stringdx=="xiao"&&JibenActivity.ill<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("ill")&&stringdx=="da"&&JibenActivity.ill>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("ill")&&stringdx=="xiao"&&JibenActivity.ill<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("pm25")&&stringdx=="da"&&JibenActivity.pm25>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("pm25")&&stringdx=="xiao"&&JibenActivity.pm25<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("pm25")&&stringdx=="da"&&JibenActivity.pm25>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("pm25")&&stringdx=="xiao"&&JibenActivity.pm25<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("air")&&stringdx=="da"&&JibenActivity.air>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("air")&&stringdx=="xiao"&&JibenActivity.air<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("air")&&stringdx=="da"&&JibenActivity.air>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("air")&&stringdx=="xiao"&&JibenActivity.air<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("smoke")&&stringdx=="da"&&JibenActivity.smoke>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("smoke")&&stringdx=="xiao"&&JibenActivity.smoke<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("smoke")&&stringdx=="da"&&JibenActivity.smoke>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("smoke")&&stringdx=="xiao"&&JibenActivity.smoke<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("co2")&&stringdx=="da"&&JibenActivity.co2>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("co2")&&stringdx=="xiao"&&JibenActivity.co2<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("co2")&&stringdx=="da"&&JibenActivity.co2>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("co2")&&stringdx=="xiao"&&JibenActivity.co2<fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("man")&&stringdx=="da"&&JibenActivity.man>fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("man")&&stringdx=="xiao"&&JibenActivity.man<fazhi&&stringkg=="kai") {
							qjk=true;
						}else if (stringshuju.equals("man")&&stringdx=="da"&&JibenActivity.man>fazhi&&stringkg=="guan") {
							qjg=true;
						}else if (stringshuju.equals("man")&&stringdx=="xiao"&&JibenActivity.man<fazhi&&stringkg=="guan") {
							qjg=true;
						}
					}
				}else {
					immoshikg.setImageResource(R.drawable.d_g);
					qjk=false;
					qjg=false;
				}
			}
		});

		btntemp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjx);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="temp";
				}else {
					stringshuju="";
					btntemp.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnhum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjx);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="hum";
				}else {
					stringshuju="";
					btnhum.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnsmoke.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjx);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="smoke";
				}else {
					stringshuju="";
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btngas.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjx);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="gas";
				}else {
					stringshuju="";
					btngas.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnill.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjx);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="ill";
				}else {
					stringshuju="";
					btnill.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnco2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjx);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="co2";
				}else {
					stringshuju="";
					btnco2.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnpm25.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjx);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="pm25";
				}else {
					stringshuju="";
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnair.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjx);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjm);
					stringshuju="air";
				}else {
					stringshuju="";
					btnair.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});
		btnman.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.d_qjm);
					btnhum.setBackgroundResource(R.drawable.d_qjm);
					btnsmoke.setBackgroundResource(R.drawable.d_qjm);
					btnill.setBackgroundResource(R.drawable.d_qjm);
					btngas.setBackgroundResource(R.drawable.d_qjm);
					btnair.setBackgroundResource(R.drawable.d_qjm);
					btnco2.setBackgroundResource(R.drawable.d_qjm);
					btnpm25.setBackgroundResource(R.drawable.d_qjm);
					btnman.setBackgroundResource(R.drawable.d_qjx);
					stringshuju="man";
				}else {
					stringshuju="";
					btnman.setBackgroundResource(R.drawable.d_qjm);
				}
			}
		});

		btnda.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnda.setBackgroundResource(R.drawable.d_qjx);
					btnxiao.setBackgroundResource(R.drawable.d_qjm);
					stringdx="da";
				}else {
					btnda.setBackgroundResource(R.drawable.d_qjm);
					stringdx="";
				}
			}
		});
		btnxiao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnda.setBackgroundResource(R.drawable.d_qjm);
					btnxiao.setBackgroundResource(R.drawable.d_qjx);
					stringdx="xiao";
				}else {
					btnxiao.setBackgroundResource(R.drawable.d_qjm);
					stringdx="";
				}
			}
		});
		btnkai.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnkai.setBackgroundResource(R.drawable.d_qjx);
					btnguan.setBackgroundResource(R.drawable.d_qjm);
					stringkg="kai";
				}else {
					btnkai.setBackgroundResource(R.drawable.d_qjm);
					stringkg="";
				}
			}
		});
		btnguan.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnkai.setBackgroundResource(R.drawable.d_qjm);
					btnguan.setBackgroundResource(R.drawable.d_qjx);
					stringkg="guan";
				}else {
					btnguan.setBackgroundResource(R.drawable.d_qjm);
					stringkg="";
				}
			}
		});
		btnlamp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnlamp.setBackgroundResource(R.drawable.d_qjx);
					lamp=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (lamp) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else if (qjg) {
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					btnlamp.setBackgroundResource(R.drawable.d_qjm);
					lamp=false;
				}
			}
		});
		btndoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btndoor.setBackgroundResource(R.drawable.d_qjx);
					door=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (door) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
								}else if (qjg) {
						
								}
							}
						}
					}).start();
				}else {
					btndoor.setBackgroundResource(R.drawable.d_qjm);
					door=false;
				}
			}
		});
		btnfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnfan.setBackgroundResource(R.drawable.d_qjx);
					fan=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (fan) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else if (qjg) {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					btnfan.setBackgroundResource(R.drawable.d_qjm);
					fan=false;
				}
			}
		});
		btncha.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btncha.setBackgroundResource(R.drawable.d_qjx);
					cha=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (cha) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
							}
						}
					}).start();
				}else {
					btncha.setBackgroundResource(R.drawable.d_qjm);
					cha=false;
				}
			}
		});
		btnbjd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnbjd.setBackgroundResource(R.drawable.d_qjx);
					bjd=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (bjd) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else if (qjg) {
									ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					btnbjd.setBackgroundResource(R.drawable.d_qjm);
					bjd=false;
				}
			}
		});
		btntv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntv.setBackgroundResource(R.drawable.d_qjx);
					tv=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (tv) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"5", ConstantUtil.OPEN);
								}else if (qjg) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"5", ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					btntv.setBackgroundResource(R.drawable.d_qjm);
					tv=false;
				}
			}
		});
		btndvd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btndvd.setBackgroundResource(R.drawable.d_qjx);
					dvd=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (dvd) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"8", ConstantUtil.OPEN);
								}else if (qjg) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"8", ConstantUtil.OPEN);
								}
							}
						}
					}).start();
				}else {
					btndvd.setBackgroundResource(R.drawable.d_qjm);
					dvd=false;
				}
			}
		});
		btnkt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnkt.setBackgroundResource(R.drawable.d_qjx);
					kt=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (kt) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (qjk) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1", ConstantUtil.OPEN);
								}else if (qjg) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1", ConstantUtil.CLOSE);
								}
							}
						}
					}).start();
				}else {
					btnkt.setBackgroundResource(R.drawable.d_qjm);
					kt=false;
				}
			}
		});
		btnjias.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnjias.setBackgroundResource(R.drawable.d_qjx);
					jiashi=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while (jiashi) {
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								
							}
						}
					}).start();
				}else {
					btnjias.setBackgroundResource(R.drawable.d_qjm);
					jiashi=false;
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
