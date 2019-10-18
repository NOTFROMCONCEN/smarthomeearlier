package com.example.shengsaiedemo20181009;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.example.shengsaiedemo20181009.fragment.BarActivity;
import com.example.shengsaiedemo20181009.mysql.MyDataBaseHelper;
import com.example.shengsaiedemo20181009.toast.DiyToast;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-9
 */
public class LoginActivity extends Activity {
	private Button btn_login;// ��¼��ť
	private EditText et_user, et_ip, et_pass;// �ı���
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	private String user, ip, pass;
	public static String IP_NUMBER, login_op;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences�Ŵ洢
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
		// ��ס�����Զ���¼
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// ��ס����
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// �Զ���¼
				cb_autologin.setChecked(true);
				DiyToast.showToast(getApplicationContext(), "4����Զ���¼");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(4000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							if (et_user.getText().toString().equals("bizideal")) {
								login_op = "����Ա";
							} else {
								login_op = "�û�";
							}
							IP_NUMBER = sharedPreferences.getString("ip", null);
							startActivity(new Intent(getApplicationContext(),
									BarActivity.class));
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					}
				}).start();
			}
		}
		// ���ø�ѡ������Ч��
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				} else {
					cb_rember.setChecked(false);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
				} else {
					cb_autologin.setChecked(false);
				}
			}
		});

		// ��¼
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				ip = et_ip.getText().toString();// IP
				if (ip.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (user.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// �½��α�
					if (cursor.moveToNext()) {// �ƶ�
						// ��½�ɹ�
						if (et_user.getText().toString().equals("bizideal")) {
							login_op = "����Ա";
						} else {
							login_op = "�û�";
						}
						IP_NUMBER = ip;
						startActivity(new Intent(getApplicationContext(),
								BarActivity.class));
						finish();
						// ��ס���롢�Զ���¼
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login_con);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}
}
