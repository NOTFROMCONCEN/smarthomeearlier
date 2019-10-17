package com.example.listonclick;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/*
 * @文件名：DialogSQLActivity.java
 * @描述：弹出Dialog
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
 */
public class DialogSQLActivity extends Activity {
	private ListView lv_dialog_sql;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dialog_sqlite);
		lv_dialog_sql = (ListView) findViewById(R.id.lv_dialog_sql);
		getsqlite();
		lv_dialog_sql.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(final AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) parent.getItemAtPosition(position);
				final String username_get = cursor.getString(1);
				final String passward_get = cursor.getString(2);

				new AlertDialog.Builder(DialogSQLActivity.this).setItems(
						new String[] { "删除", "取消" },
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								if (which == 0) {
									DiyToast.showToast(DialogSQLActivity.this,
											"0" + String.valueOf(position));
									DiyToast.showToast(DialogSQLActivity.this,
											"已删除：用户名" + username_get + "\n"
													+ "密码" + passward_get);
									db.execSQL(
											"delete from user where username = ? and passward = ?",
											new String[] { username_get,
													passward_get });
									lv_dialog_sql.setAdapter(null);
									getsqlite();
								}
								if (which == 1) {
									DiyToast.showToast(DialogSQLActivity.this,
											"1");
								}
							}
						}).show();
			}
		});
	}

	public void getsqlite() {
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from user", null);
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();
			SimpleCursorAdapter adapter = new SimpleCursorAdapter(
					getApplicationContext(), R.layout.activity_text, cursor,
					new String[] { "username", "passward" }, new int[] {
							R.id.tv_username, R.id.tv_passward });
			lv_dialog_sql.setAdapter(adapter);
		}
	}
}
