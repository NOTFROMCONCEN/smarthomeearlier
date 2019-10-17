package com.example.shengsaiedemo20181009;

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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.example.shengsaiedemo20181009.fragment.BarActivity;
import com.example.shengsaiedemo20181009.mysql.MyDataBaseHelper;
import com.example.shengsaiedemo20181009.toast.DiyToast;

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-9
 */
public class LoginActivity extends Activity {
	private Button btn_login;// 登录按钮
	private EditText et_user, et_ip, et_pass;// 文本框
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	private String user, ip, pass;
	public static String IP_NUMBER, login_op;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences磁存储
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		// 记住密码自动登录
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// 记住密码
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// 自动登录
				cb_autologin.setChecked(true);
				DiyToast.showToast(getApplicationContext(), "4秒后自动登录");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(4000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							if (et_user.getText().toString().equals("bizideal")) {
								login_op = "管理员";
							} else {
								login_op = "用户";
							}
							IP_NUMBER = sharedPreferences.getString("ip", null);
							startActivity(new Intent(getApplicationContext(),
									BarActivity.class));
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					}
				}).start();
			}
		}
		// 设置复选框联动效果
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
				} else {
					cb_autologin.setChecked(false);
				}
			}
		});

		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// IP
				if (ip.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建游标
					if (cursor.moveToNext()) {// 移动
						// 登陆成功
						if (et_user.getText().toString().equals("bizideal")) {
							login_op = "管理员";
						} else {
							login_op = "用户";
						}
						IP_NUMBER = ip;
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
						// 记住密码、自动登录
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}
}
