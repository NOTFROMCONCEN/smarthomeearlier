package com.example.guosaiddemo907;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.drm.DrmStore.Action;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @�ļ�����MainActivity.java
 * @��������ɵ�¼��ע�ᡢ��ס����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-7
 */
public class MainActivity extends Activity implements OnClickListener {
	// ��¼����
	private LinearLayout line_login;
	private Button btn_login, btn_reg;
	private EditText et_user, et_pass, et_ip;
	private CheckBox cb_rember;
	private String user, pass, ip;

	// ע�����
	private Button btn_con, btn_cls;
	private LinearLayout line_reg;
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// ��������
	private boolean isTrue = false;
	// �����ʽ
	private Pattern pattern;
	private Matcher matcher;
	// sharedPreferences�洢
	private SharedPreferences sharedPreferences;
	// Ip��ַ
	public static String ip_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// ������title
		setContentView(R.layout.activity_main);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(this, "db_user.db", null, 2);
		db = dbHelper.getWritableDatabase();
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.INVISIBLE);
		// ���ð�ť����¼�
		btn_reg.setOnClickListener(this);
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_user.setText(sharedPreferences.getString("user", null)
						.toString());
				et_ip.setText(sharedPreferences.getString("ip", null)
						.toString());
				Cursor cursor = db.rawQuery(
						"select * from tb_userInFo where username = ?",
						new String[] { et_user.getText().toString() });
				if (cursor.getCount() != 0) {
					cursor.moveToFirst();
					et_pass.setText(cursor.getString(
							cursor.getColumnIndex("passward")).toString());
					DiyToast.showToast(getApplicationContext(), cursor
							.getString(cursor.getColumnIndex("passward"))
							.toString());
				}
			}
		} else {
			et_user.setText("");
			et_ip.setText("");
			et_pass.setText("");
		}
		// �����ı�����ʾ�ַ�
		et_epass.setTransformationMethod(new WordReplacement());
		et_pass.setTransformationMethod(new WordReplacement());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// �����������
	private void oncheck() {
		et_epass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (start > 4) {// ����4������������λʱ����
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���������¼�
	 * 
	 * @ʱ �䣺����8:22:38
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// ע��ر�
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			break;
		case R.id.btn_con:
			// ע��ȷ��
			euser = et_euser.getText().toString();// �û���
			epass = et_epass.getText().toString();// ����
			repass = et_repass.getText().toString();// ȷ������
			if (euser.equals("")) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (epass.equals("")) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (repass.equals("")) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "������ȷ������");
			} else {
				if (epass.equals(repass)) {// ����һ��
					Cursor cursor = db.rawQuery(
							"select * from tb_userInFo where username = ?",
							new String[] { euser });// �½����ݿ�ָ��
					if (cursor.moveToNext()) {
						DiyToast.showToast(getApplicationContext(), "�û����Ѵ��ڣ�");
					} else {
						oncheck();
						if (isTrue) {
							// ��������ַ��Ƿ�Ϊ�����ֻ��ߴ���ĸ
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"�����ʽΪ������ + ��ĸ��ʽ");
							} else {
								db.execSQL(
										"insert into tb_userInFo (username,passward)values(?,?)",
										new String[] { euser, epass });
								DiyToast.showToast(getApplicationContext(),
										"ע��ɹ�");
								line_login.setVisibility(View.VISIBLE);
								line_reg.setVisibility(View.INVISIBLE);
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"���벻����λ");
						}
					}
				} else {
					DiyToast.showToast(getApplicationContext(), "�����������벻һ��");
				}
			}
			break;
		case R.id.btn_login:
			// ��¼
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			ip = et_ip.getText().toString();// IP��ַ
			Cursor cursor = db
					.rawQuery(
							"select * from tb_userInFo where username = ? and passward = ?",
							new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// �û���Ϊ��
				DiyToast.showToast(getApplicationContext(), "�������û���");
			} else if (pass.equals("")) {// ����Ϊ��
				DiyToast.showToast(getApplicationContext(), "����������");
			} else if (ip.equals("")) {// IP��ַΪ��
				DiyToast.showToast(getApplicationContext(), "������IP��ַ");
			} else if (!cursor.moveToNext()) {// ���ݿ���ƥ����
				DiyToast.showToast(getApplicationContext(), "������û����������");
			} else {
				// ��ת
				ip_number = ip;
				Intent intent = new Intent(MainActivity.this,
						ChoseActivity.class);
				startActivity(intent);
				finish();
				if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("rember", true)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();// ����sharedPreferences
				} else {
					sharedPreferences.edit().putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.putString("ip", ip).commit();// ����sharedPreferences
				}
			}
			break;
		case R.id.btn_reg:
			// ע��
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
