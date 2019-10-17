package com.example.listviewandcheckboxforsqlitedemo6;

import com.example.listviewandcheckboxforsqlitedemo6.mysql.MyDataBaseHelper;
import com.example.listviewandcheckboxforsqlitedemo6.toast.DiyToast;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 登录、注册
 * @package_name com.example.listviewandcheckboxforsqlitedemo6
 * @project_name ListViewAndCheckBoxForSQLiteDemo6
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	private Button btn_login;
	private Button btn_reg;
	private EditText et_user, et_pass, et_euser, et_epass;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// 用户名、密码String数值
	String user, pass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_Reg();
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pass = et_pass.getText().toString();
				user = et_user.getText().toString();
				if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });
					if (cursor.moveToNext()) {
						startActivity(new Intent(MainActivity.this,
								UserInfoActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
	}

	private void dialog_Reg() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reg, null, false);
		builder.setView(view);
		et_epass = (EditText) view.findViewById(R.id.et_epass);
		et_euser = (EditText) view.findViewById(R.id.et_euser);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String euser;
				String epass;
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				if (euser.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
					dialog_Reg();
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入密码");
					dialog_Reg();
				} else {
					Cursor c = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });
					if (c.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
						dialog_Reg();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(getApplicationContext(), "注册成功");
					}
				}
			}
		});
		builder.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}