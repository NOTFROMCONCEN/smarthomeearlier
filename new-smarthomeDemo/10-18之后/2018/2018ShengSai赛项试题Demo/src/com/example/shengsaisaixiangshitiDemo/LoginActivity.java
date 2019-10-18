package com.example.shengsaisaixiangshitiDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
 * @描述：登录、记住密码、自动登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-3
 */
public class LoginActivity extends Activity {
	private Button btn_login;
	private EditText et_ip;// IP地址
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private CheckBox cb_autologin;// 自动登录
	private CheckBox cb_rember;// 记住密码
	private String user, pass;
	public static String IP_NUMBER;// IP地址
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences存储
	static SharedPreferences sharedPreferences;
	static String login_op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// 绑定
		get_rember();
		// 自动登录联动效果
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {

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
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				IP_NUMBER = et_ip.getText().toString();// IP地址
				if (IP_NUMBER.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {// 移动
						IP_NUMBER = et_ip.getText().toString();// 赋值
						// 登陆成功，跳转
						if (et_user.getText().toString().equals("bizideal")) {
							login_op = "管理员";
						} else {
							login_op = "用户";
						}
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						// 记住密码自动登录
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", IP_NUMBER).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", IP_NUMBER).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", IP_NUMBER).commit();
						}
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("登录失败")
								.setMessage("用户名或密码输入错误")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					}
				}
			}
		});
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:38:50
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// sharedPreferences存储
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	/*
	 * @方法名：get_rember
	 * 
	 * @功 能：记住密码自动登录
	 * 
	 * @时 间：上午8:49:55
	 */
	private void get_rember() {
		// TODO Auto-generated method stub
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// 记住密码
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// 自动登录
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							// 跳转
							if (sharedPreferences.getString("user", null)
									.toString().equals("bizideal")) {
								login_op = "管理员";
							} else {
								login_op = "用户";
							}
							IP_NUMBER = sharedPreferences.getString("ip", null);
							Intent intent = new Intent(LoginActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
						}
					}
				}).start();
			}
		}
	}
}