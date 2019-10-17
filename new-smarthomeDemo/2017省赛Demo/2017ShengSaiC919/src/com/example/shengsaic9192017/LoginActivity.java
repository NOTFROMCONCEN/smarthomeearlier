package com.example.shengsaic9192017;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼��ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_port;// �˿ں�
	private EditText et_ip;// IP��ַ
	private String user, pass, port, ip;// String
	private TextView tv_login_tips, tv_login_time;// ��ʾ��ʱ��
	SharedPreferences sharedPreferences;// sharedPreferences�洢
	private Button btn_con;// ȷ��
	private Button btn_cls;// ȡ��
	private EditText et_euser;// �û���
	private EditText et_epass;// ����
	private EditText et_repass;// ȷ������
	private String euser, epass, repass;// String
	int number = 0;
	// IP��ַ
	static String ip_number;
	// ����
	private LinearLayout line_login;
	private LinearLayout line_reg;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// ��ť����¼�
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		// ���ò���
		line_reg.setVisibility(View.GONE);
		line_login.setVisibility(View.VISIBLE);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		et_pass.setTransformationMethod(new TextChanger());
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			line_reg.setVisibility(View.GONE);
			line_login.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û���Ϊ��");
			} else if (epass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����Ϊ��");
			} else if (repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "ȷ������Ϊ��");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
						line_reg.setVisibility(View.GONE);
						line_login.setVisibility(View.VISIBLE);
					} else {
						DiyToast.showToast(getApplicationContext(), "�����������벻һ��");
					}
				}
			}
			break;
		case R.id.btn_reg:
			line_reg.setVisibility(View.VISIBLE);
			line_login.setVisibility(View.GONE);
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			port = et_port.getText().toString();// �˿ں�
			ip = et_ip.getText().toString();// Ip��ַ
			if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
			} else if (port.isEmpty()) {// �˿�Ϊ��
				DiyToast.showToast(getApplicationContext(), "�˿ڲ���Ϊ��");
			} else if (ip.isEmpty()) {// IPΪ��
				DiyToast.showToast(getApplicationContext(), "IP��ַ����Ϊ��");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					sharedPreferences.edit().putString("ip", ip)
							.putString("pass", pass).putString("port", port)
							.putString("user", user).commit();
					ip_number = ip;
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);
					startActivity(intent);
					finish();
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
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����3:09:10
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what % 2 == 0) {
				tv_login_tips.setVisibility(View.INVISIBLE);
			} else {
				tv_login_tips.setVisibility(View.VISIBLE);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy��MM��dd�� HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_login_time
					.setText(simpleDateFormat.format(new java.util.Date()));
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}
