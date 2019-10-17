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
 * @文件名：MainActivity.java
 * @描述：完成动画
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-11
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
		// 设置动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(2000);// 时长
		scaleAnimation.setFillAfter(true);// 状态
		iv_logo.startAnimation(scaleAnimation);
		tv_number.setVisibility(View.INVISIBLE);// 动画结束前文本隐藏
		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {// 动画开始
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {// 动画重置
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {// 动画结束
				// TODO Auto-generated method stub
				// 动画结束时显示文本
				tv_number.setVisibility(View.VISIBLE);
				// 激活进程
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
	 * @方法名：handler
	 * 
	 * @功 能：倒计时
	 * 
	 * @时 间：上午8:16:08
	 */

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 5:
				tv_number.setText("5秒后进入登录界面。。。");
				break;
			case 4:
				tv_number.setText("4秒后进入登录界面。。。");
				break;
			case 3:
				tv_number.setText("3秒后进入登录界面。。。");
				break;
			case 2:
				tv_number.setText("2秒后进入登录界面。。。");
				break;
			case 1:
				tv_number.setText("1秒后进入登录界面。。。");
				break;
			case 0:
				tv_number.setText("0秒后进入登录界面。。。");
				// 跳转
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
