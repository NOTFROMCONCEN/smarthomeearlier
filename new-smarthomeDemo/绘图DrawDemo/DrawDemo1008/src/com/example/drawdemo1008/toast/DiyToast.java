package com.example.drawdemo1008.toast;

import android.content.Context;
import android.widget.Toast;

/*
 * @�ļ�����DiyToast.java
 * @�������Զ���Dialog
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class DiyToast {
	public static Toast toast;

	public static void showToast(Context context, String strings) {
		if (toast == null) {
			toast = Toast.makeText(context, strings, Toast.LENGTH_SHORT);
		} else {
			toast.setText(strings);
		}
		toast.show();
	}
}
