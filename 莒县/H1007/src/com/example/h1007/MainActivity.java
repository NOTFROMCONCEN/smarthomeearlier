package com.example.h1007;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ImageView imdl,imdl1,imzc;
	TextView tvxian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imdl=(ImageView)findViewById(R.id.imdl);
		imdl1=(ImageView)findViewById(R.id.Imdl1);
		imzc=(ImageView)findViewById(R.id.imzc);
		tvxian=(TextView)findViewById(R.id.tvxianshi);
		
		imdl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imdl.setImageResource(R.drawable.yha1);
				imdl1.setImageResource(R.drawable.yh);
				tvxian.setText("正在校验用户信息。。。。。。");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				tvxian.setText("用户信息正确，正在进入系统。。。。。。");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				startActivity(new Intent(MainActivity.this,ZhuActivity.class));
			}
		});
		imdl1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				imdl1.setImageResource(R.drawable.yh1);
				imdl.setImageResource(R.drawable.yha);
				tvxian.setText("正在校验用户信息。。。。。。");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				tvxian.setText("用户信息正确，正在进入系统。。。。。。");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				startActivity(new Intent(MainActivity.this,ZhuActivity.class));
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
