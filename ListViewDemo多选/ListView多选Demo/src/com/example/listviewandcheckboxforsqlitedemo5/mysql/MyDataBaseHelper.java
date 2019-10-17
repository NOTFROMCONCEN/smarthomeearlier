package com.example.listviewandcheckboxforsqlitedemo5.mysql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
				new String[] { "root", "123456" });
		for (int i = 0; i < 999; i++) {
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
