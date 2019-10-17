package com.example.listviewandcheckboxforsqlite;

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
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.listviewandcheckboxforsqlite.adapter.Bean;
import com.example.listviewandcheckboxforsqlite.adapter.ListAdapter;
import com.example.listviewandcheckboxforsqlite.mysql.MyDataBaseHelper;
import com.example.listviewandcheckboxforsqlite.toast.DiyToast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面（ListView显示、删除、增加、全选取消全选）
 * @package_name com.example.listviewandcheckbox
 * @project_name ListViewAndCheckBox
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	private ListView lv_1;// ListView
	private TextView tv_add;// 添加
	private Button btn_select_all, btn_delete;// 全选、删除按钮
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	ListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		initView();
		// setUser();
		initData();
		// 删除
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 拿到所有数据
				Map<Integer, Boolean> isCheck_delete = adapter.getMap();
				// 获取到条目数量，map.size = list.size,所以
				int count = adapter.getCount();
				// 遍历
				for (int i = 0; i < count; i++) {
					// 删除有两个map和list都要删除 ,计算方式
					int position = i - (count - adapter.getCount());
					// 判断状态 true为删除
					if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
						// listview删除数据
						isCheck_delete.remove(i);
						adapter.removeData(position);
						initData();
					}
				}
			}
		});
	}

	private void initData() {
		// 默认显示的数据
		Cursor cursor = db.rawQuery("select * from user", null);
		List<Bean> list = new ArrayList<Bean>();
		if (cursor.getCount() != 0) {
			for (int i = 0; i < cursor.getCount(); i++) {
				cursor.moveToNext();
				// list.add(Bean());
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward"))));
			}
		}
		adapter = new ListAdapter(this);
		adapter.setData(list);
		lv_1.setAdapter(adapter);
	}

	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_detele);
		btn_select_all = (Button) findViewById(R.id.btn_select_all);
		tv_add = (TextView) findViewById(R.id.tv_add);
		lv_1 = (ListView) findViewById(R.id.listview);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}