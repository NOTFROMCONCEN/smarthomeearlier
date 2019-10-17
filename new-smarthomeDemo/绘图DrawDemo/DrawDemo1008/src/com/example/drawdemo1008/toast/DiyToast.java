package com.example.drawdemo1008.toast;

import android.content.Context;
import android.widget.Toast;

/*
 * @文件名：DiyToast.java
 * @描述：自定义Dialog
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-8
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
