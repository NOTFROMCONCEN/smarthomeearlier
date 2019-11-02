package com.example.g1014;

import android.app.Activity;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText etname,etpwd,etip;
	Button btndl;
	CheckBox chjz,chdl;
	SharedPreferences sp;
	Editor editor;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etname=(EditText)findViewById(R.id.Edname);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		etip=(EditText)findViewById(R.id.edip);
		btndl=(Button)findViewById(R.id.butdl);
		chdl=(CheckBox)findViewById(R.id.Chdl);
		chjz=(CheckBox)findViewById(R.id.chjz);
		
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null)");
			db.execSQL("insert into user (name,pwd)values('bizideal','123456')");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		sp=getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
		if (sp.getBoolean("jz", false)) {
			chjz.setChecked(true);
			etname.setText(sp.getString("aa", ""));
			etpwd.setText(sp.getString("bb", ""));
			etip.setText(sp.getString("cc", ""));
			
			if (sp.getBoolean("zd", false)) {
				chdl.setChecked(true);
				startActivity(new Intent(LoginActivity.this,ZongActivity.class));
				}
		}
		
		
		
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etip.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "IP地址不能为空", Toast.LENGTH_LONG).show();
				}else if (etname.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
				}else if (etpwd.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
				}else {
					String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
					Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", arg);
					if (cursor.getCount()<=0) {
						Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
					}else {
					
					editor=sp.edit();
					editor.putString("aa", etname.getText().toString());
					editor.putString("bb", etpwd.getText().toString());
					editor.putString("cc", etip.getText().toString());
					if (chjz.isChecked()) {
						editor.putBoolean("jz", true);
					}
					if (chdl.isChecked()) {
						editor.putBoolean("zd", true);
						
					}
					editor.commit();
					startActivity(new Intent(LoginActivity.this,ZongActivity.class));
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
