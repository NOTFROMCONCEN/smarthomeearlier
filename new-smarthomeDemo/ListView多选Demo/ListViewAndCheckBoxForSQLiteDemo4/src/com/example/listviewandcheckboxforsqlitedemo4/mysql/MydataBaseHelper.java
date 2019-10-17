package com.example.listviewandcheckboxforsqlitedemo4.mysql;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MydataBaseHelper extends SQLiteOpenHelper {

	public MydataBaseHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table user (_id integer pirmary key autoincremnet,username text,passward text)");
		db.execSQL("insert into user (username,passward)values(?,?)",
				new String[] { "root", "123456" });
		for (int i = 0; i < 99; i++) {
			db.execSQL(
					"insert into user (username,passward)values(?,?)",
					new String[] { "user" + String.valueOf(i),
							"pass" + String.valueOf(i) });
			System.out.println(i);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
