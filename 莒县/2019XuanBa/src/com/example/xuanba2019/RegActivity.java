package com.example.xuanba2019;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegActivity extends Activity {
	private EditText et_euser, et_epass, et_repass;
	private Button btn_conButton;
	private String euser, epass, repass;
	private SQLiteDatabase db;
	private MyDataBasehelper dbHelper;
	private ContentValues values;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBasehelper(this, "user_table", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_conButton = (Button) findViewById(R.id.btn_con);
		btn_conButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				if (euser.equals("")) {
					Toast.makeText(RegActivity.this, "用户名不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (epass.equals("")) {
					Toast.makeText(RegActivity.this, "密码不能为空",
							Toast.LENGTH_SHORT).show();
				} else if (repass.equals("")) {
					Toast.makeText(RegActivity.this, "确认密码不能为空",
							Toast.LENGTH_SHORT).show();
				} else {
					if (epass.equals(repass)) {

						Cursor cursor = db.rawQuery(
								"select * from user_table where username = ?",
								new String[] { euser });
						if (cursor.moveToNext()) {
							Toast.makeText(RegActivity.this, "用户名已存在！",
									Toast.LENGTH_SHORT).show();
						} else {
							values = new ContentValues();
							values.put("password", epass);
							values.put("username", euser);
							db.insert("user_table", null, values);

							AlertDialog.Builder builder = new AlertDialog.Builder(
									RegActivity.this);
							builder.setMessage("注册成功");
							builder.setTitle("提示");
							builder.setPositiveButton("确定",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											Intent intent = new Intent(
													RegActivity.this,
													MainActivity.class);
											startActivity(intent);
											finish();
										}
									});
							builder.show();
						}
					} else {
						Toast.makeText(RegActivity.this, "两次密码输入不一致",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

	}
}
