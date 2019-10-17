package com.example.texteditinfocheck;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et_number = (EditText) findViewById(R.id.et_number);
		et_number.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				// �Զ�����ʾ����ʾ
				// �ж��������ʲô
				// ������ʽ
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher matcher = pattern.matcher(et_number.getText()
						.toString());
				if (matcher.matches()) {
					DiyToast.showToast(getApplicationContext(), "�����������");
				}
				pattern = Pattern.compile("[a-zA-Z]");
				matcher = pattern.matcher(et_number.getText().toString());
				if (matcher.matches()) {
					DiyToast.showToast(getApplicationContext(), "���������ĸ");
				}
				pattern = Pattern.compile("[\u4e00-\u9fa5]");
				matcher = pattern.matcher(et_number.getText().toString());
				if (matcher.matches()) {
					DiyToast.showToast(getApplicationContext(), "�����������");
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
