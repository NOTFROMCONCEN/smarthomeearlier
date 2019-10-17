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
 * @文件名：RegActivity.java
 * @描述：完成注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-14
 */
public class RegActivity extends Activity {
	private RadioButton ra_user_logo;// logo用户
	private RadioButton ra_user_maril;// 马里奥用户
	private RadioButton ra_user_gril;// 女孩用户
	private RadioButton ra_user_qq;// QQ用户
	private Button btn_con, btn_cls;// 注册按钮
	private TextView tv_tips;// 文字提示
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
		// 注册按钮
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				handler.post(timeRunnable);
			}
		});
		// 关闭按钮
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
	 * @方法名：handler
	 * 
	 * @功 能：注册用户倒计时
	 * 
	 * @时 间：下午3:26:24
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
					tv_tips.setText("正在注册用户信息。。。");
					break;
				case 3:
					tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "注册成功");
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
					tv_tips.setText("正在注册用户信息。。。");
					break;
				case 3:
					tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "注册成功");
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
					tv_tips.setText("正在注册用户信息。。。");
					break;
				case 3:
					tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "注册成功");
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
					tv_tips.setText("正在注册用户信息。。。");
					break;
				case 3:
					tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					break;
				case 5:
					DiyToast.showToast(getApplicationContext(), "注册成功");
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
