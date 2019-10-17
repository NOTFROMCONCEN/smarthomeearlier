package com.example.phonegetsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-13
 * @author Administrator
 */
public class MainActivity extends Activity implements OnClickListener {
	// ����ؼ�
	private TextView tv_network_state;// ��ϵͳ����������ı�
	private TextView tv_phone_state;// ��ϵͳӲ���������ı�
	private TextView tv_about;// �����ڡ��ı�
	private TextView tv_sys_out;// ���˳����ı�
	private TextView tv_ui;// �������������ı�

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("��ѡ��");
		setContentView(R.layout.activity_main);
		tv_ui = (TextView) findViewById(R.id.tv_ui);
		tv_network_state = (TextView) findViewById(R.id.tv_network_state);
		tv_about = (TextView) findViewById(R.id.tv_about);
		tv_phone_state = (TextView) findViewById(R.id.tv_phone_state);
		tv_sys_out = (TextView) findViewById(R.id.tv_sys_out);
		tv_sys_out.setOnClickListener(this);
		tv_about.setOnClickListener(this);
		tv_network_state.setOnClickListener(this);
		tv_phone_state.setOnClickListener(this);
		if (UIsystem.isMIUI() == true) {
			tv_ui.setText("����������" + "С���ֻ�������Ϊ��MIUIϵͳ��");
		} else {
			tv_ui.setText("����������" + "δ֪");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������onClick(View v)
	 * 
	 * @�� �ܣ��ı��������
	 * 
	 * @�� ����View v - �ı���View
	 * 
	 * @����ֵ����
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_about:
			Intent intent = new Intent(MainActivity.this, About.class);
			startActivity(intent);
			break;
		case R.id.tv_network_state:
			Intent intent2 = new Intent(MainActivity.this, NetworkMessage.class);
			startActivity(intent2);
			break;
		case R.id.tv_phone_state:
			Intent intent3 = new Intent(MainActivity.this, SystemMessage.class);
			startActivity(intent3);
			break;
		case R.id.tv_sys_out:
			finish();
			break;
		default:
			break;
		}
	}
}
