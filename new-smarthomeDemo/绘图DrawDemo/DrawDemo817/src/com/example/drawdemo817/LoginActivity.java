package com.example.drawdemo817;

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
import android.widget.Toast;

/*
 * @文件名：LoginActivity.java
 * @描述：对用户显示登录\注册界面UI,完成登陆、注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-17
 */
public class LoginActivity extends Activity implements OnClickListener {
	private LinearLayout line_reg, line_login;// 注册布局
	private Button btn_login, btn_con, btn_cls, btn_reg;// 按钮
	private EditText et_user, et_pass, et_repass, et_ip, et_euser, et_epass;// 文本框
	private String user, pass, ip, euser, epass, repass;
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private SharedPreferences sharedPreferences;// SharedPreferences存储
	public static String ip_number;// IP地址
	private CheckBox cb_autologin, cb_rember;// 自动登录、记住密码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// 自动登录、记住密码功能进程
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {// 如果sharedPreferences存在
			if (sharedPreferences.getBoolean("rember", false) == true) {// 如果记住密码被激活
				cb_rember.setChecked(true);// 设置勾选
				et_ip.setText(sharedPreferences.getString("ip", null));// 从sharedPreferences中取出数值
				et_pass.setText(sharedPreferences.getString("passward", null));
				et_user.setText(sharedPreferences.getString("username", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// 如果自动登录被激活
				cb_autologin.setChecked(true); // 设置勾选
				// 新建线程
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
			et_ip.setText("");
			et_pass.setText("");
			et_user.setText("");
		}
	}

	/*
	 * @方法名：onClick(View v)
	 * 
	 * @功 能：监听按钮的点击事件
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 登录
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 如果用户名为空
				Toast.makeText(LoginActivity.this, "用户名不能为空",
						Toast.LENGTH_SHORT).show();
			} else if (pass.equals("")) {// 如果密码为空
				Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {// 如果IP地址为空
				Toast.makeText(LoginActivity.this, "IP地址", Toast.LENGTH_SHORT)
						.show();
			} else if (!cursor.moveToNext()) {// 如果数据库内无法匹配到数值
				Toast.makeText(LoginActivity.this, "用户名或密码输入错误！",
						Toast.LENGTH_SHORT).show();
			} else {// 条件全部满足
				// 跳转到下一界面
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				ip_number = ip;
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {// 自动登录、记住密码
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {// 自动登录
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {// 记住密码
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else {// 全部未勾选
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				}
			}
			break;
		// 注册
		case R.id.btn_reg:
			line_reg.setVisibility(View.VISIBLE);
			line_login.setVisibility(View.GONE);
			break;
		// 确定
		case R.id.btn_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.equals("")) {// 如果用户名为空
				Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {// 如果密码为空
				Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {// 如果确认密码为空
				Toast.makeText(LoginActivity.this, "请输入确认密码",
						Toast.LENGTH_SHORT).show();
			} else {
				if (epass.equals(repass)) {
					// 检查两次密码输入是否一致
					Cursor cursor1 = db.rawQuery(
							"select * from user where username = ? ",
							new String[] { euser });// 新建数据库指针
					if (cursor1.moveToNext()) {
						// 匹配数据库是否已存在用户名
						Toast.makeText(LoginActivity.this, "用户名已存在！",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						Toast.makeText(LoginActivity.this, "注册成功",
								Toast.LENGTH_SHORT).show();
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
					}
				} else {
					Toast.makeText(LoginActivity.this, "两次密码输入不一致!",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		// 关闭
		case R.id.btn_cls:
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

}
