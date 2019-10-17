package com.example.guosaicdemo905;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼��ʱ����ʾ��������˸
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-4
 */
public class LoginActivity extends Activity {
	private TextView tv_tips_text;// ��ʾ����
	private TextView tv_login_time;// ʱ��
	private EditText et_user, et_port, et_ip, et_pass;// �ı���
	private String user, pass, port, ip, datetimevalue;
	private int number;
	static String ip_number;// IP��ַ
	private Button btn_login;// ��¼��ť
	private SharedPreferences sharedPreferences;// sharedPreferences�洢

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_tips_text = (TextView) findViewById(R.id.tv_tips_text);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		DiyToast.showToast(getApplicationContext(), "11");
		// ���ü�ס���롢�û����Ȳ���
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		} else {
			et_ip.setText("");
			et_pass.setText("");
			et_port.setText("");
			et_user.setText("");
		}
		// �������
		handler.post(timeRunnable);
		// ���ð�ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				port = et_port.getText().toString();// �˿�
				ip = et_ip.getText().toString();// IP��ַ
				if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (port.isEmpty()) {// �˿ں�Ϊ��
					DiyToast.showToast(getApplicationContext(), "������˿ں�");
				} else if (ip.isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else {
					if (!ip.equals("") && !port.equals("")
							&& user.equals("bizideal") && pass.equals("123456")) {// �����ȡ����ֵ����Ҫ��
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port).commit();// ����sharedPreferences�洢
						// �趨IP��ַ
						ip_number = ip;
						// ��ת
						Intent intent = new Intent(LoginActivity.this,
								SeekBarActivity.class);
						startActivity(intent);
						finish();
					} else {// ������Ҫ��
						new AlertDialog.Builder(getApplicationContext())
								.setTitle("��¼ʧ��")
								.setMessage("�û����������������")
								.setPositiveButton("Ok",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										});
					}
				}
			}
		});
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ�䡢������˸
	 * 
	 * @ʱ �䣺����8:29:44
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ������˸
			if (msg.arg1 % 2 == 0) {
				tv_tips_text.setText("������ϣ����¼......");
			} else {
				tv_tips_text.setText("");
			}
			// ����ʱ��
			SimpleDateFormat formater = new SimpleDateFormat(
					"yyyy��MM��dd�� HH:mm:ss");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			datetimevalue = formater.format(new java.util.Date());
			tv_login_time.setText(datetimevalue);
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.arg1 = number;
			handler.sendMessage(msg);
		}
	};
}
