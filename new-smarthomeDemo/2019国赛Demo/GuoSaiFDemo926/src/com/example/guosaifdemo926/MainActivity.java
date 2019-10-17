package com.example.guosaifdemo926;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/*
 * @文件名：MainActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class MainActivity extends Activity {
	private EditText et_ip;// IP地址
	private EditText et_port;// 端口号
	private EditText et_user;// 账号
	private EditText et_pass;// 密码
	private Spinner sp_user;// 用户名
	private RadioButton ra_chose_user;// 选择账号
	private RadioButton ra_edit_user;// 输入账号
	private CheckBox cb_rember;// 记住密码
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private Button btn_updata_pass;// 修改密码
	private Button btn_reback_pass;// 找回密码
	private String user, pass, port, ip;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_edit_user = (RadioButton) findViewById(R.id.ra_set_user);
		sp_user = (Spinner) findViewById(R.id.sp_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		et_user.setVisibility(View.INVISIBLE);
		sp_user.setVisibility(View.INVISIBLE);
		get_user();
		ra_chose_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_user.setVisibility(View.INVISIBLE);
				sp_user.setVisibility(View.VISIBLE);
			}
		});
		ra_edit_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_user.setVisibility(View.VISIBLE);
				sp_user.setVisibility(View.INVISIBLE);
			}
		});
		// 记住密码功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				if (sharedPreferences.getBoolean("user_state", false) == true) {
					et_user.setVisibility(View.INVISIBLE);
					sp_user.setVisibility(View.VISIBLE);
					ra_chose_user.setChecked(true);
					ra_edit_user.setChecked(false);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
					et_port.setText(sharedPreferences.getString("port", null));
				} else {
					et_user.setVisibility(View.VISIBLE);
					sp_user.setVisibility(View.INVISIBLE);
					ra_chose_user.setChecked(false);
					ra_edit_user.setChecked(true);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
					et_port.setText(sharedPreferences.getString("port", null));
					et_user.setText(sharedPreferences.getString("user", null));
				}
			}
		}
		// 修改密码功能
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updata_pass_show_dialog();
			}
		});
		// 找回密码功能
		btn_reback_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reback_pass_show_dialog();
			}
		});
		// 注册按钮功能
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reg_show_dialog();
			}
		});
		// 登录功能
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				if (ra_chose_user.isChecked()) {
					if (ip.isEmpty()) {// IP地址为空
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
					} else if (port.isEmpty()) {// 端口为空
						DiyToast.showToast(getApplicationContext(), "请输入端口");
					} else if (pass.isEmpty()) {// 密码为空
						DiyToast.showToast(getApplicationContext(), "请输入密码");
					} else {
						Cursor cursor = (Cursor) sp_user
								.getItemAtPosition(sp_user
										.getSelectedItemPosition());
						Cursor cursor_login = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { cursor.getString(1),
												pass });
						if (cursor_login.moveToNext()) {// 游标移动
							// 登录跳转
							DiyToast.showToast(getApplicationContext(), "登陆成功");
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
							if (cb_rember.isChecked()) {
								// 记住密码
								sharedPreferences.edit()
										.putBoolean("user_state", true)
										.putBoolean("rember", true)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", "上次使用选择用户")
										.putString("port", port).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("user_state", true)
										.putBoolean("rember", false)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", "上次使用选择用户")
										.putString("port", port).commit();
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码输入错误");
						}
					}
				} else if (ra_edit_user.isChecked()) {
					if (ip.isEmpty()) {// IP地址为空
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
					} else if (port.isEmpty()) {// 端口为空
						DiyToast.showToast(getApplicationContext(), "请输入端口");
					} else if (user.isEmpty()) {// 用户名为空
						DiyToast.showToast(getApplicationContext(), "请输入用户名");
					} else if (pass.isEmpty()) {// 密码为空
						DiyToast.showToast(getApplicationContext(), "请输入密码");
					} else {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { user, pass });// 新建数据库游标
						if (cursor.moveToNext()) {// 移动
							// 登录跳转
							DiyToast.showToast(getApplicationContext(), "登陆成功");
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
							if (cb_rember.isChecked()) {
								// 记住密码
								sharedPreferences.edit()
										.putBoolean("user_state", false)
										.putBoolean("rember", true)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", user)
										.putString("port", port).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("user_state", false)
										.putBoolean("rember", false)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", user)
										.putString("port", port).commit();
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"用户名或密码输入错误");
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "请选择登录方式");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：updata_pass_show_dialog
	 * 
	 * @功 能：修改密码
	 * 
	 * @时 间：上午8:18:15
	 */
	private void updata_pass_show_dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_updata_pass,
				sp_user, false);
		builder.setPositiveButton("修改密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final EditText et_updata_user = (EditText) view
								.findViewById(R.id.et_updata_user);
						final EditText et_updata_newpass = (EditText) view
								.findViewById(R.id.et_updata_newpass);
						final EditText et_updata_oldpass = (EditText) view
								.findViewById(R.id.et_updata_oldpass);
						final String updata_user = et_updata_user.getText()
								.toString();
						final String updata_newpass = et_updata_newpass
								.getText().toString();
						final String updata_oldpass = et_updata_oldpass
								.getText().toString();
						if (updata_user.isEmpty()) {// 用户名为空
							DiyToast.showToast(getApplicationContext(),
									"请输入用户名");
							updata_pass_show_dialog();
						} else if (updata_oldpass.isEmpty()) {// 旧密码为空
							DiyToast.showToast(getApplicationContext(),
									"请输入旧密码");
							updata_pass_show_dialog();
						} else if (updata_newpass.isEmpty()) {// 新密码为空
							DiyToast.showToast(getApplicationContext(),
									"请输入新密码");
							updata_pass_show_dialog();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { updata_user });// 新建数据库游标
							if (cursor.moveToNext()) {// 游标移动
								String get_oldpass = cursor.getString(cursor
										.getColumnIndex("passward"));
								if (get_oldpass.equals(updata_oldpass)) {
									if (updata_newpass.equals(updata_oldpass)) {
										DiyToast.showToast(
												getApplicationContext(),
												"新旧密码不能一致");
										updata_pass_show_dialog();
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] { updata_newpass,
														updata_user });// 更新数据库
										DiyToast.showToast(
												getApplicationContext(), "修改成功");
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"旧密码输入错误");
									updata_pass_show_dialog();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"用户名不存在");
								updata_pass_show_dialog();
							}
						}
					}
				});
		builder.setView(view);
		builder.show();
	}

	/*
	 * @方法名：reg_show_dialog
	 * 
	 * @功 能：注册
	 * 
	 * @时 间：上午8:15:43
	 */
	private void reg_show_dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reg, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("注册", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_epass = (EditText) view
						.findViewById(R.id.et_reg_pass);
				final EditText et_euser = (EditText) view
						.findViewById(R.id.et_reg_user);
				final EditText et_repass = (EditText) view
						.findViewById(R.id.et_reg_repass);
				final EditText et_two_pass = (EditText) view
						.findViewById(R.id.et_reg_two_pass);
				final String euser = et_euser.getText().toString();
				final String epass = et_epass.getText().toString();
				final String repass = et_repass.getText().toString();
				final String twopass = et_two_pass.getText().toString();
				if (euser.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
					reg_show_dialog();
				} else if (epass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
					reg_show_dialog();
				} else if (repass.isEmpty()) {// 确认密码为空
					DiyToast.showToast(getApplicationContext(), "请输入确认密码");
					reg_show_dialog();
				} else if (twopass.isEmpty()) {// 二级密码为空
					DiyToast.showToast(getApplicationContext(), "请输二级密码");
					reg_show_dialog();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username =?",
							new String[] { euser });// 新建数据库游标
					if (cursor.moveToNext()) {// 游标移动
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
						reg_show_dialog();
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] { euser, epass, twopass });
							DiyToast.showToast(getApplicationContext(), "注册成功");
							get_user();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"两次输入密码不一致");
							reg_show_dialog();
						}
					}
				}
			}
		});
		builder.show();
	}

	private void reback_pass_show_dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reback_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("找回密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final EditText et_reback_user = (EditText) view
								.findViewById(R.id.et_reback_user);
						final EditText et_reback_twopass = (EditText) view
								.findViewById(R.id.et_reback_twopass);
						final String reback_user = et_reback_user.getText()
								.toString();
						final String reback_pass = et_reback_twopass.getText()
								.toString();
						if (reback_user.isEmpty()) {// 用户名为空
							DiyToast.showToast(getApplicationContext(),
									"请输入用户名");
							reback_pass_show_dialog();
						} else if (reback_pass.isEmpty()) {// 密码为空
							DiyToast.showToast(getApplicationContext(), "请输入密码");
							reback_pass_show_dialog();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { reback_user });
							if (cursor.moveToNext()) {
								if (cursor
										.getString(
												cursor.getColumnIndex("twopass"))
										.toString().equals(reback_pass)) {
									DiyToast.showToast(
											getApplicationContext(),
											"密码："
													+ cursor.getString(cursor
															.getColumnIndex("passward")));
								} else {
									DiyToast.showToast(getApplicationContext(),
											"二级密码输入错误");
									reback_pass_show_dialog();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"用户名输入错误");
							}
						}
					}
				});
		builder.show();
	}

	/*
	 * @方法名：get_user
	 * 
	 * @功 能：获取用户名(Spinner)
	 * 
	 * @时 间：上午8:46:36
	 */
	private void get_user() {
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					MainActivity.this, R.layout.user_text, cursor,
					new String[] { "username" }, new int[] { R.id.tv_user });
			sp_user.setAdapter(adapter);
		} else {
			DiyToast.showToast(getApplicationContext(), "程序首次启动");
		}
	}
}
