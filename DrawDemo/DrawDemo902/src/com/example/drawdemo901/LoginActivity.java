package com.example.drawdemo901;

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
 * @描述：完成登录注册修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-1
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 界面
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 登录界面
	private LinearLayout line_updata_pass;// 登录界面
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;

	// 登录
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata_pass;// 修改密码按钮
	private EditText et_user, et_pass, et_ip;// 用户名、密码、IP地址
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	private String user, pass, ip;
	// 注册
	private Button btn_con;// 确定按钮
	private Button btn_cls;// 关闭按钮
	private EditText et_euser, et_epass, et_repass;// 用户名、密码、确认密码
	private String euser, epass, repass;
	// 修改密码
	private Button btn_updata_con;// 修改密码确定
	private Button btn_updata_cls;// 修改密码关闭
	private EditText et_updata_user, et_updata_newpass, et_updata_oldpass;// 用户名、新密码、旧密码
	private String updata_user, updata_oldpass, updata_newpass;
	// IP地址
	public static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("登录");
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// 设置界面
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);
		// 自动登录记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
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
						if (cb_autologin.isChecked()) {
							ip_number = et_ip.getText().toString();
							Intent intent = new Intent(LoginActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "0")
									.putString("pass", "0").putString("ip", ip)
									.commit();
						}
					}
				}).start();
			}
		} else {
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
		}
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击
	 * 
	 * @时 间：下午8:04:35
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			setTitle("登录");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			et_euser.setText("");
			et_repass.setText("");
			et_epass.setText("");
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 修改密码
			if (euser.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (epass.equals("")) {// 如果密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (repass.equals("")) {// 如果确认密码为空
				DiyToast.showToast(LoginActivity.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {// 如果密码和确认密码一致
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "用户名已存在");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(LoginActivity.this, "注册成功");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
						setTitle("登录");
						et_euser.setText("");
						et_repass.setText("");
						et_epass.setText("");
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "两次密码输入不一致");
				}
			}
			break;
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
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				DiyToast.showToast(LoginActivity.this, "登录成功");
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
			setTitle("注册");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			setTitle("登录");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			et_updata_newpass.setText("");
			et_updata_oldpass.setText("");
			et_updata_user.setText("");
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			updata_newpass = et_updata_newpass.getText().toString();// 新密码
			updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
			updata_user = et_updata_user.getText().toString();// 用户名
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// 新建数据库指针
			if (updata_user.equals("")) {// 用户名
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (updata_oldpass.equals("")) {// 旧密码
				DiyToast.showToast(LoginActivity.this, "请输入旧密码");
			} else if (updata_newpass.equals("")) {// 新密码
				DiyToast.showToast(LoginActivity.this, "请输入新密码");
			} else if (!cursor2.moveToNext()) {
				DiyToast.showToast(LoginActivity.this, "用户名不存在");
			} else {
				if (updata_newpass.equals(updata_oldpass)) {
					DiyToast.showToast(LoginActivity.this, "新旧密码不能一致");
				} else {
					if (updata_oldpass.equals(cursor2.getString(cursor2
							.getColumnIndex("passward")))) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// 更新数据库
						DiyToast.showToast(LoginActivity.this, "密码修改成功");
						setTitle("登录");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
						et_updata_newpass.setText("");
						et_updata_oldpass.setText("");
						et_updata_user.setText("");
					} else {
						DiyToast.showToast(LoginActivity.this, "旧密码输入错误");
					}
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			setTitle("修改密码");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
			break;
		default:
			break;
		}
	}
}