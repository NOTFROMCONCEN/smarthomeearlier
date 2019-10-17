package com.example.jiudianguanlisqlitest;

import java.util.Random;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * @文件名：MainActivity.java
 * @描述：新数据库操作测试
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-5
 */
public class MainActivity extends Activity implements OnClickListener {
	// 按钮状态
	private boolean view_state1 = false, view_state2 = false,
			view_state3 = false, view_state4 = false;
	// View
	private View view1;
	private View view2;
	private View view3;
	private View view4;
	// 随机数
	private Random random = new Random();
	// 数据库
	private SQLiteDatabase db;
	private MyDataBaseHelper dbHelper;
	// 参数
	private float ill, co, hum, pm, temp, gas, smo, per, press;
	// 布局
	private LinearLayout line_8101, line_8102, line_8103, line_8104, line_8201,
			line_8202, line_8203, line_8204, line_8301, line_8302, line_8303,
			line_8304, line_8401, line_8402, line_8403, line_8404;
	// 房间号
	static String room_number;
	// 按钮
	private Button btn_room_guanli, btn_room_kongzhi, btn_room_link,
			btn_room_temp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 按钮
		btn_room_guanli = (Button) findViewById(R.id.btn_room_guanli);
		btn_room_kongzhi = (Button) findViewById(R.id.btn_room_kongzhi);
		btn_room_link = (Button) findViewById(R.id.btn_link_state);
		btn_room_temp = (Button) findViewById(R.id.btn_temp_state);
		// View
		view1 = (View) findViewById(R.id.view_1);
		view2 = (View) findViewById(R.id.view_2);
		view3 = (View) findViewById(R.id.view_3);
		view4 = (View) findViewById(R.id.view_4);
		// 数据库
		dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
				null, 2);
		db = dbHelper.getWritableDatabase();
		// 布局
		line_8101 = (LinearLayout) findViewById(R.id.line_8101);
		line_8102 = (LinearLayout) findViewById(R.id.line_8102);
		line_8103 = (LinearLayout) findViewById(R.id.line_8103);
		line_8104 = (LinearLayout) findViewById(R.id.line_8104);
		line_8201 = (LinearLayout) findViewById(R.id.line_8201);
		line_8202 = (LinearLayout) findViewById(R.id.line_8202);
		line_8203 = (LinearLayout) findViewById(R.id.line_8203);
		line_8204 = (LinearLayout) findViewById(R.id.line_8204);
		line_8301 = (LinearLayout) findViewById(R.id.line_8301);
		line_8302 = (LinearLayout) findViewById(R.id.line_8302);
		line_8303 = (LinearLayout) findViewById(R.id.line_8303);
		line_8304 = (LinearLayout) findViewById(R.id.line_8304);
		line_8401 = (LinearLayout) findViewById(R.id.line_8401);
		line_8402 = (LinearLayout) findViewById(R.id.line_8402);
		line_8403 = (LinearLayout) findViewById(R.id.line_8403);
		line_8404 = (LinearLayout) findViewById(R.id.line_8404);
		// 设置点击事件
		line_8101.setOnClickListener(this);
		line_8102.setOnClickListener(this);
		line_8103.setOnClickListener(this);
		line_8104.setOnClickListener(this);
		line_8201.setOnClickListener(this);
		line_8202.setOnClickListener(this);
		line_8203.setOnClickListener(this);
		line_8204.setOnClickListener(this);
		line_8301.setOnClickListener(this);
		line_8302.setOnClickListener(this);
		line_8303.setOnClickListener(this);
		line_8304.setOnClickListener(this);
		line_8401.setOnClickListener(this);
		line_8402.setOnClickListener(this);
		line_8403.setOnClickListener(this);
		line_8404.setOnClickListener(this);
		// 设置View颜色
		view1.setBackgroundColor(Color.BLUE);
		view2.setBackgroundColor(Color.WHITE);
		view3.setBackgroundColor(Color.WHITE);
		view4.setBackgroundColor(Color.WHITE);
		view_state1 = true;
		view_state2 = false;
		view_state3 = false;
		view_state4 = false;
		// 设置按钮点击事件
		// 房间管理
		btn_room_guanli.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view1.setBackgroundColor(Color.BLUE);
				view2.setBackgroundColor(Color.WHITE);
				view3.setBackgroundColor(Color.WHITE);
				view4.setBackgroundColor(Color.WHITE);
				view_state1 = true;
				view_state2 = false;
				view_state3 = false;
				view_state4 = false;
			}
		});
		// 房间控制
		btn_room_kongzhi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view1.setBackgroundColor(Color.WHITE);
				view2.setBackgroundColor(Color.BLUE);
				view3.setBackgroundColor(Color.WHITE);
				view4.setBackgroundColor(Color.WHITE);
				view_state1 = false;
				view_state2 = true;
				view_state3 = false;
				view_state4 = false;
			}
		});
		// 联动管理
		btn_room_link.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view1.setBackgroundColor(Color.WHITE);
				view2.setBackgroundColor(Color.WHITE);
				view3.setBackgroundColor(Color.BLUE);
				view4.setBackgroundColor(Color.WHITE);
				view_state1 = false;
				view_state2 = false;
				view_state3 = true;
				view_state4 = false;
			}
		});
		// 温度趋势
		btn_room_temp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view1.setBackgroundColor(Color.WHITE);
				view2.setBackgroundColor(Color.WHITE);
				view3.setBackgroundColor(Color.WHITE);
				view4.setBackgroundColor(Color.BLUE);
				view_state1 = false;
				view_state2 = false;
				view_state3 = false;
				view_state4 = true;
			}
		});
		// 数据采集
		ControlUtils.getData();
		SocketClient.getInstance().getData(new DataCallback<DeviceBean>() {

			@Override
			public void onResult(final DeviceBean getdata) {
				// TODO Auto-generated method stub
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (!TextUtils.isEmpty(getdata.getAirPressure())) {// 气压
							press = Float.valueOf(getdata.getAirPressure());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							co = Float.valueOf(getdata.getCo2());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							gas = Float.valueOf(getdata.getGas());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							hum = Float.valueOf(getdata.getHumidity());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							ill = Float.valueOf(getdata.getIllumination());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							pm = Float.valueOf(getdata.getPM25());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							smo = Float.valueOf(getdata.getSmoke());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							temp = Float.valueOf(getdata.getTemperature());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {// 如果红外值被关闭
								per = 0;
							} else {
								per = 1;
							}
						}
					}
				});
			}
		});
		// 设置APP打开时显示房间状态
		for (int i = 1; i < 5; i++) {
			if (i == 1) {
				for (int j = 1; j < 5; j++) {
					System.out.println("房间：8" + i + "0" + j);
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8101.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8101.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8101.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8102.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8102.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8102.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8103.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8103.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8103.setBackgroundColor(Color.RED);
							}
						}

					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8104.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8104.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8104.setBackgroundColor(Color.RED);
							}
						}
					}
				}
			}
			if (i == 2) {
				for (int j = 1; j < 5; j++) {
					System.out.println("房间：8" + i + "0" + j);
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8201.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8201.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8201.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8202.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8202.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8202.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8203.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8203.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8203.setBackgroundColor(Color.RED);
							}
						}

					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8204.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8204.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8204.setBackgroundColor(Color.RED);
							}
						}
					}
				}
			}
			if (i == 3) {
				for (int j = 1; j < 5; j++) {
					System.out.println("房间：8" + i + "0" + j);
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8301.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8301.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8301.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8302.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8302.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8302.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8303.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8303.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8303.setBackgroundColor(Color.RED);
							}
						}

					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8304.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8304.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8304.setBackgroundColor(Color.RED);
							}
						}
					}
				}
			}
			if (i == 4) {
				for (int j = 1; j < 5; j++) {
					System.out.println("房间：8" + i + "0" + j);
					if (j == 1) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8401.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8401.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8401.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 2) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8402.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8402.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8402.setBackgroundColor(Color.RED);
							}
						}
					}
					if (j == 3) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8403.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8403.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8403.setBackgroundColor(Color.RED);
							}
						}

					}
					if (j == 4) {
						Cursor cursor = db.rawQuery("select * from room8" + i
								+ "0" + j, null);
						if (cursor.getCount() != 0) {
							cursor.moveToLast();
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"1")) {
								System.out.println("可入住");
								line_8404.setBackgroundColor(Color.GREEN);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"2")) {
								System.out.println("未打扫");
								line_8404.setBackgroundColor(Color.GRAY);
							}
							if (cursor.getString(
									cursor.getColumnIndex("roomstate")).equals(
									"3")) {
								System.out.println("已入住");
								line_8404.setBackgroundColor(Color.RED);
							}
						}
					}
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			room_number = "8101";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8102:
			room_number = "8102";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8103:
			room_number = "8103";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8104:
			room_number = "8104";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8201:
			room_number = "8201";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8202:
			room_number = "8202";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8203:
			room_number = "8203";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8204:
			room_number = "8204";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8301:
			room_number = "8301";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8302:
			room_number = "8302";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8303:
			room_number = "8303";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8304:
			room_number = "8304";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8401:
			room_number = "8401";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8402:
			room_number = "8402";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8403:
			room_number = "8403";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		case R.id.line_8404:
			room_number = "8404";
			if (view_state1) {
				showtitle();
			}
			if (view_state2) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state3) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			if (view_state4) {
				// Intent intent = new Intent();
				// startActivity(intent);
			}
			break;
		default:
			break;
		}
	}

	private void showtitle() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);
		builder.setTitle("房间管理");
		builder.setIcon(R.drawable.ic_launcher);
		LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
		final View view = layoutInflater.inflate(R.layout.show_title, null,
				false);
		builder.setView(view);
		final TextView tv_temp, tv_hum, tv_gas, tv_press, tv_smo, tv_ill, tv_pm, tv_co, tv_per;
		builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		final Button btn_yiruzhu, btn_weidasao, btn_weiruzhu;
		final TextView tv_room_number;
		tv_room_number = (TextView) view.findViewById(R.id.tv_room_number);
		tv_room_number.setText("房间号：" + room_number);
		// 按钮
		btn_weidasao = (Button) view.findViewById(R.id.btn_weidasao);
		btn_weiruzhu = (Button) view.findViewById(R.id.btn_weruzhu);
		btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		// 文本
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		// 数据采集
		tv_co.setText(String.valueOf(co));
		tv_gas.setText(String.valueOf(gas));
		tv_hum.setText(String.valueOf(hum));
		tv_ill.setText(String.valueOf(ill));
		tv_pm.setText(String.valueOf(pm));
		tv_press.setText(String.valueOf(press));
		tv_smo.setText(String.valueOf(smo));
		tv_temp.setText(String.valueOf(temp));
		if (per == 1) {
			tv_per.setText("有人");
		} else {
			tv_per.setText("无人");
		}
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "当前房间：" + room_number,
						Toast.LENGTH_SHORT).show();
				db.execSQL("insert into room" + room_number
						+ " (roomstate)values(?)", new String[] { "3" });
				if (room_number.equals("8101")) {
					line_8101.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8102")) {
					line_8102.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8103")) {
					line_8103.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8104")) {
					line_8104.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8201")) {
					line_8201.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8202")) {
					line_8202.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8203")) {
					line_8203.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8204")) {
					line_8204.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8301")) {
					line_8301.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8302")) {
					line_8302.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8303")) {
					line_8303.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8304")) {
					line_8304.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8401")) {
					line_8401.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8402")) {
					line_8402.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8403")) {
					line_8403.setBackgroundColor(Color.RED);
				}
				if (room_number.equals("8404")) {
					line_8404.setBackgroundColor(Color.RED);
				}
			}
		});
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "当前房间：" + room_number,
						Toast.LENGTH_SHORT).show();
				db.execSQL("insert into room" + room_number
						+ " (roomstate)values(?)", new String[] { "1" });
				if (room_number.equals("8101")) {
					line_8101.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8102")) {
					line_8102.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8103")) {
					line_8103.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8104")) {
					line_8104.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8201")) {
					line_8201.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8202")) {
					line_8202.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8203")) {
					line_8203.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8204")) {
					line_8204.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8301")) {
					line_8301.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8302")) {
					line_8302.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8303")) {
					line_8303.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8304")) {
					line_8304.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8401")) {
					line_8401.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8402")) {
					line_8402.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8403")) {
					line_8403.setBackgroundColor(Color.GREEN);
				}
				if (room_number.equals("8404")) {
					line_8404.setBackgroundColor(Color.GREEN);
				}
			}
		});
		// 按钮点击事件
		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "当前房间：" + room_number,
						Toast.LENGTH_SHORT).show();
				db.execSQL("insert into room" + room_number
						+ " (roomstate)values(?)", new String[] { "2" });
				if (room_number.equals("8101")) {
					line_8101.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8102")) {
					line_8102.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8103")) {
					line_8103.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8104")) {
					line_8104.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8201")) {
					line_8201.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8202")) {
					line_8202.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8203")) {
					line_8203.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8204")) {
					line_8204.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8301")) {
					line_8301.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8302")) {
					line_8302.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8303")) {
					line_8303.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8304")) {
					line_8304.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8401")) {
					line_8401.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8402")) {
					line_8402.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8403")) {
					line_8403.setBackgroundColor(Color.GRAY);
				}
				if (room_number.equals("8404")) {
					line_8404.setBackgroundColor(Color.GRAY);
				}
			}
		});
		builder.show();
	}
}