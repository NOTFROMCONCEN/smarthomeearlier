package com.example.disan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
	Button btn1,btn2,btn3;
	ImageView im1;
	Handler handler;
	Runnable runnable;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn1=(Button)findViewById(R.id.butzc);
		btn2=(Button)findViewById(R.id.Butdl);
		btn3=(Button)findViewById(R.id.Butxg);
		im1=(ImageView)findViewById(R.id.imageView1);
		
		btn1.setVisibility(View.INVISIBLE);
		btn2.setVisibility(View.INVISIBLE);
		btn3.setVisibility(View.INVISIBLE);
		
		
		btn1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,ResActivity.class));
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,LognActivity.class));
			}
		});
		btn3.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,XgActivity.class));
			}
		});
		
		AnimationSet animationSet=new AnimationSet(true);//动画集
		ScaleAnimation scaleAnimation=new ScaleAnimation(1.0f, 10f,1.0f,10f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);//缩放动画
		scaleAnimation.setDuration(2000);//持续时间
		animationSet.addAnimation(scaleAnimation);//保存动画效果到。。。。。
		animationSet.setFillAfter(true);//结束后保存状态
		im1.startAnimation(animationSet);//设置给控件
		
		handler=new Handler();
		runnable=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				btn1.setVisibility(View.VISIBLE);
				btn2.setVisibility(View.VISIBLE);
				btn3.setVisibility(View.VISIBLE);
			}
		};
		handler.postDelayed(runnable, 2000);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
