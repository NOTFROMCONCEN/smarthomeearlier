package com.example.shengsaiademo10052018.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;

import com.example.shengsaiademo10052018.LoginActivity;
import com.example.shengsaiademo10052018.R;
import com.example.shengsaiademo10052018.mysql.MyDataBaseHelper;
import com.example.shengsaiademo10052018.toast.DiyToast;

/*
 * @文件名：SettingActivity.java
 * @描述：用户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-5
 */
public class SettingActivity extends Fragment {
	private ListView lv_1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	static String username;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_setting, container,
				false);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		getdata();
		lv_1.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int postion, long id) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) parent.getItemAtPosition(postion);
				final String get_user = cursor.getString(1);
				final String get_pass = cursor.getString(2);
				new AlertDialog.Builder(getActivity()).setItems(
						new String[] { "更新", "删除" },
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								switch (which) {
								case 0:
									username = get_user;
									updata_pass_Dialog();
									break;
								case 1:
									if (get_user.equals("bizideal")) {
										DiyToast.showToast(getActivity(),
												"你不能删除管理员账号");
									} else {
										db.execSQL(
												"delete from user where username = ? and passward = ?",
												new String[] { get_user,
														get_pass });
										LoginActivity.sharedPreferences.edit()
												.putBoolean("autologin", false)
												.putBoolean("rember", false)
												.putString("user", "")
												.putString("pass", "")
												.putString("ip", "").commit();
										getdata();
									}
									break;
								default:
									break;
								}
							}
						}).show();
				return false;
			}
		});
		return view;
	}

	/*
	 * @方法名：updata_pass_Dialog
	 * 
	 * @功 能：修改密码
	 * 
	 * @时 间：上午9:49:24
	 */
	private void updata_pass_Dialog() {
		DiyToast.showToast(getActivity(), username);
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
		final View view = layoutInflater.inflate(R.layout.activity_view_dialog,
				null, false);
		builder.setView(view);
		builder.setTitle("修改密码");
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_passward_get;
				et_passward_get = (EditText) view
						.findViewById(R.id.et_updata_pass);
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { username });
				if (cursor.moveToNext()) {
					db.execSQL(
							"update user set passward = ? where username = ?",
							new String[] {
									et_passward_get.getText().toString(),
									username });// 更新数据库
					DiyToast.showToast(getActivity(), "修改成功");
					LoginActivity.sharedPreferences.edit()
							.putBoolean("autologin", false)
							.putBoolean("rember", false).putString("user", "")
							.putString("pass", "").putString("ip", "").commit();// 修改偏好设置（取消自动登录记住密码）
					getdata();// 更新ListView
					/*
					 * 题目没要求直接退出程序，不写退出了
					 */
					// System.exit(0);// 方法1
					// startActivity(new Intent(getActivity(),
					// LoginActivity.class));
					// getActivity().finish();// 方法2

				}
			}
		});
		builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		builder.show();
	}

	/*
	 * @方法名：getdata
	 * 
	 * @功 能：更新数据库(插入至ListView)
	 * 
	 * @时 间：上午9:48:18
	 */
	private void getdata() {
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
				getActivity(), R.layout.activity_text, c, new String[] {
						"username", "passward", "op" }, new int[] {
						R.id.tv_user, R.id.tv_pass, R.id.tv_op });
		lv_1.setAdapter(cursorAdapter);
	}
}
