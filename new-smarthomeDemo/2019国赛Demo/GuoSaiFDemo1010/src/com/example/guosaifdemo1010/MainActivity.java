package com.example.guosaifdemo1010;

import com.example.guosaifdemo1010.fragment.BarActivity;
import com.example.guosaifdemo1010.mysql.MyDataBaseHelper;
import com.example.guosaifdemo1010.toast.DiyToast;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO ��¼��ע�ᣬ�޸ġ��һ�����
 * @package_name com.example.guosaifdemo1010
 * @project_name GuoSaiFDemo1010
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity implements OnClickListener {
	// ��¼����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_updatapass;// �޸����밴ť
	private Button btn_reback_pass;// �һ����밴ť
	private Spinner sp_1;// Spinner
	private EditText et_ip, et_port, et_user, et_pass;// �ı���
	private CheckBox cb_remebr_pass;// ��ס����
	private RadioButton ra_chose_user, ra_edit_user;// ѡ���˺š������˺�
	private String user, pass, port, ip;
	// ע��
	private EditText et_euser, et_epass, et_repass, et_twopass;// �ı���
	private String euser, epass, repass, twopass;// String
	// �޸�����
	private EditText et_updata_user, et_updata_oldpass, et_updata_newpass;// �ı���
	private String updata_user, updata_newpass, updata_oldpass;// String
	// �һ�����
	private EditText et_reback_user, et_reback_twopass;// �ı���
	private String reback_user, reback_pass;// String
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// ��
		ra_chose_user.setChecked(true);
		getUserName();// ��ȡ�û���
		// ��ס����
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_remebr_pass.setChecked(true);
				if (sharedPreferences.getBoolean("mode", false) == true) {
					ra_chose_user.setChecked(true);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_user.setText("");
					et_port.setText(sharedPreferences.getString("port", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
				} else {
					ra_edit_user.setChecked(true);
					et_ip.setText(sharedPreferences.getString("ip", null));
					et_user.setText(sharedPreferences.getString("user", null));
					et_port.setText(sharedPreferences.getString("port", null));
					et_pass.setText(sharedPreferences.getString("pass", null));
				}
			}
		}
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:18:09
	 */
	private void initView() {
		// TODO Auto-generated method stub
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reback_pass.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		btn_updatapass = (Button) findViewById(R.id.btn_updata_pass);
		btn_updatapass.setOnClickListener(this);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		sp_1 = (Spinner) findViewById(R.id.sp_user);
		cb_remebr_pass = (CheckBox) findViewById(R.id.cb_rember);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_edit_user = (RadioButton) findViewById(R.id.ra_set_user);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����8:18:26
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
			ip = et_ip.getText().toString();// IP
			if (ra_chose_user.isChecked()) {
				Cursor cursor = (Cursor) sp_1.getItemAtPosition(sp_1
						.getSelectedItemPosition());
				user = cursor.getString(1);
				if (ip.isEmpty()) {
					// IP = null
					DiyToast.showToast(getApplicationContext(), "IP����Ϊ��");
				} else if (port.isEmpty()) {
					// �˿ں� = null
					DiyToast.showToast(getApplicationContext(), "�˿ںŲ���Ϊ��");
				} else if (user.isEmpty()) {
					// �û��� = null
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (pass.isEmpty()) {
					// ���� = null
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else {
					Cursor cursor2 = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });
					if (cursor2.moveToNext()) {
						// ��¼�ɹ�
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
						// ��ס����
						if (cb_remebr_pass.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("mode", true)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("mode", true)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}
			if (ra_edit_user.isChecked()) {
				if (ip.isEmpty()) {
					// IP = null
					DiyToast.showToast(getApplicationContext(), "IP����Ϊ��");
				} else if (port.isEmpty()) {
					// �˿ں� = null
					DiyToast.showToast(getApplicationContext(), "�˿ںŲ���Ϊ��");
				} else if (user.isEmpty()) {
					// �û��� = null
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (pass.isEmpty()) {
					// ���� = null
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// �½��α�
					if (cursor.moveToNext()) {// �ƶ�
						// ��¼�ɹ�
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
						// ��ס����
						if (cb_remebr_pass.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("mode", false)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("mode", false)
									.putString("ip", ip)
									.putString("user", user)
									.putString("pass", pass)
									.putString("port", port).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}

			break;
		case R.id.btn_reback_pass:
			// �һ�����
			Dialog_ReBackPass();
			break;
		case R.id.btn_reg:
			// ע��
			Dialog_Reg();
			break;
		case R.id.btn_updata_pass:
			// �޸�����
			Dialog_UpdataPass();
			break;

		default:
			break;
		}
	}

	/**
	 * �õ��û�����������Spinner��
	 * 
	 * @return
	 */
	public void getUserName() {
		Cursor c = db.rawQuery("select * from user", null);
		if (c.getCount() != 0) {
			c.moveToFirst();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					MainActivity.this,
					android.R.layout.simple_spinner_dropdown_item, c,
					new String[] { "username" },
					new int[] { android.R.id.text1 });
			sp_1.setAdapter(adapter);
		}
	}

	// �һ�����
	private void Dialog_ReBackPass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater.inflate(R.layout.dialog_reback_pass, null,
				false);
		builder.setView(view);
		final EditText et_reback_user = (EditText) view
				.findViewById(R.id.et_reback_user);
		final EditText et_reback_twopass = (EditText) view
				.findViewById(R.id.et_reback_two_pass);
		builder.setPositiveButton("�޸�����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						reback_pass = et_reback_twopass.getText().toString();
						reback_user = et_reback_user.getText().toString();
						if (reback_user.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�û�������Ϊ��");
							Dialog_ReBackPass();
						} else if (reback_pass.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�����벻��Ϊ��");
							Dialog_ReBackPass();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { reback_user });
							if (cursor.moveToNext()) {
								if (reback_pass.equals(cursor.getString(cursor
										.getColumnIndex("twopass")))) {
									for (int i = 0; i < 4; i++) {// ǿ��ǰ����ʾ
										DiyToast.showToast(
												getApplicationContext(),
												"�������룺"
														+ cursor.getString(cursor
																.getColumnIndex("passward")));
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"�����������");
									Dialog_ReBackPass();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"�û���������");
								Dialog_ReBackPass();
							}
						}
					}
				});
		builder.show();
	}

	// �޸�����
	private void Dialog_UpdataPass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater.inflate(R.layout.dialog_updatapass, null,
				false);
		builder.setView(view);
		final EditText et_updata_user = (EditText) view
				.findViewById(R.id.et_updata_user);
		final EditText et_updata_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		final EditText et_updata_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		builder.setPositiveButton("�޸�����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						updata_user = et_updata_user.getText().toString();
						updata_oldpass = et_updata_oldpass.getText().toString();
						updata_newpass = et_updata_newpass.getText().toString();
						if (updata_user.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�û�������Ϊ��");
							Dialog_UpdataPass();
						} else if (updata_oldpass.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�����벻��Ϊ��");
							Dialog_UpdataPass();
						} else if (updata_newpass.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�����벻��Ϊ��");
							Dialog_UpdataPass();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { updata_user });
							if (cursor.moveToNext()) {
								if (updata_oldpass.equals(cursor
										.getString(cursor
												.getColumnIndex("passward")))) {
									if (updata_oldpass.equals(updata_newpass)) {
										DiyToast.showToast(
												getApplicationContext(),
												"�¾����벻��һ��");
										Dialog_UpdataPass();
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] { updata_newpass,
														updata_user });
										DiyToast.showToast(
												getApplicationContext(), "�޸ĳɹ�");
									}
								} else {
									DiyToast.showToast(getApplicationContext(),
											"���������");
									Dialog_UpdataPass();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"�û���������");
								Dialog_UpdataPass();
							}
						}
					}
				});
		builder.show();
	}

	// ע��
	private void Dialog_Reg() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		View view = layoutInflater.inflate(R.layout.dialog_reg, null, false);
		builder.setView(view);
		final EditText et_euser = (EditText) view.findViewById(R.id.et_euser);
		final EditText et_epass = (EditText) view.findViewById(R.id.et_epass);
		final EditText et_repass = (EditText) view.findViewById(R.id.et_repass);
		final EditText et_retwopass = (EditText) view
				.findViewById(R.id.et_retwopass);
		builder.setPositiveButton("ע��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				twopass = et_retwopass.getText().toString();
				System.out.println(euser);
				System.out.println(epass);
				System.out.println(repass);
				System.out.println(twopass);
				if (euser.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
					Dialog_Reg();
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
					Dialog_Reg();
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "ȷ�����벻��Ϊ��");
					Dialog_Reg();
				} else if (twopass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������벻��Ϊ��");
					Dialog_Reg();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
						Dialog_Reg();
					} else {
						if (epass.equals(repass)) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] { euser, epass, twopass });
							DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
							getUserName();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"��֤���벻һ��");
							Dialog_Reg();
						}
					}
				}
			}
		});
		builder.show();
	}
}
