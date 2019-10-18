package com.example.guosaijdemo926;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：MainActivity.java
 * @描述：登录、跳转
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-29
 */
public class MainActivity extends Activity {
	private Button btn_login;// 登录按钮
	private Button btn_sql;// 数据库按钮
	private EditText et_ip;// IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_sql = (Button) findViewById(R.id.btn_sql);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// 数据库
		btn_sql.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SelectActivity.class);
				startActivity(intent);
			}
		});
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					// 网络连接
					ControlUtils.setUser("bizideal", "123456", et_ip.getText()
							.toString());
					SocketClient.getInstance().creatConnect();
					SocketClient.getInstance().login(new LoginCallback() {

						@Override
						public void onEvent(final String web_state) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (web_state.equals(ConstantUtil.SUCCESS)) {
										DiyToast.showToast(
												getApplicationContext(), "连接成功");
									} else {
										DiyToast.showToast(
												getApplicationContext(),
												"网络连接失败");
										new AlertDialog.Builder(
												MainActivity.this)
												.setTitle("网络连接")
												.setMessage("网络连接失败，是否返回登录界面？")
												.setPositiveButton(
														"确定",
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO
																// Auto-generated
																// method stub

															}
														})
												.setNegativeButton(
														"退出",
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO
																// Auto-generated
																// method stub
																System.exit(0);
															}
														})
												.setNeutralButton(
														"强行进入",
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO
																// Auto-generated
																// method stub
																Intent intent = new Intent(
																		MainActivity.this,
																		BaseActivity.class);
																startActivity(intent);
																finish();
															}
														}).show();
									}
								}
							});
						}
					});
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
