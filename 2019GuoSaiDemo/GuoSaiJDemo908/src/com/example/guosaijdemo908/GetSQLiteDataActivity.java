package com.example.guosaijdemo908;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class GetSQLiteDataActivity extends Activity {
	// 数据库
	private SQLiteDatabase db;
	private MyDataBaseHelper dbHelper;
	// ListView
	private ListView lv_sqlite;
	// Spinner
	private Spinner sp_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_get);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_sqlite = (ListView) findViewById(R.id.lv_sqlite);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		// 激活
		handler.post(timeRunnable);

	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			System.out.println("open");

			if (sp_1.equals("温度")) {
				System.out.println("温度");
				Cursor c = db.rawQuery("select * from temp_data", null);
				if (c.getCount() != 0) {
					c.moveToLast();
					SimpleCursorAdapter adapter = new SimpleCursorAdapter(
							GetSQLiteDataActivity.this,
							R.layout.activity_sqlite_text, c, new String[] {
									"data", "温度" }, new int[] {
									R.id.tv_getdata, R.id.tv_chuanganqi });
					lv_sqlite.setAdapter(adapter);
					System.out.println(c);
				}
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}
