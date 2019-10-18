package com.example.shengsaiademo2018915;

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
 * @描述：完成登录、注册跳转
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-15
 */
public class LoginActivity extends Activity {
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private EditText et_user;// 用户名
	private EditText et_ip;// IP
	private EditText et_pass;// 密码
	private String user, pass, ip;// 用户名、密码、IPSTring数值
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	public static String ip_number;// IP地址

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
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 自动登录记住密码功能
		sharedPreferences = getSharedPreferences("rember_autologin",
				MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				// 新建自动登录进程s
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);// 3秒延迟
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {// 保持选中状态
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {// 被取消
							// 如果自动登录、记住密码未被选中
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
							cb_autologin.setChecked(false);
							cb_rember.setChecked(false);
						}
					}
				}).start();
			}
			// 记住密码
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// Ip地址
				if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(),
							"ip不正确，请输入正确的ip");
				} else if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					Cursor condition = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (condition.moveToNext()) {// 游标移动
						// 跳转
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
						DiyToast.showToast(getApplicationContext(), "登陆成功");
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							// 如果自动登录、记住密码被选中
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_autologin.isChecked()) {
							// 如果自动登录被选中
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();

						} else if (cb_rember.isChecked()) {
							// 如果记住密码被选中
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							// 如果自动登录、记住密码未被选中
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误，请重新输入");
					}
				}
			}
		});
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						RegActivity.class);
				startActivity(intent);
			}
		});
	}
}
