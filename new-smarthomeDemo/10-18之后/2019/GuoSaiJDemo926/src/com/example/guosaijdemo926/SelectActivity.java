package com.example.guosaijdemo926;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Contacts.Intents.Insert;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

/*
 * @文件名：SelectActivity.java
 * @描述：查询数据库
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-29
 */
public class SelectActivity extends Activity {
	private Button btn_select;// 查询按钮
	private ListView lv_1;
	private Spinner sp_1;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_get);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		btn_select = (Button) findViewById(R.id.btn_sqlite_get);
		sp_1 = (Spinner) findViewById(R.id.spinner1);
		lv_1 = (ListView) findViewById(R.id.lv_sqlite);
		// 查找按钮
		btn_select.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (sp_1.getSelectedItem().toString().equals("全部")) {
					getdata();
				} else {
					InsertIntoSQLforActivity.getsql(getApplicationContext(),
							sp_1.getSelectedItem().toString());
					lv_1.setAdapter(InsertIntoSQLforActivity.adapter);
				}
			}
		});
		getdata();
	}

	/*
	 * @方法名：getdata
	 * 
	 * @功 能：展示数据库所有数据并插入列表
	 * 
	 * @时 间：上午8:18:56
	 */
	private void getdata() {
		Cursor cursor = db.rawQuery("select * from data", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				SelectActivity.this, R.layout.activity_sqlite_text, cursor,
				new String[] { "number", "data", "base" }, new int[] {
						R.id.tv_num, R.id.tv_chuanganqi, R.id.tv_getdata });
		lv_1.setAdapter(adapter);
	}
}
