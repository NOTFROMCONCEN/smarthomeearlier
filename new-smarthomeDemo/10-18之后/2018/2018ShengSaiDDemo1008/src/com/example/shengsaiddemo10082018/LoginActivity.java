package com.example.shengsaiddemo10082018;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaiddemo10082018.fragment.BarActivity;
import com.example.shengsaiddemo10082018.mysql.MyDataBaseHelper;
import com.example.shengsaiddemo10082018.textchanger.TextChanger;
import com.example.shengsaiddemo10082018.toast.DiyToast;

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-8
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private EditText et_user, et_pass, et_port, et_ip;
	private String user, pass, port, ip;
	public static String IP;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// 绑定
		getUserPass();// 记住密码
		// 设置字符转换
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:13:24
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		et_user = (EditText) findViewById(R.id.et_user);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_reg:
			startActivity(new Intent(getApplicationContext(), RegActivity.class));
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口
			ip = et_ip.getText().toString();// IP
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (port.isEmpty()) {// 端口号为空
				DiyToast.showToast(getApplicationContext(), "请输入端口号");
			} else if (ip.isEmpty()) {// IP为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建游标
				if (cursor.moveToNext()) {// 移动
					// 登陆成功
					IP = ip;
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					// 记住密码
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.putString("port", port).commit();
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
				}
			}
			break;

		default:
			break;
		}
	}

	/*
	 * @方法名：getUserPass
	 * 
	 * @功 能：记住密码
	 * 
	 * @时 间：上午8:18:30
	 */
	private void getUserPass() {
		// TODO Auto-generated method stub
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
	}
}
