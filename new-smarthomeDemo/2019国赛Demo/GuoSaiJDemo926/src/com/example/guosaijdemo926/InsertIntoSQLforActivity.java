package com.example.guosaijdemo926;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SimpleCursorAdapter;

public class InsertIntoSQLforActivity {
	public static SimpleCursorAdapter adapter;

	public static void getsql(Context context, String data) {
		MyDataBaseHelper dbHelper;
		SQLiteDatabase db;
		dbHelper = new MyDataBaseHelper(context, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from data where data = ?",
				new String[] { data });
		adapter = new SimpleCursorAdapter(context,
				R.layout.activity_sqlite_text, cursor, new String[] { "number",
						"data", "base" }, new int[] { R.id.tv_num,
						R.id.tv_chuanganqi, R.id.tv_getdata });
	}
}