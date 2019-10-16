package com.example.guosaiademo816drawline;

import java.nio.channels.SocketChannel;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @�ļ�����LoginActivity.java
 * @���������û���ɵ�¼�����ӷ���������
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-16
 */
public class LoginActivity extends Activity implements OnSeekBarChangeListener {
	private SeekBar sk_login;// SeekBar������
	private EditText et_ip;// IP�ı���
	private Button btn_login;// ��¼��ť
	static String ip;// IP��ַ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sk_login = (SeekBar) findViewById(R.id.sk_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// ����SeekBar����
		sk_login.setOnSeekBarChangeListener(this);
		sk_login.setMax(100);
		sk_login.setProgress(0);
		// ���õ�¼��ť����
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					// ���IP��ַ�ı���Ϊ��
					Toast.makeText(LoginActivity.this, "������IP��ַ",
							Toast.LENGTH_SHORT).show();
				} else if (sk_login.getProgress() != 100) {
					// ���SeekBarû�б�������һ��
					Toast.makeText(LoginActivity.this, "����ɻ�����֤",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(LoginActivity.this, "��½�ɹ�",
							Toast.LENGTH_SHORT).show();
					ip = et_ip.getText().toString();
					Intent intent = new Intent(LoginActivity.this,
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	@Override
	// ��SeekBar�ı�
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
	}

	@Override
	// ��SeekBar��ʼ����
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	// ��SeekBarֹͣ����
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		if (seekBar.getProgress() == 100) {
			// �ж�SeekBar�ǲ�����������
			if (!et_ip.getText().toString().equals("")) {
				ip = et_ip.getText().toString();
				Intent intent = new Intent(LoginActivity.this,
						IndexActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(LoginActivity.this, "������IP��ַ",
						Toast.LENGTH_SHORT).show();
				sk_login.setProgress(0);
			}
		} else {
			sk_login.setProgress(0);
			Toast.makeText(LoginActivity.this, "�뻬�������֤", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
