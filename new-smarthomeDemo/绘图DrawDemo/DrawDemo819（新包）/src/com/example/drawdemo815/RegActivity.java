package com.example.drawdemo815;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * @�ļ�����RegActivity.java
 * @���������û����ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-15
 */
public class RegActivity extends Activity implements OnClickListener {
	/**
	 * ����ؼ�
	 */
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private EditText et_euser, et_epass, et_repass;// �ı���-�û��������롢ȷ������
	private String euser, epass, repass;// String��ֵ
	private Button btn_con, btn_cls;// ��ť-ȷ�����ر�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		/**
		 * �󶨿ؼ�
		 */
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// ���ð�ť�ĵ���¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
	}

	/*
	 * @��������onClick(View v)
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @�� ����View v
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// �رհ�ť
			finish();
			break;
		case R.id.btn_con:
			// ȷ����ť
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			// �ж���ֵ�Ƿ�Ϊ��
			if (euser.isEmpty()) {// ����û���Ϊ��
				Toast.makeText(RegActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.isEmpty()) {// �������Ϊ��
				Toast.makeText(RegActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.isEmpty()) {// ���ȷ������Ϊ��
				Toast.makeText(RegActivity.this, "������ȷ������", Toast.LENGTH_SHORT)
						.show();
			} else {
				if (epass.equals(repass)) {// ��������ȷ������һ��
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor.moveToNext()) {// ������ݿ��ڴ����û���
						Toast.makeText(RegActivity.this, "�û����Ѵ���",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						finish();
						Toast.makeText(RegActivity.this, "ע�����",
								Toast.LENGTH_SHORT).show();
					}
				} else {// ��������ȷ�����벻һ��
					Toast.makeText(RegActivity.this, "�����������벻һ��",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break;
		}
	}

}
