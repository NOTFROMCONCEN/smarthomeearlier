package com.example.e0926;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;

public class LianActivity extends Activity {
	CheckBox chmoshi,chlian,chzhil;
	Switch swmoshi;
	EditText etlian;
	Spinner spws,spdx,spkg;
	EditText etsb,etdz,etqu;
	Button btncun,btnqu;
	ListView lv;
	TextView tvhui;
	boolean zj=false,lj=false,zhil=false;
	Cursor cur;
	int idc=0,idc1=0;
	String shij;
	List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	Map<String, String> map;
	SimpleAdapter adapter;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lian);
		chmoshi=(CheckBox)findViewById(R.id.chmoshi);
		chlian=(CheckBox)findViewById(R.id.Chlian);
		chzhil=(CheckBox)findViewById(R.id.Chzhiling);
		swmoshi=(Switch)findViewById(R.id.swmoshi);
		spws=(Spinner)findViewById(R.id.spws);
		spdx=(Spinner)findViewById(R.id.Spdx);
		spkg=(Spinner)findViewById(R.id.Spkg);
		etsb=(EditText)findViewById(R.id.edzhil);
		etdz=(EditText)findViewById(R.id.Eddongz);
		etqu=(EditText)findViewById(R.id.Edqu);
		btncun=(Button)findViewById(R.id.butcun);
		btnqu=(Button)findViewById(R.id.Butqu);
		lv=(ListView)findViewById(R.id.listView1);
		tvhui=(TextView)findViewById(R.id.tegz);
		etlian=(EditText)findViewById(R.id.edlian);
		SimpleDateFormat format=new SimpleDateFormat("HH:mm");
		shij=format.format(new java.util.Date());
		try {
			db=openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table zhiling(bh text primary key,sb text not null,dz text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		chmoshi.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chlian.setChecked(false);
					chzhil.setChecked(false);
					if (!swmoshi.isChecked()) {
						ControlUtils.control(ConstantUtil.INFRARED_1_SERVE,"空调开",ConstantUtil.OPEN);
						idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("空调"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
						adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);		
						ControlUtils.control(ConstantUtil.Lamp,ConstantUtil.CHANNEL_ALL,ConstantUtil.OPEN);
						idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("射灯"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
						adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						ControlUtils.control(ConstantUtil.Curtain,ConstantUtil.CHANNEL_1,ConstantUtil.OPEN);
						idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("窗帘"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
						adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
					}else if (swmoshi.isChecked()) {
						lj=true;
						new Thread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (lj) {
									try {
										Thread.sleep(1000);
									} catch (Exception e) {
										// TODO: handle exception
									}
									if (JibenActivity.smoke>600) {
										ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("报警灯"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
										adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
										ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("窗帘"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
										adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
										ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("换气扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
										adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
									}else if (JibenActivity.man==1) {
										ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("报警灯"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
										adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
										ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("窗帘"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
										adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
										ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
										idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("空调"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
										adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
									}
								}
							}
						}).start();
					}else {
						lj=false;
					}
				}
			}
		});
		chlian.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					chmoshi.setChecked(false);
					chzhil.setChecked(false);
					if (etlian.getText().toString().isEmpty()) {
						Toast.makeText(LianActivity.this, "输入数据为空", Toast.LENGTH_LONG).show();
						chlian.setChecked(false);
					}else if (!etlian.getText().toString().matches("[0-9]+")) {
						Toast.makeText(LianActivity.this, "输入数据有误", Toast.LENGTH_LONG).show();
						chlian.setChecked(false);
					}else {
						String v1=spws.getSelectedItem().toString();
						String v2=spdx.getSelectedItem().toString();
						String v3=spkg.getSelectedItem().toString();
						int v4=Integer.parseInt(etlian.getText().toString());
						if (v1.equals("温度")&&v2.equals(">=")&&JibenActivity.temp>=v4&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<v4&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("温度")&&v2.equals(">=")&&JibenActivity.temp>=v4&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("温度")&&v2.equals("<")&&JibenActivity.temp<v4&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals(">=")&&JibenActivity.hum>=v4&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<v4&&v3.equals("开")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("开"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals(">=")&&JibenActivity.hum>=v4&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}else if (v1.equals("湿度")&&v2.equals("<")&&JibenActivity.hum<v4&&v3.equals("关")) {
							ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							idc+=1;map=new HashMap<String, String>();map.put("id",String.valueOf(idc));map.put("dq",String.valueOf("风扇"));map.put("kg",String.valueOf("关"));map.put("sj",String.valueOf(shij));list.add(map);
							adapter=new SimpleAdapter(LianActivity.this, list, R.layout.listview, new String[]{"id","dq","kg","sj"}, new int[]{R.id.te1,R.id.te2,R.id.te3,R.id.te4});lv.setAdapter(adapter);
						}
					}
				}
			}
		});
		chzhil.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					zhil=true;
					chlian.setChecked(false);
					chmoshi.setChecked(false);
				}else {
					zhil=false;
				}
			}
		});
		btncun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etsb.getText().toString().isEmpty()||etdz.getText().toString().isEmpty()) {
					Toast.makeText(LianActivity.this,"未填入数据",0).show();
				}else {
					idc1++;
					ContentValues cValues=new ContentValues();
					cValues.put("bh",String.valueOf(idc1));
					cValues.put("sb", etsb.getText().toString());
					cValues.put("dz", etdz.getText().toString());
					db.insert("zhiling", null,cValues);
					String tishi=Integer.toString(idc1);
					AlertDialog.Builder builder=new AlertDialog.Builder(LianActivity.this);
					builder.setTitle("提示").setMessage(tishi).setPositiveButton("Ok", null).show();
				}
			}
		});
		btnqu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (zhil) {
					if (etqu.getText().toString().isEmpty()) {
						Toast.makeText(LianActivity.this, "编号不能为空",0).show();
					}else {
						//					String[] arg=new String[]{etqu.getText().toString()};
						//					Cursor cursor=db.rawQuery("select * from user where bh=?",arg);  
						Cursor cursor=db.rawQuery("select * from zhiling", null);  
						cursor.moveToPosition(Integer.valueOf(etqu.getText().toString()));
						if(cursor.getCount()>0){
							if(cursor.getString(1).equals("射灯") && cursor.getString(2).equals("开")){
								ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else if(cursor.getString(1).equals("射灯") && cursor.getString(2).equals("关")){
								ControlUtils.control(ConstantUtil.Lamp, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}else if(cursor.getString(1).equals("报警灯") && cursor.getString(2).equals("开")){
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else if(cursor.getString(1).equals("报警灯") && cursor.getString(2).equals("关")){
								ControlUtils.control(ConstantUtil.WarningLight, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}else if(cursor.getString(1).equals("窗帘") && cursor.getString(2).equals("开")){
								ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_3, ConstantUtil.OPEN);
							}else if(cursor.getString(1).equals("窗帘") && cursor.getString(2).equals("关")){
								ControlUtils.control(ConstantUtil.Curtain, ConstantUtil.CHANNEL_1, ConstantUtil.CLOSE);
							}else if(cursor.getString(1).equals("风扇") && cursor.getString(2).equals("开")){
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}else if(cursor.getString(1).equals("风扇") && cursor.getString(2).equals("关")){
								ControlUtils.control(ConstantUtil.Fan, ConstantUtil.CHANNEL_ALL, ConstantUtil.CLOSE);
							}else if(cursor.getString(1).equals("门禁") && cursor.getString(2).equals("开")){
								ControlUtils.control(ConstantUtil.RFID_Open_Door, ConstantUtil.CHANNEL_ALL, ConstantUtil.OPEN);
							}
						}else {
							Toast.makeText(LianActivity.this,"输入的指令不存在",0).show();
						}
					}
				}
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_lian, menu);
		return true;
	}

}
