package com.example.textrunhouse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tv_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv_1 = (TextView) findViewById(R.id.tv_run);
		tv_1.setTextSize(30);
		// ���峬����Ļ��ʾ��Χ�����������Ч������Ҫ��XML�ļ������ò���
		tv_1.setText("------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
