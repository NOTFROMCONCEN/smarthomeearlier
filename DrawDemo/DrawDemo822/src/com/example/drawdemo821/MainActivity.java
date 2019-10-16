package com.example.drawdemo821;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/*
 * @�ļ�����MainActivity.java
 * @���������û���ɵ�¼��ע�ᡢ�޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-20
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private Button btn_updata_pass;// �޸�����
	private Button btn_con;// ע��ȷ��
	private Button btn_cls;// ע��ر�
	private Button btn_updata_con;// �޸�����ȷ��
	private Button btn_updata_cls;// �޸�����ر�
	private EditText et_user, et_pass, et_ip;// ��¼����
	private EditText et_euser, et_epass, et_repass;// ע�����
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// �޸��������
	private String user, pass, ip, euser, epass, repass, updata_oldpass,
			updata_newpass, updata_user;// String��ֵ
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private LinearLayout line_updata_pass;// �޸����벼��
	private LinearLayout line_reg;// ע�᲼��
	private LinearLayout line_login;// ��¼�޸����벼��
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	private SharedPreferences sharedPreferences;// sharedPreferences�洢
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
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
		// ���þֲ�������ʾ˳��
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_updata_pass.setVisibility(View.INVISIBLE);
		// �Զ���¼��ס�������
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			// ����洢��Ϊ��
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				// �����ס���뱻����
				et_ip.setText(sharedPreferences.getString("ip", null));// IP��ַ
				et_user.setText(sharedPreferences.getString("username", null));// �û���
				et_pass.setText(sharedPreferences.getString("passward", null));// ����
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				// �����ס���뱻����
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
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}).start();
			}
		} else {
			// ����洢Ϊ�գ������ı���Ϊ��
			et_ip.setText("");
			et_pass.setText("");
			et_user.setText("");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������onClick(View v)
	 * 
	 * @�� �ܣ�������ť����¼�����Ӧ
	 * 
	 * @�� ����View v
	 * 
	 * @ʱ �䣺����7:58:06
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// �½����ݿ�ָ��
			if (euser.equals("")) {
				// ����û���Ϊ��
				Toast.makeText(MainActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {
				// �������Ϊ��
				Toast.makeText(MainActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {
				// ���ȷ������Ϊ��
				Toast.makeText(MainActivity.this, "������ȷ������", Toast.LENGTH_SHORT)
						.show();
			} else {
				if (epass.equals(repass)) {
					// ��������ȷ������һ��
					if (cursor.moveToNext()) {
						// ������ݿ��ں����û���
						Toast.makeText(MainActivity.this, "�û����Ѵ��ڣ�",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						// �û���������������ݿ�
						Toast.makeText(MainActivity.this, "ע��ɹ�",
								Toast.LENGTH_SHORT).show();
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
						line_updata_pass.setVisibility(View.INVISIBLE);
					}
				} else {
					// ��������ȷ�����벻һ��
					Toast.makeText(MainActivity.this, "�����������벻һ��",
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
			if (user.equals("")) {
				// �û���Ϊ��
				Toast.makeText(MainActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.equals("")) {
				// ����Ϊ��
				Toast.makeText(MainActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {
				// IP��ַΪ��
				Toast.makeText(MainActivity.this, "������IP��ַ", Toast.LENGTH_SHORT)
						.show();
			} else if (!cursor2.moveToNext()) {
				// ���ݿ��޷�ƥ��
				Toast.makeText(MainActivity.this, "�û����������������",
						Toast.LENGTH_SHORT).show();
			} else {
				// ��¼��ת
				ip_number = ip;
				Intent intent = new Intent(MainActivity.this, BarActivity.class);
				startActivity(intent);
				finish();
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					// �Զ���¼����ס���������ѡ
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_autologin.isChecked()) {
					// �Զ���¼����ѡ
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", true)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else if (cb_rember.isChecked()) {
					// ��ס���뱻��ѡ
					sharedPreferences.edit().putBoolean("rember", true)
							.putBoolean("autologin", false)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putBoolean("autologin", false)
							.putString("username", user)
							.putString("passward", pass).putString("ip", ip)
							.commit();
				}
				Toast.makeText(MainActivity.this, "��½�ɹ�", Toast.LENGTH_SHORT)
						.show();
			}
			break;
		case R.id.btn_reg:
			// ��תע��
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();// �û���
			if (updata_user.equals("")) {
				// ����û���Ϊ��
				Toast.makeText(MainActivity.this, "�û�������Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else if (updata_oldpass.equals("")) {
				// ���������Ϊ��
				Toast.makeText(MainActivity.this, "�����벻��Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else if (updata_newpass.equals("")) {
				// ���������Ϊ��
				Toast.makeText(MainActivity.this, "�����벻��Ϊ��", Toast.LENGTH_SHORT)
						.show();
			} else {
				Cursor cursor3 = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });
				cursor3.moveToFirst();
				String getusername = cursor3.getString(cursor3
						.getColumnIndex("username"));
				String getpassward = cursor3.getString(cursor3
						.getColumnIndex("passward"));
				if (updata_user.equals(getusername)) {
					if (getpassward.equals(updata_oldpass)) {
						if (!updata_newpass.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// �������ݿ�
							Toast.makeText(MainActivity.this, "�����޸ĳɹ�",
									Toast.LENGTH_SHORT).show();
							line_login.setVisibility(View.VISIBLE);
							line_reg.setVisibility(View.INVISIBLE);
							line_updata_pass.setVisibility(View.INVISIBLE);
						} else {
							Toast.makeText(MainActivity.this, "�¾����벻��һ�£�",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "�������������",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(MainActivity.this, "�û���������",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;
		case R.id.btn_updata_pass:
			// ��ת�޸�����
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
