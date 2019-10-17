package com.example.drawdemo928;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/*
 * @文件名：SQLiteControl.java
 * @描述：数据库增删改查
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-28
 */
public class SQLiteControl {
	public static boolean login_state = false;
	public static boolean reg_state = false;
	public static boolean updata_state = false;

	/*
	 * @方法名：UpdataPass()
	 * 
	 * @功 能：修改密码（更新数据库）
	 * 
	 * @参 数：username,newpass,oldpass
	 * 
	 * @时 间：下午3:22:23
	 */
	public static void UpdataPass(Context context, String username,
			String oldpass, String newpass) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (username.isEmpty()) {// 用户名为空
			DiyToast.showTaost(context, "请输入用户名");
		} else if (oldpass.isEmpty()) {
			DiyToast.showTaost(context, "请输入密码");
		} else if (newpass.isEmpty()) {
			DiyToast.showTaost(context, "请输入确认密码");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? ",
					new String[] { username });
			if (cursor.moveToNext()) {
				if (cursor.getString(cursor.getColumnIndex("passward"))
						.toString().equals(oldpass)) {
					if (oldpass.equals(newpass)) {
						DiyToast.showTaost(context, "新旧密码不能一致");
						updata_state = false;
					} else {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { newpass, username });
						DiyToast.showTaost(context, "修改成功");
						updata_state = true;
					}
				} else {
					DiyToast.showTaost(context, "旧密码错误");
					updata_state = false;
				}
			} else {
				DiyToast.showTaost(context, "用户名不存在");
				updata_state = false;
			}
		}
	}

	/*
	 * @方法名：setSQL()
	 * 
	 * @功 能：注册（插入数据库）
	 * 
	 * @参 数：username,passward
	 * 
	 * @时 间：下午3:22:23
	 */
	public static void setSQL(Context context, String username,
			String passward, String repass) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (username.isEmpty()) {// 用户名为空
			DiyToast.showTaost(context, "请输入用户名");
		} else if (passward.isEmpty()) {
			DiyToast.showTaost(context, "请输入密码");
		} else if (repass.isEmpty()) {
			DiyToast.showTaost(context, "请输入确认密码");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? ",
					new String[] { username });
			if (cursor.moveToNext()) {
				DiyToast.showTaost(context, "用户名已存在");
				reg_state = false;
			} else {
				if (passward.equals(repass)) {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { username, passward });
					DiyToast.showTaost(context, "注册成功");
					reg_state = true;
				} else {
					DiyToast.showTaost(context, "两次密码输入不一致");
					reg_state = false;
				}
			}
		}
	}

	/*
	 * @方法名：SeleSQL()
	 * 
	 * @功 能：登录(检测用户名、密码，匹配数据库)
	 * 
	 * @参 数：username,passward,context
	 * 
	 * @时 间：下午3:05:33
	 */
	public static void SeleSQL(Context context, String username, String passward) {
		MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
				null, 2);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		if (username.isEmpty()) {// 用户名为空
			DiyToast.showTaost(context, "请输入用户名");
		} else if (passward.isEmpty()) {
			DiyToast.showTaost(context, "请输入密码");
		} else {
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward  =?",
					new String[] { username, passward });
			if (cursor.moveToNext()) {
				login_state = true;
			} else {
				login_state = false;
				DiyToast.showTaost(context, "用户名或密码输入错误");
			}
		}
	}
}
