package com.example.shengsaib9192018;

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
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;// ��¼��ť
	private Button btn_exit;// �˳���ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updata_pass;// �޸����밴ť
	private EditText et_user, et_pass, et_port, et_ip;// �û�������IP�˿ں��ı���
	private String user, pass, port, ip;// String

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	// IP��ַ
	static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logoin);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ����¼�
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// �����ַ�ת��
		et_pass.setTransformationMethod(new TextChanger());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exit:
			System.exit(0);
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			port = et_port.getText().toString();// �˿�
			ip = et_ip.getText().toString();// IP
			if (port.isEmpty()) {// �˿�Ϊ��
				DiyToast.showToast(getApplicationContext(), "������˿ں�");
			} else if (ip.isEmpty()) {// IPΪ��
				DiyToast.showToast(getApplicationContext(), "������IP");
			} else if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������˺�");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else {
				Cursor condition = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½����ݿ��α�
				if (condition.moveToNext()) {// �α��ƶ�
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.putString("port", port).commit();
					ip_number = ip;
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("��¼ʧ��")
							.setMessage("������û�������")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				}
			}
			break;
		case R.id.btn_reg:
			// ע��
			Intent intent = new Intent(LoginActivity.this, RegActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_updata_pass:
			// �޸�����
			Intent intent1 = new Intent(LoginActivity.this,
					UpPassActivity.class);
			startActivity(intent1);
			break;

		default:
			break;
		}
	}
}
