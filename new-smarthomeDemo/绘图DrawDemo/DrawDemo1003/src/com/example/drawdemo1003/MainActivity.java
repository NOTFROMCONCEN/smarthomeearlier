package com.example.drawdemo1003;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.drawdemo1003.helpclass.DiyDialog;
import com.example.drawdemo1003.helpclass.DiyToast;
import com.example.drawdemo1003.mysql.MyDataBaseHelper;

/*
 * @文件名：MainActivity.java
 * @描述：登录
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-3
 */
public class MainActivity extends Activity implements OnClickListener {
	// 数据库
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// 定义
	// IP地址
	public static String IP_NUMBER;
	// 界面
	private LinearLayout line_login;// 登录界面
	private LinearLayout line_reg;// 注册界面

	// 登录功能
	private Button btn_login;// 登录按钮
	private Button btn_reg;// 注册按钮
	private EditText et_user, et_pass, et_ip;// 用户名、密码、IP地址文本框
	private String user, pass, ip;

	// 注册功能
	private Button btn_con;// 注册确定
	private Button btn_cls;// 注册关闭
	private EditText et_euser, et_epass, et_repass;// 用户名、密码、确认密码文本框
	private String euser, epass, repass;

	// 记住密码功能
	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// initView();// 绑定控件
		autoFindView();
		rember_pass();// 记住密码
		DiyDialog.showDialog(MainActivity.this, "欢迎", "欢迎使用智能家居控制系统", true,
				false);
		sharedPreferences = getSharedPreferences("rember", MODE_WORLD_WRITEABLE);
		// 注册按钮点击
		btn_reg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_login.setVisibility(View.INVISIBLE);// 隐藏
				line_reg.setVisibility(View.VISIBLE);// 显示
				setnull();// 清空文本框
			}
		});
		// 注册关闭按钮点击
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				line_reg.setVisibility(View.INVISIBLE);// 隐藏
				line_login.setVisibility(View.VISIBLE);// 显示
				setnull();// 清空文本框
			}
		});
		btn_con.setOnClickListener(this);// 注册确定按钮
		btn_login.setOnClickListener(this);// 登录按钮
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：下午7:04:23
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_login = (Button) findViewById(R.id.btn_login);
		btn_reg = (Button) findViewById(R.id.btn_reg);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_pass = (EditText) findViewById(R.id.et_pass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		et_ip = (EditText) findViewById(R.id.et_ip);
		et_user = (EditText) findViewById(R.id.et_user);
		line_login = (LinearLayout) findViewById(R.id.line_login);
		line_reg = (LinearLayout) findViewById(R.id.line_reg);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_login:
			// 登录
			user = et_user.getText().toString();// 用户名
			pass = et_pass.getText().toString();// 密码
			ip = et_ip.getText().toString();// IP地址
			if (user.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (pass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (ip.isEmpty()) {// IP地址为空
				DiyToast.showToast(getApplicationContext(), "请输入IP地址");
			} else {
				Cursor cursor = db
						.rawQuery(
								"select * from user where username = ? and passward = ?",
								new String[] { user, pass });// 新建游标
				if (cursor.moveToNext()) {// 数据库移动
					DiyToast.showToast(getApplicationContext(), "登陆成功");
					IP_NUMBER = ip;
					// 跳转
					Intent intent = new Intent(getApplicationContext(),
							LoadingActivity.class);
					startActivity(intent);
					finish();
					// 记住密码
					sharedPreferences.edit().putString("user", user)
							.putString("pass", pass).putString("ip", ip)
							.commit();
				} else {
					DiyDialog.showDialog(MainActivity.this, "登录失败",
							"用户名或密码输入错误", true, false);
				}
			}
			break;
		case R.id.btn_con:
			// 注册
			euser = et_euser.getText().toString();// 用户名
			epass = et_epass.getText().toString();// 密码
			repass = et_repass.getText().toString();// 确认密码
			if (euser.isEmpty()) {// 用户名为空
				DiyToast.showToast(getApplicationContext(), "请输入用户名");
			} else if (epass.isEmpty()) {// 密码为空
				DiyToast.showToast(getApplicationContext(), "请输入密码");
			} else if (repass.isEmpty()) {// 确认密码为空
				DiyToast.showToast(getApplicationContext(), "请输入确认密码");
			} else {
				Cursor cursor = db.rawQuery(
						"select * from user where username = ? ",
						new String[] { euser });// 新建数据库游标
				if (cursor.moveToNext()) {// 游标移动
					DiyDialog.showDialog(MainActivity.this, "注册失败", "用户名已存在",
							true, false);
				} else {
					if (epass.equals(repass)) {// 两次密码一致
						db.execSQL(
								"insert into user (username,passward)values(?,?)",
								new String[] { euser, epass });
						DiyToast.showToast(getApplicationContext(), "注册成功");
						setnull();// 清空文本框
						line_reg.setVisibility(View.INVISIBLE);// 隐藏
						line_login.setVisibility(View.VISIBLE);// 显示
					} else {
						DiyDialog.showDialog(MainActivity.this, "注册失败",
								"两次输入密码不一致", true, false);
					}
				}
			}
			break;
		default:
			break;
		}
	}

	/*
	 * @方法名：setnull
	 * 
	 * @功 能：设置文本框内容为空
	 * 
	 * @时 间：下午7:18:56
	 */
	private void setnull() {
		et_epass.setText("");
		et_euser.setText("");
		et_ip.setText("");
		et_pass.setText("");
		et_repass.setText("");
		et_user.setText("");
	}

	/*
	 * @方法名：rember_pass
	 * 
	 * @功 能：记住密码功能
	 * 
	 * @时 间：下午7:24:55
	 */
	private void rember_pass() {
		// TODO Auto-generated method stub
		if (sharedPreferences != null) {
			et_pass.setText(sharedPreferences.getString("pass", null));
			et_user.setText(sharedPreferences.getString("user", null));
			et_ip.setText(sharedPreferences.getString("ip", null));
		}
	}

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
