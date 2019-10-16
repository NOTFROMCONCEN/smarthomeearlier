package com.example.drawdemo816;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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
 * @���������û���ɵ�¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-16
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login, btn_reg, btn_con, btn_cls;// ��¼��ע�ᡢȷ�������ذ�ť
	private EditText et_user, et_pass, et_ip;// ��¼�����ı���
	private EditText et_euser, et_epass, et_repass;// ע������ı���
	private String user, pass, ip, euser, epass, repass;
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�᲼��
	private CheckBox cb_autologin;// �Զ���¼��ѡ��
	private CheckBox cb_rember;// ��ס���븴ѡ��
	private SharedPreferences sharedPreferences;// sharedPreferences���ش洢

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// �󶨸�ѡ��
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// �󶨲���
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// �󶨰�ť
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		// ���ı���
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// �Զ���¼��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {// �������sharedPreferences���ڲ���������
			if (sharedPreferences.getBoolean("rember", false) == true) {
				// ��ȡ��rmeber����booleanֵ���ж�״̬
				cb_rember.setChecked(true);
				String auto_username = sharedPreferences.getString("username",
						null);
				String auto_passward = sharedPreferences.getString("passward",
						null);
				String auto_ip = sharedPreferences.getString("ip", null);
				et_ip.setText(auto_ip);
				et_pass.setText(auto_passward);
				et_user.setText(auto_username);
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				// ��ȡ��autologin�� ��booleanֵ���ж�״̬
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);// �����Զ���¼����3��ֹͣʱ��
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						// ��ת����һ����
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
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
	 * @�� �ܣ���Ӧ��ť����¼�
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
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				Toast.makeText(LoginActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.equals("")) {// �������Ϊ��
				Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {// ���IP��ַΪ��
				Toast.makeText(LoginActivity.this, "������IP��ַ",
						Toast.LENGTH_SHORT).show();
			} else if (!cursor2.moveToNext()) {// ����û����������޷�ƥ�����ݿ�
				Toast.makeText(LoginActivity.this, "�û����������������",
						Toast.LENGTH_SHORT).show();
			} else {
				// ��ת����һ����
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				// �жϼ�ס������Զ���¼��ѡ���Ƿ񱻹�ѡ
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					// �Զ���¼����ס����
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {
					// �Զ���¼
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {
					// ��ס����
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else {
					// ȫ��δ��ѡ
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
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		// ȷ��
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// �½����ݿ�ָ��Ƚ��û���
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
				if (epass.equals(repass)) {// ��������ȷ������һ��
					if (cursor.moveToNext()) {
						Toast.makeText(LoginActivity.this, "�û����Ѵ���",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						// ��������ʱ�������ݿⲢ�ص���¼����
						line_reg.setVisibility(View.GONE);
						line_login.setVisibility(View.VISIBLE);
						Toast.makeText(LoginActivity.this, "ע��ɹ�",
								Toast.LENGTH_SHORT).show();
					}
				} else {// ��������������벻һ�¸�����ʾ
					Toast.makeText(LoginActivity.this, "�����������벻һ��",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		// �ر�
		case R.id.btn_cls:
			line_reg.setVisibility(View.GONE);
			line_login.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}