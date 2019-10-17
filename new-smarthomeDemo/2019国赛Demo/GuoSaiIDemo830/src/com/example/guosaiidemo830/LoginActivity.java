package com.example.guosaiidemo830;

import java.util.logging.SocketHandler;

import com.example.guosaiidemo830.R.layout;

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
 * @��������ɵ�¼ע���޸����빦��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-30
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ����
	private LinearLayout line_login;// ��¼
	private LinearLayout line_reg;// ע��
	private LinearLayout line_updata_pass;// �޸�����
	// ��¼����
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private Button btn_updata_pass;// �޸�����
	private EditText et_user, et_pass, et_ip;// �ı���
	private Button btn_exit;// �˳�
	private String user, pass, ip;
	public static String ip_number;// IP��ַ

	// ע�����
	private Button btn_con;// ȷ��
	private Button btn_cls;// �ر�
	private EditText et_euser, et_epass, et_repass;// �ı���
	private String euser, epass, repass;

	// �޸��������
	private Button btn_updata_con;// ȷ��
	private Button btn_updata_cls;// �ر�
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// �ı���
	private String updata_user, updata_newpass, updata_oldpass;

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// �洢
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		setTitle("���ܼҾ�");
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_newpass = (EditText) findViewById(R.id.et_new_pass);
		et_updata_oldpass = (EditText) findViewById(R.id.et_old_pass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// �����
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		// ��ס���빦��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// ��ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
		// ����������ʾ�ַ�Ϊ*
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ�����¼�
	 * 
	 * @ʱ �䣺����8:47:47
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.equals("")) {// ����û���Ϊ��
				DiyToast.showTaost(LoginActivity.this, "�������û���");
			} else if (epass.equals("")) {// �������Ϊ��
				DiyToast.showTaost(LoginActivity.this, "����������");
			} else if (repass.equals("")) {// ȷ������Ϊ��
				DiyToast.showTaost(LoginActivity.this, "������ȷ������");
			} else {
				if (epass.equals(repass)) {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor.moveToNext()) {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ʧ��")
								.setMessage("�û����Ѿ����ڣ�������ע��")
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
					} else {
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
														.setVisibility(View.VISIBLE);
												line_reg.setVisibility(View.GONE);
												line_updata_pass
														.setVisibility(View.GONE);
											}
										}).show();
					}
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
											// TODO Auto-generated method stub

										}
									}).show();
				}
			}
			break;
		case R.id.btn_exit:
			// ��¼�˳�
			System.exit(0);
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward  =?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// �û���Ϊ��
				DiyToast.showTaost(LoginActivity.this, "�������û���");
			} else if (pass.equals("")) {// ����Ϊ��
				DiyToast.showTaost(LoginActivity.this, "����������");
			} else if (ip.equals("")) {// Ip��ַΪ��
				DiyToast.showTaost(LoginActivity.this, "������IP��ַ");
			} else if (!cursor.moveToNext()) {
				DiyToast.showTaost(LoginActivity.this, "�û����������������");
			} else {
				ip_number = ip;
				sharedPreferences.edit().putBoolean("rember", true)
						.putString("user", user).putString("pass", pass)
						.putString("ip", ip).commit();
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			}
			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_cls:
			// �޸�����ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.GONE);
			break;
		case R.id.btn_updata_con:
			// �޸�����ȷ��
			updata_newpass = et_updata_newpass.getText().toString();// ������
			updata_oldpass = et_updata_oldpass.getText().toString();// ������
			updata_user = et_updata_user.getText().toString();// �û���
			if (updata_user.equals("")) {// �û���Ϊ��
				DiyToast.showTaost(LoginActivity.this, "�������û���");
			} else if (updata_oldpass.equals("")) {// ���������Ϊ��
				DiyToast.showTaost(LoginActivity.this, "�����������");
			} else if (updata_newpass.equals("")) {// ���������Ϊ��
				DiyToast.showTaost(LoginActivity.this, "������������");
			} else {
				Cursor cur_updata_user = db.rawQuery(
						"select * from user where username = ?",
						new String[] { updata_user });// �½����ݿ�ָ��1���Ա��û���
				if (cur_updata_user.moveToNext()) {
					Cursor cur_updata_pass = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// �½����ݿ�ָ��2��Ѱ������
					cur_updata_pass.moveToFirst();
					String sqlite_getpass = cur_updata_pass
							.getString(cur_updata_pass
									.getColumnIndex("passward"));
					if (updata_oldpass.equals(sqlite_getpass)) {
						if (updata_newpass.equals(updata_oldpass)) {
							DiyToast.showTaost(LoginActivity.this, "�¾����벻��һ�£�");
						} else {
							db.execSQL(
									"update user set passward = ? where username  =?",
									new String[] { updata_newpass, updata_user });// �������ݿ�
							DiyToast.showTaost(LoginActivity.this, "�޸����");
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
													line_login
															.setVisibility(View.VISIBLE);
													line_reg.setVisibility(View.GONE);
													line_updata_pass
															.setVisibility(View.GONE);
												}
											}).show();
						}
					} else {
						DiyToast.showTaost(LoginActivity.this, "�������������");
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸�ʧ��")
								.setMessage("���������")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated
												// method stub
											}
										}).show();
					}
				} else {
					DiyToast.showTaost(LoginActivity.this, "�û���������");
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("�޸�ʧ��")
							.setMessage("�޸�ʧ�ܣ��û���������")
							.setPositiveButton("Ok",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated
											// method stub
										}
									}).show();
				}
			}
			break;
		case R.id.btn_updata_pass:
			// �޸�����
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.GONE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
