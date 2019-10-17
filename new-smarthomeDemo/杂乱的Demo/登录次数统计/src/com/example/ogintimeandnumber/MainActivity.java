package com.example.ogintimeandnumber;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @文件名：MainActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-29
 */
public class MainActivity extends Activity implements OnClickListener {
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_con;// 确定按钮
	private Button btn_cls;// 关闭按钮
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_euser;// 注册用户名
	private EditText et_epass;// 注册密码
	private TextView tv_time;// 时间
	private TextView tv_login_number;// 登录次数
	// String
	private String user, pass, euser, epass;
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	// int
	private int login_number = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("登录");
		tv_login_number = (TextView) findViewById(R.id.tv_number_text);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_time = (TextView) findViewById(R.id.tv_time);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 读取次数
		Cursor cursor = db.rawQuery("select * from user_login_number", null);
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			String get_number = cursor.getString(cursor
					.getColumnIndex("login_number"));
			String get_time = cursor.getString(cursor
					.getColumnIndex("login_time"));
			tv_login_number.setText("之前有过" + get_number + "次登录，上次登录时间为："
					+ get_time);
		}
		// 设置按钮点击事件
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		// 设置界面显示
		line_login.setVisibility(View.VISIBLE);
		line_reg.setVisibility(View.GONE);
		// 激活进程
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// 获取当前时间
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_time.setText(sdf.format(new java.util.Date()));
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午7:48:21
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			// 注册关闭
			setTitle("登录");
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.GONE);
			et_epass.setText("");
			et_euser.setText("");
			break;
		case R.id.btn_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			if (euser.equals("")) {// 如果用户名为空
				DiyToast.showToast(MainActivity.this, "请输入用户名");
			} else if (epass.equals("")) {// 如果密码为空
				DiyToast.showToast(MainActivity.this, "请输入密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// 新建数据库
				if (cursor.moveToNext()) {
					DiyToast.showToast(MainActivity.this, "用户名已存在");
				} else {
					db.execSQL(
							"insert into user (username,passward)values(?,?)",
							new String[] { euser, epass });// 插入数据库
					DiyToast.showToast(MainActivity.this, "注册成功");
					et_epass.setText("");
					et_euser.setText("");
					line_login.setVisibility(View.VISIBLE);
					line_reg.setVisibility(View.GONE);
				}
			}
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			Cursor cursor = db.rawQuery(
					"select * from user where username = ? and passward  =? ",
					new String[] { user, pass });// 新建数据库指针
			if (user.equals("")) {
				DiyToast.showToast(MainActivity.this, "请输入用户名");
			} else if (pass.equals("")) {
				DiyToast.showToast(MainActivity.this, "请输入密码");
			} else if (!cursor.moveToNext()) {
				DiyToast.showToast(MainActivity.this, "用户名或密码输入错误");
			} else {

				Cursor cursor1 = db.rawQuery("select * from user_login_number",
						null);
				if (cursor1.getCount() != 0) {
					cursor1.moveToLast();
					String get_number = cursor1.getString(cursor1
							.getColumnIndex("login_number"));
					login_number = Integer.valueOf(get_number);
					login_number += 1;
				}
				db.execSQL(
						"insert into user_login_number (login_number,login_time)values(?,?)",
						new String[] { String.valueOf(login_number),
								tv_time.getText().toString() });
				DiyToast.showToast(getApplicationContext(), "登陆成功");
			}
			break;
		case R.id.btn_reg:
			setTitle("注册");
			et_user.setText("");
			et_pass.setText("");
			line_login.setVisibility(View.GONE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		default:
			break;
		}
	}
}
