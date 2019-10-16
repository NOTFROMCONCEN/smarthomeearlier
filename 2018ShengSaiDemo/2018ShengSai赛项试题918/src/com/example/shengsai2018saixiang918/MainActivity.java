package com.example.shengsai2018saixiang918;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

/*
 * @�ļ�����MainActivity.java
 * @��������ɵ�¼����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-17
 */
public class MainActivity extends Activity {
	private Button btn_login;// ��¼��ť
	private Button btn_reg;// ��¼��ť
	private ImageView iv_logo;// ͼƬ

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		iv_logo = (ImageView) findViewById(R.id.imageView1);
		// ���ذ�ť
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
		// ���ö���
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		iv_logo.startAnimation(scaleAnimation);
		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {// ������ʼ
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {// ��������
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {// ����ֹͣ
				// TODO Auto-generated method stub
				// ��ʾ����
				btn_login.setVisibility(View.VISIBLE);
				btn_reg.setVisibility(View.VISIBLE);
			}
		});
		// ���ð�ť����¼�
		// ��¼
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(intent);
			}
		});
		// ע��
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						RegActivity.class);
				startActivity(intent);
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
