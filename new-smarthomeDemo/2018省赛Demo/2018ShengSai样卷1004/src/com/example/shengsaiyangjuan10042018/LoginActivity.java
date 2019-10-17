package com.example.shengsaiyangjuan10042018;

import com.example.shengsaiyangjuan10042018.fragment.BarActivity;
import com.example.shengsaiyangjuan10042018.sqlite.MyDataBaseHelper;
import com.example.shengsaiyangjuan10042018.toast.DiyToast;

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
 * @描述：登录、注册、修改网络
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-4
 */
public class LoginActivity extends Activity {
	private Button btn_login;// 登录
	private Button btn_web;// 网络参数
	private Button btn_reg;// 注册
	private EditText et_user, et_pass;// 用户名，密码文本框
	PopupWindow mPopupWindow_reg;
	PopupWindow mPopupWindow_web;
	private Button btn_reg_con, btn_web_con;
	private String user, pass, ip, port, euser, epass, repass;
	private EditText et_euser, et_epass, et_repass;
	private EditText et_ip, et_port;
	public static SharedPreferences sharedPreferences;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();

		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reg_popwindow();
			}
		});
		// 网络参数按钮点击事件
		btn_web.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// web_popwindow();
				DiyToast.showToast(getApplicationContext(),
						"不可用，默认IP：17.1.10.2" + "\n" + "端口：6006");
			}
		});
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "登陆成功");
						sharedPreferences.edit().putString("ip", "17.1.10.2")
								.putString("port", "6006").commit();
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
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
	 * @时 间：上午8:25:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_web = (Button) findViewById(R.id.btn_web);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// sharedPreferences
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	/*
	 * @方法名：web_popwindow
	 * 
	 * @功 能：网络参数Popwindow
	 * 
	 * @时 间：上午8:33:00
	 */
	private void web_popwindow() {
		// TODO Auto-generated method stub

		final View popupView = getLayoutInflater().inflate(
				R.layout.popwindow_web, null);
		mPopupWindow_web = new PopupWindow(popupView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow_web.setTouchable(true);
		mPopupWindow_web.setOutsideTouchable(true);
		mPopupWindow_web.setBackgroundDrawable(new BitmapDrawable(
				getResources(), (Bitmap) null));
		mPopupWindow_reg.showAsDropDown(btn_web);
		et_ip = (EditText) popupView.findViewById(R.id.et_ip);
		et_port = (EditText) popupView.findViewById(R.id.et_port);
		btn_web_con = (Button) popupView.findViewById(R.id.btn_web_con);
		btn_web_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();// IP
				port = et_port.getText().toString();// port
				if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (port.isEmpty()) {// 端口为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else {
					LoginActivity.sharedPreferences.edit().putString("ip", ip)
							.putString("port", port).commit();
					DiyToast.showToast(getApplicationContext(), "设置成功");
				}
			}
		});
	}

	/*
	 * @方法名：reg_popwindow
	 * 
	 * @功 能：注册Popwindow
	 * 
	 * @时 间：上午8:33:00
	 */
	private void reg_popwindow() {
		// TODO Auto-generated method stub

		final View popupView = getLayoutInflater().inflate(
				R.layout.popwindow_reg, null);
		mPopupWindow_reg = new PopupWindow(popupView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		mPopupWindow_reg.setTouchable(true);
		mPopupWindow_reg.setOutsideTouchable(true);
		mPopupWindow_reg.setBackgroundDrawable(new BitmapDrawable(
				getResources(), (Bitmap) null));
		mPopupWindow_reg.showAsDropDown(btn_reg);
		btn_reg_con = (Button) popupView.findViewById(R.id.btn_reg_con);
		et_euser = (EditText) popupView.findViewById(R.id.et_euser);
		et_epass = (EditText) popupView.findViewById(R.id.et_epass);
		et_repass = (EditText) popupView.findViewById(R.id.et_repass);
		btn_reg_con.setOnClickListener(new OnClickListener() {

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
							new String[] { euser });// 新建游标
					if (cursor.moveToNext()) {// 游标移动
						DiyToast.showToast(getApplicationContext(), "用户名已被注册");
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { euser, epass });// 插入数据库
							DiyToast.showToast(getApplicationContext(), "注册成功");
							mPopupWindow_reg.dismiss();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"两次密码输入不一致");
						}
					}
				}
			}
		});
	}
}
