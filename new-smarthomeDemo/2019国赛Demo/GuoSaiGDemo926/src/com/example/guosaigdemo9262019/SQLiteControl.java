package com.example.guosaigdemo9262019;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * @�ļ�����SQLiteControl.java
 * @���������ݿ���ɾ�Ĳ�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-26
 */
public class SQLiteControl {
	public static boolean state = false;

	// ��ȡ�û��������루��ѯ���ݿ⣩
	public static void getUser(Context context, String username, String passward) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor;
		cursor = db.rawQuery(
				"select * from user where username = ? and passward = ?",
				new String[] { username, passward });
		if (cursor.moveToNext()) {
			state = true;
		} else {
			state = false;
		}
	}

	// ע�ᣨ�������ݿ⣩
	public static void setUser(Context context, String username, String passward) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("insert into user" + "(username,passward)" + "values(?,?)",
				new String[] { username, passward });
	}

	// �޸����루�������ݿ⣩
	public static void updata_Pass(Context context, String username,
			String passward, String newpass) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from user where username = ?",
				new String[] { username });
		if (cursor.moveToNext()) {
			String oldpass = cursor
					.getString(cursor.getColumnIndex("passward"));
			if (oldpass.equals(passward)) {
				db.execSQL("update user set passward = ? where username = ?",
						new String[] { newpass, username });
				DiyToast.showToast(context, "�޸ĳɹ�");
			} else {
				DiyToast.showToast(context, "���������");
			}
		} else {
			DiyToast.showToast(context, "�û�������");
		}
	}
}
