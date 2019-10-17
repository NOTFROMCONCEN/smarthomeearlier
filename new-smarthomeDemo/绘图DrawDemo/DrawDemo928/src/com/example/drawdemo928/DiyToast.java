package com.example.drawdemo928;

import android.content.Context;
import android.widget.Toast;

/*
 * @文件名：DiyToast.java
 * @描述：自定义Toast
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-28
 */
public class DiyToast {
	public static Toast toast;

	public static void showTaost(Context context, String text) {
		if (toast == null) {
			toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
		} else {
			toast.setText(text);
		}
		toast.show();
	}
}
