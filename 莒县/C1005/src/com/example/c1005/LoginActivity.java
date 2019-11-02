package com.example.c1005;

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
	TextView tvshan;
	EditText etname,etpwd,etip,etduankou;
	Button btndl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		tvshan=(TextView)findViewById(R.id.tvshan);
		etname=(EditText)findViewById(R.id.edname);
		etip=(EditText)findViewById(R.id.edip);
		etduankou=(EditText)findViewById(R.id.edduankou);
		etpwd=(EditText)findViewById(R.id.edpwd);
		btndl=(Button)findViewById(R.id.butdl);
		
		etpwd.setTransformationMethod(new Password());
		
		handler.post(upRunnable);
		
		btndl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!etname.getText().toString().equals("bizideal")&&etpwd.getText().toString().equals("123456")) {
				   AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
				   builder.setTitle("µÇÂ¼Ê§°Ü").setMessage("ÃÜÂë»òÓÃ»§Ãû´íÎó").setPositiveButton("OK",null);
				}else {
					Toast.makeText(LoginActivity.this, "µÇÂ¼³É¹¦ ", Toast.LENGTH_LONG).show();
					startActivity(new Intent(LoginActivity.this,YanActivity.class));
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
				
				tvshan.setText("¼ÓÔØÍê±Ï£¬ÇëµÇÂ¼.......");
			}else {
				tvshan.setText("");
			}
			handler.postDelayed(upRunnable, 100);
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
