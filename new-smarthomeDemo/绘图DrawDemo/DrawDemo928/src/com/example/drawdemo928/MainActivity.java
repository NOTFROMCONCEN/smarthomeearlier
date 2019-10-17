package com.example.drawdemo928;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @文件名：MainActivity.java
 * @描述：登录、注册、修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-28
 */
public class MainActivity extends Activity implements OnClickListener {
	// 布局
	private LinearLayout line_login;// 登录
	private LinearLayout line_reg;// 注册
	private LinearLayout line_updata_pass;// 修改密码
	// 登录界面
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata_pass;// 修改密码按钮
	private Button btn_exit;// 退出按钮
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_ip;// IP地址
	static String user, pass, ip;
	// 注册界面
	private Button btn_con, btn_cls;// 注册确定按钮、关闭按钮
	private EditText et_euser;// 用户名
	private EditText et_epass;// 密码
	private EditText et_repass;// 确认密码
	private String euser, epass, repass;
	// 修改密码界面
	private Button btn_updata_con;// 修改密码确认
	private Button btn_updata_cls;// 修改密码关闭
	private EditText et_updata_user;// 用户名
	private EditText et_updata_oldpass;// 密码
	private EditText et_updata_newpass;// 新密码
	// 记住密码
	SharedPreferences sharedPreferences;
	// 数据库
	SQLiteDatabase db;
	MyDataBaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		initView();// 绑定
		// 记住密码功能实现
		if (sharedPreferences != null) {
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void initView() {
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_cls.setOnClickListener(this);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_updata_pass.setVisibility(View.INVISIBLE);
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：点击事件
	 * 
	 * @时 间：下午3:27:49
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_updata_pass:
			// 修改密码按钮
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定按钮
			SQLiteControl.UpdataPass(getApplicationContext(), et_updata_user
					.getText().toString(), et_updata_oldpass.getText()
					.toString(), et_updata_newpass.getText().toString());
			if (SQLiteControl.updata_state) {
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_updata_pass.setVisibility(View.INVISIBLE);
			}
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭按钮
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_login:
			// 登录按钮
			SQLiteControl.SeleSQL(MainActivity.this, et_user.getText()
					.toString(), et_pass.getText().toString());
			if (SQLiteControl.login_state) {
				DiyToast.showTaost(getApplicationContext(), "登陆成功");
				ip = et_ip.getText().toString();
				sharedPreferences.edit()
						.putString("user", et_user.getText().toString())
						.putString("pass", et_pass.getText().toString())
						.putString("ip", et_ip.getText().toString()).commit();
				Intent intent = new Intent(getApplicationContext(),
						UnLockActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.btn_reg:
			// 注册按钮
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_cls:
			// 注册关闭按钮
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// 注册确定按钮
			SQLiteControl.setSQL(getApplicationContext(), et_euser.getText()
					.toString(), et_epass.getText().toString(), et_repass
					.getText().toString());
			if (SQLiteControl.reg_state) {
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_updata_pass.setVisibility(View.INVISIBLE);
			}
			break;
		default:
			break;
		}
	}
}
