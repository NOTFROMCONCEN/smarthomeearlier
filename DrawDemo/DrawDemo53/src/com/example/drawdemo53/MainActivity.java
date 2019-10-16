package com.example.drawdemo53;

import lib.SocketThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	// ��ǰ�ǵ�¼���棨�������һ����ʾ�ģ�����Ҫ����Ϊ����
	// ������¼��ע�ᡢ�޸����롢�������롢�˳�ϵͳ���Զ���¼����ס���룻
	private Button btn_login, btn_reg, btn_update_pass, btn_reback_pass,
			btn_exit;
	private CheckBox cb_autologin, cb_rember;
	private EditText et_user, et_pass, et_port, et_ip;
	private String user, pass, port, ip, autouser, autoport, autoip, autopass;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	static SharedPreferences rember;
	static SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �󶨵Ŀؼ�
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_update_pass = (Button) findViewById(R.id.btn_update_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);

		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();

		// ���ð�ť��װ
		btn_exit.setOnClickListener(this);
		btn_reback_pass.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_update_pass.setOnClickListener(this);

		// �Զ���¼��ס���빦��ʵ�ִ���
		/** ����SharedPreferences�������ļ������Լ���дȨ��ģʽ **/
		rember = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);

		if (rember != null) {
			/** ��rember��Ϊnull���������ɹ��������ڱ����ҵ��� **/
			final String autouser, autopass, autoport, autoip;
			// �����õ�String��ֵ��ֵ
			autoip = rember.getString("ip", null);
			autopass = rember.getString("pass", null);
			autoport = rember.getString("port", null);
			autouser = rember.getString("user", null);
			// ���rember�ڵõ��Ĳ�����ֵ��rember���ı�ǩΪture
			if (rember.getBoolean("rember", false) == true) {
				// ���ü�ס����CheckBox״̬Ϊѡ��
				cb_rember.setChecked(true);
				// ������õ�����ֵ���õ��ı�����
				et_ip.setText(autoip);
				et_pass.setText(autopass);
				et_port.setText(autoport);
				et_user.setText(autouser);
			} else {
				// ���rember�ڵõ��Ĳ�����ֵ��rember���ı�ǩΪfalse
				et_ip.setText("");
				et_pass.setText("");
				et_port.setText("");
				et_user.setText("");
			}
			// ���rember�ڵõ��Ĳ�����ֵ��autologin���ı�ǩΪture
			if (rember.getBoolean("autologin", false) == true) {
				// �����Զ���¼CheckBox״̬Ϊѡ��
				cb_autologin.setChecked(true);
				// �½��߳�
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							// ���߳�����һ��3����ӳ�
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						// 3������Զ���¼��ѡ���״̬��3����ȡ��ѡ�п���ֹͣ�Զ���¼��
						if (cb_autologin.isChecked()) {
							// �����ѡ�У�3�����ת����һ����
							SocketThread.SocketIp = autoip;
							SocketThread.Port = Integer.valueOf(autoport);
							Intent intent = new Intent(MainActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							// ���3����ȡ��ѡ�У����Զ���¼������ֵ�ı�ǩ��Ϊfalse�����಻��
							// �ɸ���Ҫ��ѡ���Ը��ļ�ס����Ĳ�����ֵ��ǩ��
							rember.edit().putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", autouser)
									.putString("port", autoport)
									.putString("ip", autoip)
									.putString("pass", autopass).commit();
						}
					}
					// �����߳�
				}).start();
			}
		} else {
			/** ��remberΪnull��������ʧ�ܣ� **/
			// �����ı���Ϊ��
			et_ip.setText("");
			et_pass.setText("");
			et_port.setText("");
			et_user.setText("");
		}
		// ��¼���ܵ�ʵ��
		// �������¼��ť��ʱ�򡪡�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//��String��ֵ��ֵ
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				//�½����ݿ�ָ��
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });
				if (user.equals("")) {
					Toast.makeText(MainActivity.this, "�������û���",
							Toast.LENGTH_SHORT).show();
				} else if (pass.equals("")) {
					Toast.makeText(MainActivity.this, "����������",
							Toast.LENGTH_SHORT).show();
				} else if (ip.equals("")) {
					Toast.makeText(MainActivity.this, "������IP��ַ",
							Toast.LENGTH_SHORT).show();
				} else if (port.equals("")) {
					Toast.makeText(MainActivity.this, "������������˿ں�",
							Toast.LENGTH_SHORT).show();
				} else if (!cursor.moveToNext()) {
					Toast.makeText(MainActivity.this, "�û����������������",
							Toast.LENGTH_SHORT).show();
				} else {
					if (cb_autologin.isChecked() && cb_rember.isChecked()) {
						rember.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else if (cb_autologin.isChecked()) {
						rember.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else if (cb_rember.isChecked()) {
						rember.edit().putBoolean("autologin", false)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						rember.edit().putBoolean("autologin", false)
								.putBoolean("rember", false)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exit:
			System.exit(0);
			break;
		case R.id.btn_reback_pass:
			reback_pass();
			break;
		case R.id.btn_reg:
			regdialog();
			break;
		case R.id.btn_update_pass:
			update_pass();
			break;
		default:
			break;
		}
	}

	private void update_pass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("�޸�����");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_update_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_updata_user = (EditText) view
						.findViewById(R.id.et_update_user);
				final EditText et_updata_newpass = (EditText) view
						.findViewById(R.id.et_updata_newpass);
				final EditText et_updata_oldpass = (EditText) view
						.findViewById(R.id.et_updata_oldpass);
				final String updata_user, updata_oldpass, updata_newpass, getSQLitepassward;
				updata_newpass = et_updata_newpass.getText().toString();
				updata_oldpass = et_updata_oldpass.getText().toString();
				updata_user = et_updata_user.getText().toString();
				if (updata_user.equals("")) {
					Toast.makeText(MainActivity.this, "�������û���",
							Toast.LENGTH_SHORT).show();
				} else if (updata_oldpass.equals("")) {
					Toast.makeText(MainActivity.this, "�����������",
							Toast.LENGTH_SHORT).show();
				} else if (updata_newpass.equals("")) {
					Toast.makeText(MainActivity.this, "������������",
							Toast.LENGTH_SHORT).show();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });
					cursor.moveToFirst();
					getSQLitepassward = cursor.getString(cursor
							.getColumnIndex("passward"));
					if (updata_oldpass.equals(getSQLitepassward)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });
						Toast.makeText(MainActivity.this, "�޸ĳɹ�",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(MainActivity.this, "�������������",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}

	private void reback_pass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("�һ�����");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reback_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_reback_user = (EditText) view
						.findViewById(R.id.et_reback_user);
				final EditText et_twopass = (EditText) view
						.findViewById(R.id.et_reback_twopass);
				final String reback_user, twopass, getSQLiteUser;
				reback_user = et_reback_user.getText().toString();
				twopass = et_twopass.getText().toString();
				if (reback_user.equals("")) {
					Toast.makeText(MainActivity.this, "�������û���",
							Toast.LENGTH_SHORT).show();
				} else if (twopass.equals("")) {
					Toast.makeText(MainActivity.this, "�������������",
							Toast.LENGTH_SHORT).show();
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and twopass = ?",
									new String[] { reback_user, twopass });
					if (cursor.moveToNext()) {
						getSQLiteUser = cursor.getString(cursor
								.getColumnIndex("passward"));
						Toast.makeText(
								MainActivity.this,
								"�һسɹ����û�����" + reback_user + "���룺"
										+ getSQLiteUser, Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(MainActivity.this, "�û�������������������",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}

	private void regdialog() {
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
				final EditText et_euser = (EditText) view
						.findViewById(R.id.et_euser);
				final EditText et_epass = (EditText) view
						.findViewById(R.id.et_epass);
				final EditText et_repass = (EditText) view
						.findViewById(R.id.et_repass);
				final EditText et_twopass = (EditText) view
						.findViewById(R.id.et_twopass);
				final String euser, epass, repass, twopass;
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				twopass = et_twopass.getText().toString();
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });
				if (euser.equals("")) {
					Toast.makeText(MainActivity.this, "�������û���",
							Toast.LENGTH_SHORT).show();
				} else if (epass.equals("")) {
					Toast.makeText(MainActivity.this, "����������",
							Toast.LENGTH_SHORT).show();
				} else if (repass.equals("")) {
					Toast.makeText(MainActivity.this, "������ȷ������",
							Toast.LENGTH_SHORT).show();
				} else if (twopass.equals("")) {
					Toast.makeText(MainActivity.this, "�������������",
							Toast.LENGTH_SHORT).show();
				} else {
					if (epass.equals(repass)) {
						if (cursor.moveToNext()) {
							Toast.makeText(MainActivity.this, "�û����Ѵ���",
									Toast.LENGTH_SHORT).show();
						} else {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] { euser, epass, twopass });
							Toast.makeText(MainActivity.this, "ע�����",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "�����������벻һ��",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}
}
