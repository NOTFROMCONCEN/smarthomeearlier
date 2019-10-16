package com.example.drawdemo822zhuzhuangtu;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼ע���޸������ѯ���ݿ⹦��s
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-22
 */
public class LoginActivity extends Activity implements OnClickListener {

	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private Button btn_sqlite;// ��ѯ���ݿⰴť
	private Button btn_reg_con;// ע��ȷ����ť
	private Button btn_reg_cls;// ע��رհ�ť
	private Button btn_updata_con;// �޸�����ȷ����ť
	private Button btn_updata_cls;// �޸�����رչ���
	private Button btn_getsqlite_back;// ��ȡ���ݿⷵ�ذ�ť
	private Button btn_getsqlite_delete;// ���ݿ�ɾ��
	private Button btn_getsqlite_updata;// �������ݿ�
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	private String user, pass, repass, epass, euser, ip;// ��¼ע��String��ֵ
	private String updata_newpass, updata_oldpass, updata_user;// �޸�����String��ֵ
	private String get_user, get_pass;// �޸������ȡ���롢�û���
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private SharedPreferences sharedPreferences;// sharedPreferences�洢
	private EditText et_user, et_pass, et_ip, et_euser, et_epass, et_repass,
			et_updata_user, et_updata_oldpass, et_updata_newpass;// �ı���
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�����
	private LinearLayout line_updata_pass;// �޸��������
	private LinearLayout line_get_sqlite;// ��ѯ���ݿ����
	private ListView lv_1;// ListView
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		lv_1 = (ListView) findViewById(R.id.lv_get_sqlite);
		btn_sqlite = (Button) findViewById(R.id.btn_get_sqlite);
		btn_getsqlite_back = (Button) findViewById(R.id.btn_back);
		btn_getsqlite_delete = (Button) findViewById(R.id.btn_delete_sqlite);
		btn_getsqlite_updata = (Button) findViewById(R.id.btn_updata_sqlite);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		line_get_sqlite = (LinearLayout) findViewById(R.id.line_get_sqlite);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_user = (EditText) findViewById(R.id.et_user);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		cb_autologin = (CheckBox) findViewById(R.id.ck_autologin);
		cb_rember = (CheckBox) findViewById(R.id.ck_rember);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_getsqlite_back.setOnClickListener(this);
		btn_getsqlite_delete.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_getsqlite_updata.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		btn_sqlite.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// �Զ���¼��ס���빦��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false == true)) {
				cb_rember.setChecked(true);
				String getuser, getpass, getip;
				getip = sharedPreferences.getString("ip", null);
				getpass = sharedPreferences.getString("pass", null);
				getuser = sharedPreferences.getString("user", null);
				et_ip.setText(getip);
				et_user.setText(getuser);
				et_pass.setText(getpass);
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
			et_user.setText("");
			et_pass.setText("");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_back:
			// ���ݿ�ҳ�淵�ذ�ť
			line_login.setVisibility(View.VISIBLE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_cls:
			// ע��ҳ�淵�ذ�ť
			line_login.setVisibility(View.VISIBLE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_cls:
			// �޸�����ҳ�淵�ذ�ť
			line_login.setVisibility(View.VISIBLE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_reg:
			// ��ת��ע�����
			line_login.setVisibility(View.GONE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_pass:
			// ��ת���޸��������
			line_login.setVisibility(View.GONE);
			line_get_sqlite.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_get_sqlite:
			// ��ת����ѯ���ݿ����
			line_login.setVisibility(View.GONE);
			line_get_sqlite.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		// ��¼���湦��
		case R.id.btn_login:
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (pass.equals("")) {// ����Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (ip.equals("")) {// IP��ַΪ��
				DiyToast.showToast(LoginActivity.this, "������IP��ַ");
			} else if (!cursor.moveToNext()) {// ƥ�����ݿ����û�������
				DiyToast.showToast(LoginActivity.this, "�û����������������");
			} else {
				// ��¼
				ip_number = ip;
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					// ��⸴ѡ��״̬���뱾�ش洢
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
		// ע�Ṧ��
		case R.id.btn_reg_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.equals("")) {// �û���Ϊ��
				DiyToast.showToast(LoginActivity.this, "�������û���");
			} else if (epass.equals("")) {// ����Ϊ��
				DiyToast.showToast(LoginActivity.this, "����������");
			} else if (repass.equals("")) {// ȷ������
				DiyToast.showToast(LoginActivity.this, "������ȷ������");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor2 = db.rawQuery(
							"select * from user where username =?",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor2.moveToNext()) {
						DiyToast.showToast(this, "�û����Ѵ���");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						DiyToast.showToast(this, "ע��ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_get_sqlite.setVisibility(View.GONE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}
				} else {
					DiyToast.showToast(this, "�������벻һ��");
				}
			}
			break;
		// �޸�����
		case R.id.btn_updata_con:
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();// �û���
			if (updata_user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(this, "�û�������Ϊ��");
			} else if (updata_oldpass.equals("")) {// ���������Ϊ��
				DiyToast.showToast(this, "�����벻��Ϊ��");
			} else if (updata_newpass.equals("")) {// ���������Ϊ��
				DiyToast.showToast(this, "�����벻��Ϊ��");
			} else {
				Cursor cur_getupdata = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// �½����ݿ�ָ��
				cur_getupdata.moveToFirst();
				String getpass;
				getpass = cur_getupdata.getString(cur_getupdata
						.getColumnIndex("passward"));
				if (getpass.equals(updata_oldpass)) {
					if (updata_newpass.equals(updata_oldpass)) {
						DiyToast.showToast(this, "�¾����벻��һ��");
					} else {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// �������ݿ�
						DiyToast.showToast(this, "�����޸ĳɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_get_sqlite.setVisibility(View.GONE);
						line_reg.setVisibility(View.GONE);
						line_updata_pass.setVisibility(View.GONE);
					}
				} else {
					DiyToast.showToast(this, "�������������");
				}
			}

			break;
		case R.id.btn_delete_sqlite:
			// ɾ�����ݿ�
			db.execSQL("delete from user");
			break;
		case R.id.btn_updata_sqlite:
			// ˢ�����ݿ�
			lv_1.setAdapter(null);
			Cursor cursor4 = db.rawQuery("select * from user", null);
			if (cursor4.getCount() != 0) {
				cursor4.moveToFirst();
				SimpleCursorAdapter adapter = new SimpleCursorAdapter(
						LoginActivity.this, R.layout.activity_text, cursor4,
						new String[] { "username", "passward" }, new int[] {
								R.id.tv_user, R.id.tv_pass });
				lv_1.setAdapter(adapter);
			} else {
				Toast.makeText(LoginActivity.this, "���ݿ���û�м�¼��",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
}
