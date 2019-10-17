package com.example.guosaibdemo905;

import java.util.Random;

import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.LoginCallback;
import com.bizideal.smarthome.socket.SocketClient;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

/*
 * @文件名：IndexActivity.java
 * @描述：展示主页，房间控制、管理、联动、温度管理等跳转
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
 */
public class IndexActivity extends Activity implements OnClickListener {
	// 状态
	private boolean view_1_state = false;
	private boolean view_2_state = false;
	private boolean view_3_state = false;
	private boolean view_4_state = false;
	// float
	public static float temp, hum, ill, co, pm, per, smo, press;
	// 随机数
	private Random random = new Random();
	// 数据库
	private SQLiteDatabase db;
	private MyDataBaseHelper dbHelper;
	// 按钮
	private Button btn_room_guanli;// 房间管理
	private Button btn_room_kongzhi;// 房间控制
	private Button btn_room_linkstate;// 联动管理
	private Button btn_room_tempstate;// 温度趋势
	// View
	private View view_1;
	private View view_2;
	private View view_3;
	private View view_4;
	// 房间号
	public static String room_getnumber;
	// Line
	private LinearLayout line_8101;
	private LinearLayout line_8102;
	private LinearLayout line_8103;
	private LinearLayout line_8104;
	private LinearLayout line_8201;
	private LinearLayout line_8202;
	private LinearLayout line_8203;
	private LinearLayout line_8204;
	private LinearLayout line_8301;
	private LinearLayout line_8302;
	private LinearLayout line_8303;
	private LinearLayout line_8304;
	private LinearLayout line_8401;
	private LinearLayout line_8402;
	private LinearLayout line_8403;
	private LinearLayout line_8404;
	// 网络连接状态
	private boolean web_state = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		setTitle("前台管理");
		// line
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
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();
		// 按钮
		btn_room_guanli = (Button) findViewById(R.id.btn_room_guanli);
		btn_room_kongzhi = (Button) findViewById(R.id.btn_room_kongzhi);
		btn_room_linkstate = (Button) findViewById(R.id.btn_room_linkstate);
		btn_room_tempstate = (Button) findViewById(R.id.btn_room_tempstate);
		// View
		view_1 = (View) findViewById(R.id.view_1);
		view_2 = (View) findViewById(R.id.view_2);
		view_3 = (View) findViewById(R.id.view_3);
		view_4 = (View) findViewById(R.id.view_4);
		// 设置按钮点击事件、View背景
		view_1.setBackgroundColor(Color.BLUE);
		view_2.setBackgroundColor(Color.WHITE);
		view_3.setBackgroundColor(Color.WHITE);
		view_4.setBackgroundColor(Color.WHITE);
		// 房间管理
		btn_room_guanli.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setBackgroundColor(Color.BLUE);
				view_2.setBackgroundColor(Color.WHITE);
				view_3.setBackgroundColor(Color.WHITE);
				view_4.setBackgroundColor(Color.WHITE);
				view_1_state = true;
				view_2_state = false;
				view_3_state = false;
				view_4_state = false;
			}
		});
		// 房间控制
		btn_room_kongzhi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setBackgroundColor(Color.WHITE);
				view_2.setBackgroundColor(Color.BLUE);
				view_3.setBackgroundColor(Color.WHITE);
				view_4.setBackgroundColor(Color.WHITE);
				view_1_state = false;
				view_2_state = true;
				view_3_state = false;
				view_4_state = false;
			}
		});
		// 联动管理
		btn_room_linkstate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setBackgroundColor(Color.WHITE);
				view_2.setBackgroundColor(Color.WHITE);
				view_3.setBackgroundColor(Color.BLUE);
				view_4.setBackgroundColor(Color.WHITE);
				view_1_state = false;
				view_2_state = false;
				view_3_state = true;
				view_4_state = false;
			}
		});
		// 温度趋势
		btn_room_tempstate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_1.setBackgroundColor(Color.WHITE);
				view_2.setBackgroundColor(Color.WHITE);
				view_3.setBackgroundColor(Color.WHITE);
				view_4.setBackgroundColor(Color.BLUE);
				view_1_state = false;
				view_2_state = false;
				view_3_state = false;
				view_4_state = true;
			}
		});
		// 设置Line点击事件
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
		// 激活进程
		handler.post(timeRunnable);
		// 设置数据库、设定APP开启房间状态
		// 1-已入住
		// 2-未打扫
		// 3-可入住
		// 8101
		Cursor cursor8101 = db.rawQuery("select * from room8101", null);
		if (cursor8101.getCount() != 0) {
			cursor8101.moveToLast();
			if (cursor8101.getString(cursor8101.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8101.setBackgroundColor(Color.RED);
			}
			if (cursor8101.getString(cursor8101.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8101.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8101.getString(cursor8101.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8101.setBackgroundColor(Color.GREEN);
			}
		}

		// 8102
		Cursor cursor8102 = db.rawQuery("select * from room8102", null);
		if (cursor8102.getCount() != 0) {
			cursor8102.moveToLast();
			if (cursor8102.getString(cursor8102.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8102.setBackgroundColor(Color.RED);
			}
			if (cursor8102.getString(cursor8102.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8102.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8102.getString(cursor8102.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8102.setBackgroundColor(Color.GREEN);
			}
		}
		// 8103
		Cursor cursor8103 = db.rawQuery("select * from room8103", null);
		if (cursor8103.getCount() != 0) {
			cursor8103.moveToLast();
			if (cursor8103.getString(cursor8103.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8103.setBackgroundColor(Color.RED);
			}
			if (cursor8103.getString(cursor8103.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8103.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8103.getString(cursor8103.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8103.setBackgroundColor(Color.GREEN);
			}
		}
		// 8104
		Cursor cursor8104 = db.rawQuery("select * from room8104", null);
		if (cursor8104.getCount() != 0) {
			cursor8104.moveToLast();
			if (cursor8104.getString(cursor8104.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8104.setBackgroundColor(Color.RED);
			}
			if (cursor8104.getString(cursor8104.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8104.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8104.getString(cursor8104.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8104.setBackgroundColor(Color.GREEN);
			}
		}

		// 8201
		Cursor cursor8201 = db.rawQuery("select * from room8201", null);
		if (cursor8201.getCount() != 0) {
			cursor8201.moveToLast();
			if (cursor8201.getString(cursor8201.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8201.setBackgroundColor(Color.RED);
			}
			if (cursor8201.getString(cursor8201.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8201.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8201.getString(cursor8201.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8201.setBackgroundColor(Color.GREEN);
			}
		}

		// 8202
		Cursor cursor8202 = db.rawQuery("select * from room8202", null);
		if (cursor8202.getCount() != 0) {
			cursor8202.moveToLast();
			if (cursor8202.getString(cursor8202.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8202.setBackgroundColor(Color.RED);
			}
			if (cursor8202.getString(cursor8202.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8202.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8202.getString(cursor8202.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8202.setBackgroundColor(Color.GREEN);
			}
		}
		// 8203
		Cursor cursor8203 = db.rawQuery("select * from room8203", null);
		if (cursor8203.getCount() != 0) {
			cursor8203.moveToLast();
			if (cursor8203.getString(cursor8203.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8203.setBackgroundColor(Color.RED);
			}
			if (cursor8203.getString(cursor8203.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8203.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8203.getString(cursor8203.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8203.setBackgroundColor(Color.GREEN);
			}
		}
		// 8204
		Cursor cursor8204 = db.rawQuery("select * from room8204", null);
		if (cursor8204.getCount() != 0) {
			cursor8204.moveToLast();
			if (cursor8204.getString(cursor8204.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8204.setBackgroundColor(Color.RED);
			}
			if (cursor8204.getString(cursor8204.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8204.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8204.getString(cursor8204.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8204.setBackgroundColor(Color.GREEN);
			}
		}

		// 8301
		Cursor cursor8301 = db.rawQuery("select * from room8301", null);
		if (cursor8301.getCount() != 0) {
			cursor8301.moveToLast();
			if (cursor8301.getString(cursor8301.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8301.setBackgroundColor(Color.RED);
			}
			if (cursor8301.getString(cursor8301.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8301.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8301.getString(cursor8301.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8301.setBackgroundColor(Color.GREEN);
			}
		}

		// 8302
		Cursor cursor8302 = db.rawQuery("select * from room8302", null);
		if (cursor8302.getCount() != 0) {
			cursor8302.moveToLast();
			if (cursor8302.getString(cursor8302.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8302.setBackgroundColor(Color.RED);
			}
			if (cursor8302.getString(cursor8302.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8302.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8302.getString(cursor8302.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8302.setBackgroundColor(Color.GREEN);
			}
		}
		// 8303
		Cursor cursor8303 = db.rawQuery("select * from room8303", null);
		if (cursor8303.getCount() != 0) {
			cursor8303.moveToLast();
			if (cursor8303.getString(cursor8303.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8303.setBackgroundColor(Color.RED);
			}
			if (cursor8303.getString(cursor8303.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8303.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8303.getString(cursor8303.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8303.setBackgroundColor(Color.GREEN);
			}
		}
		// 8304
		Cursor cursor8304 = db.rawQuery("select * from room8304", null);
		if (cursor8304.getCount() != 0) {
			cursor8304.moveToLast();
			if (cursor8304.getString(cursor8304.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8304.setBackgroundColor(Color.RED);
			}
			if (cursor8304.getString(cursor8304.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8304.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8304.getString(cursor8304.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8304.setBackgroundColor(Color.GREEN);
			}
		}

		// 8401
		Cursor cursor8401 = db.rawQuery("select * from room8401", null);
		if (cursor8401.getCount() != 0) {
			cursor8401.moveToLast();
			if (cursor8401.getString(cursor8401.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8401.setBackgroundColor(Color.RED);
			}
			if (cursor8401.getString(cursor8401.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8401.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8401.getString(cursor8401.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8401.setBackgroundColor(Color.GREEN);
			}
		}

		// 8402
		Cursor cursor8402 = db.rawQuery("select * from room8402", null);
		if (cursor8402.getCount() != 0) {
			cursor8402.moveToLast();
			if (cursor8402.getString(cursor8402.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8402.setBackgroundColor(Color.RED);
			}
			if (cursor8402.getString(cursor8402.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8402.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8402.getString(cursor8402.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8402.setBackgroundColor(Color.GREEN);
			}
		}
		// 8403
		Cursor cursor8403 = db.rawQuery("select * from room8403", null);
		if (cursor8403.getCount() != 0) {
			cursor8403.moveToLast();
			if (cursor8403.getString(cursor8403.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8403.setBackgroundColor(Color.RED);
			}
			if (cursor8403.getString(cursor8403.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8403.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8403.getString(cursor8403.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8403.setBackgroundColor(Color.GREEN);
			}
		}
		// 8404
		Cursor cursor8404 = db.rawQuery("select * from room8404", null);
		if (cursor8404.getCount() != 0) {
			cursor8404.moveToLast();
			if (cursor8404.getString(cursor8404.getColumnIndex("roomstate"))
					.toString().equals("1")) {// 已入住
				line_8404.setBackgroundColor(Color.RED);
			}
			if (cursor8404.getString(cursor8404.getColumnIndex("roomstate"))
					.toString().equals("2")) {// 未打扫
				line_8404.setBackgroundColor(R.drawable.line_back696969);
			}
			if (cursor8404.getString(cursor8404.getColumnIndex("roomstate"))
					.toString().equals("3")) {// 可入住
				line_8404.setBackgroundColor(Color.GREEN);
			}
		}

	}

	/*
	 * @方法名：onCLick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午8:52:49
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			room_getnumber = "8101";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8102:
			room_getnumber = "8102";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8103:
			room_getnumber = "8103";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8104:
			room_getnumber = "8104";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8201:
			room_getnumber = "8201";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8202:
			room_getnumber = "8202";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8203:
			room_getnumber = "8203";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8204:
			room_getnumber = "8204";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8401:
			room_getnumber = "8401";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8402:
			room_getnumber = "8402";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8403:
			room_getnumber = "8403";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8404:
			room_getnumber = "8404";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;

		case R.id.line_8301:
			room_getnumber = "8301";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8302:
			room_getnumber = "8302";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8303:
			room_getnumber = "8303";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8304:
			room_getnumber = "8304";
			if (view_1_state) {
				showTitle();
			}
			if (view_2_state) {
				Intent intent = new Intent(IndexActivity.this,
						RoomControl.class);
				startActivity(intent);
			}
			if (view_3_state) {
				Intent intent = new Intent(IndexActivity.this, RoomLink.class);
				startActivity(intent);
			}
			if (view_4_state) {
				Intent intent = new Intent(IndexActivity.this, RoomTemp.class);
				startActivity(intent);
			}
			break;

		default:
			break;
		}
	}

	/*
	 * @方法名：showtitle
	 * 
	 * @功 能：展示自定义Dialog
	 * 
	 * @时 间：上午8:53:12
	 */
	private void showTitle() {
		AlertDialog.Builder builder = new AlertDialog.Builder(
				IndexActivity.this);
		builder.setTitle("房间管理");// 设置标题
		// 设置按钮
		builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		LayoutInflater layoutInflater = LayoutInflater.from(IndexActivity.this);
		View view = layoutInflater.inflate(R.layout.activity_show_title, null,
				false);
		builder.setView(view);
		// 房间号
		final TextView tv_room_state;
		tv_room_state = (TextView) view.findViewById(R.id.tv_room_number);
		tv_room_state.setText("房间号：" + room_getnumber);
		// 按钮
		final Button btn_yiruzhu;
		final Button btn_weidasao;
		final Button btn_weiruzhu;
		btn_weidasao = (Button) view.findViewById(R.id.btn_weidasao);
		btn_weiruzhu = (Button) view.findViewById(R.id.btn_weiruzhu);
		btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room" + room_getnumber
						+ " (roomstate)values(?)", new String[] { "2" });
				if (room_getnumber.equals("8101")) {
					line_8101.setBackgroundColor(R.drawable.line_back696969);
					System.out.println("背景设置");
				}
				if (room_getnumber.equals("8102")) {
					line_8102.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8103")) {
					line_8103.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8104")) {
					line_8104.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8201")) {
					line_8201.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8202")) {
					line_8202.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8203")) {
					line_8203.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8204")) {
					line_8204.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8301")) {
					line_8301.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8302")) {
					line_8302.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8303")) {
					line_8303.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8304")) {
					line_8304.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8401")) {
					line_8401.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8402")) {
					line_8402.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8403")) {
					line_8403.setBackgroundColor(R.drawable.line_back696969);
				}
				if (room_getnumber.equals("8404")) {
					line_8404.setBackgroundColor(R.drawable.line_back696969);
				}
			}
		});
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room" + room_getnumber
						+ " (roomstate)values(?)", new String[] { "1" });
				if (room_getnumber.equals("8101")) {
					System.out.println("背景设置");
					line_8101.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8102")) {
					line_8102.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8103")) {
					line_8103.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8104")) {
					line_8104.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8201")) {
					line_8201.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8202")) {
					line_8202.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8203")) {
					line_8203.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8204")) {
					line_8204.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8301")) {
					line_8301.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8302")) {
					line_8302.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8303")) {
					line_8303.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8304")) {
					line_8304.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8401")) {
					line_8401.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8402")) {
					line_8402.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8403")) {
					line_8403.setBackgroundColor(Color.RED);
				}
				if (room_getnumber.equals("8404")) {
					line_8404.setBackgroundColor(Color.RED);
				}
			}
		});
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				db.execSQL("insert into room" + room_getnumber
						+ " (roomstate)values(?)", new String[] { "3" });
				if (room_getnumber.equals("8101")) {
					line_8101.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8102")) {
					line_8102.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8103")) {
					line_8103.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8104")) {
					line_8104.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8201")) {
					line_8201.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8202")) {
					line_8202.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8203")) {
					line_8203.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8204")) {
					line_8204.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8301")) {
					line_8301.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8302")) {
					line_8302.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8303")) {
					line_8303.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8304")) {
					line_8304.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8401")) {
					line_8401.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8402")) {
					line_8402.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8403")) {
					line_8403.setBackgroundColor(Color.GREEN);
				}
				if (room_getnumber.equals("8404")) {
					line_8404.setBackgroundColor(Color.GREEN);
				}
			}
		});
		builder.show();
	}

	/*
	 * @方法名：handler
	 * 
	 * @功 能：网络连接
	 * 
	 * @时 间：上午8:58:24
	 */
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 10));
			hum = Float.valueOf(random.nextInt(40) % (40 - 10));
			ill = Float.valueOf(random.nextInt(40) % (40 - 10));
			co = Float.valueOf(random.nextInt(40) % (40 - 10));
			pm = Float.valueOf(random.nextInt(40) % (40 - 10));
			per = Float.valueOf(random.nextInt(40) % (40 - 10));
			smo = Float.valueOf(random.nextInt(40) % (40 - 10));
			press = Float.valueOf(random.nextInt(40) % (40 - 10));
			System.out.println(temp);
			ControlUtils.setUser("bizideal", "123456", MainActivity.ip);
			SocketClient.getInstance().creatConnect();
			SocketClient.getInstance().login(new LoginCallback() {

				@Override
				public void onEvent(final String link_state) {
					// TODO Auto-generated method stub
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							if (link_state.equals("Success")) {
								DiyToast.showToast(IndexActivity.this, "网络连接成功");
								web_state = false;
							} else {
							}
						}
					});
				}
			});
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
