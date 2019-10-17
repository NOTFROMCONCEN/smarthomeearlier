package com.example.guosaiidemo914;

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
 * @描述：完成登录、注册、修改密码等功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-14
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 布局
	private LinearLayout line_login;
	private LinearLayout line_reg;
	private LinearLayout line_updata_pass;
	// 登录控件
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_exit;// 退出按钮
	private Button btn_updtaa_pass;// 修改密码按钮
	private String user, pass, ip;// String 用户名、密码、IP
	private EditText et_ip, et_user, et_pass;// 文本框
	public static String ip_number;// IP地址

	// 注册控件
	private Button btn_con;// 注册确定
	private Button btn_cls;// 注册退出
	private EditText et_reg_user, et_reg_pass, et_reg_repass;// 文本框
	private String euser, epass, repass;// String 用户名、密码、确认密码

	// 修改密码控件
	private Button btn_updata_con;// 注册确定
	private Button btn_updata_cls;// 注册退出
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// 文本框
	private String updata_newpass, updata_oldpass, updata_user;// String
																// 用户名、新密码，旧密码

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();

	}

	/*
	 * @方法名：initView
	 * 
	 * @功能：绑定控件
	 * 
	 * @时 间：上午8:34:21
	 */
	private void initView() {
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updtaa_pass = (Button) findViewById(R.id.btn_updata_pass);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_user = (EditText) findViewById(R.id.et_user);

		// 设置点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updtaa_pass.setOnClickListener(this);
		// 设置布局参数
		line_login.setVisibility(View.VISIBLE);// 显示登录
		line_reg.setVisibility(View.INVISIBLE);// 隐藏注册
		line_updata_pass.setVisibility(View.INVISIBLE);// 隐藏修改密码
		// 记住密码功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
		}
		// 设置密码框
		et_pass.setTransformationMethod(new TextChanger());
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			line_login.setVisibility(View.VISIBLE);// 显示登录
			line_reg.setVisibility(View.INVISIBLE);// 隐藏注册
			line_updata_pass.setVisibility(View.INVISIBLE);// 隐藏修改密码
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_reg_user.getText().toString();// 用户名
			epass = et_reg_pass.getText().toString();// 密码
			repass = et_reg_repass.getText().toString();// 确认密码
			if (euser.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (epass.isEmpty()) { // 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (repass.isEmpty()) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "请输入确认密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("注册失败")
							.setMessage("用户名已存在，请重新注册")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				} else {
					if (epass.equals(repass)) {// 密码、确认密码一致
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
														.setVisibility(View.VISIBLE);// 显示登录
												line_reg.setVisibility(View.INVISIBLE);// 隐藏注册
												line_updata_pass
														.setVisibility(View.INVISIBLE);// 隐藏修改密码
											}
										}).show();
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
												// TODO Auto-generated method
												// stub

											}
										}).show();
					}
				}
			}
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "用户名不能为空");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "密码不能为空");
			} else if (ip.isEmpty()) {// IP为空
				DiyToast.showToast(getApplicationContext(), "IP地址不能为空");
			} else {
				Cursor condition = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (!condition.moveToNext()) {// 如果游标未移动（无匹配项）
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("登录失败")
							.setMessage("用户名或密码错误")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				} else {
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
					ip_number = ip;
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
				}
			}
			break;
		case R.id.btn_exit:
			// 退出
			System.exit(0);
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.INVISIBLE);// 隐藏登录
			line_reg.setVisibility(View.VISIBLE);// 显示注册
			line_updata_pass.setVisibility(View.INVISIBLE);// 隐藏修改密码
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			updata_newpass = et_updata_newpass.getText().toString();// 新密码
			updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
			updata_user = et_updata_user.getText().toString();// 用户名
			if (updata_user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (updata_oldpass.isEmpty()) {// 旧密码为空
				DiyToast.showToast(getApplicationContext(), "请输入旧密码");
			} else if (updata_newpass.isEmpty()) {// 新密码为空
				DiyToast.showToast(getApplicationContext(), "请输入新密码");
			} else {
				Cursor curso = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// 新建数据库游标
				if (curso.moveToNext()) {// 数据库有匹配项
					if (curso.getString(curso.getColumnIndex("passward"))
							.toString().equals(updata_oldpass)) {
						if (updata_newpass.equals(updata_oldpass)) {
							DiyToast.showToast(getApplicationContext(),
									"新旧密码不能一样");
						} else {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// 更新
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
													// 修改密码关闭
													line_login
															.setVisibility(View.VISIBLE);// 显示登录
													line_reg.setVisibility(View.INVISIBLE);// 隐藏注册
													line_updata_pass
															.setVisibility(View.INVISIBLE);// 隐藏修改密码
												}
											}).show();
						}
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("修改失败")
								.setMessage("旧密码输入错误")
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
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("修改失败")
							.setMessage("修改失败：输入的用户不存在")
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
		case R.id.btn_updata_pass:
			// 修改密码
			line_login.setVisibility(View.INVISIBLE);// 隐藏登录
			line_reg.setVisibility(View.INVISIBLE);// 隐藏注册
			line_updata_pass.setVisibility(View.VISIBLE);// 显示修改密码
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			line_login.setVisibility(View.VISIBLE);// 显示登录
			line_reg.setVisibility(View.INVISIBLE);// 隐藏注册
			line_updata_pass.setVisibility(View.INVISIBLE);// 隐藏修改密码
			break;
		default:
			break;
		}
	}
}
