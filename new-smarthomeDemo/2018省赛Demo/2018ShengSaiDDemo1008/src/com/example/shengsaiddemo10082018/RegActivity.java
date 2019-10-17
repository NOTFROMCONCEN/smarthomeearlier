package com.example.shengsaiddemo10082018;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.shengsaiddemo10082018.mysql.MyDataBaseHelper;
import com.example.shengsaiddemo10082018.toast.DiyToast;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/*
 * @文件名：LoginActivity.java
 * @描述：注册
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-10-8
 */
public class RegActivity extends Activity implements
		android.view.View.OnClickListener {
	private Button btn_con;// 确定
	private Button btn_cls;// 关闭
	private EditText et_euser, et_epass, et_repass;
	private String euser, epass, repass;

	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;

	// 密码字数
	boolean isTrue = false;

	// 正则表达式
	Pattern pattern;
	Matcher matcher;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reg);
		initView();// 绑定
	}

	/*
	 * @方法名：initView
	 * 
	 * @功 能：绑定控件
	 * 
	 * @时 间：上午8:13:24
	 */
	private void initView() {
		// TODO Auto-generated method stub
		btn_cls = (Button) findViewById(R.id.btn_cls);
		btn_cls.setOnClickListener(this);
		btn_con = (Button) findViewById(R.id.btn_con);
		btn_con.setOnClickListener(this);
		et_euser = (EditText) findViewById(R.id.et_euser);
		et_epass = (EditText) findViewById(R.id.et_epass);
		et_repass = (EditText) findViewById(R.id.et_repass);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午8:33:26
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_cls:
			finish();
			break;
		case R.id.btn_con:
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
						"select * from user where username = ?",
						new String[] { euser });// 新建游标
				if (cursor.moveToNext()) {
					DiyToast.showToast(getApplicationContext(), "用户名已存在");
				} else {
					if (epass.equals(repass)) {
						EditTextChanger();
						if (isTrue) {
							// 检测输入字符是否为纯数字或者纯字母
							pattern = Pattern
									.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
							matcher = pattern.matcher(et_epass.getText()
									.toString());
							if (!matcher.matches()) {
								DiyToast.showToast(getApplicationContext(),
										"注册密码必须是字母和数字格式");
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
									"密码不足6位");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "验证密码不一致");
					}
				}
			}
			break;

		default:
			break;
		}
	}

	/*
	 * @方法名：EditTextChanger
	 * 
	 * @功 能：监听文字改变是否满足要求
	 * 
	 * @时 间：上午8:33:06
	 */
	private void EditTextChanger() {
		// TODO Auto-generated method stub
		et_epass.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				if (start > 4) {
					isTrue = true;
				} else {
					isTrue = false;
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}
}
