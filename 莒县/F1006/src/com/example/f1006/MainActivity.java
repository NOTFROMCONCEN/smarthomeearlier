package com.example.f1006;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	Button btndl,btnzc,btnxg,btnzh;
	Button btnzhu,btnxiu;
	EditText etpwd,etip,etduankou;
	Spinner spname;
	EditText etname1,etpwd1,etqr,etqr1;
	EditText etxg,etold,etnew;
	RadioButton raxz,rashuru;
	CheckBox chjz;
	SQLiteDatabase db;
	Editor editor;
	SharedPreferences sp;
	AlertDialog builder,builderxg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btndl=(Button)findViewById(R.id.butdl);
		btnzc=(Button)findViewById(R.id.Butzc);
		btnxg=(Button)findViewById(R.id.Butxg);
		btnzh=(Button)findViewById(R.id.Butzh);
	    spname=(Spinner)findViewById(R.id.spname);
	    etpwd=(EditText)findViewById(R.id.Edpwd);
	    etip=(EditText)findViewById(R.id.edip);
	    etduankou=(EditText)findViewById(R.id.Edduankou);
	    raxz=(RadioButton)findViewById(R.id.raxz);
	    rashuru=(RadioButton)findViewById(R.id.Rashur);
	    chjz=(CheckBox)findViewById(R.id.chjz);
	    raxz.setEnabled(false);
	    
	    sp=getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
	    try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null,qr text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
	     if (sp.getBoolean("jz", false)) {
			chjz.setChecked(true);
			// etname.setText(sp.getString("aa", ""));
		     etpwd.setText(sp.getString("bb", ""));
		}
	     btnzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builder=new AlertDialog.Builder(MainActivity.this).create();
				builder.setCancelable(true);
				builder.show();
				View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.res, null);
				Window window=builder.getWindow();
				window.setContentView(view);
				
				btnzhu=(Button)window.findViewById(R.id.butzhu);
				etname1=(EditText)window.findViewById(R.id.edname1);
				etpwd1=(EditText)window.findViewById(R.id.Edpwd1);
				etqr1=(EditText)window.findViewById(R.id.Edqr1);
				etqr=(EditText)window.findViewById(R.id.Edqr);
				
				btnzhu.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if (!etpwd1.getText().toString().equals(etqr.getText().toString())) {
							Toast.makeText(MainActivity.this,"两次密码不一致",Toast.LENGTH_LONG).show();
						}else {
							ContentValues cValues=new ContentValues();
							cValues.put("name", etname1.getText().toString());
							cValues.put("pwd", etpwd1.getText().toString());
							cValues.put("qr", etqr1.getText().toString());
							long row=db.insert("user", null, cValues);
							if (row<=0) {
								Toast.makeText(MainActivity.this,"账号已存在",Toast.LENGTH_LONG).show();
							}else {
								Toast.makeText(MainActivity.this, "注册成功 ", Toast.LENGTH_LONG).show();
								raxz.setEnabled(true);
								builder.dismiss();
							}
						}
					}
				});
			}
		});
	     btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etpwd.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
				}
				//else {
				//	String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
					//Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", arg);
					//if (cursor.getCount()<=0) {
					//	Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
				//	}
			else {
//						editor=sp.edit();
//						editor.putString("aa", etname.getText().toString());
//						editor.putString("bb", etname.getText().toString());
//						if (chjz.isChecked()) {
//							editor.putBoolean("jz", true);
//						}
					//	editor.commit();
						startActivity(new Intent(MainActivity.this,ZongActivity.class));
					}
			//	}
			}
		});
	     btnxg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				builderxg=new AlertDialog.Builder(MainActivity.this).create();
				builderxg.setCancelable(true);
				builderxg.show();
				View view=LayoutInflater.from(MainActivity.this).inflate(R.layout.xiu, null);
				Window window=builderxg.getWindow();
				window.setContentView(view);
				
				btnxiu=(Button)window.findViewById(R.id.butxiu);
				etxg=(EditText)window.findViewById(R.id.ednamex);
				etold=(EditText)window.findViewById(R.id.Edpwdx);
				etnew=(EditText)window.findViewById(R.id.Edqrx);
				
				btnxiu.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String[] arg1=new String[]{etxg.getText().toString()};
						Cursor cursor=db.rawQuery("select * from user where name=?", arg1);
						if (cursor.getCount()<=0) {
							Toast.makeText(MainActivity.this, "用户名不存在", Toast.LENGTH_LONG).show();
						}else {
								String[] arg2=new String[]{etold.getText().toString()};
								Cursor cursor1=db.rawQuery("select * from user where pwd=?", arg2);
								if (cursor1.getCount()<=0) {
									AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
									builder.setTitle("修改失败").setMessage("原密码不正确").setPositiveButton("Ok", null).show();
								}else {
									ContentValues cValues=new ContentValues();
									cValues.put("name", etxg.getText().toString());
									cValues.put("pwd", etnew.getText().toString());
									int i=db.update("user", cValues, "name=?", new String[]{etxg.getText().toString()});
									if (i>0) {
										AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
										builder.setTitle("修改成功").setMessage("密码修改成功").setPositiveButton("Ok", null).show();
										builderxg.dismiss();
									}
								}
						}
						
					}
				});
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
