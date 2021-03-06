package com.example.guosaigdemo911;

import android.content.Context;
import android.widget.Toast;

/*
 * @文件名：DiyToast.java
 * @描述：自定义Dialog
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-11
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
