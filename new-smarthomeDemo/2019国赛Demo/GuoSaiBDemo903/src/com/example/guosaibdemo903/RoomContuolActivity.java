package com.example.guosaibdemo903;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class RoomContuolActivity extends Activity {
	private TextView tv_kongzhi_roomnumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_room_control);
		tv_kongzhi_roomnumber = (TextView) findViewById(R.id.tv_kongzhi_room_number);
		tv_kongzhi_roomnumber.setText(IndexActivity.room_number);
	}
}