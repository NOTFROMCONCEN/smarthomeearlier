package com.example.shengsaiedemo9172018;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @�ļ�����RegActivity.java
 * @������ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-17
 */
public class RegActivity extends Activity {
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// ����
	private Button btn_reg;// ע�ᰴť
	private EditText et_euser;// �û���
	private EditText et_epass;// ����
	private EditText et_repass;// ȷ������
	private String euser, epass, repass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��
		btn_reg = (Button) findViewById(R.id.btn_reg_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
					if (cursor.moveToNext()) {
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("ע��ʧ��")
								.setMessage("�û����Ѵ���")
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
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { euser, epass, "�û�" });// �������ݿ�
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("ע��ɹ�")
									.setMessage("�û�ע��ɹ�")
									.setPositiveButton(
											"ȷ��",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub
													Intent intent = new Intent(
															RegActivity.this,
															LoginActivity.class);
													startActivity(intent);
													finish();
												}
											}).show();
						} else {
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("ע��ʧ��")
									.setMessage("�����������벻һ��")
									.setPositiveButton(
											"ȷ��",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub

												}
											}).show();
						}
					}
				}
			}
		});
	}
}
