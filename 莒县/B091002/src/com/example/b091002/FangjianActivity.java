package com.example.b091002;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class FangjianActivity extends Fragment {
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tvtemp,tvhum,tvsmoke,tvair,tvgas,tvpm25,tvco2,tvman,tvill,tvfangjian;
	Button btnzhu,btndasao,btnmeizhu;
	TextView midtext;
	public static float temp,hum,ill,smoke,gas,air,pm25,co2,man;
	AlertDialog builder;
	String string;
	SharedPreferences sp;
	Editor editor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_fangjian, container,false);
		tv1=(TextView)view.findViewById(R.id.tv1);
		tv2=(TextView)view.findViewById(R.id.tv2);
		tv3=(TextView)view.findViewById(R.id.tv3);
		tv4=(TextView)view.findViewById(R.id.tv4);
		tv5=(TextView)view.findViewById(R.id.tv5);
		tv6=(TextView)view.findViewById(R.id.tv6);
		tv7=(TextView)view.findViewById(R.id.tv7);
		tv8=(TextView)view.findViewById(R.id.tv8);
		tv9=(TextView)view.findViewById(R.id.tv9);
		tv10=(TextView)view.findViewById(R.id.tv10);
		tv11=(TextView)view.findViewById(R.id.tv11);
		tv12=(TextView)view.findViewById(R.id.tv12);
		tv13=(TextView)view.findViewById(R.id.tv13);
		tv14=(TextView)view.findViewById(R.id.tv14);
		tv15=(TextView)view.findViewById(R.id.tv15);
		tv16=(TextView)view.findViewById(R.id.tv16);
		
		sp=getActivity().getSharedPreferences("yanse.xml",Context.MODE_PRIVATE);
		
		tv1.setBackgroundColor(sp.getInt("8101", Color.GREEN));
		tv2.setBackgroundColor(sp.getInt("8102", Color.GREEN));
		tv3.setBackgroundColor(sp.getInt("8103", Color.GREEN));
		tv4.setBackgroundColor(sp.getInt("8104", Color.GREEN));
		tv5.setBackgroundColor(sp.getInt("8201", Color.GREEN));
		tv6.setBackgroundColor(sp.getInt("8202", Color.GREEN));
		tv7.setBackgroundColor(sp.getInt("8203", Color.GREEN));
		tv8.setBackgroundColor(sp.getInt("8204", Color.GREEN));
		tv9.setBackgroundColor(sp.getInt("8301", Color.GREEN));
		tv10.setBackgroundColor(sp.getInt("8302", Color.GREEN));
		tv11.setBackgroundColor(sp.getInt("8303", Color.GREEN));
		tv12.setBackgroundColor(sp.getInt("8304", Color.GREEN));
		tv13.setBackgroundColor(sp.getInt("8401", Color.GREEN));
		tv14.setBackgroundColor(sp.getInt("8402", Color.GREEN));
		tv15.setBackgroundColor(sp.getInt("8403", Color.GREEN));
		tv16.setBackgroundColor(sp.getInt("8404", Color.GREEN));
		
	
		OnClickListener tvoncClick=new OnClickListener() {

			@Override
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				string=((TextView)v).getText().toString();
				midtext=((TextView)v);

				builder=new AlertDialog.Builder(getActivity()).create();
				builder.setCancelable(false);
				builder.show();

				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102,null);
				Window window=builder.getWindow();
				window.setContentView(view);

				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
				btndasao=(Button)window.findViewById(R.id.Butda);
				btnzhu=(Button)window.findViewById(R.id.butruzhu);

				tvill=(TextView)window.findViewById(R.id.Text1);
				tvhum=(TextView)window.findViewById(R.id.Text2);
				tvtemp=(TextView)window.findViewById(R.id.Text3);
				tvsmoke=(TextView)window.findViewById(R.id.Text4);
				tvair=(TextView)window.findViewById(R.id.Text5);
				tvco2=(TextView)window.findViewById(R.id.Text6);
				tvpm25=(TextView)window.findViewById(R.id.Text7);
				tvgas=(TextView)window.findViewById(R.id.Text8);
				tvman=(TextView)window.findViewById(R.id.Text9);
				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
				
				tvfangjian.setText(string);
				
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
				btnzhu.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editor=sp.edit();
						editor.putInt(string,Color.RED);
						editor.commit();
				
						midtext.setBackgroundColor(Color.RED);
						builder.dismiss();
					}
				});
				btndasao.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editor=sp.edit();
						editor.putInt(string,Color.GRAY);
						editor.commit();
						
						midtext.setBackgroundColor(Color.GRAY);
						builder.dismiss();
					}
				});
				btnmeizhu.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editor=sp.edit();
						editor.putInt(string,Color.GREEN);
						editor.commit();

						midtext.setBackgroundColor(Color.GREEN);
						builder.dismiss();
					}
				});
			}
		};
		tv1.setOnClickListener(tvoncClick);
		tv2.setOnClickListener(tvoncClick);
		tv3.setOnClickListener(tvoncClick);
		tv4.setOnClickListener(tvoncClick);
		tv5.setOnClickListener(tvoncClick);
		tv6.setOnClickListener(tvoncClick);
		tv7.setOnClickListener(tvoncClick);
		tv8.setOnClickListener(tvoncClick);
		tv9.setOnClickListener(tvoncClick);
		tv10.setOnClickListener(tvoncClick);
		tv11.setOnClickListener(tvoncClick);
		tv12.setOnClickListener(tvoncClick);
		tv13.setOnClickListener(tvoncClick);
		tv14.setOnClickListener(tvoncClick);
		tv15.setOnClickListener(tvoncClick);
		tv16.setOnClickListener(tvoncClick);






		//		tv1.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8101");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv1.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv1.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv1.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//
		//			
		//			}
		//				});
		//		tv2.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8102");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv2.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv2.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv2.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv3.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8103");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv3.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv3.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv3.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv4.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8104");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv4.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv4.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv4.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv5.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8201");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv5.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv5.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv5.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv6.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8202");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv6.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv6.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv6.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv7.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8203");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv7.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv7.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv7.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv8.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8204");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv8.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv8.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv8.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv9.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8301");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv9.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv9.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv9.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv10.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8302");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv10.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv10.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv10.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv11.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8303");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv11.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv11.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv11.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv12.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8304");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv12.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv12.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv12.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv13.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8401");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv13.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv13.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv13.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv14.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8302");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv14.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv14.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv14.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv15.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8403");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv15.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv15.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv15.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//		tv16.setOnClickListener(new OnClickListener() {
		//			
		//			@Override
		//			public void onClick(View v) {
		//				// TODO Auto-generated method stub
		//				builder=new AlertDialog.Builder(getActivity()).create();
		//				builder.setCancelable(false);
		//				builder.show();
		//				
		//				View view=LayoutInflater.from(getActivity()).inflate(R.layout.listvi8102, null);
		//				Window window=builder.getWindow();
		//				window.setContentView(view);
		//				
		//				btnmeizhu=(Button)window.findViewById(R.id.Butmeizhu);
		//				btndasao=(Button)window.findViewById(R.id.Butda);
		//				btnzhu=(Button)window.findViewById(R.id.butruzhu);
		//				
		//				tvill=(TextView)window.findViewById(R.id.Text1);
		//				tvhum=(TextView)window.findViewById(R.id.Text2);
		//				tvtemp=(TextView)window.findViewById(R.id.Text3);
		//				tvsmoke=(TextView)window.findViewById(R.id.Text4);
		//				tvair=(TextView)window.findViewById(R.id.Text5);
		//				tvco2=(TextView)window.findViewById(R.id.Text6);
		//				tvpm25=(TextView)window.findViewById(R.id.Text7);
		//				tvgas=(TextView)window.findViewById(R.id.Text8);
		//				tvman=(TextView)window.findViewById(R.id.Text9);
		//				tvfangjian=(TextView)window.findViewById(R.id.textfangjian);
		//				ControlUtils.getData();
		//				SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {
		//					@Override
		//					public void onResult(final DeviceBean bean) {
		//						// TODO Auto-generated method stub
		//						getActivity().runOnUiThread(new Runnable() {
		//
		//							@Override
		//							public void run() {
		//								// TODO Auto-generated method stub
		//								if (!TextUtils.isEmpty(bean.getTemperature())) {
		//									tvtemp.setText(bean.getTemperature());
		//								}
		//								if (!TextUtils.isEmpty(bean.getHumidity())) {
		//									tvhum.setText(bean.getHumidity());
		//								}
		//								if (!TextUtils.isEmpty(bean.getGas())) {
		//									tvgas.setText(bean.getGas());
		//								}
		//								if (!TextUtils.isEmpty(bean.getIllumination())) {
		//									tvill.setText(bean.getIllumination());
		//								}
		//								if (!TextUtils.isEmpty(bean.getPM25())) {
		//									tvpm25.setText(bean.getPM25());
		//								}
		//								if (!TextUtils.isEmpty(bean.getAirPressure())) {
		//									tvair.setText(bean.getAirPressure());
		//								}
		//								if (!TextUtils.isEmpty(bean.getSmoke())) {
		//									tvsmoke.setText(bean.getSmoke());
		//								}
		//								if (!TextUtils.isEmpty(bean.getCo2())) {
		//									tvco2.setText(bean.getCo2());
		//								}
		//								if (!TextUtils.isEmpty(bean.getStateHumanInfrared())&&bean.getStateHumanInfrared().equals(ConstantUtil.CLOSE)) {
		//									tvman.setText("无人");
		//								}else {
		//									tvman.setText("有人");
		//								}
		//								temp=Float.parseFloat(tvtemp.getText().toString());
		//								hum=Float.parseFloat(tvhum.getText().toString());
		//								smoke=Float.parseFloat(tvsmoke.getText().toString());
		//								gas=Float.parseFloat(tvgas.getText().toString());
		//								ill=Float.parseFloat(tvill.getText().toString());
		//								co2=Float.parseFloat(tvco2.getText().toString());
		//								pm25=Float.parseFloat(tvpm25.getText().toString());
		//								air=Float.parseFloat(tvair.getText().toString());
		//								man=Float.parseFloat(bean.getStateHumanInfrared());
		//							}
		//						});
		//					}
		//				});
		//				
		//				tvfangjian.setText("8404");
		//				
		//				btnzhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv16.setBackgroundColor(Color.RED);
		//						builder.dismiss();
		//					}
		//				});
		//				btndasao.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv16.setBackgroundColor(Color.GRAY);
		//						builder.dismiss();
		//					}
		//				});
		//				btnmeizhu.setOnClickListener(new OnClickListener() {
		//					
		//					@Override
		//					public void onClick(View v) {
		//						// TODO Auto-generated method stub
		//						tv16.setBackgroundColor(Color.GREEN);
		//						builder.dismiss();
		//					}
		//				});
		//			}
		//		});
		//			

		return view;
	}

}
