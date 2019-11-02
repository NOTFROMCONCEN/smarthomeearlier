package com.example.d0924;

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
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etname,etip,etpwd;
	CheckBox chjz;
	Button btndl;
	TextView tvzc;
	SQLiteDatabase db;
	SharedPreferences sp;
	Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etname=(EditText)findViewById(R.id.edname);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		etip=(EditText)findViewById(R.id.Edip);
		chjz=(CheckBox)findViewById(R.id.chejz);
		btndl=(Button)findViewById(R.id.butdl);
		tvzc=(TextView)findViewById(R.id.tezc);
		chjz.setChecked(true);
			
		sp=getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
		try {
			db=this.openOrCreateDatabase("db_user.db", Context.MODE_PRIVATE, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
			if (sp.getBoolean("jz", false)) {
				chjz.setChecked(true);
				etname.setText(sp.getString("aa", ""));
				etpwd.setText(sp.getString("bb", ""));
				etip.setText(sp.getString("cc", ""));
			}
			etpwd.setTransformationMethod(new Password());
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
				Cursor cursor=db.rawQuery("select * from tb_userInfo where name=? and pwd=?",arg);
				if (cursor.getCount()<=0) {
					Toast.makeText(MainActivity.this, "ÕËºÅ»òÃÜÂë´íÎó", Toast.LENGTH_LONG).show();
				}else {
					editor=sp.edit();
					editor.putString("aa", etname.getText().toString());
					editor.putString("bb", etpwd.getText().toString());
					editor.putString("cc", etip.getText().toString());
					
					if (chjz.isChecked()) {
						editor.putBoolean("jz", true);
					}
					editor.commit();
					startActivity(new Intent(MainActivity.this,ZhuActivity.class));
				}
			}
		});
		tvzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,ResActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
