package com.example.shengsaicdemo10072018;

import com.example.shengsaicdemo10072018.fragment.BarActivity;

import android.app.Activity;
import android.content.Intent;
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
 * @��������������¼ע����ת
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-7
 */
public class MainActivity extends Activity {
	private Button btn_login;// ��¼
	private Button btn_reg;// ע��
	private ImageView iv_1;
	private ScaleAnimation scaleAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		iv_1 = (ImageView) findViewById(R.id.imageView1);
		// ���ذ�ť
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
		// "�Զ���¼"
		LoginActivity.sharedPreferences = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (LoginActivity.sharedPreferences.getBoolean("autologin", false) == true) {
			startActivity(new Intent(getApplicationContext(), BarActivity.class));
			finish();
		}
		// ���ö���
		scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		iv_1.setAnimation(scaleAnimation);

		// ���ö��������¼�
		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {// ��ʼ
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {// ����
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {// ����
				// TODO Auto-generated method stub
				btn_login.setVisibility(View.VISIBLE);
				btn_reg.setVisibility(View.VISIBLE);
			}
		});
		// ��¼��ť���
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
			}
		});
		// ע�ᰴť���
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						RegActivity.class));
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
