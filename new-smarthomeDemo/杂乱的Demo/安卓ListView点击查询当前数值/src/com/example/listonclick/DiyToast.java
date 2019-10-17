package com.example.listonclick;

import android.content.Context;
import android.widget.Toast;

/*
 * @�ļ�����DiyToast.java
 * @����������Զ���Toats
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-3
 */
public class DiyToast {
	public static Toast toast;

	public static void showToast(Context context, String string) {
		if (toast == null) {
			toast = Toast.makeText(context, string, Toast.LENGTH_SHORT);
		} else {
			toast.setText(string);
		}
		toast.show();
	}

}
