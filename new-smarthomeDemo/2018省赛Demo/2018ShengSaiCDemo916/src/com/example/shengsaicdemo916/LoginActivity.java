package com.example.shengsaicdemo916;

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
import android.widget.EditText;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class LoginActivity extends Activity {
	private Button btn_login_con;// 登录按钮
	private EditText et_user;// 用户名
	private EditText et_ip;// Ip地址
	private EditText et_pass;// 密码
	private String ip, user, pass;// 用户名、IP、密码的String数值
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录

	// Ip地址
	static String ip_number;
	// 权限
	static String login_op;
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences存储
	static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 绑定
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login_con = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		// 记住密码自动登录
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {// 自动登录
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(0);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (sharedPreferences.getString("user", null).equals(
								"bizideal")) {
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
				}).start();

			}
			if (sharedPreferences.getBoolean("rember", false) == true) {// 记住密码
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// 设置登录按钮点击事件
		btn_login_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// IP
				if (ip.isEmpty()) {// 如果IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (user.isEmpty()) {// 如果用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 如果密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {// 如果游标移动
						DiyToast.showToast(getApplicationContext(), "登录成功");
						// 跳转
						ip_number = ip;
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						// 自动登录记住密码
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									// 自动登录true
									.putBoolean("rember", true)
									// 记住密码true
									.putString("user", user)// 用户名
									.putString("pass", pass)// 密码
									.putString("ip", ip).commit();// Ip地址
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									// 自动登录false
									.putBoolean("rember", true)
									// 记住密码true
									.putString("user", user)// 用户名
									.putString("pass", pass)// 密码
									.putString("ip", ip).commit();// Ip地址
						} else if (cb_autologin.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									// 自动登录true
									.putBoolean("rember", true)
									// 记住密码true
									.putString("user", user)// 用户名
									.putString("pass", pass)// 密码
									.putString("ip", ip).commit();// Ip地址
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									// 自动登录false
									.putBoolean("rember", false)
									// 记住密码false
									.putString("user", user)// 用户名
									.putString("pass", pass)// 密码
									.putString("ip", ip).commit();// Ip地址
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入不正确，请重新输入");
					}
				}
			}
		});
	}
}