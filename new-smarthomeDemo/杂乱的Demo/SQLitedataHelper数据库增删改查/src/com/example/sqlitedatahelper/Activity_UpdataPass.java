package com.example.sqlitedatahelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：Activity_Login.java
 * @描述：修改密码功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class Activity_UpdataPass extends Activity implements OnClickListener {
	private Button btn_updata_con, btn_updata_cls;// 按钮
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// 文本框
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;// 数据库
	private String updata_user, updata_newpass, updata_oldpass, updata_getuser,
			updata_getpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updata_passward);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 按钮点击事件
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_updata_cls:// 关闭修改密码
			finish();
			break;
		case R.id.btn_updata_con:
			updata_newpass = et_updata_newpass.getText().toString();// 旧密码
			updata_user = et_updata_user.getText().toString();// 用户名
			updata_oldpass = et_updata_oldpass.getText().toString();// 新密码
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// 新建数据库
			cursor.moveToFirst();
			updata_getpass = cursor
					.getString(cursor.getColumnIndex("passward"));
			if (updata_user.equals("")) {// 新密码为空
				DiyToast.showToast(Activity_UpdataPass.this, "请输入用户名");
			} else if (updata_oldpass.equals("")) {// 旧密码为空
				DiyToast.showToast(Activity_UpdataPass.this, "请输入旧密码");
			} else if (updata_newpass.equals("")) {// 新密码为空
				DiyToast.showToast(Activity_UpdataPass.this, "请输入新密码");
			} else {
				if (updata_oldpass.equals(updata_newpass)) {
					DiyToast.showToast(Activity_UpdataPass.this, "新密码和旧密码不能一样！");
				} else {
					if (updata_getpass.equals(updata_oldpass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// 更新数据库
						DiyToast.showToast(Activity_UpdataPass.this, "密码修改成功");
					} else {
						DiyToast.showToast(Activity_UpdataPass.this, "旧密码输入错误");
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
