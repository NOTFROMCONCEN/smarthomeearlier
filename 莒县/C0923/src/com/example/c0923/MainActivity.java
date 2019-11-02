package com.example.c0923;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvjindu;
	ProgressBar pro1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvjindu=(TextView)findViewById(R.id.textjindu);
		pro1=(ProgressBar)findViewById(R.id.progressBar1);
		
		handler.post(upRunnable);
	}
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			pro1.setProgress(msg.arg1);
			switch (msg.arg1) {
			case 10:
				tvjindu.setText("正在加载串口配置.......");
				break;
			case 20:
				tvjindu.setText("串口配置加载完成.......");
				break;
			case 30:
				tvjindu.setText("正在加载界面配置.......");
				break;
			case 50:
				tvjindu.setText("界面配置加载完成.......");
				break;
			case 60:
				tvjindu.setText("正在初始化界面.......");
				break;
			case 80:
				tvjindu.setText("界面初始化完成.......");
				break;
			case 99:
				tvjindu.setText("进入系统中.......");
				startActivity(new Intent(MainActivity.this,LoginActivity.class));
				finish();
				break;

			default:
				break;
			}
			handler.postDelayed(upRunnable, 50);
		}
	};
	Runnable upRunnable=new Runnable() {
		int i=0;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			msg.arg1=i;
			i++;
			handler.sendMessage(msg);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
