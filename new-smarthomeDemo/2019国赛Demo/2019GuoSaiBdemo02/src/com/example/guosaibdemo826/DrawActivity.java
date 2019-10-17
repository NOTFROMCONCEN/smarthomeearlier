package com.example.guosaibdemo826;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DrawActivity extends Activity {
	private TextView tv_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		tv_num = (TextView) findViewById(R.id.tv_draw_roomnumber);
		tv_num.setText(MenuActivity.room_number);
	}
}