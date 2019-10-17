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
 * @文件名：UpPassActivity.java
 * @描述：修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class UpPassActivity extends Activity {
	private Button btn_updata_con;// 确定
	private Button btn_updata_cls;// 关闭
	private EditText et_updata_user;// 用户名
	private EditText et_updata_newpass;// 新密码
	private EditText et_updata_oldpass;// 旧密码

	// STring
	String updata_user;
	String updata_newpass;
	String updata_oldpass;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updata_pass);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updata_newpass = et_updata_newpass.getText().toString();
				updata_oldpass = et_updata_oldpass.getText().toString();
				updata_user = et_updata_user.getText().toString();
				if (updata_newpass.isEmpty() || updata_oldpass.isEmpty()
						|| updata_user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "不能有空白项");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });
					if (cursor.moveToNext()) {
						String get_oldpass = cursor.getString(cursor
								.getColumnIndex("passward"));
						if (get_oldpass.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward  = ? where username = ?",
									new String[] { updata_newpass, updata_user });
							new AlertDialog.Builder(UpPassActivity.this)
									.setTitle("修改成功")
									.setTitle("密码修改成功")
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													finish();
												}
											}).show();
						} else {
							new AlertDialog.Builder(UpPassActivity.this)
									.setTitle("修改失败")
									.setTitle("旧密码错误")
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub

												}
											}).show();
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "用户名不存在");
					}
				}
			}
		});
	}
}
