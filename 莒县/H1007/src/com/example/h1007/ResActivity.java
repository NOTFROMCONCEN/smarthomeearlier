package com.example.h1007;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResActivity extends Activity {
	ImageView imzca,imzcb,imzcc,imzcd;
	Button btnzc,btntc;
	TextView tvxian;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		imzca=(ImageView)findViewById(R.id.imzca);
		imzcb=(ImageView)findViewById(R.id.Imzcb);
		imzcc=(ImageView)findViewById(R.id.Imzcc);
		imzcd=(ImageView)findViewById(R.id.Imzcd);
		btntc=(Button)findViewById(R.id.Butfan);
		btnzc=(Button)findViewById(R.id.butzc);
		tvxian=(TextView)findViewById(R.id.tetishi);
		
		btnzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tvxian.setText("正在注册用户信息");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				tvxian.setText("用户信息注册成功");
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
					// TODO: handle exception
				}
				startActivity(new Intent(ResActivity.this,MainActivity.class));
			}
		});
		btntc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ResActivity.this,MainActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_res, menu);
		return true;
	}

}
