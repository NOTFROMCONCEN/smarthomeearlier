package com.example.guosaigdemo9262019;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * @文件名：SQLiteControl.java
 * @描述：数据库增删改查
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class SQLiteControl {
	public static boolean state = false;

	// 获取用户名、密码（查询数据库）
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

	// 注册（插入数据库）
	public static void setUser(Context context, String username, String passward) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.execSQL("insert into user" + "(username,passward)" + "values(?,?)",
				new String[] { username, passward });
	}

	// 修改密码（更新数据库）
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
				DiyToast.showToast(context, "修改成功");
			} else {
				DiyToast.showToast(context, "旧密码错误");
			}
		} else {
			DiyToast.showToast(context, "用户名错误");
		}
	}
}
