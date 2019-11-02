package com.example.dlian;

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

public class ZongActivity extends Activity {
	ImageView ivcgq,ivsb,ivqj;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zong);
		ivcgq=(ImageView)findViewById(R.id.ivzcgq);
		ivsb=(ImageView)findViewById(R.id.ivzsb);
		ivqj=(ImageView)findViewById(R.id.ivzqj);
		
		ControlUtils.setUser("bizideal", "123456", "18.1.10.7");
		SocketClient.getInstance().creatConnect();
		SocketClient.getInstance().login(new LoginCallback() {
			
			@Override
			public void onEvent(final String qwe) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (qwe.equals(ConstantUtil.SUCCESS)) {
							Toast.makeText(ZongActivity.this, "网络连接成功", 0).show();
						}else if (qwe.equals(ConstantUtil.FAILURE)) {
							Toast.makeText(ZongActivity.this, "网络连接失败", 0).show();
						}else {
							Toast.makeText(ZongActivity.this, "连接中......", 0).show();	
						}
						
					}
				});
				
			}
		});
		ivcgq.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ZongActivity.this,CgqActivity.class));
				
			}
		});
		ivsb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ZongActivity.this,ShebActivity.class));
				
			}
		});
		ivqj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ZongActivity.this,QingjActivity.class));
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_zong, menu);
		return true;
	}

}
