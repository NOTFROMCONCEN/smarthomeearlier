package com.example.i1016;

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
		String str="正在初始化.....";
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			pro1.setProgress(msg.arg1);
			switch (msg.arg1) {
			case 0:
				str="正在初始化.....";
				break;
			case 21:
				str="正在载入系统.....";
				break;
			case 51:
				str="正在连接服务器....";
				break;
			case 100:
				str="进入登录界面.....";
				startActivity(new Intent(MainActivity.this,LoginActivity.class));
				finish();
				break;

			default:
				tvjindu.setText(str+msg.arg1+"%");
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
