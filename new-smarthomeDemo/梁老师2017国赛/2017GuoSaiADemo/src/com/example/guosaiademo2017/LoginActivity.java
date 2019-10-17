package com.example.guosaiademo2017;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
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
 * @描述：完成登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private TextView tv_tips_text;// 提示
	private int number = 0;
	private TextView tv_time;// 时间
	private String user, pass, port, ip;// String数值
	public static String ip_number;// IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_tips_text = (TextView) findViewById(R.id.tv_tips_text);
		tv_time = (TextView) findViewById(R.id.tv_time);
		// 激活进程
		handler.post(timeRunnable);
		// 登录按钮
		btn_login.setOnClickListener(this);
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：加载时间、切换文本显示内容
	 * 
	 * @时 间：上午11:01:40
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_time.setText(sdf.format(new java.util.Date()));
			if (msg.what % 2 == 0) {
				tv_tips_text.setText("NM❤SL");
			} else {
				tv_tips_text.setText("蔡徐坤");
			}
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口号
			ip = et_ip.getText().toString();// IP地址
			if (user.equals("")) {
				DiyToast.showToast(LoginActivity.this, "请输入用户名");
			} else if (pass.equals("")) {
				DiyToast.showToast(LoginActivity.this, "请输入密码");
			} else if (ip.equals("")) {
				DiyToast.showToast(LoginActivity.this, "请输入IP地址");
			} else if (port.equals("")) {
				DiyToast.showToast(LoginActivity.this, "请输入端口号");
			} else {
				if (user.equals("bizideal") && pass.equals("123456")
						&& !ip.equals("") && !port.equals("")) {
					ip_number = et_ip.getText().toString();
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					DiyToast.showToast(LoginActivity.this, "用户名或密码输入错误");
				}
			}
			break;
		default:
			break;
		}
	}
}