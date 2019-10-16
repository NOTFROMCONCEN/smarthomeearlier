package com.example.drawdemo53;

import lib.SocketThread;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	// 当前是登录界面（打开软件第一个显示的），主要功能为——
	// ——登录、注册、修改密码、忘记密码、退出系统、自动登录、记住密码；
	private Button btn_login, btn_reg, btn_update_pass, btn_reback_pass,
			btn_exit;
	private CheckBox cb_autologin, cb_rember;
	private EditText et_user, et_pass, et_port, et_ip;
	private String user, pass, port, ip, autouser, autoport, autoip, autopass;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	static SharedPreferences rember;
	static SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 绑定的控件
		btn_exit = (Button) findViewById(R.id.btn_exit);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reback_pass = (Button) findViewById(R.id.btn_reback_pass);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_update_pass = (Button) findViewById(R.id.btn_update_pass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);

		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();

		// 设置按钮封装
		btn_exit.setOnClickListener(this);
		btn_reback_pass.setOnClickListener(this);
		btn_reg.setOnClickListener(this);
		btn_update_pass.setOnClickListener(this);

		// 自动登录记住密码功能实现代码
		/** 设置SharedPreferences、本地文件名称以及读写权限模式 **/
		rember = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);

		if (rember != null) {
			/** 当rember不为null（即创建成功，可以在本地找到） **/
			final String autouser, autopass, autoport, autoip;
			// 给设置的String数值赋值
			autoip = rember.getString("ip", null);
			autopass = rember.getString("pass", null);
			autoport = rember.getString("port", null);
			autouser = rember.getString("user", null);
			// 如果rember内得到的布尔数值“rember”的标签为ture
			if (rember.getBoolean("rember", false) == true) {
				// 设置记住密码CheckBox状态为选中
				cb_rember.setChecked(true);
				// 将上面得到的数值设置到文本框内
				et_ip.setText(autoip);
				et_pass.setText(autopass);
				et_port.setText(autoport);
				et_user.setText(autouser);
			} else {
				// 如果rember内得到的布尔数值“rember”的标签为false
				et_ip.setText("");
				et_pass.setText("");
				et_port.setText("");
				et_user.setText("");
			}
			// 如果rember内得到的布尔数值“autologin”的标签为ture
			if (rember.getBoolean("autologin", false) == true) {
				// 设置自动登录CheckBox状态为选中
				cb_autologin.setChecked(true);
				// 新建线程
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							// 给线程设置一个3秒的延迟
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						// 3秒后检查自动登录复选框的状态（3秒内取消选中可以停止自动登录）
						if (cb_autologin.isChecked()) {
							// 如果被选中，3秒后跳转到下一界面
							SocketThread.SocketIp = autoip;
							SocketThread.Port = Integer.valueOf(autoport);
							Intent intent = new Intent(MainActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
						} else {
							// 如果3秒内取消选中，将自动登录布尔数值的标签改为false，其余不变
							// 可根据要求选择性更改记住密码的布尔数值标签；
							rember.edit().putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user", autouser)
									.putString("port", autoport)
									.putString("ip", autoip)
									.putString("pass", autopass).commit();
						}
					}
					// 启动线程
				}).start();
			}
		} else {
			/** 当rember为null（即创建失败） **/
			// 设置文本框为空
			et_ip.setText("");
			et_pass.setText("");
			et_port.setText("");
			et_user.setText("");
		}
		// 登录功能的实现
		// 当点击登录按钮的时候——
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//给String数值赋值
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
				//新建数据库指针
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });
				if (user.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else if (pass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入密码",
							Toast.LENGTH_SHORT).show();
				} else if (ip.equals("")) {
					Toast.makeText(MainActivity.this, "请输入IP地址",
							Toast.LENGTH_SHORT).show();
				} else if (port.equals("")) {
					Toast.makeText(MainActivity.this, "请输入服务器端口号",
							Toast.LENGTH_SHORT).show();
				} else if (!cursor.moveToNext()) {
					Toast.makeText(MainActivity.this, "用户名或密码输入错误",
							Toast.LENGTH_SHORT).show();
				} else {
					if (cb_autologin.isChecked() && cb_rember.isChecked()) {
						rember.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else if (cb_autologin.isChecked()) {
						rember.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else if (cb_rember.isChecked()) {
						rember.edit().putBoolean("autologin", false)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						rember.edit().putBoolean("autologin", false)
								.putBoolean("rember", false)
								.putString("user", user)
								.putString("port", port).putString("ip", ip)
								.putString("pass", pass).commit();
						SocketThread.Port = Integer.valueOf(port);
						SocketThread.SocketIp = ip;
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_exit:
			System.exit(0);
			break;
		case R.id.btn_reback_pass:
			reback_pass();
			break;
		case R.id.btn_reg:
			regdialog();
			break;
		case R.id.btn_update_pass:
			update_pass();
			break;
		default:
			break;
		}
	}

	private void update_pass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("修改密码");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_update_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_updata_user = (EditText) view
						.findViewById(R.id.et_update_user);
				final EditText et_updata_newpass = (EditText) view
						.findViewById(R.id.et_updata_newpass);
				final EditText et_updata_oldpass = (EditText) view
						.findViewById(R.id.et_updata_oldpass);
				final String updata_user, updata_oldpass, updata_newpass, getSQLitepassward;
				updata_newpass = et_updata_newpass.getText().toString();
				updata_oldpass = et_updata_oldpass.getText().toString();
				updata_user = et_updata_user.getText().toString();
				if (updata_user.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else if (updata_oldpass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入旧密码",
							Toast.LENGTH_SHORT).show();
				} else if (updata_newpass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入新密码",
							Toast.LENGTH_SHORT).show();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { updata_user });
					cursor.moveToFirst();
					getSQLitepassward = cursor.getString(cursor
							.getColumnIndex("passward"));
					if (updata_oldpass.equals(getSQLitepassward)) {
						db.execSQL(
								"update user set passward = ? where username = ?",
								new String[] { updata_newpass, updata_user });
						Toast.makeText(MainActivity.this, "修改成功",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(MainActivity.this, "旧密码输入错误",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}

	private void reback_pass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("找回密码");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reback_pass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_reback_user = (EditText) view
						.findViewById(R.id.et_reback_user);
				final EditText et_twopass = (EditText) view
						.findViewById(R.id.et_reback_twopass);
				final String reback_user, twopass, getSQLiteUser;
				reback_user = et_reback_user.getText().toString();
				twopass = et_twopass.getText().toString();
				if (reback_user.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else if (twopass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入二级密码",
							Toast.LENGTH_SHORT).show();
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and twopass = ?",
									new String[] { reback_user, twopass });
					if (cursor.moveToNext()) {
						getSQLiteUser = cursor.getString(cursor
								.getColumnIndex("passward"));
						Toast.makeText(
								MainActivity.this,
								"找回成功，用户名：" + reback_user + "密码："
										+ getSQLiteUser, Toast.LENGTH_SHORT)
								.show();
					} else {
						Toast.makeText(MainActivity.this, "用户名或二级密码输入错误",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}

	private void regdialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("注册");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_reg, null,
				false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_euser = (EditText) view
						.findViewById(R.id.et_euser);
				final EditText et_epass = (EditText) view
						.findViewById(R.id.et_epass);
				final EditText et_repass = (EditText) view
						.findViewById(R.id.et_repass);
				final EditText et_twopass = (EditText) view
						.findViewById(R.id.et_twopass);
				final String euser, epass, repass, twopass;
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				twopass = et_twopass.getText().toString();
				Cursor cursor = db.rawQuery(
						"select * from user where username = ?",
						new String[] { euser });
				if (euser.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else if (epass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入密码",
							Toast.LENGTH_SHORT).show();
				} else if (repass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入确认密码",
							Toast.LENGTH_SHORT).show();
				} else if (twopass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入二级密码",
							Toast.LENGTH_SHORT).show();
				} else {
					if (epass.equals(repass)) {
						if (cursor.moveToNext()) {
							Toast.makeText(MainActivity.this, "用户名已存在",
									Toast.LENGTH_SHORT).show();
						} else {
							db.execSQL(
									"insert into user (username,passward,twopass)values(?,?,?)",
									new String[] { euser, epass, twopass });
							Toast.makeText(MainActivity.this, "注册完成",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "两次密码输入不一致",
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}
}
