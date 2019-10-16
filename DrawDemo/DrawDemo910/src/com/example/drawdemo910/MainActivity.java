package com.example.drawdemo910;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
 * @ʱ�䣺2019-9-10
 */
public class MainActivity extends Activity {
	// ������ʽ�������ʽ����
	private Matcher matcher;
	private Pattern pattern;
	private boolean isTrue = false;
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private Button btn_updata_pass;// �޸�����
	private Button btn_reback_pass;// �һ�����
	private Spinner sp_user;// �����˵�
	private EditText et_user, et_pass, et_ip;// �ı���
	private CheckBox cb_autologin;// �Զ���¼
	private CheckBox cb_rember;// ��ס����
	private RadioButton ra_chose_user;// ѡ���û�
	private RadioButton ra_set_user;// �����û�
	public static SharedPreferences sharedPreferences;// sharedPreferences�洢
	// ���ݿ�
	private MyDataBaseHelepr dbHelepr;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("��¼");
		dbHelepr = new MyDataBaseHelepr(this, "info.db", null, 2);
		db = dbHelepr.getWritableDatabase();
		initView();
		set_user();
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog_Reg();
			}
		});
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String user, pass, ip;
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				ip = et_ip.getText().toString();
				if (ra_chose_user.isChecked()) {
					if (pass.isEmpty() || ip.isEmpty()) {
						DiyToast.showToas(getApplicationContext(), "�����пհ���Ŀ");
					} else {
						Cursor c = (Cursor) sp_user.getItemAtPosition(sp_user
								.getSelectedItemPosition());
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { c.getString(1),
												et_pass.getText().toString() });
						if (cursor.moveToNext()) {
							Intent intent = new Intent(MainActivity.this,
									UnLockActivity.class);
							startActivity(intent);
							finish();
							DiyToast.showToas(getApplicationContext(), "��½�ɹ�");
							if (cb_autologin.isChecked()
									&& cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_autologin.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", true)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", c.getString(1))
										.putString("pass", pass)
										.putString("ip", ip).commit();
							}
						} else {
							DiyToast.showToas(getApplicationContext(), "�����������");
						}
					}
				} else if (ra_set_user.isChecked()) {
					if (user.isEmpty() || pass.isEmpty() || ip.isEmpty()) {
						DiyToast.showToas(getApplicationContext(), "�����пհ���Ŀ!");
					} else {
						Cursor cursor = db
								.rawQuery(
										"select * from user where username = ? and passward = ?",
										new String[] { user, pass });
						if (cursor.moveToNext()) {
							Intent intent = new Intent(MainActivity.this,
									UnLockActivity.class);
							startActivity(intent);
							finish();
							DiyToast.showToas(getApplicationContext(), "��¼�ɹ�");
							if (cb_autologin.isChecked()
									&& cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", user)
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_autologin.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", true)
										.putBoolean("rember", true)
										.putString("user", user)
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else if (cb_rember.isChecked()) {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", true)
										.putString("user", user)
										.putString("pass", pass)
										.putString("ip", ip).commit();
							} else {
								sharedPreferences.edit()
										.putBoolean("autologin", false)
										.putBoolean("rember", false)
										.putString("user", user)
										.putString("pass", pass)
										.putString("ip", ip).commit();
							}
						} else {
							DiyToast.showToas(getApplicationContext(),
									"�û����������������");
						}
					}
				} else {
					DiyToast.showToas(getApplicationContext(), "��ѡ���¼��ʽ");
				}
			}
		});
		// �޸����밴ť����¼�
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog_UpdataPass();
			}
		});
		// �һ����밴ť����¼�
		btn_reback_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showDialog_RebackPass();
			}
		});
		// ��¼ʱѡ���û���������
		ra_chose_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sp_user.setVisibility(View.VISIBLE);
				et_user.setVisibility(View.GONE);
			}
		});
		ra_set_user.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sp_user.setVisibility(View.GONE);
				et_user.setVisibility(View.VISIBLE);
			}
		});
		// ��ס���빦��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������showDialog
	 * 
	 * @�� �ܣ�չʾ�Զ���Dialog�һ�����
	 * 
	 * @ʱ �䣺����6:45:17
	 */
	public void showDialog_RebackPass() {

	}

	/*
	 * @��������showDialog
	 * 
	 * @�� �ܣ�չʾ�Զ���Dialog�޸����빦��
	 * 
	 * @ʱ �䣺����6:45:17
	 */
	public void showDialog_UpdataPass() {

		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("�޸�����");
		LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
		final View view = inflater.inflate(R.layout.activity_updata_pass, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_updata_user = (EditText) view
						.findViewById(R.id.et_updata_user);
				final EditText et_updata_oldpass = (EditText) view
						.findViewById(R.id.et_updata_oldpass);
				final EditText et_updata_newpass = (EditText) view
						.findViewById(R.id.et_updata_newpass);
				if (et_updata_newpass.getText().toString().isEmpty()
						|| et_updata_oldpass.getText().toString().isEmpty()
						|| et_updata_user.getText().toString().isEmpty()) {
					DiyToast.showToas(getApplicationContext(), "�����пհ���");
					showDialog_UpdataPass();
				} else {
					Cursor cursor = db
							.rawQuery("select * from user where username = ?",
									new String[] { et_updata_user.getText()
											.toString() });
					if (cursor.moveToNext()) {
						if (cursor.getString(cursor.getColumnIndex("passward"))
								.toString()
								.equals(et_updata_oldpass.getText().toString())) {
							if (et_updata_newpass
									.getText()
									.toString()
									.equals(et_updata_oldpass.getText()
											.toString())) {
								DiyToast.showToas(getApplicationContext(),
										"�¾����벻��һ��");
								showDialog_UpdataPass();
							} else {
								db.execSQL(
										"update user set passward = ? where username = ?",
										new String[] {
												et_updata_newpass.getText()
														.toString(),
												et_updata_user.getText()
														.toString() });
								DiyToast.showToas(getApplicationContext(),
										"�޸ĳɹ�");
							}
						} else {
							DiyToast.showToas(getApplicationContext(),
									"�������������");
							showDialog_UpdataPass();
						}
					} else {
						DiyToast.showToas(getApplicationContext(), "�û���������");
						showDialog_UpdataPass();
					}
				}
			}
		});
		builder.show();
	}

	/*
	 * @��������showDialog
	 * 
	 * @�� �ܣ�չʾ�Զ���Dialogע�Ṧ��
	 * 
	 * @ʱ �䣺����6:34:54
	 */
	public void showDialog_Reg() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("ע��");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reg, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_reg_user = (EditText) view
						.findViewById(R.id.et_reg_user);
				final EditText et_reg_pass = (EditText) view
						.findViewById(R.id.et_reg_pass);
				final EditText et_reg_repass = (EditText) view
						.findViewById(R.id.et_reg_repass);
				final EditText et_reg_twopass = (EditText) view
						.findViewById(R.id.et_reg_twopass);
				if (et_reg_user.getText().toString().isEmpty()
						|| et_reg_repass.getText().toString().isEmpty()
						|| et_reg_twopass.getText().toString().isEmpty()
						|| et_reg_pass.getText().toString().isEmpty()) {// ����һ��Ϊ��
					DiyToast.showToas(getApplicationContext(), "�������пհ���");
					showDialog_Reg();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { et_reg_user.getText().toString() });
					if (cursor.moveToNext()) {
						DiyToast.showToas(getApplicationContext(), "�û����Ѵ��ڣ�");
						showDialog_Reg();
					} else {
						if (et_reg_pass.getText().toString()
								.equals(et_reg_repass.getText().toString())) {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] {
											et_reg_user.getText().toString(),
											et_reg_pass.getText().toString(),
											et_reg_twopass.getText().toString() });
							DiyToast.showToas(getApplicationContext(), "ע��ɹ�");
							set_user();
						} else {
							DiyToast.showToas(getApplicationContext(),
									"�����������벻һ��");
							showDialog_Reg();
						}
					}
				}
			}
		});
		builder.show();
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����6:32:52
	 */
	public void initView() {
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updata_pass = (Button) findViewById(R.id.btn_updata_pass);
		sp_user = (Spinner) findViewById(R.id.sp_user);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		ra_chose_user = (RadioButton) findViewById(R.id.ra_chose_user);
		ra_set_user = (RadioButton) findViewById(R.id.ra_set_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	}

	/*
	 * @��������set_user
	 * 
	 * @�� �ܣ�����Spinner���ݿ�
	 * 
	 * @ʱ �䣺����7:23:29
	 */
	public void set_user() {
		// ��������ʱ�趨Spinner��ʾ����
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, c,
				new String[] { "username" }, new int[] { android.R.id.text1 });
		sp_user.setAdapter(adapter);
	}
}
