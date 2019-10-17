package com.example.androidshaperlogin;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

/*
 * @�ļ�����MainActivity.java
 * @��������ɵ�½
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-4
 */
public class MainActivity extends Activity {
	private EditText et_user, et_pass, et_ip;// �û��������롢IP��ַ�ı���
	private SharedPreferences sharedPreferences;
	private String user, pass, ip;
	private LinearLayout line_login;
	private LinearLayout line_reg;
	private Button btn_login, btn_reg, btn_con, btn_cls;
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// ��
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_epass.getText().toString();
				if (euser.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�û�������Ϊ��");
				} else if (epass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "���벻��Ϊ��");
				} else if (repass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "ȷ�����벻��Ϊ��");
				} else {
					if (epass.equals(repass)) {
						sharedPreferences.edit().putString("user", euser)
								.putString("pass", epass).commit();
						DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
					} else {
						DiyToast.showToast(getApplicationContext(), "�����������벻һ��");
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

	/*
	 * @��������initView
	 * 
	 * @�� �ܣ��󶨿ؼ�
	 * 
	 * @ʱ �䣺����4:56:58
	 */
	private void initView() {
		// TODO Auto-generated method stub
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
	}
}
