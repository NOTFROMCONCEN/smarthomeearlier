package com.example.shengsaib9192018;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：RegActivity.java
 * @描述：注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class RegActivity extends Activity {
	private Button btn_con;// 确定
	private Button btn_cls;// 关闭
	private EditText et_euser;// 用户名
	private EditText et_epass;// 密码
	private EditText et_repass;// 确认密码
	private String euser, epass, repass;// String
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 关闭
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 确认按钮
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
							new String[] { euser });
					if (cursor.moveToNext()) {
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("注册失败")
								.setMessage("用户已经存在")
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
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { euser, epass, "用户" });
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("注册成功")
									.setMessage("用户注册成功")
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub
													finish();
												}
											}).show();
						} else {
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("注册失败")
									.setMessage("验证密码不一致")
									.setPositiveButton(
											"Ok",
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
				}
			}
		});
	}
}
