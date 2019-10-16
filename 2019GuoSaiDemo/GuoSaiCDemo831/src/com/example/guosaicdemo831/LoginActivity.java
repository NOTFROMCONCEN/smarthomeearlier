package com.example.guosaicdemo831;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.logging.SocketHandler;

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
 * @��������¼���ܡ�ʱ����ʾ��������˸
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-31
 */
public class LoginActivity extends Activity {
	private TextView tv_tips_text, tv_time;
	private Button btn_login;
	private EditText et_user, et_port, et_ip, et_pass;
	private int number = 0;
	private String user, pass, ip, port;
	public static String ip_number;// IP��ַ
	private SharedPreferences sharedPreferences;// sharedPreferences�洢

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tv_time = (TextView) findViewById(R.id.tv_time);
		tv_tips_text = (TextView) findViewById(R.id.tv_tips_text);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		// �Զ���¼��ס����
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_port.setText(sharedPreferences.getString("port", null));
			}
		}
		// �������
		handler.post(timeRunnable);
		// ���������ַ�
		et_pass.setTransformationMethod(new TextChanger());
		// ��¼��ť
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_user.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "�������û���");
				} else if (et_port.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "������˿ں�");
				} else if (et_ip.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "������IP��ַ");
				} else if (et_pass.getText().toString().equals("")) {
					DiyToast.showTaost(LoginActivity.this, "����������");
				} else {
					user = et_user.getText().toString();
					pass = et_pass.getText().toString();
					port = et_port.getText().toString();
					ip = et_ip.getText().toString();
					// �ж��û��������Ƿ���ȷ
					if (user.equals("bizideal001") && pass.equals("123456")
							&& !port.equals("") && !ip.equals("")) {
						ip_number = ip;
						Intent intent = new Intent(LoginActivity.this,
								UnLockActivity.class);
						startActivity(intent);
						finish();
						// ����sharedPreferences�洢
						sharedPreferences.edit().putBoolean("rember", true)
								.putString("ip", ip).putString("port", port)
								.putString("pass", pass)
								.putString("user", user).commit();
					} else {
						new AlertDialog.Builder(LoginActivity.this)
								.setTitle("��¼ʧ��")
								.setMessage("������û�������")
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
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ����ʱ����ء�������˸
	 * 
	 * @ʱ �䣺����8:38:22
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_time.setText(sdf.format(new java.util.Date()));
			if (msg.what % 2 == 0) {
				tv_tips_text.setText("");
			} else {
				tv_tips_text.setText("������ϣ����¼......");
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}
