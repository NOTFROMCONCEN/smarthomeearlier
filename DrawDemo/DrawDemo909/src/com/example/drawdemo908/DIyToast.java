package com.example.drawdemo908;

import android.content.Context;
import android.widget.Toast;

/*
 * @文件名：DIyToast.java
 * @描述：自定义Toast
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-7
 */
public class DIyToast {
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
