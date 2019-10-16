package com.example.socket_json;




	import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

	import org.json.JSONException;
import org.json.JSONObject;


	import lib.MyThread;
import lib.SocketThread;
import lib.SysApplication;
import lib.json_dispose;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.R.id;
import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
	public class JoinActivity extends Activity {
		private EditText username_AutoCompleteTextView;
		private EditText ip_AutoCompleteTextView;
		private EditText port_AutoCompleteTextView;
		private EditText password_EditText;
		public String password,username;
		private Button button;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			SysApplication.getInstance().addActivity(this);                             
			setContentView(R.layout.join);
			


			username_AutoCompleteTextView = (EditText) findViewById(R.id.autoCompleteTextView_username);  
			ip_AutoCompleteTextView= (EditText) findViewById(R.id.autoCompleteTextView_ip);
			port_AutoCompleteTextView= (EditText) findViewById(R.id.autoCompleteTextView_port);
	        password_EditText=(EditText)findViewById(R.id.editText_password);
			
			button=(Button)findViewById(R.id.button1);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					if(username_AutoCompleteTextView.getText().toString().equals("bizideal")&&password_EditText.getText().toString().equals("123456"))
					{
						username=username_AutoCompleteTextView.getText().toString();
						password=password_EditText.getText().toString();
						SocketThread.SocketIp = ip_AutoCompleteTextView.getText().toString();                        	
						SocketThread.Port = Integer.parseInt(port_AutoCompleteTextView.getText().toString());
						startActivity(new Intent(JoinActivity.this,MainActivity.class));	
					}
					else{
						Toast.makeText(JoinActivity.this, "用户名或密码错误，请重新输入...", Toast.LENGTH_SHORT).show();
					}
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

