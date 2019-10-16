package com.example.drawdemo910;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
 * @时间：2019-9-10
 */
public class MainActivity extends Activity {
	// 正则表达式、密码格式限制
	private Matcher matcher;
	private Pattern pattern;
	private boolean isTrue = false;
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private Button btn_updata_pass;// 修改密码
	private Button btn_reback_pass;// 找回密码
	private Spinner sp_user;// 下拉菜单
	private EditText et_user, et_pass, et_ip;// 文本框
	private CheckBox cb_autologin;// 自动登录
	private CheckBox cb_rember;// 记住密码
	private RadioButton ra_chose_user;// 选择用户
	private RadioButton ra_set_user;// 输入用户
	public static SharedPreferences sharedPreferences;// sharedPreferences存储
	// 数据库
	private MyDataBaseHelepr dbHelepr;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("登录");
		dbHelepr = new MyDataBaseHelepr(this, "info.db", null, 2);
		db = dbHelepr.getWritableDatabase();
		initView();
		set_user();
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog_Reg();
			}
		});
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String user, pass, ip;
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				ip = et_ip.getText().toString();
				if (ra_chose_user.isChecked()) {
					if (pass.isEmpty() || ip.isEmpty()) {
						DiyToast.showToas(getApplicationContext(), "不能有空白项目");
					} else {
						Cursor c = (Cursor) sp_user.getItemAtPosition(sp_user
								.getSelectedItemPosition());
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { c.getString(1),
												et_pass.getText().toString() });
						if (cursor.moveToNext()) {
							Intent intent = new Intent(MainActivity.this,
									UnLockActivity.class);
							startActivity(intent);
							finish();
							DiyToast.showToas(getApplicationContext(), "登陆成功");
							if (cb_autologin.isChecked()
									&& cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_autologin.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", true)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							}
						} else {
							DiyToast.showToas(getApplicationContext(), "密码输入错误");
						}
					}
				} else if (ra_set_user.isChecked()) {
					if (user.isEmpty() || pass.isEmpty() || ip.isEmpty()) {
						DiyToast.showToas(getApplicationContext(), "不能有空白项目!");
					} else {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { user, pass });
						if (cursor.moveToNext()) {
							Intent intent = new Intent(MainActivity.this,
									UnLockActivity.class);
							startActivity(intent);
							finish();
							DiyToast.showToas(getApplicationContext(), "登录成功");
							if (cb_autologin.isChecked()
									&& cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", user)
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_autologin.isChecked()) {
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
						} else {
							DiyToast.showToas(getApplicationContext(),
									"用户名或密码输入错误");
						}
					}
				} else {
					DiyToast.showToas(getApplicationContext(), "请选择登录方式");
				}
			}
		});
		// 修改密码按钮点击事件
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog_UpdataPass();
			}
		});
		// 找回密码按钮点击事件
		btn_reback_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog_RebackPass();
			}
		});
		// 登录时选择用户还是输入
		ra_chose_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sp_user.setVisibility(View.VISIBLE);
				et_user.setVisibility(View.GONE);
			}
		});
		ra_set_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sp_user.setVisibility(View.GONE);
				et_user.setVisibility(View.VISIBLE);
			}
		});
		// 记住密码功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：showDialog
	 * 
	 * @功 能：展示自定义Dialog找回密码
	 * 
	 * @时 间：下午6:45:17
	 */
	public void showDialog_RebackPass() {

	}

	/*
	 * @方法名：showDialog
	 * 
	 * @功 能：展示自定义Dialog修改密码功能
	 * 
	 * @时 间：下午6:45:17
	 */
	public void showDialog_UpdataPass() {

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("修改密码");
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		final View view = inflater.inflate(R.layout.activity_updata_pass, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_updata_user = (EditText) view
						.findViewById(R.id.et_updata_user);
				final EditText et_updata_oldpass = (EditText) view
						.findViewById(R.id.et_updata_oldpass);
				final EditText et_updata_newpass = (EditText) view
						.findViewById(R.id.et_updata_newpass);
				if (et_updata_newpass.getText().toString().isEmpty()
						|| et_updata_oldpass.getText().toString().isEmpty()
						|| et_updata_user.getText().toString().isEmpty()) {
					DiyToast.showToas(getApplicationContext(), "不能有空白项");
					showDialog_UpdataPass();
				} else {
					Cursor cursor = db
							.rawQuery("select * from user where username = ?",
									new String[] { et_updata_user.getText()
											.toString() });
					if (cursor.moveToNext()) {
						if (cursor.getString(cursor.getColumnIndex("passward"))
								.toString()
								.equals(et_updata_oldpass.getText().toString())) {
							if (et_updata_newpass
									.getText()
									.toString()
									.equals(et_updata_oldpass.getText()
											.toString())) {
								DiyToast.showToas(getApplicationContext(),
										"新旧密码不能一致");
								showDialog_UpdataPass();
							} else {
								db.execSQL(
										"update user set passward = ? where username = ?",
										new String[] {
												et_updata_newpass.getText()
														.toString(),
												et_updata_user.getText()
														.toString() });
								DiyToast.showToas(getApplicationContext(),
										"修改成功");
							}
						} else {
							DiyToast.showToas(getApplicationContext(),
									"旧密码输入错误");
							showDialog_UpdataPass();
						}
					} else {
						DiyToast.showToas(getApplicationContext(), "用户名不存在");
						showDialog_UpdataPass();
					}
				}
			}
		});
		builder.show();
	}

	/*
	 * @方法名：showDialog
	 * 
	 * @功 能：展示自定义Dialog注册功能
	 * 
	 * @时 间：下午6:34:54
	 */
	public void showDialog_Reg() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("注册");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reg, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_reg_user = (EditText) view
						.findViewById(R.id.et_reg_user);
				final EditText et_reg_pass = (EditText) view
						.findViewById(R.id.et_reg_pass);
				final EditText et_reg_repass = (EditText) view
						.findViewById(R.id.et_reg_repass);
				final EditText et_reg_twopass = (EditText) view
						.findViewById(R.id.et_reg_twopass);
				if (et_reg_user.getText().toString().isEmpty()
						|| et_reg_repass.getText().toString().isEmpty()
						|| et_reg_twopass.getText().toString().isEmpty()
						|| et_reg_pass.getText().toString().isEmpty()) {// 任意一项为空
					DiyToast.showToas(getApplicationContext(), "不能留有空白项");
					showDialog_Reg();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { et_reg_user.getText().toString() });
					if (cursor.moveToNext()) {
						DiyToast.showToas(getApplicationContext(), "用户名已存在！");
						showDialog_Reg();
					} else {
						if (et_reg_pass.getText().toString()
								.equals(et_reg_repass.getText().toString())) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] {
											et_reg_user.getText().toString(),
											et_reg_pass.getText().toString(),
											et_reg_twopass.getText().toString() });
							DiyToast.showToas(getApplicationContext(), "注册成功");
							set_user();
						} else {
							DiyToast.showToas(getApplicationContext(),
									"两次输入密码不一致");
							showDialog_Reg();
						}
					}
				}
			}
		});
		builder.show();
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：下午6:32:52
	 */
	public void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		sp_user = (Spinner) findViewById(R.id.sp_user);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_set_user = (RadioButton) findViewById(R.id.ra_set_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	}

	/*
	 * @方法名：set_user
	 * 
	 * @功 能：设置Spinner数据库
	 * 
	 * @时 间：下午7:23:29
	 */
	public void set_user() {
		// 程序启动时设定Spinner显示内容
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, c,
				new String[] { "username" }, new int[] { android.R.id.text1 });
		sp_user.setAdapter(adapter);
	}
}
