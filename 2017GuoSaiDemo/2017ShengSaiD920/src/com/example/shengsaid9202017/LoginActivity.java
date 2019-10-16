package com.example.shengsaid9202017;

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
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼��ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-20
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	int recLen = 0;
	static String ip_number;

	// ����
	private TextView tv_login_tips;// ��ʾ
	private TextView tv_login_time;// ʱ��
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_ip;// IP
	private EditText et_port;// �˿�
	private EditText et_euser;// ע���û���
	private EditText et_epass;// ע������
	private EditText et_repass;// ע��ȷ������
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_con;// ȷ����ť
	private CheckBox cb_rember;// ��ס����
	private RelativeLayout line_login;// ��¼
	private RelativeLayout line_reg;// ע��
	// sharedPreferences�洢
	SharedPreferences sharedPreferences;
	// String
	private String user, pass, port, ip, euser, epass, repass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "smarthome.db", null, 2);
		db = dbHelper.getWritableDatabase();
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
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_port.setText(sharedPreferences.getString("port", null));
			}
		}
		// �������
		handler.post(timeRunnable);
		// ��ť����¼�
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_con:
			// ȷ��
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
						"select * from userpass where User = ?",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {
						db.execSQL(
								"insert into userpass(User,Pass)values(?,?)",
								new String[] { euser, epass });// ����
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
					} else {
						DiyToast.showToast(getApplicationContext(), "�����������벻һ��");
					}
				}
			}
			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP
			port = et_port.getText().toString();// �˿�
			if (ip.isEmpty()) {// IP��ַΪ��
				DiyToast.showToast(getApplicationContext(), "������IP��ַ");
			} else if (port.isEmpty()) {// �˿�Ϊ��
				DiyToast.showToast(getApplicationContext(), "������˿�");
			} else if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from userpass where User = ? and Pass = ?",
						new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					// ��½�ɹ�
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
					ip_number = ip;
					if (cb_rember.isChecked()) {// ��ס����ѡ��
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port)
								.putBoolean("rember", true).commit();
					} else {
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port)
								.putBoolean("rember", false).commit();
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
	 * @�� �ܣ�����ʱ�䡢������˸
	 * 
	 * @ʱ �䣺����8:24:36
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
			recLen++;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			handler.sendMessage(msg);
		}
	};
}
