package com.example.guosaigdemo9262019;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-26
 */
public class LoginActivity extends Activity {
	private EditText et_ip;// IP��ַ
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private Button btn_login;// ��¼
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	// sharedPreferences�洢
	static SharedPreferences sharedPreferences;
	private String user, pass, ip;
	public static String IP_NUMBER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// �������ݿ�
		SQLiteControl.setUser(getApplicationContext(), "bizideal", "123456");
		initView();// ��
		rember_autologin();// ��ס���롢�Զ���¼
		// ���ü�ס�����Զ���¼��ѡ��
		// ��ס����
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
		// �Զ���¼
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
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				ip = et_ip.getText().toString();// IP��ַ
				if (ip.isEmpty()) {// Ip��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else {
					SQLiteControl.getUser(getApplicationContext(), user, pass);
					if (SQLiteControl.state == true) {
						// ��¼��ת
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
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
						DiyToast.showToast(getApplicationContext(),
								"�û����������������");
					}
				}
			}
		});
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����3:10:10
	 */
	private void initView() {
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	/*
	 * @��������rember_autologin
	 * 
	 * @�� �ܣ���ס�����Զ���¼
	 * 
	 * @ʱ �䣺����3:10:22
	 */
	private void rember_autologin() {
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							IP_NUMBER = et_ip.getText().toString();
							Intent intent = new Intent(LoginActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					}
				}).start();
			}
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null)
						.toString());
				et_pass.setText(sharedPreferences.getString("pass", null)
						.toString());
				et_user.setText(sharedPreferences.getString("user", null)
						.toString());
			}
		}
	}
}
