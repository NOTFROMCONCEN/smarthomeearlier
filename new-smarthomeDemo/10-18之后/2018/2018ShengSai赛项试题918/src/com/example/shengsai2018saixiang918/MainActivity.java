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
 * @文件名：MainActivity.java
 * @描述：完成登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-17
 */
public class MainActivity extends Activity {
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 登录按钮
	private ImageView iv_logo;// 图片

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		iv_logo = (ImageView) findViewById(R.id.imageView1);
		// 隐藏按钮
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
		// 设置动画
		ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		iv_logo.startAnimation(scaleAnimation);
		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {// 动画开始
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {// 动画重制
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {// 动画停止
				// TODO Auto-generated method stub
				// 显示动画
				btn_login.setVisibility(View.VISIBLE);
				btn_reg.setVisibility(View.VISIBLE);
			}
		});
		// 设置按钮点击事件
		// 登录
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						LoginActivity.class);
				startActivity(intent);
			}
		});
		// 注册
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
