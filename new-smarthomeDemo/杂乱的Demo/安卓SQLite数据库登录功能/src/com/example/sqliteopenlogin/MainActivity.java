package com.example.sqliteopenlogin;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Shader;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.TextUtils.TruncateAt;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * @文件名：MainActivity.java
 * @描述：完成登录、注册、修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-7
 */
public class MainActivity extends Activity implements OnClickListener {
	// 正则表达式
	private Matcher matcher;
	private Pattern pattern;
	private boolean isTrue = false;
	// 提示
	private TextView tv_tips;
	// 登录界面
	private EditText et_user, et_pass, et_ip;
	private String user, pass, ip;
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private Button btn_updata_pass;// 修改密码
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	// 注册界面
	private Button btn_con, btn_cls;
	private String euser, epass, repass;
	private EditText et_euser, et_epass, et_repass;
	// 修改密码界面
	private Button btn_updata_con;
	private Button btn_updata_cls;
	private EditText et_updata_user, et_updata_newpass, et_updata_oldpass;
	private String updata_user, updata_oldpass, updata_newpass;
	// 界面布局
	private LinearLayout line_login;// 登录
	private LinearLayout line_reg;// 登录
	private LinearLayout line_updata_pass;// 登录
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_tips = (TextView) findViewById(R.id.tv_tips);
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
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 设置布局参数
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_updata_pass.setVisibility(View.INVISIBLE);
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
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
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							Intent intent = new Intent(MainActivity.this,
									LoginOJBKActivity.class);
							startActivity(intent);
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

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
			repass = et_repass.getText().toString();// 确认密码
			if (euser.equals("")) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (epass.equals("")) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (repass.equals("")) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						oncheck();
						if (isTrue) {
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"密码格式为：数字+字母");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });// 插入数据库
								DiyToast.showToast(getApplicationContext(),
										"注册成功");
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.INVISIBLE);
								line_updata_pass.setVisibility(View.INVISIBLE);
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码不能小于6位");
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "两次密码输入不一致");
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
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.equals("")) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (ip.equals("")) {// IP地址为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else if (!cursor.moveToNext()) {// 数据库无匹配项
				DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
			} else {
				// 跳转
				Intent intent = new Intent(MainActivity.this,
						LoginOJBKActivity.class);
				startActivity(intent);
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
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// 新建数据库指针
			if (updata_user.equals("")) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (updata_oldpass.equals("")) {// 旧密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (updata_newpass.equals("")) {// 新密码为空
				DiyToast.showToast(getApplicationContext(), "请输入新密码");
			} else {
				if (cursor2.moveToNext()) {
					if (!updata_newpass.equals(updata_oldpass)) {
						String get_pass;
						get_pass = cursor2.getString(
								cursor2.getColumnIndex("passward")).toString();
						if (!updata_oldpass.equals(get_pass)) {
							DiyToast.showToast(getApplicationContext(),
									"旧密码输入错误");
						} else {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });
							DiyToast.showToast(getApplicationContext(),
									"密码修改成功");
							line_login.setVisibility(View.VISIBLE);
							line_reg.setVisibility(View.INVISIBLE);
							line_updata_pass.setVisibility(View.INVISIBLE);
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "新旧密码不能一致");
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名不存在");
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	public void oncheck() {
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start <= 4) {
					isTrue = false;
				} else {
					isTrue = true;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}
}
