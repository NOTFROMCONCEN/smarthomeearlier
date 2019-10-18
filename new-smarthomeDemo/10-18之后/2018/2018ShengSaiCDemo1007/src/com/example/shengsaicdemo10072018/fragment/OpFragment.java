package com.example.shengsaicdemo10072018.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.ListView;

import com.example.shengsaicdemo10072018.LoginActivity;
import com.example.shengsaicdemo10072018.R;
import com.example.shengsaicdemo10072018.mysql.MyDataBaseHelper;
import com.example.shengsaicdemo10072018.toast.DiyToast;

/*
 * @文件名：OpFragment.java
 * @描述：账户管理
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-7
 */
public class OpFragment extends Fragment {
	private Button btn_updata_pass;// 修改密码按钮
	private Button btn_del;// 删除按钮
	private ListView lv_1;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private String get_user = "";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_op, container, false);
		btn_del = (Button) view.findViewById(R.id.btn_del);
		btn_updata_pass = (Button) view.findViewById(R.id.btn_updata_pass);
		lv_1 = (ListView) view.findViewById(R.id.listView1);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		getdata();// 更新
		// ListView点击选择账户功能
		lv_1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				get_user = cursor.getString(1);
				DiyToast.showToast(getActivity(), "你选择了：" + get_user);

			}
		});
		btn_updata_pass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
				LayoutInflater layoutInflater = LayoutInflater
						.from(getActivity());
				final View view = layoutInflater.inflate(
						R.layout.dialog_updata_pass, null, false);
				b.setView(view);
				b.setPositiveButton("确定",
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								// TODO Auto-generated method stub
								EditText et_updata_pass = (EditText) view
										.findViewById(R.id.et_updata_pass);
								if (et_updata_pass.getText().toString()
										.isEmpty()) {
									DiyToast.showToast(getActivity(), "数值不能为空");
								} else {
									if (get_user.isEmpty()) {
										DiyToast.showToast(getActivity(),
												"请选择用户名");
									} else {
										db.execSQL(
												"update user set passward = ? where username = ?",
												new String[] {
														et_updata_pass
																.getText()
																.toString(),
														get_user });
										DiyToast.showToast(getActivity(),
												"修改成功");
										LoginActivity.sharedPreferences = getActivity()
												.getSharedPreferences(
														"rember",
														getActivity().MODE_WORLD_WRITEABLE);
										LoginActivity.sharedPreferences.edit()
												.putBoolean("rember", false)
												.putBoolean("autologin", false)
												.putString("user", "")
												.putString("pass", "")
												.putString("ip", "").commit();
										startActivity(new Intent(getActivity(),
												LoginActivity.class));
										getActivity().finish();
									}
								}
							}
						});
				b.show();
			}
		});
		// 删除按钮
		btn_del.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (get_user.isEmpty()) {
					DiyToast.showToast(getActivity(), "请先选择");
				} else {
					if (get_user.equals("bizideal")) {
						DiyToast.showToast(getActivity(), "你不能删除管理员账号");
					} else {
						new AlertDialog.Builder(getActivity())
								.setTitle("警告")
								.setMessage("你确定要删除账号信息？")
								.setPositiveButton("确定",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub
												delete();
											}
										})
								.setNegativeButton("取消",
										new DialogInterface.OnClickListener() {

											@Override
											public void onClick(
													DialogInterface dialog,
													int which) {
												// TODO Auto-generated method
												// stub

											}
										}).show();
					}
				}
			}
		});

		return view;
	}

	private void delete() {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(getActivity()).setTitle("警告")
				.setMessage("确定要删除吗？")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method
						// stub
						db.execSQL("delete * from user where username = ?",
								new String[] { get_user });
					}
				}).show();
	}

	/*
	 * @方法名：getdata
	 * 
	 * @功 能：更新ListVioew内容
	 * 
	 * @时 间：上午10:11:02
	 */
	private void getdata() {
		// TODO Auto-generated method stub
		Cursor c = db.rawQuery("select * from user", null);
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(),
				R.layout.activity_op_text, c, new String[] { "username",
						"passward" }, new int[] { R.id.tv_username,
						R.id.tv_passward });
		lv_1.setAdapter(adapter);
	}
}