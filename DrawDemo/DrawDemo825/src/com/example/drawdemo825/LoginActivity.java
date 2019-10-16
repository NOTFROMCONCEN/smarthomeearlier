package com.example.drawdemo825;

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
 * @���������û����UI��ʾ����¼ע���޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-25
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ����
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�����
	private LinearLayout line_updata_pass;// �޸��������

	// ��¼����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata;// �޸����밴ť
	private EditText et_user, et_pass, et_ip;// �ı���
	private CheckBox cb_rember, cb_autologin;// ��ס�����Զ���¼
	private String user, pass, ip;

	// ע�����
	private Button btn_con, btn_cls;// ȷ�����رհ�ť
	private EditText et_euser, et_epass, et_repass;// �ı���
	private String euser, epass, repass;

	// �޸�����
	private Button btn_updata_con, btn_updata_cls;// ���޸�����ȷ�����رհ�ť
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// �ı���
	private String updata_user, updata_newpass, updata_oldpass;

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// �洢
	private SharedPreferences sharedPreferences;

	// IP��ַ
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata = (Button) findViewById(R.id.btn_updata);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
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
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		// ���ý�����ʾ����
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);
		// �Զ���¼��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
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
			et_user.setText("");
			et_pass.setText("");
			et_ip.setText("");
		}
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����8:28:49
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			Cursor cur_reg = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });
			if (euser.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (epass.equals("")) {
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (repass.equals("")) {
				DiyToast.showToast(LoginActivity.this, "������ȷ������");
			} else {
				if (epass.equals(repass)) {
					if (cur_reg.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "�û����Ѵ���s");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						DiyToast.showToast(LoginActivity.this, "ע�����");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
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
			ip = et_ip.getText().toString();// Ip��ַ
			Cursor cur_login = db.rawQuery(
					"select * from user where username  =? and passward = ?",
					new String[] { user, pass });
			if (user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (ip.equals("")) {// ���IP��ַΪ��
				DiyToast.showToast(LoginActivity.this, "������IP��ַ");
			} else if (!cur_login.moveToNext()) {
				DiyToast.showToast(LoginActivity.this, "�û����������������");
			} else {
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putBoolean("autologin", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();
				}
			}

			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata:
			// �޸�����
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��

			break;
		default:
			break;
		}
	}
}