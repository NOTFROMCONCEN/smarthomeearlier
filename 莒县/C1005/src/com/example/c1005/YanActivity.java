package com.example.c1005;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.Toast;

public class YanActivity extends Activity {
	SeekBar seekBar;
	boolean kg=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_yan);
		seekBar=(SeekBar)findViewById(R.id.seekBar1);
		
		
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
				if (progress==100) {
					kg=true;
					Toast.makeText(YanActivity.this, "验证成功 ", Toast.LENGTH_LONG).show();
					startActivity(new Intent(YanActivity.this,ZongActivity.class));
					finish();
				}else {
					kg=false;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_yan, menu);
		return true;
	}

}
