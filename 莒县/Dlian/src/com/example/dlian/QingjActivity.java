package com.example.dlian;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

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

public class QingjActivity extends Activity {
	Button btntemp,btnhum,btnco2,btnill,btnsmoke,btnair,btnpm25,btngas,btnman,btnd,btnx,btnk,btng,btnbjd,btnfan,btndoor,btnlamp,btntv,btndvd,btnkt,btnzncz,btnjsq;
	String strdx="d";
	String strkg="k";
	String str="temp";
	ImageView ivfan,ivkg;
	EditText etsj;
	boolean kg=false,kai=false,guan=false,bjd=false,fan=false,door=false,lamp=false,tv=false,dvd=false,kt=false;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qingj);
		btntemp=(Button)findViewById(R.id.btntemp);
		btnhum=(Button)findViewById(R.id.btnhum);
		btnco2=(Button)findViewById(R.id.btnco2);
		btnill=(Button)findViewById(R.id.btnill);
		btnsmoke=(Button)findViewById(R.id.btnsmoke);
		btnair=(Button)findViewById(R.id.btnair);
		btnpm25=(Button)findViewById(R.id.btnpm25);
		btngas=(Button)findViewById(R.id.btngas);
		btnman=(Button)findViewById(R.id.btnman);
		btnd=(Button)findViewById(R.id.btnd);
		btnx=(Button)findViewById(R.id.btnx);
		btnk=(Button)findViewById(R.id.btnk);
		btng=(Button)findViewById(R.id.btng);
		btnbjd=(Button)findViewById(R.id.btnbjd);
		btnfan=(Button)findViewById(R.id.btnfan);
		btndoor=(Button)findViewById(R.id.btndoor);
		btnlamp=(Button)findViewById(R.id.btnlamp);
		btntv=(Button)findViewById(R.id.btntv);
		btndvd=(Button)findViewById(R.id.btndvd);
		btnkt=(Button)findViewById(R.id.btnkt);
		btnzncz=(Button)findViewById(R.id.btnzncz);
		btnjsq=(Button)findViewById(R.id.btnjsq);
		etsj=(EditText)findViewById(R.id.etsj);
		ivfan=(ImageView)findViewById(R.id.ivfan3);
		ivkg=(ImageView)findViewById(R.id.ivqkg);
		ivfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent(QingjActivity.this,ZongActivity.class));	
			}
		});
		ivkg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					if (etsj.getText().toString().isEmpty()) {
						Toast.makeText(QingjActivity.this, "阈值不能为空", 0).show();
					}else if (!etsj.getText().toString().matches("[0-9]+")) {
						Toast.makeText(QingjActivity.this, "阈值不正确", 0).show();
					}else {
						ivkg.setImageResource(R.drawable.c);
						int text=Integer.parseInt(etsj.getText().toString());
						if (str=="temp"&&strdx=="d"&&strkg=="k"&&CgqActivity.temp>text) {
							kai=true;
						}else if (str=="temp"&&strdx=="x"&&strkg=="k"&&CgqActivity.temp<text) {
							kai=true;
						}else if (str=="temp"&&strdx=="d"&&strkg=="g"&&CgqActivity.temp>text) {
							guan=true;
						}else if (str=="temp"&&strdx=="x"&&strkg=="g"&&CgqActivity.temp<text) {
							guan=true;
						}else if (str=="hum"&&strdx=="d"&&strkg=="k"&&CgqActivity.hum>text) {
							kai=true;
						}else if (str=="hum"&&strdx=="x"&&strkg=="k"&&CgqActivity.hum<text) {
							kai=true;
						}else if (str=="hum"&&strdx=="d"&&strkg=="g"&&CgqActivity.hum>text) {
							guan=true;
						}else if (str=="hum"&&strdx=="x"&&strkg=="g"&&CgqActivity.hum<text) {
							guan=true;
						}else if (str=="co2"&&strdx=="d"&&strkg=="k"&&CgqActivity.co2>text) {
							kai=true;
						}else if (str=="co2"&&strdx=="x"&&strkg=="k"&&CgqActivity.co2<text) {
							kai=true;
						}else if (str=="co2"&&strdx=="d"&&strkg=="g"&&CgqActivity.co2>text) {
							guan=true;
						}else if (str=="co2"&&strdx=="x"&&strkg=="g"&&CgqActivity.co2<text) {
							guan=true;
						}else if (str=="ill"&&strdx=="d"&&strkg=="k"&&CgqActivity.ill>text) {
							kai=true;
						}else if (str=="ill"&&strdx=="x"&&strkg=="k"&&CgqActivity.ill<text) {
							kai=true;
						}else if (str=="ill"&&strdx=="d"&&strkg=="g"&&CgqActivity.ill>text) {
							guan=true;
						}else if (str=="ill"&&strdx=="x"&&strkg=="g"&&CgqActivity.ill<text) {
							guan=true;
						}else if (str=="smoke"&&strdx=="d"&&strkg=="k"&&CgqActivity.smoke>text) {
							kai=true;
						}else if (str=="smoke"&&strdx=="x"&&strkg=="k"&&CgqActivity.smoke<text) {
							kai=true;
						}else if (str=="smoke"&&strdx=="d"&&strkg=="g"&&CgqActivity.smoke>text) {
							guan=true;
						}else if (str=="smoke"&&strdx=="x"&&strkg=="g"&&CgqActivity.smoke<text) {
							guan=true;
						}else if (str=="air"&&strdx=="d"&&strkg=="k"&&CgqActivity.air>text) {
							kai=true;
						}else if (str=="air"&&strdx=="x"&&strkg=="k"&&CgqActivity.air<text) {
							kai=true;
						}else if (str=="air"&&strdx=="d"&&strkg=="g"&&CgqActivity.air>text) {
							guan=true;
						}else if (str=="air"&&strdx=="x"&&strkg=="g"&&CgqActivity.air<text) {
							guan=true;
						}else if (str=="pm25"&&strdx=="d"&&strkg=="k"&&CgqActivity.pm25>text) {
							kai=true;
						}else if (str=="pm25"&&strdx=="x"&&strkg=="k"&&CgqActivity.pm25<text) {
							kai=true;
						}else if (str=="pm25"&&strdx=="d"&&strkg=="g"&&CgqActivity.pm25>text) {
							guan=true;
						}else if (str=="pm25"&&strdx=="x"&&strkg=="g"&&CgqActivity.pm25<text) {
							guan=true;
						}else if (str=="gas"&&strdx=="d"&&strkg=="k"&&CgqActivity.gas>text) {
							kai=true;
						}else if (str=="gas"&&strdx=="x"&&strkg=="k"&&CgqActivity.gas<text) {
							kai=true;
						}else if (str=="gas"&&strdx=="d"&&strkg=="g"&&CgqActivity.gas>text) {
							guan=true;
						}else if (str=="gas"&&strdx=="x"&&strkg=="g"&&CgqActivity.gas<text) {
							guan=true;
						}else if (str=="man"&&strdx=="d"&&strkg=="k"&&CgqActivity.man>text) {
							kai=true;
						}else if (str=="man"&&strdx=="x"&&strkg=="k"&&CgqActivity.man<text) {
							kai=true;
						}else if (str=="man"&&strdx=="d"&&strkg=="g"&&CgqActivity.man>text) {
							guan=true;
						}else if (str=="man"&&strdx=="x"&&strkg=="g"&&CgqActivity.man<text) {
							guan=true;
						}
						
					}
				}else {
					ivkg.setImageResource(R.drawable.kga);
					str="";
					strdx="";
					strkg="";
					kai=false;
					guan=false;
				}
				
			}
		});
		btntemp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtnk);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="temp";
				}else {
					str="";
					btntemp.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnhum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtnk);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="hum";
				}else {
					str="";
					btnhum.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnco2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtnk);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="co2";
				}else {
					str="";
					btnco2.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnill.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtnk);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="ill";
				}else {
					str="";
					btnill.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnsmoke.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtnk);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="smoke";
				}else {
					str="";
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnair.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtnk);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="air";
				}else {
					str="";
					btnair.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnpm25.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtnk);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="pm25";
				}else {
					str="";
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btngas.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtnk);
					btnman.setBackgroundResource(R.drawable.qjbtn);
					str="gas";
				}else {
					str="";
					btngas.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnman.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntemp.setBackgroundResource(R.drawable.qjbtn);
					btnhum.setBackgroundResource(R.drawable.qjbtn);
					btnco2.setBackgroundResource(R.drawable.qjbtn);
					btnill.setBackgroundResource(R.drawable.qjbtn);
					btnsmoke.setBackgroundResource(R.drawable.qjbtn);
					btnair.setBackgroundResource(R.drawable.qjbtn);
					btnpm25.setBackgroundResource(R.drawable.qjbtn);
					btngas.setBackgroundResource(R.drawable.qjbtn);
					btnman.setBackgroundResource(R.drawable.qjbtnk);
					str="man";
				}else {
					str="";
					btnman.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btnd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnd.setBackgroundResource(R.drawable.qjbtnk);
					btnx.setBackgroundResource(R.drawable.qjbtn);
					strdx="d";
				}else {
					btnd.setBackgroundResource(R.drawable.qjbtn);
					strdx="";
				}
				
			}
		});
		btnx.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnd.setBackgroundResource(R.drawable.qjbtn);
					btnx.setBackgroundResource(R.drawable.qjbtnk);
					strdx="x";
				}else {
					btnx.setBackgroundResource(R.drawable.qjbtn);
					strdx="";
				}
				
			}
		});
		btnk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnk.setBackgroundResource(R.drawable.qjbtnk);
					btng.setBackgroundResource(R.drawable.qjbtn);
					strkg="k";
				}else {
					btnk.setBackgroundResource(R.drawable.qjbtn);
					strkg="";
				}
				
			}
		});
		btng.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnk.setBackgroundResource(R.drawable.qjbtn);
					btng.setBackgroundResource(R.drawable.qjbtnk);
					strkg="g";
				}else {
					btng.setBackgroundResource(R.drawable.qjbtn);
					strkg="";
				}
				
			}
		});
		btnbjd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnbjd.setBackgroundResource(R.drawable.qjbtnk);
						bjd=true;
						new Thread(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								while(bjd){
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
										// TODO: handle exception
									}
									if (kai) {
										ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
									}else if (guan) {
										ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
									}
								}
							}
						}).start();	
				}else {
					btnbjd.setBackgroundResource(R.drawable.qjbtn);
					bjd=false;
				}
			}
		});
		btnfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnfan.setBackgroundResource(R.drawable.qjbtnk);
					fan=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(fan){
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kai) {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else if (guan) {
									ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();	
				}else {
					fan=false;
					btnfan.setBackgroundResource(R.drawable.qjbtn);
				}
			}
		});
		btndoor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btndoor.setBackgroundResource(R.drawable.qjbtnk);
					door=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(door){
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kai) {
									ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
								}else if (guan) {
								}
							}
						}
					}).start();	
				}else {
					btndoor.setBackgroundResource(R.drawable.qjbtn);
					door=false;
				}
			}
		});
		btnlamp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btnlamp.setBackgroundResource(R.drawable.qjbtnk);
					lamp=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(lamp){
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kai) {
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
								}else if (guan) {
									ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
								}
							}
						}
					}).start();	
				}else {
					btnlamp.setBackgroundResource(R.drawable.qjbtn);
					lamp=false;
				}
			}
		});
		btntv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					btntv.setBackgroundResource(R.drawable.qjbtnk);
					tv=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(tv){
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kai) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8", ConstantUtil.OPEN);
								}else if (guan) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8", ConstantUtil.CLOSE);
								}
							}
						}
					}).start();	
				}else {
					btntv.setBackgroundResource(R.drawable.qjbtn);
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
					btndvd.setBackgroundResource(R.drawable.qjbtnk);
					dvd=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(dvd){
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kai) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.OPEN);
								}else if (guan) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.CLOSE);
								}
							}
						}
					}).start();	
				}else {
					btndvd.setBackgroundResource(R.drawable.qjbtn);
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
					btnkt.setBackgroundResource(R.drawable.qjbtnk);
					kt=true;
					new Thread(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							while(kt){
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (kai) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.OPEN);
								}else if (guan) {
									ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.CLOSE);
								}
							}
						}
					}).start();	
				}else {
					btnkt.setBackgroundResource(R.drawable.qjbtn);
					kt=false;
				}
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_qingj, menu);
		return true;
	}

}
