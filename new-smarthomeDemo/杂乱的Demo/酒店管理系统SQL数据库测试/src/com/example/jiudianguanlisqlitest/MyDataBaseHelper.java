package com.example.jiudianguanlisqlitest;

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
		db.execSQL("create table room8101 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8102 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8103 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8104 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8201 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8202 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8203 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8204 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8301 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8302 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8303 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8304 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8401 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8402 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8403 (_id integer primary key autoincrement,roomstate text)");
		db.execSQL("create table room8404 (_id integer primary key autoincrement,roomstate text)");
		// 1-可入住
		// 2-未打扫
		// 3-已入住
		db.execSQL("insert into room8101 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8102 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8103 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8104 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8201 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8202 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8203 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8204 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8301 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8302 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8303 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8304 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8401 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8402 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8403 (roomstate)values(?)",
				new String[] { "1" });
		db.execSQL("insert into room8404 (roomstate)values(?)",
				new String[] { "1" });
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
