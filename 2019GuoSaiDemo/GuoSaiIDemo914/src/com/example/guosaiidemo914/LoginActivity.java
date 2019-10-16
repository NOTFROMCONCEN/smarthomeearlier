package com.example.guosaiidemo914;

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
import android.widget.LinearLayout;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼��ע�ᡢ�޸�����ȹ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-14
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ����
	private LinearLayout line_login;
	private LinearLayout line_reg;
	private LinearLayout line_updata_pass;
	// ��¼�ؼ�
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_exit;// �˳���ť
	private Button btn_updtaa_pass;// �޸����밴ť
	private String user, pass, ip;// String �û��������롢IP
	private EditText et_ip, et_user, et_pass;// �ı���
	public static String ip_number;// IP��ַ

	// ע��ؼ�
	private Button btn_con;// ע��ȷ��
	private Button btn_cls;// ע���˳�
	private EditText et_reg_user, et_reg_pass, et_reg_repass;// �ı���
	private String euser, epass, repass;// String �û��������롢ȷ������

	// �޸�����ؼ�
	private Button btn_updata_con;// ע��ȷ��
	private Button btn_updata_cls;// ע���˳�
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// �ı���
	private String updata_newpass, updata_oldpass, updata_user;// String
																// �û����������룬������

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// sharedPreferences�洢
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();

	}

	/*
	 * @��������initView
	 * 
	 * @���ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:34:21
	 */
	private void initView() {
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updtaa_pass = (Button) findViewById(R.id.btn_updata_pass);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_repass = (EditText) findViewById(R.id.et_reg_repass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_updata_oldpass = (EditText) findViewById(R.id.et_updata_oldpass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_updata_newpass = (EditText) findViewById(R.id.et_updata_newpass);
		et_user = (EditText) findViewById(R.id.et_user);

		// ���õ���¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updtaa_pass.setOnClickListener(this);
		// ���ò��ֲ���
		line_login.setVisibility(View.VISIBLE);// ��ʾ��¼
		line_reg.setVisibility(View.INVISIBLE);// ����ע��
		line_updata_pass.setVisibility(View.INVISIBLE);// �����޸�����
		// ��ס���빦��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_user.setText(sharedPreferences.getString("user", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
		}
		// ���������
		et_pass.setTransformationMethod(new TextChanger());
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			line_login.setVisibility(View.VISIBLE);// ��ʾ��¼
			line_reg.setVisibility(View.INVISIBLE);// ����ע��
			line_updata_pass.setVisibility(View.INVISIBLE);// �����޸�����
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_reg_user.getText().toString();// �û���
			epass = et_reg_pass.getText().toString();// ����
			repass = et_reg_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (epass.isEmpty()) { // ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "������ȷ������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("ע��ʧ��")
							.setMessage("�û����Ѵ��ڣ�������ע��")
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
					if (epass.equals(repass)) {// ���롢ȷ������һ��
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// �������ݿ�
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ɹ�")
								.setMessage("�û�ע��ɹ�")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												line_login
														.setVisibility(View.VISIBLE);// ��ʾ��¼
												line_reg.setVisibility(View.INVISIBLE);// ����ע��
												line_updata_pass
														.setVisibility(View.INVISIBLE);// �����޸�����
											}
										}).show();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ʧ��")
								.setMessage("�ظ����벻һ��")
								.setPositiveButton("Ok",
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
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
			} else if (ip.isEmpty()) {// IPΪ��
				DiyToast.showToast(getApplicationContext(), "IP��ַ����Ϊ��");
			} else {
				Cursor condition = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½����ݿ��α�
				if (!condition.moveToNext()) {// ����α�δ�ƶ�����ƥ���
					DiyToast.showToast(getApplicationContext(), "�û����������������");
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("��¼ʧ��")
							.setMessage("�û������������")
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
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
					ip_number = ip;
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
				}
			}
			break;
		case R.id.btn_exit:
			// �˳�
			System.exit(0);
			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.INVISIBLE);// ���ص�¼
			line_reg.setVisibility(View.VISIBLE);// ��ʾע��
			line_updata_pass.setVisibility(View.INVISIBLE);// �����޸�����
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
				Cursor curso = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// �½����ݿ��α�
				if (curso.moveToNext()) {// ���ݿ���ƥ����
					if (curso.getString(curso.getColumnIndex("passward"))
							.toString().equals(updata_oldpass)) {
						if (updata_newpass.equals(updata_oldpass)) {
							DiyToast.showToast(getApplicationContext(),
									"�¾����벻��һ��");
						} else {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// ����
							new AlertDialog.Builder(LoginActivity.this)
									.setTitle("�޸ĳɹ�")
									.setMessage("�����޸ĳɹ�")
									.setPositiveButton(
											"Ok",
											new DialogInterface.OnClickListener() {

												@Override
												public void onClick(
														DialogInterface dialog,
														int which) {
													// TODO Auto-generated
													// method stub
													// �޸�����ر�
													line_login
															.setVisibility(View.VISIBLE);// ��ʾ��¼
													line_reg.setVisibility(View.INVISIBLE);// ����ע��
													line_updata_pass
															.setVisibility(View.INVISIBLE);// �����޸�����
												}
											}).show();
						}
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸�ʧ��")
								.setMessage("�������������")
								.setPositiveButton("Ok",
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
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("�޸�ʧ��")
							.setMessage("�޸�ʧ�ܣ�������û�������")
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
		case R.id.btn_updata_pass:
			// �޸�����
			line_login.setVisibility(View.INVISIBLE);// ���ص�¼
			line_reg.setVisibility(View.INVISIBLE);// ����ע��
			line_updata_pass.setVisibility(View.VISIBLE);// ��ʾ�޸�����
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			line_login.setVisibility(View.VISIBLE);// ��ʾ��¼
			line_reg.setVisibility(View.INVISIBLE);// ����ע��
			line_updata_pass.setVisibility(View.INVISIBLE);// �����޸�����
			break;
		default:
			break;
		}
	}
}
