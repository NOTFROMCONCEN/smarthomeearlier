package com.example.shengsai2018saixiang918;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-18
 */
public class LoginActivity extends Activity {
	private Button btn_login;// 登录按钮
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_ip;// IP地址
	private String user, pass, ip;// STring:用户名、密码、IP
	public static String ip_number, login_op;// IP地址
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录

	// sharedPreferences存储
	static SharedPreferences sharedPreferences;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login_button);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 设置逻辑
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					cb_autologin.setChecked(false);
				}
			}
		});
		// 记住密码自动登录功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("user", null));
				et_user.setText(sharedPreferences.getString("pass", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				// 直接跳转
				if (sharedPreferences.getString("user", null)
						.equals("bizideal")) {
					login_op = "管理员";
				} else {
					login_op = "用户";
				}
				ip_number = sharedPreferences.getString("ip", null);
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			}
		}
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				ip = et_ip.getText().toString();// IP地址
				pass = et_pass.getText().toString();// 密码
				if (ip.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "登陆成功");
						cursor.close();
						if (user.equals("bizideal")) {
							login_op = "管理员";
						} else {
							login_op = "成员";
						}
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
						ip_number = ip;
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误，请重新输入");
						cursor.close();// 关闭
					}
				}
			}
		});
	}
}
