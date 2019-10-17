package com.example.shengsaicdemo10072018;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.shengsaicdemo10072018.mysql.MyDataBaseHelper;
import com.example.shengsaicdemo10072018.toast.DiyToast;

public class RegActivity extends Activity {
	private Button btn_login_con;// ��¼��ť
	private EditText et_repass, et_euser, et_epass;// IP�û��������ı���

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// String
	private String euser, epass, repass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();// ��
		// ��¼��ť���
		btn_login_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();// �û���
				epass = et_epass.getText().toString();// ����
				repass = et_repass.getText().toString();// ȷ������
				if (euser.isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (epass.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else if (repass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "������ȷ������");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// �½��α�
					if (cursor.moveToNext()) {// y�ƶ�
						// չʾ��ʾ��
						new AlertDialog.Builder(RegActivity.this)
								.setTitle("��ʾ")
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
									new String[] { euser, epass, "�û�" });
							// չʾ��ʾ��
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
													// method
													// stub
													finish();
												}
											}).show();
						} else {
							// չʾ��ʾ��
							new AlertDialog.Builder(RegActivity.this)
									.setTitle("��ʾ")
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

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:21:24
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login_con = (Button) findViewById(R.id.btn_reg_con);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
