package com.example.c0923;

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

public class LoginActivity extends Activity {
	EditText etname,etpwd,etduankou,etip;
	Button btndl;
	TextView tvshan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		etname=(EditText)findViewById(R.id.edname);
		etduankou=(EditText)findViewById(R.id.Edduankou);
		etpwd=(EditText)findViewById(R.id.Edpwd);
		etip=(EditText)findViewById(R.id.Edip);
		btndl=(Button)findViewById(R.id.butdl);
		tvshan=(TextView)findViewById(R.id.textshan);
		
		handler.post(runnable);
		
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!etname.getText().toString().equals("bizideal001")&&etpwd.getText().toString().equals("123456")) {
					AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
					builder.setTitle("µÇÂ¼Ê§°Ü").setMessage("ÃÜÂë»òÓÃ»§Ãû´íÎó").setPositiveButton("OK", null).show();
				}else {
					startActivity(new Intent(LoginActivity.this,ZongActivity.class));
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
				tvshan.setText(" ");
			}else {
				tvshan.setText("¼ÓÔØÍê±Ï£¬ÇëµÇÂ¼.....");
			}
			handler.postDelayed(runnable, 1000);
		}
	};
	 Runnable runnable=new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg=handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

}
