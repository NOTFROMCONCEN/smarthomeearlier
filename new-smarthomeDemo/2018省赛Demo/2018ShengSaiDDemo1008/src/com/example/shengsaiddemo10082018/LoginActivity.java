package com.example.shengsaiddemo10082018;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaiddemo10082018.fragment.BarActivity;
import com.example.shengsaiddemo10082018.mysql.MyDataBaseHelper;
import com.example.shengsaiddemo10082018.textchanger.TextChanger;
import com.example.shengsaiddemo10082018.toast.DiyToast;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private EditText et_user, et_pass, et_port, et_ip;
	private String user, pass, port, ip;
	public static String IP;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// ��
		getUserPass();// ��ס����
		// �����ַ�ת��
		et_pass.setTransformationMethod(new TextChanger());
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
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		et_user = (EditText) findViewById(R.id.et_user);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_reg:
			startActivity(new Intent(getApplicationContext(), RegActivity.class));
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			port = et_port.getText().toString();// �˿�
			ip = et_ip.getText().toString();// IP
			if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (port.isEmpty()) {// �˿ں�Ϊ��
				DiyToast.showToast(getApplicationContext(), "������˿ں�");
			} else if (ip.isEmpty()) {// IPΪ��
				DiyToast.showToast(getApplicationContext(), "������IP��ַ");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½��α�
				if (cursor.moveToNext()) {// �ƶ�
					// ��½�ɹ�
					IP = ip;
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					// ��ס����
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.putString("port", port).commit();
				} else {
					DiyToast.showToast(getApplicationContext(), "�û����������������");
				}
			}
			break;

		default:
			break;
		}
	}

	/*
	 * @��������getUserPass
	 * 
	 * @�� �ܣ���ס����
	 * 
	 * @ʱ �䣺����8:18:30
	 */
	private void getUserPass() {
		// TODO Auto-generated method stub
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
	}
}
