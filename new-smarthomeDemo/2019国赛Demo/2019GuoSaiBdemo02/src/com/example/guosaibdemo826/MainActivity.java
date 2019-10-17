package com.example.guosaibdemo826;

import lib.SocketThread;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class MainActivity extends Activity implements OnSeekBarChangeListener {
	private Button btn_login;
	private SeekBar sk_1;
	private EditText et_ip;
	private String ip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_login = (Button) findViewById(R.id.btn_login);
		et_ip = (EditText) findViewById(R.id.et_ip);
		sk_1 = (SeekBar) findViewById(R.id.seekBar1);
		sk_1.setOnSeekBarChangeListener(this);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ip = et_ip.getText().toString();
				if (ip.equals("")) {
					Toast.makeText(MainActivity.this, "请输入IP地址",
							Toast.LENGTH_SHORT).show();
				} else {
					if (sk_1.getProgress() != sk_1.getMax()) {
						Toast.makeText(MainActivity.this, "请完成滑动验证",
								Toast.LENGTH_SHORT).show();
					} else {
						SocketThread.SocketIp = ip;
						SocketThread.Port = Integer.valueOf("6006");
						Intent intent = new Intent(MainActivity.this,
								LoadingActivity.class);
						startActivity(intent);
						finish();
					}
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		if (seekBar.getProgress() == seekBar.getMax()) {
			Toast.makeText(MainActivity.this, "验证完成", Toast.LENGTH_SHORT)
					.show();
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		if (seekBar.getProgress() != seekBar.getMax()) {
			seekBar.setProgress(0);
			Toast.makeText(MainActivity.this, "请滑动至最右端完成验证", Toast.LENGTH_SHORT)
					.show();
		}
	}

}
