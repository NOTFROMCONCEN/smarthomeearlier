package com.example.shengsaiyangjuan9172018;

import java.util.logging.SocketHandler;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

/*
 * @文件名：LoginActivity.java
 * @描述：登录、注册、网络参数
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-17
 */
public class LoginActivity extends Activity {
	private Button btn_web;// 网络参数按钮
	private Button btn_reg;// 注册按钮
	private Button btn_login;// 登录按钮
	private EditText et_user, et_pass;// 用户名、密码文本框
	PopupWindow mPopupWindow_reg;// popwindow
	private SharedPreferences sharedPreferences;// sharedPreferences存储
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	//
	static Button btn_reg_con;
	//
	String user, pass, port, ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_web = (Button) findViewById(R.id.btn_web);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pass = et_pass.getText().toString();
				user = et_user.getText().toString();
				if (pass.isEmpty() || user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名或密码不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });
					if (!cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					} else {
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});

	}

	/*
	 * @方法名：popwindow_reg
	 * 
	 * @功 能：弹出popwindow完成注册
	 * 
	 * @时 间：下午3:00:51
	 */
	private void popwindow_reg() {
		final View popupView = getLayoutInflater().inflate(
				R.layout.popwindow_web, null);
		mPopupWindow_reg = new PopupWindow(popupView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow_reg.setTouchable(true);
		mPopupWindow_reg.setOutsideTouchable(true);
		mPopupWindow_reg.setBackgroundDrawable(new BitmapDrawable(
				getResources(), (Bitmap) null));
		mPopupWindow_reg.showAsDropDown(btn_reg);
		btn_reg_con = (Button) popupView.findViewById(R.id.btn_reg_con);
		btn_reg_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// // TODO Auto-generated method stub
				// EditText et_euser = (EditText) popupView
				// .findViewById(R.id.et_euser);
				// EditText et_epass = (EditText) popupView
				// .findViewById(R.id.et_epass);
				// EditText et_repass = (EditText) popupView
				// .findViewById(R.id.et_repass);
				// String euser = et_euser.getText().toString();
				// String epass = et_epass.getText().toString();
				// String repass = et_repass.getText().toString();
				// if (euser.isEmpty()) {
				// DiyToast.showToast(getApplicationContext(), "请输入用户名");
				// } else if (epass.isEmpty()) {
				// DiyToast.showToast(getApplicationContext(), "请输入密码");
				// } else if (repass.isEmpty()) {
				// DiyToast.showToast(getApplicationContext(), "请输入确认密码");
				// } else {
				// Cursor cursor = db.rawQuery(
				// "select * from user where username = ?",
				// new String[] { euser });// 新建数据库指针
				// if (cursor.moveToNext()) {
				// DiyToast.showToast(getApplicationContext(), "用户名已存在");
				// } else {
				// if (epass.equals(repass)) {
				// db.execSQL(
				// "insert into user (username,passward)values(?,?)",
				// new String[] { euser, epass });// 插入数据库
				// DiyToast.showToast(getApplicationContext(), "注册成功");
				// mPopupWindow_reg.dismiss();
				// } else {
				// DiyToast.showToast(getApplicationContext(),
				// "两次输入的密码不一致");
				// }
				// }
				//
				// }

			}
		});
	}
}