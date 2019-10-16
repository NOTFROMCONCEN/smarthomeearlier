package com.example.guosaihdemo914;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/*
 * @�ļ�����RegActivity.java
 * @���������ע�Ṧ��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-14
 */
public class RegActivity extends Activity {
	private RadioButton ra_user_logo;// logo�û�
	private RadioButton ra_user_maril;// ������û�
	private RadioButton ra_user_gril;// Ů���û�
	private RadioButton ra_user_qq;// QQ�û�
	private Button btn_con, btn_cls;// ע�ᰴť
	private TextView tv_tips;// ������ʾ
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		tv_tips = (TextView) findViewById(R.id.tv_reg_tips);
		ra_user_gril = (RadioButton) findViewById(R.id.ra_user_gril_reg);
		ra_user_logo = (RadioButton) findViewById(R.id.ra_user_logo_reg);
		ra_user_maril = (RadioButton) findViewById(R.id.ra_user_maril_reg);
		ra_user_qq = (RadioButton) findViewById(R.id.ra_user_qq_reg);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		// ע�ᰴť
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler.post(timeRunnable);
			}
		});
		// �رհ�ť
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		});
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�ע���û�����ʱ
	 * 
	 * @ʱ �䣺����3:26:24
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_user_gril.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				MainActivity.sharedPreferences.edit()
						.putBoolean("user_gril", true).commit();
				switch (msg.what) {
				case 1:
					tv_tips.setText("����ע���û���Ϣ������");
					break;
				case 3:
					tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}

			if (ra_user_logo.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				MainActivity.sharedPreferences.edit()
						.putBoolean("user_logo", true).commit();
				switch (msg.what) {
				case 1:
					tv_tips.setText("����ע���û���Ϣ������");
					break;
				case 3:
					tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}

			if (ra_user_maril.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				MainActivity.sharedPreferences.edit()
						.putBoolean("user_maril", true).commit();
				switch (msg.what) {
				case 1:
					tv_tips.setText("����ע���û���Ϣ������");
					break;
				case 3:
					tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}

			if (ra_user_qq.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				MainActivity.sharedPreferences.edit()
						.putBoolean("user_qq", true).commit();
				switch (msg.what) {
				case 1:
					tv_tips.setText("����ע���û���Ϣ������");
					break;
				case 3:
					tv_tips.setText("�û���Ϣע��ɹ���2��󷵻ص�¼���档");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "ע��ɹ�");
					Intent intent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};

}
