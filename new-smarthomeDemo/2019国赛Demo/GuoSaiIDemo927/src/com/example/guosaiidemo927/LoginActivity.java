package com.example.guosaiidemo927;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
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
import android.widget.LinearLayout;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-27
 */
public class LoginActivity extends Activity implements OnClickListener {
	// IP��ַ
	public static String ip_number;
	// sharedPreferences�洢
	SharedPreferences sharedPreferences;
	// ����
	private LinearLayout line_login;// ��¼
	private LinearLayout line_reg;// ע��
	private LinearLayout line_updata_pass;// �޸�����

	// ��ť���ǡ�
	private Button btn_login;// ��¼��ť
	private Button btn_updata_pass;// �޸����밴ť
	private Button btn_exit;// �˳���ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_reg_con;// ע��ȷ��
	private Button btn_reg_cls;// ע��ر�
	private Button btn_updata_con;// �޸�����ȷ��
	private Button btn_updata_cls;// �޸�����ر�

	// ��¼����
	private EditText et_user;// �û���
	private EditText et_ip;// IP��ַ
	private EditText et_pass;// ����
	private String user, ip, pass;

	// ע�����
	private EditText et_reg_user;// �û���
	private EditText et_reg_pass;// ����
	private EditText et_reg_repass;// �ظ�����
	private String reg_user, reg_pass, reg_repass;

	// �޸��������
	private EditText et_updata_user;// �û���
	private EditText et_updata_newpass;// ������
	private EditText et_updata_oldpass;// ������
	private String updata_user, updata_oldpass, updata_newpass;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();// �󶨿ؼ�
		rember_pass();// ��ס����
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// �˳���ť����¼�
		btn_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);// �˳�
			}
		});
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.GONE);
				line_reg.setVisibility(View.VISIBLE);
				line_updata_pass.setVisibility(View.GONE);
			}
		});
		// �޸����밴ť����¼�
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.GONE);
				line_reg.setVisibility(View.GONE);
				line_updata_pass.setVisibility(View.VISIBLE);
			}
		});
		// ע��رհ�ť����¼�
		btn_reg_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.GONE);
				line_updata_pass.setVisibility(View.GONE);
			}
		});
		// �޸�����رհ�ť����¼�
		btn_updata_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.VISIBLE);
				line_reg.setVisibility(View.GONE);
				line_updata_pass.setVisibility(View.GONE);
			}
		});
	}

	/*
	 * @��������initView()
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����3:06:40
	 */
	private void initView() {
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_con);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// ��¼��ť
		btn_login.setOnClickListener(this);
		// ע��ȷ����ť
		btn_reg_con.setOnClickListener(this);
		// �޸�����ȷ����ť
		btn_updata_con.setOnClickListener(this);
	}

	/*
	 * @��������rember_pass
	 * 
	 * @�� �ܣ���ס����
	 * 
	 * @ʱ �䣺����3:09:49
	 */
	private void rember_pass() {
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stubs
		switch (v.getId()) {
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			ip = et_ip.getText().toString();// IP��ַ
			pass = et_pass.getText().toString();// ����
			if (ip.isEmpty()) {// ���IPΪ��
				DiyToast.showToast(getApplicationContext(), "IP��ַ����Ϊ��");
			} else if (user.isEmpty()) {// ����û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
			} else if (pass.isEmpty()) {// �������Ϊ��
				DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username =? and passward = ?",
								new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					ip_number = ip;
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("��¼ʧ��")
							.setMessage("�û����������������")
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
		case R.id.btn_con:
			// ע��ȷ��
			reg_pass = et_reg_pass.getText().toString();// ����
			reg_repass = et_reg_repass.getText().toString();// ȷ������
			reg_user = et_reg_user.getText().toString();// �û���
			if (reg_user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
			} else if (reg_pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
			} else if (reg_repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "ȷ�����벻��Ϊ��");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { reg_user });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("ע��ʧ��")
							.setMessage("�û����Ѵ���")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				} else {
					if (reg_repass.equals(reg_pass)) {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { reg_user, reg_pass });
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ɹ�")
								.setMessage("�û�ע��ɹ�")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												line_login
														.setVisibility(View.VISIBLE);
												line_reg.setVisibility(View.GONE);
												line_updata_pass
														.setVisibility(View.GONE);
											}
										}).show();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ʧ��")
								.setMessage("�ظ����벻һ��")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					}
				}
			}
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();// �û���
			if (updata_user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (updata_oldpass.isEmpty()) {// ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "�����������");
			} else if (updata_newpass.isEmpty()) {// ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "������������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// �½����ݿ��α�
				if (cursor.moveToNext()) {
					if (updata_newpass.equals(updata_oldpass)) {// �¾�����һ��
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸�ʧ��")
								.setMessage("�¾����벻��һ��")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					} else {
						if (cursor.getString(cursor.getColumnIndex("passward"))
								.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("�޸ĳɹ�")
									.setMessage("�����޸ĳɹ�")
									.setPositiveButton(
											"ȷ��",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub
													line_login
															.setVisibility(View.VISIBLE);
													line_reg.setVisibility(View.GONE);
													line_updata_pass
															.setVisibility(View.GONE);
												}
											}).show();
						} else {
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("�޸�ʧ��")
									.setMessage("���������")
									.setPositiveButton(
											"ȷ��",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method
													// stub

												}
											}).show();
						}
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("�޸�ʧ��")
							.setMessage("�޸�ʧ�ܣ��û���������")
							.setPositiveButton("ȷ��",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method
											// stub

										}
									}).show();
				}
			}

			break;

		default:
			break;
		}
	}
}
