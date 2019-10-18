package com.example.guosaibdemo2018915;

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
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text,op text)");
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "bizideal", "123456", "管理员" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "1", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "2", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "3", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "4", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "5", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "6", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "7", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "8", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "9", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "11", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "21", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "31", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "41", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "51", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "61", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "71", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "81", "1", "用户" });
		db.execSQL("insert into user (username,passward,op)values(?,?,?)",
				new String[] { "91", "1", "用户" });
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
}
