package com.example.guosaibdemo922;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����MainActivity.java
 * @��������¼����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-22
 */
public class MainActivity extends Activity {
	private Button btn_login;
	private SeekBar sk_1;
	private EditText et_ip;
	public static String IP_NUMBER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// �󶨿ؼ�
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// ��¼��ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {// IPΪ��
					DiyToast.showTasot(getApplicationContext(), "IP�Ƿ�");
				} else {
					IP_NUMBER = et_ip.getText().toString();// ��ֵIP��ַ
					// ��ת
					Intent intent = new Intent(getApplicationContext(),
							LoadingActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// ����SeekBar����
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showTasot(getApplicationContext(), "������IP��ַ");
						sk_1.setProgress(0);
					} else {
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(getApplicationContext(),
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					sk_1.setProgress(0);
					DiyToast.showTasot(getApplicationContext(), "�������֤");
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// �������ı�
				// TODO Auto-generated method stub

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
