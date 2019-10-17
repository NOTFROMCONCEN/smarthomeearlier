package com.example.phonegetsystem;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity {
	TextView tv_about_text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		tv_about_text = (TextView) findViewById(R.id.tv_about_text);

		tv_about_text.setText("product =  产品" + "\n" + "cpu =  CPU_ABI" + "\n"
				+ "cpu2 =  CPU_ABI2" + "\n" + "tags =  标签" + "\n"
				+ "model =  型号" + "\n" + "sdk =  SDK版本" + "\n"
				+ "androidversion =  Android 版本" + "\n" + "device =  驱动" + "\n"
				+ "display =  DISPLAY" + "\n" + "brand =  品牌" + "\n"
				+ "board =  基板" + "\n" + "sign =  设备标识" + "\n" + "id =  版本号"
				+ "\n" + "maker =  制造商" + "\n" + "user =  用户" + "\n"
				+ "hardware =  硬件" + "\n" + "host =  主机地址" + "\n"
				+ "unknown =  未知信息" + "\n" + "type =  版本类型" + "\n"
				+ "time =  时间" + "\n" + "radio =  Radio" + "\n"
				+ "serial =  序列号");
	}
}
