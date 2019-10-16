package com.example.drawdemo822zhuzhuangtu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录注册修改密码查询数据库功能s
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-22
 */
public class LoginActivity extends Activity implements OnClickListener {

	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata_pass;// 修改密码按钮
	private Button btn_sqlite;// 查询数据库按钮
	private Button btn_reg_con;// 注册确定按钮
	private Button btn_reg_cls;// 注册关闭按钮
	private Button btn_updata_con;// 修改密码确定按钮
	private Button btn_updata_cls;// 修改密码关闭功能
	private Button btn_getsqlite_back;// 获取数据库返回按钮
	private Button btn_getsqlite_delete;// 数据库删除
	private Button btn_getsqlite_updata;// 更新数据库
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	private String user, pass, repass, epass, euser, ip;// 登录注册String数值
	private String updata_newpass, updata_oldpass, updata_user;// 修改密码String数值
	private String get_user, get_pass;// 修改密码获取密码、用户名
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private SharedPreferences sharedPreferences;// sharedPreferences存储
	private EditText et_user, et_pass, et_ip, et_euser, et_epass, et_repass,
			et_updata_user, et_updata_oldpass, et_updata_newpass;// 文本框
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面
	private LinearLayout line_updata_pass;// 修改密码界面
	private LinearLayout line_get_sqlite;// 查询数据库界面
	private ListView lv_1;// ListView
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		lv_1 = (ListView) findViewById(R.id.lv_get_sqlite);
		btn_sqlite = (Button) findViewById(R.id.btn_get_sqlite);
		btn_getsqlite_back = (Button) findViewById(R.id.btn_back);
		btn_getsqlite_delete = (Button) findViewById(R.id.btn_delete_sqlite);
		btn_getsqlite_updata = (Button) findViewById(R.id.btn_updata_sqlite);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		line_get_sqlite = (LinearLayout) findViewById(R.id.line_get_sqlite);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_user = (EditText) findViewById(R.id.et_user);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		cb_autologin = (CheckBox) findViewById(R.id.ck_autologin);
		cb_rember = (CheckBox) findViewById(R.id.ck_rember);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_getsqlite_back.setOnClickListener(this);
		btn_getsqlite_delete.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_getsqlite_updata.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		btn_sqlite.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// 自动登录记住密码功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false == true)) {
				cb_rember.setChecked(true);
				String getuser, getpass, getip;
				getip = sharedPreferences.getString("ip", null);
				getpass = sharedPreferences.getString("pass", null);
				getuser = sharedPreferences.getString("user", null);
				et_ip.setText(getip);
				et_user.setText(getuser);
				et_pass.setText(getpass);
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
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
			et_user.setText("");
			et_pass.setText("");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			// 数据库页面返回按钮
			line_login.setVisibility(View.VISIBLE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_cls:
			// 注册页面返回按钮
			line_login.setVisibility(View.VISIBLE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_cls:
			// 修改密码页面返回按钮
			line_login.setVisibility(View.VISIBLE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg:
			// 跳转到注册界面
			line_login.setVisibility(View.GONE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_pass:
			// 跳转到修改密码界面
			line_login.setVisibility(View.GONE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_get_sqlite:
			// 跳转到查询数据库界面
			line_login.setVisibility(View.GONE);
			line_get_sqlite.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		// 登录界面功能
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (pass.equals("")) {// 密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (ip.equals("")) {// IP地址为空
				DiyToast.showToast(LoginActivity.this, "请输入IP地址");
			} else if (!cursor.moveToNext()) {// 匹配数据库内用户名密码
				DiyToast.showToast(LoginActivity.this, "用户名或密码输入错误");
			} else {
				// 登录
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					// 检测复选框状态插入本地存储
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
		// 注册功能
		case R.id.btn_reg_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.equals("")) {// 用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (epass.equals("")) {// 密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (repass.equals("")) {// 确认密码
				DiyToast.showToast(LoginActivity.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor2 = db.rawQuery(
							"select * from user where username =?",
							new String[] { euser });// 新建数据库指针
					if (cursor2.moveToNext()) {
						DiyToast.showToast(this, "用户名已存在");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						DiyToast.showToast(this, "注册成功");
						line_login.setVisibility(View.VISIBLE);
						line_get_sqlite.setVisibility(View.GONE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}
				} else {
					DiyToast.showToast(this, "两次密码不一致");
				}
			}
			break;
		// 修改密码
		case R.id.btn_updata_con:
			updata_newpass = et_updata_newpass.getText().toString();// 新密码
			updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
			updata_user = et_updata_user.getText().toString();// 用户名
			if (updata_user.equals("")) {// 如果用户名为空
				DiyToast.showToast(this, "用户名不能为空");
			} else if (updata_oldpass.equals("")) {// 如果旧密码为空
				DiyToast.showToast(this, "旧密码不能为空");
			} else if (updata_newpass.equals("")) {// 如果新密码为空
				DiyToast.showToast(this, "新密码不能为空");
			} else {
				Cursor cur_getupdata = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// 新建数据库指针
				cur_getupdata.moveToFirst();
				String getpass;
				getpass = cur_getupdata.getString(cur_getupdata
						.getColumnIndex("passward"));
				if (getpass.equals(updata_oldpass)) {
					if (updata_newpass.equals(updata_oldpass)) {
						DiyToast.showToast(this, "新旧密码不能一致");
					} else {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// 更新数据库
						DiyToast.showToast(this, "密码修改成功");
						line_login.setVisibility(View.VISIBLE);
						line_get_sqlite.setVisibility(View.GONE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}
				} else {
					DiyToast.showToast(this, "旧密码输入错误");
				}
			}

			break;
		case R.id.btn_delete_sqlite:
			// 删除数据库
			db.execSQL("delete from user");
			break;
		case R.id.btn_updata_sqlite:
			// 刷新数据库
			lv_1.setAdapter(null);
			Cursor cursor4 = db.rawQuery("select * from user", null);
			if (cursor4.getCount() != 0) {
				cursor4.moveToFirst();
				SimpleCursorAdapter adapter = new SimpleCursorAdapter(
						LoginActivity.this, R.layout.activity_text, cursor4,
						new String[] { "username", "passward" }, new int[] {
								R.id.tv_user, R.id.tv_pass });
				lv_1.setAdapter(adapter);
			} else {
				Toast.makeText(LoginActivity.this, "数据库中没有记录！",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
}
