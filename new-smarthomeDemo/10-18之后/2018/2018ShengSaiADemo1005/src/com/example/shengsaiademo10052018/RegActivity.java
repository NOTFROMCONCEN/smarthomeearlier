package com.example.shengsaiademo10052018;

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

import com.example.shengsaiademo10052018.mysql.MyDataBaseHelper;
import com.example.shengsaiademo10052018.toast.DiyToast;

/*
 * @�ļ�����LoginActivity.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-5
 */
public class RegActivity extends Activity {
	private Button btn_con;// ע��
	private EditText et_repass, et_euser, et_epass;// IP�û�������
	private String repass, user, pass;// IP�û�������

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		// ȷ����ť
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_euser.getText().toString();// �û���
				pass = et_epass.getText().toString();// ����
				repass = et_repass.getText().toString();// ȷ������
				if (user.isEmpty()) {// IPΪ��
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (pass.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "ȷ�����벻��Ϊ��");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });// �½����ݿ��α�
					if (cursor.moveToNext()) {
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
						if (pass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,op)values(?,?,?)",
									new String[] { user, pass, "�û�" });
							DiyToast.showToast(getApplicationContext(), "ע�����");
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
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:50:44
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
	}
}
