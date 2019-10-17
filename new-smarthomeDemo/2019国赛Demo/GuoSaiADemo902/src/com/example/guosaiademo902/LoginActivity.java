package com.example.guosaiademo902;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����LoginActivity.java
 * @��������ɵ�¼����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-2
 */
public class LoginActivity extends Activity {
	private SeekBar sk_1;
	private EditText et_ip;
	private Button btn_login;
	public static String ip_number;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sk_1 = (SeekBar) findViewById(R.id.sk_1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// ���û���������
		sk_1.setMax(100);
		sk_1.setProgress(0);
		// ���ð�ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					DiyToast.showToast(LoginActivity.this, "������IP��ַ");
				} else {
					ip_number = et_ip.getText().toString();
					Intent intent = new Intent(LoginActivity.this,
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// ���û������¼�
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(LoginActivity.this, "������IP��ַ");
						sk_1.setProgress(0);
					} else {
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								IndexActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "����ɻ�����֤");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					DiyToast.showToast(LoginActivity.this, "������IP��ַ");
					sk_1.setProgress(0);
				}
			}
		});
	}
}
