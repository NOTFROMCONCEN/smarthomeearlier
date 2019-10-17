package com.example.guosaigdemo9262019;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/*
 * @文件名：SettingActivity.java
 * @描述：修改密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class SettingActivity extends Fragment {
	private EditText et_new_pass, et_old_pass;
	private ImageView iv_exit;
	private Button btn_updatapass_con;
	private String getusername, new_passward, old_passward, getoldpass;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.activity_setting, container,
				false);
		iv_exit = (ImageView) view.findViewById(R.id.iv_exit);
		btn_updatapass_con = (Button) view.findViewById(R.id.btn_updata_pass);
		et_new_pass = (EditText) view.findViewById(R.id.et_new_pass);
		et_old_pass = (EditText) view.findViewById(R.id.et_old_pass);
		getusername = "bizideal";
		// 退出按钮
		iv_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginActivity.sharedPreferences.edit()
						.putBoolean("rember", false)
						.putBoolean("autologin", false).commit();
				Intent intent = new Intent(getActivity(), LoginActivity.class);
				startActivity(intent);
				getActivity().finish();
			}
		});
		// 修改密码按钮
		btn_updatapass_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new_passward = et_new_pass.getText().toString();
				old_passward = et_old_pass.getText().toString();
				if (old_passward.equals("")) {
					Toast.makeText(getActivity(), "请输入旧密码", Toast.LENGTH_SHORT)
							.show();
				} else if (new_passward.equals("")) {
					Toast.makeText(getActivity(), "请输入新密码", Toast.LENGTH_SHORT)
							.show();
				} else {
					if (new_passward.equals(old_passward)) {
						DiyToast.showToast(getActivity(), "新旧密码不能一致");
					} else {
						SQLiteControl.updata_Pass(getActivity(), getusername,
								old_passward, new_passward);
					}
				}
			}
		});
		return view;
	}
}
