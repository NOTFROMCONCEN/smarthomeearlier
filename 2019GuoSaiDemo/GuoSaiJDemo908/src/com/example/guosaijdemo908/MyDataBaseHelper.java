package com.example.guosaijdemo908;

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
		db.execSQL("create table temp_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table hum_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table ill_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table smo_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table gas_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table press_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table pm_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table co_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table per_data (_id integer primary key autoincrement,data text)");
		db.execSQL("create table data (_id integer primary key autoincrement,num text,data text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
