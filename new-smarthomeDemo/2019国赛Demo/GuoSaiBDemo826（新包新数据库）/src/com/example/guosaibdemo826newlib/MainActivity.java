package com.example.guosaibdemo826newlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����MainActivity.java
 * @������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-26
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;// ������
	private EditText et_ip;// �ı���
	private Button btn_login;// ��ť
	static String ip_number;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
		startActivity(intent);
		sk_1.setProgress(0);
		sk_1.setMax(100);
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(MainActivity.this, "������IP��ַ");
						seekBar.setProgress(0);
					} else {
						// ��ת�����ؽ���
					}
				} else {

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