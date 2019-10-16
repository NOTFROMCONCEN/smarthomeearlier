package com.example.guosaibdemo904;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RoomTempActivity extends Activity {
	TextView tv_temp_state_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_temp_state);
		tv_temp_state_number = (TextView) findViewById(R.id.tv_temp_state_number);
	}
}
