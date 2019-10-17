package com.example.drawdemo828;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录注册修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-28
 */
public class LoginActivity extends Activity {
	// 登录
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updata_pass;// 修改密码按钮
	private EditText et_user, et_pass, et_ip;// 文本框
	private String user, pass, ip;
	private CheckBox cb_rember, cb_autologin;

	// 注册
	private Button btn_reg_con;// 确定按钮
	private Button btn_reg_cls;// 关闭按钮
	private EditText et_reg_user, et_reg_pass, et_reg_repass;// 文本框
	private String reg_user, reg_pass, reg_repass;

	// 修改密码
	private Button btn_updata_con;// 确定
	private Button btn_updata_cls;// 关闭
	private EditText et_updata_user, et_updata_newpass, et_updata_oldpass;
	private String updata_user, updata_newpass, updata_oldpass;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
	}
}