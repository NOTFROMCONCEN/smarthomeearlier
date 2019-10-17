package com.example.shengsaicdemo10072018;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.shengsaicdemo10072018.mysql.MyDataBaseHelper;
import com.example.shengsaicdemo10072018.toast.DiyToast;

public class RegActivity extends Activity {
	private Button btn_login_con;// 登录按钮
	private EditText et_repass, et_euser, et_epass;// IP用户名密码文本框

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// String
	private String euser, epass, repass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();// 绑定
		// 登录按钮点击
		btn_login_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();// 用户名
				epass = et_epass.getText().toString();// 密码
				repass = et_repass.getText().toString();// 确认密码
				if (euser.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (epass.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (repass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入确认密码");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建游标
					if (cursor.moveToNext()) {// y移动
						// 展示提示框
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("提示")
								.setMessage("用户名已存在")
								.setPositiveButton("确定",
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
							// 展示提示框
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
													// method
													// stub
													finish();
												}
											}).show();
						} else {
							// 展示提示框
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("提示")
									.setMessage("两次输入密码不一致")
									.setPositiveButton(
											"确定",
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
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:21:24
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login_con = (Button) findViewById(R.id.btn_reg_con);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
