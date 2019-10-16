package com.example.guosaibdemo2018915;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @�ļ�����MainActivity.java
 * @��������¼��ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-15
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_con;// ע��ȷ����ť

	static String ip_number;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// ע�����
	private EditText et_euser, et_epass, et_repass;// �û������롢ȷ������;
	private String euser, epass, repass;
	// ��¼����
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_port;// �˿ں�
	private EditText et_ip;// IP��ַ
	private String user, pass, port, ip;

	// ����
	private LinearLayout line_login;// ��¼
	private LinearLayout line_reg;// ע��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// �����ı���
		et_pass.setTransformationMethod(new TextChanger());
		// ע��ȷ����ť����¼�
		btn_con.setOnClickListener(this);
		// ��¼��ť����¼�
		btn_login.setOnClickListener(this);
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(this);
		// ����line����
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (epass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "������ȷ������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {// �¾�����һ��
						db.execSQL(
								"insert into user (username,passward,op)values(?,?,?)",
								new String[] { euser, epass, "�û�" });
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
					} else {
						DiyToast.showToast(getApplicationContext(), "�����������벻һ��");
					}
				}

			}
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			port = et_port.getText().toString();// �˿�
			ip = et_ip.getText().toString();// ip��ַ
			if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (port.isEmpty()) {// �˿�Ϊ��
				DiyToast.showToast(getApplicationContext(), "������˿ں�");
			} else if (ip.isEmpty()) {// IP��ַΪ��
				DiyToast.showToast(getApplicationContext(), "������IP��ַ");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
					ip_number = ip;
					Intent intent = new Intent(getApplicationContext(),
							ChoseActivity.class);
					startActivity(intent);
					finish();
				} else {
					DiyToast.showToast(getApplicationContext(), "�û����������������");
				}
			}
			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
