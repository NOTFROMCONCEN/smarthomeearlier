package com.example.shengsaif9222017;

import android.content.Context;
import android.widget.Toast;

/*
 * @�ļ�����DiyToast.java
 * 
 * @�������Զ���Toast
 * 
 * @���ߣ�������
 * 
 * @��Ȩ��Bilibili ���ͻ�÷��
 * 
 * @ʱ�䣺2019-9-22
 * 
 * @author Administrator
 */
public class DiyToast {
	public static Toast toast;

	public static void showTaost(Context context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}
}
