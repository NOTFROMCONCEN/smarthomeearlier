package com.example.guosaibdemo922;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @文件名：MainActivity.java
 * @描述：登录、网络连接
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class MainActivity extends Activity {
	private Button btn_login;
	private SeekBar sk_1;
	private EditText et_ip;
	public static String IP_NUMBER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 绑定控件
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {// IP为空
					DiyToast.showTasot(getApplicationContext(), "IP非法");
				} else {
					IP_NUMBER = et_ip.getText().toString();// 赋值IP地址
					// 跳转
					Intent intent = new Intent(getApplicationContext(),
							LoadingActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 设置SeekBar滑动
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showTasot(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);
					} else {
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(getApplicationContext(),
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					sk_1.setProgress(0);
					DiyToast.showTasot(getApplicationContext(), "请完成验证");
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度条改变
				// TODO Auto-generated method stub

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
