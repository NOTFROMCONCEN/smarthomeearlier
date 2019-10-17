package com.example.shengsaibdemo10062018;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaibdemo10062018.mysql.MyDataBaseHelper;
import com.example.shengsaibdemo10062018.textchangerHelper.TextChanger;
import com.example.shengsaibdemo10062018.toast.DiyToast;

/*
 * @文件名：MainActivity.java
 * @描述：注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-6
 */
public class RegActivity extends Activity {
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_repass;// 确认密码
	private String user, pass, repass;
	private Button btn_login;// 确定

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();// 绑定
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				repass = et_repass.getText().toString();// 确认密码
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (repass.isEmpty()) {// 确认密码为空
					DiyToast.showToast(getApplicationContext(), "请输入确认密码");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// 新建数据库游标
					if (cursor.moveToNext()) {// 移动
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						if (pass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { user, pass });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"两次密码输入不一致");
						}
					}
				}
			}
		});
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:15:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_con);
		et_pass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_euser);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}