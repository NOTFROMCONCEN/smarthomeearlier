package com.example.guosaicdemo831;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.SocketHandler;

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
 * @描述：登录功能、时间显示、字体闪烁
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-31
 */
public class LoginActivity extends Activity {
	private TextView tv_tips_text, tv_time;
	private Button btn_login;
	private EditText et_user, et_port, et_ip, et_pass;
	private int number = 0;
	private String user, pass, ip, port;
	public static String ip_number;// IP地址
	private SharedPreferences sharedPreferences;// sharedPreferences存储

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_tips_text = (TextView) findViewById(R.id.tv_tips_text);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		// 自动登录记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_port.setText(sharedPreferences.getString("port", null));
			}
		}
		// 激活进程
		handler.post(timeRunnable);
		// 设置密码字符
		et_pass.setTransformationMethod(new TextChanger());
		// 登录按钮
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "请输入用户名");
				} else if (et_port.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "请输入端口号");
				} else if (et_ip.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "请输入IP地址");
				} else if (et_pass.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "请输入密码");
				} else {
					user = et_user.getText().toString();
					pass = et_pass.getText().toString();
					port = et_port.getText().toString();
					ip = et_ip.getText().toString();
					// 判断用户名密码是否正确
					if (user.equals("bizideal001") && pass.equals("123456")
							&& !port.equals("") && !ip.equals("")) {
						ip_number = ip;
						Intent intent = new Intent(LoginActivity.this,
								UnLockActivity.class);
						startActivity(intent);
						finish();
						// 插入sharedPreferences存储
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("ip", ip).putString("port", port)
								.putString("pass", pass)
								.putString("user", user).commit();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("登录失败")
								.setMessage("密码或用户名错误")
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
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：完成时间加载、文字闪烁
	 * 
	 * @时 间：上午8:38:22
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_time.setText(sdf.format(new java.util.Date()));
			if (msg.what % 2 == 0) {
				tv_tips_text.setText("");
			} else {
				tv_tips_text.setText("加载完毕，请登录......");
			}
			handler.postDelayed(timeRunnable, 1000);
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
}
