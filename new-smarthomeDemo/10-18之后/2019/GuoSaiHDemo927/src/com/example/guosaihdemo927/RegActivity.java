package com.example.guosaihdemo927;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

/*
 * @文件名：RegActivity.java
 * @描述：注册账户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-27
 */
public class RegActivity extends Activity {
	private ToggleButton tg_reg_logo;// logo图标
	private ToggleButton tg_reg_maril;// 男图标
	private ToggleButton tg_reg_gril;// 女图标
	private ToggleButton tg_reg_qq;// qq图标
	private Button btn_con;// 注册确定按钮
	private Button btn_cls;// 注册关闭按钮
	private TextView tv_tips;// 注册提示
	private boolean maril_state = false;// 注册状态（用户被选中）
	private boolean qq_state = false;// 注册状态（用户被选中）
	private boolean logo_state = false;// 注册状态（用户被选中）
	private boolean gril_state = false;// 注册状态（用户被选中）
	private boolean reg_state = false;// 注册状态
	private int number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		// 绑定控件
		initView();
		// 注册按钮
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tg_reg_gril.isChecked() || tg_reg_logo.isChecked()
						|| tg_reg_maril.isChecked() || tg_reg_qq.isChecked()) {
					reg_state = true;
				} else {
					reg_state = false;
					DiyToast.showToast(getApplicationContext(), "请选择要注册的用户头像");
				}
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
		// 设置”只能选择一个头像“效果
		// 女被点击
		tg_reg_gril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = true;
					maril_state = false;
					qq_state = false;
					logo_state = false;
					tg_reg_gril.setChecked(true);
					tg_reg_logo.setChecked(false);
					tg_reg_maril.setChecked(false);
					tg_reg_qq.setChecked(false);
				}
			}
		});
		// 男被点击
		tg_reg_maril.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = true;
					qq_state = false;
					logo_state = false;
					tg_reg_gril.setChecked(false);
					tg_reg_logo.setChecked(false);
					tg_reg_maril.setChecked(true);
					tg_reg_qq.setChecked(false);
				}
			}
		});
		// qq被点击
		tg_reg_qq.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = true;
					logo_state = false;
					tg_reg_gril.setChecked(false);
					tg_reg_logo.setChecked(false);
					tg_reg_maril.setChecked(false);
					tg_reg_qq.setChecked(true);
				}
			}
		});
		// logo被点击
		tg_reg_logo.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					gril_state = false;
					maril_state = false;
					qq_state = false;
					logo_state = true;
					tg_reg_gril.setChecked(false);
					tg_reg_logo.setChecked(true);
					tg_reg_maril.setChecked(false);
					tg_reg_qq.setChecked(false);
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
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
			if (reg_state) {
				// 点击男图标登录
				if (maril_state) {
					number++;// 自增加
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("正在注册用户信息");
					}
					if (number == 3) {
						tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					}
					if (number == 5) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("maril", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				// 点击女孩图标登录
				if (gril_state) {
					number++;// 自增加
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("正在注册用户信息");
					}
					if (number == 3) {
						tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					}
					if (number == 5) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("gril", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				// 点击logo图标登录
				if (logo_state) {
					number++;// 自增加
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("正在注册用户信息");
					}
					if (number == 3) {
						tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					}
					if (number == 5) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("logo", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
				}
				// 点击QQ图标登录
				if (qq_state) {
					number++;// 自增加
					System.out.println(number);
					if (number == 1) {
						tv_tips.setText("正在注册用户信息");
					}
					if (number == 4) {
						tv_tips.setText("用户信息注册成功，2秒后返回登录界面。");
					}
					if (number == 6) {
						MainActivity.sharedPreferences.edit()
								.putBoolean("qq", true).commit();
						Intent intent = new Intent(getApplicationContext(),
								MainActivity.class);
						startActivity(intent);
						finish();
					}
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
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		tg_reg_gril = (ToggleButton) findViewById(R.id.tg_reg_gril);
		tg_reg_logo = (ToggleButton) findViewById(R.id.tg_reg_logo);
		tg_reg_maril = (ToggleButton) findViewById(R.id.tg_reg_maril);
		tg_reg_qq = (ToggleButton) findViewById(R.id.tg_reg_qq);
		tv_tips = (TextView) findViewById(R.id.tv_reg_tips);
	}
}
