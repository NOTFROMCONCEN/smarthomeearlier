package com.example.guosaibdemo2018915;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/*
 * @文件名：SettingActivity.java
 * @描述：账户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-15
 */
public class SettingActivity extends Activity {
	private Button btn_sql_delete;
	private ListView listView;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private ArrayAdapter<String> mAdapter;
	private List<String> list = new ArrayList<String>();
	private String get_username, get_passward;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		btn_sql_delete = (Button) findViewById(R.id.btn_sql_delete);
		listView = (ListView) findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		getdata();
		// 设置删除按钮点击事件
		btn_sql_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_username.isEmpty() || get_passward.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "请选择要删除的账号");
				} else {
					if (get_username.equals("bizideal")) {
						DiyToast.showToast(getApplicationContext(),
								"你不能删除管理员账号");
					} else {
						db.execSQL(
								"delete from user where username = ? and passward = ?",
								new String[] { get_username, get_passward });
						DiyToast.showToast(getApplicationContext(), "删除成功");
						getdata();
					}
				}
			}
		});
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_username = cursor.getString(1);
				get_passward = cursor.getString(2);
				DiyToast.showToast(getApplicationContext(), "将要删除的用户："
						+ get_username);
			}
		});
	}

	// 从数据库取出数值
	private void getdata() {
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
				SettingActivity.this, R.layout.activity_text, c, new String[] {
						"username", "passward" }, new int[] { R.id.tv_user,
						R.id.tv_pass });
		listView.setAdapter(cursorAdapter);
	}
}
