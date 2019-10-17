package com.example.drawdemo815;

import java.util.Currency;

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
import android.widget.Toast;

/*
 * @�ļ�����LoginActivity.java
 * @���������û���ɵ�¼�Լ�ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-15
 */
public class LoginActivity extends Activity implements OnClickListener {
	/**
	 * ����ؼ�
	 */
	private EditText et_user, et_pass, et_ip;// �ı���-�û�����ip��ַ������
	private Button btn_login, btn_reg;// ��ť-��¼��ע��
	private CheckBox cb_autologin, cb_rember;// ��ѡ��-�Զ���¼����ס����
	private String user, pass, ip;// String��ֵ
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private SharedPreferences sharedPreferences;// sharedPreferences�洢
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		/**
		 * �󶨿ؼ�
		 */
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);// ���ݿ�
		db = dbHelper.getReadableDatabase();// ���ݿ�
		// ��¼��ע�ᰴť�ĵ���¼�
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// �Զ���¼����ס�����¼�
		sharedPreferences = getSharedPreferences("rember_autologin",
				MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				// �ж�boolean�Ƿ�Ϊtrue����ɼ�ס����
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				// �ж�boolean�Ƿ�Ϊtrue������Զ���¼
				cb_autologin.setChecked(true);
				// �½�����
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);// ����3���ӳ�
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						ip_number = et_ip.getText().toString();// ����IP��ַ
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);// ��ת����һ����
						startActivity(intent);
					}
				}).start();
			}
		} else {
			et_ip.setText("");
			et_pass.setText("");
			et_user.setText("");
		}
	}

	/*
	 * @��������onClick(View v)
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @�� ����View v
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// ��¼
		case R.id.btn_login:
			user = et_user.getText().toString();// ��ֵ-�û���
			pass = et_pass.getText().toString();// ��ֵ-����
			ip = et_ip.getText().toString();// ��ֵ-IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��Ƚ��û���������
			if (user.isEmpty()) {
				// �ж��û����Ƿ�Ϊ��
				Toast.makeText(LoginActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.isEmpty()) {
				// �ж������Ƿ�Ϊ��
				Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.isEmpty()) {
				// �ж�IP��ַ�Ƿ�Ϊ��
				Toast.makeText(LoginActivity.this, "������IP��ַ",
						Toast.LENGTH_SHORT).show();
			} else if (!cursor.moveToNext()) {
				// �ж�������û����������Ƿ�ƥ��
				Toast.makeText(LoginActivity.this, "�û����������������",
						Toast.LENGTH_SHORT).show();
			} else {
				// �ж��Զ���¼�ͼ�ס�����Ƿ񱻹�ѡ
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();// ��дsharedPreferences
					ip_number = ip;// ����IP��ַ
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// ��ת����һ����
					startActivity(intent);
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();// ��дsharedPreferences
					ip_number = ip;// ����IP��ַ
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// ��ת����һ����
					startActivity(intent);
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();// ��дsharedPreferences
					ip_number = ip;// ����IP��ַ
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// ��ת����һ����
					startActivity(intent);
				} else {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();// ��дsharedPreferences
					ip_number = ip;// ����IP��ַ
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);// ��ת����һ����
					startActivity(intent);
				}
			}
			break;
		// ע��
		case R.id.btn_reg:
			// ��ת����¼����
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
