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

		tv_about_text.setText("product =  ��Ʒ" + "\n" + "cpu =  CPU_ABI" + "\n"
				+ "cpu2 =  CPU_ABI2" + "\n" + "tags =  ��ǩ" + "\n"
				+ "model =  �ͺ�" + "\n" + "sdk =  SDK�汾" + "\n"
				+ "androidversion =  Android �汾" + "\n" + "device =  ����" + "\n"
				+ "display =  DISPLAY" + "\n" + "brand =  Ʒ��" + "\n"
				+ "board =  ����" + "\n" + "sign =  �豸��ʶ" + "\n" + "id =  �汾��"
				+ "\n" + "maker =  ������" + "\n" + "user =  �û�" + "\n"
				+ "hardware =  Ӳ��" + "\n" + "host =  ������ַ" + "\n"
				+ "unknown =  δ֪��Ϣ" + "\n" + "type =  �汾����" + "\n"
				+ "time =  ʱ��" + "\n" + "radio =  Radio" + "\n"
				+ "serial =  ���к�");
	}
}
