package com.example.shengsaiademo2018915;

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
 * @��������ɵ�¼��ע����ת
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-15
 */
public class LoginActivity extends Activity {
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private EditText et_user;// �û���
	private EditText et_ip;// IP
	private EditText et_pass;// ����
	private String user, pass, ip;// �û��������롢IPSTring��ֵ
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	public static String ip_number;// IP��ַ

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
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// �Զ���¼��ס���빦��
		sharedPreferences = getSharedPreferences("rember_autologin",
				MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				// �½��Զ���¼����s
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);// 3���ӳ�
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {// ����ѡ��״̬
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {// ��ȡ��
							// ����Զ���¼����ס����δ��ѡ��
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
							cb_autologin.setChecked(false);
							cb_rember.setChecked(false);
						}
					}
				}).start();
			}
			// ��ס����
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
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				ip = et_ip.getText().toString();// Ip��ַ
				if (ip.isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(),
							"ip����ȷ����������ȷ��ip");
				} else if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					Cursor condition = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// �½����ݿ��α�
					if (condition.moveToNext()) {// �α��ƶ�
						// ��ת
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
						DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							// ����Զ���¼����ס���뱻ѡ��
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_autologin.isChecked()) {
							// ����Զ���¼��ѡ��
							sharedPreferences.edit()
									.putBoolean("autologin", true)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();

						} else if (cb_rember.isChecked()) {
							// �����ס���뱻ѡ��
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							// ����Զ���¼����ס����δ��ѡ��
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û��������������������������");
					}
				}
			}
		});
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						RegActivity.class);
				startActivity(intent);
			}
		});
	}
}
