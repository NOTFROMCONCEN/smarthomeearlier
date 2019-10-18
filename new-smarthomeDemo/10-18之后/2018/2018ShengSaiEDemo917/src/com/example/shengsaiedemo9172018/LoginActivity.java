package com.example.shengsaiedemo9172018;

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
 * @��������ɵ�¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-17
 */
public class LoginActivity extends Activity {
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// SharedPreferences�洢
	static SharedPreferences sharedPreferences;

	// ����ؼ�
	private Button btn_login;// ��¼��ť
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_ip;// IP��ַ
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	private String user, pass, ip;// �û��������롢IP��ַ
	// IP��ַ
	static String ip_number;
	static String login_op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��
		btn_login = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// ���ü�ס�����Զ���¼�߼�
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				// �Զ���¼�رա��򿪣�������ס���밴ť
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
				// ��ס����رա��ر��Զ���¼
				if (isChecked) {

				} else {
					if (cb_autologin.isChecked()) {
						cb_autologin.setChecked(false);
					}
				}
			}
		});
		// ��ס�����Զ���¼����ʵ��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// ��ס����
				cb_rember.setChecked(true);// ��ס���븴ѡ��
				et_ip.setText(sharedPreferences.getString("ip", null));// IP
				et_pass.setText(sharedPreferences.getString("pass", null));// ����
				et_user.setText(sharedPreferences.getString("user", null));// �û���
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// �Զ���¼
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] {
												sharedPreferences.getString(
														"user", null),
												sharedPreferences.getString(
														"pass", null) });// �½����ݿ��α�
						if (cursor.moveToNext()) {
							if (cb_autologin.isChecked()) {
								ip_number = sharedPreferences.getString("ip",
										null);
								if (sharedPreferences.getString("user", null)
										.toString().equals("bizideal")) {
									login_op = "����Ա";
								} else {
									login_op = "�û�";
								}
								Intent intent = new Intent(LoginActivity.this,
										BarActivity.class);
								startActivity(intent);
								finish();
							} else {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", "")
										.putString("pass", "")
										.putString("ip", "").commit();
							}
						} else {
							System.err.println("�˻��쳣");
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
							cb_autologin.setChecked(false);
							cb_rember.setChecked(false);
							et_ip.setText("");
							et_pass.setText("");
							et_user.setText("");
						}
					}
				}).start();
			}
		}
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				ip = et_ip.getText().toString();// IP��ַ
				if (ip.isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (user.isEmpty()) {// �û���Ϊ��\
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// �½����ݿ��α�
					if (cursor.moveToNext()) {// �α��ƶ�\
						DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
						ip_number = ip;
						if (user.equals("bizideal")) {
							login_op = "����Ա";
						} else {
							login_op = "�û�";
						}
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							// ��ס���롢�Զ���¼
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							// ��ס����
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
						DiyToast.showToast(getApplicationContext(),
								"�û��������벻��ȷ������������");
					}
				}
			}
		});
	}
}
