package com.example.a1011;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class LoginActivity extends Activity {
      EditText etip;
      Button btndl;
      SeekBar seekBar;
      boolean kg=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etip=(EditText)findViewById(R.id.edip);
		btndl=(Button)findViewById(R.id.butdl);
		seekBar=(SeekBar)findViewById(R.id.seekBar1);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (progress==100) {
					kg=true;
					Toast.makeText(LoginActivity.this, "验证成功 ", Toast.LENGTH_LONG).show();
					
				}else {
					kg=false;
				}
			}
		});
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etip.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "IP地址不能为空", Toast.LENGTH_LONG).show();
				}else if (kg) {
					ControlUtils.setUser("bizideal", "123456", "18.1.10.7");
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
										Toast.makeText(LoginActivity.this, "网络连接成功", Toast.LENGTH_LONG).show();
										startActivity(new Intent(LoginActivity.this,ZhuActivity.class));
									}else if (lj.equals(ConstantUtil.FAILURE)) {
										Toast.makeText(LoginActivity.this, "网络连接失败", Toast.LENGTH_LONG).show();
										startActivity(new Intent(LoginActivity.this,ZhuActivity.class));
									}
								}
							});
						}
					});
				}else {
					Toast.makeText(LoginActivity.this, "请滑动验证 ", Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
