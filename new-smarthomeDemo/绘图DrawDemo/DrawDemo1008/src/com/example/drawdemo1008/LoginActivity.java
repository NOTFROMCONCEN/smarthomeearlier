package com.example.drawdemo1008;

import java.util.regex.Pattern;

import java.util.regex.Matcher;

import com.example.drawdemo1008.fargment.BarActivity;
import com.example.drawdemo1008.mysql.MyDataBaseHelper;
import com.example.drawdemo1008.textchanger.TextChanger;
import com.example.drawdemo1008.toast.DiyToast;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class LoginActivity extends Activity implements OnClickListener {
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// ������ʽ
	Matcher matcher;
	Pattern pattern;

	// ����
	private LinearLayout line_login;// ��¼
	private LinearLayout line_reg;// ע��

	// ��¼����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private EditText et_user, et_pass, et_ip;
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼
	private String user, pass, ip;
	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	// ע�����
	private Button btn_con;// ȷ��
	private Button btn_cls;// �ر�
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	// IP��ַ
	public static String IP_NUMBER;

	// �������
	private boolean isTrue = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();// ��
		// ��ס�����Զ���¼����
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {// ��ס����
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
			if (sharedPreferences.getBoolean("autologin", false) == true) {// �Զ���¼
				cb_autologin.setChecked(true);
				DiyToast.showToast(getApplicationContext(), "�Զ���¼3�����У����԰�����");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e);
						}
						if (cb_autologin.isChecked()) {
							IP_NUMBER = sharedPreferences.getString("ip", null);
							startActivity(new Intent(getApplicationContext(),
									BarActivity.class));
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false).commit();
						}
					}
				}).start();
			}
		}
		// �ı�ת��Ϊ��*��
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����7:03:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��رհ�ť
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			setNull();
			break;
		case R.id.btn_con:
			// ע��ȷ����ť
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (epass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showToast(getApplicationContext(), "������ȷ������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {
						editChanger();
						if (isTrue) {
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"�����ʽ��������+��ĸ���");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showToast(getApplicationContext(),
										"ע��ɹ�");
								setNull();
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.GONE);
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"���벻��6λ");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "��֤���벻һ�£�");
					}
				}
			}
			break;
		case R.id.btn_login:
			// ��¼��ť
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (ip.isEmpty()) {// IP��ַΪ��
				DiyToast.showToast(getApplicationContext(), "������IP��ַ");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½��α�
				if (cursor.moveToNext()) {
					// ��½�ɹ�
					IP_NUMBER = ip;
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
					// ��ס���롢�Զ���¼
					if (cb_autologin.isChecked() && cb_rember.isChecked()) {// ��ס���롢�Զ���¼
						sharedPreferences.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.commit();
					} else if (cb_rember.isChecked()) {// ��ס����
						sharedPreferences.edit().putBoolean("autologin", false)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.commit();
					} else {// ȫ��û��
						sharedPreferences.edit().putBoolean("autologin", false)
								.putBoolean("rember", false)
								.putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.commit();
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "�û����������������");
				}
			}
			break;
		case R.id.btn_reg:
			// ע�ᰴť
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			setNull();
			break;
		default:
			break;
		}
	}

	/*
	 * @��������setNull
	 * 
	 * @�� �ܣ������л��������EditText����
	 * 
	 * @ʱ �䣺����7:32:06
	 */
	private void setNull() {
		// TODO Auto-generated method stub
		et_epass.setText("");
		et_euser.setText("");
		et_ip.setText("");
		et_pass.setText("");
		et_repass.setText("");
		et_user.setText("");
	}

	/*
	 * @��������editChanger
	 * 
	 * @�� �ܣ������������ݴ�С
	 * 
	 * @����ֵ��isTrue
	 * 
	 * @ʱ �䣺����7:25:33
	 */
	private void editChanger() {
		// TODO Auto-generated method stub
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 4) {// ����������4,����0�㣬����5��������5��������6������С�ڣ������ڣ� 6 ��
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}
}
