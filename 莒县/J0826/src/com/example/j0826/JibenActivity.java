package com.example.j0826;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

public class JibenActivity extends Fragment {
	TextView tvtemp,tvhum,tvsmoke,tvco2,tvpm25,tvair,tvill,tvgas,tvman;
	LinearLayout litemp,lihum,lismoke,lico2,lipm25,liair,liill,ligas,liman,litd1,litd2,litd3;
	ImageView imlogo,imbjd,imdoor,imfan,imlamp,imclk,imcllogo,imclt,imclg,imhw,imtd1,imtd2,imtd3,imtv,imdvd,imkt;
	RelativeLayout jb;
	ContentValues cvValues;
	boolean kg=false;
	public static float temp,hum,smoke,gas,ill,air,pm25,co2,man;
	int mx,my;
	SQLiteDatabase db;
	
	OnLongClickListener longdian=new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			v.setClickable(false);
			v.setOnTouchListener(tuozhuai);
			return false;
		}
	};
	OnTouchListener tuozhuai=new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			// TODO Auto-generated method stub
			switch (event.getAction()) {
			case MotionEvent.ACTION_UP:
				v.setOnTouchListener(null);
				break;
			case MotionEvent.ACTION_MOVE:
				mx=(int)event.getRawX();
				my=(int)event.getRawY();
				
				v.layout(mx -v.getWidth()/2, my -v.getWidth()/2,
						mx +v.getHeight()/2, my +v.getHeight()/2);
				break;

			default:
				break;
			}
			return false;
		}
	};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_jiben, container,false);
		tvtemp=(TextView)view.findViewById(R.id.text1);
		tvhum=(TextView)view.findViewById(R.id.text2);
		tvgas=(TextView)view.findViewById(R.id.text3);
		tvill=(TextView)view.findViewById(R.id.text4);
		tvpm25=(TextView)view.findViewById(R.id.text5);
		tvair=(TextView)view.findViewById(R.id.text6);
		tvsmoke=(TextView)view.findViewById(R.id.text7);
		tvco2=(TextView)view.findViewById(R.id.text8);
		tvman=(TextView)view.findViewById(R.id.text9);
		
		litemp=(LinearLayout)view.findViewById(R.id.Litemp);
		lihum=(LinearLayout)view.findViewById(R.id.Lihum);
		lismoke=(LinearLayout)view.findViewById(R.id.Lismoke);
		liill=(LinearLayout)view.findViewById(R.id.Liill);
		ligas=(LinearLayout)view.findViewById(R.id.Ligas);
		lico2=(LinearLayout)view.findViewById(R.id.Lico2);
		lipm25=(LinearLayout)view.findViewById(R.id.Lipm25);
		liair=(LinearLayout)view.findViewById(R.id.Liair);
		liman=(LinearLayout)view.findViewById(R.id.Liman);
		litd1=(LinearLayout)view.findViewById(R.id.Litd1);
		litd2=(LinearLayout)view.findViewById(R.id.Litd2);
		litd3=(LinearLayout)view.findViewById(R.id.Litd3);
		jb=(RelativeLayout)view.findViewById(R.id.jb);
		
		imbjd=(ImageView)view.findViewById(R.id.imbjd);
		imdoor=(ImageView)view.findViewById(R.id.Imdoor);
		imlamp=(ImageView)view.findViewById(R.id.Imlamp);
		imfan=(ImageView)view.findViewById(R.id.Imfan);
		imcllogo=(ImageView)view.findViewById(R.id.imcllogo);
		imclk=(ImageView)view.findViewById(R.id.Imclk);
		imclt=(ImageView)view.findViewById(R.id.Imclt);
		imclg=(ImageView)view.findViewById(R.id.Imclg);
		imhw=(ImageView)view.findViewById(R.id.Imhw);
		imtd1=(ImageView)view.findViewById(R.id.imtd1);
		imtd2=(ImageView)view.findViewById(R.id.Imtd2);
		imtd3=(ImageView)view.findViewById(R.id.Imtd3);
		imkt=(ImageView)view.findViewById(R.id.Imkt);
		imdvd=(ImageView)view.findViewById(R.id.Imdvd);
		imtv=(ImageView)view.findViewById(R.id.imtv);
		imlogo=(ImageView)view.findViewById(R.id.imlogo);
		
		litemp.setOnLongClickListener(longdian);
		lihum.setOnLongClickListener(longdian);
		lismoke.setOnLongClickListener(longdian);
		liill.setOnLongClickListener(longdian);
		lico2.setOnLongClickListener(longdian);
		lipm25.setOnLongClickListener(longdian);
		liair.setOnLongClickListener(longdian);
		ligas.setOnLongClickListener(longdian);
		liman.setOnLongClickListener(longdian);
		litd1.setOnLongClickListener(longdian);
		litd2.setOnLongClickListener(longdian);
		litd3.setOnLongClickListener(longdian);
		
		imbjd.setOnLongClickListener(longdian);
		imdoor.setOnLongClickListener(longdian);
		imfan.setOnLongClickListener(longdian);
		imlamp.setOnLongClickListener(longdian);
		imclk.setOnLongClickListener(longdian);
		imclt.setOnLongClickListener(longdian);
		imclg.setOnLongClickListener(longdian);
		
		try {
			db=this.getActivity().openOrCreateDatabase("smart1.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table shuju(ming text not null,zhi text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
			@Override
			public void onResult(final DeviceBean bean) {
				// TODO Auto-generated method stub
				getActivity().runOnUiThread(new Runnable() {
					
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
						if (!TextUtils.isEmpty(bean.getCo2())) {
							tvco2.setText(bean.getCo2());
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
						if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
							tvman.setText("无人");
						}else {
							tvman.setText("有人");
						}
						temp=Float.parseFloat(tvtemp.getText().toString());
						hum=Float.parseFloat(tvhum.getText().toString());
						smoke=Float.parseFloat(tvsmoke.getText().toString());
						ill=Float.parseFloat(tvill.getText().toString());
						gas=Float.parseFloat(tvgas.getText().toString());
						co2=Float.parseFloat(tvco2.getText().toString());
						pm25=Float.parseFloat(tvpm25.getText().toString());
						air=Float.parseFloat(tvair.getText().toString());
						man=Float.parseFloat(bean.getStateHumanInfrared());
						
						cvValues=new ContentValues();
						cvValues.put("ming","温度");
						cvValues.put("zhi", String.valueOf(temp));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","湿度");
						cvValues.put("zhi", String.valueOf(hum));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","烟雾");
						cvValues.put("zhi", String.valueOf(smoke));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","光照");
						cvValues.put("zhi", String.valueOf(ill));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","燃气");
						cvValues.put("zhi", String.valueOf(gas));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","Co2");
						cvValues.put("zhi", String.valueOf(co2));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","Pm2.5");
						cvValues.put("zhi", String.valueOf(pm25));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","气压");
						cvValues.put("zhi", String.valueOf(air));
						db.insert("shuju", null, cvValues);
						
						cvValues=new ContentValues();
						cvValues.put("ming","人体");
						cvValues.put("zhi", String.valueOf(man));
						db.insert("shuju", null, cvValues);
						
					}
				});
			}
		});
	imlogo.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				startActivity(new Intent(getActivity(),ZongActivity.class));
				getActivity().finish();
			}
		}
	});
	jb.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			startActivity(new Intent(getActivity(),ShujukuActivity.class));
			getActivity().finish();
			return false;
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
				imbjd.setImageResource(R.drawable.bjd_b);
				ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
		}
	});
	imdoor.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				imdoor.setImageResource(R.drawable.door_y);
				ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
			}else {
				imdoor.setImageResource(R.drawable.door_b);
			}
		}
	});
	imlamp.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				imlamp.setImageResource(R.drawable.lamp_y);
				ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
			}else {
				imlamp.setImageResource(R.drawable.lamp_b);
				ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
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
				imfan.setImageResource(R.drawable.fan_b);
				ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
			}
		}
	});
	imclk.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			imclk.setImageResource(R.drawable.clk_y);
			imclg.setImageResource(R.drawable.clg_b);
			imclt.setImageResource(R.drawable.clt_b);
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
		}
	});
	imclt.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			imclk.setImageResource(R.drawable.clk_b);
			imclg.setImageResource(R.drawable.clg_b);
			imclt.setImageResource(R.drawable.clt_y);
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_2, ConstantUtil.OPEN);
		}
	});
	imclg.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			imclk.setImageResource(R.drawable.clk_b);
			imclg.setImageResource(R.drawable.clg_y);
			imclt.setImageResource(R.drawable.clt_b);
			ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
		}
	});
	imclk.setOnLongClickListener(new OnLongClickListener() {
		
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			v.setOnTouchListener(tuozhuai);
			imcllogo.setVisibility(View.INVISIBLE);
			imhw.setVisibility(View.INVISIBLE);
			return false;
		}
	});
	imclg.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			v.setOnTouchListener(tuozhuai);
			imcllogo.setVisibility(View.INVISIBLE);
			imhw.setVisibility(View.INVISIBLE);
			return false;
		}
	});
	imclt.setOnLongClickListener(new OnLongClickListener() {
		
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			v.setOnTouchListener(tuozhuai);
			imcllogo.setVisibility(View.INVISIBLE);
			imhw.setVisibility(View.INVISIBLE);
			return false;
		}
	});
	imtd1.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				imtd1.setImageResource(R.drawable.tb_y);
				imtv.setImageResource(R.drawable.tv_y);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
			}else {
				imtd1.setImageResource(R.drawable.tb_b);
				imtv.setImageResource(R.drawable.tv_b);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
			}
		}
	});
	imtd2.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				imtd2.setImageResource(R.drawable.tb2_y);
				imdvd.setImageResource(R.drawable.dvd_y);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
			}else {
				imtd2.setImageResource(R.drawable.tb2_b);
				imdvd.setImageResource(R.drawable.dvd_b);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
			}
		}
	});
	imtd3.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			kg=!kg;
			if (kg) {
				imtd3.setImageResource(R.drawable.tb3_y);
				imkt.setImageResource(R.drawable.kt_y);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.OPEN);
			}else {
				imtd3.setImageResource(R.drawable.tb3_b);
				imkt.setImageResource(R.drawable.kt_b);
				ControlUtils.control(ConstantUtil.INFRARED_1_SERVE, "", ConstantUtil.CLOSE);
			}
		}
	});
		
		return view;
	}

}
