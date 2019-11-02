package com.example.d0924;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class KzActivity extends Activity {
	ImageView imlamp,imdoor,imfan,imbjd,imtv,imdvd,imcl,imkt,imlampk,imdoork,imfank,imbjdk,imtvk,imtvg,imdvdk,imdvdg,imclk,imclg,imclt,imktk,imktg,imkzfh;
    Boolean kg=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kz);
		imlamp=(ImageView)findViewById(R.id.imlamp);
		imdoor=(ImageView)findViewById(R.id.Imdoor);
		imfan=(ImageView)findViewById(R.id.Imfan);
		imbjd=(ImageView)findViewById(R.id.Imbjd);
		imtv=(ImageView)findViewById(R.id.imtv);
		imdvd=(ImageView)findViewById(R.id.Imdvd);
		imcl=(ImageView)findViewById(R.id.Imcl);
		imkt=(ImageView)findViewById(R.id.Imkt);
		imlampk=(ImageView)findViewById(R.id.imlampk);
		imdoork=(ImageView)findViewById(R.id.Imdoork);
		imfank=(ImageView)findViewById(R.id.Imfank);
		imbjdk=(ImageView)findViewById(R.id.Imbjdk);
		
		imtvk=(ImageView)findViewById(R.id.imtvk);
		imtvg=(ImageView)findViewById(R.id.imtvg);
		imdvdk=(ImageView)findViewById(R.id.Imdvdk);
		imdvdg=(ImageView)findViewById(R.id.Imdvdg);
		imktk=(ImageView)findViewById(R.id.Imktk);
		imktg=(ImageView)findViewById(R.id.Imktg);
		imclk=(ImageView)findViewById(R.id.Imclk);
		imclg=(ImageView)findViewById(R.id.Imclg);
		imclt=(ImageView)findViewById(R.id.Imclt);
		imkzfh=(ImageView)findViewById(R.id.imkzfh);
		
		imkzfh.setOnClickListener(new OnClickListener() {
			
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
					ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
				}else {
					imlamp.setImageResource(R.drawable.d_lamp1);
					imlampk.setImageResource(R.drawable.d_g);
					ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
				}
			}
		});
		
	    imdoork.setOnClickListener(new OnClickListener() {
	    	
	    	@Override
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		kg=!kg;
	    		if (kg) {
	    			imdoor.setImageResource(R.drawable.d_mj);
	    			imdoork.setImageResource(R.drawable.d_k);
	    			ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
	    		}else {
	    			imdoor.setImageResource(R.drawable.d_mj1);
	    			imdoork.setImageResource(R.drawable.d_g);
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
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"电视关",ConstantUtil.CLOSE);
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
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"电视开",ConstantUtil.OPEN);
				}
			}
		});
	    imdvdg.setOnClickListener(new OnClickListener() {
	    	
	    	@Override
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		kg=!kg;
	    		if (kg) {
	    			imdvd.setImageResource(R.drawable.d_dvd);
	    			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"dvd关",ConstantUtil.CLOSE);
	    		}
	    	}
	    });
	    imdvdk.setOnClickListener(new OnClickListener() {
	    	
	    	@Override
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		kg=!kg;
	    		if (kg) {
	    			imdvd.setImageResource(R.drawable.d_dvd1);
	    			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"dvd开",ConstantUtil.OPEN);
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
	    			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"空调关",ConstantUtil.CLOSE);
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
	    			ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"空调开",ConstantUtil.OPEN);
	    		}
	    	}
	    });
	    imclk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					imcl.setImageResource(R.drawable.d_cl);
					ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				}
			}
		});
	    imclt.setOnClickListener(new OnClickListener() {
	    	
	    	@Override
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		kg=!kg;
	    		if (kg) {
	    			imcl.setImageResource(R.drawable.d_cl1);
	    			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
	    		}
	    	}
	    });
	    imclg.setOnClickListener(new OnClickListener() {
	    	
	    	@Override
	    	public void onClick(View v) {
	    		// TODO Auto-generated method stub
	    		kg=!kg;
	    		if (kg) {
	    			imcl.setImageResource(R.drawable.d_cl1);
	    			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
	    		}
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
