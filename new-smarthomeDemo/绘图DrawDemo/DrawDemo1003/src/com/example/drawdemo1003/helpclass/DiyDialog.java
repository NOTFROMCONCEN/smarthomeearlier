package com.example.drawdemo1003.helpclass;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/*
 * @�ļ�����DiyDialog.java
 * @�������Զ���Dialog
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-3
 */
public class DiyDialog {
	public static void showDialog(Context context, String title,
			String message, boolean okbutton, boolean clsbutton) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(title);
		builder.setMessage(message);
		if (clsbutton) {
			builder.setNegativeButton("�ر�",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});
		}
		if (okbutton) {
			builder.setPositiveButton("ȷ��",
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
