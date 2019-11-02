package com.example.edemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.edemo.fragment.BarActivity;
import com.example.edemo.mysql.MyDataBaseHelper;
import com.example.edemo.toast.DiyToast;

/**
 * ��¼
 * 
 * @author A
 * 
 */
public class MainActivity extends Activity {
	boolean user_mode = false;
	private Button btn_login;
	private Button btn_reg;
	private Button btn_updata_pass;
	private Button btn_reback_pass;
	private EditText et_user, et_pass, et_port, et_ip;
	private Spinner sp_1;
	private String user, pass, port, ip;
	private RadioButton ra_chose_user, ra_set_user;
	private CheckBox cb_rember;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences�洢����ס���룩
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// ��
		/*
		 * ��ס����
		 */
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// �ж��ϴε�¼�Ƿ�ѡ��ס����
				if (sharedPreferences.getBoolean("user_mode", false) == true) {// �ж��ϴε�¼��ʽ
					sp_1.setVisibility(View.VISIBLE);
					et_user.setVisibility(View.GONE);
					ra_chose_user.setChecked(true);
					ra_set_user.setChecked(false);
					user_mode = true;
				} else {
					sp_1.setVisibility(View.GONE);
					et_user.setVisibility(View.VISIBLE);
					ra_chose_user.setChecked(false);
					ra_set_user.setChecked(true);
					user_mode = false;
				}
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_port.setText(sharedPreferences.getString("port", null));
				et_user.setText(sharedPreferences.getString("user", null));
			} else {
				et_ip.setText("");
				et_pass.setText("");
				et_port.setText("");
				et_user.setText("");
			}
		} else {
			et_ip.setText("");
			et_pass.setText("");
			et_port.setText("");
			et_user.setText("");
			/*
			 * Ĭ��״̬
			 */
			sp_1.setVisibility(View.VISIBLE);
			et_user.setVisibility(View.GONE);
			user_mode = true;
		}
		/*
		 * ���ݿ�
		 */
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		set_user();// ��SPinner�������û���
		// ע��
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_reg();
			}
		});
		// �޸�����
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_updata_pass();
			}
		});
		// �һ�����
		btn_reback_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog_reback_pass();
			}
		});
		/*
		 * ��¼
		 */
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				if (user_mode) {
					Cursor cursor = (Cursor) sp_1.getItemAtPosition(sp_1
							.getSelectedItemPosition());
					user = cursor.getString(1);
					login_start();
				} else {
					user = et_user.getText().toString();
					login_start();
				}
			}
		});

		ra_chose_user.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sp_1.setVisibility(View.VISIBLE);
					et_user.setVisibility(View.GONE);
					user_mode = true;
				}
			}
		});
		ra_set_user.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					sp_1.setVisibility(View.GONE);
					et_user.setVisibility(View.VISIBLE);
					user_mode = false;
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_set_user = (RadioButton) findViewById(R.id.ra_set_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		sp_1 = (Spinner) findViewById(R.id.sp_user);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void dialog_reg() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reg, null, false);
		builder.setView(view);
		final EditText et_euser = (EditText) view.findViewById(R.id.et_euser);
		final EditText et_epass = (EditText) view.findViewById(R.id.et_epass);
		final EditText et_repass = (EditText) view.findViewById(R.id.et_repass);
		final EditText et_two_pass = (EditText) view
				.findViewById(R.id.et_two_pass);
		builder.setPositiveButton("ע��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				if (et_euser.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������û���");
					dialog_reg();
				} else if (et_epass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "����������");
					dialog_reg();
				} else if (et_repass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "������ȷ������");
					dialog_reg();
				} else if (et_two_pass.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������������");
					dialog_reg();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { et_euser.getText().toString() });
					if (cursor.moveToNext()) {
						dialog_reg();
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
					} else {
						if (et_epass.getText().toString()
								.equals(et_repass.getText().toString())) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] {
											et_euser.getText().toString(),
											et_epass.getText().toString(),
											et_two_pass.getText().toString() });
							DiyToast.showToast(getApplicationContext(), "ע�����");
						} else {
							DiyToast.showToast(getApplicationContext(),
									"�����������벻һ��");
							dialog_reg();
						}
					}
				}
			}
		});
		builder.show();
	}

	private void dialog_reback_pass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_reback_pass, null, false);
		builder.setView(view);
		final EditText et_user = (EditText) view
				.findViewById(R.id.et_reback_user);
		final EditText et_reback_two_pass = (EditText) view
				.findViewById(R.id.et_reback_twopass);
		builder.setPositiveButton("�޸�����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_user.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�������û���");
							dialog_reback_pass();
						} else if (et_reback_two_pass.getText().toString()
								.isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�������������");
							dialog_reback_pass();
						} else {
							Cursor cursor = db
									.rawQuery(
											"select * from user where username = ?",
											new String[] { et_user.getText()
													.toString() });
							if (cursor.moveToNext()) {
								if (cursor.getString(
										cursor.getColumnIndex("twopass"))
										.equals(et_reback_two_pass.getText()
												.toString())) {
									DiyToast.showToast(
											getApplicationContext(),
											"���룺"
													+ cursor.getString(cursor
															.getColumnIndex("passward")));
								} else {
									DiyToast.showToast(getApplicationContext(),
											"���������������");
									dialog_updata_pass();
								}
							} else {
								dialog_updata_pass();
								DiyToast.showToast(getApplicationContext(),
										"�û���������");
							}
						}
					}
				});
		builder.show();
	}

	private void dialog_updata_pass() {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_updata_pass, null, false);
		builder.setView(view);
		final EditText et_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		final EditText et_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		final EditText et_updata_user = (EditText) view
				.findViewById(R.id.et_updata_user);
		builder.setPositiveButton("�޸�����",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (et_updata_user.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�������û���");
							dialog_updata_pass();
						} else if (et_oldpass.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"�����������");
							dialog_updata_pass();
						} else if (et_newpass.getText().toString().isEmpty()) {
							DiyToast.showToast(getApplicationContext(),
									"������������");
							dialog_updata_pass();
						} else {
							Cursor cursor = db.rawQuery(
									"select * from user where username = ?",
									new String[] { et_updata_user.getText()
											.toString() });
							if (cursor.moveToNext()) {
								if (cursor
										.getString(
												cursor.getColumnIndex("passward"))
										.equals(et_oldpass.getText().toString())) {
									if (et_newpass
											.getText()
											.toString()
											.equals(et_oldpass.getText()
													.toString())) {
										DiyToast.showToast(
												getApplicationContext(),
												"�¾����벻��һ��");
										dialog_updata_pass();
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] {
														et_newpass.getText()
																.toString(),
														et_updata_user
																.getText()
																.toString() });
									}

								} else {
									DiyToast.showToast(getApplicationContext(),
											"���������");
									dialog_updata_pass();
								}
							} else {
								dialog_updata_pass();
								DiyToast.showToast(getApplicationContext(),
										"�û���������");
							}
						}
					}
				});
		builder.show();
	}

	private void set_user() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("Select * from user", null);
		if (cursor.getCount() != 0) {
			SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
					getApplicationContext(), R.layout.item, cursor,
					new String[] { "username" }, new int[] { R.id.tv_set_user });
			sp_1.setAdapter(simpleCursorAdapter);
		}
	}

	private void login_start() {
		// TODO Auto-generated method stub
		if (ip.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "������IP��ַ");
		} else if (port.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "������˿�");
		} else if (user.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "�������û���");
		} else if (pass.isEmpty()) {
			DiyToast.showToast(getApplicationContext(), "����������");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });
			if (cursor.moveToNext()) {
				startActivity(new Intent(getApplicationContext(),
						BarActivity.class));
				if (cb_rember.isChecked()) {
					if (user_mode) {
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("pass", pass).putString("ip", ip)
								.putString("user", user)
								.putString("port", port)
								.putBoolean("user_mode", true).commit();
					} else {
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("pass", pass).putString("ip", ip)
								.putString("user", user)
								.putString("port", port)
								.putBoolean("user_mode", false).commit();
					}
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putString("pass", pass).putString("ip", ip)
							.putString("user", user).putString("port", port)
							.putBoolean("user_mode", false).commit();
				}
			} else {
				DiyToast.showToast(getApplicationContext(), "�û����������������");
			}
		}
	}
}