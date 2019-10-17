package com.example.shengsaicdemo10072017;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import com.example.shengsaicdemo10072017.fragment.BarActivity;
import com.example.shengsaicdemo10072017.mysql.MyDataBaseHelper;
import com.example.shengsaicdemo10072017.timeget.TimeGet;
import com.example.shengsaicdemo10072017.toast.DiyToast;

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
 * @描述：登录、注册、记住密码
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-7
 */
public class LoginActivity extends Activity implements OnClickListener {
	private LinearLayout line_login, line_reg;
	private Button btn_login;// 登录
	private Button btn_reg;// 注册
	private Button btn_con;// 确认
	private Button btn_cls;// 关闭
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_port;// 端口号
	private EditText et_ip;// IP地址
	private EditText et_euser;// 注册用户名
	private EditText et_epass;// 注册密码
	private EditText et_repass;// 注册确认密码
	private TextView tv_time;// 时间
	String user, pass, port, ip;
	String euser, epass, repass;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences存储
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logion);
		initView();// 绑定控件
		// 设置程序启动时两个View的显隐
		line_login.setVisibility(View.VISIBLE);// 显示登陆
		line_reg.setVisibility(View.GONE);// 隐藏注册
		// 记住密码
		if (sharedPreferences != null) {
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_port.setText(sharedPreferences.getString("port", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
			et_user.setText(sharedPreferences.getString("user", null));
		}

	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：下午3:46:13
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(this);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_reg.setOnClickListener(this);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
		tv_time = (TextView) findViewById(R.id.tv_login_time);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 激活进程
		handler.post(timeRunnable);
		TimeGet.startThread();
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：下午3:48:15
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			port = et_port.getText().toString();// 端口
			ip = et_ip.getText().toString();// IP地址
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (port.isEmpty()) {// 端口号为空
				DiyToast.showToast(getApplicationContext(), "请输入端口号");
			} else if (ip.isEmpty()) {// IP为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建数据库游标
				if (cursor.moveToNext()) {// 移动
					startActivity(new Intent(getApplicationContext(),
							BarActivity.class));
					finish();
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.putString("port", port).commit();
				} else {
					DiyToast.showToast(getApplicationContext(), "用户名或密码输入错误");
				}
			}
			break;
		case R.id.btn_cls:
			// 注册关闭按钮
			line_login.setVisibility(View.VISIBLE);
			line_reg.setVisibility(View.INVISIBLE);
			setNull();// 清空
			break;
		case R.id.btn_reg:
			// 注册按钮
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			setNull();// 清空
			break;
		case R.id.btn_con:
			// 注册确定按钮
			euser = et_euser.getText().toString();// 注册用户名
			epass = et_epass.getText().toString();// 注册密码
			repass = et_repass.getText().toString();// 注册确认密码
			if (euser.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (epass.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (repass.isEmpty()) {
				DiyToast.showToast(getApplicationContext(), "请输入确认密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {// 移动
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {// 密码一致
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });// 插入数据库
						DiyToast.showToast(getApplicationContext(), "注册成功");
						setNull();// 清空
					} else {
						DiyToast.showToast(getApplicationContext(), "两次输入密码不一致");
					}
				}
			}
			break;
		default:
			break;
		}
	}

	/*
	 * @方法名：setNull
	 * 
	 * @功 能：按钮切换界面时将文本框清空
	 * 
	 * @时 间：下午3:55:20
	 */
	private void setNull() {
		// TODO Auto-generated method stub
		et_epass.setText("");
		et_euser.setText("");
		et_ip.setText("");
		et_pass.setText("");
		et_port.setText("");
		et_repass.setText("");
		et_user.setText("");
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：更新时间
	 * 
	 * @时 间：下午4:03:20
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			tv_time.setText(TimeGet.TIME);
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
}
