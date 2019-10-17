package com.example.remberandautologin;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
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

/*
 * @文件名：MainActivity.java
 * @描述：完成登录注册记住密码自动登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-1
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_reg_con;// 注册确定按钮
	private Button btn_reg_cls;// 注册关闭按钮
	private EditText et_user, et_pass, et_reg_pass, et_reg_user;// 文本框
	private String user, pass, reg_user, reg_pass;// String数值
	private LinearLayout line_reg;// 注册界面
	private LinearLayout line_login;// 登录界面
	private SharedPreferences sharedPreferences;// sharedPreferences存储
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private CheckBox cb_rember, cb_autologin;// 自动登录记住密码复选框
	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("登录");
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 设置按钮点击事件
		btn_login.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		// 自动登录记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			// 如果记住密码boolean值为true
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			// 如果自动登录boolean值为true
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				DiyToast.showToast(MainActivity.this, "即将自动登录");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);// 设置延迟
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {// 如果延迟时间内复选框（自动登录）没有被取消勾选，继续自动登录
							Intent intent = new Intent(MainActivity.this,
									OkActivity.class);
							startActivity(intent);
						} else {// 否则更改自动登录boolean值为false
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "0")
									.putString("pass", "0").commit();// 插入sharedPreferences存储
						}
					}
				}).start();
			}
		} else {

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应按钮点击
	 * 
	 * @时 间：下午6:42:41
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// 登录按钮
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 如果用户名为空
				DiyToast.showToast(MainActivity.this, "请输入用户名");
			} else if (pass.equals("")) {// 如果密码为空
				DiyToast.showToast(MainActivity.this, "请输入密码");
			} else if (!cursor.moveToNext()) {// 如果数据库无匹配项
				DiyToast.showToast(MainActivity.this, "用户名或密码输入错误");
			} else {
				DiyToast.showToast(MainActivity.this, "登陆成功");
				// 跳转
				Intent intent = new Intent(MainActivity.this, OkActivity.class);
				startActivity(intent);
				// 判断复选框选中状态
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).commit();// 插入sharedPreferences存储
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).commit();// 插入sharedPreferences存储
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).commit();// 插入sharedPreferences存储
				} else {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.commit();// 插入sharedPreferences存储
				}
			}
			break;
		case R.id.btn_reg:
			// 注册按钮
			setTitle("注册");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_reg_cls:
			// 注册关闭按钮
			setTitle("登录");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_con:
			// 注册打开按钮
			reg_pass = et_reg_pass.getText().toString();// 用户名
			reg_user = et_reg_user.getText().toString();// 密码
			if (reg_user.equals("")) {// 如果用户名为空
				DiyToast.showToast(MainActivity.this, "请输入用户名");
			} else if (reg_pass.equals("")) {// 如果密码为空
				DiyToast.showToast(MainActivity.this, "请输入密码");
			} else {
				Cursor cursor1 = db.rawQuery(
						"select * from user where username = ?",
						new String[] { reg_user });// 新建数据库指针
				if (cursor1.moveToNext()) {
					DiyToast.showToast(MainActivity.this, "用户名已存在");
				} else {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { reg_user, reg_pass });// 插入数据库
					DiyToast.showToast(MainActivity.this, "注册成功");
					line_login.setVisibility(View.VISIBLE);
					line_reg.setVisibility(View.GONE);
				}
			}
			break;
		default:
			break;
		}
	}
}
