package com.example.i082102;

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
import android.widget.Toast;

public class ResActivity extends Activity {
	EditText etname,etqr,etpwd;
	Button btnzc,btntc;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		etqr=(EditText)findViewById(R.id.Edpwd2);
		etname=(EditText)findViewById(R.id.Edname1);
		etpwd=(EditText)findViewById(R.id.Edpwd1);
		btntc=(Button)findViewById(R.id.Buttuichu);
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
					builder.setTitle("ע��ʧ��").setMessage("�ظ����벻һ��").setPositiveButton("Ok", null).show();
				}else {
					ContentValues cValues=new ContentValues();
					cValues.put("name",etname.getText().toString());
					cValues.put("pwd",etqr.getText().toString());
					long row=db.insert("user", null, cValues);
					if (row<=0) {
						AlertDialog.Builder builder=new AlertDialog.Builder(ResActivity.this);
						builder.setTitle("ע��ʧ��").setMessage("�û����Ѵ��ڣ�������ע��").setPositiveButton("Ok", null).show();
					}else {
						AlertDialog.Builder builder=new AlertDialog.Builder(ResActivity.this);
						builder.setTitle("ע��ɹ�").setMessage("�û�ע��ɹ�").setPositiveButton("Ok", null).show();
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
