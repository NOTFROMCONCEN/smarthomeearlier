package com.example.jiugonggedrawdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 自定义Toast
 * @package_name com.example.jiugonggedrawdemo
 * @project_name 九宫格DrawDemo
 * @file_name DiyToast.java
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
