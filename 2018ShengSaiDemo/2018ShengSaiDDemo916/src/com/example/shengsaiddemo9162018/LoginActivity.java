package com.example.shengsaiddemo9162018;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录功能、记住密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class LoginActivity extends Activity {
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private EditText et_user, et_pass, et_port, et_ip;// 用户名、密码、IP地址、端口号文本框
	private String user, pass, port, ip;// 用户名、密码、IP地址、端口号String数值
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// 存储
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 记住密码功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		// 设置文本框显示样式
		et_pass.setTransformationMethod(new TextChanger());
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// IP地址
				port = et_port.getText().toString();// 端口号
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (port.isEmpty()) {// 端口号为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username =? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {// 如果游标移动
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port).commit();// 插入sharedPreferences存储
						// 登陆成功
						DiyToast.showToast(getApplicationContext(), "登录成功");
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(), "用户名或密码有误");
					}
				}
			}
		});
		// 注册按钮跳转
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(LoginActivity.this,
						RegActivity.class);
				startActivity(intent);
			}
		});
	}
}
