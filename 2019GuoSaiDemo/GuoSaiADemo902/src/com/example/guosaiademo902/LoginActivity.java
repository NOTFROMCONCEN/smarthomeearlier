package com.example.guosaiademo902;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @文件名：LoginActivity.java
 * @描述：完成登录、网络连接
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-2
 */
public class LoginActivity extends Activity {
	private SeekBar sk_1;
	private EditText et_ip;
	private Button btn_login;
	public static String ip_number;// IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sk_1 = (SeekBar) findViewById(R.id.sk_1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// 设置滑动栏参数
		sk_1.setMax(100);
		sk_1.setProgress(0);
		// 设置按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					DiyToast.showToast(LoginActivity.this, "请输入IP地址");
				} else {
					ip_number = et_ip.getText().toString();
					Intent intent = new Intent(LoginActivity.this,
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 设置滑动栏事件
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(LoginActivity.this, "请输入IP地址");
						sk_1.setProgress(0);
					} else {
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(LoginActivity.this,
								IndexActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					DiyToast.showToast(LoginActivity.this, "请完成滑动验证");
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					DiyToast.showToast(LoginActivity.this, "请输入IP地址");
					sk_1.setProgress(0);
				}
			}
		});
	}
}
