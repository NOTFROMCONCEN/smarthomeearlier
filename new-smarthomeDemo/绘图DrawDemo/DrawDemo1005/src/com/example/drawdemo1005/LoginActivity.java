package com.example.drawdemo1005;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.drawdemo1005.fragment.BarActivity;
import com.example.drawdemo1005.mysql.MyDataBaseHelper;
import com.example.drawdemo1005.toast.DiyToast;

/*
 * @文件名：LoginActivity.java
 * @描述：登录、注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-5
 */
public class LoginActivity extends Activity {
	PopupWindow popupWindow;
	private Button btn_reg;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private Button btn_login;
	private EditText et_user, et_pass, et_ip;// 用户名、密码、IP文本框
	private CheckBox cb_autologin;// 自动登录
	private CheckBox cb_rember;// 记住密码
	private String user, pass, ip;
	SharedPreferences sharedPreferences;// sharedPreferences存储
	public static String IP_NUMBER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();// 绑定控件
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				show_Popwindow();
			}
		});
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_autologin.setChecked(true);
					cb_rember.setChecked(true);
				} else {
					cb_autologin.setChecked(false);
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					cb_autologin.setChecked(false);
					cb_rember.setChecked(false);
				}
			}
		});
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				ip = et_ip.getText().toString();
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {
						// 记住密码自动登录
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
						// 跳转
						IP_NUMBER = ip;
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

	private void initView() {
		// TODO Auto-generated method stub
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// 记住密码自动登录
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							IP_NUMBER = sharedPreferences.getString("ip", null);
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					}
				}).start();
			}
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
	}

	/*
	 * @方法名：show_Popwindow
	 * 
	 * @功 能：显示注册Popwindow
	 * 
	 * @时 间：下午4:42:06
	 */
	private void show_Popwindow() {
		final View popwindowView = getLayoutInflater().inflate(
				R.layout.popwindow_reg, null, false);
		popupWindow = new PopupWindow(popwindowView,
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT, true);
		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(),
				(Bitmap) null));
		popupWindow.showAsDropDown(btn_reg);
		Button btn_con = (Button) popwindowView.findViewById(R.id.btn_con);
		final EditText et_euser = (EditText) popwindowView
				.findViewById(R.id.et_euser);
		final EditText et_epass = (EditText) popwindowView
				.findViewById(R.id.et_epass);
		final EditText et_repass = (EditText) popwindowView
				.findViewById(R.id.et_repass);
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String pass;
				String user;
				String repass;
				user = et_euser.getText().toString();
				pass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入确认密码");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// 新建数据库游标
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						if (pass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { user, pass });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							popupWindow.dismiss();
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
