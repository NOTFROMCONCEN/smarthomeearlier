package com.example.drawdemo816;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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
 * @描述：对用户完成登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-16
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login, btn_reg, btn_con, btn_cls;// 登录、注册、确定、返回按钮
	private EditText et_user, et_pass, et_ip;// 登录界面文本框
	private EditText et_euser, et_epass, et_repass;// 注册界面文本框
	private String user, pass, ip, euser, epass, repass;
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private LinearLayout line_login;// 登录布局
	private LinearLayout line_reg;// 注册布局
	private CheckBox cb_autologin;// 自动登录复选框
	private CheckBox cb_rember;// 记住密码复选框
	private SharedPreferences sharedPreferences;// sharedPreferences本地存储

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 绑定复选框
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 绑定布局
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// 绑定按钮
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		// 绑定文本框
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// 自动登录记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {// 如果本地sharedPreferences存在并且有内容
			if (sharedPreferences.getBoolean("rember", false) == true) {
				// 获取“rmeber”的boolean值，判断状态
				cb_rember.setChecked(true);
				String auto_username = sharedPreferences.getString("username",
						null);
				String auto_passward = sharedPreferences.getString("passward",
						null);
				String auto_ip = sharedPreferences.getString("ip", null);
				et_ip.setText(auto_ip);
				et_pass.setText(auto_passward);
				et_user.setText(auto_username);
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				// 获取“autologin” 的boolean值，判断状态
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);// 设置自动登录进程3秒停止时间
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						// 跳转到下一界面
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
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
	 * @功 能：响应按钮点击事件
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
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 如果用户名为空
				Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.equals("")) {// 如果密码为空
				Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {// 如果IP地址为空
				Toast.makeText(LoginActivity.this, "请输入IP地址",
						Toast.LENGTH_SHORT).show();
			} else if (!cursor2.moveToNext()) {// 如果用户名和密码无法匹配数据库
				Toast.makeText(LoginActivity.this, "用户名或密码输入错误",
						Toast.LENGTH_SHORT).show();
			} else {
				// 跳转到下一界面
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				// 判断记住密码和自动登录复选框是否被勾选
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					// 自动登录、记住密码
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {
					// 自动登录
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {
					// 记住密码
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else {
					// 全部未勾选
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
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		// 确定
		case R.id.btn_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// 新建数据库指针比较用户名
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
				if (epass.equals(repass)) {// 如果密码和确认密码一致
					if (cursor.moveToNext()) {
						Toast.makeText(LoginActivity.this, "用户名已存在",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						// 条件满足时插入数据库并回到登录界面
						line_reg.setVisibility(View.GONE);
						line_login.setVisibility(View.VISIBLE);
						Toast.makeText(LoginActivity.this, "注册成功",
								Toast.LENGTH_SHORT).show();
					}
				} else {// 如果两次密码输入不一致给出提示
					Toast.makeText(LoginActivity.this, "两次密码输入不一致",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		// 关闭
		case R.id.btn_cls:
			line_reg.setVisibility(View.GONE);
			line_login.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}