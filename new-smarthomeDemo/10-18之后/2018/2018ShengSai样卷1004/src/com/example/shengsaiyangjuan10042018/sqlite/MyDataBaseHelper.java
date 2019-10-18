package com.example.shengsaiyangjuan10042018.sqlite;

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
				new String[] { "bizideal", "123456" });
		db.execSQL("create table data (_id integer primary key autoincrement,wendu text,shidu text,guangzhao text,ranqi text,qiya text,co2 text,pm25 text,yanwu text,renti text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
