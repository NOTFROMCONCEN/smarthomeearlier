package com.example.c1005;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

public class LianActivity extends Fragment {
	Spinner spws,spdx;
	TextView tvsj;
	EditText etlian,etsj;
	Button btnlian;
	Switch swbjd,swfan,swlamp,swdoor;
	boolean kg=false;
	CountDownTimer timer;
	long shi,fen,miao;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_lian, container,false);
		spws=(Spinner)view.findViewById(R.id.spinner1);
		spdx=(Spinner)view.findViewById(R.id.Spinner01);
		etlian=(EditText)view.findViewById(R.id.edlian);
		etsj=(EditText)view.findViewById(R.id.Edshij);
		btnlian=(Button)view.findViewById(R.id.butlian);
		swbjd=(Switch)view.findViewById(R.id.switch1);
		swfan=(Switch)view.findViewById(R.id.Switch01);
		swlamp=(Switch)view.findViewById(R.id.Switch02);
		swdoor=(Switch)view.findViewById(R.id.Switch03);
		tvsj=(TextView)view.findViewById(R.id.teshij);
		
		btnlian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
					String v1=spws.getSelectedItem().toString();
					String v2=spdx.getSelectedItem().toString();
					int v3=Integer.parseInt(etlian.getText().toString());
					if (btnlian.getText().toString().equals("开启联动")) {
						if (v1.equals("温度")&&v2.equals(">")&&JibenActivity.temp>v3) {
							kg=true;
							btnlian.setText("停止联动");
						}else if (v1.equals("温度")&&v2.equals("<=")&&JibenActivity.temp<=v3) {
							kg=true;
							btnlian.setText("停止联动");
						}else if (v1.equals("湿度")&&v2.equals(">")&&JibenActivity.hum>v3) {
							kg=true;
							btnlian.setText("停止联动");
						}else if (v1.equals("湿度")&&v2.equals("<=")&&JibenActivity.hum<=v3) {
							kg=true;
							btnlian.setText("停止联动");
						}else if (v1.equals("光照")&&v2.equals(">")&&JibenActivity.ill>v3) {
							kg=true;
							btnlian.setText("停止联动");
						}else if (v1.equals("光照")&&v2.equals("<=")&&JibenActivity.ill<=v3) {
							kg=true;
							btnlian.setText("停止联动");
						}
						if (kg) {
							shi=Integer.parseInt(etsj.getText().toString())*60*1000;
							timer=new CountDownTimer(shi,1000) {

								@Override
								public void onTick(long millisUntilFinished) {
									// TODO Auto-generated method stub
									fen=millisUntilFinished/1000/60;
									miao=millisUntilFinished/1000%60;

									tvsj.setText("联动模式还有"+fen+"分"+miao+"秒");
								}

								@Override
								public void onFinish() {
									// TODO Auto-generated method stub
									kg=false;
									btnlian.setText("开启联动");
									tvsj.setText("联动模式还有"+fen+"分"+miao+"秒");
								}
							}.start();
						}
					}else if (btnlian.getText().toString().equals("停止联动")) {
						kg=false;
						btnlian.setText("开启联动");
						tvsj.setText("联动模式还有"+fen+"分"+miao+"秒");
						timer.cancel();
					}
			}
		});
		swbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		swlamp.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		swfan.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
					}
				}
			}
		});
		swdoor.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
					}
				}
			}
		});
		return view;
	}

}
