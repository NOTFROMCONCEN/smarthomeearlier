package com.example.d1014;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class KzActivity extends Activity {
	ImageView imlamp,imdoor,imfan,imbjd,imtv,imkt,imdvd,imcl,imlampk,imdoork,imfank,imbjdk,imtvk,imtvg,imdvdk,imdvdg,imktg,imktk,imclk,imclg,imclt,imkzfan;
	boolean kg=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kz);
		imkzfan=(ImageView)findViewById(R.id.imkzf);
		imlamp=(ImageView)findViewById(R.id.imlamp);
		imdoor=(ImageView)findViewById(R.id.Imdoor);
		imfan=(ImageView)findViewById(R.id.Imfan);
		imbjd=(ImageView)findViewById(R.id.Imbjd);
		imtv=(ImageView)findViewById(R.id.imtv);
		imkt=(ImageView)findViewById(R.id.Imkt);
		imdvd=(ImageView)findViewById(R.id.Imdvd);
		imcl=(ImageView)findViewById(R.id.Imcl);
		imlampk=(ImageView)findViewById(R.id.Imlampkg);
		imfank=(ImageView)findViewById(R.id.Imfankg);
		imdoork=(ImageView)findViewById(R.id.Imdoorkg);
		imbjdk=(ImageView)findViewById(R.id.Imbjdkg);
		imtvk=(ImageView)findViewById(R.id.Imtvk);
		imtvg=(ImageView)findViewById(R.id.Imtvg);
		imdvdg=(ImageView)findViewById(R.id.Imdvdg);
		imdvdk=(ImageView)findViewById(R.id.Imdvdk);
		imclg=(ImageView)findViewById(R.id.Imaclg);
		imclk=(ImageView)findViewById(R.id.Imaclk);
		imclt=(ImageView)findViewById(R.id.Imaclt);
		imktg=(ImageView)findViewById(R.id.Imktg);
		imktk=(ImageView)findViewById(R.id.Imktk);
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
//							Toast.makeText(KzActivity.this, "网络连接成功 ", Toast.LENGTH_LONG).show();
//						}else if (lj.equals(ConstantUtil.FAILURE)) {
//							Toast.makeText(KzActivity.this, "网络连接失败 ", Toast.LENGTH_LONG).show();
//						}
//					}
//				});
//			}
//		});
		imkzfan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(KzActivity.this,ZhuActivity.class));
			}
		});
		imlampk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				imlamp.setImageResource(R.drawable.d_lamp);
				imlampk.setImageResource(R.drawable.d_k);
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else {
				imlamp.setImageResource(R.drawable.d_lamp1);
				imlampk.setImageResource(R.drawable.d_g);
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
			}
		});
		imdoork.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imdoork.setImageResource(R.drawable.d_k);
					imdoor.setImageResource(R.drawable.d_mj);
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					imdoork.setImageResource(R.drawable.d_g);
					imdoor.setImageResource(R.drawable.d_mj1);
				
				}
			}
		});
		imfank.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imfan.setImageResource(R.drawable.d_fan);
					imfank.setImageResource(R.drawable.d_k);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imfan.setImageResource(R.drawable.d_fan1);
					imfank.setImageResource(R.drawable.d_g);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imbjdk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imbjd.setImageResource(R.drawable.d_bjd);
					imbjdk.setImageResource(R.drawable.d_k);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					imbjd.setImageResource(R.drawable.d_bjd1);
					imbjdk.setImageResource(R.drawable.d_g);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
			}
		});
		imtvg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imtv.setImageResource(R.drawable.d_tv1);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.OPEN);
				}
			}
		});
		imtvk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imtv.setImageResource(R.drawable.d_tv);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.OPEN);
				}
			}
		});
		imdvdg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imdvd.setImageResource(R.drawable.d_dvd1);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "11", ConstantUtil.OPEN);
				}
			}
		});
		imdvdk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imdvd.setImageResource(R.drawable.d_dvd);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "11", ConstantUtil.OPEN);
				}
			}
		});
		imktg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imkt.setImageResource(R.drawable.d_kt1);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.OPEN);
				}
			}
		});
		imktk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imkt.setImageResource(R.drawable.d_kt);
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.OPEN);
				}
			}
		});
		imclk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		imclg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}
		});
		imclt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_kz, menu);
		return true;
	}

}
