package com.example.jiugonggedrawdemo2;

import java.sql.Connection;
import java.sql.DriverManager;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO 主页
 * @package_name com.example.jiugonggedrawdemo2
 * @project_name 九宫格DrawDemo2
 * @file_name MainActivity.java
 */
public class MainActivity extends Activity {
	String sql = "SELECT * FROM user_info";// 查询表名为“table_test”的所有内容

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
