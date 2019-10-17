package com.example.listonclick;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/*
 * @文件名：MainActivity.java
 * @描述：读取数据库、设置listview点击
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-3
 */
public class MainActivity extends Activity {
	private ListView lv_1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getReadableDatabase();
		lv_1 = (ListView) findViewById(R.id.listView1);
		lv_1.setAdapter(null);
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				System.out.println(arg2);
			}
		});
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					MainActivity.this,
					android.R.layout.simple_list_item_checked, cursor,
					new String[] { "username", "passward" }, new int[] {
							android.R.id.text1, android.R.id.text2 });
			lv_1.setAdapter(adapter);
		} else {
			Toast.makeText(MainActivity.this, "数据库中没有记录！", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
