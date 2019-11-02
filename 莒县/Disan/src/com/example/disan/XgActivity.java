package com.example.disan;

import android.R.integer;
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
	EditText et1,et2,et3;
	Button btn1,btn2;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_xg);
		et1=(EditText)findViewById(R.id.edname2);
		et2=(EditText)findViewById(R.id.Edold);
		et3=(EditText)findViewById(R.id.Ednew);
		btn1=(Button)findViewById(R.id.Butxiugai);
		btn2=(Button)findViewById(R.id.Butgaunbi);
		
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues cvValues=new ContentValues();
				cvValues.put("name", et1.getText().toString());
				cvValues.put("pwd", et3.getText().toString());
				
				String[] arg=new String[]{et1.getText().toString(),et2.getText().toString()}; 
				Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", arg);
				if (cursor.getCount()<=0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
					builder.setTitle("修改失败");
					builder.setMessage("旧密码错误");
					builder.setPositiveButton("ok", null);
					builder.show();
				}else {
					int i=db.update("user", cvValues, "name=?", new String[]{et1.getText().toString()});
					if (i>0) {
						AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
						builder.setTitle("修改成功");
						builder.setMessage("用户修改成功");
						builder.setPositiveButton("ok", null);
						builder.show();
					}
				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(XgActivity.this,MainActivity.class));
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
