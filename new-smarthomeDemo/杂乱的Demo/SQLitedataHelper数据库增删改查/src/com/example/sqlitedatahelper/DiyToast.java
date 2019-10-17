package com.example.sqlitedatahelper;

import android.content.Context;
import android.widget.Toast;

/*
 * @�ļ�����DiyToast.java
 * @�������Զ��幫���࣬�����Ż�Toast
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-21
 */
public class DiyToast {

	public static Toast toast;

	public static void showToast(Context context, String content) {
		// �ж�Toast�����Ƿ�Ϊ��
		if (toast == null) {
			// ����ǿյ�����µ���makeText()������ȥ����һ��Toast����
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		} else {
			// �������ʾ�е�Toast�����ֱ�ӵ���setText()������������ʾ������
			toast.setText(content);
		}
		toast.show();
	}
}
