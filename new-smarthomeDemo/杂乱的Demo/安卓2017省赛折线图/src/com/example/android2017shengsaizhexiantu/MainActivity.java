package com.example.android2017shengsaizhexiantu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;

/*
 * @文件名：MainActivity.java
 * @描述：主页显示、跳转、数据采集模拟
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class MainActivity extends Activity {
	public static float getdata;
	public static String number;
	public static boolean draw_state = true;
	private RadioButton ra_temp, ra_ill, ra_hum, ra_gas, ra_smo;
	private ListView lv_1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_1 = (ListView) findViewById(R.id.listView1);
		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_ill = (RadioButton) findViewById(R.id.ra_ill);
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_smo = (RadioButton) findViewById(R.id.ra_smo);
		// 激活随机数
		Intent intent = new Intent(MainActivity.this, DataThread.class);
		startActivity(intent);
		// 激活进程
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_hum.isChecked()) {
				getdata = DataThread.hum;
				number = "湿度";
			}
			if (ra_ill.isChecked()) {
				getdata = DataThread.ill;
				number = "光照";
			}
			if (ra_temp.isChecked()) {
				getdata = DataThread.temp;
				number = "温度";
			}
			if (ra_smo.isChecked()) {
				getdata = DataThread.smo;
				number = "烟雾";
			}
			if (ra_gas.isChecked()) {
				getdata = DataThread.gas;
				number = "燃气";
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(
						getApplicationContext(), MyView.list,
						R.layout.activity_draw_text, new String[] { "number",
								"data" }, new int[] { R.id.tv_number,
								R.id.tv_data });
				lv_1.setAdapter(adapter);
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};

}
