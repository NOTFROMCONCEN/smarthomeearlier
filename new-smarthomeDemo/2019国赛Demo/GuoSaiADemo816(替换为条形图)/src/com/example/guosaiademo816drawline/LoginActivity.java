package com.example.guosaiademo816drawline;

import java.nio.channels.SocketChannel;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

/*
 * @文件名：LoginActivity.java
 * @描述：对用户完成登录、连接服务器功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-16
 */
public class LoginActivity extends Activity implements OnSeekBarChangeListener {
	private SeekBar sk_login;// SeekBar滑动条
	private EditText et_ip;// IP文本框
	private Button btn_login;// 登录按钮
	static String ip;// IP地址

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sk_login = (SeekBar) findViewById(R.id.sk_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		btn_login = (Button) findViewById(R.id.btn_login);
		// 设置SeekBar监听
		sk_login.setOnSeekBarChangeListener(this);
		sk_login.setMax(100);
		sk_login.setProgress(0);
		// 设置登录按钮监听
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (et_ip.getText().toString().equals("")) {
					// 如果IP地址文本框为空
					Toast.makeText(LoginActivity.this, "请输入IP地址",
							Toast.LENGTH_SHORT).show();
				} else if (sk_login.getProgress() != 100) {
					// 如果SeekBar没有被拉到另一边
					Toast.makeText(LoginActivity.this, "请完成滑动验证",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(LoginActivity.this, "登陆成功",
							Toast.LENGTH_SHORT).show();
					ip = et_ip.getText().toString();
					Intent intent = new Intent(LoginActivity.this,
							IndexActivity.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	@Override
	// 当SeekBar改变
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
	}

	@Override
	// 当SeekBar开始滑动
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	}

	@Override
	// 当SeekBar停止滑动
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		if (seekBar.getProgress() == 100) {
			// 判断SeekBar是不是满足条件
			if (!et_ip.getText().toString().equals("")) {
				ip = et_ip.getText().toString();
				Intent intent = new Intent(LoginActivity.this,
						IndexActivity.class);
				startActivity(intent);
				finish();
			} else {
				Toast.makeText(LoginActivity.this, "请输入IP地址",
						Toast.LENGTH_SHORT).show();
				sk_login.setProgress(0);
			}
		} else {
			sk_login.setProgress(0);
			Toast.makeText(LoginActivity.this, "请滑动完成验证", Toast.LENGTH_SHORT)
					.show();
		}
	}
}
