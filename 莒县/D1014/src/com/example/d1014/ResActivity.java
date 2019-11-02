package com.example.d1014;

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
import android.widget.TextView;
import android.widget.Toast;

public class ResActivity extends Activity {
	EditText etname,etpwd,etqr;
	Button btnzc;
	TextView tvdl;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		etname=(EditText)findViewById(R.id.edname1);
		etpwd=(EditText)findViewById(R.id.Edpwd1);
		etqr=(EditText)findViewById(R.id.Edqe);
		btnzc=(Button)findViewById(R.id.butzc);
		tvdl=(TextView)findViewById(R.id.textdl);
		
		try {
			db=this.openOrCreateDatabase("db_user.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		etpwd.setTransformationMethod(new Password());
		btnzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etname.getText().toString().isEmpty()) {
					Toast.makeText(ResActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
				}else if (etpwd.getText().toString().isEmpty()) {
					Toast.makeText(ResActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
				}else if (!etpwd.getText().toString().equals(etqr.getText().toString())) {
					Toast.makeText(ResActivity.this, "两次密码不一致", Toast.LENGTH_LONG).show();
				}else if (etpwd.getText().toString().length()<6) {
					Toast.makeText(ResActivity.this, "密码长度不足六位", Toast.LENGTH_LONG).show();
				}else {
					ContentValues cvValues=new ContentValues();
					cvValues.put("name", etname.getText().toString());
					cvValues.put("pwd", etqr.getText().toString());
					long row=db.insert("tb_userInfo", null, cvValues);
					if (row<=0) {
						Toast.makeText(ResActivity.this, "用户名已经存在", Toast.LENGTH_LONG).show();
					}else {
						Toast.makeText(ResActivity.this, "用户注册成功", Toast.LENGTH_LONG).show();
					}
				}
			}
		});
		tvdl.setOnClickListener(new OnClickListener() {
			
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
