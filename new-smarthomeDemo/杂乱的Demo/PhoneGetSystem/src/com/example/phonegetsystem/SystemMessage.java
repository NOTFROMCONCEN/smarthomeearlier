package com.example.phonegetsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

/*
 * @文件名：SystemMessage.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-13
 */
public class SystemMessage extends Activity {
	// 定义控件
	private TextView tv_MANUFACTURER;// 制造厂商
	private TextView tv_BRAND;// 制造品牌
	private TextView tv_MODEL;// 设备型号
	private TextView tv_RELEASE;// 设备安卓版本
	private TextView tv_SDK;// SDK版本号
	private TextView tv_DISPLAY;// DISPLAY
	private TextView tv_CPU1;// CPU指令集1
	private TextView tv_CPU2;// CPU指令集2
	private TextView tv_board;// 主板
	private TextView tv_bootloader;// 主板引导程序
	private TextView tv_build_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTitle("系统硬件参数");
		super.onCreate(savedInstanceState);
		// 绑定界面
		setContentView(R.layout.activity_sysmessage);
		// 绑定控件
		tv_BRAND = (TextView) findViewById(R.id.tv_BRAND);
		tv_MANUFACTURER = (TextView) findViewById(R.id.tv_MANUFACTURER);
		tv_MODEL = (TextView) findViewById(R.id.tv_MODEL);
		tv_RELEASE = (TextView) findViewById(R.id.tv_RELEASE);
		tv_SDK = (TextView) findViewById(R.id.tv_SDK);
		tv_DISPLAY = (TextView) findViewById(R.id.tv_DISPLAY);
		tv_CPU1 = (TextView) findViewById(R.id.tv_CPU1);
		tv_CPU2 = (TextView) findViewById(R.id.tv_CPU2);
		tv_board = (TextView) findViewById(R.id.tv_board);
		tv_bootloader = (TextView) findViewById(R.id.tv_bootloader);
		tv_build_time = (TextView) findViewById(R.id.tv_build_time);
		// 获取参数(使用android.os,Build)
		tv_BRAND.setText(android.os.Build.BRAND);// 品牌
		tv_MANUFACTURER.setText(android.os.Build.MANUFACTURER);// 制造商
		tv_MODEL.setText(android.os.Build.MODEL);// 型号
		tv_RELEASE.setText(android.os.Build.VERSION.RELEASE);// 系统版本
		tv_SDK.setText(android.os.Build.VERSION.SDK);// SDK版本
		tv_DISPLAY.setText(android.os.Build.DISPLAY);// DISPLAY
		tv_CPU1.setText(android.os.Build.CPU_ABI);// CPU指令集1
		tv_CPU2.setText(android.os.Build.CPU_ABI2);// CPU指令集2
		tv_board.setText(android.os.Build.BOARD);// 主板
		tv_bootloader.setText(android.os.Build.BOOTLOADER);// 主板引导程序
		tv_build_time.setText(android.os.Build.TIME + "");// 编译时间

	}
}
