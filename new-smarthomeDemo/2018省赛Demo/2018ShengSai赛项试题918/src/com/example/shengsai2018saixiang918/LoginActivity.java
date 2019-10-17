package com.example.shengsai2018saixiang918;

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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-18
 */
public class LoginActivity extends Activity {
	private Button btn_login;// ��¼��ť
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_ip;// IP��ַ
	private String user, pass, ip;// STring:�û��������롢IP
	public static String ip_number, login_op;// IP��ַ
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼

	// sharedPreferences�洢
	static SharedPreferences sharedPreferences;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login_button);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// �����߼�
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					cb_autologin.setChecked(false);
				}
			}
		});
		// ��ס�����Զ���¼����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("user", null));
				et_user.setText(sharedPreferences.getString("pass", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				// ֱ����ת
				if (sharedPreferences.getString("user", null)
						.equals("bizideal")) {
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
		}
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				ip = et_ip.getText().toString();// IP��ַ
				pass = et_pass.getText().toString();// ����
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
									new String[] { user, pass });// �½����ݿ��α�
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
						cursor.close();
						if (user.equals("bizideal")) {
							login_op = "����Ա";
						} else {
							login_op = "��Ա";
						}
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
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
						ip_number = ip;
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û��������������������������");
						cursor.close();// �ر�
					}
				}
			}
		});
	}
}
