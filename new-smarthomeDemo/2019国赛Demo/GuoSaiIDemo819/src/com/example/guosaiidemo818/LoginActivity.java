package com.example.guosaiidemo818;

import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

/*
 * @�ļ�����LoginActivity.java
 * @���������û���ɵ�¼UI��ʾ����¼ע���޸����빦��;
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-18
 */
public class LoginActivity extends Activity implements OnClickListener {
	// ��¼����
	private Button btn_login, btn_reg, btn_updata_pass, btn_exit;// ��ť
	private String user, pass, ip;// String��ֵ
	private EditText et_user, et_pass, et_ip;// �ı���
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// ע�Ჿ��
	private Button btn_con, btn_cls;// ��ť
	private EditText et_euser, et_epass, et_repass;// �ı���
	private String euser, epass, repass;

	// �޸����벿��
	private Button btn_updata_con, btn_updata_cls;// ��ť
	private String updata_user, new_pass, old_pass;// STring��ֵ
	private EditText et_updata_user, et_new_passward, et_old_passward;// �ı���

	// ����
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�����
	private LinearLayout line_updata_pass;// �޸��������

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_updata_pass = (LinearLayout) findViewById(R.id.line_updata_pass);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_cls = (Button) findViewById(R.id.btn_updata_cls);
		btn_updata_con = (Button) findViewById(R.id.btn_updata_con);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_new_passward = (EditText) findViewById(R.id.et_new_pass);
		et_old_passward = (EditText) findViewById(R.id.et_old_pass);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_updata_user = (EditText) findViewById(R.id.et_updata_user);
		et_user = (EditText) findViewById(R.id.et_user);
		// ��������������ʽ
		et_pass.setTransformationMethod(new WordReplacement());
		// �������ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_exit.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_updata_cls.setOnClickListener(this);
		btn_updata_con.setOnClickListener(this);
		btn_updata_pass.setOnClickListener(this);
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����3:21:07
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// �ر�ע�����
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// ע�����ȷ����ť
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.equals("")) {// ����û����ı����Ƿ�Ϊ��
				Toast.makeText(LoginActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.equals("")) {// ��������ı����Ƿ�Ϊ��
				Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.equals("")) {// ���ȷ�������ı����Ƿ�Ϊ��
				Toast.makeText(LoginActivity.this, "������ȷ������",
						Toast.LENGTH_SHORT).show();
			} else {
				if (epass.equals(repass)) {
					// ������ȷ������һ��
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor.moveToNext()) {
						// ���ݿ��ڴ����û���
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("ע��ʧ��")
								.setMessage("�û����Ѵ��ڣ�������ע��")
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
												line_reg.setVisibility(View.INVISIBLE);
												line_updata_pass
														.setVisibility(View.INVISIBLE);
											}
										}).show();
					}
				} else {
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("ע��ʧ��")
							.setMessage("�����������벻һ��")
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
			break;
		case R.id.btn_exit:
			// �˳�����
			System.exit(0);
			break;
		case R.id.btn_login:
			// ��¼��ť
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				Toast.makeText(LoginActivity.this, "�������û���", Toast.LENGTH_SHORT)
						.show();
			} else if (pass.equals("")) {// �������Ϊ��
				Toast.makeText(LoginActivity.this, "����������", Toast.LENGTH_SHORT)
						.show();
			} else if (ip.equals("")) {// ���IP��ַΪ��
				Toast.makeText(LoginActivity.this, "������IP��ַ",
						Toast.LENGTH_SHORT).show();
			} else if (!cursor.moveToNext()) {// ������ݿ��޷�ƥ��
				new AlertDialog.Builder(LoginActivity.this)
						.setTitle("��¼ʧ��")
						.setMessage("�û������������")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();
			} else {
				// ��ת����һ����
				Intent intent = new Intent(LoginActivity.this,
						BarActivity.class);
				startActivity(intent);
				finish();
			}

			break;
		case R.id.btn_reg:
			// ����ע�����
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_cls:
			// �ر��޸��������
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_updata_con:
			// �޸��������ȷ����ť
			updata_user = et_updata_user.getText().toString();// �û���
			new_pass = et_new_passward.getText().toString();// ������
			old_pass = et_old_passward.getText().toString();// ������
			String get_username,
			get_passward;// �����ݿ�ȡ���û���������
			Cursor cursor2 = db.rawQuery(
					"select * from user where username = ?",
					new String[] { updata_user });// �½����ݿ�ָ��
			cursor2.moveToFirst();
			get_username = cursor2
					.getString(cursor2.getColumnIndex("username"));
			get_passward = cursor2
					.getString(cursor2.getColumnIndex("passward"));
			if (get_username.equals(updata_user)) {
				// �û����Ƿ�ƥ��
				if (get_passward.equals(old_pass)) {
					// �¾������Ƿ�ƥ��
					if (old_pass.equals(new_pass)) {
						// �¾�����һ��
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸�ʧ��")
								.setMessage("�¾����벻��һ��")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method

											}
										}).show();
					} else {
						// �����޸ĳɹ�
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { new_pass, updata_user });// �������ݿ�
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("�޸ĳɹ�")
								.setMessage("�����޸ĳɹ�")
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
												line_reg.setVisibility(View.INVISIBLE);
												line_updata_pass
														.setVisibility(View.INVISIBLE);
											}
										}).show();
					}
				} else {
					// ���벻ƥ��
					new AlertDialog.Builder(LoginActivity.this)
							.setTitle("�޸�ʧ��")
							.setMessage("���������")
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
			} else {
				// �û�����ƥ��
				new AlertDialog.Builder(LoginActivity.this)
						.setTitle("�޸�ʧ��")
						.setMessage("������û���������")
						.setPositiveButton("Ok",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// TODO Auto-generated method stub

									}
								}).show();
			}

			break;
		case R.id.btn_updata_pass:
			// �����޸��������
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			line_updata_pass.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}