package com.example.j1019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

public class ShujuActivity extends Activity {
	ListView lv;
	Spinner spws;
	Button btncha;
	int cur=0,idc=1,syj=1;
	public static boolean jiben=false,login=false,btnflag=false;
	List<Map<String, String>> list=new ArrayList<Map<String,String>>();
	Handler handler;
	Runnable runnable;
	Cursor cursor;
	SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shuju);
		lv=(ListView)findViewById(R.id.listView1);
		spws=(Spinner)findViewById(R.id.spinner1);
		btncha=(Button)findViewById(R.id.butcha);
		btncha.setEnabled(false);
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table if not exsits shuju(ming text not null,zhi text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		handler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (btnflag) {
					btnflag=false;
					btncha.setEnabled(true);
				}else if (jiben) {
					jiben=false;
					startActivity(new Intent(ShujuActivity.this,ZongActivity.class));
					finish();
				}else if (login) {
					login=false;
					startActivity(new Intent(ShujuActivity.this,MainActivity.class));
					finish();
				}
				handler.postDelayed(runnable, 200);
			}
		};
		runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.sendEmptyMessage(0x1234);
			}
		};
		handler.post(runnable);
		spws.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				String sql=null;
				cur=0;syj=1;
				switch (arg2) {
				case 9:
					sql="select * from shuju";
					cursor=db.rawQuery(sql, null);
					break;

				default:
					sql="select * from shuju where ming='"+spws.getItemAtPosition(arg2).toString()+"'";
					cursor=db.rawQuery(sql, null);
					break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btncha.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				list.clear();
				cursor.moveToPosition(cur);
				do {
				Map<String, String> map=new HashMap<String, String>();
				map.put("id",String.valueOf(idc));
				map.put("ming", cursor.getString(0));
				map.put("zhi", cursor.getString(1));
				list.add(map);
				cur++;
				idc++;
				} while (cursor.moveToNext()&&cur<9*syj);
				idc=1;
				SimpleAdapter adapter=new SimpleAdapter(ShujuActivity.this, list, R.layout.listview, new String[]{"id","ming","zhi"},new int[]{R.id.te1,R.id.te2,R.id.te3});
				lv.setAdapter(adapter);
				syj++;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_shuju, menu);
		return true;
	}

}
