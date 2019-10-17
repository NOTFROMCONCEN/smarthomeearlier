package com.example.guosaifdemo926;

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
		db.execSQL("create table user (_id integer primary key autoincrement,username text,passward text,twopass text)");
		db.execSQL("create table login_rember (_id integer primary key autoincrement,username text,ip text,port text,passward text,rember_state text)");
		db.execSQL("create table shebei_state (_id integer primary key autoincrement,shebei text,state text)");
		db.execSQL("create table link_state (_id integer primary key autoincrement,one text,two text,number text,three text,name text)");
		db.execSQL("create table basedata (_id integer primary key autoincrement,temp text, smo text, hum text, press text, ill text, gas text, co text, pm text, per text)");
		db.execSQL("create table data (_id integer primary key autoincrement,num text,data text)");
		db.execSQL("create table radiostate(_id integer primary key autoincrement,radioname text,radiostate text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
