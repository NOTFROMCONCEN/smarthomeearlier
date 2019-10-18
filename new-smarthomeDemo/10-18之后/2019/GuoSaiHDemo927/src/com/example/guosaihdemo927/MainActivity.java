package com.example.guosaihdemo927;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @文件名：RegActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-27
 */
public class MainActivity extends Activity {
	private ToggleButton tg_user_logo;// logo图标
	private ToggleButton tg_user_maril;// 男图标
	private ToggleButton tg_user_gril;// 女图标
	private ToggleButton tg_user_qq;// qq图标
	static SharedPreferences sharedPreferences;// sharedPreferences存储
	private Button btn_reg;// 注册按钮
	private TextView tv_tips;// 登录提示
	private boolean maril_state = false;// 登录状态（用户被选中）
	private boolean qq_state = false;// 登录状态（用户被选中）
	private boolean logo_state = false;// 登录状态（用户被选中）
	private boolean gril_state = false;// 登录状态（用户被选中）
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定控件
		// 程序启动时隐藏所有用户
		tg_user_gril.setVisibility(View.GONE);
		tg_user_logo.setVisibility(View.GONE);
		tg_user_maril.setVisibility(View.GONE);
		tg_user_qq.setVisibility(View.GONE);
		get_user();// 获取已注册用户
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getApplicationContext(),
						RegActivity.class);
				startActivity(intent);
				finish();
			}
		});
		// 设置”只能选择一个头像“效果
		// 女被点击
		tg_user_gril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = true;
					maril_state = false;
					qq_state = false;
					logo_state = false;
					tg_user_gril.setChecked(true);
					// tg_user_logo.setChecked(false);
					// tg_user_maril.setChecked(false);
					// tg_user_qq.setChecked(false);
				}
			}
		});
		// 男被点击
		tg_user_maril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = true;
					qq_state = false;
					logo_state = false; // tg_user_gril.setChecked(false);
					// tg_user_logo.setChecked(false);
					tg_user_maril.setChecked(true);
					// tg_user_qq.setChecked(false);
				}
			}
		});
		// qq被点击
		tg_user_qq.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = true;
					logo_state = false; // tg_user_gril.setChecked(false);
					// tg_user_logo.setChecked(false);
					// tg_user_maril.setChecked(false);
					tg_user_qq.setChecked(true);
				}
			}
		});
		// logo被点击
		tg_user_logo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = false;
					logo_state = true; // tg_user_gril.setChecked(false);
					tg_user_logo.setChecked(true);
					// tg_user_maril.setChecked(false);
					// tg_user_qq.setChecked(false);
				}
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

	/*
	 * @方法名：handler
	 * 
	 * @功 能：激活登陆倒计时
	 * 
	 * @时 间：上午8:17:29
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 点击男图标登录
			if (maril_state) {
				number++;// 自增加

				if (number == 1) {
					tv_tips.setText("正在校验用户信息。。。");
				}
				if (number == 3) {
					tv_tips.setText("用户信息正确，正在进入系统。。。");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
			// 点击女孩图标登录
			if (gril_state) {
				number++;// 自增加

				if (number == 1) {
					tv_tips.setText("正在校验用户信息。。。");
				}
				if (number == 3) {
					tv_tips.setText("用户信息正确，正在进入系统。。。");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
			// 点击logo图标登录
			if (logo_state) {
				number++;// 自增加

				if (number == 1) {
					tv_tips.setText("正在校验用户信息。。。");
				}
				if (number == 3) {
					tv_tips.setText("用户信息正确，正在进入系统。。。");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
			// 点击QQ图标登录
			if (qq_state) {
				number++;// 自增加

				if (number == 1) {
					tv_tips.setText("正在校验用户信息。。。");
				}
				if (number == 3) {
					tv_tips.setText("用户信息正确，正在进入系统。。。");
				}
				if (number == 5) {
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
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

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:08:48
	 */
	private void initView() {
		btn_reg = (Button) findViewById(R.id.btn_login);
		tg_user_gril = (ToggleButton) findViewById(R.id.tg_user_gril);
		tg_user_logo = (ToggleButton) findViewById(R.id.tg_user_logo);
		tg_user_maril = (ToggleButton) findViewById(R.id.tg_user_maril);
		tg_user_qq = (ToggleButton) findViewById(R.id.tg_user_qq);
		tv_tips = (TextView) findViewById(R.id.ttv_login_tips);
	}

	/*
	 * @方法名：get_user
	 * 
	 * @功 能：从存储中获取已注册用户
	 * 
	 * @时 间：上午8:09:42
	 */
	private void get_user() {
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("logo", false) == true) {// logo
				tg_user_logo.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("maril", false) == true) {// 男
				tg_user_maril.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("gril", false) == true) {// 女
				tg_user_gril.setVisibility(View.VISIBLE);
			}
			if (sharedPreferences.getBoolean("qq", false) == true) {// QQ
				tg_user_qq.setVisibility(View.VISIBLE);
			}
		}
	}
}
