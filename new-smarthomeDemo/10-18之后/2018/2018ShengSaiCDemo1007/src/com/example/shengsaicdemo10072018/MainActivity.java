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
 * @文件名：MainActivity.java
 * @描述：动画、登录注册跳转
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-7
 */
public class MainActivity extends Activity {
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private ImageView iv_1;
	private ScaleAnimation scaleAnimation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		iv_1 = (ImageView) findViewById(R.id.imageView1);
		// 隐藏按钮
		btn_login.setVisibility(View.INVISIBLE);
		btn_reg.setVisibility(View.INVISIBLE);
		// "自动登录"
		LoginActivity.sharedPreferences = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (LoginActivity.sharedPreferences.getBoolean("autologin", false) == true) {
			startActivity(new Intent(getApplicationContext(), BarActivity.class));
			finish();
		}
		// 设置动画
		scaleAnimation = new ScaleAnimation(0f, 1f, 0f, 1f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
				ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
		scaleAnimation.setDuration(2000);
		scaleAnimation.setFillAfter(true);
		iv_1.setAnimation(scaleAnimation);

		// 设置动画监听事件
		scaleAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {// 开始
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation) {// 重置
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationEnd(Animation animation) {// 结束
				// TODO Auto-generated method stub
				btn_login.setVisibility(View.VISIBLE);
				btn_reg.setVisibility(View.VISIBLE);
			}
		});
		// 登录按钮点击
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						LoginActivity.class));
			}
		});
		// 注册按钮点击
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
