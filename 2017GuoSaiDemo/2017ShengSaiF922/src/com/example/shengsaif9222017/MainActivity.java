package com.example.shengsaif9222017;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @�ļ�����MainActivity.java
 * @��������¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-22
 */
public class MainActivity extends Activity {
	private EditText et_ip;// IP��ַ
	private EditText et_user;// �û���
	private EditText et_pass;// ����
	private Button btn_login;// ��¼��ť
	static String IP_NUMBER;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip.setText("17.1.10.12");
		et_pass.setText("123456");
		et_user.setText("bizideal");
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub\
				if (et_ip.getText().toString().isEmpty()) {// IP��ַΪ��
					DiyToast.showTaost(getApplicationContext(), "������IP��ַ");
				} else if (et_user.getText().toString().isEmpty()) {// �û���Ϊ��
					DiyToast.showTaost(getApplicationContext(), "�������û���");
				} else if (et_pass.getText().toString().isEmpty()) {// ����Ϊ��
					DiyToast.showTaost(getApplicationContext(), "����������");
				} else {
					// �û���������ֱ���ȷ
					if (et_user.getText().toString().equals("bizideal")
							&& et_pass.getText().toString().equals("123456")) {
						IP_NUMBER = et_ip.getText().toString();// IP��ַ
						Intent intent = new Intent(getApplicationContext(),
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						DiyToast.showTaost(getApplicationContext(),
								"�û����������������");
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

}
