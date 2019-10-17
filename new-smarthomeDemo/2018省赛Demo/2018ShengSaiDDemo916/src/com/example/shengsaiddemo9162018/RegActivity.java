package com.example.shengsaiddemo9162018;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：LoginActivity.java
 * @描述：完成注册功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-16
 */
public class RegActivity extends Activity {
	private EditText et_euser;// 用户名
	private EditText et_epass;// 密码
	private EditText et_repass;// 确认密码
	private String euser, epass, repass;// String数值
	private Button btn_con;// 确定
	private Button btn_cls;// 关闭

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// boolean
	boolean isTrue = false;

	// 正则表达式
	private Matcher matcher;
	private Pattern pattern;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 绑定
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_con = (Button) findViewById(R.id.btn_con);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_repass = (EditText) findViewById(R.id.et_repass);
		// 关闭按钮事件
		btn_cls.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// 确定按钮事件
		btn_con.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
							"select * from user where username = ?",
							new String[] { euser });// 新建数据库游标
					if (cursor.moveToNext()) {// 用户已存在（游标移动）
						DiyToast.showToast(getApplicationContext(), "用户名已存在");
					} else {
						if (epass.equals(repass)) {// 新旧密码一致
							oncheck();
							if (isTrue) {
								// 检测输入字符是否为纯数字或者纯字母
								pattern = Pattern
										.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
								matcher = pattern.matcher(et_epass.getText()
										.toString());
								if (!matcher.matches()) {
									DiyToast.showToast(getApplicationContext(),
											"密码格式有误");
								} else {
									db.execSQL(
											"insert into user (username,passward)values(?,?)",
											new String[] { euser, epass });// 插入数据库
									DiyToast.showToast(getApplicationContext(),
											"注册完成");
									finish();
								}
							} else {
								DiyToast.showToast(getApplicationContext(),
										"密码不能小于六位");
							}
						} else {
							DiyToast.showToast(getApplicationContext(),
									"验证密码不一致");
						}
					}
				}
			}
		});
	}

	/*
	 * @方法名：oncheck
	 * 
	 * @功 能：检测输入字符
	 * 
	 * @时 间：下午3:18:45
	 */
	private void oncheck() {
		et_epass.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (start > 4) {// 大于4，输入至第六位时激活
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});
	}
}
