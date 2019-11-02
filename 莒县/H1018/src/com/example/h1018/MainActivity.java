package com.example.h1018;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ImageView  imyha,imyhb,imzc;
	TextView tvxian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imyha=(ImageView)findViewById(R.id.imyha);
		imyhb=(ImageView)findViewById(R.id.Imyhb);
		imzc=(ImageView)findViewById(R.id.imzc);
		tvxian=(TextView)findViewById(R.id.texian);
		tvxian.setText("");
		
		imyha.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imyha.setImageResource(R.drawable.yha1);
				imyhb.setImageResource(R.drawable.yh);
				tvxian.setText("正在校验用户信息。。。。");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				tvxian.setText("用户信息正确，正在进入系统......");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				startActivity(new Intent(MainActivity.this,ZhuActivity.class));
				finish();
			}
		});
		imyhb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				imyha.setImageResource(R.drawable.yha);
				imyhb.setImageResource(R.drawable.yh1);
				tvxian.setText("正在校验用户信息。。。。");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				tvxian.setText("用户信息正确，正在进入系统......");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				startActivity(new Intent(MainActivity.this,ZhuActivity.class));
				finish();
			}
		});
		imzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(MainActivity.this,ResActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
