package com.example.shengsaiademo2018915;

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
 * @描述：完成注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-15
 */
public class RegActivity extends Activity {
	private Button btn_reg;// 注册确定按钮
	private EditText et_euser, et_epass, et_repass;// 用户名、密码、确认密码文本框
	private String user, pass, repass;// STring数值用户名、密码、确认密码
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		btn_reg = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// 注册确定按钮
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_euser.getText().toString();// 用户名
				pass = et_epass.getText().toString();// 密码
				repass = et_repass.getText().toString();// 确认密码
				if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// 新建数据库游标
					if (cursor.moveToNext()) {// 如果数据库游标移动
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("提示")
								.setMessage("该用户已存在")
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
						if (pass.equals(repass)) {// 两次输入密码一致
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { user, pass, "用户" });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"两次输入密码不一致");
						}
					}
				}
			}
		});
	}
}
