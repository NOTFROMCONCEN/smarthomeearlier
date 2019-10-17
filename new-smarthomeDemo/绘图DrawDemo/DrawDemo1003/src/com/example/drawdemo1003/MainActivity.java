package com.example.drawdemo1003;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.drawdemo1003.helpclass.DiyDialog;
import com.example.drawdemo1003.helpclass.DiyToast;
import com.example.drawdemo1003.mysql.MyDataBaseHelper;

/*
 * @�ļ�����MainActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-3
 */
public class MainActivity extends Activity implements OnClickListener {
	// ���ݿ�
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// ����
	// IP��ַ
	public static String IP_NUMBER;
	// ����
	private LinearLayout line_login;// ��¼����
	private LinearLayout line_reg;// ע�����

	// ��¼����
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private EditText et_user, et_pass, et_ip;// �û��������롢IP��ַ�ı���
	private String user, pass, ip;

	// ע�Ṧ��
	private Button btn_con;// ע��ȷ��
	private Button btn_cls;// ע��ر�
	private EditText et_euser, et_epass, et_repass;// �û��������롢ȷ�������ı���
	private String euser, epass, repass;

	// ��ס���빦��
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// initView();// �󶨿ؼ�
		autoFindView();
		rember_pass();// ��ס����
		DiyDialog.showDialog(MainActivity.this, "��ӭ", "��ӭʹ�����ܼҾӿ���ϵͳ", true,
				false);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// ע�ᰴť���
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.INVISIBLE);// ����
				line_reg.setVisibility(View.VISIBLE);// ��ʾ
				setnull();// ����ı���
			}
		});
		// ע��رհ�ť���
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_reg.setVisibility(View.INVISIBLE);// ����
				line_login.setVisibility(View.VISIBLE);// ��ʾ
				setnull();// ����ı���
			}
		});
		btn_con.setOnClickListener(this);// ע��ȷ����ť
		btn_login.setOnClickListener(this);// ��¼��ť
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
	 * @ʱ �䣺����7:04:23
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// ��¼
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
				if (cursor.moveToNext()) {// ���ݿ��ƶ�
					DiyToast.showToast(getApplicationContext(), "��½�ɹ�");
					IP_NUMBER = ip;
					// ��ת
					Intent intent = new Intent(getApplicationContext(),
							LoadingActivity.class);
					startActivity(intent);
					finish();
					// ��ס����
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else {
					DiyDialog.showDialog(MainActivity.this, "��¼ʧ��",
							"�û����������������", true, false);
				}
			}
			break;
		case R.id.btn_con:
			// ע��
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
						"select * from user where username = ? ",
						new String[] { euser });// �½����ݿ��α�
				if (cursor.moveToNext()) {// �α��ƶ�
					DiyDialog.showDialog(MainActivity.this, "ע��ʧ��", "�û����Ѵ���",
							true, false);
				} else {
					if (epass.equals(repass)) {// ��������һ��
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
						setnull();// ����ı���
						line_reg.setVisibility(View.INVISIBLE);// ����
						line_login.setVisibility(View.VISIBLE);// ��ʾ
					} else {
						DiyDialog.showDialog(MainActivity.this, "ע��ʧ��",
								"�����������벻һ��", true, false);
					}
				}
			}
			break;
		default:
			break;
		}
	}

	/*
	 * @��������setnull
	 * 
	 * @�� �ܣ������ı�������Ϊ��
	 * 
	 * @ʱ �䣺����7:18:56
	 */
	private void setnull() {
		et_epass.setText("");
		et_euser.setText("");
		et_ip.setText("");
		et_pass.setText("");
		et_repass.setText("");
		et_user.setText("");
	}

	/*
	 * @��������rember_pass
	 * 
	 * @�� �ܣ���ס���빦��
	 * 
	 * @ʱ �䣺����7:24:55
	 */
	private void rember_pass() {
		// TODO Auto-generated method stub
		if (sharedPreferences != null) {
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
		}
	}

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
