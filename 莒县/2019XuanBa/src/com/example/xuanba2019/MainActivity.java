package com.example.xuanba2019;

import com.bizideal.smarthometest.lib.SocketThread;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText et_user, et_pass, et_port, et_ip;
	private Button btn_login, btn_reg;
	private CheckBox cb_rmeber;
	private String user, pass, port, ip;
	private MyDataBasehelper dbHelper;
	private SQLiteDatabase db;
	private String autopass, autouser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		cb_rmeber = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBasehelper(this, "user_table", null, 2);
		db = dbHelper.getReadableDatabase();
		final SharedPreferences rember = getSharedPreferences("rember_pssword",
				MODE_WORLD_WRITEABLE);
		if (rember != null) {
			if (rember.getBoolean("rember", false) == true) {
				cb_rmeber.setChecked(true);
				autopass = rember.getString("pass", null);
				autouser = rember.getString("user", null);
				et_pass.setText(autopass);
				et_user.setText(autouser);
			} else {
				cb_rmeber.setChecked(false);
				et_pass.setText("");
				et_user.setText("");
			}
		} else {
			cb_rmeber.setChecked(false);
			et_pass.setText("");
			et_user.setText("");
		}
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, RegActivity.class);
				startActivity(intent);
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				Cursor cursor = db
						.rawQuery(
								"select * from user_table where username = ? and password = ?",
								new String[] { user, pass });
				if (user.equals("")) {
					Toast.makeText(MainActivity.this, "用户名不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (pass.equals("")) {
					Toast.makeText(MainActivity.this, "密码不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (ip.equals("")) {
					Toast.makeText(MainActivity.this, "服务器IP地址不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (port.equals("")) {
					Toast.makeText(MainActivity.this, "服务器端口不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (!cursor.moveToNext()) {
					Toast.makeText(MainActivity.this, "用户名或密码错误，请重新输入",
							Toast.LENGTH_SHORT).show();
				} else {
					if (cb_rmeber.isChecked()) {
						rember.edit().putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass).commit();
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf("6006");
						Intent intent = new Intent(MainActivity.this,
								CenterActivity.class);
						startActivity(intent);
						finish();
					} else {
						rember.edit().putBoolean("rember", false)
								.putString("user", user)
								.putString("pass", pass).commit();
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf("6006");
						Intent intent = new Intent(MainActivity.this,
								CenterActivity.class);
						startActivity(intent);
						finish();
					}
				}
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
