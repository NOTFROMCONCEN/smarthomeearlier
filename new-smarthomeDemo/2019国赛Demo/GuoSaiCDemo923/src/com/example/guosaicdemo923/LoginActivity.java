package com.example.guosaicdemo923;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
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
 * @描述：登录、时间、文字闪烁
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-23
 */
public class LoginActivity extends Activity {
	private TextView tv_login_time;// 时间
	private TextView tv_login_tips;// 提示
	private EditText et_user;// 用户名
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private EditText et_pass;// 密码
	private Button btn_login;// 登录按钮
	private int recLen = 0;
	public static String get_time;
	private String user, pass, port;// 用户名密码IP端口号String类型数值
	public static String IP_NUMBER;// IP地址
	// sharedPreferences存储
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		// 激活进程
		handler.post(timeRunnable);
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				port = et_port.getText().toString();// 端口号
				IP_NUMBER = et_ip.getText().toString();// IP地址
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (port.isEmpty()) {// 端口号为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else if (IP_NUMBER.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					if (user.equals("bizideal") && pass.equals("123456")) {
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass)
								.putString("ip", IP_NUMBER)
								.putString("port", port).commit();// 插入本地存储
						IP_NUMBER = et_ip.getText().toString();// 获取IP
						Intent intent = new Intent(getApplicationContext(),
								UnLockActivity.class);// 跳转
						startActivity(intent);
						finish();
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
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间、闪烁字
	 * 
	 * @时 间：上午8:23:10
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 闪烁
			if (msg.arg1 % 2 == 0) {
				tv_login_tips.setVisibility(View.INVISIBLE);
			} else {
				tv_login_tips.setVisibility(View.VISIBLE);
			}
			// 时间
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			get_time = simpleDateFormat.format(new java.util.Date());
			tv_login_time.setText(get_time);
			handler.postDelayed(timeRunnable, 500);
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
