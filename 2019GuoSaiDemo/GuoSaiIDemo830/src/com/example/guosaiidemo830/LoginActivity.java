package com.example.guosaiidemo830;

import java.util.logging.SocketHandler;

import com.example.guosaiidemo830.R.layout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录注册修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-30
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 界面
	private LinearLayout line_login;// 登录
	private LinearLayout line_reg;// 注册
	private LinearLayout line_updata_pass;// 修改密码
	// 登录界面
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private Button btn_updata_pass;// 修改密码
	private EditText et_user, et_pass, et_ip;// 文本框
	private Button btn_exit;// 退出
	private String user, pass, ip;
	public static String ip_number;// IP地址

	// 注册界面
	private Button btn_con;// 确定
	private Button btn_cls;// 关闭
	private EditText et_euser, et_epass, et_repass;// 文本框
	private String euser, epass, repass;

	// 修改密码界面
	private Button btn_updata_con;// 确定
	private Button btn_updata_cls;// 关闭
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// 文本框
	private String updata_user, updata_newpass, updata_oldpass;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// 存储
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("智能家居");
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_new_pass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_old_pass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 界面绑定
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		// 记住密码功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// 按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// 设置密码显示字符为*
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：点击事件
	 * 
	 * @时 间：上午8:47:47
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
			repass = et_repass.getText().toString();// 确定密码
			if (euser.equals("")) {// 如果用户名为空
				DiyToast.showTaost(LoginActivity.this, "请输入用户名");
			} else if (epass.equals("")) {// 如果密码为空
				DiyToast.showTaost(LoginActivity.this, "请输入密码");
			} else if (repass.equals("")) {// 确认密码为空
				DiyToast.showTaost(LoginActivity.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor.moveToNext()) {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("注册失败")
								.setMessage("用户名已经存在，请重新注册")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("注册成功")
								.setMessage("用户注册成功")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												line_login
														.setVisibility(View.VISIBLE);
												line_reg.setVisibility(View.GONE);
												line_updata_pass
														.setVisibility(View.GONE);
											}
										}).show();
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("注册失败")
							.setMessage("重复密码不一致")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				}
			}
			break;
		case R.id.btn_exit:
			// 登录退出
			System.exit(0);
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward  =?",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 用户名为空
				DiyToast.showTaost(LoginActivity.this, "请输入用户名");
			} else if (pass.equals("")) {// 密码为空
				DiyToast.showTaost(LoginActivity.this, "请输入密码");
			} else if (ip.equals("")) {// Ip地址为空
				DiyToast.showTaost(LoginActivity.this, "请输入IP地址");
			} else if (!cursor.moveToNext()) {
				DiyToast.showTaost(LoginActivity.this, "用户名或密码输入错误");
			} else {
				ip_number = ip;
				sharedPreferences.edit().putBoolean("rember", true)
						.putString("user", user).putString("pass", pass)
						.putString("ip", ip).commit();
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			updata_newpass = et_updata_newpass.getText().toString();// 新密码
			updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
			updata_user = et_updata_user.getText().toString();// 用户名
			if (updata_user.equals("")) {// 用户名为空
				DiyToast.showTaost(LoginActivity.this, "请输入用户名");
			} else if (updata_oldpass.equals("")) {// 如果旧密码为空
				DiyToast.showTaost(LoginActivity.this, "请输入旧密码");
			} else if (updata_newpass.equals("")) {// 如果新密码为空
				DiyToast.showTaost(LoginActivity.this, "请输入新密码");
			} else {
				Cursor cur_updata_user = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// 新建数据库指针1，对比用户名
				if (cur_updata_user.moveToNext()) {
					Cursor cur_updata_pass = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// 新建数据库指针2，寻找密码
					cur_updata_pass.moveToFirst();
					String sqlite_getpass = cur_updata_pass
							.getString(cur_updata_pass
									.getColumnIndex("passward"));
					if (updata_oldpass.equals(sqlite_getpass)) {
						if (updata_newpass.equals(updata_oldpass)) {
							DiyToast.showTaost(LoginActivity.this, "新旧密码不能一致！");
						} else {
							db.execSQL(
									"update user set passward = ? where username  =?",
									new String[] { updata_newpass, updata_user });// 更新数据库
							DiyToast.showTaost(LoginActivity.this, "修改完成");
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("修改成功")
									.setMessage("密码修改成功")
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													line_login
															.setVisibility(View.VISIBLE);
													line_reg.setVisibility(View.GONE);
													line_updata_pass
															.setVisibility(View.GONE);
												}
											}).show();
						}
					} else {
						DiyToast.showTaost(LoginActivity.this, "旧密码输入错误");
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("修改失败")
								.setMessage("旧密码错误")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated
												// method stub
											}
										}).show();
					}
				} else {
					DiyToast.showTaost(LoginActivity.this, "用户名不存在");
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("修改失败")
							.setMessage("修改失败：用户名不存在")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated
											// method stub
										}
									}).show();
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
