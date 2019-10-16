package com.example.drawdemo825;

import android.content.Context;
import android.widget.Toast;

/*
 * @�ļ�����DiyToast.java
 * @�������Զ���Toast
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-25
 */
public class DiyToast {
	public static Toast toast;

	public static void showToast(Context context, String content) {
		if (toast == null) {
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		} else {
			toast.setText(content);
		}
		toast.show();
	}
}
