package com.example.textcolor;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView tv1,tv2;
	TextView midTv;
	String  string;
	Button btnButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1=(TextView)findViewById(R.id.tv8101);
		tv2=(TextView)findViewById(R.id.tvtv8102);
		btnButton=(Button)findViewById(R.id.button1);
		
		
		
		
		
		OnClickListener tvClick= new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				string="·¿¼äºÅ£º" + ((TextView)v).getText().toString();
				midTv=((TextView)v);
				
				
				
			}
		};
		
		
		tv1.setOnClickListener(tvClick);
		tv2.setOnClickListener(tvClick);
		
		btnButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				midTv.setBackgroundColor(Color.RED);
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
