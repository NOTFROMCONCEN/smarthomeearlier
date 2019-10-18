package com.example.shengsaisaixiangshitiDemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
 * @时间：2019-10-3
 */
public class RegActivity extends Activity {
	private Button btn_reg;// 注册
	private EditText et_euser;// 用户名
	private EditText et_epass;// 密码
	private EditText et_repass;// 确认密码
	private String euser, epass, repass;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		// 注册按钮点击时间
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();// 用户名
				epass = et_epass.getText().toString();// 密码
				repass = et_repass.getText().toString();// 确认密码s
				if (euser.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入确认密码");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库游标
					if (cursor.moveToNext()) {// 移动
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { euser, epass });
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
													Intent intent = new Intent(
															getApplicationContext(),
															LoginActivity.class);
													startActivity(intent);
													finish();
												}
											}).show();
						} else {
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("注册失败")
									.setMessage("两次密码输入不一致")
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

	/*
	 * @方法名：initview()
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:56:29
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_reg = (Button) findViewById(R.id.btn_reg_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
