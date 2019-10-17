package com.example.drawdemo1003.helpclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/*
 * @文件名：DiyDialog.java
 * @描述：自定义Dialog
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-3
 */
public class DiyDialog {
	public static void showDialog(Context context, String title,
			String message, boolean okbutton, boolean clsbutton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		if (clsbutton) {
			builder.setNegativeButton("关闭",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
		}
		if (okbutton) {
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
		}
		builder.show();
	}
}
