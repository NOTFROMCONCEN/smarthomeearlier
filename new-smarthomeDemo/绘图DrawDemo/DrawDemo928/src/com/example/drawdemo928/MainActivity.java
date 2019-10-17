package com.example.drawdemo928;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @�ļ�����MainActivity.java
 * @��������¼��ע�ᡢ�޸�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-28
 */
public class MainActivity extends Activity implements OnClickListener {
	// ����
	private LinearLayout line_login;// ��¼
	private LinearLayout line_reg;// ע��
	private LinearLayout line_updata_pass;// �޸�����
	// ��¼����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private Button btn_exit;// �˳���ť
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_ip;// IP��ַ
	static String user, pass, ip;
	// ע�����
	private Button btn_con, btn_cls;// ע��ȷ����ť���رհ�ť
	private EditText et_euser;// �û���
	private EditText et_epass;// ����
	private EditText et_repass;// ȷ������
	private String euser, epass, repass;
	// �޸��������
	private Button btn_updata_con;// �޸�����ȷ��
	private Button btn_updata_cls;// �޸�����ر�
	private EditText et_updata_user;// �û���
	private EditText et_updata_oldpass;// ����
	private EditText et_updata_newpass;// ������
	// ��ס����
	SharedPreferences sharedPreferences;
	// ���ݿ�
	SQLiteDatabase db;
	MyDataBaseHelper dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		initView();// ��
		// ��ס���빦��ʵ��
		if (sharedPreferences != null) {
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void initView() {
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_cls.setOnClickListener(this);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		line_updata_pass.setVisibility(View.INVISIBLE);
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ�����¼�
	 * 
	 * @ʱ �䣺����3:27:49
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_updata_pass:
			// �޸����밴ť
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ����ť
			SQLiteControl.UpdataPass(getApplicationContext(), et_updata_user
					.getText().toString(), et_updata_oldpass.getText()
					.toString(), et_updata_newpass.getText().toString());
			if (SQLiteControl.updata_state) {
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_updata_pass.setVisibility(View.INVISIBLE);
			}
			break;
		case R.id.btn_updata_cls:
			// �޸�����رհ�ť
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_login:
			// ��¼��ť
			SQLiteControl.SeleSQL(MainActivity.this, et_user.getText()
					.toString(), et_pass.getText().toString());
			if (SQLiteControl.login_state) {
				DiyToast.showTaost(getApplicationContext(), "��½�ɹ�");
				ip = et_ip.getText().toString();
				sharedPreferences.edit()
						.putString("user", et_user.getText().toString())
						.putString("pass", et_pass.getText().toString())
						.putString("ip", et_ip.getText().toString()).commit();
				Intent intent = new Intent(getApplicationContext(),
						UnLockActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.btn_reg:
			// ע�ᰴť
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_cls:
			// ע��رհ�ť
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// ע��ȷ����ť
			SQLiteControl.setSQL(getApplicationContext(), et_euser.getText()
					.toString(), et_epass.getText().toString(), et_repass
					.getText().toString());
			if (SQLiteControl.reg_state) {
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.INVISIBLE);
				line_updata_pass.setVisibility(View.INVISIBLE);
			}
			break;
		default:
			break;
		}
	}
}
