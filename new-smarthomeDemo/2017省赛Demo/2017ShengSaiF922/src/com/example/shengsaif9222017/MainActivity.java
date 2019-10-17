package com.example.shengsaif9222017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：MainActivity.java
 * @描述：登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class MainActivity extends Activity {
	private EditText et_ip;// IP地址
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private Button btn_login;// 登录按钮
	static String IP_NUMBER;// IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip.setText("17.1.10.12");
		et_pass.setText("123456");
		et_user.setText("bizideal");
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub\
				if (et_ip.getText().toString().isEmpty()) {// IP地址为空
					DiyToast.showTaost(getApplicationContext(), "请输入IP地址");
				} else if (et_user.getText().toString().isEmpty()) {// 用户名为空
					DiyToast.showTaost(getApplicationContext(), "请输入用户名");
				} else if (et_pass.getText().toString().isEmpty()) {// 密码为空
					DiyToast.showTaost(getApplicationContext(), "请输入密码");
				} else {
					// 用户名、密码分别正确
					if (et_user.getText().toString().equals("bizideal")
							&& et_pass.getText().toString().equals("123456")) {
						IP_NUMBER = et_ip.getText().toString();// IP地址
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showTaost(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
