package com.example.drawdemo901;

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
 * @��������ɵ�¼ע���޸�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-1
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ����
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ��¼����
	private LinearLayout line_updata_pass;// ��¼����
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// sharedPreferences�洢
	private SharedPreferences sharedPreferences;

	// ��¼
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private EditText et_user, et_pass, et_ip;// �û��������롢IP��ַ
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	private String user, pass, ip;
	// ע��
	private Button btn_con;// ȷ����ť
	private Button btn_cls;// �رհ�ť
	private EditText et_euser, et_epass, et_repass;// �û��������롢ȷ������
	private String euser, epass, repass;
	// �޸�����
	private Button btn_updata_con;// �޸�����ȷ��
	private Button btn_updata_cls;// �޸�����ر�
	private EditText et_updata_user, et_updata_newpass, et_updata_oldpass;// �û����������롢������
	private String updata_user, updata_oldpass, updata_newpass;
	// IP��ַ
	public static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("��¼");
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
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
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// ���ý���
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);
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
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							ip_number = et_ip.getText().toString();
							Intent intent = new Intent(LoginActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "0")
									.putString("pass", "0").putString("ip", ip)
									.commit();
						}
					}
				}).start();
			}
		} else {
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
		}
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ���
	 * 
	 * @ʱ �䣺����8:04:35
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			setTitle("��¼");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			et_euser.setText("");
			et_repass.setText("");
			et_epass.setText("");
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// �޸�����
			if (euser.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (epass.equals("")) {// �������Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (repass.equals("")) {// ���ȷ������Ϊ��
				DiyToast.showToast(LoginActivity.this, "������ȷ������");
			} else {
				if (epass.equals(repass)) {// ��������ȷ������һ��
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "�û����Ѵ���");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(LoginActivity.this, "ע��ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
						setTitle("��¼");
						et_euser.setText("");
						et_repass.setText("");
						et_epass.setText("");
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "�����������벻һ��");
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
			if (user.equals("")) {// �û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (pass.equals("")) {// ����Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (ip.equals("")) {// IP��ַΪ��
				DiyToast.showToast(LoginActivity.this, "������IP��ַ");
			} else if (!cursor.moveToNext()) {
				DiyToast.showToast(LoginActivity.this, "�û����������������");
			} else {
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				DiyToast.showToast(LoginActivity.this, "��¼�ɹ�");
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
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			setTitle("��¼");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			et_updata_newpass.setText("");
			et_updata_oldpass.setText("");
			et_updata_user.setText("");
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();// �û���
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// �½����ݿ�ָ��
			if (updata_user.equals("")) {// �û���
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (updata_oldpass.equals("")) {// ������
				DiyToast.showToast(LoginActivity.this, "�����������");
			} else if (updata_newpass.equals("")) {// ������
				DiyToast.showToast(LoginActivity.this, "������������");
			} else if (!cursor2.moveToNext()) {
				DiyToast.showToast(LoginActivity.this, "�û���������");
			} else {
				if (updata_newpass.equals(updata_oldpass)) {
					DiyToast.showToast(LoginActivity.this, "�¾����벻��һ��");
				} else {
					if (updata_oldpass.equals(cursor2.getString(cursor2
							.getColumnIndex("passward")))) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// �������ݿ�
						DiyToast.showToast(LoginActivity.this, "�����޸ĳɹ�");
						setTitle("��¼");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
						et_updata_newpass.setText("");
						et_updata_oldpass.setText("");
						et_updata_user.setText("");
					} else {
						DiyToast.showToast(LoginActivity.this, "�������������");
					}
				}
			}
			break;
		case R.id.btn_updata_pass:
			// �޸�����
			setTitle("�޸�����");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
			break;
		default:
			break;
		}
	}
}