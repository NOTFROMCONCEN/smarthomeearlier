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
		// 字体超过屏幕显示范围即可有跑马灯效果，需要在XML文件中设置参数
		tv_1.setText("------------------------------------------------------------------------------------------------------------------------------------------");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
