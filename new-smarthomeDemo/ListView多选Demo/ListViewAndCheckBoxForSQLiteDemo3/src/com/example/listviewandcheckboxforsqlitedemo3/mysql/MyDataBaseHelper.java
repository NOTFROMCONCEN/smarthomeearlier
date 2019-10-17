package com.example.listviewandcheckboxforsqlitedemo3.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 数据库创建
 * @package_name com.example.listviewandcheckboxforsqlitedemo3.mysql
 * @project_name ListViewAndCheckBoxForSQLiteDemo3
 * @file_name MyDataBaseHelper.java
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
		db.execSQL("insert into user (username,passward)values(?,?)",
				new String[] { "root", "xiaoyu0632" });
		for (int i = 0; i < 200; i++) {
			db.execSQL(
					"insert into user (username,passward)values(?,?)",
					new String[] { "user" + String.valueOf(i),
							"pass" + String.valueOf(i) });
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
