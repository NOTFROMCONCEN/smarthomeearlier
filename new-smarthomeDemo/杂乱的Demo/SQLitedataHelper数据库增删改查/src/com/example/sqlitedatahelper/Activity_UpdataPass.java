package com.example.sqlitedatahelper;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @�ļ�����Activity_Login.java
 * @�������޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-21
 */
public class Activity_UpdataPass extends Activity implements OnClickListener {
	private Button btn_updata_con, btn_updata_cls;// ��ť
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// �ı���
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;// ���ݿ�
	private String updata_user, updata_newpass, updata_oldpass, updata_getuser,
			updata_getpass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_updata_passward);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��ť����¼�
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_updata_cls:// �ر��޸�����
			finish();
			break;
		case R.id.btn_updata_con:
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();// �û���
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// �½����ݿ�
			cursor.moveToFirst();
			updata_getpass = cursor
					.getString(cursor.getColumnIndex("passward"));
			if (updata_user.equals("")) {// ������Ϊ��
				DiyToast.showToast(Activity_UpdataPass.this, "�������û���");
			} else if (updata_oldpass.equals("")) {// ������Ϊ��
				DiyToast.showToast(Activity_UpdataPass.this, "�����������");
			} else if (updata_newpass.equals("")) {// ������Ϊ��
				DiyToast.showToast(Activity_UpdataPass.this, "������������");
			} else {
				if (updata_oldpass.equals(updata_newpass)) {
					DiyToast.showToast(Activity_UpdataPass.this, "������;����벻��һ����");
				} else {
					if (updata_getpass.equals(updata_oldpass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });// �������ݿ�
						DiyToast.showToast(Activity_UpdataPass.this, "�����޸ĳɹ�");
					} else {
						DiyToast.showToast(Activity_UpdataPass.this, "�������������");
					}
				}
			}
			break;
		default:
			break;
		}
	}
}
