package com.example.shengsaiddemo2017921;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼��ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-21
 */
public class LoginActivity extends Activity implements OnClickListener {

	// ������int��ֵ
	private int number = 0;
	// IP��ַ
	public static String IP_NUMBER;
	// ���ش洢
	SharedPreferences sharedPreferences;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// TextView
	private TextView tv_login_time;// ʱ��
	private TextView tv_login_tips;// ��ʾ
	// ��¼����
	private RelativeLayout line_login;
	private CheckBox cb_rember;// ��ס����
	private EditText et_ip;// IP��ַ�ı���
	private EditText et_port;// �˿ں��ı���
	private EditText et_user;// �û����ı���
	private EditText et_pass;// �����ı���
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private String user, pass, port, ip;// �û�������˿�IPString��ֵ
	// ע�����
	private RelativeLayout line_reg;
	private EditText et_euser;// �û����ı���
	private EditText et_epass;// �����ı���
	private EditText et_repass;// ȷ�������ı���
	private Button btn_con;// ע��ȷ��
	private String euser, epass, repass;// �����û���ȷ������String��ֵ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// �������
		handler.post(timeRunnable);
		// ��
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		line_login = (RelativeLayout) findViewById(R.id.line_login);
		line_reg = (RelativeLayout) findViewById(R.id.line_reg);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(),
				"smarthome.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_port.setText(sharedPreferences.getString("port", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// ����û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (epass.isEmpty()) {// �������Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (repass.isEmpty()) {// ���ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "������ȷ������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from userpass where User = ? ",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {
						db.execSQL(
								"insert into userpass (User,Pass)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.GONE);
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�����ȷ�����벻һ��");
					}
				}
			}
			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			port = et_port.getText().toString();// �˿ں�
			if (ip.isEmpty()) {// IP��ַΪ��
				DiyToast.showToast(getApplicationContext(), "IP��ַ����Ϊ��");
			} else if (port.isEmpty()) {// �˿ں�Ϊ��
				DiyToast.showToast(getApplicationContext(), "�˿ںŲ���Ϊ��");
			} else if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
			} else {
				Cursor cursor = db.rawQuery(
						"select* from userpass where User = ? and Pass = ?",
						new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					// ����IP
					IP_NUMBER = ip;
					// ��ת
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
					// ��ס����
					if (cb_rember.isChecked()) {
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port).commit();
					} else {
						sharedPreferences.edit().putBoolean("rember", false)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port).commit();
					}
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
	 * @�� �ܣ�����ʱ�䡢�ַ���˸
	 * 
	 * @ʱ �䣺����3:13:11
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy��MM��dd�� HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_login_time
					.setText(simpleDateFormat.format(new java.util.Date()));
			if (msg.what % 2 == 0) {
				tv_login_tips.setVisibility(View.VISIBLE);
			} else {
				tv_login_tips.setVisibility(View.INVISIBLE);
			}
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
