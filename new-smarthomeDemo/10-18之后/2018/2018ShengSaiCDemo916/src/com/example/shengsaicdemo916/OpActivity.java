package com.example.shengsaicdemo916;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;

/*
 * @文件名：OpActivity.java
 * @描述：账户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class OpActivity extends Fragment {
	private Button btn_delete;// 删除
	private Button btn_updata_pass;// 修改密码
	private ListView lv_1;
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	String get_user, get_pass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, container, false);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		btn_delete = (Button) view.findViewById(R.id.btn_delete);
		btn_updata_pass = (Button) view.findViewById(R.id.btn_updata_pass);
		if (!LoginActivity.login_op.equals("管理员")) {
			new AlertDialog.Builder(getActivity())
					.setTitle("提示")
					.setMessage("你非管理员账号不能进行该操作")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									BarActivity.mViewPager.setCurrentItem(0);
								}
							}).show();
		}
		// 设置ListView
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				get_pass = cursor.getString(2);
				DiyToast.showToast(getActivity(), "即将进行操作的用户：" + get_user);
			}
		});
		btn_delete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					new AlertDialog.Builder(getActivity())
							.setTitle("提示")
							.setMessage("请选择要删除的账号信息？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

										}
									}).show();
				} else {
					new AlertDialog.Builder(getActivity())
							.setTitle("提示")
							.setMessage("确定要删除吗？")
							.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											db.execSQL(
													"delete from user where username = ? and passward = ?",
													new String[] { get_user,
															get_pass });
										}
									}).show();
				}
			}
		});
		// 插入
		getdata();

		return view;
	}

	private void getdata() {
		Cursor c = db.rawQuery("select * from user", null);
		c.moveToFirst();
		SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
				getActivity(), R.layout.activity_text, c, new String[] {
						"username", "passward" }, new int[] { R.id.tv_user,
						R.id.tv_pass });
		lv_1.setAdapter(simpleCursorAdapter);
	}
}
