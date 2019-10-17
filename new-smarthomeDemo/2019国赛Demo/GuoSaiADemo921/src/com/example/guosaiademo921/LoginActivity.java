package com.example.guosaiademo921;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @文件名：LoginActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-21
 */
public class LoginActivity extends Activity {
	private SeekBar sk_1;
	private EditText et_ip;
	private Button btn_login;
	public static String IP_NUMBER;// IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					IP_NUMBER = et_ip.getText().toString();
					Intent intent = new Intent(getApplicationContext(),
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 滑动栏滑动事件
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);// 重置进度
					} else {
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(getApplicationContext(),
								IndexActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					sk_1.setProgress(0);
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub
				Log.e("滑动栏进度", String.valueOf(seekBar.getProgress()));
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度条改变
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().isEmpty()) {
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);// 重置进度
					} else {
						IP_NUMBER = et_ip.getText().toString();
						Intent intent = new Intent(getApplicationContext(),
								IndexActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});
	}
}
