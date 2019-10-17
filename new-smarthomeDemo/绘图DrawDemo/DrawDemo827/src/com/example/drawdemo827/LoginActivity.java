package com.example.drawdemo827;

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
 * @��������ɵ�¼ע���޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-27
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ����
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�����
	private LinearLayout line_updata_pass;// �޸��������
	// ��¼����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private EditText et_user, et_pass, et_ip;// �ı���
	private CheckBox cb_autologin;// �Զ���¼��ѡ��
	private CheckBox cb_rember;// ��ס���븴ѡ��
	private String user, pass, ip;

	// ע�����
	private Button btn_reg_con;// ע��ȷ����ť
	private Button btn_reg_cls;// ע��رհ�ť
	private EditText et_reg_user, et_reg_pass, et_reg_repass;// �ı���
	private String reg_user, reg_pass, reg_repass;

	// �޸��������
	private Button btn_updata_con;// �޸�����ȷ����ť
	private Button btn_updata_cls;// �޸�����رհ�ť
	private EditText et_updata_user, et_updata_old_pass, et_updata_new_pass;// �ı���
	private String updata_user, updata_old_pass, updata_new_pass;

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// sharedPreferences�洢
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
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_updata_new_pass = (EditText) findViewById(R.id.et_updata_new_pass);
		et_updata_old_pass = (EditText) findViewById(R.id.et_updata_old_pass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updatapass);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);

		// ���ý�����ʾ
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		line_updata_pass.setVisibility(View.GONE);

		// ��ס�����Զ���¼
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
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
						// �Զ���¼�ɹ�
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
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (ip.equals("")) {// ���IP��ַΪ��
				DiyToast.showToast(LoginActivity.this, "������IP��ַ");
			} else if (!cursor.moveToNext()) {// ������ݿ���ƥ����
				DiyToast.showToast(LoginActivity.this, "������û����������");
			} else {
				DiyToast.showToast(LoginActivity.this, "��¼�ɹ�");
				ip_number = et_ip.getText().toString();
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
			// ע��
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_cls:
			// ע��ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_con:
			// ע��ȷ��
			reg_pass = et_reg_pass.getText().toString();// ����
			reg_repass = et_reg_repass.getText().toString();// ȷ������
			reg_user = et_reg_user.getText().toString();// �û���
			Cursor cursor1 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { reg_user });// �½����ݿ�ָ��
			if (reg_user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (reg_pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (reg_repass.equals("")) {// ���ȷ������Ϊ��
				DiyToast.showToast(LoginActivity.this, "������ȷ������");
			} else {
				if (reg_pass.equals(reg_repass)) {// ��������ȷ������һ��
					if (cursor1.moveToNext()) {
						DiyToast.showToast(LoginActivity.this, "�û����Ѵ���");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { reg_user, reg_pass });// �������ݿ�
						DiyToast.showToast(LoginActivity.this, "ע��ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "�����������벻һ��");
				}
			}
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��
			updata_old_pass = et_updata_old_pass.getText().toString();// ������
			updata_user = et_user.getText().toString();// �û���
			updata_new_pass = et_updata_new_pass.getText().toString();// ������
			if (updata_user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (updata_old_pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(LoginActivity.this, "�����������");
			} else if (updata_new_pass.equals("")) {// ���������Ϊ��
				DiyToast.showToast(LoginActivity.this, "������������");
			} else {
				if (!updata_old_pass.equals(updata_new_pass)) {
					Cursor cursor2 = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// �½����ݿ�ָ��
					cursor2.moveToFirst();
					String get_passward = cursor2.getString(cursor2
							.getColumnIndex("passward"));
					if (get_passward.equals(updata_old_pass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_new_pass, updata_user });// �������ݿ�
						DiyToast.showToast(LoginActivity.this, "�����޸ĳɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
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
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
