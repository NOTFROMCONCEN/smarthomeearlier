package com.example.listviewandcheckboxforsqlitedemo3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.listviewandcheckboxforsqlitedemo3.mysql.MyDataBaseHelper;
import com.example.listviewandcheckboxforsqlitedemo3.toast.DiyToast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 登录
 * @package_name com.example.listviewandcheckboxforsqlitedemo3
 * @project_name ListViewAndCheckBoxForSQLiteDemo3
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// 文本框
	private EditText et_pass;
	private EditText et_user;
	String pass, user, euser, epass, repass;

	// 按钮
	Button btn_login, btn_reg;

	// 记住密码
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 无title栏
		setContentView(R.layout.activity_main);
		// // 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 绑定
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		// 记住账号密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
		}
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				if (user.isEmpty() || pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项目");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });
					if (cursor.moveToNext()) {
						startActivity(new Intent(MainActivity.this,
								SystemNameActivity.class));
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
		// 注册
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Reg_Dialog();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// 注册自定义Dialog
	private void Reg_Dialog() {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		final View view = inflater.inflate(R.layout.dialog_reg, null, false);
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		builder.setView(view);
		builder.setTitle("注册");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_user = (EditText) view
						.findViewById(R.id.et_euser);
				final EditText et_pass = (EditText) view
						.findViewById(R.id.et_epass);
				final EditText et_repass = (EditText) view
						.findViewById(R.id.et_repass);
				euser = et_user.getText().toString();
				epass = et_pass.getText().toString();
				repass = et_repass.getText().toString();
				if (epass.equals(repass)) {
					if (euser.isEmpty() && epass.isEmpty() && repass.isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请不要留空白项");
						Reg_Dialog();
					} else {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { user, pass });
						if (cursor.moveToNext()) {
							DiyToast.showToast(getApplicationContext(),
									"用户名已存在");
						} else {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { euser, epass });
							DiyToast.showToast(getApplicationContext(), "注册成功");
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "两次密码输入不一致");
					Reg_Dialog();
				}
			}
		});
		builder.show();
	}
}
