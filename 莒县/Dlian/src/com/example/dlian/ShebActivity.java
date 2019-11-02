package com.example.dlian;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ShebActivity extends Activity {
	ImageView ivlamp,ivlampkg,ivdoor,ivdoorkg,ivfan,ivfankg,ivbjd,ivbjdkg,ivtv,ivtvg,ivtvk,ivdvd,ivdvdk,ivdvdg,ivcur,ivcurk,ivcurt,ivcurg,ivkt,ivktk,ivktg,ivfanhui;
	boolean kg=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sheb);
		ivlamp=(ImageView)findViewById(R.id.ivlamp);
		ivlampkg=(ImageView)findViewById(R.id.ivlampkg);
		ivdoor=(ImageView)findViewById(R.id.ivdoor);
		ivdoorkg=(ImageView)findViewById(R.id.ivdoorkg);
		ivfan=(ImageView)findViewById(R.id.ivfan);
		ivfankg=(ImageView)findViewById(R.id.ivfankg);
		ivbjd=(ImageView)findViewById(R.id.ivbjd);
		ivbjdkg=(ImageView)findViewById(R.id.ivbjdkg);
		ivtv=(ImageView)findViewById(R.id.ivtv);
		ivtvg=(ImageView)findViewById(R.id.ivtvg);
		ivtvk=(ImageView)findViewById(R.id.ivtvk);
		ivdvd=(ImageView)findViewById(R.id.ivdvd);
		ivdvdg=(ImageView)findViewById(R.id.ivdvdg);
		ivdvdk=(ImageView)findViewById(R.id.ivdvdk);
		ivcur=(ImageView)findViewById(R.id.ivcur);
		ivcurg=(ImageView)findViewById(R.id.ivcurg);
		ivcurk=(ImageView)findViewById(R.id.ivcurk);
		ivcurt=(ImageView)findViewById(R.id.ivcurt);
		ivktk=(ImageView)findViewById(R.id.ivktk);
		ivktg=(ImageView)findViewById(R.id.ivktg);
		ivkt=(ImageView)findViewById(R.id.ivkt);
		ivfanhui=(ImageView)findViewById(R.id.ivfan2);
		ivfanhui.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ShebActivity.this,ZongActivity.class));
				
			}
		});
		ivlampkg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ivlampkg.setImageResource(R.drawable.c);
					ivlamp.setImageResource(R.drawable.lampk);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ivlampkg.setImageResource(R.drawable.kga);
					ivlamp.setImageResource(R.drawable.lamp);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				
			}
		});
		ivdoorkg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ivdoorkg.setImageResource(R.drawable.c);
					ivdoor.setImageResource(R.drawable.doork);
					ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
				}else {
					ivdoorkg.setImageResource(R.drawable.kga);
					ivdoor.setImageResource(R.drawable.door);
				}
				
			}
		});
		ivfankg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ivfankg.setImageResource(R.drawable.c);
					ivfan.setImageResource(R.drawable.fank);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ivfankg.setImageResource(R.drawable.kga);
					ivfan.setImageResource(R.drawable.fan);
					ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				
			}
		});
		ivbjdkg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ivbjdkg.setImageResource(R.drawable.c);
					ivbjd.setImageResource(R.drawable.bjdk);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
				}else {
					ivbjdkg.setImageResource(R.drawable.kga);
					ivbjd.setImageResource(R.drawable.bjd);
					ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
				}
				
			}
		});
		ivtvk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivtv.setImageResource(R.drawable.tvk);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8", ConstantUtil.OPEN);
				
			}
		});
		ivtvg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivtv.setImageResource(R.drawable.tv);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "8", ConstantUtil.CLOSE);
				
			}
		});
		ivktk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivkt.setImageResource(R.drawable.ktk);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.OPEN);
				
			}
		});
		ivktg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivkt.setImageResource(R.drawable.kt);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "5", ConstantUtil.CLOSE);
				
			}
		});
		ivdvdk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivdvd.setImageResource(R.drawable.dvdk);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.OPEN);
				
			}
		});
		ivdvdg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivdvd.setImageResource(R.drawable.dvd);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "1", ConstantUtil.CLOSE);
				
			}
		});
		ivcurk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivcurk.setImageResource(R.drawable.curkk);
				ivcurg.setImageResource(R.drawable.curg);
				ivcurt.setImageResource(R.drawable.curt);
				ivcur.setImageResource(R.drawable.ivcurk);
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
			}
		});
		ivcurt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivcurk.setImageResource(R.drawable.curk);
				ivcurg.setImageResource(R.drawable.curg);
				ivcurt.setImageResource(R.drawable.curtt);
				ivcur.setImageResource(R.drawable.curtain);
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
			}
		});
		ivcurg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ivcurk.setImageResource(R.drawable.curk);
				ivcurg.setImageResource(R.drawable.curgg);
				ivcurt.setImageResource(R.drawable.curt);
				ivcur.setImageResource(R.drawable.curtain);
				ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sheb, menu);
		return true;
	}

}
