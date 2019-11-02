package com.example.dlian;

import android.app.Activity;
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
import android.widget.Toast;

public class ResActivity extends Activity {
	EditText etname,etpwd,etqrpwd;
	Button btndl,btnres;
	SQLiteDatabase db=null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		etname=(EditText)findViewById(R.id.etname2);
		etpwd=(EditText)findViewById(R.id.etpwd2);
		etqrpwd=(EditText)findViewById(R.id.etqrpwd);
		btndl=(Button)findViewById(R.id.btndl2);
		btnres=(Button)findViewById(R.id.btnres2);
		etpwd.setTransformationMethod(new Password());
		etqrpwd.setTransformationMethod(new Password());
		
		try {
			db=this.openOrCreateDatabase("smarthome.db", Context.MODE_PRIVATE, null);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		btnres.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etname.getText().toString().isEmpty()) {
					Toast.makeText(ResActivity.this, "用户名不能为空", 0).show();
				}else if (!etpwd.getText().toString().equals(etqrpwd.getText().toString())) {
					Toast.makeText(ResActivity.this, "两次密码不一致", 0).show();
					
				}else if (etpwd.getText().toString().length()<6) {
					Toast.makeText(ResActivity.this, "密码长度不能少于6位", 0).show();
				}else {
					ContentValues cValues=new ContentValues();
					cValues.put("name",etname.getText().toString());
					cValues.put("pwd",etpwd.getText().toString());
					long row=db.insert("user", null, cValues);
					if (row<0) {
						Toast.makeText(ResActivity.this, "该用户已存在", 0).show();
					}else {
						Toast.makeText(ResActivity.this, "注册成功", 0).show();
					}
				} 
				
			}
		});
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ResActivity.this,MainActivity.class));
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
