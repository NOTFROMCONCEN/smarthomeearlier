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
 * @�ļ�����MainActivity.java
 * @�������ı�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-5
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
				// �Զ�����ʾ����ʾ
				// �ж��������ʲô
				// ������ʽ
				// ���������ַ��Ƿ���a-z A-Z 0-9 �»��� �����ַ� ��Χ��
				// pattern = Pattern.compile("[A-Za-z0-9_\\-\\u4e00-\\u9fa5]+");
				// ���������ַ��Ƿ��� A-Z a-z 0-9 ��Χ��
				// pattern = Pattern.compile("[a-zA-Z0-9]");
				// ��������ַ��Ƿ�Ϊ�����ֻ��ߴ���ĸ
				// pattern =
				// Pattern.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
				if (et_number.getText().toString().equals("")) {
					DiyToast.showToast(getApplicationContext(), "��������ֵ");
				} else {
					if (isTrue) {
						// ��������ַ��Ƿ�Ϊ�����ֻ��ߴ���ĸ
						pattern = Pattern
								.compile("^[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]");
						matcher = pattern.matcher(et_number.getText()
								.toString());
						if (!matcher.matches()) {
							DiyToast.showToast(getApplicationContext(),
									"ע�������������ĸ�����ָ�ʽ");
						}
					} else {
						DiyToast.showToast(getApplicationContext(), "��������λ������");
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
				if (start > 4) {// ����4������������λʱ����
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
