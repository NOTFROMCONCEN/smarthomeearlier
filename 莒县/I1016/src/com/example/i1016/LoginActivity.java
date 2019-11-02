package com.example.i1016;

import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.app.AlertDialog;
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
import android.widget.Toast;

public class LoginActivity extends Activity {
	Button btndl,btnzc,btnxg,btntc;
	EditText etname,etip,etpwd;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btndl=(Button)findViewById(R.id.Butdl);
		btnzc=(Button)findViewById(R.id.butzc);
		btnxg=(Button)findViewById(R.id.Butxg);
		btntc=(Button)findViewById(R.id.Buttc);
		etip=(EditText)findViewById(R.id.edip);
		etname=(EditText)findViewById(R.id.Edname);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		etpwd.setTransformationMethod(new Password());
		btnzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,ResActivity.class));
			}
		});
		btnxg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,XgActivity.class));
			}
		});
		btntc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			  if (etname.getText().toString().isEmpty()) {
				Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
			}else {
				String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
				Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?",arg);
				if (cursor.getCount()<=0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
					builder.setTitle("登录失败").setMessage("密码或用户名错误").setPositiveButton("Ok",null).show();
				}else {
					
					startActivity(new Intent(LoginActivity.this,HuaActivity.class));
				}
			}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
