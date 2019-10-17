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
 * @�ļ�����MainActivity.java
 * @��������ɵ�¼����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-8
 */
public class MainActivity extends Activity {
	private Button btn_login, btn_sqlite;
	private EditText et_ip;
	public static String ip_number;// String��IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_sqlite = (Button) findViewById(R.id.btn_sqlite);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// ��¼��ť���
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					// ��������
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
												getApplicationContext(), "���ӳɹ�");
										Intent intent = new Intent(
												MainActivity.this,
												BaseActivity.class);
										startActivity(intent);
										finish();
									} else {
										DiyToast.showToast(
												getApplicationContext(), "����ʧ��");
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
		// �鿴���ݿⰴť
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
