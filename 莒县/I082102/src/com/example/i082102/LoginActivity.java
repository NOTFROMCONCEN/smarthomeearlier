package com.example.i082102;

import com.bizideal.smarthome.socket.ControlUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	EditText etname,etip,etpwd;
	Button btndl,btnzc,btnxg,btntc;
	SQLiteDatabase db;
	Editor editor;
	SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etip=(EditText)findViewById(R.id.edip);
		etname=(EditText)findViewById(R.id.Edname);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		btndl=(Button)findViewById(R.id.Butdl);
		btnzc=(Button)findViewById(R.id.butzc);
		btnxg=(Button)findViewById(R.id.Butxg);
		btntc=(Button)findViewById(R.id.Buttc);
		
		sp=getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		etip.setText(sp.getString("ip", ""));
		etname.setText(sp.getString("name", ""));
		etpwd.setText(sp.getString("pwd", ""));
		etpwd.setTransformationMethod(new password());
		
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
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
				Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", arg);
				if (cursor.getCount()<=0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
					builder.setTitle("µÇÂ¼Ê§°Ü").setMessage("ÃÜÂë»òÓÃ»§Ãû´íÎó").setPositiveButton("Ok", null).show();
				}else {
					editor=sp.edit();
					editor.putString("ip", etip.getText().toString());
					editor.putString("name", etname.getText().toString());
					editor.putString("pwd", etpwd.getText().toString());
					editor.commit();
					
					ControlUtils.setUser("bizideal", "123456", "18.1.10.7");
					startActivity(new Intent(LoginActivity.this,ZongActivity.class));
				}
			}
		});
		btntc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
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
