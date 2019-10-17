package com.example.tabmenudemo;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import lib.SocketThread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends TabActivity implements
		OnCheckedChangeListener {
	private TabHost tabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tabHost = this.getTabHost();
		tabHost.addTab(tabHost.newTabSpec("1").setIndicator("")
				.setContent(new Intent(this, Activity1.class)));
		tabHost.addTab(tabHost.newTabSpec("2").setIndicator("")
				.setContent(new Intent(this, Activity2.class)));
		tabHost.addTab(tabHost.newTabSpec("3").setIndicator("")
				.setContent(new Intent(this, Activity_3.class)));
		tabHost.addTab(tabHost.newTabSpec("4").setIndicator("")
				.setContent(new Intent(this, Activity4.class)));
		SocketThread.SocketIp = "10.1.10.2";
		SocketThread.Port = Integer.valueOf("6006");
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			SimpleDateFormat formater = new SimpleDateFormat(
					"yyyy-MM-dd  HH:mm:ss");
			formater.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}