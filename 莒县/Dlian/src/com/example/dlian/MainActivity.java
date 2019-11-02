package com.example.dlian;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etip,etname,etpwd;
	ImageView ivjzmm;
	Button btndl,btnres;
	SQLiteDatabase db=null;
	SharedPreferences.Editor editor;
	SharedPreferences sp;
	boolean kg=false,kgg=false;
			

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etip=(EditText)findViewById(R.id.etip);
		etname=(EditText)findViewById(R.id.etname);
		etpwd=(EditText)findViewById(R.id.etpwd);
		ivjzmm=(ImageView)findViewById(R.id.ivjzmm);
		btndl=(Button)findViewById(R.id.btndl);
		btnres=(Button)findViewById(R.id.btnres);
		sp=this.getSharedPreferences("auto.xml",Context.MODE_PRIVATE);
		try {
			db=this.openOrCreateDatabase("smarthome.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null)");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		etip.setText(sp.getString("ip", ""));
		etname.setText(sp.getString("name", ""));
		etpwd.setText(sp.getString("pwd", ""));
		etpwd.setTransformationMethod(new Password());
		ivjzmm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				kg=!kg;
				if (kg) {
					ivjzmm.setImageResource(R.drawable.jzmmb);
					kgg=true;
				}else {
					ivjzmm.setImageResource(R.drawable.jzmma);
					kgg=false;
				}
			}
		});
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etip.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "IP地址不能为空", 0).show();
				}else {
					String[] str=new String[]{etname.getText().toString(),etpwd.getText().toString()};
					Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", str);
					if (cursor.getCount()<=0) {
						Toast.makeText(MainActivity.this, "用户名或密码错误", 0).show();
					}else {
						if (kgg) {
							editor=sp.edit();
							editor.putString("ip",etip.getText().toString());
							editor.putString("name",etname.getText().toString());
							editor.putString("pwd",etpwd.getText().toString());
							editor.commit();
						}
						startActivity(new Intent(MainActivity.this,ZongActivity.class));
					}
				}
				
			}
		});
		btnres.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,ResActivity.class));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
