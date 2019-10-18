package com.example.shengsaibdemo10062018;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.shengsaibdemo10062018.index.SelectActivity;
import com.example.shengsaibdemo10062018.mysql.MyDataBaseHelper;
import com.example.shengsaibdemo10062018.textchangerHelper.TextChanger;
import com.example.shengsaibdemo10062018.toast.DiyToast;

/*
 * @文件名：MainActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-6
 */
public class MainActivity extends Activity {
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private String user, pass, port, ip;
	private Button btn_login, btn_reg;// 登录注册按钮
	public static String IP;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();// 绑定
		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// Ip地址
				port = et_port.getText().toString();// 端口号
				if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "请输入用户名");
				} else if (pass.isEmpty()) {// 密码为空
					DiyToast.showToast(getApplicationContext(), "请输入密码");
				} else if (port.isEmpty()) {// 端口为空
					DiyToast.showToast(getApplicationContext(), "请输入端口号");
				} else if (ip.isEmpty()) {// IP地址为空
					DiyToast.showToast(getApplicationContext(), "请输入IP地址");
				} else {
					System.out.println(pass);
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward = ?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {// 移动
						// 跳转
						IP = ip;
						startActivity(new Intent(MainActivity.this,
								SelectActivity.class));
						finish();
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),
						RegActivity.class));
			}
		});
		// 转换字符
		et_pass.setTransformationMethod(new TextChanger());
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:15:01
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
