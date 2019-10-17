package com.example.drawdemo817;

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
import android.widget.LinearLayout;
import android.widget.Toast;

/*
 * @�ļ�����LoginActivity.java
 * @���������û���ʾ��¼\ע�����UI,��ɵ�½��ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-17
 */
public class LoginActivity extends Activity implements OnClickListener {
	private LinearLayout line_reg, line_login;// ע�᲼��
	private Button btn_login, btn_con, btn_cls, btn_reg;// ��ť
	private EditText et_user, et_pass, et_repass, et_ip, et_euser, et_epass;// �ı���
	private String user, pass, ip, euser, epass, repass;
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private SharedPreferences sharedPreferences;// SharedPreferences�洢
	public static String ip_number;// IP��ַ
	private CheckBox cb_autologin, cb_rember;// �Զ���¼����ס����

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// �Զ���¼����ס���빦�ܽ���
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {// ���sharedPreferences����
			if (sharedPreferences.getBoolean("rember", false) == true) {// �����ס���뱻����
				cb_rember.setChecked(true);// ���ù�ѡ
				et_ip.setText(sharedPreferences.getString("ip", null));// ��sharedPreferences��ȡ����ֵ
				et_pass.setText(sharedPreferences.getString("passward", null));
				et_user.setText(sharedPreferences.getString("username", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// ����Զ���¼������
				cb_autologin.setChecked(true); // ���ù�ѡ
				// �½��߳�
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						// ��ת����һ����
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
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
	 * @�� �ܣ�������ť�ĵ���¼�
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// ��¼
		case R.id.btn_login:
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				Toast.makeText(LoginActivity.this, "�û�������Ϊ��",
						Toast.LENGTH_SHORT).show();
			} else if (pass.equals("")) {// �������Ϊ��
				Toast.makeText(LoginActivity.this, "���벻��Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {// ���IP��ַΪ��
				Toast.makeText(LoginActivity.this, "IP��ַ", Toast.LENGTH_SHORT)
						.show();
			} else if (!cursor.moveToNext()) {// ������ݿ����޷�ƥ�䵽��ֵ
				Toast.makeText(LoginActivity.this, "�û����������������",
						Toast.LENGTH_SHORT).show();
			} else {// ����ȫ������
				// ��ת����һ����
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				ip_number = ip;
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {// �Զ���¼����ס����
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {// �Զ���¼
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {// ��ס����
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else {// ȫ��δ��ѡ
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				}
			}
			break;
		// ע��
		case R.id.btn_reg:
			line_reg.setVisibility(View.VISIBLE);
			line_login.setVisibility(View.GONE);
			break;
		// ȷ��
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.equals("")) {// ����û���Ϊ��
				Toast.makeText(LoginActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {// �������Ϊ��
				Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {// ���ȷ������Ϊ��
				Toast.makeText(LoginActivity.this, "������ȷ������",
						Toast.LENGTH_SHORT).show();
			} else {
				if (epass.equals(repass)) {
					// ����������������Ƿ�һ��
					Cursor cursor1 = db.rawQuery(
							"select * from user where username = ? ",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor1.moveToNext()) {
						// ƥ�����ݿ��Ƿ��Ѵ����û���
						Toast.makeText(LoginActivity.this, "�û����Ѵ��ڣ�",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						Toast.makeText(LoginActivity.this, "ע��ɹ�",
								Toast.LENGTH_SHORT).show();
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
					}
				} else {
					Toast.makeText(LoginActivity.this, "�����������벻һ��!",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		// �ر�
		case R.id.btn_cls:
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

}
