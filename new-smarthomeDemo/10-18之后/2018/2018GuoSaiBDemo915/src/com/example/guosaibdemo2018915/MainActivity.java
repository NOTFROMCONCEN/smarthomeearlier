package com.example.guosaibdemo2018915;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @文件名：MainActivity.java
 * @描述：登录、注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-15
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_con;// 注册确定按钮

	static String ip_number;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// 注册界面
	private EditText et_euser, et_epass, et_repass;// 用户、密码、确认密码;
	private String euser, epass, repass;
	// 登录界面
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private String user, pass, port, ip;

	// 布局
	private LinearLayout line_login;// 登录
	private LinearLayout line_reg;// 注册

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 设置文本框
		et_pass.setTransformationMethod(new TextChanger());
		// 注册确定按钮点击事件
		btn_con.setOnClickListener(this);
		// 登录按钮点击事件
		btn_login.setOnClickListener(this);
		// 注册按钮点击事件
		btn_reg.setOnClickListener(this);
		// 设置line布局
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
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
		case R.id.btn_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (epass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (repass.isEmpty()) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "请输入确认密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {// 新旧密码一致
						db.execSQL(
								"insert into user (username,passward,op)values(?,?,?)",
								new String[] { euser, epass, "用户" });
						DiyToast.showToast(getApplicationContext(), "注册成功");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
					} else {
						DiyToast.showToast(getApplicationContext(), "两次输入密码不一致");
					}
				}

			}
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口
			ip = et_ip.getText().toString();// ip地址
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (port.isEmpty()) {// 端口为空
				DiyToast.showToast(getApplicationContext(), "请输入端口号");
			} else if (ip.isEmpty()) {// IP地址为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					DiyToast.showToast(getApplicationContext(), "登陆成功");
					ip_number = ip;
					Intent intent = new Intent(getApplicationContext(),
							ChoseActivity.class);
					startActivity(intent);
					finish();
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
				}
			}
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
