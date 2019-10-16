package com.example.shengsaib9192018;

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

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;// 登录按钮
	private Button btn_exit;// 退出按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata_pass;// 修改密码按钮
	private EditText et_user, et_pass, et_port, et_ip;// 用户名密码IP端口号文本框
	private String user, pass, port, ip;// String

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	// IP地址
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logoin);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 点击事件
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// 设置字符转换
		et_pass.setTransformationMethod(new TextChanger());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exit:
			System.exit(0);
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口
			ip = et_ip.getText().toString();// IP
			if (port.isEmpty()) {// 端口为空
				DiyToast.showToast(getApplicationContext(), "请输入端口号");
			} else if (ip.isEmpty()) {// IP为空
				DiyToast.showToast(getApplicationContext(), "请输入IP");
			} else if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入账号");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else {
				Cursor condition = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (condition.moveToNext()) {// 游标移动
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.putString("port", port).commit();
					ip_number = ip;
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("登录失败")
							.setMessage("密码或用户名错误")
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
		case R.id.btn_reg:
			// 注册
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			Intent intent1 = new Intent(LoginActivity.this,
					UpPassActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
	}
}
