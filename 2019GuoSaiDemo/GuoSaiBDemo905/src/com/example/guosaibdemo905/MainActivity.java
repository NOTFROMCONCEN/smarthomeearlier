package com.example.guosaibdemo905;

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
 * @��������ɵ�½����������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-6
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;
	private EditText et_ip;
	private Button btn_login;
	static String ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("");
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// ���ð�ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {// IPΪ��
					DiyToast.showToast(getApplicationContext(), "������IP��ַ");
				} else {
					ip = et_ip.getText().toString();
					Intent intent = new Intent(MainActivity.this,
							LoadingActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// ���û�����
		sk_1.setMax(100);// ���
		sk_1.setProgress(0);// ��ǰ
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// ֹͣ����
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {// �����ǰֵ==100
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(getApplicationContext(), "������IP��ַ");
						sk_1.setProgress(0);
					} else {
						ip = et_ip.getText().toString();
						Intent intent = new Intent(MainActivity.this,
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// ��ʼ����
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// ���ȸı�
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
