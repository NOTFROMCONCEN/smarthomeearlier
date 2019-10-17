package com.example.guosaifdemo1010;

import com.example.guosaifdemo1010.fragment.BarActivity;
import com.example.guosaifdemo1010.mysql.MyDataBaseHelper;
import com.example.guosaifdemo1010.toast.DiyToast;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 登录、注册，修改、找回密码
 * @package_name com.example.guosaifdemo1010
 * @project_name GuoSaiFDemo1010
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity implements OnClickListener {
	// 登录功能
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_updatapass;// 修改密码按钮
	private Button btn_reback_pass;// 找回密码按钮
	private Spinner sp_1;// Spinner
	private EditText et_ip, et_port, et_user, et_pass;// 文本框
	private CheckBox cb_remebr_pass;// 记住密码
	private RadioButton ra_chose_user, ra_edit_user;// 选择账号、输入账号
	private String user, pass, port, ip;
	// 注册
	private EditText et_euser, et_epass, et_repass, et_twopass;// 文本框
	private String euser, epass, repass, twopass;// String
	// 修改密码
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// 文本框
	private String updata_user, updata_newpass, updata_oldpass;// String
	// 找回密码
	private EditText et_reback_user, et_reback_twopass;// 文本框
	private String reback_user, reback_pass;// String
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定
		ra_chose_user.setChecked(true);
		getUserName();// 获取用户名
		// 记住密码
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_remebr_pass.setChecked(true);
				if (sharedPreferences.getBoolean("mode", false) == true) {
					ra_chose_user.setChecked(true);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_user.setText("");
					et_port.setText(sharedPreferences.getString("port", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
				} else {
					ra_edit_user.setChecked(true);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_user.setText(sharedPreferences.getString("user", null));
					et_port.setText(sharedPreferences.getString("port", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
				}
			}
		}
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:18:09
	 */
	private void initView() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reback_pass.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		btn_updatapass = (Button) findViewById(R.id.btn_updata_pass);
		btn_updatapass.setOnClickListener(this);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		sp_1 = (Spinner) findViewById(R.id.sp_user);
		cb_remebr_pass = (CheckBox) findViewById(R.id.cb_rember);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_edit_user = (RadioButton) findViewById(R.id.ra_set_user);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午8:18:26
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口
			ip = et_ip.getText().toString();// IP
			if (ra_chose_user.isChecked()) {
				Cursor cursor = (Cursor) sp_1.getItemAtPosition(sp_1
						.getSelectedItemPosition());
				user = cursor.getString(1);
				if (ip.isEmpty()) {
					// IP = null
					DiyToast.showToast(getApplicationContext(), "IP不能为空");
				} else if (port.isEmpty()) {
					// 端口号 = null
					DiyToast.showToast(getApplicationContext(), "端口号不能为空");
				} else if (user.isEmpty()) {
					// 用户名 = null
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (pass.isEmpty()) {
					// 密码 = null
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else {
					Cursor cursor2 = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });
					if (cursor2.moveToNext()) {
						// 登录成功
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
						// 记住密码
						if (cb_remebr_pass.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("mode", true)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("mode", true)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
			if (ra_edit_user.isChecked()) {
				if (ip.isEmpty()) {
					// IP = null
					DiyToast.showToast(getApplicationContext(), "IP不能为空");
				} else if (port.isEmpty()) {
					// 端口号 = null
					DiyToast.showToast(getApplicationContext(), "端口号不能为空");
				} else if (user.isEmpty()) {
					// 用户名 = null
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (pass.isEmpty()) {
					// 密码 = null
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建游标
					if (cursor.moveToNext()) {// 移动
						// 登录成功
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
						// 记住密码
						if (cb_remebr_pass.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("mode", false)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("mode", false)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}

			break;
		case R.id.btn_reback_pass:
			// 找回密码
			Dialog_ReBackPass();
			break;
		case R.id.btn_reg:
			// 注册
			Dialog_Reg();
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			Dialog_UpdataPass();
			break;

		default:
			break;
		}
	}

	/**
	 * 得到用户名并放置在Spinner中
	 * 
	 * @return
	 */
	public void getUserName() {
		Cursor c = db.rawQuery("select * from user", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					MainActivity.this,
					android.R.layout.simple_spinner_dropdown_item, c,
					new String[] { "username" },
					new int[] { android.R.id.text1 });
			sp_1.setAdapter(adapter);
		}
	}

	// 找回密码
	private void Dialog_ReBackPass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater.inflate(R.layout.dialog_reback_pass, null,
				false);
		builder.setView(view);
		final EditText et_reback_user = (EditText) view
				.findViewById(R.id.et_reback_user);
		final EditText et_reback_twopass = (EditText) view
				.findViewById(R.id.et_reback_two_pass);
		builder.setPositiveButton("修改密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						reback_pass = et_reback_twopass.getText().toString();
						reback_user = et_reback_user.getText().toString();
						if (reback_user.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"用户名不能为空");
							Dialog_ReBackPass();
						} else if (reback_pass.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"旧密码不能为空");
							Dialog_ReBackPass();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { reback_user });
							if (cursor.moveToNext()) {
								if (reback_pass.equals(cursor.getString(cursor
										.getColumnIndex("twopass")))) {
									for (int i = 0; i < 4; i++) {// 强制前置显示
										DiyToast.showToast(
												getApplicationContext(),
												"您的密码："
														+ cursor.getString(cursor
																.getColumnIndex("passward")));
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"二级密码错误");
									Dialog_ReBackPass();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"用户名不存在");
								Dialog_ReBackPass();
							}
						}
					}
				});
		builder.show();
	}

	// 修改密码
	private void Dialog_UpdataPass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater.inflate(R.layout.dialog_updatapass, null,
				false);
		builder.setView(view);
		final EditText et_updata_user = (EditText) view
				.findViewById(R.id.et_updata_user);
		final EditText et_updata_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		final EditText et_updata_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		builder.setPositiveButton("修改密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						updata_user = et_updata_user.getText().toString();
						updata_oldpass = et_updata_oldpass.getText().toString();
						updata_newpass = et_updata_newpass.getText().toString();
						if (updata_user.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"用户名不能为空");
							Dialog_UpdataPass();
						} else if (updata_oldpass.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"旧密码不能为空");
							Dialog_UpdataPass();
						} else if (updata_newpass.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"新密码不能为空");
							Dialog_UpdataPass();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { updata_user });
							if (cursor.moveToNext()) {
								if (updata_oldpass.equals(cursor
										.getString(cursor
												.getColumnIndex("passward")))) {
									if (updata_oldpass.equals(updata_newpass)) {
										DiyToast.showToast(
												getApplicationContext(),
												"新旧密码不能一致");
										Dialog_UpdataPass();
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] { updata_newpass,
														updata_user });
										DiyToast.showToast(
												getApplicationContext(), "修改成功");
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"旧密码错误");
									Dialog_UpdataPass();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"用户名不存在");
								Dialog_UpdataPass();
							}
						}
					}
				});
		builder.show();
	}

	// 注册
	private void Dialog_Reg() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater.inflate(R.layout.dialog_reg, null, false);
		builder.setView(view);
		final EditText et_euser = (EditText) view.findViewById(R.id.et_euser);
		final EditText et_epass = (EditText) view.findViewById(R.id.et_epass);
		final EditText et_repass = (EditText) view.findViewById(R.id.et_repass);
		final EditText et_retwopass = (EditText) view
				.findViewById(R.id.et_retwopass);
		builder.setPositiveButton("注册", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				twopass = et_retwopass.getText().toString();
				System.out.println(euser);
				System.out.println(epass);
				System.out.println(repass);
				System.out.println(twopass);
				if (euser.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
					Dialog_Reg();
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
					Dialog_Reg();
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
					Dialog_Reg();
				} else if (twopass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "二级密码不能为空");
					Dialog_Reg();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
						Dialog_Reg();
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] { euser, epass, twopass });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							getUserName();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"验证密码不一致");
							Dialog_Reg();
						}
					}
				}
			}
		});
		builder.show();
	}
}
