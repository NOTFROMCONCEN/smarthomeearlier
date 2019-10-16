package com.example.shengsaie920217;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class UpdataPassActivity extends Fragment {
	private EditText et_updata_user;// 用户名
	private EditText et_updata_oldpass;// 旧密码
	private EditText et_updata_newpass;// 新密码
	private EditText et_updata_repass;// 确认密码
	private Button btn_updata_con;// 确定
	private String updata_user, updata_newpass, updata_oldpass, updata_repass;
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_updata_pass, container,
				false);
		et_updata_newpass = (EditText) view
				.findViewById(R.id.et_updata_newpass);
		et_updata_oldpass = (EditText) view
				.findViewById(R.id.et_updata_oldpass);
		et_updata_repass = (EditText) view.findViewById(R.id.et_updata_repass);
		et_updata_user = (EditText) view.findViewById(R.id.et_updata_user);
		btn_updata_con = (Button) view.findViewById(R.id.btn_updata_con);
		// 数据库
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updata_newpass = et_updata_newpass.getText().toString();// 新密码
				updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
				updata_user = et_updata_user.getText().toString();// 用户名
				updata_repass = et_updata_repass.getText().toString();// 确认密码
				if (updata_user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getActivity(), "用户名不能为空");
				} else if (updata_oldpass.isEmpty()) {// 旧密码为空
					DiyToast.showToast(getActivity(), "旧密码不能为空");
				} else if (updata_newpass.isEmpty()) {// 新密码为空
					DiyToast.showToast(getActivity(), "新密码不能为空");
				} else if (updata_repass.isEmpty()) {// 确认密码为空
					DiyToast.showToast(getActivity(), "确认密码不能为空");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });// 新建数据库游标
					if (cursor.moveToNext()) {
						String get_old_pasString = cursor.getString(cursor
								.getColumnIndex("passward"));// 查找密码
						if (updata_oldpass.equals(get_old_pasString)) {
							if (updata_newpass.equals(updata_repass)) {
								if (updata_newpass.equals(updata_oldpass)) {
									DiyToast.showToast(getActivity(),
											"新旧密码不能一致");
								} else {
									db.execSQL(
											"update user set passward = ? where username = ?",
											new String[] { updata_newpass,
													updata_user });// 更新数据
									DiyToast.showToast(getActivity(), "更新成功");
									Intent intent = new Intent(getActivity(),
											MainActivity.class);
									startActivity(intent);
									getActivity().finish();
								}
							} else {
								DiyToast.showToast(getActivity(), "新密码和确认密码不一致");
							}
						} else {
							DiyToast.showToast(getActivity(), "旧密码输入错误");
						}
					} else {
						DiyToast.showToast(getActivity(), "用户名不存在");
					}
				}
			}
		});
		return view;
	}
}