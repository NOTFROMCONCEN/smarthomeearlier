package com.example.texteditinfocheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
 * @文件名：MainActivity.java
 * @描述：文本框检测
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-5
 */
public class MainActivity extends Activity {
	private EditText et_number;
	private Pattern pattern;
	private Matcher matcher;
	private Button btn_check;
	private boolean isTrue = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_number = (EditText) findViewById(R.id.et_number);
		btn_check = (Button) findViewById(R.id.btn_check);
		btn_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				oncheck();
				// 自定义提示框提示
				// 判断输入的是什么
				// 正则表达式
				// 检测输入的字符是否在a-z A-Z 0-9 下划线 中文字符 范围内
				// pattern = Pattern.compile("[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+");
				// 检测输入的字符是否在 A-Z a-z 0-9 范围内
				// pattern = Pattern.compile("[a-zA-Z0-9]");
				// 检测输入字符是否为纯数字或者纯字母
				// pattern =
				// Pattern.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
				if (et_number.getText().toString().equals("")) {
					DiyToast.showToast(getApplicationContext(), "请输入数值");
				} else {
					if (isTrue) {
						// 检测输入字符是否为纯数字或者纯字母
						pattern = Pattern
								.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
						matcher = pattern.matcher(et_number.getText()
								.toString());
						if (!matcher.matches()) {
							DiyToast.showToast(getApplicationContext(),
									"注册密码必须是字母和数字格式");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "请输入六位数密码");
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

	private void oncheck() {
		et_number.addTextChangedListener(new TextWatcher() {
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
