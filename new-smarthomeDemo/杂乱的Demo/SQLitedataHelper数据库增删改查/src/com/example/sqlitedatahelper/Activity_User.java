package com.example.sqlitedatahelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

/*
 * @文件名：Activity_Login.java
 * @描述：账户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class Activity_User extends Activity implements OnClickListener {
	private Button btn_getsqlite, btn_deletesqliute;// 按钮
	private ListView lv_1;// ListView
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;// 数据库

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		btn_deletesqliute = (Button) findViewById(R.id.btn_deletesqliute);
		btn_getsqlite = (Button) findViewById(R.id.btn_getsqlite);
		lv_1 = (ListView) findViewById(R.id.listView1);
		btn_getsqlite.setOnClickListener(this);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_getsqlite:
			lv_1.setAdapter(null);
			Cursor cursor = db.rawQuery("select * from user", null);
			if (cursor.getCount() != 0) {
				// cursor.moveToFirst();
				SimpleCursorAdapter adapter = new SimpleCursorAdapter(
						Activity_User.this, R.layout.activity_text, cursor,
						new String[] { "username", "passward" }, new int[] {
								R.id.tv_username, R.id.tv_passward });
				lv_1.setAdapter(adapter);
			} else {
				Toast.makeText(Activity_User.this, "数据库中没有记录！",
						Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}
}