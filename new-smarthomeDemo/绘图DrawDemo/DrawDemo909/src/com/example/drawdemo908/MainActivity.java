package com.example.drawdemo908;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/*
 * @文件名：MainActivity.java
 * @描述：完成登录、注册、修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-7
 */
public class MainActivity extends Activity implements OnClickListener {
	// 正则表达式
	private boolean isTrue = false;
	private Matcher matcher;
	private Pattern pattern;
	// 登录界面
	private Button btn_login, btn_reg, btn_updata_pass;
	private EditText et_user, et_pass, et_ip;
	private String user, pass, ip;
	private CheckBox cb_rember, cb_autologin;
	// 注册界面
	private Button btn_con, btn_cls;
	private String euser, epass, repass;
	private EditText et_euser, et_epass, et_repass;
	// 修改密码界面
	private EditText et_updata_user;
	private EditText et_updata_newpass;
	private EditText et_updata_oldpass;
	private Button btn_updata_con, btn_updata_cls;
	private String user_updata, newpass_updata, oldpass_updata;
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// 布局
	private LinearLayout line_login, line_reg, line_updata;
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;
	// ListView
	private ListView lv_1;
	// 展示数据库
	private Button btn_show_sqlite;
	// ListView展示
	private SimpleCursorAdapter simpleCursorAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initview();
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();

		btn_show_sqlite = (Button) findViewById(R.id.btn_show_sqlite);

		// 展示数据库
		btn_show_sqlite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lv_1.setAdapter(null);
				sqlite_show();
			}
		});
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> persion, View view,
					int potsion, long id) {
				// TODO Auto-generated method stub
				final Cursor cursor = (Cursor) persion
						.getItemAtPosition(potsion);
				new AlertDialog.Builder(MainActivity.this).setItems(
						new String[] { "删除", "更新" },
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								if (which == 0) {
									db.execSQL(
											"delete from user where username = ? and passward = ?",
											new String[] { cursor.getString(1),
													cursor.getString(2) });
									lv_1.setAdapter(null);
									sqlite_show();
								}
								if (which == 1) {
									
								}
							}
						}).show();
			}
		});

		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// 设置布局
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_updata.setVisibility(View.INVISIBLE);
		// 记住密码自动登录功能
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {

						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					}
				}).start();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.equals("")) {// 用户名为空
				DIyToast.showToast(MainActivity.this, "请输入用户名");
			} else if (epass.equals("")) {// 密码为空
				DIyToast.showToast(MainActivity.this, "请输入密码");
			} else if (repass.equals("")) {// 确认密码为空
				DIyToast.showToast(MainActivity.this, "请输入确认密码");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });
					if (cursor.moveToNext()) {
						DIyToast.showToast(MainActivity.this, "用户名已存在");
					} else {
						oncheck();
						if (isTrue) {
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]|.*[0-9].*[a-zA-Z].*[0-9]|.*[a-zA-Z].*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DIyToast.showToast(MainActivity.this,
										"密码格式：字母+数字");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });
								DIyToast.showToast(MainActivity.this, "注册成功");
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.INVISIBLE);
								line_updata.setVisibility(View.INVISIBLE);
								et_euser.setText("");
								et_epass.setText("");
								et_repass.setText("");
							}
						} else {
							DIyToast.showToast(MainActivity.this, "密码不足六位");
						}
					}
				} else {
					DIyToast.showToast(MainActivity.this, "两次密码不一致");
				}
			}
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor condition = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });
			if (user.equals("")) {// 用户名为空
				DIyToast.showToast(MainActivity.this, "用户名不能为空");
			} else if (pass.equals("")) {// 密码为空
				DIyToast.showToast(MainActivity.this, "密码不能为空");
			} else if (ip.equals("")) {// IP地址为空
				DIyToast.showToast(MainActivity.this, "IP地址不能为空");
			} else if (!condition.moveToNext()) {
				DIyToast.showToast(MainActivity.this, "用户名或密码输入错误");
			} else {
				// 登录成功
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putBoolean("autologin", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				}
			}
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_cls:
			// 修改密码关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_con:
			// 修改密码确定
			user_updata = et_updata_user.getText().toString();// 用户名
			newpass_updata = et_updata_newpass.getText().toString();// 新密码
			oldpass_updata = et_updata_oldpass.getText().toString();// 旧密码
			if (user_updata.equals("")) {// 用户名为空

			} else if (oldpass_updata.equals("")) {// 旧密码为空

			} else if (newpass_updata.equals("")) {// 新密码为空

			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { user_updata });
				if (cursor.moveToNext()) {
					if (cursor.getString(cursor.getColumnIndex("passward"))
							.toString().equals(oldpass_updata)) {
						if (oldpass_updata.equals(newpass_updata)) {
							DIyToast.showToast(MainActivity.this, "新旧密码不能一致");
						} else {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { newpass_updata, user_updata });
							DIyToast.showToast(MainActivity.this, "修改密码成功");
							et_updata_newpass.setText("");
							et_updata_oldpass.setText("");
							et_updata_user.setText("");
							line_login.setVisibility(View.VISIBLE);
							line_reg.setVisibility(View.INVISIBLE);
							line_updata.setVisibility(View.INVISIBLE);
						}
					} else {
						DIyToast.showToast(MainActivity.this, "旧密码输入错误");
					}
				} else {
					DIyToast.showToast(MainActivity.this, "用户名不存在");
				}
			}
			break;
		case R.id.btn_updata_pass:
			// 修改密码
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	public void oncheck() {
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start <= 4) {
					isTrue = false;
				} else {
					isTrue = true;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	private void sqlite_show() {
		// ListView展示
		Cursor cursor = db.rawQuery("select * from user", null);// 新建数据库游标
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this,
					R.layout.activity_text, cursor, new String[] { "username",
							"passward" }, new int[] { R.id.tv_user,
							R.id.tv_pass });
			lv_1.setAdapter(simpleCursorAdapter);
		} else {
			lv_1.setAdapter(null);
		}
	}

	public void initview() {
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata = (LinearLayout) findViewById(R.id.line_updata);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		lv_1 = (ListView) findViewById(R.id.listView1);
	}
}
