package com.example.guosaicdemo905;

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
 * @描述：完成登录、时间显示、文字闪烁
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-4
 */
public class LoginActivity extends Activity {
	private TextView tv_tips_text;// 提示文字
	private TextView tv_login_time;// 时间
	private EditText et_user, et_port, et_ip, et_pass;// 文本框
	private String user, pass, port, ip, datetimevalue;
	private int number;
	static String ip_number;// IP地址
	private Button btn_login;// 登录按钮
	private SharedPreferences sharedPreferences;// sharedPreferences存储

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_tips_text = (TextView) findViewById(R.id.tv_tips_text);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		DiyToast.showToast(getApplicationContext(), "11");
		// 设置记住密码、用户名等参数
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		} else {
			et_ip.setText("");
			et_pass.setText("");
			et_port.setText("");
			et_user.setText("");
		}
		// 激活进程
		handler.post(timeRunnable);
		// 设置按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				port = et_port.getText().toString();// 端口
				ip = et_ip.getText().toString();// IP地址
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (port.isEmpty()) {// 端口号为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else {
					if (!ip.equals("") && !port.equals("")
							&& user.equals("bizideal") && pass.equals("123456")) {// 如果获取的数值满足要求
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port).commit();// 插入sharedPreferences存储
						// 设定IP地址
						ip_number = ip;
						// 跳转
						Intent intent = new Intent(LoginActivity.this,
								SeekBarActivity.class);
						startActivity(intent);
						finish();
					} else {// 不满足要求
						new AlertDialog.Builder(getApplicationContext())
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
										});
					}
				}
			}
		});
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间、文字闪烁
	 * 
	 * @时 间：上午8:29:44
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 文字闪烁
			if (msg.arg1 % 2 == 0) {
				tv_tips_text.setText("加载完毕，请登录......");
			} else {
				tv_tips_text.setText("");
			}
			// 更新时间
			SimpleDateFormat formater = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			datetimevalue = formater.format(new java.util.Date());
			tv_login_time.setText(datetimevalue);
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.arg1 = number;
			handler.sendMessage(msg);
		}
	};
}
