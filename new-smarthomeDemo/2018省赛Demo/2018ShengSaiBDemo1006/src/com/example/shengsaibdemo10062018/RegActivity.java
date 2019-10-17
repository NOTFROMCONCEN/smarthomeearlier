package com.example.shengsaibdemo10062018;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaibdemo10062018.mysql.MyDataBaseHelper;
import com.example.shengsaibdemo10062018.textchangerHelper.TextChanger;
import com.example.shengsaibdemo10062018.toast.DiyToast;

/*
 * @�ļ�����MainActivity.java
 * @������ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-6
 */
public class RegActivity extends Activity {
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_repass;// ȷ������
	private String user, pass, repass;
	private Button btn_login;// ȷ��

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();// ��
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				repass = et_repass.getText().toString();// ȷ������
				if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else if (repass.isEmpty()) {// ȷ������Ϊ��
					DiyToast.showToast(getApplicationContext(), "������ȷ������");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// �½����ݿ��α�
					if (cursor.moveToNext()) {// �ƶ�
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
					} else {
						if (pass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { user, pass });
							DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�����������벻һ��");
						}
					}
				}
			}
		});
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:15:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_con);
		et_pass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_euser);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}