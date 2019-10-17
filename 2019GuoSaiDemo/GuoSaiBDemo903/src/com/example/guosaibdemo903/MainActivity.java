package com.example.guosaibdemo903;

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
 * @��������ɵ�¼������������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-3
 */
public class MainActivity extends Activity {
	private Button btn_login;
	private EditText et_ip;
	public static String ip_number;// IP��ַ
	private SeekBar sk_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setProgress(0);// seekbar��������ǰֵ
		sk_1.setMax(100);// seekbar���ֵ
		et_ip.setText("1");
		ip_number = et_ip.getText().toString();
		Intent intent = new Intent(MainActivity.this, IndexActivity.class);
		startActivity(intent);
		finish();
		// ���ð�ť����¼�
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {// ���IP��ַΪ��
					DiyToast.showToast(MainActivity.this, "������IP��ַ");
				} else {
					ip_number = et_ip.getText().toString();
					Intent intent = new Intent(MainActivity.this,
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
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(MainActivity.this, "IP��ַ����Ϊ��");
						sk_1.setProgress(0);
					} else {
						// ��ת����һ����
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(MainActivity.this,
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					DiyToast.showToast(MainActivity.this, "�������֤");
					sk_1.setProgress(0);
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