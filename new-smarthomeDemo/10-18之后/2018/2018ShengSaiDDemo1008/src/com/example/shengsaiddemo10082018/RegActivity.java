package com.example.shengsaiddemo10082018;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.shengsaiddemo10082018.mysql.MyDataBaseHelper;
import com.example.shengsaiddemo10082018.toast.DiyToast;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @������ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class RegActivity extends Activity implements
		android.view.View.OnClickListener {
	private Button btn_con;// ȷ��
	private Button btn_cls;// �ر�
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// ��������
	boolean isTrue = false;

	// ������ʽ
	Pattern pattern;
	Matcher matcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();// ��
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:13:24
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����8:33:26
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			finish();
			break;
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
			} else if (epass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
			} else if (repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "ȷ�����벻��Ϊ��");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// �½��α�
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {
						EditTextChanger();
						if (isTrue) {
							// ��������ַ��Ƿ�Ϊ�����ֻ��ߴ���ĸ
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"ע�������������ĸ�����ָ�ʽ");
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
									"���벻��6λ");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "��֤���벻һ��");
					}
				}
			}
			break;

		default:
			break;
		}
	}

	/*
	 * @��������EditTextChanger
	 * 
	 * @�� �ܣ��������ָı��Ƿ�����Ҫ��
	 * 
	 * @ʱ �䣺����8:33:06
	 */
	private void EditTextChanger() {
		// TODO Auto-generated method stub
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 4) {
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}
}
