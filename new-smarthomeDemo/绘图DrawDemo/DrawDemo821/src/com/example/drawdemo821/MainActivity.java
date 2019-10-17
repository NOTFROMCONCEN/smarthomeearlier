package com.example.drawdemo821;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/*
 * @文件名：MainActivity.java
 * @描述：对用户完成登录、注册、修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-20
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private Button btn_updata_pass;// 修改密码
	private Button btn_con;// 注册确定
	private Button btn_cls;// 注册关闭
	private Button btn_updata_con;// 修改密码确定
	private Button btn_updata_cls;// 修改密码关闭
	private EditText et_user, et_pass, et_ip;// 登录界面
	private EditText et_euser, et_epass, et_repass;// 注册界面
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// 修改密码界面
	private String user, pass, ip, euser, epass, repass, updata_oldpass,
			updata_newpass, updata_user;// String数值
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private LinearLayout line_updata_pass;// 修改密码布局
	private LinearLayout line_reg;// 注册布局
	private LinearLayout line_login;// 登录修改密码布局
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	private SharedPreferences sharedPreferences;// sharedPreferences存储
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
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
		// 设置局部隐藏显示顺序
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_updata_pass.setVisibility(View.INVISIBLE);
		// 自动登录记住密码进程
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			// 如果存储不为空
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				// 如果记住密码被激活
				et_ip.setText(sharedPreferences.getString("ip", null));// IP地址
				et_user.setText(sharedPreferences.getString("username", null));// 用户名
				et_pass.setText(sharedPreferences.getString("passward", null));// 密码
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				// 如果记住密码被激活
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
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}).start();
			}
		} else {
			// 如果存储为空，设置文本框为空
			et_ip.setText("");
			et_pass.setText("");
			et_user.setText("");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：onClick(View v)
	 * 
	 * @功 能：监听按钮点击事件并响应
	 * 
	 * @参 数：View v
	 * 
	 * @时 间：上午7:58:06
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确定密码
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// 新建数据库指针
			if (euser.equals("")) {
				// 如果用户名为空
				Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {
				// 如果密码为空
				Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {
				// 如果确认密码为空
				Toast.makeText(MainActivity.this, "请输入确认密码", Toast.LENGTH_SHORT)
						.show();
			} else {
				if (epass.equals(repass)) {
					// 如果密码和确认密码一致
					if (cursor.moveToNext()) {
						// 如果数据库内含有用户名
						Toast.makeText(MainActivity.this, "用户名已存在！",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						// 用户名和密码插入数据库
						Toast.makeText(MainActivity.this, "注册成功",
								Toast.LENGTH_SHORT).show();
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
						line_updata_pass.setVisibility(View.INVISIBLE);
					}
				} else {
					// 如果密码和确认密码不一致
					Toast.makeText(MainActivity.this, "两次密码输入不一致",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {
				// 用户名为空
				Toast.makeText(MainActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.equals("")) {
				// 密码为空
				Toast.makeText(MainActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {
				// IP地址为空
				Toast.makeText(MainActivity.this, "请输入IP地址", Toast.LENGTH_SHORT)
						.show();
			} else if (!cursor2.moveToNext()) {
				// 数据库无法匹配
				Toast.makeText(MainActivity.this, "用户名和密码输入错误",
						Toast.LENGTH_SHORT).show();
			} else {
				// 登录跳转
				ip_number = ip;
				Intent intent = new Intent(MainActivity.this, BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					// 自动登录、记住密码均被勾选
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {
					// 自动登录被勾选
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {
					// 记住密码被勾选
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", false)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putBoolean("autologin", false)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				}
				Toast.makeText(MainActivity.this, "登陆成功", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		case R.id.btn_reg:
			// 跳转注册
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			updata_newpass = et_updata_newpass.getText().toString();// 新密码
			updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
			updata_user = et_updata_user.getText().toString();// 用户名
			if (updata_user.equals("")) {
				// 如果用户名为空
				Toast.makeText(MainActivity.this, "用户名不能为空", Toast.LENGTH_SHORT)
						.show();
			} else if (updata_oldpass.equals("")) {
				// 如果旧密码为空
				Toast.makeText(MainActivity.this, "旧密码不能为空", Toast.LENGTH_SHORT)
						.show();
			} else if (updata_newpass.equals("")) {
				// 如果新密码为空
				Toast.makeText(MainActivity.this, "新密码不能为空", Toast.LENGTH_SHORT)
						.show();
			} else {
				Cursor cursor3 = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });
				cursor3.moveToFirst();
				String getusername = cursor3.getString(cursor3
						.getColumnIndex("username"));
				String getpassward = cursor3.getString(cursor3
						.getColumnIndex("passward"));
				if (updata_user.equals(getusername)) {
					if (getpassward.equals(updata_oldpass)) {
						if (!updata_newpass.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// 更新数据库
							Toast.makeText(MainActivity.this, "密码修改成功",
									Toast.LENGTH_SHORT).show();
							line_login.setVisibility(View.VISIBLE);
							line_reg.setVisibility(View.INVISIBLE);
							line_updata_pass.setVisibility(View.INVISIBLE);
						} else {
							Toast.makeText(MainActivity.this, "新旧密码不能一致！",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "旧密码输入错误",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "用户名不存在",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 跳转修改密码
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
