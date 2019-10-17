package com.example.shengsaicdemo916;

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
 * @��������ɵ�¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-16
 */
public class LoginActivity extends Activity {
	private Button btn_login_con;// ��¼��ť
	private EditText et_user;// �û���
	private EditText et_ip;// Ip��ַ
	private EditText et_pass;// ����
	private String ip, user, pass;// �û�����IP�������String��ֵ
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼

	// Ip��ַ
	static String ip_number;
	// Ȩ��
	static String login_op;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences�洢
	static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// ��
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login_con = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		// ��ס�����Զ���¼
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {// �Զ���¼
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(0);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (sharedPreferences.getString("user", null).equals(
								"bizideal")) {
							login_op = "����Ա";
						} else {
							login_op = "�û�";
						}
						ip_number = sharedPreferences.getString("ip", null);
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}).start();

			}
			if (sharedPreferences.getBoolean("rember", false) == true) {// ��ס����
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// ���õ�¼��ť����¼�
		btn_login_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				ip = et_ip.getText().toString();// IP
				if (ip.isEmpty()) {// ���IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (user.isEmpty()) {// ����û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {// �������Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// �½����ݿ��α�
					if (cursor.moveToNext()) {// ����α��ƶ�
						DiyToast.showToast(getApplicationContext(), "��¼�ɹ�");
						// ��ת
						ip_number = ip;
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						// �Զ���¼��ס����
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									// �Զ���¼true
									.putBoolean("rember", true)
									// ��ס����true
									.putString("user", user)// �û���
									.putString("pass", pass)// ����
									.putString("ip", ip).commit();// Ip��ַ
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									// �Զ���¼false
									.putBoolean("rember", true)
									// ��ס����true
									.putString("user", user)// �û���
									.putString("pass", pass)// ����
									.putString("ip", ip).commit();// Ip��ַ
						} else if (cb_autologin.isChecked()) {
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									// �Զ���¼true
									.putBoolean("rember", true)
									// ��ס����true
									.putString("user", user)// �û���
									.putString("pass", pass)// ����
									.putString("ip", ip).commit();// Ip��ַ
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									// �Զ���¼false
									.putBoolean("rember", false)
									// ��ס����false
									.putString("user", user)// �û���
									.putString("pass", pass)// ����
									.putString("ip", ip).commit();// Ip��ַ
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û������������벻��ȷ������������");
					}
				}
			}
		});
	}
}