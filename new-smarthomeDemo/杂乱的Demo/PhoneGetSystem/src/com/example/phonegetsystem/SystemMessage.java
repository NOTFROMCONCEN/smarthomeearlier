package com.example.phonegetsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.widget.TextView;

/*
 * @�ļ�����SystemMessage.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-13
 */
public class SystemMessage extends Activity {
	// ����ؼ�
	private TextView tv_MANUFACTURER;// ���쳧��
	private TextView tv_BRAND;// ����Ʒ��
	private TextView tv_MODEL;// �豸�ͺ�
	private TextView tv_RELEASE;// �豸��׿�汾
	private TextView tv_SDK;// SDK�汾��
	private TextView tv_DISPLAY;// DISPLAY
	private TextView tv_CPU1;// CPUָ�1
	private TextView tv_CPU2;// CPUָ�2
	private TextView tv_board;// ����
	private TextView tv_bootloader;// ������������
	private TextView tv_build_time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setTitle("ϵͳӲ������");
		super.onCreate(savedInstanceState);
		// �󶨽���
		setContentView(R.layout.activity_sysmessage);
		// �󶨿ؼ�
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
		// ��ȡ����(ʹ��android.os,Build)
		tv_BRAND.setText(android.os.Build.BRAND);// Ʒ��
		tv_MANUFACTURER.setText(android.os.Build.MANUFACTURER);// ������
		tv_MODEL.setText(android.os.Build.MODEL);// �ͺ�
		tv_RELEASE.setText(android.os.Build.VERSION.RELEASE);// ϵͳ�汾
		tv_SDK.setText(android.os.Build.VERSION.SDK);// SDK�汾
		tv_DISPLAY.setText(android.os.Build.DISPLAY);// DISPLAY
		tv_CPU1.setText(android.os.Build.CPU_ABI);// CPUָ�1
		tv_CPU2.setText(android.os.Build.CPU_ABI2);// CPUָ�2
		tv_board.setText(android.os.Build.BOARD);// ����
		tv_bootloader.setText(android.os.Build.BOOTLOADER);// ������������
		tv_build_time.setText(android.os.Build.TIME + "");// ����ʱ��

	}
}
