package com.example.d0924;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class ZhuActivity extends Activity {
	ImageView imcgq,imkz,imqj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhu);
		imcgq=(ImageView)findViewById(R.id.imcgq);
		imkz=(ImageView)findViewById(R.id.Imkz);
		imqj=(ImageView)findViewById(R.id.Imqj);
		
		ControlUtils.setUser("bizideal", "123456", "10.1.1.1");
		ControlUtils.getData();
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String lj) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (lj.equals(ConstantUtil.SUCCESS)) {
							Toast.makeText(ZhuActivity.this, "网络连接成功 ", Toast.LENGTH_LONG).show();
						}else if (lj.equals(ConstantUtil.FAILURE)) {
							Toast.makeText(ZhuActivity.this, "网络连接失败 ", Toast.LENGTH_LONG).show();
						}else {
							Toast.makeText(ZhuActivity.this, "网络连接中", Toast.LENGTH_LONG).show();
						}
					}
				});
			}
		});
		
		imcgq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ZhuActivity.this,JibenActivity.class));
			}
		});
		imkz.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ZhuActivity.this,KzActivity.class));
			}
		});
		imqj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ZhuActivity.this,MoshiActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_zhu, menu);
		return true;
	}

}
