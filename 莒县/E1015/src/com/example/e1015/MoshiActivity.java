package com.example.e1015;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

public class MoshiActivity extends Activity {
	CheckBox chmoshi,chlian,chzhiling;
	Switch swmoshi;
	Spinner spws,spdx,spkz;
	EditText etlian;
	ListView lv;
	boolean kg=false,swkg=false,swzj=false;
	int idc=0;
	String shij;
	List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	Map<String, String> map;
	SimpleAdapter adapter;
	Handler handler;
	Runnable zjRunnable,ljRunnable;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_moshi);
		chlian=(CheckBox)findViewById(R.id.CheckBox01);
		chmoshi=(CheckBox)findViewById(R.id.chmoshi);
		swmoshi=(Switch)findViewById(R.id.swmoshi);
		spdx=(Spinner)findViewById(R.id.Spdx);
		spws=(Spinner)findViewById(R.id.spws);
		spkz=(Spinner)findViewById(R.id.Spkz);
		etlian=(EditText)findViewById(R.id.edlian);
		chzhiling=(CheckBox)findViewById(R.id.CheckBox02);
		lv=(ListView)findViewById(R.id.listView1);
		SimpleDateFormat format=new SimpleDateFormat("HH:mm");
		shij=format.format(new java.util.Date());
		
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 0x0001:
					ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"1", ConstantUtil.OPEN);
					ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}
				   ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
				   handler.postDelayed(zjRunnable, 2000);
					break;
				case 0x0002:
					if (JibenActivity.smoke>600) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
						}
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}else if (JibenActivity.man==1) {
						ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.OPEN);
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
						}
						ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
					}
					break;

				default:
					break;
				}
			}
		};
		zjRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (swmoshi.isChecked()) {
					chlian.setEnabled(false);
					chzhiling.setEnabled(false);
					handler.sendEmptyMessage(0x0001);
				}else {
					chlian.setEnabled(true);
					chzhiling.setEnabled(true);
				}
			}
		};
		ljRunnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (swmoshi.isChecked()) {
					
				}else {
					handler.sendEmptyMessage(0x0002);
				}
			}
		};
		
		
		
		

//		chmoshi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
//				if (isChecked) {
//					swkg=true;
//					chlian.setEnabled(false);
//					chzhiling.setEnabled(false);
//				}else {
//					swkg=false;
//					chlian.setEnabled(true);
//					chzhiling.setEnabled(true);
//				}
//			}
//		});
//		swmoshi.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//
//			@Override
//			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//				// TODO Auto-generated method stub
//				if (swkg) {
//					if (isChecked) {
//						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"空调开",ConstantUtil.OPEN);
//						idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("空调"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//						adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);		
//						ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
//						idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("射灯"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//						adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//						ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
//						idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("窗帘"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
//						adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//					}else {
//						new Thread(new Runnable() {
//
//							@Override
//							public void run() {
//								// TODO Auto-generated method stub
//								while (true) {
//									try {
//										Thread.sleep(1000);
//									} catch (Exception e) {
//										// TODO: handle exception
//									}
//									if (JibenActivity.smoke>600) {
//										ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
//										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("报警灯"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//										adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//										ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
//										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("窗帘"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//										adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//										ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
//										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("换气扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//										adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//									}else if (JibenActivity.man==1) {
//										ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
//										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("报警灯"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//										adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//										ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
//										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("窗帘"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//										adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//										ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
//										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("空调"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
//										adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
//									}
//								}
//							}
//						});
//					}
//				}
//			}
//		});
		chlian.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chmoshi.setEnabled(false);
					chzhiling.setEnabled(false);
					if (etlian.getText().toString().isEmpty()) {
						Toast.makeText(MoshiActivity.this, "输入数据为空", Toast.LENGTH_LONG).show();
						chlian.setChecked(false);
					}else if (!etlian.getText().toString().matches("[0-9]+")) {
						Toast.makeText(MoshiActivity.this,"输入数据有误",Toast.LENGTH_LONG).show();
						chlian.setChecked(false);
					}else {
						String v1=spws.getSelectedItem().toString();
						String v2=spdx.getSelectedItem().toString();
						String v3=spkz.getSelectedItem().toString();
						int fazhi=Integer.parseInt(etlian.getText().toString());
						if (v1.equals("温度")&&v2.equals(">=")&&JibenActivity.temp>=fazhi&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("温度")&&v2.equals(">=")&&JibenActivity.temp>=fazhi&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<fazhi&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals(">=")&&JibenActivity.hum>=fazhi&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals(">=")&&JibenActivity.hum>=fazhi&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<fazhi&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan,ConstantUtil.CHANNEL_ALL,ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("kz",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(MoshiActivity.this, list, R.layout.listview, new String[]{"id","kz","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}
					}
				}else {
					chmoshi.setEnabled(true);
					chzhiling.setEnabled(true);
				}
			}
		});
		chzhiling.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chmoshi.setEnabled(false);
					chlian.setEnabled(false);
				}else {
					chmoshi.setEnabled(true);
					chlian.setEnabled(true);
				}

			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_moshi, menu);
		return true;
	}

}
