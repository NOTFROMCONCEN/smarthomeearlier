package com.example.drawdemo815;

import java.util.Currency;

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
import android.widget.Toast;

/*
 * @文件名：LoginActivity.java
 * @描述：对用户完成登录以及注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-15
 */
public class LoginActivity extends Activity implements OnClickListener {
	/**
	 * 定义控件
	 */
	private EditText et_user, et_pass, et_ip;// 文本框-用户名、ip地址、密码
	private Button btn_login, btn_reg;// 按钮-登录、注册
	private CheckBox cb_autologin, cb_rember;// 复选框-自动登录、记住密码
	private String user, pass, ip;// String数值
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private SharedPreferences sharedPreferences;// sharedPreferences存储
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		/**
		 * 绑定控件
		 */
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);// 数据库
		db = dbHelper.getReadableDatabase();// 数据库
		// 登录、注册按钮的点击事件
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// 自动登录、记住密码事件
		sharedPreferences = getSharedPreferences("rember_autologin",
				MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				// 判断boolean是否为true，完成记住密码
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				// 判断boolean是否为true，完成自动登录
				cb_autologin.setChecked(true);
				// 新建进程
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);// 设置3秒延迟
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						ip_number = et_ip.getText().toString();// 设置IP地址
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);// 跳转到下一界面
						startActivity(intent);
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
	 * @功 能：响应点击事件
	 * 
	 * @参 数：View v
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 登录
		case R.id.btn_login:
			user = et_user.getText().toString();// 赋值-用户名
			pass = et_pass.getText().toString();// 赋值-密码
			ip = et_ip.getText().toString();// 赋值-IP地址
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针比较用户名和密码
			if (user.isEmpty()) {
				// 判断用户名是否为空
				Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.isEmpty()) {
				// 判断密码是否为空
				Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.isEmpty()) {
				// 判断IP地址是否为空
				Toast.makeText(LoginActivity.this, "请输入IP地址",
						Toast.LENGTH_SHORT).show();
			} else if (!cursor.moveToNext()) {
				// 判断输入的用户名和密码是否匹配
				Toast.makeText(LoginActivity.this, "用户名或密码输入错误",
						Toast.LENGTH_SHORT).show();
			} else {
				// 判断自动登录和记住密码是否被勾选
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();// 填写sharedPreferences
					ip_number = ip;// 设置IP地址
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// 跳转到下一界面
					startActivity(intent);
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();// 填写sharedPreferences
					ip_number = ip;// 设置IP地址
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// 跳转到下一界面
					startActivity(intent);
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();// 填写sharedPreferences
					ip_number = ip;// 设置IP地址
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// 跳转到下一界面
					startActivity(intent);
				} else {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();// 填写sharedPreferences
					ip_number = ip;// 设置IP地址
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// 跳转到下一界面
					startActivity(intent);
				}
			}
			break;
		// 注册
		case R.id.btn_reg:
			// 跳转到登录界面
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
