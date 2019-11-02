package com.example.d1014;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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
	EditText etname,etpwd,etip;
	Button btndl;
	TextView tvzc;
	SQLiteDatabase db;
	Editor editor;
	SharedPreferences sp;
	CheckBox chjz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etip=(EditText)findViewById(R.id.Edip);
		etname=(EditText)findViewById(R.id.edname);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		btndl=(Button)findViewById(R.id.butdl);
		tvzc=(TextView)findViewById(R.id.textZc);
		chjz=(CheckBox)findViewById(R.id.chjz);
		chjz.setChecked(true);
		
		
		sp=getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
		
		try {
			db=this.openOrCreateDatabase("db_user.db", Context.MODE_PRIVATE, null);
			db.execSQL("create table tb_userInfo(name text primary key,pwd text not null)");
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (sp.getBoolean("jz", false)) {
			chjz.setChecked(true);
			etname.setText(sp.getString("aa", ""));
			etpwd.setText(sp.getString("bb", ""));
		}
		etpwd.setTransformationMethod(new Password());
		
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] arg=new String[]{etname.getText().toString(),etpwd.getText().toString()};
				Cursor cursor=db.rawQuery("select * from tb_userInfo where name=? and pwd=?", arg);
				if (cursor.getCount()<=0) {
					Toast.makeText(MainActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
				}else {
					editor=sp.edit();
					editor.putString("aa", etname.getText().toString());
					editor.putString("bb", etpwd.getText().toString());
					
					if (chjz.isChecked()) {
						editor.putBoolean("jz", true);
					}
					editor.commit();
					ControlUtils.setUser("bizideal", "123456", "19.1.10.2");
					SocketClient.getInstance().creatConnect();
					SocketClient.getInstance().login(new LoginCallback() {
						
						@Override
						public void onEvent(final String lj) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (lj.equals(ConstantUtil.SUCCESS)) {
										Toast.makeText(MainActivity.this, "网络连接成功 ", Toast.LENGTH_LONG).show();
									}else if (lj.equals(ConstantUtil.FAILURE)) {
										Toast.makeText(MainActivity.this, "网络连接失败 ", Toast.LENGTH_LONG).show();
									}
								}
							});
						}
					});
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
