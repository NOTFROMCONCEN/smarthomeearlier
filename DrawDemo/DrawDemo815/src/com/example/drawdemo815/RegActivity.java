package com.example.drawdemo815;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * @文件名：RegActivity.java
 * @描述：对用户完成注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-15
 */
public class RegActivity extends Activity implements OnClickListener {
	/**
	 * 定义控件
	 */
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private EditText et_euser, et_epass, et_repass;// 文本框-用户名、密码、确认密码
	private String euser, epass, repass;// String数值
	private Button btn_con, btn_cls;// 按钮-确定、关闭

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		/**
		 * 绑定控件
		 */
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// 设置按钮的点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
	}

	/*
	 * @方法名：onClick(View v)
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @参 数：View v
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 关闭按钮
			finish();
			break;
		case R.id.btn_con:
			// 确定按钮
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			// 判断数值是否为空
			if (euser.isEmpty()) {// 如果用户名为空
				Toast.makeText(RegActivity.this, "请输入用户名", Toast.LENGTH_SHORT)
						.show();
			} else if (epass.isEmpty()) {// 如果密码为空
				Toast.makeText(RegActivity.this, "请输入密码", Toast.LENGTH_SHORT)
						.show();
			} else if (repass.isEmpty()) {// 如果确认密码为空
				Toast.makeText(RegActivity.this, "请输入确认密码", Toast.LENGTH_SHORT)
						.show();
			} else {
				if (epass.equals(repass)) {// 如果密码和确认密码一致
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库指针
					if (cursor.moveToNext()) {// 如果数据库内存在用户名
						Toast.makeText(RegActivity.this, "用户名已存在",
								Toast.LENGTH_SHORT).show();
					} else {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						finish();
						Toast.makeText(RegActivity.this, "注册完成",
								Toast.LENGTH_SHORT).show();
					}
				} else {// 如果密码和确认密码不一致
					Toast.makeText(RegActivity.this, "两次密码输入不一致",
							Toast.LENGTH_SHORT).show();
				}
			}
			break;

		default:
			break;
		}
	}

}
