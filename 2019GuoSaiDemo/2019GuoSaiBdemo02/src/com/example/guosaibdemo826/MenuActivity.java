package com.example.guosaibdemo826;

import lib.Json_data;
import lib.SocketThread;
import lib.Updata_activity;
import lib.json_dispose;
import org.json.JSONException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MenuActivity extends Activity implements OnClickListener {
	static LinearLayout line_8101, line_8102, line_8103, line_8104, line_8201,
			line_8202, line_8203, line_8204, line_8301, line_8302, line_8303,
			line_8304, line_8401, line_8402, line_8403, line_8404;
	static String room_number, room_state, state;
	public Thread UpdataThread;
	public json_dispose Js = new json_dispose();
	private SQLiteDatabase db;
	private MyDataBaseHelper dbHelper;
	private int count = 1;
	private ToggleButton tg_room_state;
	private ToggleButton tg_room_conture;
	private ToggleButton tg_room_link;
	private ToggleButton tg_room_temp;
	static float ill, temp, hum;
	private String ill_num, temp_num, hum_num;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		// 激活封装内部的线程（网络连接功能）
		network();

		// 绑定控件
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
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		tg_room_conture = (ToggleButton) findViewById(R.id.tg_room_conture);
		tg_room_link = (ToggleButton) findViewById(R.id.tg_room_link);
		tg_room_state = (ToggleButton) findViewById(R.id.tg_room_state);
		tg_room_temp = (ToggleButton) findViewById(R.id.tg_temp_state);
		// 设置linearlayout点击事件并封装
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

		// 设置数据采集进程
		UpdataThread = new Thread(new Updata_activity());
		UpdataThread.start();
		Updata_activity.updatahandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				try {
					Js.receive();
					ill_num = Js.receive_data.get(Json_data.Illumination)
							.toString();
					hum_num = Js.receive_data.get(Json_data.Humidity)
							.toString();
					temp_num = Js.receive_data.get(Json_data.Temp).toString();
					ill = Float.parseFloat(ill_num);
					hum = Float.parseFloat(hum_num);
					temp = Float.parseFloat(temp_num);
				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};
		// 设置顶部ToggButton按钮显示顺序逻辑
		tg_room_state.setChecked(true);
		tg_room_conture
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (isChecked) {
							tg_room_conture.setChecked(true);
							tg_room_link.setChecked(false);
							tg_room_temp.setChecked(false);
							tg_room_state.setChecked(false);
						}
					}
				});
		tg_room_link.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					tg_room_conture.setChecked(false);
					tg_room_link.setChecked(true);
					tg_room_temp.setChecked(false);
					tg_room_state.setChecked(false);
				}
			}
		});
		tg_room_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					tg_room_conture.setChecked(false);
					tg_room_link.setChecked(false);
					tg_room_temp.setChecked(false);
					tg_room_state.setChecked(true);
				}
			}
		});
		tg_room_temp.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					tg_room_conture.setChecked(false);
					tg_room_link.setChecked(false);
					tg_room_temp.setChecked(true);
					tg_room_state.setChecked(false);
				}
			}
		});

		// 8201房间状态
		Cursor cursor = db.rawQuery("select * from room where roomnumber = ?",
				new String[] { "8201" });
		if (cursor.getCount() != 0) {
			cursor.moveToLast();
			state = cursor.getString(cursor.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8201.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8201.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8201.setBackgroundColor(Color.GRAY);
			} else {
				line_8201.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8201.setBackgroundColor(Color.GREEN);
		}

		// 8202房间状态
		Cursor cursor1 = db.rawQuery("select * from room where roomnumber = ?",
				new String[] { "8202" });
		if (cursor1.getCount() != 0) {
			cursor1.moveToLast();
			state = cursor1.getString(cursor1.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8202.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8202.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8202.setBackgroundColor(Color.GRAY);
			} else {
				line_8202.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8202.setBackgroundColor(Color.GREEN);
		}

		// 8203房间状态
		Cursor cursor11 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8203" });
		if (cursor11.getCount() != 0) {
			cursor11.moveToLast();
			state = cursor11.getString(cursor11.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8203.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8203.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8203.setBackgroundColor(Color.GRAY);
			} else {
				line_8203.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8203.setBackgroundColor(Color.GREEN);
		}

		// 8204房间状态
		Cursor cursor111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8204" });
		if (cursor111.getCount() != 0) {
			cursor111.moveToLast();
			state = cursor111.getString(cursor111.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8204.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8204.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8204.setBackgroundColor(Color.GRAY);
			} else {
				line_8204.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8204.setBackgroundColor(Color.GREEN);
		}

		// 8101房间状态
		Cursor cursor1111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8101" });
		if (cursor1111.getCount() != 0) {
			cursor1111.moveToLast();
			state = cursor1111
					.getString(cursor1111.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8101.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8101.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8101.setBackgroundColor(Color.GRAY);
			} else {
				line_8101.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8101.setBackgroundColor(Color.GREEN);
		}

		// 8102房间状态
		Cursor cursor11111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8102" });
		if (cursor11111.getCount() != 0) {
			cursor11111.moveToLast();
			state = cursor11111.getString(
					cursor11111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8102.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8102.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8102.setBackgroundColor(Color.GRAY);
			} else {
				line_8102.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8102.setBackgroundColor(Color.GREEN);
		}

		// 8103房间状态
		Cursor cursor111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8103" });
		if (cursor111111.getCount() != 0) {
			cursor111111.moveToLast();
			state = cursor111111.getString(
					cursor111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8103.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8103.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8103.setBackgroundColor(Color.GRAY);
			} else {
				line_8103.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8103.setBackgroundColor(Color.GREEN);
		}

		// 8103房间状态
		Cursor cursor1111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8104" });
		if (cursor1111111.getCount() != 0) {
			cursor1111111.moveToLast();
			state = cursor1111111.getString(
					cursor1111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8104.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8104.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8104.setBackgroundColor(Color.GRAY);
			} else {
				line_8104.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8104.setBackgroundColor(Color.GREEN);
		}

		// 8301房间状态
		Cursor cursor11111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8301" });
		if (cursor11111111.getCount() != 0) {
			cursor11111111.moveToLast();
			state = cursor11111111.getString(
					cursor11111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8301.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8301.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8301.setBackgroundColor(Color.GRAY);
			} else {
				line_8301.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8301.setBackgroundColor(Color.GREEN);
		}

		// 8302房间状态
		Cursor cursor111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8302" });
		if (cursor111111111.getCount() != 0) {
			cursor111111111.moveToLast();
			state = cursor111111111.getString(
					cursor111111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8302.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8302.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8302.setBackgroundColor(Color.GRAY);
			} else {
				line_8302.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8302.setBackgroundColor(Color.GREEN);
		}

		// 8303房间状态
		Cursor cursor1111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8303" });
		if (cursor1111111111.getCount() != 0) {
			cursor1111111111.moveToLast();
			state = cursor1111111111.getString(
					cursor1111111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8303.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8303.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8303.setBackgroundColor(Color.GRAY);
			} else {
				line_8303.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8303.setBackgroundColor(Color.GREEN);
		}

		// 8304房间状态
		Cursor cursor11111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8304" });
		if (cursor11111111111.getCount() != 0) {
			cursor11111111111.moveToLast();
			state = cursor11111111111.getString(
					cursor11111111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8304.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8304.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8304.setBackgroundColor(Color.GRAY);
			} else {
				line_8304.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8204.setBackgroundColor(Color.GREEN);
		}

		// 8401房间状态
		Cursor cursor111111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8401" });
		if (cursor111111111111.getCount() != 0) {
			cursor111111111111.moveToLast();
			state = cursor111111111111.getString(
					cursor111111111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8401.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8401.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8401.setBackgroundColor(Color.GRAY);
			} else {
				line_8401.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8401.setBackgroundColor(Color.GREEN);
		}

		// 8402房间状态
		Cursor cursor1111111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8402" });
		if (cursor1111111111111.getCount() != 0) {
			cursor1111111111111.moveToLast();
			state = cursor1111111111111.getString(
					cursor1111111111111.getColumnIndex("roomstate")).toString();
			if (state.equals("已入住")) {
				line_8402.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8402.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8402.setBackgroundColor(Color.GRAY);
			} else {
				line_8402.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8402.setBackgroundColor(Color.GREEN);
		}

		// 8403房间状态
		Cursor cursor11111111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8403" });
		if (cursor11111111111111.getCount() != 0) {
			cursor11111111111111.moveToLast();
			state = cursor11111111111111.getString(
					cursor11111111111111.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8403.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8403.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8403.setBackgroundColor(Color.GRAY);
			} else {
				line_8403.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8403.setBackgroundColor(Color.GREEN);
		}

		// 8404房间状态
		Cursor cursor111111111111111 = db.rawQuery(
				"select * from room where roomnumber = ?",
				new String[] { "8404" });
		if (cursor111111111111111.getCount() != 0) {
			cursor111111111111111.moveToLast();
			state = cursor111111111111111.getString(
					cursor111111111111111.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("已入住")) {
				line_8404.setBackgroundColor(Color.RED);
			} else if (state.equals("未入住")) {
				line_8404.setBackgroundColor(Color.GREEN);
			} else if (state.equals("未打扫")) {
				line_8404.setBackgroundColor(Color.GRAY);
			} else {
				line_8404.setBackgroundColor(Color.GREEN);
			}
		} else {
			line_8404.setBackgroundColor(Color.GREEN);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			room_number = "8101";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8102:
			room_number = "8102";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8103:
			room_number = "8103";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8104:
			room_number = "8104";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8201:
			room_number = "8201";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8202:
			room_number = "8202";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8203:
			room_number = "8203";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8204:
			room_number = "8204";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8301:
			room_number = "8301";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8302:
			room_number = "8302";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8303:
			room_number = "8303";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8304:
			room_number = "8304";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8401:
			room_number = "8401";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8402:
			room_number = "8402";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8403:
			room_number = "8403";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8404:
			room_number = "8404";
			if (tg_room_state.isChecked()) {
				showTitleMessage();
			}
			if (tg_room_link.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						LinkActivity.class);
				startActivity(intent);
			}
			if (tg_room_conture.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						KongzhiActivity.class);
				startActivity(intent);
			}
			if (tg_room_temp.isChecked()) {
				Intent intent = new Intent(MenuActivity.this,
						DrawActivity.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}

	}

	private void network() {
		SocketThread.mHandlerSocketState = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				Bundle b = msg.getData();
				if (count == 1) {
					if (b.getString("SocketThread_State") == "error") {
						// Toast.makeText(MenuActivity.this, "组网失败",
						// Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(MenuActivity.this, "组网成功",
								Toast.LENGTH_SHORT).show();
						count = 0;
					}
				}
			};
		};

	}

	private void showTitleMessage() {
		final AlertDialog dlg = new AlertDialog.Builder(this).create();
		dlg.show();
		Window window = dlg.getWindow();
		// 主要就是在这里实现这种效果
		// 设置窗口的内容页面,shrew_exit_dialog.xml文件中定义view内容
		window.setContentView(R.layout.show_title);
		final Button btn_room_state_yiruzhu = (Button) window
				.findViewById(R.id.btn_room_yiruzhu);
		final Button btn_room_state_weidasao = (Button) window
				.findViewById(R.id.btn_room_weidasao);
		final Button btn_room_state_weiyiruzhu = (Button) window
				.findViewById(R.id.btn_room_weiruzhu);
		final TextView tv_ill = (TextView) window.findViewById(R.id.tv_ill);
		final TextView tv_co = (TextView) window.findViewById(R.id.tv_co);
		final TextView tv_pm = (TextView) window.findViewById(R.id.tv_pm);
		final TextView tv_hum = (TextView) window.findViewById(R.id.tv_hum);
		final TextView tv_temp = (TextView) window.findViewById(R.id.tv_temp);
		final TextView tv_gas = (TextView) window.findViewById(R.id.tv_gas);
		final TextView tv_smo = (TextView) window.findViewById(R.id.tv_smo);
		final TextView tv_press = (TextView) window.findViewById(R.id.tv_press);
		final TextView tv_per = (TextView) window.findViewById(R.id.tv_per);
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		final TextView tv_num = (TextView) window
				.findViewById(R.id.tv_title_roomnumber);
		tv_num.setText("房间号：" + room_number);
		UpdataThread = new Thread(new Updata_activity());
		UpdataThread.start();
		Updata_activity.updatahandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				try {
					Js.receive();
					tv_co.setText(Js.receive_data.get(Json_data.Co2).toString());
					tv_gas.setText(Js.receive_data.get(Json_data.Gas)
							.toString());
					tv_hum.setText(Js.receive_data.get(Json_data.Humidity)
							.toString());
					tv_ill.setText(Js.receive_data.get(Json_data.Illumination)
							.toString());
					tv_pm.setText(Js.receive_data.get(Json_data.PM25)
							.toString());
					tv_press.setText(Js.receive_data.get(Json_data.AirPressure)
							.toString());
					tv_smo.setText(Js.receive_data.get(Json_data.Smoke)
							.toString());
					tv_temp.setText(Js.receive_data.get(Json_data.Temp)
							.toString());
					if (Js.receive_data.get(Json_data.StateHumanInfrared)
							.toString().equals("1")) {
						tv_per.setText("有人");
					} else {
						tv_per.setText("无人");
					}
				} catch (JSONException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		};
		btn_room_state_yiruzhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tv_num.getText().toString().equals("房间号：8101")) {
					line_8101.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8101", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8102")) {
					line_8102.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8102", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8103")) {
					line_8103.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8103", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8104")) {
					line_8104.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8104", "已入住" });
				}

				if (tv_num.getText().toString().equals("房间号：8201")) {
					line_8201.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8201", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8202")) {
					line_8202.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8202", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8203")) {
					line_8203.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8203", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8204")) {
					line_8204.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8204", "已入住" });
				}

				if (tv_num.getText().toString().equals("房间号：8301")) {
					line_8301.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8301", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8302")) {
					line_8302.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8302", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8303")) {
					line_8303.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8303", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8304")) {
					line_8304.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8304", "已入住" });
				}

				if (tv_num.getText().toString().equals("房间号：8401")) {
					line_8401.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8401", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8402")) {
					line_8402.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8402", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8403")) {
					line_8403.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8403", "已入住" });
				} else if (tv_num.getText().toString().equals("房间号：8404")) {
					line_8404.setBackgroundColor(Color.RED);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8404", "已入住" });
				}

			}
		});

		btn_room_state_weiyiruzhu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tv_num.getText().toString().equals("房间号：8101")) {
					line_8101.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8101", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8102")) {
					line_8102.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8102", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8103")) {
					line_8103.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8103", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8104")) {
					line_8104.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8104", "未入住" });
				}

				if (tv_num.getText().toString().equals("房间号：8201")) {
					line_8201.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8201", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8202")) {
					line_8202.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8202", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8203")) {
					line_8203.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8203", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8204")) {
					line_8204.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8204", "未入住" });
				}

				if (tv_num.getText().toString().equals("房间号：8301")) {
					line_8301.setBackgroundColor(Color.GREEN);

					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8301", "未入住" });

				} else if (tv_num.getText().toString().equals("房间号：8302")) {
					line_8302.setBackgroundColor(Color.GREEN);

					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8302", "未入住" });

				} else if (tv_num.getText().toString().equals("房间号：8303")) {
					line_8303.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8303", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8304")) {
					line_8304.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8304", "未入住" });
				}

				if (tv_num.getText().toString().equals("房间号：8401")) {
					line_8401.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8401", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8402")) {
					line_8402.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8402", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8403")) {
					line_8403.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8403", "未入住" });
				} else if (tv_num.getText().toString().equals("房间号：8404")) {
					line_8404.setBackgroundColor(Color.GREEN);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8404", "未入住" });
				}
			}
		});
		btn_room_state_weidasao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tv_num.getText().toString().equals("房间号：8101")) {
					line_8101.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8101", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8102")) {
					line_8102.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8102", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8103")) {
					line_8103.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8103", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8104")) {
					line_8104.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8104", "未打扫" });
				}

				if (tv_num.getText().toString().equals("房间号：8201")) {
					line_8201.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8201", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8202")) {
					line_8202.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8202", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8203")) {
					line_8203.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8203", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8204")) {
					line_8204.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8204", "未打扫" });
				}

				if (tv_num.getText().toString().equals("房间号：8301")) {
					line_8301.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8301", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8302")) {
					line_8302.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8302", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8303")) {
					line_8303.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8303", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8304")) {
					line_8304.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8304", "未打扫" });
				}

				if (tv_num.getText().toString().equals("房间号：8401")) {
					line_8401.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8401", "未打扫" });
				} else if (tv_num.getText().toString().equals("房间号：8402")) {
					line_8402.setBackgroundColor(Color.GRAY);

					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8402", "未打扫" });

				} else if (tv_num.getText().toString().equals("房间号：8403")) {
					line_8403.setBackgroundColor(Color.GRAY);

					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8403", "未打扫" });

				} else if (tv_num.getText().toString().equals("房间号：8404")) {
					line_8404.setBackgroundColor(Color.GRAY);
					db.execSQL(
							"insert into room (roomnumber,roomstate)values(?,?)",
							new String[] { "8404", "未打扫" });
				}
			}
		});
		Button cancel = (Button) window.findViewById(R.id.btn_title_cls);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dlg.cancel();
			}
		});
	}
}