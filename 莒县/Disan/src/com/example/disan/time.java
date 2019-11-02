package com.example.disan;

import java.text.SimpleDateFormat;

import android.R.integer;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;

public class time extends TextView{
	String shijian;
	public time(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss");
		shijian=format.format(new java.util.Date());
		time.this.setText(shijian);
		
		handler.post(upRunnable);
}
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日  hh:mm:ss");
			shijian=format.format(new java.util.Date());
			time.this.setText(shijian);
			handler.postDelayed(upRunnable, 1000);
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

}
