package com.example.drawdemo827;

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
 * @描述：完成登录注册修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-27
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 布局
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面
	private LinearLayout line_updata_pass;// 修改密码界面
	// 登录界面
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata_pass;// 修改密码按钮
	private EditText et_user, et_pass, et_ip;// 文本框
	private CheckBox cb_autologin;// 自动登录复选框
	private CheckBox cb_rember;// 记住密码复选框
	private String user, pass, ip;

	// 注册界面
	private Button btn_reg_con;// 注册确定按钮
	private Button btn_reg_cls;// 注册关闭按钮
	private EditText et_reg_user, et_reg_pass, et_reg_repass;// 文本框
	private String reg_user, reg_pass, reg_repass;

	// 修改密码界面
	private Button btn_updata_con;// 修改密码确定按钮
	private Button btn_updata_cls;// 修改密码关闭按钮
	private EditText et_updata_user, et_updata_old_pass, et_updata_new_pass;// 文本框
	private String updata_user, updata_old_pass, updata_new_pass;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// sharedPreferences存储
	private SharedPreferences sharedPreferences;

	// IP地址
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
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
		et_updata_new_pass = (EditText) findViewById(R.id.et_updata_new_pass);
		et_updata_old_pass = (EditText) findViewById(R.id.et_updata_old_pass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updatapass);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);

		// 设置界面显示
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);

		// 记住密码自动登录
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
						// 自动登录成功
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
			if (user.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (pass.equals("")) {// 如果密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (ip.equals("")) {// 如果IP地址为空
				DiyToast.showToast(LoginActivity.this, "请输入IP地址");
			} else if (!cursor.moveToNext()) {// 如果数据库无匹配项
				DiyToast.showToast(LoginActivity.this, "密码或用户名输入错误");
			} else {
				DiyToast.showToast(LoginActivity.this, "登录成功");
				ip_number = et_ip.getText().toString();
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
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
			}
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_cls:
			// 注册关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_con:
			// 注册确定
			reg_pass = et_reg_pass.getText().toString();// 密码
			reg_repass = et_reg_repass.getText().toString();// 确认密码
			reg_user = et_reg_user.getText().toString();// 用户名
			Cursor cursor1 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { reg_user });// 新建数据库指针
			if (reg_user.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (reg_pass.equals("")) {// 如果密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (reg_repass.equals("")) {// 如果确认密码为空
				DiyToast.showToast(LoginActivity.this, "请输入确认密码");
			} else {
				if (reg_pass.equals(reg_repass)) {// 如果密码和确认密码一致
					if (cursor1.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "用户名已存在");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { reg_user, reg_pass });// 插入数据库
						DiyToast.showToast(LoginActivity.this, "注册成功");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "两次密码输入不一致");
				}
			}
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			updata_old_pass = et_updata_old_pass.getText().toString();// 旧密码
			updata_user = et_user.getText().toString();// 用户名
			updata_new_pass = et_updata_new_pass.getText().toString();// 新密码
			if (updata_user.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (updata_old_pass.equals("")) {// 如果密码为空
				DiyToast.showToast(LoginActivity.this, "请输入旧密码");
			} else if (updata_new_pass.equals("")) {// 如果新密码为空
				DiyToast.showToast(LoginActivity.this, "请输入新密码");
			} else {
				if (!updata_old_pass.equals(updata_new_pass)) {
					Cursor cursor2 = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// 新建数据库指针
					cursor2.moveToFirst();
					String get_passward = cursor2.getString(cursor2
							.getColumnIndex("passward"));
					if (get_passward.equals(updata_old_pass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_new_pass, updata_user });// 更新数据库
						DiyToast.showToast(LoginActivity.this, "密码修改成功");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					} else {
						DiyToast.showToast(LoginActivity.this, "旧密码输入错误");
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "新旧密码不能一致");
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
