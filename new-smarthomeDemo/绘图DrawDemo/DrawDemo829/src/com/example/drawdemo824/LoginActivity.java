package com.example.drawdemo824;

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

/*
 * @�ļ�����LoginActivity.java
 * @���������û���ɵ�¼��ע�ᡢ�޸�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-23
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ��¼
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private EditText et_user, et_pass, et_ip;// �ı���
	// ע��
	private Button btn_con;// ע��ȷ����ť
	private Button btn_cls;// ע��رհ�ť
	private EditText et_euser, et_epass, et_repass;// �ı���

	// �޸�����
	private Button btn_updata_con;// �޸�����ȷ����ť
	private Button btn_updata_cls;// �޸�����رհ�ť
	private EditText et_updata_user, et_updata_newpass, et_updata_oldpass;// �ı���

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// IP��ַ
	static String ip_number;

	// String��ֵ
	private String user, pass, ip, euser, epass, repass, updata_user,
			updata_oldpass, updata_newpass;

	// ��ѡ��ť
	private CheckBox cb_rember, cb_autologin;

	// ����
	private LinearLayout line_login, line_reg, line_updata_pass;

	// �洢
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setTitle("��¼");
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
		// ��ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// �Զ���¼��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
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
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						// ��ת��¼
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			setTitle("��¼");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			Cursor cur_reg = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// �½����ݿ�ָ��
			if (euser.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(this, "�������û���");
			} else if (epass.equals("")) {// �������Ϊ��
				DiyToast.showToast(this, "����������");
			} else if (repass.equals("")) {// ���ȷ������Ϊ��
				DiyToast.showToast(this, "������ȷ������");
			} else {
				if (epass.equals(repass)) {
					if (cur_reg.moveToNext()) {
						DiyToast.showToast(this, "�û����Ѵ��ڣ�");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(this, "ע��ɹ�");
						setTitle("��¼");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
						line_updata_pass.setVisibility(View.INVISIBLE);
					}
				} else {
					DiyToast.showToast(this, "�����������벻һ��");
				}
			}
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(this, "�������û���");
			} else if (pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(this, "����������");
			} else if (ip.equals("")) {// ���IPΪ��
				DiyToast.showToast(this, "������IP��ַ");
			} else if (!cursor.moveToNext()) {// ������ݿ�δƥ��
				DiyToast.showToast(this, "�û����������������");
			} else {
				// ��ת��¼
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				// ��ס�����Զ���¼
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
			// ע��
			setTitle("ע��");
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			setTitle("��¼");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();
			if (updata_user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (updata_oldpass.equals("")) {// ���������Ϊ��
				DiyToast.showToast(LoginActivity.this, "�����������");
			} else if (updata_newpass.equals("")) {// ���������Ϊ��
				DiyToast.showToast(LoginActivity.this, "������������");
			} else {
				if (!updata_newpass.equals(updata_oldpass)) {

					Cursor cur_updata_user = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// �½����ݿ�ָ��
					cur_updata_user.moveToFirst();
					String get_passString = cur_updata_user
							.getString(cur_updata_user
									.getColumnIndex("passward"));
					if (get_passString.equals(updata_oldpass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// �������ݿ�
						DiyToast.showToast(LoginActivity.this, "���³ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
						line_updata_pass.setVisibility(View.INVISIBLE);
					} else {
						DiyToast.showToast(LoginActivity.this, "�������������");
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "�¾����벻��һ��");
				}
			}
			break;
		case R.id.btn_updata_pass:
			// �޸�����
			setTitle("�޸�����");
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
