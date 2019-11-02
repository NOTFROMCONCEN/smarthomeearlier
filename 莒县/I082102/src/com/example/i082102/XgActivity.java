package com.example.i082102;

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
		etname=(EditText)findViewById(R.id.Edname2);
		etold=(EditText)findViewById(R.id.Edold);
		etnew=(EditText)findViewById(R.id.Ednew);
		btntj=(Button)findViewById(R.id.buttj);
		btntc=(Button)findViewById(R.id.Butfan);
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		btntj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{etname.getText().toString()};
				Cursor cursor=db.rawQuery("select * from user where name=?", arg);
				if (cursor.getCount()<=0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
					builder.setTitle("�޸�ʧ��").setMessage("�޸�ʧ�ܣ�������û���������").setPositiveButton("Ok", null).show();
				}else {
					String[] arg1=new String[]{etold.getText().toString()};
					Cursor cursor1=db.rawQuery("select * from user where pwd=?", arg1);
					if (cursor1.getCount()<=0) {
						AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
						builder.setTitle("�޸�ʧ��").setMessage("���������").setPositiveButton("Ok", null).show();
					}else {
						ContentValues cValues=new ContentValues();
						cValues.put("name", etname.getText().toString());
						cValues.put("pwd", etnew.getText().toString());
						int i=db.update("user", cValues, "name=?", new String[]{etname.getText().toString()});
						if (i>0) {
							AlertDialog.Builder builder=new AlertDialog.Builder(XgActivity.this);
							builder.setTitle("�޸ĳɹ�").setMessage("�����޸ĳɹ�").setPositiveButton("Ok", null).show();
						}
					}
				}
			}
		});
		btntc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(XgActivity.this,LoginActivity.class));
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
