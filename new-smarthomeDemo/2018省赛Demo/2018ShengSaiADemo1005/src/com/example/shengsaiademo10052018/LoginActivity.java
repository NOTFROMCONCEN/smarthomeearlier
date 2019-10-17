package com.example.shengsaiademo10052018;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

import com.example.shengsaiademo10052018.fragment.BarActivity;
import com.example.shengsaiademo10052018.mysql.MyDataBaseHelper;
import com.example.shengsaiademo10052018.toast.DiyToast;

/*
 * @文件名：LoginActivity.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-5
 */
public class LoginActivity extends Activity {
	private Button btn_login, btn_reg;// 登录注册
	private EditText et_ip, et_user, et_pass;// IP用户名密码
	private String ip, user, pass;// IP用户名密码
	private CheckBox cb_rember;// 记住密码
	private CheckBox cb_autologin;// 自动登录

	public static String IP_NUMBER;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// sharedPreferences存储
	public static SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// initView();// 绑定控件
		autoFindView();
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// 记住密码、自动登录
		if (sharedPreferences != null) {
			if (sharedPreferences.getBoolean("autologin", false) == true) {
				cb_autologin.setChecked(true);
				new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						if (cb_autologin.isChecked()) {
							IP_NUMBER = et_ip.getText().toString();
							startActivity(new Intent(LoginActivity.this,
									BarActivity.class));
							finish();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", "")
									.putString("pass", "").putString("ip", "")
									.commit();
						}
					}
				}).start();
			}
			if (sharedPreferences.getBoolean("rember", false) == true) {
				cb_rember.setChecked(true);
				et_ip.setText(sharedPreferences.getString("ip", null));
				et_pass.setText(sharedPreferences.getString("pass", null));
				et_user.setText(sharedPreferences.getString("user", null));
			}
		}
		// 注册按钮点击事件
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(LoginActivity.this, RegActivity.class));
			}
		});
		// 联动效果
		cb_autologin.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					cb_rember.setChecked(true);
				}
			}
		});
		cb_rember.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {

				} else {
					cb_autologin.setChecked(false);
				}
			}
		});

		// 登录按钮点击事件
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				user = et_user.getText().toString();// 用户名
				pass = et_pass.getText().toString();// 密码
				ip = et_ip.getText().toString();// IP
				if (ip.isEmpty()) {// IP为空
					DiyToast.showToast(getApplicationContext(), "IP地址不能为空");
				} else if (user.isEmpty()) {// 用户名为空
					DiyToast.showToast(getApplicationContext(), "用户名不能为空");
				} else if (pass.isEmpty()) {
					DiyToast.showToast(getApplicationContext(), "密码不能为空");
				} else {
					Cursor cursor = db
							.rawQuery(
									"select * from user where username = ? and passward  =?",
									new String[] { user, pass });// 新建数据库游标
					if (cursor.moveToNext()) {
						// 跳转
						IP_NUMBER = ip;
						startActivity(new Intent(LoginActivity.this,
								BarActivity.class));
						// 记住密码自动登录
						if (cb_autologin.isChecked() && cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", true)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else if (cb_rember.isChecked()) {
							sharedPreferences.edit().putBoolean("rember", true)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						} else {
							sharedPreferences.edit()
									.putBoolean("rember", false)
									.putBoolean("autologin", false)
									.putString("user", user)
									.putString("pass", pass)
									.putString("ip", ip).commit();
						}
					} else {
						DiyToast.showToast(getApplicationContext(),
								"用户名或密码输入错误");
					}
				}
			}
		});
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定
	 * 
	 * @时 间：上午8:50:44
	 */
	// private void initView() {
	// // TODO Auto-generated method stub
	// btn_login = (Button) findViewById(R.id.btn_login);
	// btn_reg = (Button) findViewById(R.id.btn_reg);
	// et_ip = (EditText) findViewById(R.id.et_ip);
	// et_user = (EditText) findViewById(R.id.et_user);
	// et_pass = (EditText) findViewById(R.id.et_pass);
	// cb_autologin = (CheckBox) findViewById(R.id.cb_autologin);
	// cb_rember = (CheckBox) findViewById(R.id.cb_rember);
	// }

	// 自动findViewById(必须保证布局文件中的id与变量名一致)
	private void autoFindView() {
		Class mClass = this.getClass();
		while (true) {
			Field[] fields = mClass.getDeclaredFields();
			for (Field field : fields) {
				if (View.class.isAssignableFrom(field.getType())) {
					String fieldName = field.getName();
					View view = findViewById(getResources().getIdentifier(
							fieldName, "id", getPackageName()));
					if (view != null) {
						field.setAccessible(true);
						try {
							field.set(this, view);
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
				}
			}
			mClass = mClass.getSuperclass();
			// tip：可修改Activity.class.getName()以重新指定搜寻父类上限
			if (mClass.getName().equals(Activity.class.getName()))
				break;
		}
	}
}
