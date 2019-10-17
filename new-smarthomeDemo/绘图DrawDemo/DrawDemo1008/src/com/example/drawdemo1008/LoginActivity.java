package com.example.drawdemo1008;

import java.util.regex.Pattern;

import java.util.regex.Matcher;

import com.example.drawdemo1008.fargment.BarActivity;
import com.example.drawdemo1008.mysql.MyDataBaseHelper;
import com.example.drawdemo1008.textchanger.TextChanger;
import com.example.drawdemo1008.toast.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-8
 */
public class LoginActivity extends Activity implements OnClickListener {
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// 正则表达式
	Matcher matcher;
	Pattern pattern;

	// 布局
	private LinearLayout line_login;// 登录
	private LinearLayout line_reg;// 注册

	// 登录界面
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private EditText et_user, et_pass, et_ip;
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录
	private String user, pass, ip;
	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	// 注册界面
	private Button btn_con;// 确定
	private Button btn_cls;// 关闭
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	// IP地址
	public static String IP_NUMBER;

	// 密码个数
	private boolean isTrue = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();// 绑定
		// 记住密码自动登录功能
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// 记住密码
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// 自动登录
				cb_autologin.setChecked(true);
				DiyToast.showToast(getApplicationContext(), "自动登录3秒后进行，请稍安勿躁");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e);
						}
						if (cb_autologin.isChecked()) {
							IP_NUMBER = sharedPreferences.getString("ip", null);
							startActivity(new Intent(getApplicationContext(),
									BarActivity.class));
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false).commit();
						}
					}
				}).start();
			}
		}
		// 文本转换为‘*’
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：下午7:03:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭按钮
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			setNull();
			break;
		case R.id.btn_con:
			// 注册确定按钮
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
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {
						editChanger();
						if (isTrue) {
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"密码格式错误：数字+字母组合");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showToast(getApplicationContext(),
										"注册成功");
								setNull();
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.GONE);
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"密码不足6位");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "验证密码不一致！");
					}
				}
			}
			break;
		case R.id.btn_login:
			// 登录按钮
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
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
								new String[] { user, pass });// 新建游标
				if (cursor.moveToNext()) {
					// 登陆成功
					IP_NUMBER = ip;
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
					// 记住密码、自动登录
					if (cb_autologin.isChecked() && cb_rember.isChecked()) {// 记住密码、自动登录
						sharedPreferences.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.commit();
					} else if (cb_rember.isChecked()) {// 记住密码
						sharedPreferences.edit().putBoolean("autologin", false)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.commit();
					} else {// 全部没有
						sharedPreferences.edit().putBoolean("autologin", false)
								.putBoolean("rember", false)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.commit();
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
				}
			}
			break;
		case R.id.btn_reg:
			// 注册按钮
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			setNull();
			break;
		default:
			break;
		}
	}

	/*
	 * @方法名：setNull
	 * 
	 * @功 能：界面切换清空所有EditText内容
	 * 
	 * @时 间：下午7:32:06
	 */
	private void setNull() {
		// TODO Auto-generated method stub
		et_epass.setText("");
		et_euser.setText("");
		et_ip.setText("");
		et_pass.setText("");
		et_repass.setText("");
		et_user.setText("");
	}

	/*
	 * @方法名：editChanger
	 * 
	 * @功 能：监听输入内容大小
	 * 
	 * @返回值：isTrue
	 * 
	 * @时 间：下午7:25:33
	 */
	private void editChanger() {
		// TODO Auto-generated method stub
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 4) {// 最后输入大于4,（从0算，大于5但不等于5，即大于6，即不小于（但等于） 6 ）
					isTrue = true;
				} else {
					isTrue = false;
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
