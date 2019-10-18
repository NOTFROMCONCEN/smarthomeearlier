package com.example.shengsaiademo10052018;

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

import com.example.shengsaiademo10052018.mysql.MyDataBaseHelper;
import com.example.shengsaiademo10052018.toast.DiyToast;

/*
 * @文件名：LoginActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-5
 */
public class RegActivity extends Activity {
	private Button btn_con;// 注册
	private EditText et_repass, et_euser, et_epass;// IP用户名密码
	private String repass, user, pass;// IP用户名密码

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		// 确定按钮
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_euser.getText().toString();// 用户名
				pass = et_epass.getText().toString();// 密码
				repass = et_repass.getText().toString();// 确认密码
				if (user.isEmpty()) {// IP为空
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (pass.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {
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
						if (pass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { user, pass, "用户" });
							DiyToast.showToast(getApplicationContext(), "注册完成");
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

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:50:44
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
	}
}
