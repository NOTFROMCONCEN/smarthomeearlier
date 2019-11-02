package com.example.ademo;

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

import com.example.ademo.toast.DiyToast;

/**
 * ��¼
 * 
 * @author A
 * 
 */
public class MainActivity extends Activity {
	SeekBar sk_1;
	EditText et_ip;
	Button btn_login;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		/*
		 * ��
		 */
		setContentView(R.layout.activity_main);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// /����Ĭ��IP
		et_ip.setText("18.1.10.7");
		// ��������
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP�Ƿ�");
					sk_1.setProgress(0);
				} else {
					if (sk_1.getProgress() != 100) {
						sk_1.setProgress(0);
						DiyToast.showToast(getApplicationContext(), "����ɻ�����֤");
					} else {
						if (et_ip.getText().toString().equals("18.1.10.7")) {
							startActivity(new Intent(getApplicationContext(),
									LoadingActivity.class));
							finish();
						} else {
							DiyToast.showToast(getApplicationContext(),
									"IP�������");
						}
					}
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
		// ��¼
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "IP�Ƿ�");
				} else {
					if (et_ip.getText().toString().equals("18.1.10.7")) {
						startActivity(new Intent(getApplicationContext(),
								LoadingActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(), "IP�������");
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
