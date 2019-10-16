package com.example.shengsaid9202017;

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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/*
 * @文件名：LoginActivity.java
 * @描述：登录、注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-20
 */
public class LoginActivity extends Activity implements OnClickListener {
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	int recLen = 0;
	static String ip_number;

	// 定义
	private TextView tv_login_tips;// 提示
	private TextView tv_login_time;// 时间
	private EditText et_user;// 用户名
	private EditText et_pass;// 密码
	private EditText et_ip;// IP
	private EditText et_port;// 端口
	private EditText et_euser;// 注册用户名
	private EditText et_epass;// 注册密码
	private EditText et_repass;// 注册确认密码
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private Button btn_con;// 确定按钮
	private CheckBox cb_rember;// 记住密码
	private RelativeLayout line_login;// 登录
	private RelativeLayout line_reg;// 注册
	// sharedPreferences存储
	SharedPreferences sharedPreferences;
	// String
	private String user, pass, port, ip, euser, epass, repass;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "smarthome.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 绑定
		tv_login_time = (TextView) findViewById(R.id.tv_login_time);
		tv_login_tips = (TextView) findViewById(R.id.tv_login_tips);
		line_login = (RelativeLayout) findViewById(R.id.line_login);
		line_reg = (RelativeLayout) findViewById(R.id.line_reg);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		// 记住密码
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_user.setText(sharedPreferences.getString("user", null));
				et_port.setText(sharedPreferences.getString("port", null));
			}
		}
		// 激活进程
		handler.post(timeRunnable);
		// 按钮点击事件
		btn_con.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_con:
			// 确定
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "用户名不能为空");
			} else if (epass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "密码不能为空");
			} else if (repass.isEmpty()) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "确认密码不能为空");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from userpass where User = ?",
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {
						db.execSQL(
								"insert into userpass(User,Pass)values(?,?)",
								new String[] { euser, epass });// 插入
						DiyToast.showToast(getApplicationContext(), "注册成功");
						line_login.setVisibility(View.VISIBLE);
						line_reg.setVisibility(View.INVISIBLE);
					} else {
						DiyToast.showToast(getApplicationContext(), "两次密码输入不一致");
					}
				}
			}
			break;
		case R.id.btn_reg:
			// 注册
			line_login.setVisibility(View.INVISIBLE);
			line_reg.setVisibility(View.VISIBLE);
			break;
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP
			port = et_port.getText().toString();// 端口
			if (ip.isEmpty()) {// IP地址为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else if (port.isEmpty()) {// 端口为空
				DiyToast.showToast(getApplicationContext(), "请输入端口");
			} else if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from userpass where User = ? and Pass = ?",
						new String[] { user, pass });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					// 登陆成功
					Intent intent = new Intent(getApplicationContext(),
							BarActivity.class);
					startActivity(intent);
					finish();
					ip_number = ip;
					if (cb_rember.isChecked()) {// 记住密码选中
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port)
								.putBoolean("rember", true).commit();
					} else {
						sharedPreferences.edit().putString("user", user)
								.putString("pass", pass).putString("ip", ip)
								.putString("port", port)
								.putBoolean("rember", false).commit();
					}
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
	 * @功 能：更新时间、字体闪烁
	 * 
	 * @时 间：上午8:24:36
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
			recLen++;
			Message msg = handler.obtainMessage();
			msg.what = recLen;
			handler.sendMessage(msg);
		}
	};
}
