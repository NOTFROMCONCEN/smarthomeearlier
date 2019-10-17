/**
 * 
 */
package com.example.listviewandcheckboxforsqlitedemo6;

import java.util.ArrayList;
import java.util.List;

import com.example.listviewandcheckboxforsqlitedemo6.adapter.Bean;
import com.example.listviewandcheckboxforsqlitedemo6.adapter.MyListViewBaseAdapter;
import com.example.listviewandcheckboxforsqlitedemo6.mysql.MyDataBaseHelper;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面
 * @package_name com.example.listviewandcheckboxforsqlitedemo6
 * @project_name ListViewAndCheckBoxForSQLiteDemo6
 * @file_name UserInfoActivity.java
 */
public class UserInfoActivity extends Activity {
	private Button btn_delete;// 删除
	private Button btn_update;// 更新
	private Button btn_selectall;// 全选
	private ListView lv_1;// ListView

	// 适配器
	MyListViewBaseAdapter adapter;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_userinfo);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		initData();
	}

	// 绑定
	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_update = (Button) findViewById(R.id.btn_update);
		btn_selectall = (Button) findViewById(R.id.btn_selectall);
		lv_1 = (ListView) findViewById(R.id.listView1);
	}

	// 插入数据
	private void initData() {
		// TODO Auto-generated method stub
		Cursor cursor = db.rawQuery("select * from user", null);
		List<Bean> list = new ArrayList<Bean>();
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward"))));
			}
			adapter = new MyListViewBaseAdapter(getApplicationContext());
			adapter.setData(list);
			lv_1.setAdapter(adapter);
		}
	}
}