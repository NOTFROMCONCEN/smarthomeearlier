package com.example.c1017;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

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
import android.widget.Toast;

public class LianActivity extends Fragment {
	Spinner spws,spdx;
	EditText etlian,etshi;
	Button btnlian;
	Switch swbjd,swfan,swlamp,swdoor;
	TextView tvlian;
	CountDownTimer timer;
	long shi,fen,miao;
	boolean kg=false;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.activity_lian, container,false);
		spdx=(Spinner)view.findViewById(R.id.Spdx);
		spws=(Spinner)view.findViewById(R.id.spws);
		etlian=(EditText)view.findViewById(R.id.edlian);
		btnlian=(Button)view.findViewById(R.id.butlian);
		swbjd=(Switch)view.findViewById(R.id.swbjd);
		swfan=(Switch)view.findViewById(R.id.Swfan);
		swlamp=(Switch)view.findViewById(R.id.Swlamp);
		swdoor=(Switch)view.findViewById(R.id.Swdoor);
		etshi=(EditText)view.findViewById(R.id.EditText01);
		tvlian=(TextView)view.findViewById(R.id.tvlians);
		
		btnlian.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    if (etlian.getText().toString().isEmpty()) {
					Toast.makeText(getActivity(), "��������Ϊ��", Toast.LENGTH_LONG).show();
				}else if (!etlian.getText().toString().matches("[0-9]+")) {
					Toast.makeText(getActivity(), "������������", Toast.LENGTH_LONG).show();
				}else {
					String v1=spws.getSelectedItem().toString();
					String v2=spdx.getSelectedItem().toString();
					int v3=Integer.parseInt(etlian.getText().toString());
					if (btnlian.getText().toString().equals("��������")) {
						if (v1.equals("�¶�")&&v2.equals(">")&&JibenActivity.temp>v3) {
							kg=true;
							btnlian.setText("ֹͣ����");
						}else if (v1.equals("�¶�")&&v2.equals("<")&&JibenActivity.temp<v3) {
							kg=true;
							btnlian.setText("ֹͣ����");
						}else if (v1.equals("ʪ��")&&v2.equals(">")&&JibenActivity.hum>v3) {
							kg=true;
							btnlian.setText("ֹͣ����");
						}else if (v1.equals("ʪ��")&&v2.equals("<")&&JibenActivity.hum<v3) {
							kg=true;
							btnlian.setText("ֹͣ����");
						}else if (v1.equals("����")&&v2.equals(">")&&JibenActivity.ill>v3) {
							kg=true;
							btnlian.setText("ֹͣ����");
						}else if (v1.equals("����")&&v2.equals("<")&&JibenActivity.ill<v3) {
							kg=true;
							btnlian.setText("ֹͣ����");
						}
						if (kg) {
							shi=Integer.parseInt(etshi.getText().toString())*60*1000;
							timer=new CountDownTimer(shi,1000) {
								
								@Override
								public void onTick(long millisUntilFinished) {
									// TODO Auto-generated method stub
									fen=millisUntilFinished/1000/60;
									miao=millisUntilFinished/1000%60;
									
									tvlian.setText("����ģʽ����"+fen+"��"+miao+"��");
								}
								
								@Override
								public void onFinish() {
									// TODO Auto-generated method stub
									kg=false;
									btnlian.setText("��������");
									tvlian.setText("����ģʽ����"+fen+"��"+miao+"��");
								}
							}.start();
						}else if (btnlian.getText().toString().equals("ֹͣ����")) {
							kg=false;
							btnlian.setText("��������");
							tvlian.setText("����ģʽ����"+fen+"��"+miao+"��");
							timer.cancel();
						}
					}
				}
			}
		});
		swbjd.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (kg) {
					if (isChecked) {
						ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL	, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.WarningLight,ConstantUtil.CHANNEL_ALL	, ConstantUtil.OPEN);
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
						ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL	, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL	, ConstantUtil.OPEN);
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
						ControlUtils.control(ConstantUtil.RFID_Open_Door,ConstantUtil.CHANNEL_1	, ConstantUtil.OPEN);
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
						ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL	, ConstantUtil.OPEN);
					}else {
						ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL	, ConstantUtil.OPEN);
					}
				}
			}
		});
		
		return view;
	}

}
