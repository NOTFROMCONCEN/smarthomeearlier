package com.example.guosaigdemo911;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-11
 */
public class LoginActivity extends Activity {
	private Button btn_login;// ��¼��ť
	private EditText et_user, et_pass, et_ip;// �ı���
	private CheckBox cb_rember, cb_autologin;// ��ס�����Զ���¼�ı���
	private String user, pass, ip;
	static SharedPreferences sharedPreferences;
	public static String ip_number;

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// ��ס�����Զ���¼
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(100);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							// ��ת
							ip_number = et_ip.getText().toString();
							Intent intent = new Intent(LoginActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", null)
									.putString("pass", null)
									.putString("ip", null).commit();

						}
					}
				}).start();
			}
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				ip = et_ip.getText().toString();
				if (ip.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "����������");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });
					if (cursor.moveToNext()) {
						// ��½�ɹ���ת
						// ��ת
						ip_number = ip;
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						// ����洢
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_autologin.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					} else {
						if (user.equals("bizideal") && !pass.equals("123456")) {
							DiyToast.showToast(getApplicationContext(),
									"�����������");
						} else if (!user.equals("bizideal")
								&& pass.equals("123456")) {
							DiyToast.showToast(getApplicationContext(),
									"�û����������");
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�û����������������");
						}
					}
				}
			}
		});
	}
}
