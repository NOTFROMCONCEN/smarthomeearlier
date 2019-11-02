package com.example.b1008;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class JibenActivity extends Fragment {
	TextView tv8101, tv8102, tv8103, tv8104, tv8201, tv8202, tv8203, tv8204,
			tv8301, tv8302, tv8303, tv8304, tv8401, tv8402, tv8403, tv8404,
			tvtemp, tvhum, tvsmoke, tvgas, tvill, tvco2, tvpm25, tvair, tvman;
	public static float temp, hum, smoke, ill, gas, co2, air, man, pm25;
	Button btnzhu, btnmei, btnsao;
	TextView tvjbfh, midText;
	String str;
	AlertDialog builder;
	SharedPreferences sp;
	Editor editor;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_jiben, container, false);
		tv8101 = (TextView) view.findViewById(R.id.Tex1);
		tv8102 = (TextView) view.findViewById(R.id.Tex2);
		tv8103 = (TextView) view.findViewById(R.id.Tex3);
		tv8104 = (TextView) view.findViewById(R.id.Tex4);
		tv8201 = (TextView) view.findViewById(R.id.Tex5);
		tv8202 = (TextView) view.findViewById(R.id.Tex6);
		tv8203 = (TextView) view.findViewById(R.id.Tex7);
		tv8204 = (TextView) view.findViewById(R.id.Tex8);
		tv8301 = (TextView) view.findViewById(R.id.Tex9);
		tv8302 = (TextView) view.findViewById(R.id.Tex10);
		tv8303 = (TextView) view.findViewById(R.id.Tex11);
		tv8304 = (TextView) view.findViewById(R.id.Tex12);
		tv8401 = (TextView) view.findViewById(R.id.Tex13);
		tv8402 = (TextView) view.findViewById(R.id.Tex14);
		tv8403 = (TextView) view.findViewById(R.id.Tex15);
		tv8404 = (TextView) view.findViewById(R.id.Tex16);

		sp = getActivity().getSharedPreferences("yanse.xml",
				Context.MODE_PRIVATE);

		tv8101.setBackgroundColor(sp.getInt("8101", Color.GREEN));
		tv8102.setBackgroundColor(sp.getInt("8102", Color.GREEN));
		tv8103.setBackgroundColor(sp.getInt("8103", Color.GREEN));
		tv8104.setBackgroundColor(sp.getInt("8104", Color.GREEN));
		tv8201.setBackgroundColor(sp.getInt("8201", Color.GREEN));
		tv8202.setBackgroundColor(sp.getInt("8202", Color.GREEN));
		tv8203.setBackgroundColor(sp.getInt("8203", Color.GREEN));
		tv8204.setBackgroundColor(sp.getInt("8204", Color.GREEN));
		tv8301.setBackgroundColor(sp.getInt("8301", Color.GREEN));
		tv8302.setBackgroundColor(sp.getInt("8302", Color.GREEN));
		tv8303.setBackgroundColor(sp.getInt("8303", Color.GREEN));
		tv8304.setBackgroundColor(sp.getInt("8304", Color.GREEN));
		tv8401.setBackgroundColor(sp.getInt("8401", Color.GREEN));
		tv8402.setBackgroundColor(sp.getInt("8402", Color.GREEN));
		tv8403.setBackgroundColor(sp.getInt("8403", Color.GREEN));
		tv8404.setBackgroundColor(sp.getInt("8404", Color.GREEN));

		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				str = ((TextView) v).getText().toString();
				midText = ((TextView) v);

				builder = new AlertDialog.Builder(getActivity()).create();
				builder.show();
				builder.setCancelable(false);
				View view = LayoutInflater.from(getActivity()).inflate(
						R.layout.jiben, null);
				Window window = builder.getWindow();
				window.setContentView(view);

				tvill = (TextView) window.findViewById(R.id.text1);
				tvhum = (TextView) window.findViewById(R.id.text2);
				tvtemp = (TextView) window.findViewById(R.id.text3);
				tvsmoke = (TextView) window.findViewById(R.id.text4);
				tvair = (TextView) window.findViewById(R.id.text5);
				tvco2 = (TextView) window.findViewById(R.id.text6);
				tvpm25 = (TextView) window.findViewById(R.id.text7);
				tvgas = (TextView) window.findViewById(R.id.text8);
				tvman = (TextView) window.findViewById(R.id.text9);
				tvjbfh = (TextView) window.findViewById(R.id.tejienf);
				btnmei = (Button) window.findViewById(R.id.Butemi);
				btnzhu = (Button) window.findViewById(R.id.butzhu);
				btnsao = (Button) window.findViewById(R.id.Butsao);

				tvjbfh.setText(str);
				ControlUtils.getData();
				SocketClient.getInstance().getData(
						new DataCallback<DeviceBean>() {
							@Override
							public void onResult(final DeviceBean bean) {
								// TODO Auto-generated method stub
								getActivity().runOnUiThread(new Runnable() {

									@Override
									public void run() {
										// TODO Auto-generated method stub
										if (!TextUtils.isEmpty(bean
												.getTemperature())) {
											tvtemp.setText(bean
													.getTemperature());
										}
										if (!TextUtils.isEmpty(bean
												.getHumidity())) {
											tvhum.setText(bean.getHumidity());
										}
										if (!TextUtils.isEmpty(bean.getSmoke())) {
											tvsmoke.setText(bean.getSmoke());
										}
										if (!TextUtils.isEmpty(bean.getGas())) {
											tvgas.setText(bean.getGas());
										}
										if (!TextUtils.isEmpty(bean
												.getIllumination())) {
											tvill.setText(bean
													.getIllumination());
										}
										if (!TextUtils.isEmpty(bean.getCo2())) {
											tvco2.setText(bean.getCo2());
										}
										if (!TextUtils.isEmpty(bean.getPM25())) {
											tvpm25.setText(bean.getPM25());
										}
										if (!TextUtils.isEmpty(bean
												.getAirPressure())) {
											tvair.setText(bean.getAirPressure());
										}
										if (!TextUtils.isEmpty(bean
												.getStateHumanInfrared())
												&& bean.getStateHumanInfrared()
														.equals(ConstantUtil.CLOSE)) {
											tvman.setText("无人");
										} else {
											tvman.setText("有人");
										}
										temp = Float.parseFloat(tvtemp
												.getText().toString());
										hum = Float.parseFloat(tvhum.getText()
												.toString());
										smoke = Float.parseFloat(tvsmoke
												.getText().toString());
										gas = Float.parseFloat(tvgas.getText()
												.toString());
										ill = Float.parseFloat(tvill.getText()
												.toString());
										co2 = Float.parseFloat(tvco2.getText()
												.toString());
										pm25 = Float.parseFloat(tvpm25
												.getText().toString());
										air = Float.parseFloat(tvair.getText()
												.toString());
										man = Float.parseFloat(bean
												.getStateHumanInfrared());
									}
								});
							}
						});
				btnzhu.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editor = sp.edit();
						editor.putInt(str, Color.RED);
						editor.commit();

						midText.setBackgroundColor(Color.RED);

						builder.dismiss();
					}
				});
				btnmei.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editor = sp.edit();
						editor.putInt(str, Color.GREEN);
						editor.commit();

						midText.setBackgroundColor(Color.GREEN);
						builder.dismiss();
					}
				});
				btnsao.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						editor = sp.edit();
						editor.putInt(str, Color.GRAY);
						editor.commit();

						midText.setBackgroundColor(Color.GRAY);
						builder.dismiss();
					}
				});
			}
		};
		tv8101.setOnClickListener(onClickListener);
		tv8102.setOnClickListener(onClickListener);
		tv8103.setOnClickListener(onClickListener);
		tv8104.setOnClickListener(onClickListener);
		tv8201.setOnClickListener(onClickListener);
		tv8202.setOnClickListener(onClickListener);
		tv8203.setOnClickListener(onClickListener);
		tv8204.setOnClickListener(onClickListener);
		tv8301.setOnClickListener(onClickListener);
		tv8302.setOnClickListener(onClickListener);
		tv8303.setOnClickListener(onClickListener);
		tv8304.setOnClickListener(onClickListener);
		tv8401.setOnClickListener(onClickListener);
		tv8402.setOnClickListener(onClickListener);
		tv8403.setOnClickListener(onClickListener);
		tv8404.setOnClickListener(onClickListener);
		return view;
	}
}