package com.example.guosaigdemo911;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * @�ļ�����MainActivity.java
 * @��������ɶ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-11
 */
public class MainActivity extends Activity {
	private ImageView iv_logo;
	private TextView tv_number;
	private int number = 6;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv_logo = (ImageView) findViewById(R.id.iv_logo);
		tv_number = (TextView) findViewById(R.id.tv_number);
		// ���ö���
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(2000);// ʱ��
		scaleAnimation.setFillAfter(true);// ״̬
		iv_logo.startAnimation(scaleAnimation);
		tv_number.setVisibility(View.INVISIBLE);// ��������ǰ�ı�����
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
			public void onAnimationEnd(Animation animation) {// ��������
				// TODO Auto-generated method stub
				// ��������ʱ��ʾ�ı�
				tv_number.setVisibility(View.VISIBLE);
				// �������
				handler.post(timeRunnable);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�����ʱ
	 * 
	 * @ʱ �䣺����8:16:08
	 */

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 5:
				tv_number.setText("5�������¼���档����");
				break;
			case 4:
				tv_number.setText("4�������¼���档����");
				break;
			case 3:
				tv_number.setText("3�������¼���档����");
				break;
			case 2:
				tv_number.setText("2�������¼���档����");
				break;
			case 1:
				tv_number.setText("1�������¼���档����");
				break;
			case 0:
				tv_number.setText("0�������¼���档����");
				// ��ת
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				startActivity(intent);
				finish();
				break;

			default:
				break;
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number--;
			Message msg = handler.obtainMessage();
			msg.what = number;
			if (msg.what < 0) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};

}
