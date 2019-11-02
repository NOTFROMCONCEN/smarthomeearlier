package com.example.disan;

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
	EditText et1,et2,et3;
	Button btn1;
	SQLiteDatabase db;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		et1=(EditText)findViewById(R.id.edname1);
		et2=(EditText)findViewById(R.id.Edpwd1);
		et3=(EditText)findViewById(R.id.Edqr);
    	btn1=(Button)findViewById(R.id.Butzhuce);
    	
    	try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
    	btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ContentValues cValues=new ContentValues();
				cValues.put("name", et1.getText().toString());
				cValues.put("pwd", et3.getText().toString());
				
				long row=db.insert("user", null, cValues);
				if (row<0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(ResActivity.this);
					builder.setTitle("注册失败");
					builder.setMessage("用户已存在");
					builder.setPositiveButton("ok", null);
					builder.show();
				}else {
					Toast.makeText(ResActivity.this, "注册成功", Toast.LENGTH_LONG).show();
					startActivity(new Intent(ResActivity.this,MainActivity.class));
				}
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
