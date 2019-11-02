package com.example.g1014;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ImageView imlogo;
	TextView tvjishi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imlogo=(ImageView)findViewById(R.id.imlogo);
		tvjishi=(TextView)findViewById(R.id.Tejishi);
		
		handler.post(upRunnable);
		
		AnimationSet animationSet=new AnimationSet(true);
		ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f, 10f,1.0f,10f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
		scaleAnimation.setDuration(2000);
		animationSet.addAnimation(scaleAnimation);
		animationSet.setFillAfter(true);
		imlogo.startAnimation(animationSet);
	
	}
		Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			tvjishi.setText(msg.arg1+"秒后进入登录界面......");
			if (msg.arg1==1) {
				startActivity(new Intent(MainActivity.this,LoginActivity.class));
			}
			handler.postDelayed(upRunnable,1000);
		}
	};
	Runnable upRunnable=new Runnable() {
		int i=5;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			msg.arg1=i;
			i--;
			if (i>=0) {
				handler.sendMessage(msg);
			}else {
				handler.removeCallbacks(upRunnable);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
