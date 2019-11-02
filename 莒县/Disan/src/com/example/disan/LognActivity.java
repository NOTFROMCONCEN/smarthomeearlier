package com.example.disan;

import com.bizideal.smarthometest.lib.SocketThread;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LognActivity extends Activity {
	EditText et1,et2,et3;
	Button btn1;
	CheckBox ch1;
	SQLiteDatabase db;
	Editor editor;
	SharedPreferences sp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logn);
		et1=(EditText)findViewById(R.id.edip);
		et2=(EditText)findViewById(R.id.Edname);
		et3=(EditText)findViewById(R.id.edpwd);
		ch1=(CheckBox)findViewById(R.id.chjizju);
		
		btn1=(Button)findViewById(R.id.Butdl);
		
		sp=getSharedPreferences("sql.xml", Context.MODE_PRIVATE);
		
		try {
			db=this.openOrCreateDatabase("smart.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table user(name text primary key,pwd text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sp.getBoolean("jz", false)) {
			ch1.setChecked(true);
			et2.setText(sp.getString("name", ""));
			et3.setText(sp.getString("pwd", ""));
		}
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{et2.getText().toString(),et3.getText().toString()};
				Cursor cursor=db.rawQuery("select * from user where name=? and pwd=?", arg);
				
				if (cursor.getCount()<0) {
					AlertDialog.Builder builder=new AlertDialog.Builder(LognActivity.this);
					builder.setTitle("µÇÂ¼Ê§°Ü");
					builder.setMessage("ÓÃ»§Ãû»òÃÜÂë´íÎó");
					builder.setPositiveButton("ok", null);
					builder.show();
				}else {
					SocketThread.Port=6006;
					SocketThread.SocketIp="15.1.10.66";
					Toast.makeText(LognActivity.this,"µÇÂ¼³É¹¦",Toast.LENGTH_LONG).show();
					startActivity(new Intent(LognActivity.this,ZongActivity.class));
				}
				editor=sp.edit();
				editor.putString("name", et2.getText().toString());
				editor.putString("pwd", et3.getText().toString());
				if (ch1.isChecked()) {
					editor.putBoolean("jz", true);
				}
				editor.commit();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_logn, menu);
		return true;
	}

}
