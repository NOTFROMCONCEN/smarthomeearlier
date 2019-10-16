package com.example.drawdemo825;

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
 * @描述：对用户完成UI显示、登录注册修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-25
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 布局
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面
	private LinearLayout line_updata_pass;// 修改密码界面

	// 登录界面
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata;// 修改密码按钮
	private EditText et_user, et_pass, et_ip;// 文本框
	private CheckBox cb_rember, cb_autologin;// 记住密码自动登录
	private String user, pass, ip;

	// 注册界面
	private Button btn_con, btn_cls;// 确定、关闭按钮
	private EditText et_euser, et_epass, et_repass;// 文本框
	private String euser, epass, repass;

	// 修改密码
	private Button btn_updata_con, btn_updata_cls;// 秀修改密码确定、关闭按钮
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// 文本框
	private String updata_user, updata_newpass, updata_oldpass;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// 存储
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
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata = (Button) findViewById(R.id.btn_updata);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
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
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		// 设置界面显示隐藏
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);
		// 自动登录记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
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
						// 跳转到下一界面
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
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
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午8:28:49
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			Cursor cur_reg = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });
			if (euser.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (epass.equals("")) {
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (repass.equals("")) {
				DiyToast.showToast(LoginActivity.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					if (cur_reg.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "用户名已存在s");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						DiyToast.showToast(LoginActivity.this, "注册完成");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
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
			ip = et_ip.getText().toString();// Ip地址
			Cursor cur_login = db.rawQuery(
					"select * from user where username  =? and passward = ?",
					new String[] { user, pass });
			if (user.equals("")) {// 如果用户名为空
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (pass.equals("")) {// 如果密码为空
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (ip.equals("")) {// 如果IP地址为空
				DiyToast.showToast(LoginActivity.this, "请输入IP地址");
			} else if (!cur_login.moveToNext()) {
				DiyToast.showToast(LoginActivity.this, "用户名或密码输入错误");
			} else {
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putBoolean("autologin", false)
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
		case R.id.btn_updata:
			// 修改密码
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定

			break;
		default:
			break;
		}
	}
}