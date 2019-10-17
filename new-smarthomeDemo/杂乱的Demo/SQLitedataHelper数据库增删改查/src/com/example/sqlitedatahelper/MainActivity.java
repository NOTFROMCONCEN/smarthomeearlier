package com.example.sqlitedatahelper;

import java.text.SimpleDateFormat;
import java.util.TimeZone;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	private TextView tv_main_time;
	private Button btn_main_login;// ��¼
	private Button btn_main_reg;// ע��
	private Button btn_main_updatapass;// �޸�����
	private Button btn_main_user;// �˻�����
	private Button btn_main_sqladd;// ���ݿ�����
	private Button btn_main_sqldelete;// ���ݿ�ɾ��
	private Button btn_main_sqlsearch;// ���ݿ��ѯ
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("��ҳ");
		setContentView(R.layout.activity_main);
		tv_main_time = (TextView) findViewById(R.id.tv_main_time);
		btn_main_login = (Button) findViewById(R.id.btn_main_login);
		btn_main_reg = (Button) findViewById(R.id.btn_main_reg);
		btn_main_sqladd = (Button) findViewById(R.id.btn_main_sqladd);
		btn_main_sqldelete = (Button) findViewById(R.id.btn_main_sqldelete);
		btn_main_sqlsearch = (Button) findViewById(R.id.btn_main_sqlsearch);
		btn_main_updatapass = (Button) findViewById(R.id.btn_main_updata);
		btn_main_user = (Button) findViewById(R.id.btn_main_user);
		// ���ð�ť����¼�
		btn_main_login.setOnClickListener(this);
		btn_main_reg.setOnClickListener(this);
		btn_main_sqladd.setOnClickListener(this);
		btn_main_sqldelete.setOnClickListener(this);
		btn_main_sqlsearch.setOnClickListener(this);
		btn_main_updatapass.setOnClickListener(this);
		btn_main_user.setOnClickListener(this);
		// �����߳�
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_main_time.setText(sdf.format(new java.util.Date()));
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	/*
	 * @��������onClick(View v)
	 * 
	 * @�� �ܣ���Ӧ����¼�
	 * 
	 * @ʱ �䣺����6:39:46
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_main_login:// ��¼����
			intent = new Intent(MainActivity.this, Activity_Login.class);
			startActivity(intent);
			DiyToast.showToast(MainActivity.this, "��ת��¼������");
			break;
		case R.id.btn_main_reg:// ע�Ṧ��
			intent = new Intent(MainActivity.this, Activity_Reg.class);
			startActivity(intent);
			DiyToast.showToast(MainActivity.this, "��תע�Ṧ����");
			break;
		case R.id.btn_main_sqladd:// ���ݿ�����

			break;
		case R.id.btn_main_sqldelete:// ���ݿ�ɾ��

			break;
		case R.id.btn_main_sqlsearch:// ���ݿ�����

			break;
		case R.id.btn_main_updata:// �޸����빦��
			intent = new Intent(MainActivity.this, Activity_UpdataPass.class);
			startActivity(intent);
			DiyToast.showToast(MainActivity.this, "��ת�����������");
			break;
		case R.id.btn_main_user:// �˻�����
			intent = new Intent(MainActivity.this, Activity_User.class);
			startActivity(intent);
			DiyToast.showToast(MainActivity.this, "��ת�˻���������");
			break;
		default:
			break;
		}
	}

}
