package com.example.modetest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/*
 * @�ļ�����ModeActivity.java
 * @���������û�
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-25
 */
public class ModeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mode);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_mode, menu);
		return true;
	}
}