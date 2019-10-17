package com.example.shengsaibdemo10062018.index;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.example.shengsaibdemo10062018.R;
import com.example.shengsaibdemo10062018.mysql.MyDataBaseHelper;
import com.example.shengsaibdemo10062018.toast.DiyToast;

/*
 * @文件名：OpActivity.java
 * @描述：账户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-6
 */
public class OpActivity extends Activity {
	private Button btn_delete;
	private ListView lv_1;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	String get_user = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_op);
		btn_delete = (Button) findViewById(R.id.btn_delete);
		lv_1 = (ListView) findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		getdata();// 加载进ListView
		// ListView点击事件
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				DiyToast.showToast(getApplicationContext(), "您选中了：" + get_user);
			}
		});
		// 删除按钮
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					new AlertDialog.Builder(OpActivity.this)
							.setTitle("警告")
							.setIcon(android.R.drawable.ic_dialog_dialer)
							.setMessage("您未选中任何条目，或者说您想全部删除？")
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									})
							.setPositiveButton("全部删除",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											db.execSQL("delete * from user");
										}
									}).show();
				} else {
					db.execSQL("delete * from user where username = ?",
							new String[] { get_user });
				}
			}
		});
	}

	/*
	 * @方法名：getdata
	 * 
	 * @功 能：加载ListView内容
	 * 
	 * @时 间：上午9:54:53
	 */
	private void getdata() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				OpActivity.this, R.layout.activity_op_text, c, new String[] {
						"username", "passward" }, new int[] { R.id.tv_username,
						R.id.tv_pass });
		lv_1.setAdapter(simpleCursorAdapter);
	}
}
