package com.example.shengsaiddemo9162018;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @���������ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-16
 */
public class RegActivity extends Activity {
	private EditText et_euser;// �û���
	private EditText et_epass;// ����
	private EditText et_repass;// ȷ������
	private String euser, epass, repass;// String��ֵ
	private Button btn_con;// ȷ��
	private Button btn_cls;// �ر�

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// boolean
	boolean isTrue = false;

	// ������ʽ
	private Matcher matcher;
	private Pattern pattern;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// ��
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// �رհ�ť�¼�
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// ȷ����ť�¼�
		btn_con.setOnClickListener(new OnClickListener() {

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
					if (cursor.moveToNext()) {// �û��Ѵ��ڣ��α��ƶ���
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
					} else {
						if (epass.equals(repass)) {// �¾�����һ��
							oncheck();
							if (isTrue) {
								// ��������ַ��Ƿ�Ϊ�����ֻ��ߴ���ĸ
								pattern = Pattern
										.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
								matcher = pattern.matcher(et_epass.getText()
										.toString());
								if (!matcher.matches()) {
									DiyToast.showToast(getApplicationContext(),
											"�����ʽ����");
								} else {
									db.execSQL(
											"insert into user (username,passward)values(?,?)",
											new String[] { euser, epass });// �������ݿ�
									DiyToast.showToast(getApplicationContext(),
											"ע�����");
									finish();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"���벻��С����λ");
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"��֤���벻һ��");
						}
					}
				}
			}
		});
	}

	/*
	 * @��������oncheck
	 * 
	 * @�� �ܣ���������ַ�
	 * 
	 * @ʱ �䣺����3:18:45
	 */
	private void oncheck() {
		et_epass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (start > 4) {// ����4������������λʱ����
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}
}
