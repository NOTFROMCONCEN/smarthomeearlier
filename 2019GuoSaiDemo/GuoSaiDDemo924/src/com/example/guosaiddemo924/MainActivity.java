package com.example.guosaiddemo924;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

/*
 * @�ļ�����MainActivity.java
 * @��������¼��ע��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-24
 */
public class MainActivity extends Activity implements OnClickListener {
	private RelativeLayout re_login;// ��¼����
	private RelativeLayout re_reg;// ע�����
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private EditText et_repass;// ȷ������
	private EditText et_ip;// IP��ַ
	private EditText et_epass;// ע������
	private EditText et_euser;// ע���û���
	private CheckBox cb_rember;// ��ס����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_con;// ȷ����ť
	private Button btn_cls;// �رհ�ť
	private String user, pass, ip, euser, epass, repass;
	public static String IP_NUMBER;
	private boolean True = false;// ���볤��
	// ������ʽ
	private Pattern pattern;
	private Matcher matcher;
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// sharedPreferences�洢
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		initView();// ��
		// ���õ���¼�
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// ��ס����
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
			}
		}
		// �����ַ�ת��
		et_epass.setTransformationMethod(new TextChanger());
		et_pass.setTransformationMethod(new TextChanger());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����8:07:37
	 */
	private void initView() {
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		re_login = (RelativeLayout) findViewById(R.id.re_login);
		re_reg = (RelativeLayout) findViewById(R.id.re_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		re_login.setVisibility(View.VISIBLE);
		re_reg.setVisibility(View.INVISIBLE);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			re_login.setVisibility(View.VISIBLE);
			re_reg.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.isEmpty()) {// �û���Ϊ��
				DiyToast.showTaost(getApplicationContext(), "�������û���");
			} else if (epass.isEmpty()) {// ����Ϊ��
				DiyToast.showTaost(getApplicationContext(), "����������");
			} else if (repass.isEmpty()) {// ȷ������Ϊ��
				DiyToast.showTaost(getApplicationContext(), "������ȷ������");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username =?",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�\
					DiyToast.showTaost(getApplicationContext(), "�û����Ѵ���");
				} else {
					if (epass.equals(repass)) {
						oncheck_textsize();
						if (True) {
							// ��������ַ��Ƿ�Ϊ�����ֻ��ߴ���ĸ
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showTaost(getApplicationContext(),
										"�����ʽΪ������+��ĸ");
							} else {
								db.execSQL(
										"insert into user (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showTaost(getApplicationContext(),
										"ע��ɹ�");
								re_login.setVisibility(View.VISIBLE);
								re_reg.setVisibility(View.INVISIBLE);
							}
						} else {
							DiyToast.showTaost(getApplicationContext(),
									"���볤�Ȳ�����λ");
						}
					} else {
						DiyToast.showTaost(getApplicationContext(), "�����������벻һ��");
					}
				}
			}
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			if (user.isEmpty()) {// �û���Ϊ��
				DiyToast.showTaost(getApplicationContext(), "�û�������Ϊ��");
			} else if (pass.isEmpty()) {// ����Ϊ��
				DiyToast.showTaost(getApplicationContext(), "���벻��Ϊ��");
			} else if (ip.isEmpty()) {// IP��ַΪ��
				DiyToast.showTaost(getApplicationContext(), "IP��ַ����Ϊ��");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// �½����ݿ��α�
				if (cursor.moveToNext()) {
					// IP��ַ
					IP_NUMBER = ip;
					// ��ת
					Intent intent = new Intent(getApplicationContext(),
							MenuActivity.class);
					startActivity(intent);
					finish();
					if (cb_rember.isChecked()) {
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("ip", ip).putString("user", user)
								.putString("pass", pass).commit();
					} else {
						sharedPreferences.edit().putBoolean("rember", false)
								.putString("ip", ip).putString("user", user)
								.putString("pass", pass).commit();
					}
				} else {
					DiyToast.showTaost(getApplicationContext(), "�û����������������");
				}
			}
			break;
		case R.id.btn_reg:
			// ע��
			re_login.setVisibility(View.INVISIBLE);
			re_reg.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}

	/*
	 * @��������oncheck_textsize
	 * 
	 * @�� �ܣ�����ı�����
	 * 
	 * @ʱ �䣺����8:20:40
	 */
	private void oncheck_textsize() {
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 3) {
					True = true;
				} else {
					True = false;
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
