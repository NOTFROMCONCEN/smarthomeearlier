package com.example.guosaibdemo903;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RoomLinkActivity extends Activity {
	TextView tv_link_room_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link_state);
		tv_link_room_number = (TextView) findViewById(R.id.tv_link_room_number);
		tv_link_room_number.setText(IndexActivity.room_number);
	}
}
