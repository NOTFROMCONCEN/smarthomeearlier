package com.example.i1016;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class XgActivity extends Activity {
	EditText etname,etold,etnew;
	Button btntj,btntc;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xg);
		etname=(EditText)findViewById(R.id.edname2);
		etold=(EditText)findViewById(R.id.Edold);
		etnew=(EditText)findViewById(R.id.Ednew);
		btntc=(Button)findViewById(R.id.Butfan);
		btntj=(Button)findViewById(R.id.butxiu);
		
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		btntc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			startActivity(new Intent(XgActivity.this,LoginActivity.class));	
			}
		});
		btntj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{etname.getText().toString()};
				Cursor cursor=db.rawQuery("select * from user where name=? ", arg);
				if (cursor.getCount()<=0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
					builder.setTitle("修改失败").setMessage("输入的用户名不存在").setPositiveButton("Ok",null).show();
				}else {
					String[] arg1=new String[]{etold.getText().toString()};
					Cursor cursor1=db.rawQuery("select * from user where pwd=?", arg1);
					if (cursor1.getCount()<=0) {
						AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
						builder.setTitle("修改失败").setMessage("旧密码错误").setPositiveButton("Ok",null).show();
					}else {
						ContentValues cvValues=new ContentValues();
						cvValues.put("name", etname.getText().toString());
						cvValues.put("pwd", etnew.getText().toString());
						int i=db.update("user", cvValues, "pwd=?", new String[]{etname.getText().toString()});
						if (i>0) {
							AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
							builder.setTitle("修改成功").setMessage("密码修改成功").setPositiveButton("Ok",null).show();
						}
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_xg, menu);
		return true;
	}

}
