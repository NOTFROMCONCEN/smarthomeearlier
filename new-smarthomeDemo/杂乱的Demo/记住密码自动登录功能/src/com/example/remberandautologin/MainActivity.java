package com.example.remberandautologin;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @�ļ�����MainActivity.java
 * @��������ɵ�¼ע���ס�����Զ���¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-1
 */
public class MainActivity extends Activity implements OnClickListener {
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ע�ᰴť
	private Button btn_reg_con;// ע��ȷ����ť
	private Button btn_reg_cls;// ע��رհ�ť
	private EditText et_user, et_pass, et_reg_pass, et_reg_user;// �ı���
	private String user, pass, reg_user, reg_pass;// String��ֵ
	private LinearLayout line_reg;// ע�����
	private LinearLayout line_login;// ��¼����
	private SharedPreferences sharedPreferences;// sharedPreferences�洢
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private CheckBox cb_rember, cb_autologin;// �Զ���¼��ס���븴ѡ��
	private AlertDialog.Builder builder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("��¼");
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg_cls = (Button) findViewById(R.id.btn_reg_cls);
		btn_reg_con = (Button) findViewById(R.id.btn_reg_con);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_reg_pass = (EditText) findViewById(R.id.et_reg_pass);
		et_reg_user = (EditText) findViewById(R.id.et_reg_user);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// ���ݿ�
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// ���ð�ť����¼�
		btn_login.setOnClickListener(this);
		btn_reg_cls.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_reg_con.setOnClickListener(this);
		// �Զ���¼��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			// �����ס����booleanֵΪtrue
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
			// ����Զ���¼booleanֵΪtrue
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				DiyToast.showToast(MainActivity.this, "�����Զ���¼");
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);// �����ӳ�
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {// ����ӳ�ʱ���ڸ�ѡ���Զ���¼��û�б�ȡ����ѡ�������Զ���¼
							Intent intent = new Intent(MainActivity.this,
									OkActivity.class);
							startActivity(intent);
						} else {// ��������Զ���¼booleanֵΪfalse
							sharedPreferences.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", false)
									.putString("user", "0")
									.putString("pass", "0").commit();// ����sharedPreferences�洢
						}
					}
				}).start();
			}
		} else {

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������onClick
	 * 
	 * @�� �ܣ���Ӧ��ť���
	 * 
	 * @ʱ �䣺����6:42:41
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// ��¼��ť
			user = et_user.getText().toString();// �û���
			pass = et_pass.getText().toString();// ����
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward = ?",
					new String[] { user, pass });// �½����ݿ�ָ��
			if (user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(MainActivity.this, "�������û���");
			} else if (pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(MainActivity.this, "����������");
			} else if (!cursor.moveToNext()) {// ������ݿ���ƥ����
				DiyToast.showToast(MainActivity.this, "�û����������������");
			} else {
				DiyToast.showToast(MainActivity.this, "��½�ɹ�");
				// ��ת
				Intent intent = new Intent(MainActivity.this, OkActivity.class);
				startActivity(intent);
				// �жϸ�ѡ��ѡ��״̬
				if (cb_autologin.isChecked() && cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).commit();// ����sharedPreferences�洢
				} else if (cb_autologin.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", true)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).commit();// ����sharedPreferences�洢
				} else if (cb_rember.isChecked()) {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", true).putString("user", user)
							.putString("pass", pass).commit();// ����sharedPreferences�洢
				} else {
					sharedPreferences.edit().putBoolean("autologin", false)
							.putBoolean("rember", false)
							.putString("user", user).putString("pass", pass)
							.commit();// ����sharedPreferences�洢
				}
			}
			break;
		case R.id.btn_reg:
			// ע�ᰴť
			setTitle("ע��");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_reg_cls:
			// ע��رհ�ť
			setTitle("��¼");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			break;
		case R.id.btn_reg_con:
			// ע��򿪰�ť
			reg_pass = et_reg_pass.getText().toString();// �û���
			reg_user = et_reg_user.getText().toString();// ����
			if (reg_user.equals("")) {// ����û���Ϊ��
				DiyToast.showToast(MainActivity.this, "�������û���");
			} else if (reg_pass.equals("")) {// �������Ϊ��
				DiyToast.showToast(MainActivity.this, "����������");
			} else {
				Cursor cursor1 = db.rawQuery(
						"select * from user where username = ?",
						new String[] { reg_user });// �½����ݿ�ָ��
				if (cursor1.moveToNext()) {
					DiyToast.showToast(MainActivity.this, "�û����Ѵ���");
				} else {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { reg_user, reg_pass });// �������ݿ�
					DiyToast.showToast(MainActivity.this, "ע��ɹ�");
					line_login.setVisibility(View.VISIBLE);
					line_reg.setVisibility(View.GONE);
				}
			}
			break;
		default:
			break;
		}
	}
}
