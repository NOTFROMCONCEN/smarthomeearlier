package com.example.shengsaiademo2018915;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @�ļ�����RegActivity.java
 * @���������ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-15
 */
public class RegActivity extends Activity {
	private Button btn_reg;// ע��ȷ����ť
	private EditText et_euser, et_epass, et_repass;// �û��������롢ȷ�������ı���
	private String user, pass, repass;// STring��ֵ�û��������롢ȷ������
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		btn_reg = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// ע��ȷ����ť
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_euser.getText().toString();// �û���
				pass = et_epass.getText().toString();// ����
				repass = et_repass.getText().toString();// ȷ������
				if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "ȷ�����벻��Ϊ��");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// �½����ݿ��α�
					if (cursor.moveToNext()) {// ������ݿ��α��ƶ�
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("��ʾ")
								.setMessage("���û��Ѵ���")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					} else {
						if (pass.equals(repass)) {// ������������һ��
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { user, pass, "�û�" });
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
}
