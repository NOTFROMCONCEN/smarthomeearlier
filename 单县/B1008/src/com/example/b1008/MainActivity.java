package com.example.b1008;

import com.bizideal.smarthome.socket.ControlUtils;

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

public class MainActivity extends Activity {
	Button btndl;
	EditText etip;
	SeekBar seekBar;
	boolean kg=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btndl=(Button)findViewById(R.id.butdl);
		etip=(EditText)findViewById(R.id.edip);
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
					Toast.makeText(MainActivity.this, "验证成功", Toast.LENGTH_LONG).show();
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
					Toast.makeText(MainActivity.this, "IP非法", Toast.LENGTH_LONG).show();
				}else if (kg) {
					startActivity(new Intent(MainActivity.this,JinduActivity.class));
				}else {
					Toast.makeText(MainActivity.this, "请滑动验证 ", Toast.LENGTH_LONG).show();
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
