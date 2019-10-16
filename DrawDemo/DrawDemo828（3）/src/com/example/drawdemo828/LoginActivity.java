package com.example.drawdemo828;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼ע���޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-28
 */
public class LoginActivity extends Activity {
	// ��¼
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private EditText et_user, et_pass, et_ip;// �ı���
	private String user, pass, ip;
	private CheckBox cb_rember, cb_autologin;

	// ע��
	private Button btn_reg_con;// ȷ����ť
	private Button btn_reg_cls;// �رհ�ť
	private EditText et_reg_user, et_reg_pass, et_reg_repass;// �ı���
	private String reg_user, reg_pass, reg_repass;

	// �޸�����
	private Button btn_updata_con;// ȷ��
	private Button btn_updata_cls;// �ر�
	private EditText et_updata_user, et_updata_newpass, et_updata_oldpass;
	private String updata_user, updata_newpass, updata_oldpass;

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
	}
}