package com.example.shengsaiademo2017918;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-18
 */
public class LoginActivity extends Activity {
	private Button btn_login;// 登录按钮
	private TextView tv_login_tips;// 提示
	private TextView tv_login_time;// 时间
	private int recLen = 0;// 闪烁
	private String user, pass, port, ip;// String
	private EditText et_user, et_pass, et_port, et_ip;
	// sharedPreferences存储
	SharedPreferences sharedPreferences;
	// IP地址
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		et_pass.setTransformationMethod(new TextChanger());
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (port.isEmpty()) {// 端口号为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					if (user.equals("bizideal") && pass.equals("123456")) {
						// 跳转
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port).commit();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("登录失败")
								.setMessage("用户名或密码输入错误")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					}
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载时间
	 * 
	 * @时 间：下午3:12:59
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (recLen % 2 == 0) {
				tv_login_tips.setVisibility(View.VISIBLE);
			} else {
				tv_login_tips.setVisibility(View.INVISIBLE);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_login_time
					.setText(simpleDateFormat.format(new java.util.Date()));
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen++;
			Message msg = handler.obtainMessage();
			msg.arg1 = recLen;
			handler.sendMessage(msg);
		}
	};
}
