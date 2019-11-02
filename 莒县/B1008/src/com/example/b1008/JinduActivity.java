package com.example.b1008;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.TextView;

public class JinduActivity extends Activity {
	TextView textjindu;
	ProgressBar pro1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jindu);
		textjindu=(TextView)findViewById(R.id.textjin);
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
			case 100:
			//	startActivity(new Intent(JinduActivity.this,ZongActivity.class));
				AlertDialog.Builder builder=new AlertDialog.Builder(JinduActivity.this);
				builder.setTitle("��¼�ɹ�").setMessage("��ӭ����").setPositiveButton("Ok",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						startActivity(new Intent(JinduActivity.this,ZongActivity.class));
						finish();
					}
				}).show();
				
				break;

			default:
				textjindu.setText(msg.arg1+"%");
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
			if (i<=101) {
				handler.sendMessage(msg);
			}else {
				handler.removeCallbacks(upRunnable);
			}
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_jindu, menu);
		return true;
	}

}
