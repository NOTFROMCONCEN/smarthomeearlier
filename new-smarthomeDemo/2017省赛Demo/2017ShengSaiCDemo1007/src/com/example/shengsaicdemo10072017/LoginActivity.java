package com.example.shengsaicdemo10072017;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.example.shengsaicdemo10072017.fragment.BarActivity;
import com.example.shengsaicdemo10072017.mysql.MyDataBaseHelper;
import com.example.shengsaicdemo10072017.timeget.TimeGet;
import com.example.shengsaicdemo10072017.toast.DiyToast;

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
 * @��������¼��ע�ᡢ��ס����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
 */
public class LoginActivity extends Activity implements OnClickListener {
	private LinearLayout line_login, line_reg;
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private Button btn_con;// ȷ��
	private Button btn_cls;// �ر�
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_port;// �˿ں�
	private EditText et_ip;// IP��ַ
	private EditText et_euser;// ע���û���
	private EditText et_epass;// ע������
	private EditText et_repass;// ע��ȷ������
	private TextView tv_time;// ʱ��
	String user, pass, port, ip;
	String euser, epass, repass;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logion);
		initView();// �󶨿ؼ�
		// ���ó�������ʱ����View������
		line_login.setVisibility(View.VISIBLE);// ��ʾ��½
		line_reg.setVisibility(View.GONE);// ����ע��
		// ��ס����
		if (sharedPreferences != null) {
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}

	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����3:46:13
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		tv_time = (TextView) findViewById(R.id.tv_login_time);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// �������
		handler.post(timeRunnable);
		TimeGet.startThread();
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����3:48:15
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			port = et_port.getText().toString();// �˿�
			ip = et_ip.getText().toString();// IP��ַ
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
								new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �ƶ�
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.putString("port", port).commit();
				} else {
					DiyToast.showToast(getApplicationContext(), "�û����������������");
				}
			}
			break;
		case R.id.btn_cls:
			// ע��رհ�ť
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			setNull();// ���
			break;
		case R.id.btn_reg:
			// ע�ᰴť
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			setNull();// ���
			break;
		case R.id.btn_con:
			// ע��ȷ����ť
			euser = et_euser.getText().toString();// ע���û���
			epass = et_epass.getText().toString();// ע������
			repass = et_repass.getText().toString();// ע��ȷ������
			if (euser.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (epass.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (repass.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "������ȷ������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �ƶ�
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {// ����һ��
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
						setNull();// ���
					} else {
						DiyToast.showToast(getApplicationContext(), "�����������벻һ��");
					}
				}
			}
			break;
		default:
			break;
		}
	}

	/*
	 * @��������setNull
	 * 
	 * @�� �ܣ���ť�л�����ʱ���ı������
	 * 
	 * @ʱ �䣺����3:55:20
	 */
	private void setNull() {
		// TODO Auto-generated method stub
		et_epass.setText("");
		et_euser.setText("");
		et_ip.setText("");
		et_pass.setText("");
		et_port.setText("");
		et_repass.setText("");
		et_user.setText("");
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����4:03:20
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_time.setText(TimeGet.TIME);
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
