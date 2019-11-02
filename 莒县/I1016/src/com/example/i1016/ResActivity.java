package com.example.i1016;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ResActivity extends Activity {
	EditText etname,etpwd,etqr;
	Button btnzc,btntc;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		etname=(EditText)findViewById(R.id.edname1);
		etpwd=(EditText)findViewById(R.id.Edpwd1);
		etqr=(EditText)findViewById(R.id.Edpwd2);
		btntc=(Button)findViewById(R.id.Butui);
		btnzc=(Button)findViewById(R.id.butzhu);

		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		btnzc.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!etpwd.getText().toString().equals(etqr.getText().toString())) {
					AlertDialog.Builder builder=new AlertDialog.Builder(ResActivity.this);
					builder.setTitle("注册失败").setMessage("重复密码不一致").setPositiveButton("Ok", null).show();	
				}else {
					ContentValues cvValues=new ContentValues();
					cvValues.put("name", etname.getText().toString());
					cvValues.put("pwd", etqr.getText().toString());
					long row=db.insert("user", null, cvValues);
					if (row<=0) {
						AlertDialog.Builder builder=new AlertDialog.Builder(ResActivity.this);
						builder.setTitle("注册失败").setMessage("用户名已经存在,请重新注册").setPositiveButton("Ok", null).show();	
					}else {
						AlertDialog.Builder builder=new AlertDialog.Builder(ResActivity.this);
						builder.setTitle("注册成功").setMessage("用户注册成功").setPositiveButton("Ok", null).show();	
					}
				}	
			}
		});
		btntc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ResActivity.this,LoginActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_res, menu);
		return true;
	}

}
