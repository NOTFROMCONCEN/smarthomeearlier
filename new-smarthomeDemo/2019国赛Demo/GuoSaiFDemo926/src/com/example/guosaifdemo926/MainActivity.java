package com.example.guosaifdemo926;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/*
 * @�ļ�����MainActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-26
 */
public class MainActivity extends Activity {
	private EditText et_ip;// IP��ַ
	private EditText et_port;// �˿ں�
	private EditText et_user;// �˺�
	private EditText et_pass;// ����
	private Spinner sp_user;// �û���
	private RadioButton ra_chose_user;// ѡ���˺�
	private RadioButton ra_edit_user;// �����˺�
	private CheckBox cb_rember;// ��ס����
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private Button btn_updata_pass;// �޸�����
	private Button btn_reback_pass;// �һ�����
	private String user, pass, port, ip;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_edit_user = (RadioButton) findViewById(R.id.ra_set_user);
		sp_user = (Spinner) findViewById(R.id.sp_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		et_user.setVisibility(View.INVISIBLE);
		sp_user.setVisibility(View.INVISIBLE);
		get_user();
		ra_chose_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_user.setVisibility(View.INVISIBLE);
				sp_user.setVisibility(View.VISIBLE);
			}
		});
		ra_edit_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				et_user.setVisibility(View.VISIBLE);
				sp_user.setVisibility(View.INVISIBLE);
			}
		});
		// ��ס���빦��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				if (sharedPreferences.getBoolean("user_state", false) == true) {
					et_user.setVisibility(View.INVISIBLE);
					sp_user.setVisibility(View.VISIBLE);
					ra_chose_user.setChecked(true);
					ra_edit_user.setChecked(false);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
					et_port.setText(sharedPreferences.getString("port", null));
				} else {
					et_user.setVisibility(View.VISIBLE);
					sp_user.setVisibility(View.INVISIBLE);
					ra_chose_user.setChecked(false);
					ra_edit_user.setChecked(true);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
					et_port.setText(sharedPreferences.getString("port", null));
					et_user.setText(sharedPreferences.getString("user", null));
				}
			}
		}
		// �޸����빦��
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updata_pass_show_dialog();
			}
		});
		// �һ����빦��
		btn_reback_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reback_pass_show_dialog();
			}
		});
		// ע�ᰴť����
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				reg_show_dialog();
			}
		});
		// ��¼����
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				if (ra_chose_user.isChecked()) {
					if (ip.isEmpty()) {// IP��ַΪ��
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
					} else if (port.isEmpty()) {// �˿�Ϊ��
						DiyToast.showToast(getApplicationContext(), "������˿�");
					} else if (pass.isEmpty()) {// ����Ϊ��
						DiyToast.showToast(getApplicationContext(), "����������");
					} else {
						Cursor cursor = (Cursor) sp_user
								.getItemAtPosition(sp_user
										.getSelectedItemPosition());
						Cursor cursor_login = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { cursor.getString(1),
												pass });
						if (cursor_login.moveToNext()) {// �α��ƶ�
							// ��¼��ת
							DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
							if (cb_rember.isChecked()) {
								// ��ס����
								sharedPreferences.edit()
										.putBoolean("user_state", true)
										.putBoolean("rember", true)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", "�ϴ�ʹ��ѡ���û�")
										.putString("port", port).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("user_state", true)
										.putBoolean("rember", false)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", "�ϴ�ʹ��ѡ���û�")
										.putString("port", port).commit();
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�����������");
						}
					}
				} else if (ra_edit_user.isChecked()) {
					if (ip.isEmpty()) {// IP��ַΪ��
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
					} else if (port.isEmpty()) {// �˿�Ϊ��
						DiyToast.showToast(getApplicationContext(), "������˿�");
					} else if (user.isEmpty()) {// �û���Ϊ��
						DiyToast.showToast(getApplicationContext(), "�������û���");
					} else if (pass.isEmpty()) {// ����Ϊ��
						DiyToast.showToast(getApplicationContext(), "����������");
					} else {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { user, pass });// �½����ݿ��α�
						if (cursor.moveToNext()) {// �ƶ�
							// ��¼��ת
							DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
							Intent intent = new Intent(getApplicationContext(),
									BarActivity.class);
							startActivity(intent);
							finish();
							if (cb_rember.isChecked()) {
								// ��ס����
								sharedPreferences.edit()
										.putBoolean("user_state", false)
										.putBoolean("rember", true)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", user)
										.putString("port", port).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("user_state", false)
										.putBoolean("rember", false)
										.putString("ip", ip)
										.putString("pass", pass)
										.putString("user", user)
										.putString("port", port).commit();
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�û����������������");
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "��ѡ���¼��ʽ");
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������updata_pass_show_dialog
	 * 
	 * @�� �ܣ��޸�����
	 * 
	 * @ʱ �䣺����8:18:15
	 */
	private void updata_pass_show_dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_updata_pass,
				sp_user, false);
		builder.setPositiveButton("�޸�����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final EditText et_updata_user = (EditText) view
								.findViewById(R.id.et_updata_user);
						final EditText et_updata_newpass = (EditText) view
								.findViewById(R.id.et_updata_newpass);
						final EditText et_updata_oldpass = (EditText) view
								.findViewById(R.id.et_updata_oldpass);
						final String updata_user = et_updata_user.getText()
								.toString();
						final String updata_newpass = et_updata_newpass
								.getText().toString();
						final String updata_oldpass = et_updata_oldpass
								.getText().toString();
						if (updata_user.isEmpty()) {// �û���Ϊ��
							DiyToast.showToast(getApplicationContext(),
									"�������û���");
							updata_pass_show_dialog();
						} else if (updata_oldpass.isEmpty()) {// ������Ϊ��
							DiyToast.showToast(getApplicationContext(),
									"�����������");
							updata_pass_show_dialog();
						} else if (updata_newpass.isEmpty()) {// ������Ϊ��
							DiyToast.showToast(getApplicationContext(),
									"������������");
							updata_pass_show_dialog();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { updata_user });// �½����ݿ��α�
							if (cursor.moveToNext()) {// �α��ƶ�
								String get_oldpass = cursor.getString(cursor
										.getColumnIndex("passward"));
								if (get_oldpass.equals(updata_oldpass)) {
									if (updata_newpass.equals(updata_oldpass)) {
										DiyToast.showToast(
												getApplicationContext(),
												"�¾����벻��һ��");
										updata_pass_show_dialog();
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] { updata_newpass,
														updata_user });// �������ݿ�
										DiyToast.showToast(
												getApplicationContext(), "�޸ĳɹ�");
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"�������������");
									updata_pass_show_dialog();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"�û���������");
								updata_pass_show_dialog();
							}
						}
					}
				});
		builder.setView(view);
		builder.show();
	}

	/*
	 * @��������reg_show_dialog
	 * 
	 * @�� �ܣ�ע��
	 * 
	 * @ʱ �䣺����8:15:43
	 */
	private void reg_show_dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reg, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("ע��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_epass = (EditText) view
						.findViewById(R.id.et_reg_pass);
				final EditText et_euser = (EditText) view
						.findViewById(R.id.et_reg_user);
				final EditText et_repass = (EditText) view
						.findViewById(R.id.et_reg_repass);
				final EditText et_two_pass = (EditText) view
						.findViewById(R.id.et_reg_two_pass);
				final String euser = et_euser.getText().toString();
				final String epass = et_epass.getText().toString();
				final String repass = et_repass.getText().toString();
				final String twopass = et_two_pass.getText().toString();
				if (euser.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
					reg_show_dialog();
				} else if (epass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
					reg_show_dialog();
				} else if (repass.isEmpty()) {// ȷ������Ϊ��
					DiyToast.showToast(getApplicationContext(), "������ȷ������");
					reg_show_dialog();
				} else if (twopass.isEmpty()) {// ��������Ϊ��
					DiyToast.showToast(getApplicationContext(), "�����������");
					reg_show_dialog();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username =?",
							new String[] { euser });// �½����ݿ��α�
					if (cursor.moveToNext()) {// �α��ƶ�
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
						reg_show_dialog();
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] { euser, epass, twopass });
							DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
							get_user();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�����������벻һ��");
							reg_show_dialog();
						}
					}
				}
			}
		});
		builder.show();
	}

	private void reback_pass_show_dialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reback_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("�һ�����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						final EditText et_reback_user = (EditText) view
								.findViewById(R.id.et_reback_user);
						final EditText et_reback_twopass = (EditText) view
								.findViewById(R.id.et_reback_twopass);
						final String reback_user = et_reback_user.getText()
								.toString();
						final String reback_pass = et_reback_twopass.getText()
								.toString();
						if (reback_user.isEmpty()) {// �û���Ϊ��
							DiyToast.showToast(getApplicationContext(),
									"�������û���");
							reback_pass_show_dialog();
						} else if (reback_pass.isEmpty()) {// ����Ϊ��
							DiyToast.showToast(getApplicationContext(), "����������");
							reback_pass_show_dialog();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { reback_user });
							if (cursor.moveToNext()) {
								if (cursor
										.getString(
												cursor.getColumnIndex("twopass"))
										.toString().equals(reback_pass)) {
									DiyToast.showToast(
											getApplicationContext(),
											"���룺"
													+ cursor.getString(cursor
															.getColumnIndex("passward")));
								} else {
									DiyToast.showToast(getApplicationContext(),
											"���������������");
									reback_pass_show_dialog();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"�û����������");
							}
						}
					}
				});
		builder.show();
	}

	/*
	 * @��������get_user
	 * 
	 * @�� �ܣ���ȡ�û���(Spinner)
	 * 
	 * @ʱ �䣺����8:46:36
	 */
	private void get_user() {
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					MainActivity.this, R.layout.user_text, cursor,
					new String[] { "username" }, new int[] { R.id.tv_user });
			sp_user.setAdapter(adapter);
		} else {
			DiyToast.showToast(getApplicationContext(), "�����״�����");
		}
	}
}
