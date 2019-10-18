package com.example.guosaiademo921;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����LoginActivity.java
 * @��������¼
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-21
 */
public class LoginActivity extends Activity {
	private SeekBar sk_1;
	private EditText et_ip;
	private Button btn_login;
	public static String IP_NUMBER;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					IP_NUMBER = et_ip.getText().toString();
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// �����������¼�
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
						sk_1.setProgress(0);// ���ý���
					} else {
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(getApplicationContext(),
								IndexActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub
				Log.e("����������", String.valueOf(seekBar.getProgress()));
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// �������ı�
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
						sk_1.setProgress(0);// ���ý���
					} else {
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(getApplicationContext(),
								IndexActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});
	}
}
