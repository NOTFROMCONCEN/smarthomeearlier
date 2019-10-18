package com.example.shengsaiyangjuan9172018;

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

public class UpdataActivity extends Fragment {
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// 定义
	private Button btn_updata_con;

	private EditText et_updata_newpass;
	private EditText et_updata_oldpass;
	private EditText et_updata_user;
	private EditText et_updata_repass;

	private String updata_newpass, updata_oldpass, updata_user, repass;

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
		et_updata_user = (EditText) view.findViewById(R.id.et_updata_user);
		btn_updata_con = (Button) view.findViewById(R.id.btn_updata_con);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 修改密码确定
				updata_newpass = et_updata_newpass.getText().toString();// 新密码
				updata_oldpass = et_updata_oldpass.getText().toString();// 旧密码
				updata_user = et_updata_user.getText().toString();
				if (updata_user.equals("")) {// 如果用户名为空
					DiyToast.showToast(getActivity(), "请输入用户名");
				} else if (updata_oldpass.equals("")) {// 如果旧密码为空
					DiyToast.showToast(getActivity(), "请输入旧密码");
				} else if (updata_newpass.equals("")) {// 如果新密码为空
					DiyToast.showToast(getActivity(), "请输入新密码");
				} else {
					if (!updata_newpass.equals(updata_oldpass)) {

						Cursor cur_updata_user = db.rawQuery(
								"select * from user where username = ?",
								new String[] { updata_user });// 新建数据库指针
						cur_updata_user.moveToFirst();
						String get_passString = cur_updata_user
								.getString(cur_updata_user
										.getColumnIndex("passward"));
						if (get_passString.equals(updata_oldpass)) {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { updata_newpass, updata_user });// 更新数据库
							DiyToast.showToast(getActivity(), "密码修改成功");
						} else {
							DiyToast.showToast(getActivity(), "旧密码输入错误");
						}
					} else {
						DiyToast.showToast(getActivity(), "新旧密码不能一致");
					}
				}
			}
		});
		return view;
	}
}
