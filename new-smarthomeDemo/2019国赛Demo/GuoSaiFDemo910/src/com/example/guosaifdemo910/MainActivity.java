package com.example.guosaifdemo910;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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

/*
 * @文件名：MainActivity.java
 * @描述：登录、注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-10
 */
public class MainActivity extends Activity {
	private Button btn_login, btn_reg, btn_updata_pass, btn_reback_pass;
	private EditText et_user, et_pass, et_ip, et_port;
	private RadioButton ra_chose_user, ra_set_user;
	private CheckBox cb_rember;
	private Spinner sp_user;
	// 数据库
	private MydataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MydataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		// 数据库记住密码功能
		Cursor cursor = db.rawQuery("select * from login_rember ", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			if (cursor.getString(cursor.getColumnIndex("rember_state"))
					.toString().equals("1")) {
				cb_rember.setChecked(true);
				et_user.setText(cursor.getString(cursor
						.getColumnIndex("username")));
				et_pass.setText(cursor.getString(cursor
						.getColumnIndex("passward")));
				et_ip.setText(cursor.getString(cursor.getColumnIndex("ip")));
				et_port.setText(cursor.getString(cursor.getColumnIndex("port")));
			} else {

			}
		}
		// 注册按钮
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showdialog_reg();
			}
		});
		// 修改密码按钮
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showdialog_updata_pass();
			}
		});
		// 找回密码按钮
		btn_reback_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showdialog_reback_pass();
			}
		});
		// 程序启动时向spinner内插入数据库
		Cursor c = db.rawQuery("select * from user", null);
		if (c.getCount() != 0) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					MainActivity.this, R.layout.user_text, c,
					new String[] { "username" }, new int[] { R.id.tv_user });
			sp_user.setAdapter(adapter);
		} else {
			DiyToast.showToast(getApplicationContext(), "请先注册");
		}
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (ra_chose_user.isChecked()) {
					if (et_ip.getText().toString().isEmpty()
							|| et_pass.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "不能有空白项");
					} else {
						Cursor cursor = (Cursor) sp_user
								.getItemAtPosition(sp_user
										.getSelectedItemPosition());
						Cursor cursor_login = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { cursor.getString(1),
												et_pass.getText().toString() });
						if (cursor_login.moveToNext()) {
							DiyToast.showToast(getApplicationContext(), "登陆成功");
							Intent intent = new Intent(MainActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
							if (cb_rember.isChecked()) {
								db.execSQL(
										"insert into login_rember (username,ip,port,passward,rember_state)values(?,?,?,?,?)",
										new String[] {
												et_user.getText().toString(),
												et_ip.getText().toString(),
												et_port.getText().toString(),
												et_pass.getText().toString(),
												"1" });
							} else {
								db.execSQL(
										"insert into login_rember (username,ip,port,passward,rember_state)values(?,?,?,?,?)",
										new String[] {
												et_user.getText().toString(),
												et_ip.getText().toString(),
												et_port.getText().toString(),
												et_pass.getText().toString(),
												"0" });
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"用户名或密码输入错误");
						}
					}
				}
				if (ra_set_user.isChecked()) {
					if (et_ip.getText().toString().isEmpty()
							|| et_pass.getText().toString().isEmpty()
							|| et_port.getText().toString().isEmpty()
							|| et_user.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "不能有空白项");
					}
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
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:21:17
	 */
	public void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		sp_user = (Spinner) findViewById(R.id.sp_user);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_set_user = (RadioButton) findViewById(R.id.ra_set_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	}

	// 找回密码自定义Dialog
	public void showdialog_reback_pass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reback_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("找回密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final EditText et_reback_user;
						final EditText et_reback_twopass;
						et_reback_twopass = (EditText) view
								.findViewById(R.id.et_reback_twopass);
						et_reback_user = (EditText) view
								.findViewById(R.id.et_reback_user);
						if (et_reback_twopass.getText().toString().equals("")
								|| et_reback_user.getText().toString()
										.equals("")) {
							DiyToast.showToast(MainActivity.this, "不能有空白项");
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { et_reback_user.getText()
											.toString() });
							if (cursor.moveToNext()) {
								String get_twopass = cursor.getString(cursor
										.getColumnIndex("twopass"));
								if (get_twopass.equals(et_reback_twopass
										.getText().toString())) {
									DiyToast.showToast(
											MainActivity.this,
											"密码："
													+ cursor.getString(cursor
															.getColumnIndex("passward")));
								} else {
									DiyToast.showToast(MainActivity.this,
											"二级密码输入错误");
								}
							} else {
								DiyToast.showToast(MainActivity.this, "用户不存在");
							}
						}
					}
				});
		builder.show();
	}

	// 注册自定义Dialog
	public void showdialog_reg() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reg, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("注册", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_reg_user;
				final EditText et_reg_pass;
				final EditText et_reg_repass;
				final EditText et_reg_two_pass;
				et_reg_pass = (EditText) view.findViewById(R.id.et_reg_pass);
				et_reg_repass = (EditText) view
						.findViewById(R.id.et_reg_repass);
				et_reg_two_pass = (EditText) view
						.findViewById(R.id.et_reg_two_pass);
				et_reg_user = (EditText) view.findViewById(R.id.et_reg_user);
				if (et_reg_pass.getText().toString().isEmpty()
						|| et_reg_repass.getText().toString().isEmpty()
						|| et_reg_two_pass.getText().toString().isEmpty()
						|| et_reg_user.getText().toString().isEmpty()) {
					DiyToast.showToast(MainActivity.this, "请填写完整信息，不能有空白项");
					showdialog_reg();
				} else {
					if (et_reg_pass.getText().toString()
							.equals(et_reg_repass.getText().toString())) {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ?",
										new String[] { et_reg_user.getText()
												.toString() });
						if (cursor.moveToNext()) {
							DiyToast.showToast(MainActivity.this, "用户名已存在");
							showdialog_reg();
							db.close();
						} else {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] {
											et_reg_user.getText().toString(),
											et_reg_pass.getText().toString(),
											et_reg_two_pass.getText()
													.toString() });
							db.close();
							DiyToast.showToast(MainActivity.this, "注册成功");
						}
					} else {
						DiyToast.showToast(MainActivity.this, "两次密码输入不一致");
						showdialog_reg();
					}
				}
			}
		});
		builder.show();
	}

	// 修改密码自定义Dialog
	public void showdialog_updata_pass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_updata_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("修改密码",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final EditText et_updata_user;
						final EditText et_updata_newpass;
						final EditText et_updata_oldpass;
						et_updata_newpass = (EditText) view
								.findViewById(R.id.et_updata_newpass);
						et_updata_oldpass = (EditText) view
								.findViewById(R.id.et_updata_oldpass);
						et_updata_user = (EditText) view
								.findViewById(R.id.et_updata_user);
						if (et_updata_newpass.getText().toString().isEmpty()
								|| et_updata_oldpass.getText().toString()
										.isEmpty()
								|| et_updata_user.getText().toString()
										.isEmpty()) {
							DiyToast.showToast(MainActivity.this,
									"请填写完整信息，不能有空白项");
							showdialog_updata_pass();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { et_updata_user.getText()
											.toString() });
							if (cursor.moveToNext()) {
								if (et_updata_newpass
										.getText()
										.toString()
										.equals(et_updata_oldpass.getText()
												.toString())) {
									DiyToast.showToast(MainActivity.this,
											"新旧密码不能一致");
									showdialog_updata_pass();
								} else {
									if (cursor.getString(
											cursor.getColumnIndex("passward"))
											.equals(et_updata_oldpass.getText()
													.toString())) {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] {
														et_updata_newpass
																.getText()
																.toString(),
														et_updata_user
																.getText()
																.toString() });
										DiyToast.showToast(MainActivity.this,
												"修改成功");
									} else {
										DiyToast.showToast(MainActivity.this,
												"旧密码输入错误");
									}
								}
							} else {
								DiyToast.showToast(MainActivity.this, "用户名不存在");
								showdialog_updata_pass();
							}
						}
					}
				});
		builder.show();
	}
}
