package com.example.drawdemo820shanxingtu;

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
 * @��������ɵ�¼��ע�ᡢ�޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-21
 */
public class LoginActivity extends Activity implements OnClickListener {
	private LinearLayout line_updata_pass;// �޸����벼��
	private LinearLayout line_reg;// ע�᲼��
	private LinearLayout line_login;// ��¼����
	private String euser, epass, repass, user, pass, ip, updata_user,
			updata_oldpass, updata_newpass;
	private EditText et_user, et_pass, et_ip, et_euser, et_epass, et_repass,
			et_updata_user, et_updata_newpass, et_updata_oldpass;// �ı���
	private Button btn_login, btn_reg, btn_updata_pass;// ��¼���水ť
	private Button btn_updata_con, btn_updata_cls;// �޸����밴ť
	private Button btn_con, btn_cls;// ע�ᰴť
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;
	private CheckBox cb_autologin;// �Զ���¼
	private CheckBox cb_rember;// ��ס����
	private SharedPreferences sharedPreferences;// SharedPreferences�洢
	static String ip_state;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// �Զ���¼��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			// ����洢��Ϊ��
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
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
						ip_state = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}).start();
			}
		} else {

		}
		// ���ý�������
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);
		// ��ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
	}

	/*
	 * @��������onClick(View v)
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����11:00:09
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��رհ�ť
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_con:
			// ע��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// �½����ݿ�ָ��
			if (euser.equals("")) {// �ж��û���Ϊ��
				Toast.makeText(LoginActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {// �ж�����Ϊ��
				Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {// �ж�ȷ������Ϊ��
				Toast.makeText(LoginActivity.this, "������ȷ������",
						Toast.LENGTH_SHORT).show();
			} else {
				if (epass.equals(repass)) {
					// ��������ȷ������һ��
					if (cursor.moveToNext()) {// ������ݿ����Ѿ����û���
						Toast.makeText(LoginActivity.this, "�û��Ѵ���",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						Toast.makeText(LoginActivity.this, "ע��ɹ�",
								Toast.LENGTH_SHORT).show();
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}

				} else {
					Toast.makeText(LoginActivity.this, "�����������벻һ��",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				Toast.makeText(LoginActivity.this, "�û�������Ϊ��",
						Toast.LENGTH_SHORT).show();
			} else if (pass.equals("")) {// �������Ϊ��
				Toast.makeText(LoginActivity.this, "���벻��Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {// ���IP��ַΪ��
				Toast.makeText(LoginActivity.this, "IP��ַ����Ϊ��",
						Toast.LENGTH_SHORT).show();
			} else if (!cursor2.moveToNext()) {// ������ݿ���û��ƥ����
				Toast.makeText(LoginActivity.this, "�û����������������",
						Toast.LENGTH_SHORT).show();
			} else {
				ip_state = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				}
			}
			break;
		case R.id.btn_reg:
			// ע����ת��ť
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_cls:
			// �޸�����رհ�ť
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			break;
		case R.id.btn_updata_pass:
			// �޸�������ת��ť
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
