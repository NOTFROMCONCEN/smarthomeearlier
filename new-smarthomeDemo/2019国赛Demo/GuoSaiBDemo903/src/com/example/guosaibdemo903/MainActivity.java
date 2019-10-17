package com.example.guosaibdemo903;

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
 * @描述：完成登录、服务器连接
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-3
 */
public class MainActivity extends Activity {
	private Button btn_login;
	private EditText et_ip;
	public static String ip_number;// IP地址
	private SeekBar sk_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setProgress(0);// seekbar进度条当前值
		sk_1.setMax(100);// seekbar最高值
		et_ip.setText("1");
		ip_number = et_ip.getText().toString();
		Intent intent = new Intent(MainActivity.this, IndexActivity.class);
		startActivity(intent);
		finish();
		// 设置按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {// 如果IP地址为空
					DiyToast.showToast(MainActivity.this, "请输入IP地址");
				} else {
					ip_number = et_ip.getText().toString();
					Intent intent = new Intent(MainActivity.this,
							LoadingActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
		// 设置SeekBar功能
		sk_1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {// 停止滑动
				// TODO Auto-generated method stub
				if (seekBar.getProgress() == 100) {
					if (et_ip.getText().toString().equals("")) {
						DiyToast.showToast(MainActivity.this, "IP地址不能为空");
						sk_1.setProgress(0);
					} else {
						// 跳转到下一界面
						ip_number = et_ip.getText().toString();
						Intent intent = new Intent(MainActivity.this,
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
				} else {
					DiyToast.showToast(MainActivity.this, "请完成验证");
					sk_1.setProgress(0);
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
