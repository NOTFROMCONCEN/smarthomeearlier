package com.example.h1018;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ResActivity extends Activity {
    ImageView imyha,imyhb,imyhc,imyhd;
    Button btnzc,btnfan;
    TextView tvxian;
    SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_res);
		imyha=(ImageView)findViewById(R.id.imzca);
		imyhb=(ImageView)findViewById(R.id.Imzcb);
		imyhc=(ImageView)findViewById(R.id.Imzcc);
		imyhd=(ImageView)findViewById(R.id.Imzcd);
		btnfan=(Button)findViewById(R.id.Butfan);
		btnzc=(Button)findViewById(R.id.butzc);
		tvxian=(TextView)findViewById(R.id.texians);
		
		btnzc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (imyha.isClickable()) {
					imyha.setImageResource(R.drawable.qqa1);
					tvxian.setText("正在注册用户信息。。。 ");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					tvxian.setText("用户信息注册成功.......");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					startActivity(new Intent(ResActivity.this,MainActivity.class));
					finish();
				}else if (imyhb.isClickable()) {
					imyhb.setImageResource(R.drawable.yha1);
					tvxian.setText("正在注册用户信息。。。 ");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					tvxian.setText("用户信息注册成功.......");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					startActivity(new Intent(ResActivity.this,MainActivity.class));
					finish();
				}else if (imyhc.isClickable()) {
					imyhc.setImageResource(R.drawable.qq1);
					tvxian.setText("正在注册用户信息。。。 ");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					tvxian.setText("用户信息注册成功.......");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					startActivity(new Intent(ResActivity.this,MainActivity.class));
					finish();
				}else if (imyhd.isClickable()) {
					imyhd.setImageResource(R.drawable.yh1);
					tvxian.setText("正在注册用户信息。。。 ");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					tvxian.setText("用户信息注册成功.......");
					try {
						Thread.sleep(2000);
					} catch (Exception e) {
						// TODO: handle exception
					}
					startActivity(new Intent(ResActivity.this,MainActivity.class));
					finish();
				}
			}
		});
		btnfan.setOnClickListener(new OnClickListener() {
			
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
