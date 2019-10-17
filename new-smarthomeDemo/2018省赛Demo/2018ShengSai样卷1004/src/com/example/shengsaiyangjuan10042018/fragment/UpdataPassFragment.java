package com.example.shengsaiyangjuan10042018.fragment;

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

import com.example.shengsaiyangjuan10042018.R;
import com.example.shengsaiyangjuan10042018.sqlite.MyDataBaseHelper;
import com.example.shengsaiyangjuan10042018.toast.DiyToast;

/*
 * @文件名：UpdataPassFragment.java
 * @描述：修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-4
 */
public class UpdataPassFragment extends Fragment {
	private Button btn_updata_con;
	private EditText et_user, et_pass, et_newpass, et_repass;
	private String user, oldpass, newpass, repass;
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_updata_pass, container,
				false);
		initView(view);// 绑定控件
		btn_updata_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				newpass = et_newpass.getText().toString();// 新密码
				oldpass = et_pass.getText().toString();// 旧密码
				repass = et_repass.getText().toString();// 确认密码
				if (user.isEmpty() || oldpass.isEmpty() || newpass.isEmpty()
						|| repass.isEmpty()) {
					DiyToast.showToast(getActivity(), "不能有空白项");
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { user });// 新建数据库游标
					if (cursor.moveToNext()) {// 游标移动
						if (oldpass.equals(cursor.getString(cursor
								.getColumnIndex("passward")))) {
							if (newpass.equals(oldpass)) {
								DiyToast.showToast(getActivity(), "新旧密码不能一致");
							} else {
								if (newpass.equals(repass)) {
									db.execSQL(
											"update user set passward = ? where username = ?",
											new String[] { newpass, user });
									DiyToast.showToast(getActivity(), "修改完成");
								} else {
									DiyToast.showToast(getActivity(),
											"新密码和确认密码不一致");
								}
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

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午10:03:20
	 */
	private void initView(View view) {
		// TODO Auto-generated method stub
		btn_updata_con = (Button) view.findViewById(R.id.btn_updata_con);
		et_newpass = (EditText) view.findViewById(R.id.et_updata_newpass);
		et_pass = (EditText) view.findViewById(R.id.et_updata_oldpass);
		et_repass = (EditText) view.findViewById(R.id.et_updata_repass);
		et_user = (EditText) view.findViewById(R.id.et_updata_user);
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
	}
}
