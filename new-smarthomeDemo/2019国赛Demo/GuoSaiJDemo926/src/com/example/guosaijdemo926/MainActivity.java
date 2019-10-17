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
 * @�ļ�����MainActivity.java
 * @��������¼����ת
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-29
 */
public class MainActivity extends Activity {
	private Button btn_login;// ��¼��ť
	private Button btn_sql;// ���ݿⰴť
	private EditText et_ip;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_sql = (Button) findViewById(R.id.btn_sql);
		et_ip = (EditText) findViewById(R.id.et_ip);
		// ���ݿ�
		btn_sql.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						SelectActivity.class);
				startActivity(intent);
			}
		});
		// ��¼
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					// ��������
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
												getApplicationContext(), "���ӳɹ�");
									} else {
										DiyToast.showToast(
												getApplicationContext(),
												"��������ʧ��");
										new AlertDialog.Builder(
												MainActivity.this)
												.setTitle("��������")
												.setMessage("��������ʧ�ܣ��Ƿ񷵻ص�¼���棿")
												.setPositiveButton(
														"ȷ��",
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
														"�˳�",
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
														"ǿ�н���",
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
