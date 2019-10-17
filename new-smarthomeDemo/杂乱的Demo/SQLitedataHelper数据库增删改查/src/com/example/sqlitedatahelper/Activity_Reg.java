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
import android.widget.Toast;

/*
 * @文件名：Activity_Login.java
 * @描述：注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class Activity_Reg extends Activity implements OnClickListener {
	private Button btn_con, btn_cls;// 按钮
	private String euser, epass, repass;
	private EditText et_euser, et_epass, et_repass;// 文本框
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库

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
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// 新建数据库指针
			if (euser.equals("")) {
				DiyToast.showToast(Activity_Reg.this, "请输入用户名");
			} else if (epass.equals("")) {
				DiyToast.showToast(Activity_Reg.this, "请输入密码");
			} else if (repass.equals("")) {
				DiyToast.showToast(Activity_Reg.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					if (cursor.moveToNext()) {
						DiyToast.showToast(Activity_Reg.this, "用户名已存在");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						DiyToast.showToast(Activity_Reg.this, "注册成功");
					}
				} else {
					DiyToast.showToast(Activity_Reg.this, "两次密码输入不一致！");
				}
			}
			break;
		case R.id.btn_cls:
			Intent intent = new Intent(Activity_Reg.this, Activity_Login.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
}