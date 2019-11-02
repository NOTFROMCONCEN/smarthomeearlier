package com.example.disan;

import android.R.integer;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthometest.lib.Json_data;
import com.bizideal.smarthometest.lib.SocketThread;
import com.bizideal.smarthometest.lib.Updata_activity;
import com.bizideal.smarthometest.lib.json_dispose;

public class KongzhiActivity extends Fragment {
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tvcj,tvkz;
	Switch sw1,sw2,sw3,sw4;
	Button btn1,btn2,btn3,btnfashe;
	EditText et1;
	Thread upThread;
	public static float temp,humibity,co2,ill,smoke,air,pm25,gas,man;
	public static json_dispose js=new json_dispose();
	GridLayout laycj,laykz;
	int count=0;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_kongzhi, container,false);
		tv1=(TextView)view.findViewById(R.id.text1);
		tv2=(TextView)view.findViewById(R.id.Text2);
		tv3=(TextView)view.findViewById(R.id.Text3);
		tv4=(TextView)view.findViewById(R.id.Text4);
		tv5=(TextView)view.findViewById(R.id.Text5);
		tv6=(TextView)view.findViewById(R.id.Text6);
		tv7=(TextView)view.findViewById(R.id.Text7);
		tv8=(TextView)view.findViewById(R.id.Text8);
		tv9=(TextView)view.findViewById(R.id.Text9);
		tvcj=(TextView)view.findViewById(R.id.TextView12);
		tvkz=(TextView)view.findViewById(R.id.Tvkongzhi);
		laycj=(GridLayout)view.findViewById(R.id.gridcaiji);
		laykz=(GridLayout)view.findViewById(R.id.gridkz);

		sw1=(Switch)view.findViewById(R.id.switch1);
		sw2=(Switch)view.findViewById(R.id.Switch2);
		sw3=(Switch)view.findViewById(R.id.Switch3);
		sw4=(Switch)view.findViewById(R.id.Switch4);

		btn1=(Button)view.findViewById(R.id.Butkai);
		btn2=(Button)view.findViewById(R.id.Butguan);
		btn3=(Button)view.findViewById(R.id.butting);
		btnfashe=(Button)view.findViewById(R.id.butfashe);
		et1=(EditText)view.findViewById(R.id.edfashe);
		
		laykz.setVisibility(View.INVISIBLE);

		tvcj.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				laycj.setVisibility(View.VISIBLE);
				laykz.setVisibility(View.INVISIBLE);
				tvcj.setBackgroundResource(Color.BLUE);
				tvkz.setBackgroundResource(R.drawable.qianlan);
			}
		});
		tvkz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				laycj.setVisibility(View.INVISIBLE);
				laykz.setVisibility(View.VISIBLE);
				tvkz.setBackgroundResource(Color.BLUE);
				tvcj.setBackgroundResource(R.drawable.qianlan);
				
			}
		});

		upThread=new Thread(new Updata_activity());
		upThread.start();
		Updata_activity.updatahandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				try {
					js.receive();

					tv1.setText(js.receive_data.get(Json_data.Temp).toString());
					tv2.setText(js.receive_data.get(Json_data.Humidity).toString());
					tv3.setText(js.receive_data.get(Json_data.Co2).toString());
					tv4.setText(js.receive_data.get(Json_data.Illumination).toString());
					tv5.setText(js.receive_data.get(Json_data.Smoke).toString());
					tv6.setText(js.receive_data.get(Json_data.AirPressure).toString());
					tv7.setText(js.receive_data.get(Json_data.PM25).toString());
					tv8.setText(js.receive_data.get(Json_data.Gas).toString());
					tv9.setText(js.receive_data.get(Json_data.StateHumanInfrared).toString());

					temp=Float.parseFloat(tv1.getText().toString());
					humibity=Float.parseFloat(tv2.getText().toString());
					co2=Float.parseFloat(tv3.getText().toString());
					ill=Float.parseFloat(tv4.getText().toString());
					smoke=Float.parseFloat(tv5.getText().toString());
					air=Float.parseFloat(tv6.getText().toString());
					pm25=Float.parseFloat(tv7.getText().toString());
					gas=Float.parseFloat(tv8.getText().toString());
					man=Float.parseFloat(tv9.getText().toString());
					if (man==1) {
						tv9.setText("有人");
					}else {
						tv9.setText("无人");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		};

		sw1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					js.control(Json_data.WarningLight, 0, 1);
				}else {
					js.control(Json_data.WarningLight, 0, 0);
				}
			}
		});
		sw2.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					js.control(Json_data.RFID_Open_Door, 0, 1);
				}else {
					js.control(Json_data.RFID_Open_Door, 0, 0);
				}
			}
		});
		sw3.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					js.control(Json_data.Fan, 0, 1);
				}else {
					js.control(Json_data.Fan, 0, 0);
				}
			}
		});
		sw4.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					js.control(Json_data.Lamp, 0, 1);
				}else {
					js.control(Json_data.Lamp, 0, 0);
				}
			}
		});
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				js.control(Json_data.Curtain, 0, 1);
			}
		});
		btn2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				js.control(Json_data.Curtain, 0, 2);
			}
		});
		btn3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				js.control(Json_data.Curtain, 0, 3);
			}
		});
		btnfashe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int fashe=Integer.parseInt(et1.getText().toString());
				js.control(Json_data.InfraredLaunch, 0, fashe);
			}
		});
		SocketThread.mHandlerSocketState=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Bundle bundle=msg.getData();
				if (count==1) {
					if (bundle.getString("SocketThread_State")=="error") {
						Toast.makeText(getActivity(), "网络连接失败", Toast.LENGTH_LONG).show();
					}else {
						Toast.makeText(getActivity(), "网络连接成功", Toast.LENGTH_LONG).show();
					}
				}
			}
		};
		return view;
	}

}
