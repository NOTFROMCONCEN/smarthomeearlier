package com.example.guosaiddemo924;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

/*
 * @文件名：MainActivity.java
 * @描述：登录、注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-24
 */
public class MainActivity extends Activity implements OnClickListener {
	private RelativeLayout re_login;// 登录界面
	private RelativeLayout re_reg;// 注册界面
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_repass;// 确认密码
	private EditText et_ip;// IP地址
	private EditText et_epass;// 注册密码
	private EditText et_euser;// 注册用户名
	private CheckBox cb_rember;// 记住密码
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_con;// 确定按钮
	private Button btn_cls;// 关闭按钮
	private String user, pass, ip, euser, epass, repass;
	public static String IP_NUMBER;
	private boolean True = false;// 密码长度
	// 正则表达式
	private Pattern pattern;
	private Matcher matcher;
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		initView();// 绑定
		// 设置点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// 记住密码
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
		}
		// 设置字符转换
		et_epass.setTransformationMethod(new TextChanger());
		et_pass.setTransformationMethod(new TextChanger());
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
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:07:37
	 */
	private void initView() {
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		re_login = (RelativeLayout) findViewById(R.id.re_login);
		re_reg = (RelativeLayout) findViewById(R.id.re_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		re_login.setVisibility(View.VISIBLE);
		re_reg.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			re_login.setVisibility(View.VISIBLE);
			re_reg.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// 注册确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.isEmpty()) {// 用户名为空
				DiyToast.showTaost(getApplicationContext(), "请输入用户名");
			} else if (epass.isEmpty()) {// 密码为空
				DiyToast.showTaost(getApplicationContext(), "请输入密码");
			} else if (repass.isEmpty()) {// 确认密码为空
				DiyToast.showTaost(getApplicationContext(), "请输入确认密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username =?",
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动\
					DiyToast.showTaost(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {
						oncheck_textsize();
						if (True) {
							// 检测输入字符是否为纯数字或者纯字母
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showTaost(getApplicationContext(),
										"密码格式为：数字+字母");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showTaost(getApplicationContext(),
										"注册成功");
								re_login.setVisibility(View.VISIBLE);
								re_reg.setVisibility(View.INVISIBLE);
							}
						} else {
							DiyToast.showTaost(getApplicationContext(),
									"密码长度不足六位");
						}
					} else {
						DiyToast.showTaost(getApplicationContext(), "两次输入密码不一致");
					}
				}
			}
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showTaost(getApplicationContext(), "用户名不能为空");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showTaost(getApplicationContext(), "密码不能为空");
			} else if (ip.isEmpty()) {// IP地址为空
				DiyToast.showTaost(getApplicationContext(), "IP地址不能为空");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (cursor.moveToNext()) {
					// IP地址
					IP_NUMBER = ip;
					// 跳转
					Intent intent = new Intent(getApplicationContext(),
							MenuActivity.class);
					startActivity(intent);
					finish();
					if (cb_rember.isChecked()) {
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("ip", ip).putString("user", user)
								.putString("pass", pass).commit();
					} else {
						sharedPreferences.edit().putBoolean("rember", false)
								.putString("ip", ip).putString("user", user)
								.putString("pass", pass).commit();
					}
				} else {
					DiyToast.showTaost(getApplicationContext(), "用户名或密码输入错误");
				}
			}
			break;
		case R.id.btn_reg:
			// 注册
			re_login.setVisibility(View.INVISIBLE);
			re_reg.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/*
	 * @方法名：oncheck_textsize
	 * 
	 * @功 能：监测文本长度
	 * 
	 * @时 间：上午8:20:40
	 */
	private void oncheck_textsize() {
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 3) {
					True = true;
				} else {
					True = false;
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
}
