package com.example.guosaibdemo903;

import java.text.DecimalFormat;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bizideal.smarthome.socket.ConstantUtil;
import com.bizideal.smarthome.socket.ControlUtils;
import com.bizideal.smarthome.socket.DataCallback;
import com.bizideal.smarthome.socket.DeviceBean;
import com.bizideal.smarthome.socket.SocketClient;

/*
 * @文件名：IndexActivity.java
 * @描述：程序主页，显示房间状态、跳转其他功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-3
 */
public class IndexActivity extends Activity implements OnClickListener {
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;

	// 随机数
	private Random random = new Random();
	// boolean
	private boolean state_1 = false;
	private boolean state_2 = false;
	private boolean state_3 = false;
	private boolean state_4 = false;
	// View
	private View view_1;
	private View view_2;
	private View view_3;
	private View view_4;
	// Line布局
	// 810x
	private LinearLayout line_8101;
	private LinearLayout line_8102;
	private LinearLayout line_8103;
	private LinearLayout line_8104;
	// 820x
	private LinearLayout line_8201;
	private LinearLayout line_8202;
	private LinearLayout line_8203;
	private LinearLayout line_8204;
	// 830x
	private LinearLayout line_8301;
	private LinearLayout line_8302;
	private LinearLayout line_8303;
	private LinearLayout line_8304;
	// 840x
	private LinearLayout line_8401;
	private LinearLayout line_8402;
	private LinearLayout line_8403;
	private LinearLayout line_8404;
	// 房间号
	static String room_number;

	// 按钮
	private Button btn_room_guanli;// 房间管理
	private Button btn_room_kongzhi;// 房间控制
	private Button btn_link_state;// 联动管理
	private Button btn_temp_state;// 温度趋势

	// float
	static float ill, co, hum, pm, temp, gas, smo, per, press;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);
		// 810x
		line_8101 = (LinearLayout) findViewById(R.id.line_8101);
		line_8102 = (LinearLayout) findViewById(R.id.line_8102);
		line_8103 = (LinearLayout) findViewById(R.id.line_8103);
		line_8104 = (LinearLayout) findViewById(R.id.line_8104);
		// 820x
		line_8201 = (LinearLayout) findViewById(R.id.line_8201);
		line_8202 = (LinearLayout) findViewById(R.id.line_8202);
		line_8203 = (LinearLayout) findViewById(R.id.line_8203);
		line_8204 = (LinearLayout) findViewById(R.id.line_8204);
		// 830x
		line_8301 = (LinearLayout) findViewById(R.id.line_8301);
		line_8302 = (LinearLayout) findViewById(R.id.line_8302);
		line_8303 = (LinearLayout) findViewById(R.id.line_8303);
		line_8304 = (LinearLayout) findViewById(R.id.line_8304);
		// 840x
		line_8401 = (LinearLayout) findViewById(R.id.line_8401);
		line_8402 = (LinearLayout) findViewById(R.id.line_8402);
		line_8403 = (LinearLayout) findViewById(R.id.line_8403);
		line_8404 = (LinearLayout) findViewById(R.id.line_8404);
		// 数据库
		dbHelper = new MyDataBaseHelper(this, "info.db", null, 2);
		db = dbHelper.getWritableDatabase();

		// 设置line点击事件
		// 810x
		line_8101.setOnClickListener(this);
		line_8102.setOnClickListener(this);
		line_8103.setOnClickListener(this);
		line_8104.setOnClickListener(this);
		// 820x
		line_8201.setOnClickListener(this);
		line_8202.setOnClickListener(this);
		line_8203.setOnClickListener(this);
		line_8204.setOnClickListener(this);
		// 830x
		line_8301.setOnClickListener(this);
		line_8302.setOnClickListener(this);
		line_8303.setOnClickListener(this);
		line_8304.setOnClickListener(this);
		// 840x
		line_8401.setOnClickListener(this);
		line_8402.setOnClickListener(this);
		line_8403.setOnClickListener(this);
		line_8404.setOnClickListener(this);

		// View
		view_1 = (View) findViewById(R.id.view_1);
		view_2 = (View) findViewById(R.id.view_2);
		view_3 = (View) findViewById(R.id.view_3);
		view_4 = (View) findViewById(R.id.view_4);

		// 激活进程
		handler.post(timeRunnable);

		// 按钮
		btn_link_state = (Button) findViewById(R.id.btn_link_state);
		btn_room_guanli = (Button) findViewById(R.id.btn_room_guanli);
		btn_room_kongzhi = (Button) findViewById(R.id.btn_room_kongzhi);
		btn_temp_state = (Button) findViewById(R.id.btn_temp_state);

		// 设置按钮
		btn_link_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_3.setBackgroundColor(Color.BLUE);
				view_2.setBackgroundColor(Color.WHITE);
				view_1.setBackgroundColor(Color.WHITE);
				view_4.setBackgroundColor(Color.WHITE);
				state_1 = false;
				state_2 = false;
				state_3 = true;
				state_4 = false;
			}
		});
		// 设置按钮
		btn_room_guanli.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_3.setBackgroundColor(Color.WHITE);
				view_2.setBackgroundColor(Color.WHITE);
				view_1.setBackgroundColor(Color.BLUE);
				view_4.setBackgroundColor(Color.WHITE);
				state_1 = true;
				state_2 = false;
				state_3 = false;
				state_4 = false;
			}
		});
		// 设置按钮
		btn_room_kongzhi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_3.setBackgroundColor(Color.WHITE);
				view_2.setBackgroundColor(Color.BLUE);
				view_1.setBackgroundColor(Color.WHITE);
				view_4.setBackgroundColor(Color.WHITE);
				state_1 = false;
				state_2 = true;
				state_3 = false;
				state_4 = false;
			}
		});
		// 设置按钮
		btn_temp_state.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				view_3.setBackgroundColor(Color.WHITE);
				view_2.setBackgroundColor(Color.WHITE);
				view_1.setBackgroundColor(Color.WHITE);
				view_4.setBackgroundColor(Color.BLUE);
				state_1 = false;
				state_2 = false;
				state_3 = false;
				state_4 = true;
			}
		});

		// 开启程序检查8101房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8101 = db.rawQuery("select * from room8101", null);
		if (cursor8101.getCount() != 0) {
			cursor8101.moveToLast();
			String state;
			state = cursor8101
					.getString(cursor8101.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8101.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8101.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8101.setBackgroundColor(Color.GRAY);
			} else {
				line_8101.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8101.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8102房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8102 = db.rawQuery("select * from room8102", null);
		if (cursor8102.getCount() != 0) {
			cursor8102.moveToLast();
			String state;
			state = cursor8102
					.getString(cursor8102.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8102.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8102.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8102.setBackgroundColor(Color.GRAY);
			} else {
				line_8102.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8102.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8103房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8103 = db.rawQuery("select * from room8103", null);
		if (cursor8103.getCount() != 0) {
			cursor8103.moveToLast();
			String state;
			state = cursor8103
					.getString(cursor8103.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8103.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8103.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8103.setBackgroundColor(Color.GRAY);
			} else {
				line_8103.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8103.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8104房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8104 = db.rawQuery("select * from room8104", null);
		if (cursor8104.getCount() != 0) {
			cursor8104.moveToLast();
			String state;
			state = cursor8104
					.getString(cursor8104.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8104.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8104.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8104.setBackgroundColor(Color.GRAY);
			} else {
				line_8104.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8104.setBackgroundColor(Color.WHITE);
		}
		/**
		 * 
		 */

		// 开启程序检查8201房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8201 = db.rawQuery("select * from room8201", null);
		if (cursor8201.getCount() != 0) {
			cursor8201.moveToLast();
			String state;
			state = cursor8101
					.getString(cursor8201.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8201.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8201.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8201.setBackgroundColor(Color.GRAY);
			} else {
				line_8201.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8201.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8102房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8202 = db.rawQuery("select * from room8202", null);
		if (cursor8202.getCount() != 0) {
			cursor8202.moveToLast();
			String state;
			state = cursor8202
					.getString(cursor8202.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8202.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8202.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8202.setBackgroundColor(Color.GRAY);
			} else {
				line_8202.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8202.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8103房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8203 = db.rawQuery("select * from room8203", null);
		if (cursor8203.getCount() != 0) {
			cursor8203.moveToLast();
			String state;
			state = cursor8203
					.getString(cursor8203.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8203.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8203.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8203.setBackgroundColor(Color.GRAY);
			} else {
				line_8203.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8203.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8104房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8204 = db.rawQuery("select * from room8204", null);
		if (cursor8204.getCount() != 0) {
			cursor8204.moveToLast();
			String state;
			state = cursor8204
					.getString(cursor8204.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8204.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8204.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8204.setBackgroundColor(Color.GRAY);
			} else {
				line_8204.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8204.setBackgroundColor(Color.WHITE);
		}
		/**
		 * 
		 */

		// 开启程序检查8201房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8301 = db.rawQuery("select * from room8301", null);
		if (cursor8301.getCount() != 0) {
			cursor8301.moveToLast();
			String state;
			state = cursor8301
					.getString(cursor8301.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8301.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8301.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8301.setBackgroundColor(Color.GRAY);
			} else {
				line_8301.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8301.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8102房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8302 = db.rawQuery("select * from room8302", null);
		if (cursor8302.getCount() != 0) {
			cursor8302.moveToLast();
			String state;
			state = cursor8302
					.getString(cursor8302.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8302.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8302.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8302.setBackgroundColor(Color.GRAY);
			} else {
				line_8302.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8302.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8103房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8303 = db.rawQuery("select * from room8303", null);
		if (cursor8303.getCount() != 0) {
			cursor8303.moveToLast();
			String state;
			state = cursor8303
					.getString(cursor8303.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8303.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8303.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8303.setBackgroundColor(Color.GRAY);
			} else {
				line_8303.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8303.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8104房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8304 = db.rawQuery("select * from room8304", null);
		if (cursor8304.getCount() != 0) {
			cursor8304.moveToLast();
			String state;
			state = cursor8304
					.getString(cursor8304.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8304.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8304.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8304.setBackgroundColor(Color.GRAY);
			} else {
				line_8304.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8304.setBackgroundColor(Color.WHITE);
		}
		/**
		 * 
		 */
		// 开启程序检查8201房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8401 = db.rawQuery("select * from room8401", null);
		if (cursor8401.getCount() != 0) {
			cursor8401.moveToLast();
			String state;
			state = cursor8401
					.getString(cursor8401.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8401.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8401.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8401.setBackgroundColor(Color.GRAY);
			} else {
				line_8401.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8401.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8102房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8402 = db.rawQuery("select * from room8402", null);
		if (cursor8402.getCount() != 0) {
			cursor8402.moveToLast();
			String state;
			state = cursor8402
					.getString(cursor8402.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8402.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8402.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8402.setBackgroundColor(Color.GRAY);
			} else {
				line_8402.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8402.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8103房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8403 = db.rawQuery("select * from room8403", null);
		if (cursor8403.getCount() != 0) {
			cursor8403.moveToLast();
			String state;
			state = cursor8403
					.getString(cursor8403.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8403.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8403.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8403.setBackgroundColor(Color.GRAY);
			} else {
				line_8403.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8403.setBackgroundColor(Color.WHITE);
		}
		// 开启程序检查8104房间状态
		/*
		 * 插入数据库房间状态：1-可入住2-未打扫3-已入住
		 */
		Cursor cursor8404 = db.rawQuery("select * from room8404", null);
		if (cursor8404.getCount() != 0) {
			cursor8404.moveToLast();
			String state;
			state = cursor8404
					.getString(cursor8404.getColumnIndex("roomstate"))
					.toString();
			if (state.equals("3")) {
				line_8404.setBackgroundColor(Color.RED);
			} else if (state.equals("1")) {
				line_8404.setBackgroundColor(Color.GREEN);
			} else if (state.equals("2")) {
				line_8404.setBackgroundColor(Color.GRAY);
			} else {
				line_8404.setBackgroundColor(Color.WHITE);
			}
		} else {
			line_8404.setBackgroundColor(Color.WHITE);
		}

	}

	/*
	 * @方法名：onClick
	 * 
	 * @功 能：响应点击事件
	 * 
	 * @时 间：上午9:22:16
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.line_8101:
			room_number = "房间号：" + "8101";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8102:
			room_number = "房间号：" + "8102";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8103:
			room_number = "房间号：" + "8103";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8104:
			room_number = "房间号：" + "8104";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8201:
			room_number = "房间号：" + "8201";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8202:
			room_number = "房间号：" + "8202";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8203:
			room_number = "房间号：" + "8203";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8204:
			room_number = "房间号：" + "8204";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8301:
			room_number = "房间号：" + "8301";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8302:
			room_number = "房间号：" + "8302";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8303:
			room_number = "房间号：" + "8303";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8304:
			room_number = "房间号：" + "8304";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8401:
			room_number = "房间号：" + "8401";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8402:
			room_number = "房间号：" + "8402";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8403:
			room_number = "房间号：" + "8403";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;
		case R.id.line_8404:
			room_number = "房间号：" + "8404";
			if (state_1) {
				showdialog();
			}
			if (state_2) {
				Intent intent = new Intent(IndexActivity.this,
						RoomContuolActivity.class);
				startActivity(intent);
			}
			if (state_3) {
				Intent intent = new Intent(IndexActivity.this,
						RoomLinkActivity.class);
				startActivity(intent);
			}
			if (state_4) {
				Intent intent = new Intent(IndexActivity.this,
						RoomTempActivity.class);
				startActivity(intent);
			}
			break;

		default:
			break;
		}
	}

	// 自定义Dialog
	private void showdialog() {
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				IndexActivity.this);
		builder.setTitle("房间管理");
		LayoutInflater layoutInflater = LayoutInflater.from(IndexActivity.this);
		final View view = layoutInflater.inflate(R.layout.show_title, null,
				false);
		builder.setView(view);
		builder.setNegativeButton("关闭", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		final TextView tv_room_number;
		final Button btn_yiruzhu, btn_weiruzhu, btn_weidasao;
		btn_weidasao = (Button) view.findViewById(R.id.btn_weidasao);
		btn_weiruzhu = (Button) view.findViewById(R.id.btn_weruzhu);
		btn_yiruzhu = (Button) view.findViewById(R.id.btn_yiruzhu);
		tv_room_number = (TextView) view.findViewById(R.id.tv_room_number);
		tv_room_number.setText(room_number);
		// textview
		final TextView tv_ill, tv_co, tv_pm, tv_hum, tv_temp, tv_gas, tv_smo, tv_per, tv_press;
		tv_co = (TextView) view.findViewById(R.id.tv_co);
		tv_gas = (TextView) view.findViewById(R.id.tv_gas);
		tv_hum = (TextView) view.findViewById(R.id.tv_hum);
		tv_ill = (TextView) view.findViewById(R.id.tv_ill);
		tv_per = (TextView) view.findViewById(R.id.tv_per);
		tv_pm = (TextView) view.findViewById(R.id.tv_pm);
		tv_smo = (TextView) view.findViewById(R.id.tv_smo);
		tv_press = (TextView) view.findViewById(R.id.tv_press);
		tv_temp = (TextView) view.findViewById(R.id.tv_temp);
		tv_co.setText("Co2：" + String.valueOf(co));
		tv_gas.setText("燃气：" + String.valueOf(gas));
		tv_hum.setText("湿度：" + String.valueOf(hum));
		tv_ill.setText("光照：" + String.valueOf(ill));
		tv_pm.setText("PM2.5：" + String.valueOf(pm));
		tv_press.setText("气压：" + String.valueOf(press));
		tv_smo.setText("烟雾：" + String.valueOf(smo));
		tv_temp.setText("温度：" + String.valueOf(temp));

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
							// tv_press.setText(getdata.getAirPressure());
							// press = Float.parseFloat(tv_press.getText()
							// .toString());
						}
						if (!TextUtils.isEmpty(getdata.getCo2())) {// Co2
							// tv_co.setText(getdata.getCo2());
							// co =
							// Float.parseFloat(tv_co.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getGas())) {// 燃气
							// tv_gas.setText(getdata.getCo2());
							// gas =
							// Float.parseFloat(tv_gas.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getHumidity())) {// 湿度
							// tv_hum.setText(getdata.getHumidity());
							// hum =
							// Float.parseFloat(tv_hum.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getIllumination())) {// 光照
							// tv_ill.setText(getdata.getIllumination());
							// ill =
							// Float.parseFloat(tv_ill.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getPM25())) {// PM2.5
							// tv_pm.setText(getdata.getPM25());
							// pm =
							// Float.parseFloat(tv_pm.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getSmoke())) {// 烟雾
							// tv_smo.setText(getdata.getSmoke());
							// smo =
							// Float.parseFloat(tv_smo.getText().toString());
						}
						if (!TextUtils.isEmpty(getdata.getTemperature())) {// 温度
							// tv_temp.setText(getdata.getTemperature());
							// temp = Float.parseFloat(tv_temp.getText()
							// .toString());
						}
						if (!TextUtils.isEmpty(getdata.getStateHumanInfrared())) {// 人体红外
							if (getdata.getStateHumanInfrared().equals(
									ConstantUtil.CLOSE)) {
								tv_per.setText("无人");
								per = 0;
							} else {
								tv_per.setText("有人");
								per = 1;
							}
						}
					}
				});
			}
		});

		/**
		 * 
		 */
		// 设置可入住按钮
		btn_weiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/**
				 * 检测房间号、设置背景、插入数据库
				 */
				if (room_number == "房间号：8101") {
					line_8101.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8101 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8102") {
					line_8102.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8102 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8103") {
					line_8103.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8103 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8104") {
					line_8104.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8104 set roomstate = ?",
							new String[] { "1" });
				}

				if (room_number == "房间号：8201") {
					line_8201.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8201 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8202") {
					line_8202.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8202 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8203") {
					line_8203.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8203 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8204") {
					line_8204.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8204 set roomstate = ?",
							new String[] { "1" });
				}

				if (room_number == "房间号：8301") {
					line_8301.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8301 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8302") {
					line_8302.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8302 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8303") {
					line_8303.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8303 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8304") {
					line_8304.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8304 set roomstate = ?",
							new String[] { "1" });
				}

				if (room_number == "房间号：8401") {
					line_8401.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8401 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8402") {
					line_8402.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8402 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8403") {
					line_8403.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8403 set roomstate = ?",
							new String[] { "1" });
				}
				if (room_number == "房间号：8404") {
					line_8404.setBackgroundColor(Color.GREEN);
					db.execSQL("update room8404 set roomstate = ?",
							new String[] { "1" });
				}
			}
		});

		/**
		 * 
		 */

		// 设置已入住按钮
		btn_yiruzhu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/**
				 * 检测房间号、设置背景、插入数据库
				 */
				if (room_number == "房间号：8101") {
					line_8101.setBackgroundColor(Color.RED);
					db.execSQL("update room8101 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8102") {
					line_8102.setBackgroundColor(Color.RED);
					db.execSQL("update room8102 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8103") {
					line_8103.setBackgroundColor(Color.RED);
					db.execSQL("update room8103 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8104") {
					line_8104.setBackgroundColor(Color.RED);
					db.execSQL("update room8104 set roomstate = ?",
							new String[] { "3" });
				}

				if (room_number == "房间号：8201") {
					line_8201.setBackgroundColor(Color.RED);
					db.execSQL("update room8201 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8202") {
					line_8202.setBackgroundColor(Color.RED);
					db.execSQL("update room8202 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8203") {
					line_8203.setBackgroundColor(Color.RED);
					db.execSQL("update room8203 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8204") {
					line_8204.setBackgroundColor(Color.RED);
					db.execSQL("update room8204 set roomstate = ?",
							new String[] { "3" });
				}

				if (room_number == "房间号：8301") {
					line_8301.setBackgroundColor(Color.RED);
					db.execSQL("update room8301 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8302") {
					line_8302.setBackgroundColor(Color.RED);
					db.execSQL("update room8302 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8303") {
					line_8303.setBackgroundColor(Color.RED);
					db.execSQL("update room8303 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8304") {
					line_8304.setBackgroundColor(Color.RED);
					db.execSQL("update room8304 set roomstate = ?",
							new String[] { "3" });
				}

				if (room_number == "房间号：8401") {
					line_8401.setBackgroundColor(Color.RED);
					db.execSQL("update room8401 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8402") {
					line_8402.setBackgroundColor(Color.RED);
					db.execSQL("update room8402 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8403") {
					line_8403.setBackgroundColor(Color.RED);
					db.execSQL("update room8403 set roomstate = ?",
							new String[] { "3" });
				}
				if (room_number == "房间号：8404") {
					line_8404.setBackgroundColor(Color.RED);
					db.execSQL("update room8404 set roomstate = ?",
							new String[] { "3" });
				}
			}
		});

		/**
		 * 
		 */
		// 设置未打扫按钮
		btn_weidasao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/**
				 * 检测房间号、设置背景、插入数据库
				 */
				if (room_number == "房间号：8101") {
					line_8101.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8101 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8102") {
					line_8102.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8102 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8103") {
					line_8103.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8103 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8104") {
					line_8104.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8104 set roomstate = ?",
							new String[] { "2" });
				}

				if (room_number == "房间号：8201") {
					line_8201.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8201 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8202") {
					line_8202.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8202 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8203") {
					line_8203.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8203 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8204") {
					line_8204.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8204 set roomstate = ?",
							new String[] { "2" });
				}

				if (room_number == "房间号：8301") {
					line_8301.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8301 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8302") {
					line_8302.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8302 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8303") {
					line_8303.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8303 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8304") {
					line_8304.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8304 set roomstate = ?",
							new String[] { "2" });
				}

				if (room_number == "房间号：8401") {
					line_8401.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8401 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8402") {
					line_8402.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8402 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8403") {
					line_8403.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8403 set roomstate = ?",
							new String[] { "2" });
				}
				if (room_number == "房间号：8404") {
					line_8404.setBackgroundColor(Color.GRAY);
					db.execSQL("update room8404 set roomstate = ?",
							new String[] { "2" });
				}
			}
		});
		builder.show();
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			temp = Float.valueOf(random.nextInt(40) % (40 - 20 + 1));// 温度
			hum = Float.valueOf(random.nextInt(40) % (120 - 10 + 1));// 湿度
			gas = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 燃气
			smo = Float.valueOf(random.nextInt(40) % (40 - 10 + 1));// 烟雾
			ill = Float.valueOf(random.nextInt(9999) % (9999 - 500 + 1));// 光照
			co = Float.valueOf(random.nextInt(40) % (100 - 10 + 1));// Co2
			pm = Float.valueOf(random.nextInt(40) % (100 - 40 + 1));// pm2.5
			press = Float.valueOf(random.nextInt(1800) % (1800 - 10 + 1));// 气压
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