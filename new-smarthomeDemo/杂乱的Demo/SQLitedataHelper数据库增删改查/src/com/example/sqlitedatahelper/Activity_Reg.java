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
import android.widget.Toast;

/*
 * @�ļ�����Activity_Login.java
 * @������ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-21
 */
public class Activity_Reg extends Activity implements OnClickListener {
	private Button btn_con, btn_cls;// ��ť
	private String euser, epass, repass;
	private EditText et_euser, et_epass, et_repass;// �ı���
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			Cursor cursor = db.rawQuery(
					"select * from user where username = ?",
					new String[] { euser });// �½����ݿ�ָ��
			if (euser.equals("")) {
				DiyToast.showToast(Activity_Reg.this, "�������û���");
			} else if (epass.equals("")) {
				DiyToast.showToast(Activity_Reg.this, "����������");
			} else if (repass.equals("")) {
				DiyToast.showToast(Activity_Reg.this, "������ȷ������");
			} else {
				if (epass.equals(repass)) {
					if (cursor.moveToNext()) {
						DiyToast.showToast(Activity_Reg.this, "�û����Ѵ���");
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						DiyToast.showToast(Activity_Reg.this, "ע��ɹ�");
					}
				} else {
					DiyToast.showToast(Activity_Reg.this, "�����������벻һ�£�");
				}
			}
			break;
		case R.id.btn_cls:
			Intent intent = new Intent(Activity_Reg.this, Activity_Login.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
}