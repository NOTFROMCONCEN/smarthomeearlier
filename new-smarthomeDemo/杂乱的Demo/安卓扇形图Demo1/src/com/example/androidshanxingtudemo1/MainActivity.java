package com.example.androidshanxingtudemo1;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// 绑定
	private RadioButton ra_hum;
	private RadioButton ra_temp;
	private RadioButton ra_gas;
	private RadioButton ra_press;
	private RadioButton ra_smo;
	private ToggleButton tg_draw_state;
	private ListView lv_1;
	public static float getdata;
	public static String num;
	public static boolean draw_state = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(MainActivity.this, DataThread.class);
		startActivity(intent);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		tg_draw_state = (ToggleButton) findViewById(R.id.tg_draw_state);
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_press = (RadioButton) findViewById(R.id.ra_press);
		ra_smo = (RadioButton) findViewById(R.id.ra_smo);
		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		lv_1 = (ListView) findViewById(R.id.listView1);
		tg_draw_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					draw_state = true;
				} else {
					draw_state = false;
				}
			}
		});
		// 激活进程
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：插入ListView、传输数据
	 * 
	 * @时 间：下午8:08:58
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_gas.isChecked()) {
				num = "燃气";
				getdata = DataThread.gas;
			}
			if (ra_hum.isChecked()) {
				num = "湿度";
				getdata = DataThread.hum;
			}
			if (ra_press.isChecked()) {
				num = "气压";
				getdata = DataThread.press;
			}
			if (ra_smo.isChecked()) {
				num = "烟雾";
				getdata = DataThread.smo;
			}
			if (ra_temp.isChecked()) {
				num = "温度";
				getdata = DataThread.temp;
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
						MyView.list, R.layout.activity_text, new String[] {
								"number", "data" }, new int[] { R.id.tv_num,
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
