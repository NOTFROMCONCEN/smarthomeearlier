package com.example.guosaiddemo924;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

/*
 * @�ļ�����MenuActivity.java
 * @������ѡ����ת
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-24
 */
public class MenuActivity extends Activity {
	private ImageView iv_base;// ���ݲɼ�
	private ImageView iv_link;// �龰
	private ImageView iv_control;// ����
	static String TIME;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		iv_base = (ImageView) findViewById(R.id.iv_chuanganqi);
		iv_control = (ImageView) findViewById(R.id.iv_shebeikongzhi);
		iv_link = (ImageView) findViewById(R.id.iv_qingjingmoshi);
		// ����¼�
		iv_base.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						BaseActivity.class);
				startActivity(intent);
			}
		});
		iv_control.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						ControlActivity.class);
				startActivity(intent);
			}
		});
		iv_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						LinkActivity.class);
				startActivity(intent);
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ��
	 * 
	 * @ʱ �䣺����8:49:07
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			TIME = simpleDateFormat.format(new java.util.Date());
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
}