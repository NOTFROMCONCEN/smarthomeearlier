package com.example.drawdemo50;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lib.SocketThread;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btn_login, btn_sysout, btn_reg, btn_updatapass,
			btn_rebackpass;
	private EditText et_user, et_pass, et_port, et_ip;
	private String user, pass, port, ip, getnum, gettime, time;
	private CheckBox cb_rember, cb_autologin;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	Double num;
	private TextView tv_login_state;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_rebackpass = (Button) findViewById(R.id.btn_rebackpass);
		btn_sysout = (Button) findViewById(R.id.btn_sysout);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		btn_updatapass = (Button) findViewById(R.id.btn_updatapass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_port = (EditText) findViewById(R.id.et_port);
		et_user = (EditText) findViewById(R.id.et_user);
		tv_login_state = (TextView) findViewById(R.id.tv_login_state);
		cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
		cb_rember = (CheckBox) findViewById(R.id.cb_rember);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery("select * from login", null);
		DecimalFormat df = new DecimalFormat("##0");
		if (cursor.getCount() != 0) {
			// 如果数据库有数据，指针上移
			cursor.moveToLast();
			// 得到数据库内部数据并转换为String数值
			getnum = cursor.getString(cursor.getColumnIndex("loginnum"));
			gettime = cursor.getString(cursor.getColumnIndex("logintime"));
			// 将登录次数String数值转换为整数
			num = Double.valueOf(getnum);
			// 设置文本
			tv_login_state.setText("之前已有" + df.format(num) + "次登录，上次登录时间为"
					+ gettime);
		} else {
			// 如果数据为空，防止报错将文本设置为固定值
			tv_login_state.setText("之前已有null次登录，上次登录时间为HH:mm");
		}
		final SharedPreferences rember = getSharedPreferences("rember",
				MODE_WORLD_WRITEABLE);
		if (rember != null) {
			if (rember.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(rember.getString("ip", null));
				et_pass.setText(rember.getString("pass", null));
				et_port.setText(rember.getString("port", null));
				et_user.setText(rember.getString("user", null));
			} else {
				et_ip.setText("");
				et_pass.setText("");
				et_port.setText("");
				et_user.setText("");
			}
			if (rember.getBoolean("autologin", false == true)) {
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(3000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							SocketThread.SocketIp = et_ip.getText().toString();
							SocketThread.Port = Integer.valueOf(et_port
									.getText().toString());
							Intent intent = new Intent(MainActivity.this,
									BarActivity.class);
							startActivity(intent);
							finish();
							num++;
							String setnumString = Double.toString(num);
							db.execSQL(
									"insert into login (loginnum,logintime)values(?,?)",
									new String[] { setnumString, time });
						} else {
							rember.edit()
									.putBoolean("autologin", false)
									.putBoolean("rember", true)
									.putString("user",
											et_user.getText().toString())
									.putString("pass",
											et_pass.getText().toString())
									.putString("port",
											et_port.getText().toString())
									.putString("ip", et_ip.getText().toString())
									.commit();
						}
					}
				}).start();
			}
		} else {
			et_ip.setText("");
			et_pass.setText("");
			et_port.setText("");
			et_user.setText("");
		}
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				regdialog();
			}
		});
		btn_updatapass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				updatapassdialog();
			}
		});
		btn_sysout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		btn_rebackpass.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				rebackpass();
			}
		});
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();
				pass = et_pass.getText().toString();
				port = et_port.getText().toString();
				ip = et_ip.getText().toString();
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
					Toast.makeText(MainActivity.this, "请输入服务器端口",
							Toast.LENGTH_SHORT).show();
				} else if (!cursor.moveToNext()) {
					Toast.makeText(MainActivity.this, "用户名或密码输入错误",
							Toast.LENGTH_SHORT).show();
				} else {
					if (cb_autologin.isChecked() && cb_rember.isChecked()) {
						rember.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass)
								.putString("port", port).putString("ip", ip)
								.commit();
						num++;
						String setnumString = Double.toString(num);
						db.execSQL(
								"insert into login (loginnum,logintime)values(?,?)",
								new String[] { setnumString, time });
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf(port);
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else if (cb_autologin.isChecked()) {
						rember.edit().putBoolean("autologin", true)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass)
								.putString("port", port).putString("ip", ip)
								.commit();
						num++;
						String setnumString = Double.toString(num);
						db.execSQL(
								"insert into login (loginnum,logintime)values(?,?)",
								new String[] { setnumString, time });
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf(port);
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else if (cb_rember.isChecked()) {
						rember.edit().putBoolean("autologin", false)
								.putBoolean("rember", true)
								.putString("user", user)
								.putString("pass", pass)
								.putString("port", port).putString("ip", ip)
								.commit();
						num++;
						String setnumString = Double.toString(num);
						db.execSQL(
								"insert into login (loginnum,logintime)values(?,?)",
								new String[] { setnumString, time });
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf(port);
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					} else {
						rember.edit().putBoolean("autologin", false)
								.putBoolean("rember", false)
								.putString("user", user)
								.putString("pass", pass)
								.putString("port", port).putString("ip", ip)
								.commit();
						num++;
						String setnumString = Double.toString(num);
						db.execSQL(
								"insert into login (loginnum,logintime)values(?,?)",
								new String[] { setnumString, time });
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf(port);
						Intent intent = new Intent(MainActivity.this,
								BarActivity.class);
						startActivity(intent);
						finish();
					}
				}
			}
		});
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void rebackpass() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("找回密码");
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.activity_rebackpass,
				null, false);
		builder.setView(view);
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText et_user = (EditText) view
						.findViewById(R.id.et_rebackuser);
				final String username, getSQLitepassward;
				username = et_user.getText().toString();
				if (username.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { username });
					cursor.moveToFirst();
					if (!cursor.moveToFirst()) {
						Toast.makeText(MainActivity.this, "数值有误！",
								Toast.LENGTH_SHORT).show();
					} else {
						getSQLitepassward = cursor.getString(cursor
								.getColumnIndex("passward"));
						Toast.makeText(MainActivity.this,
								"找回成功，密码：" + getSQLitepassward,
								Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		builder.show();
	}

	private void updatapassdialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(
				R.layout.activity_updatapassward, null);
		builder.setView(view);
		builder.setTitle("修改密码");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				final EditText etnewpassword = (EditText) view
						.findViewById(R.id.et_updatanewpass);
				final EditText etoldpassword = (EditText) view
						.findViewById(R.id.et_updataoldpass);
				final EditText etmodifyusername = (EditText) view
						.findViewById(R.id.et_updatauser);
				final String newpassward, oldpassward, username, getSQLitepassward;
				newpassward = etnewpassword.getText().toString();
				oldpassward = etoldpassword.getText().toString();
				username = etmodifyusername.getText().toString();
				if (username.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else if (oldpassward.equals("")) {
					Toast.makeText(MainActivity.this, "请输入旧密码",
							Toast.LENGTH_SHORT).show();
				} else if (newpassward.equals("")) {
					Toast.makeText(MainActivity.this, "请输入新密码",
							Toast.LENGTH_SHORT).show();
				} else {
					Cursor cursor = db.rawQuery(
							"select * from user where username = ?",
							new String[] { username });
					cursor.moveToFirst();
					getSQLitepassward = cursor.getString(cursor
							.getColumnIndex("passward"));
					if (oldpassward.equals(getSQLitepassward)) {
						if (newpassward.equals(getSQLitepassward)) {
							Toast.makeText(MainActivity.this, "新密码和旧密码不能一样",
									Toast.LENGTH_SHORT).show();
						} else {
							db.execSQL(
									"update user set passward = ? where username = ?",
									new String[] { newpassward, username });
							Toast.makeText(MainActivity.this, "修改成功",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(MainActivity.this, "旧密码和数据库内不一致！",
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
		final View view = layoutInflater.inflate(R.layout.activity_reg, null);
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
				final String euser, epass, repass;
				euser = et_euser.getText().toString();
				epass = et_epass.getText().toString();
				repass = et_repass.getText().toString();
				if (euser.equals("")) {
					Toast.makeText(MainActivity.this, "请输入用户名",
							Toast.LENGTH_SHORT).show();
				} else if (epass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入密码",
							Toast.LENGTH_SHORT).show();
				} else if (repass.equals("")) {
					Toast.makeText(MainActivity.this, "请输入确认密码",
							Toast.LENGTH_SHORT).show();
				} else {
					if (epass.equals(repass)) {
						Cursor cursor = db.rawQuery(
								"select * from user where username = ?",
								new String[] { euser });
						if (cursor.moveToNext()) {
							Toast.makeText(MainActivity.this, "用户名已存在！",
									Toast.LENGTH_SHORT).show();
						} else {
							db.execSQL(
									"insert into user (username,passward)values(?,?)",
									new String[] { euser, epass });
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

	// 获取当前时间的Handler进程
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat formater = new SimpleDateFormat("HH:mm");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			time = formater.format(new java.util.Date());
			handler.postDelayed(timeRunnable, 100);
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
