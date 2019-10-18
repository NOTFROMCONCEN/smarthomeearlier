package com.example.guosaiedemo950;

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
		db.execSQL("create table login_rember (_id integer primary key autoincrement,login_time text,login_number text)");
		db.execSQL(
				"insert into login_rember (login_time,login_number)values(?,?)",
				new String[] { "0", "0" });
		db.execSQL("create table log (_id integer primary key autoincrement,number text,shebei text,state text,time text)");
		db.execSQL(
				"insert into log (number ,shebei ,state ,time )values(?,?,?,?)",
				new String[] { "1", "opensystem", "1", "1" });
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
