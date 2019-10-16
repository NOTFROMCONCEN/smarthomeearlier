package com.example.guosaigdemo911;

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

public class SettingActivity extends Fragment {
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
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
		dbHelper = new MyDataBaseHelper(getActivity(), "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		getusername = "bizideal";
		iv_exit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		btn_updatapass_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new_passward = et_new_pass.getText().toString();
				old_passward = et_old_pass.getText().toString();
				if (old_passward.equals("")) {
					Toast.makeText(getActivity(), "«Î ‰»Îæ…√‹¬Î", Toast.LENGTH_SHORT)
							.show();
				} else if (new_passward.equals("")) {
					Toast.makeText(getActivity(), "«Î ‰»Î–¬√‹¬Î", Toast.LENGTH_SHORT)
							.show();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { getusername });
					cursor.moveToLast();
					getoldpass = cursor.getString(cursor
							.getColumnIndex("passward"));
					if (old_passward.equals(getoldpass)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { new_passward, getusername });
						Toast.makeText(getActivity(), "–ﬁ∏ƒ≥…π¶",
								Toast.LENGTH_SHORT).show();
						LoginActivity.sharedPreferences.edit()
								.putBoolean("autologin", false)
								.putBoolean("rember", false)
								.putString("user", "").putString("pass", "")
								.putString("port", "").putString("ip", "")
								.commit();
					} else {
						Toast.makeText(getActivity(), "æ…√‹¬Î≤ª“ª÷¬£°",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		return view;
	}
}
