package com.example.listviewandcheckboxforsqlitedemo5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.listviewandcheckboxforsqlitedemo5.adapter.Bean;
import com.example.listviewandcheckboxforsqlitedemo5.adapter.MyListViewAdapter;
import com.example.listviewandcheckboxforsqlitedemo5.mysql.MyDataBaseHelper;
import com.example.listviewandcheckboxforsqlitedemo5.toast.DiyToast;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主界面
 * @package_name com.example.listviewandcheckboxforsqlitedemo5
 * @project_name ListViewAndCheckBoxForSQLiteDemo5
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// 适配器
	MyListViewAdapter mylistadapter;
	// 控件
	private ListView lv_1;
	private Button btn_delete, btn_update;
	public static String get_user = null;
	private List<String> user_info = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定
		initData();// 插入数据
		// 删除按钮及功能
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Map<Integer, Boolean> isCheck_delete = mylistadapter.getMap();
				int count = mylistadapter.getCount();
				for (int i = 0; i < count; i++) {
					int postion = i - (count - mylistadapter.getCount());
					if (isCheck_delete.get(i) != null && isCheck_delete.get(i)) {
						isCheck_delete.remove(i);
						mylistadapter.remove(postion);
						initData();
					}
				}
			}
		});
		// 更新按钮及功能
		btn_update.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user_info.clear();
				Map<Integer, Boolean> isCheck_updata = mylistadapter.getMap();
				int count = mylistadapter.getCount();
				for (int i = 0; i < count; i++) {
					int postion = i - (count - mylistadapter.getCount());
					if (isCheck_updata.get(i) != null && isCheck_updata.get(i)) {
						mylistadapter.updata(postion);
						user_info.add(get_user);
					}
				}
				UpdataPass();
				// System.out.println(user_info);
			}
		});
	}

	// 自定义Dialog
	private void UpdataPass() {
		DiyToast.showToast(getApplicationContext(), "当前用户名：" + user_info);
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		View view = LayoutInflater.from(MainActivity.this).inflate(
				R.layout.dialog_updatapass, null, false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				for (int i = 0; i < user_info.size(); i++) {
					System.out.println(user_info.get(i));
					db.execSQL(
							"update user set passward = ? where username = ?",
							new String[] { "1", user_info.get(i) });
					initData();
				}
			}
		});
		builder.show();
	}

	// 绑定
	private void initView() {
		// TODO Auto-generated method stub
		btn_delete = (Button) findViewById(R.id.btn_delete);
		btn_update = (Button) findViewById(R.id.btn_update);
		lv_1 = (ListView) findViewById(R.id.listView1);
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
				list.add(new Bean(cursor.getString(cursor
						.getColumnIndex("username")), cursor.getString(cursor
						.getColumnIndex("passward"))));
			}
			mylistadapter = new MyListViewAdapter(getApplicationContext());
			mylistadapter.setData(list);
			lv_1.setAdapter(mylistadapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}