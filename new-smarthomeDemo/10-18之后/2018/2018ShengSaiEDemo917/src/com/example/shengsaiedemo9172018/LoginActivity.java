package com.example.shengsaiedemo9172018;

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
 * @描述：完成登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-17
 */
public class LoginActivity extends Activity {
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// SharedPreferences存储
	static SharedPreferences sharedPreferences;

	// 定义控件
	private Button btn_login;// 登录按钮
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_ip;// IP地址
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	private String user, pass, ip;// 用户名、密码、IP地址
	// IP地址
	static String ip_number;
	static String login_op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 绑定
		btn_login = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 设置记住密码自动登录逻辑
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				// 自动登录关闭、打开，联动记住密码按钮
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
				// 记住密码关闭、关闭自动登录
				if (isChecked) {

				} else {
					if (cb_autologin.isChecked()) {
						cb_autologin.setChecked(false);
					}
				}
			}
		});
		// 记住密码自动登录功能实现
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// 记住密码
				cb_rember.setChecked(true);// 记住密码复选框
				et_ip.setText(sharedPreferences.getString("ip", null));// IP
				et_pass.setText(sharedPreferences.getString("pass", null));// 密码
				et_user.setText(sharedPreferences.getString("user", null));// 用户名
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// 自动登录
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] {
												sharedPreferences.getString(
														"user", null),
												sharedPreferences.getString(
														"pass", null) });// 新建数据库游标
						if (cursor.moveToNext()) {
							if (cb_autologin.isChecked()) {
								ip_number = sharedPreferences.getString("ip",
										null);
								if (sharedPreferences.getString("user", null)
										.toString().equals("bizideal")) {
									login_op = "管理员";
								} else {
									login_op = "用户";
								}
								Intent intent = new Intent(LoginActivity.this,
										BarActivity.class);
								startActivity(intent);
								finish();
							} else {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", "")
										.putString("pass", "")
										.putString("ip", "").commit();
							}
						} else {
							System.err.println("账户异常");
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
							cb_autologin.setChecked(false);
							cb_rember.setChecked(false);
							et_ip.setText("");
							et_pass.setText("");
							et_user.setText("");
						}
					}
				}).start();
			}
		}
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// IP地址
				if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (user.isEmpty()) {// 用户名为空\
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {// 游标移动\
						DiyToast.showToast(getApplicationContext(), "登陆成功");
						ip_number = ip;
						if (user.equals("bizideal")) {
							login_op = "管理员";
						} else {
							login_op = "用户";
						}
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							// 记住密码、自动登录
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							// 记住密码
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
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码不正确，请重新输入");
					}
				}
			}
		});
	}
}
