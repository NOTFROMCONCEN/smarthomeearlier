package com.example.guosaiddemo907;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @文件名：MainActivity.java
 * @描述：完成登录、注册、记住密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-7
 */
public class MainActivity extends Activity implements OnClickListener {
	// 登录界面
	private LinearLayout line_login;
	private Button btn_login, btn_reg;
	private EditText et_user, et_pass, et_ip;
	private CheckBox cb_rember;
	private String user, pass, ip;

	// 注册界面
	private Button btn_con, btn_cls;
	private LinearLayout line_reg;
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// 密码字数
	private boolean isTrue = false;
	// 密码格式
	private Pattern pattern;
	private Matcher matcher;
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;
	// Ip地址
	public static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置无title
		setContentView(R.layout.activity_main);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(this, "db_user.db", null, 2);
		db = dbHelper.getWritableDatabase();
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		// 设置按钮点击事件
		btn_reg.setOnClickListener(this);
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null)
						.toString());
				et_ip.setText(sharedPreferences.getString("ip", null)
						.toString());
				Cursor cursor = db.rawQuery(
						"select * from tb_userInFo where username = ?",
						new String[] { et_user.getText().toString() });
				if (cursor.getCount() != 0) {
					cursor.moveToFirst();
					et_pass.setText(cursor.getString(
							cursor.getColumnIndex("passward")).toString());
					DiyToast.showToast(getApplicationContext(), cursor
							.getString(cursor.getColumnIndex("passward"))
							.toString());
				}
			}
		} else {
			et_user.setText("");
			et_ip.setText("");
			et_pass.setText("");
		}
		// 设置文本框显示字符
		et_epass.setTransformationMethod(new WordReplacement());
		et_pass.setTransformationMethod(new WordReplacement());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// 密码字数检测
	private void oncheck() {
		et_epass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (start > 4) {// 大于4，输入至第六位时激活
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：监听点击事件
	 * 
	 * @时 间：上午8:22:38
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.equals("")) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (epass.equals("")) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (repass.equals("")) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入确认密码");
			} else {
				if (epass.equals(repass)) {// 密码一致
					Cursor cursor = db.rawQuery(
							"select * from tb_userInFo where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "用户名已存在！");
					} else {
						oncheck();
						if (isTrue) {
							// 检测输入字符是否为纯数字或者纯字母
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"密码格式为：数字 + 字母格式");
							} else {
								db.execSQL(
										"insert into tb_userInFo (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showToast(getApplicationContext(),
										"注册成功");
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.INVISIBLE);
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码不足六位");
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "两次密码输入不一致");
				}
			}
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			Cursor cursor = db
					.rawQuery(
							"select * from tb_userInFo where username = ? and passward = ?",
							new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.equals("")) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (ip.equals("")) {// IP地址为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else if (!cursor.moveToNext()) {// 数据库无匹配项
				DiyToast.showToast(getApplicationContext(), "密码或用户名输入错误");
			} else {
				// 跳转
				ip_number = ip;
				Intent intent = new Intent(MainActivity.this,
						ChoseActivity.class);
				startActivity(intent);
				finish();
				if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();// 插入sharedPreferences
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();// 插入sharedPreferences
				}
			}
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
