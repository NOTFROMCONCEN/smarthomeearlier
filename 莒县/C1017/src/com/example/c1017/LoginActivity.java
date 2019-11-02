package com.example.c1017;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	EditText etname,etpwd,etip,etduankou;
	Button btndl;
	TextView tvshan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etname=(EditText)findViewById(R.id.edname);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		etip=(EditText)findViewById(R.id.Edip);
		etduankou=(EditText)findViewById(R.id.Edduankou);
		btndl=(Button)findViewById(R.id.butdl);
		tvshan=(TextView)findViewById(R.id.teshan);
//		handler.post(upRunnable);
		
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etname.getText().toString().isEmpty()) {
					Toast.makeText(LoginActivity.this, "用户名不能为空 ", Toast.LENGTH_LONG).show();
				}else if (!etname.getText().toString().equals("bizideal")&&etpwd.getText().toString().equals("123456")) {
					AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
					builder.setTitle("登录失败").setMessage("密码或用户名错误").setPositiveButton("Ok", null).show();
				}else {
					startActivity(new Intent(LoginActivity.this,YanzActivity.class));
				}
			}
		});
		
	}
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.arg1%2==0) {
				tvshan.setText("");
			}else {
				tvshan.setText("加载完毕，请登录......");
			}
			handler.postDelayed(upRunnable, 1000);
		}
	};
	Runnable upRunnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

}
