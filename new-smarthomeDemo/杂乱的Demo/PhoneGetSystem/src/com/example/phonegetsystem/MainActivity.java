package com.example.phonegetsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-13
 * @author Administrator
 */
public class MainActivity extends Activity implements OnClickListener {
	// 定义控件
	private TextView tv_network_state;// “系统网络参数”文本
	private TextView tv_phone_state;// “系统硬件参数”文本
	private TextView tv_about;// “关于”文本
	private TextView tv_sys_out;// ”退出“文本
	private TextView tv_ui;// “操作环境”文本

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("请选择");
		setContentView(R.layout.activity_main);
		tv_ui = (TextView) findViewById(R.id.tv_ui);
		tv_network_state = (TextView) findViewById(R.id.tv_network_state);
		tv_about = (TextView) findViewById(R.id.tv_about);
		tv_phone_state = (TextView) findViewById(R.id.tv_phone_state);
		tv_sys_out = (TextView) findViewById(R.id.tv_sys_out);
		tv_sys_out.setOnClickListener(this);
		tv_about.setOnClickListener(this);
		tv_network_state.setOnClickListener(this);
		tv_phone_state.setOnClickListener(this);
		if (UIsystem.isMIUI() == true) {
			tv_ui.setText("操作环境：" + "小米手机（可能为非MIUI系统）");
		} else {
			tv_ui.setText("操作环境：" + "未知");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：onClick(View v)
	 * 
	 * @功 能：文本点击监听
	 * 
	 * @参 数：View v - 文本的View
	 * 
	 * @返回值：无
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.tv_about:
			Intent intent = new Intent(MainActivity.this, About.class);
			startActivity(intent);
			break;
		case R.id.tv_network_state:
			Intent intent2 = new Intent(MainActivity.this, NetworkMessage.class);
			startActivity(intent2);
			break;
		case R.id.tv_phone_state:
			Intent intent3 = new Intent(MainActivity.this, SystemMessage.class);
			startActivity(intent3);
			break;
		case R.id.tv_sys_out:
			finish();
			break;
		default:
			break;
		}
	}
}
