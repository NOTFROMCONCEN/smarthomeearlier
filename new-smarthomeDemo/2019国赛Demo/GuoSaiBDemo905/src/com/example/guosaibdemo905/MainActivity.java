package com.example.guosaibdemo905;

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
 * @描述：完成登陆、网络连接
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
 */
public class MainActivity extends Activity {
	private SeekBar sk_1;
	private EditText et_ip;
	private Button btn_login;
	static String ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("");
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// 设置按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {// IP为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					ip = et_ip.getText().toString();
					Intent intent = new Intent(MainActivity.this,
							LoadingActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 设置滑动栏
		sk_1.setMax(100);// 最高
		sk_1.setProgress(0);// 当前
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {// 如果当前值==100
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(getApplicationContext(), "请输入IP地址");
						sk_1.setProgress(0);
					} else {
						ip = et_ip.getText().toString();
						Intent intent = new Intent(MainActivity.this,
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {// 开始滑动
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {// 进度改变
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
