package com.example.androidremberpass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("登录");
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 记住密码自动登录
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
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
					}
				}

			}
		});
	}
}