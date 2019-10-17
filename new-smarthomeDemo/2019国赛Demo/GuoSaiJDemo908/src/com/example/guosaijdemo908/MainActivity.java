package com.example.guosaijdemo908;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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
 * @描述：完成登录、网络连接
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-8
 */
public class MainActivity extends Activity {
	private Button btn_login, btn_sqlite;
	private EditText et_ip;
	public static String ip_number;// String：IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_sqlite = (Button) findViewById(R.id.btn_sqlite);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// 登录按钮点击
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					// 网络连接
					ControlUtils.setUser("bizideal", "123456", et_ip.getText()
							.toString());
					SocketClient.getInstance().creatConnect();
					SocketClient.getInstance().login(new LoginCallback() {

						@Override
						public void onEvent(final String link_state) {
							// TODO Auto-generated method stub
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									if (link_state.equals("Success")) {
										DiyToast.showToast(
												getApplicationContext(), "连接成功");
										Intent intent = new Intent(
												MainActivity.this,
												BaseActivity.class);
										startActivity(intent);
										finish();
									} else {
										DiyToast.showToast(
												getApplicationContext(), "连接失败");
										Intent intent = new Intent(
												MainActivity.this,
												BaseActivity.class);
										startActivity(intent);
										finish();
									}
								}
							});
						}
					});
				}
			}
		});
		// 查看数据库按钮
		btn_sqlite.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this,
						GetSQLiteDataActivity.class);
				startActivity(intent);
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
