package com.example.shengsaiyangtidemo9282019;

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
		db.execSQL("create table login_number (_id integer primary key autoincrement,number text,time text)");
		db.execSQL("insert into login_number (number,time)values(?,?)",
				new String[] { "0", "0" });
		db.execSQL("create table base_state (_id integer primary key autoincrement,name text,state text)");
		db.execSQL("create table link_state (_id integer primary key autoincrement,number text,name text,state text,time text)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
