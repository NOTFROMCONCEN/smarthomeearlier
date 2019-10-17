package com.example.shengsaic9192017;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @文件名：LoginActivity.java
 * @描述：登录、注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-19
 */
public class LoginActivity extends Activity implements OnClickListener {
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private String user, pass, port, ip;// String
	private TextView tv_login_tips, tv_login_time;// 提示、时间
	SharedPreferences sharedPreferences;// sharedPreferences存储
	private Button btn_con;// 确定
	private Button btn_cls;// 取消
	private EditText et_euser;// 用户名
	private EditText et_epass;// 密码
	private EditText et_repass;// 确认密码
	private String euser, epass, repass;// String
	int number = 0;
	// IP地址
	static String ip_number;
	// 布局
	private LinearLayout line_login;
	private LinearLayout line_reg;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		// 按钮点击事件
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_cls.setOnClickListener(this);
		btn_con.setOnClickListener(this);
		// 设置参数
		line_reg.setVisibility(View.GONE);
		line_login.setVisibility(View.VISIBLE);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}
		et_pass.setTransformationMethod(new TextChanger());
		// 激活进程
		handler.post(timeRunnable);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			line_reg.setVisibility(View.GONE);
			line_login.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_con:
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "用户名为空");
			} else if (epass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "密码为空");
			} else if (repass.isEmpty()) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "确认密码为空");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(getApplicationContext(), "注册成功");
						line_reg.setVisibility(View.GONE);
						line_login.setVisibility(View.VISIBLE);
					} else {
						DiyToast.showToast(getApplicationContext(), "两次输入密码不一致");
					}
				}
			}
			break;
		case R.id.btn_reg:
			line_reg.setVisibility(View.VISIBLE);
			line_login.setVisibility(View.GONE);
			break;
		case R.id.btn_login:
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口号
			ip = et_ip.getText().toString();// Ip地址
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "用户名不能为空");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "密码不能为空");
			} else if (port.isEmpty()) {// 端口为空
				DiyToast.showToast(getApplicationContext(), "端口不能为空");
			} else if (ip.isEmpty()) {// IP为空
				DiyToast.showToast(getApplicationContext(), "IP地址不能为空");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					sharedPreferences.edit().putString("ip", ip)
							.putString("pass", pass).putString("port", port)
							.putString("user", user).commit();
					ip_number = ip;
					Intent intent = new Intent(LoginActivity.this,
							BarActivity.class);
					startActivity(intent);
					finish();
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
				}
			}
			break;
		default:
			break;
		}
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：下午3:09:10
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what % 2 == 0) {
				tv_login_tips.setVisibility(View.INVISIBLE);
			} else {
				tv_login_tips.setVisibility(View.VISIBLE);
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy年MM月dd日 HH:mm:ss");
			simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			tv_login_time
					.setText(simpleDateFormat.format(new java.util.Date()));
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			number++;
			Message msg = handler.obtainMessage();
			msg.what = number;
			handler.sendMessage(msg);
		}
	};
}
