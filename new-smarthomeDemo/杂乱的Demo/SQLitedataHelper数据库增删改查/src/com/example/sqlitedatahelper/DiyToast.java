package com.example.sqlitedatahelper;

import android.content.Context;
import android.widget.Toast;

/*
 * @文件名：DiyToast.java
 * @描述：自定义公共类，用于优化Toast
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class DiyToast {

	public static Toast toast;

	public static void showToast(Context context, String content) {
		// 判断Toast对象是否为空
		if (toast == null) {
			// 如果是空的情况下调用makeText()方法来去生成一个Toast对象
			toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
		} else {
			// 如果有显示中的Toast对象就直接调用setText()方法来设置显示的内容
			toast.setText(content);
		}
		toast.show();
	}
}
