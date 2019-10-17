package com.example.shengsaiedemo20181009;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.shengsaiedemo20181009.mysql.MyDataBaseHelper;
import com.example.shengsaiedemo20181009.toast.DiyToast;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-9
 */
public class RegActivity extends Activity {
	private Button btn_con;// ��¼��ť
	private EditText et_euser, et_repass, et_epass;// �ı���
	private String euser, repass, epass;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();
		// ��¼
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();// �û���
				epass = et_epass.getText().toString();// ����
				repass = et_repass.getText().toString();// IP
				if (euser.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "����������");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "������ȷ������");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ? ",
							new String[] { euser });// �½��α�
					if (cursor.moveToNext()) {// �ƶ�
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { euser, epass, "�û�" });
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("��ʾ")
									.setMessage("ע��ɹ�")
									.setPositiveButton(
											"ȷ��",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													startActivity(new Intent(
															getApplicationContext(),
															LoginActivity.class));
													finish();
												}
											}).show();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�����������벻һ��");
						}
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_con = (Button) findViewById(R.id.btn_reg_con);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
