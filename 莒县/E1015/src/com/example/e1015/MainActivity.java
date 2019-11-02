package com.example.e1015;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

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
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText etip;
	Button btndl;
	SeekBar seekBar;
	SharedPreferences sp;
	boolean kg = false;
	int cishu;
	String shijian;
	Editor editor;
	TextView tvxs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etip = (EditText) findViewById(R.id.Edip);
		btndl = (Button) findViewById(R.id.butdl);
		seekBar = (SeekBar) findViewById(R.id.seekBar1);
		tvxs = (TextView) findViewById(R.id.tedl);

		sp = getSharedPreferences("jz.xml", Context.MODE_PRIVATE);
		cishu = sp.getInt("cs", 0);
		tvxs.setText("之前已有" + String.valueOf(cishu) + "次登录，上次登录时间为"
				+ sp.getString("sj", ""));

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
				if (progress > 60 && progress < 80) {
					Toast.makeText(MainActivity.this, "验证成功 ",
							Toast.LENGTH_SHORT).show();
					kg = true;
				} else {
					kg = false;
				}
			}
		});
		btndl.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (etip.getText().toString().isEmpty()) {
					Toast.makeText(MainActivity.this, "IP地址不能为空",
							Toast.LENGTH_LONG).show();
				} else if (kg) {
					SimpleDateFormat format = new SimpleDateFormat("HH:mm");
					shijian = format.format(new java.util.Date());

					editor = sp.edit();
					editor.putInt("cs", cishu + 1);
					editor.putString("sj", shijian);
					editor.commit();

					// ControlUtils.setUser("bizideal", "123456",
					// "14.15.67.3.");
					// SocketClient.getInstance().creatConnect();
					// SocketClient.getInstance().login(new LoginCallback() {
					//
					// @Override
					// public void onEvent(final String lj) {
					// // TODO Auto-generated method stub
					// runOnUiThread(new Runnable() {
					//
					// @Override
					// public void run() {
					// // TODO Auto-generated method stub
					// if (lj.equals(ConstantUtil.SUCCESS)) {
					// Toast.makeText(MainActivity.this,
					// "网络连接成功", Toast.LENGTH_LONG)
					// .show();
					// startActivity(new Intent(
					// MainActivity.this,
					// JibenActivity.class));
					// } else if (lj.equals(ConstantUtil.FAILURE)) {
					// Toast.makeText(MainActivity.this,
					// "网络连接失败", Toast.LENGTH_LONG)
					// .show();
					// startActivity(new Intent(
					// MainActivity.this,
					// JibenActivity.class));
					// } else {
					// startActivity(new Intent(
					// MainActivity.this,
					// JibenActivity.class));
					// }
					// }
					// });
					// }
					// });
					startActivity(new Intent(MainActivity.this,
							JibenActivity.class));
				} else {
					Toast.makeText(MainActivity.this, "请滑动验证",
							Toast.LENGTH_LONG).show();
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
