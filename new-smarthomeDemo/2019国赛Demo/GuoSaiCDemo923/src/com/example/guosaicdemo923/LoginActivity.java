package com.example.guosaicdemo923;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
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
 * @��������¼��ʱ�䡢������˸
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-23
 */
public class LoginActivity extends Activity {
	private TextView tv_login_time;// ʱ��
	private TextView tv_login_tips;// ��ʾ
	private EditText et_user;// �û���
	private EditText et_port;// �˿ں�
	private EditText et_ip;// IP��ַ
	private EditText et_pass;// ����
	private Button btn_login;// ��¼��ť
	private int recLen = 0;
	public static String get_time;
	private String user, pass, port;// �û�������IP�˿ں�String������ֵ
	public static String IP_NUMBER;// IP��ַ
	// sharedPreferences�洢
	private SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		btn_login = (Button) findViewById(R.id.btn_login);
		// ��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		// �������
		handler.post(timeRunnable);
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// �û���
				pass = et_pass.getText().toString();// ����
				port = et_port.getText().toString();// �˿ں�
				IP_NUMBER = et_ip.getText().toString();// IP��ַ
				if (user.isEmpty()) {// �û���Ϊ��
					DiyToast.showToast(getApplicationContext(), "�������û���");
				} else if (port.isEmpty()) {// �˿ں�Ϊ��
					DiyToast.showToast(getApplicationContext(), "������˿ں�");
				} else if (IP_NUMBER.isEmpty()) {// IP��ַΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else if (pass.isEmpty()) {// ����Ϊ��
					DiyToast.showToast(getApplicationContext(), "����������");
				} else {
					if (user.equals("bizideal") && pass.equals("123456")) {
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass)
								.putString("ip", IP_NUMBER)
								.putString("port", port).commit();// ���뱾�ش洢
						IP_NUMBER = et_ip.getText().toString();// ��ȡIP
						Intent intent = new Intent(getApplicationContext(),
								UnLockActivity.class);// ��ת
						startActivity(intent);
						finish();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
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
										}).show();
					}
				}
			}
		});
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ�䡢��˸��
	 * 
	 * @ʱ �䣺����8:23:10
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ��˸
			if (msg.arg1 % 2 == 0) {
				tv_login_tips.setVisibility(View.INVISIBLE);
			} else {
				tv_login_tips.setVisibility(View.VISIBLE);
			}
			// ʱ��
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy��MM��dd�� HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			get_time = simpleDateFormat.format(new java.util.Date());
			tv_login_time.setText(get_time);
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			recLen++;
			Message msg = handler.obtainMessage();
			msg.arg1 = recLen;
			handler.sendMessage(msg);
		}
	};
}
