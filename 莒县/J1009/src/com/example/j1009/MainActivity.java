package com.example.j1009;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	 Button btndl,btnck;
	 EditText etip;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnck=(Button)findViewById(R.id.Butck);
		btndl=(Button)findViewById(R.id.butdl);
		etip=(EditText)findViewById(R.id.edip);
		
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!etip.getText().toString().equals("12.1.6.2")) {
					Toast.makeText(MainActivity.this, "IP地址不正确", Toast.LENGTH_LONG).show();
				}else {
					ControlUtils.setUser("bizideal", "123456", "12.1.6.2");
					
				  startActivity(new Intent(MainActivity.this,ZongActivity.class));
				  
				}
			}
		});
		btnck.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				  startActivity(new Intent(MainActivity.this,ShujuActivity.class));
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
