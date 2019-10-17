package com.example.listviewandcheckboxforsqlitedemo4;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.example.listviewandcheckboxforsqlitedemo4.adapter.Bean;
import com.example.listviewandcheckboxforsqlitedemo4.adapter.MyListViewAdapter;
import com.example.listviewandcheckboxforsqlitedemo4.mysql.MydataBaseHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面
 * @package_name com.example.listviewandcheckboxforsqlitedemo4
 * @project_name ListViewAndCheckBoxForSQLiteDemo4
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	// 数据库
	MydataBaseHelper dbHelper;
	SQLiteDatabase db;

	// 适配器
	MyListViewAdapter adapter;

	// 控件
	private ListView lv_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		findViewById(R.id.btn_delete).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Map<Integer, Boolean> isCheck_delete = adapter.getMap();
				int count = adapter.getCount();
				for (int i = 0; i < count; i++) {
					int postion = i - (count - adapter.getCount());
					if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
						isCheck_delete.remove(i);
						adapter.removeData(postion);
						System.out.println(count);
						initData();
					}
				}
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		lv_1 = (ListView) findViewById(R.id.listView1);
		// 数据库
		dbHelper = new MydataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	private void initData() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from user", null);
		List<Bean> list = new ArrayList<Bean>();
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				list.add(new Bean(cursor.getString(
						cursor.getColumnIndex("username")).toString(), cursor
						.getString(cursor.getColumnIndex("passward"))));
			}
			adapter = new MyListViewAdapter(this);
			adapter.setData(list);
			lv_1.setAdapter(adapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}