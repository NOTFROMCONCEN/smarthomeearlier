package com.example.guosaiidemo927;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
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
 * @描述：登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-27
 */
public class LoginActivity extends Activity implements OnClickListener {
	// IP地址
	public static String ip_number;
	// sharedPreferences存储
	SharedPreferences sharedPreferences;
	// 界面
	private LinearLayout line_login;// 登录
	private LinearLayout line_reg;// 注册
	private LinearLayout line_updata_pass;// 修改密码

	// 按钮“们”
	private Button btn_login;// 登录按钮
	private Button btn_updata_pass;// 修改密码按钮
	private Button btn_exit;// 退出按钮
	private Button btn_reg;// 注册按钮
	private Button btn_reg_con;// 注册确定
	private Button btn_reg_cls;// 注册关闭
	private Button btn_updata_con;// 修改密码确定
	private Button btn_updata_cls;// 修改密码关闭

	// 登录界面
	private EditText et_user;// 用户名
	private EditText et_ip;// IP地址
	private EditText et_pass;// 密码
	private String user, ip, pass;

	// 注册界面
	private EditText et_reg_user;// 用户名
	private EditText et_reg_pass;// 密码
	private EditText et_reg_repass;// 重复密码
	private String reg_user, reg_pass, reg_repass;

	// 修改密码界面
	private EditText et_updata_user;// 用户名
	private EditText et_updata_newpass;// 新密码
	private EditText et_updata_oldpass;// 旧密码
	private String updata_user, updata_oldpass, updata_newpass;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// 绑定控件
		rember_pass();// 记住密码
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 退出按钮点击事件
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);// 退出
			}
		});
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.GONE);
				line_reg.setVisibility(View.VISIBLE);
				line_updata_pass.setVisibility(View.GONE);
			}
		});
		// 修改密码按钮点击事件
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.GONE);
				line_reg.setVisibility(View.GONE);
				line_updata_pass.setVisibility(View.VISIBLE);
			}
		});
		// 注册关闭按钮点击事件
		btn_reg_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.GONE);
				line_updata_pass.setVisibility(View.GONE);
			}
		});
		// 修改密码关闭按钮点击事件
		btn_updata_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.GONE);
				line_updata_pass.setVisibility(View.GONE);
			}
		});
	}

	/*
	 * @方法名：initView()
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：下午3:06:40
	 */
	private void initView() {
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// 登录按钮
		btn_login.setOnClickListener(this);
		// 注册确定按钮
		btn_reg_con.setOnClickListener(this);
		// 修改密码确定按钮
		btn_updata_con.setOnClickListener(this);
	}

	/*
	 * @方法名：rember_pass
	 * 
	 * @功 能：记住密码
	 * 
	 * @时 间：下午3:09:49
	 */
	private void rember_pass() {
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stubs
		switch (v.getId()) {
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			ip = et_ip.getText().toString();// IP地址
			pass = et_pass.getText().toString();// 密码
			if (ip.isEmpty()) {// 如果IP为空
				DiyToast.showToast(getApplicationContext(), "IP地址不能为空");
			} else if (user.isEmpty()) {// 如果用户名为空
				DiyToast.showToast(getApplicationContext(), "用户名不能为空");
			} else if (pass.isEmpty()) {// 如果密码为空
				DiyToast.showToast(getApplicationContext(), "密码不能为空");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username =? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					ip_number = ip;
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("登录失败")
							.setMessage("用户名或密码输入错误")
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
		case R.id.btn_con:
			// 注册确定
			reg_pass = et_reg_pass.getText().toString();// 密码
			reg_repass = et_reg_repass.getText().toString();// 确认密码
			reg_user = et_reg_user.getText().toString();// 用户名
			if (reg_user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "用户名不能为空");
			} else if (reg_pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "密码不能为空");
			} else if (reg_repass.isEmpty()) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { reg_user });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("注册失败")
							.setMessage("用户名已存在")
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
					if (reg_repass.equals(reg_pass)) {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { reg_user, reg_pass });
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("注册成功")
								.setMessage("用户注册成功")
								.setPositiveButton("确定",
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
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("注册失败")
								.setMessage("重复密码不一致")
								.setPositiveButton("确定",
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
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// 新建数据库游标
				if (cursor.moveToNext()) {
					if (updata_newpass.equals(updata_oldpass)) {// 新旧密码一致
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("修改失败")
								.setMessage("新旧密码不能一致")
								.setPositiveButton("确定",
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
						if (cursor.getString(cursor.getColumnIndex("passward"))
								.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("修改成功")
									.setMessage("密码修改成功")
									.setPositiveButton(
											"确定",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub
													line_login
															.setVisibility(View.VISIBLE);
													line_reg.setVisibility(View.GONE);
													line_updata_pass
															.setVisibility(View.GONE);
												}
											}).show();
						} else {
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("修改失败")
									.setMessage("旧密码错误")
									.setPositiveButton(
											"确定",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub

												}
											}).show();
						}
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("修改失败")
							.setMessage("修改失败：用户名不存在")
							.setPositiveButton("确定",
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

			break;

		default:
			break;
		}
	}
}
