package com.example.shengsaiademo10052018;

import java.lang.reflect.Field;

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

import com.example.shengsaiademo10052018.fragment.BarActivity;
import com.example.shengsaiademo10052018.mysql.MyDataBaseHelper;
import com.example.shengsaiademo10052018.toast.DiyToast;

/*
 * @�ļ�����LoginActivity.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-5
 */
public class LoginActivity extends Activity {
	private Button btn_login, btn_reg;// ��¼ע��
	private EditText et_ip, et_user, et_pass;// IP�û�������
	private String ip, user, pass;// IP�û�������
	private CheckBox cb_rember;// ��ס����
	private CheckBox cb_autologin;// �Զ���¼

	public static String IP_NUMBER;

	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences�洢
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// initView();// �󶨿ؼ�
		autoFindView();
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// ��ס���롢�Զ���¼
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
							startActivity(new Intent(LoginActivity.this,
									BarActivity.class));
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
						}
					}
				}).start();
			}
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// ע�ᰴť����¼�
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this, RegActivity.class));
			}
		});
		// ����Ч��
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
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

		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				ip = et_ip.getText().toString();// IP
				if (ip.isEmpty()) {// IPΪ��
					DiyToast.showToast(getApplicationContext(), "IP��ַ����Ϊ��");
				} else if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });// �½����ݿ��α�
					if (cursor.moveToNext()) {
						// ��ת
						IP_NUMBER = ip;
						startActivity(new Intent(LoginActivity.this,
								BarActivity.class));
						// ��ס�����Զ���¼
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

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ���
	 * 
	 * @ʱ �䣺����8:50:44
	 */
	// private void initView() {
	// // TODO Auto-generated method stub
	// btn_login = (Button) findViewById(R.id.btn_login);
	// btn_reg = (Button) findViewById(R.id.btn_reg);
	// et_ip = (EditText) findViewById(R.id.et_ip);
	// et_user = (EditText) findViewById(R.id.et_user);
	// et_pass = (EditText) findViewById(R.id.et_pass);
	// cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
	// cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	// }

	// �Զ�findViewById(���뱣֤�����ļ��е�id�������һ��)
	private void autoFindView() {
		Class mClass = this.getClass();
		while (true) {
			Field[] fields = mClass.getDeclaredFields();
			for (Field field : fields) {
				if (View.class.isAssignableFrom(field.getType())) {
					String fieldName = field.getName();
					View view = findViewById(getResources().getIdentifier(
							fieldName, "id", getPackageName()));
					if (view != null) {
						field.setAccessible(true);
						try {
							field.set(this, view);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			mClass = mClass.getSuperclass();
			// tip�����޸�Activity.class.getName()������ָ����Ѱ��������
			if (mClass.getName().equals(Activity.class.getName()))
				break;
		}
	}
}
