package com.example.guosaihdemo914;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：完成登录功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-14
 */
public class MainActivity extends Activity {
	private RadioButton ra_user_logo;// logo用户
	private RadioButton ra_user_maril;// 马里奥用户
	private RadioButton ra_user_gril;// 女孩用户
	private RadioButton ra_user_qq;// QQ用户
	private Button btn_reg;// 注册按钮
	private TextView tv_tips;// 文字提示
	private int number = 0;
	static SharedPreferences sharedPreferences;// sharedPreferences存储

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_tips = (TextView) findViewById(R.id.tv_tips);
		ra_user_gril = (RadioButton) findViewById(R.id.ra_user_gril);
		ra_user_logo = (RadioButton) findViewById(R.id.ra_user_logo);
		ra_user_maril = (RadioButton) findViewById(R.id.ra_user_maril);
		ra_user_qq = (RadioButton) findViewById(R.id.ra_user_qq);
		btn_reg = (Button) findViewById(R.id.btn_login);
		ra_user_gril.setVisibility(View.GONE);
		ra_user_logo.setVisibility(View.GONE);
		ra_user_maril.setVisibility(View.GONE);
		ra_user_qq.setVisibility(View.GONE);
		// 显示、隐藏用户头像
		sharedPreferences = getSharedPreferences("user", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("user_logo", false) == true) {
				ra_user_logo.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("user_maril", false) == true) {
				ra_user_maril.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("user_gril", false) == true) {
				ra_user_gril.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("user_qq", false) == true) {
				ra_user_qq.setVisibility(View.VISIBLE);
			}
		}
		// 注册按钮
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, RegActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// 激活进程
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
			if (ra_user_gril.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				switch (msg.what) {
				case 2:
					tv_tips.setText("正在效验用户信息。。。");
					break;
				case 4:
					tv_tips.setText("用户信息正确，正在进入系统");
					break;
				case 6:
					DiyToast.showToast(getApplicationContext(), "登陆成功");
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}
			if (ra_user_maril.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				switch (msg.what) {
				case 2:
					tv_tips.setText("正在效验用户信息。。。");
					break;
				case 4:
					tv_tips.setText("用户信息正确，正在进入系统");
					break;
				case 6:
					DiyToast.showToast(getApplicationContext(), "登陆成功");
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}
			if (ra_user_qq.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				switch (msg.what) {
				case 2:
					tv_tips.setText("正在效验用户信息。。。");
					break;
				case 4:
					tv_tips.setText("用户信息正确，正在进入系统");
					break;
				case 6:
					DiyToast.showToast(getApplicationContext(), "登陆成功");
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
					break;
				default:
					break;
				}
			}
			if (ra_user_logo.isChecked()) {
				tv_tips.setTextColor(Color.BLACK);
				switch (msg.what) {
				case 2:
					tv_tips.setText("正在效验用户信息。。。");
					break;
				case 4:
					tv_tips.setText("用户信息正确，正在进入系统");
					break;
				case 6:
					DiyToast.showToast(getApplicationContext(), "登陆成功");
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
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
