package com.example.sqlitedatahelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/*
 * @�ļ�����Activity_Login.java
 * @��������¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-21
 */
public class Activity_Login extends Activity implements OnClickListener {
	private Button btn_login, btn_reg;// ��ť
	private EditText et_user, et_pass, et_ip;// �ı���
	private String user, pass, ip;
	private CheckBox cb_autologin, cb_rember;// ��ѡ��
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getReadableDatabase();// ��ȡ���ݿ�
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			user = et_user.getText().toString();
			pass = et_pass.getText().toString();
			ip = et_ip.getText().toString();
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });
			if (user.equals("")) {// �û���
				DiyToast.showToast(Activity_Login.this, "�û�������Ϊ��");
			} else if (pass.equals("")) {// ����
				DiyToast.showToast(Activity_Login.this, "���벻��Ϊ��");
			} else if (ip.equals("")) {// IP��ַ
				DiyToast.showToast(Activity_Login.this, "IP��ַ����Ϊ��");
			} else if (!cursor.moveToNext()) {
				DiyToast.showToast(Activity_Login.this, "�û����������������");
			} else {
				DiyToast.showToast(Activity_Login.this, "��¼�ɹ�");
			}
			break;
		case R.id.btn_reg:
			Intent intent = new Intent(Activity_Login.this, Activity_Reg.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
}
