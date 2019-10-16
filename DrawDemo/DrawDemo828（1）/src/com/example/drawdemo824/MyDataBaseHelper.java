package com.example.drawdemo824;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * @文件名：MyDataBaseHelper.java
 * @描述：数据库
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-23
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {

	public MyDataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text)");
		db.execSQL("create table data (_id integer primary key autoincrement,num text,data text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
