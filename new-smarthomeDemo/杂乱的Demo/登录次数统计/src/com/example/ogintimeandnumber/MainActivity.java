package com.example.ogintimeandnumber;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-29
 */
public class MainActivity extends Activity implements OnClickListener {
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_con;// ȷ����ť
	private Button btn_cls;// �رհ�ť
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_euser;// ע���û���
	private EditText et_epass;// ע������
	private TextView tv_time;// ʱ��
	private TextView tv_login_number;// ��¼����
	// String
	private String user, pass, euser, epass;
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// int
	private int login_number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("��¼");
		tv_login_number = (TextView) findViewById(R.id.tv_number_text);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_time = (TextView) findViewById(R.id.tv_time);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ��ȡ����
		Cursor cursor = db.rawQuery("select * from user_login_number", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			String get_number = cursor.getString(cursor
					.getColumnIndex("login_number"));
			String get_time = cursor.getString(cursor
					.getColumnIndex("login_time"));
			tv_login_number.setText("֮ǰ�й�" + get_number + "�ε�¼���ϴε�¼ʱ��Ϊ��"
					+ get_time);
		}
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// ���ý�����ʾ
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// ��ȡ��ǰʱ��
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_time.setText(sdf.format(new java.util.Date()));
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

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����7:48:21
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			setTitle("��¼");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			et_epass.setText("");
			et_euser.setText("");
			break;
		case R.id.btn_con:
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			if (euser.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(MainActivity.this, "�������û���");
			} else if (epass.equals("")) {// �������Ϊ��
				DiyToast.showToast(MainActivity.this, "����������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// �½����ݿ�
				if (cursor.moveToNext()) {
					DiyToast.showToast(MainActivity.this, "�û����Ѵ���");
				} else {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { euser, epass });// �������ݿ�
					DiyToast.showToast(MainActivity.this, "ע��ɹ�");
					et_epass.setText("");
					et_euser.setText("");
					line_login.setVisibility(View.VISIBLE);
					line_reg.setVisibility(View.GONE);
				}
			}
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward  =? ",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {
				DiyToast.showToast(MainActivity.this, "�������û���");
			} else if (pass.equals("")) {
				DiyToast.showToast(MainActivity.this, "����������");
			} else if (!cursor.moveToNext()) {
				DiyToast.showToast(MainActivity.this, "�û����������������");
			} else {

				Cursor cursor1 = db.rawQuery("select * from user_login_number",
						null);
				if (cursor1.getCount() != 0) {
					cursor1.moveToLast();
					String get_number = cursor1.getString(cursor1
							.getColumnIndex("login_number"));
					login_number = Integer.valueOf(get_number);
					login_number += 1;
				}
				db.execSQL(
						"insert into user_login_number (login_number,login_time)values(?,?)",
						new String[] { String.valueOf(login_number),
								tv_time.getText().toString() });
				DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
			}
			break;
		case R.id.btn_reg:
			setTitle("ע��");
			et_user.setText("");
			et_pass.setText("");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
