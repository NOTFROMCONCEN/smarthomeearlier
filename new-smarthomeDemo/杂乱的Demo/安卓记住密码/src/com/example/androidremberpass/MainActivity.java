package com.example.androidremberpass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

/*
 * @�ļ�����MainActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-10
 */
public class MainActivity extends Activity {
	// ������ʽ�������ʽ����
	private Matcher matcher;
	private Pattern pattern;
	private boolean isTrue = false;
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private Button btn_updata_pass;// �޸�����
	private Button btn_reback_pass;// �һ�����
	private Spinner sp_user;// �����˵�
	private EditText et_user, et_pass, et_ip;// �ı���
	private CheckBox cb_autologin;// �Զ���¼
	private CheckBox cb_rember;// ��ס����
	private RadioButton ra_chose_user;// ѡ���û�
	private RadioButton ra_set_user;// �����û�
	public static SharedPreferences sharedPreferences;// sharedPreferences�洢

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("��¼");
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// ��ס�����Զ���¼
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String user, pass, ip;
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				ip = et_ip.getText().toString();
				if (ra_chose_user.isChecked()) {
					if (pass.isEmpty() || ip.isEmpty()) {
						DiyToast.showToas(getApplicationContext(), "�����пհ���Ŀ");
					} else {
					}
				}

			}
		});
	}
}