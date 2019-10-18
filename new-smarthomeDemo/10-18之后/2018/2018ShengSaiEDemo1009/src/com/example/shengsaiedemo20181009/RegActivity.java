package com.example.shengsaiedemo20181009;

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
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.shengsaiedemo20181009.mysql.MyDataBaseHelper;
import com.example.shengsaiedemo20181009.toast.DiyToast;

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-9
 */
public class RegActivity extends Activity {
	private Button btn_con;// 登录按钮
	private EditText et_euser, et_repass, et_epass;// 文本框
	private String euser, repass, epass;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		// 登录
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();// 用户名
				epass = et_epass.getText().toString();// 密码
				repass = et_repass.getText().toString();// IP
				if (euser.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入确认密码");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ? ",
							new String[] { euser });// 新建游标
					if (cursor.moveToNext()) {// 移动
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { euser, epass, "用户" });
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("提示")
									.setMessage("注册成功")
									.setPositiveButton(
											"确定",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													startActivity(new Intent(
															getApplicationContext(),
															LoginActivity.class));
													finish();
												}
											}).show();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"两次密码输入不一致");
						}
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_con = (Button) findViewById(R.id.btn_reg_con);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
