package com.example.guosaiidemo818;

import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/*
 * @文件名：LoginActivity.java
 * @描述：对用户完成登录UI显示、登录注册修改密码功能;
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-18
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 登录部分
	private Button btn_login, btn_reg, btn_updata_pass, btn_exit;// 按钮
	private String user, pass, ip;// String数值
	private EditText et_user, et_pass, et_ip;// 文本框
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// 注册部分
	private Button btn_con, btn_cls;// 按钮
	private EditText et_euser, et_epass, et_repass;// 文本框
	private String euser, epass, repass;

	// 修改密码部分
	private Button btn_updata_con, btn_updata_cls;// 按钮
	private String updata_user, new_pass, old_pass;// STring数值
	private EditText et_updata_user, et_new_passward, et_old_passward;// 文本框

	// 布局
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面
	private LinearLayout line_updata_pass;// 修改密码界面

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_new_passward = (EditText) findViewById(R.id.et_new_pass);
		et_old_passward = (EditText) findViewById(R.id.et_old_pass);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		// 设置输入文字样式
		et_pass.setTransformationMethod(new WordReplacement());
		// 设置数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：下午3:21:07
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 关闭注册界面
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// 注册界面确定按钮
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.equals("")) {// 检查用户名文本框是否为空
				Toast.makeText(LoginActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {// 检查密码文本框是否为空
				Toast.makeText(LoginActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {// 检查确认密码文本框是否为空
				Toast.makeText(LoginActivity.this, "请输入确认密码",
						Toast.LENGTH_SHORT).show();
			} else {
				if (epass.equals(repass)) {
					// 密码与确认密码一致
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor.moveToNext()) {
						// 数据库内存在用户名
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("注册失败")
								.setMessage("用户名已存在，请重新注册")
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
												line_reg.setVisibility(View.INVISIBLE);
												line_updata_pass
														.setVisibility(View.INVISIBLE);
											}
										}).show();
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("注册失败")
							.setMessage("两次密码输入不一致")
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
			break;
		case R.id.btn_exit:
			// 退出程序
			System.exit(0);
			break;
		case R.id.btn_login:
			// 登录按钮
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor = db.rawQuery(
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
			} else if (!cursor.moveToNext()) {// 如果数据库无法匹配
				new AlertDialog.Builder(LoginActivity.this)
						.setTitle("登录失败")
						.setMessage("用户名或密码错误")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();
			} else {
				// 跳转到下一界面
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			}

			break;
		case R.id.btn_reg:
			// 进入注册界面
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_cls:
			// 关闭修改密码界面
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_con:
			// 修改密码界面确定按钮
			updata_user = et_updata_user.getText().toString();// 用户名
			new_pass = et_new_passward.getText().toString();// 新密码
			old_pass = et_old_passward.getText().toString();// 旧密码
			String get_username,
			get_passward;// 从数据库取出用户名、密码
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// 新建数据库指针
			cursor2.moveToFirst();
			get_username = cursor2
					.getString(cursor2.getColumnIndex("username"));
			get_passward = cursor2
					.getString(cursor2.getColumnIndex("passward"));
			if (get_username.equals(updata_user)) {
				// 用户名是否匹配
				if (get_passward.equals(old_pass)) {
					// 新旧密码是否匹配
					if (old_pass.equals(new_pass)) {
						// 新旧密码一致
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("修改失败")
								.setMessage("新旧密码不能一致")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method

											}
										}).show();
					} else {
						// 密码修改成功
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { new_pass, updata_user });// 更新数据库
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("修改成功")
								.setMessage("密码修改成功")
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
												line_reg.setVisibility(View.INVISIBLE);
												line_updata_pass
														.setVisibility(View.INVISIBLE);
											}
										}).show();
					}
				} else {
					// 密码不匹配
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("修改失败")
							.setMessage("旧密码错误")
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
			} else {
				// 用户名不匹配
				new AlertDialog.Builder(LoginActivity.this)
						.setTitle("修改失败")
						.setMessage("输入的用户名不存在")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();
			}

			break;
		case R.id.btn_updata_pass:
			// 进入修改密码界面
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}