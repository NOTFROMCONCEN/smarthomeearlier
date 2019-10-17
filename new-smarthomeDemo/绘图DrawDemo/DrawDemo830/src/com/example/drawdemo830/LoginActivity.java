package com.example.drawdemo830;

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
import android.widget.LinearLayout;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录、注册、修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-30
 */
public class LoginActivity extends Activity implements OnClickListener {

	// IP地址
	public static String ip_number;
	// 界面
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面
	private LinearLayout line_updata_pass;// 修改密码界面
	// 登录
	private Button btn_login;
	private Button btn_reg;
	private Button btn_updata_pass;
	private CheckBox cb_autologin;
	private CheckBox cb_rember;
	private EditText et_user, et_pass, et_ip;
	private String user, pass, ip;
	// 注册
	private Button btn_reg_con;
	private Button btn_reg_cls;
	private EditText et_reg_user, et_reg_pass, et_reg_repass;
	private String euser, epass, repass;
	// 修改密码
	private Button btn_updata_con;
	private Button btn_updata_cls;
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;
	private String updata_user, updata_newpass, updata_oldpass;

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
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		// 按钮点击事件
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 自动登录记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
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
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}).start();
			}
		} else {
			et_ip.setText("");
			et_pass.setText("");
			et_user.setText("");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (pass.equals("")) {// 密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (ip.equals("")) {// IP地址为空
				DiyToast.showToast(LoginActivity.this, "请输入IP地址");
			} else if (!cursor.moveToNext()) {
				DiyToast.showToast(LoginActivity.this, "用户名或密码输入错误");
			} else {
				// 登录完成
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				}
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.btn_reg:
			// 注册
			setTitle("注册");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_cls:
			// 注册关闭
			setTitle("登录");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_con:
			// 注册确定
			euser = et_reg_user.getText().toString();// 用户名
			epass = et_reg_pass.getText().toString();// 用户名
			repass = et_reg_repass.getText().toString();// 用户名
			if (euser.equals("")) {// 用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (epass.equals("")) {// 密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (repass.equals("")) {// 确认密码为空
				DiyToast.showToast(LoginActivity.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor2 = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor2.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "用户名已存在");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
						DiyToast.showToast(LoginActivity.this, "注册成功");
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "两次输入密码不一致！");
				}
			}
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			setTitle("登录");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			updata_newpass = et_updata_newpass.getText().toString();// 新密码
			updata_oldpass = et_updata_oldpass.getText().toString();// 新密码
			updata_user = et_updata_user.getText().toString();// 新密码
			Cursor cursor_getuser = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// 新建数据库指针
			if (updata_user.equals("")) {// 用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (updata_oldpass.equals("")) {// 旧密码为空
				DiyToast.showToast(LoginActivity.this, "请输入旧密码");
			} else if (updata_newpass.equals("")) {// 新密码为空
				DiyToast.showToast(LoginActivity.this, "请输入新密码");
			} else {
				if (cursor_getuser.moveToNext()) {
					Cursor cursor_getoldpass = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });
					cursor_getoldpass.moveToFirst();
					String oldpass = cursor_getoldpass
							.getString(cursor_getoldpass
									.getColumnIndex("passward"));
					if (updata_oldpass.equals(oldpass)) {
						if (updata_newpass.equals(updata_oldpass)) {
							DiyToast.showToast(LoginActivity.this, "新旧密码不能一致！");
						} else {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// 更新数据库
							DiyToast.showToast(LoginActivity.this, "修改密码成功");
							line_login.setVisibility(View.VISIBLE);
							line_reg.setVisibility(View.GONE);
							line_updata_pass.setVisibility(View.GONE);
						}
					} else {
						DiyToast.showToast(LoginActivity.this, "旧密码输入错误");
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "用户名错误");
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			setTitle("修改密码");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}