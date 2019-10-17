package com.example.drawnotextdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.ToggleButton;

/*
 * @�ļ�����MainActivity.java
 * @����������������ݴ���
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-6
 */
public class MainActivity extends Activity {
	Random random = new Random();// �����
	static float getdata;// ��ֵ
	static String number;
	static boolean draw_state = false;
	private ToggleButton tg_draw_start;
	private List<Float> nowdata = new ArrayList<Float>();
	// �������ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// ѡ���ͼ
	private RadioButton ra_draw_zhexiantu;
	private RadioButton ra_draw_zhuzhuangtu;
	private RadioButton ra_draw_bingzhuangtu;
	// ����
	private LinearLayout line_zhexiantu;
	private LinearLayout line_zhuzhuangtu;
	private LinearLayout line_bingzhuangtu;
	// ��ѡ
	private RadioButton ra_temp;
	private RadioButton ra_hum;
	private RadioButton ra_gas;
	// ListView
	private ListView listView_zhuzhuangtu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		ra_gas = (RadioButton) findViewById(R.id.ra_gas);
		ra_hum = (RadioButton) findViewById(R.id.ra_hum);
		ra_temp = (RadioButton) findViewById(R.id.ra_temp);
		line_zhuzhuangtu = (LinearLayout) findViewById(R.id.line_zhuzhuangtu);
		line_zhexiantu = (LinearLayout) findViewById(R.id.line_zhexiantu);
		listView_zhuzhuangtu = (ListView) findViewById(R.id.listView_zhuzhuangtu);
		ra_draw_bingzhuangtu = (RadioButton) findViewById(R.id.ra_draw_bingzhuangtu);
		ra_draw_zhexiantu = (RadioButton) findViewById(R.id.ra_draw_zhexian);
		ra_draw_zhuzhuangtu = (RadioButton) findViewById(R.id.ra_draw_zhuzhuangtu);
		Intent intent = new Intent(getApplicationContext(), DataThread.class);
		startActivity(intent);
		listView_zhuzhuangtu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Cursor cursor = (Cursor) arg0.getItemAtPosition(arg2);
				DiyToast.showToast(
						getApplicationContext(),
						"������ֵ��" + cursor.getString(1) + "-------"
								+ cursor.getString(2));
			}
		});
		tg_draw_start = (ToggleButton) findViewById(R.id.tg_draw_start);
		tg_draw_start.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					draw_state = true;
				} else {
					draw_state = false;
					DiyToast.showToast(MainActivity.this, "�ѹر�");
				}
			}
		});
		// �������
		handler.post(timeRunnable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ��������ݡ�����ListView
	 * 
	 * @ʱ �䣺����11:22:26
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (ra_gas.isChecked()) {
				getdata = DataThread.gas;
				number = "ȼ��";
			}
			if (ra_hum.isChecked()) {
				getdata = DataThread.hum;
				number = "ʪ��";
			}
			if (ra_temp.isChecked()) {
				getdata = DataThread.temp;
				number = "�¶�";
			}
			if (draw_state && getdata != 0) {
				SimpleAdapter simpleAdapter = new SimpleAdapter(
						MainActivity.this, MyView_01.list,
						R.layout.activity_text,
						new String[] { "number", "data" }, new int[] {
								R.id.tv_number, R.id.tv_data });
				listView_zhuzhuangtu.setAdapter(simpleAdapter);
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

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);

	};

}
