package com.example.i1020;

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
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText etip,etname,etpwd;
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
		btntc=(Button)findViewById(R.id.Butc);
		
		//��ס����
		sp=getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
		//�������ݿ�
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null)");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//��ס����
		etname.setText(sp.getString("aa", ""));
		etpwd.setText(sp.getString("bb", ""));
		etpwd.setTransformationMethod(new Password());
		//ע��
		btnzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,ResActivity.class));
			}
		});
		//��¼
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etip.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "IP��ַ����Ϊ��", Toast.LENGTH_LONG).show();
				}else {
					String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
					Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", arg);
					if (cursor.getCount()<=0) {
						AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
						builder.setTitle("��¼ʧ��").setMessage("������û�������").setPositiveButton("ok", null).show();
					}else {
						editor=sp.edit();
						editor.putString("aa", etname.getText().toString());
						editor.putString("bb", etpwd.getText().toString());
						editor.commit();
						startActivity(new Intent(LoginActivity.this,ZongActivity.class));
					}
				}
			}
		});
		//�޸�
		btnxg.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this,XgActivity.class));
			}
		});
		//�˳�
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
