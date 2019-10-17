package com.example.listviewandcheckboxforsqlitedemo2;

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
import android.widget.Button;
import android.widget.ListView;

import com.example.listviewandcheckboxforsqlitedemo2.adapter.Bean;
import com.example.listviewandcheckboxforsqlitedemo2.adapter.ListAdapter;
import com.example.listviewandcheckboxforsqlitedemo2.mysql.MydataBaseHelper;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面、数据库操作、显示数据
 * @package_name com.example.listviewandcheckboxforsqlitedemo2
 * @project_name ListViewAndCheckBoxForSQLiteDemo2
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	private Button btn_delete;// 删除
	private Button btn_update;// 更新(修改)
	private ListView lv_1;

	// 适配器
	ListAdapter adapter;

	// 数据库
	MydataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		initData();
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DeleteData();
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_update = (Button) findViewById(R.id.btn_update);
		lv_1 = (ListView) findViewById(R.id.listView1);
		dbHelper = new MydataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	private void DeleteData() {
		Map<Integer, Boolean> isCheck_delete = adapter.getMap();
		int count = adapter.getCount();
		for (int i = 0; i < count; i++) {
			int postion = i - (count - adapter.getCount());
			if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
				isCheck_delete.remove(i);
				adapter.removeData(postion);
				initData();
			}
		}
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
			adapter = new ListAdapter(this);
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