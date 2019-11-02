package com.example.e0926;

import java.text.SimpleDateFormat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

public class MainActivity extends Activity {
	EditText etduankou;
	EditText etip;
	Button btndl;
	SeekBar seekBar;
	TextView tvjs;
	boolean kg=false;
	SharedPreferences sp;
	SharedPreferences.Editor editor;
	int cishu=0;
	String time;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etduankou=(EditText)findViewById(R.id.edduankou);
		etip=(EditText)findViewById(R.id.Edip);
		btndl=(Button)findViewById(R.id.butdl);
		seekBar=(SeekBar)findViewById(R.id.seekBar1);
		tvjs=(TextView)findViewById(R.id.textView2);

		sp=getSharedPreferences("jzz.xml", Context.MODE_PRIVATE);
		cishu=sp.getInt("cs",0);
		tvjs.setText("之前已有"+cishu+"次登录，上次登录时间为"+sp.getString("sj", ""));
		
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

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
				if (progress>65&&progress<90) {
					kg=true;
					Toast.makeText(MainActivity.this, "验证成功 ",0).show();
				}else {
					kg=false;
				}
			}
		});
		btndl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etduankou.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "端口号不能为空", Toast.LENGTH_LONG).show();
				}else if (etip.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "IP不能为空", Toast.LENGTH_LONG).show();
				}else if (kg) {
					SimpleDateFormat format=new SimpleDateFormat("HH:mm");
					time=format.format(new java.util.Date());
				
					editor=sp.edit();
					editor.putInt("cs", cishu+1);
					editor.putString("sj",time);
					editor.commit();
					
					ControlUtils.setUser("bizideal", "123456", "123456");
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
										Toast.makeText(MainActivity.this, "网络连接成功 ",0).show();
										startActivity(new Intent(MainActivity.this,JibenActivity.class));
									}else if (lj.equals(ConstantUtil.FAILURE)) {
										Toast.makeText(MainActivity.this, "网络连接失败",0).show();
										startActivity(new Intent(MainActivity.this,JibenActivity.class));
									}else {
										Toast.makeText(MainActivity.this,"连接中",0).show();
									}
								}
							});
						}
					});
				}else {
					Toast.makeText(MainActivity.this,"请滑动验证 ",0).show();
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
