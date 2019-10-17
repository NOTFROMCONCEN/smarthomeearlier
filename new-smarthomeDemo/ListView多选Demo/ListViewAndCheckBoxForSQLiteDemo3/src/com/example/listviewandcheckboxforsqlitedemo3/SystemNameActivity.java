package com.example.listviewandcheckboxforsqlitedemo3;

import java.util.ArrayList;
import java.util.List;

import com.example.listviewandcheckboxforsqlitedemo3.adapter.Bean;
import com.example.listviewandcheckboxforsqlitedemo3.adapter.MyListViewAdapter;
import com.example.listviewandcheckboxforsqlitedemo3.mysql.MyDataBaseHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 显示所有账号
 * @package_name com.example.listviewandcheckboxforsqlitedemo3
 * @project_name ListViewAndCheckBoxForSQLiteDemo3
 * @file_name SystemNameActivity.java
 */
public class SystemNameActivity extends Activity {
	private MyListViewAdapter list;
	private Button btn_delete;// 删除
	private ListView lv_1;// ListView

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	MyListViewAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 无title
		setContentView(R.layout.activity_system);
		initView();// 绑定
		initData();// 获取数据
	}

	// 绑定
	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_delete);
		lv_1 = (ListView) findViewById(R.id.lv_1);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	// 获取数据
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
}
