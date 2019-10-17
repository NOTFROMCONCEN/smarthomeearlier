package com.example.android2017guosaibzhexiantu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/*
 * @文件名：MyView.java
 * @描述：对用户
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-25
 * 
 * @author Administrator
 */
public class MyView extends View {
	public static HashMap<String, String> map;
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xheight = 40;
	private int Yheight = 40;
	private int Xline = 400;
	private int Yline = 200;
	private int num = 1;
	private List<String> Xlable = new ArrayList<String>();
	private String[] Ylable = new String[5];
	private List<Float> data = new ArrayList<Float>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (MainActivity.draw_state && MainActivity.getdata != 0) {
				if (msg.what == 0x1234) {
					MyView.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (number,data)values(?,?)",
							new String[] { MainActivity.number + num,
									String.valueOf(MainActivity.getdata) });
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		for (int i = 0; i < 5; i++) {
			Ylable[i] = String.valueOf(i * 20);
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {

					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (MainActivity.getdata != 0 && MainActivity.draw_state) {
						data.add(MainActivity.getdata);
						Xlable.add(MainActivity.number + num);
						map = new HashMap<String, String>();
						map.put("number", MainActivity.number + num);
						map.put("data", String.valueOf(MainActivity.getdata));
						list.add(map);
						num++;
					}
					if (data.size() >= 10) {
						data.remove(0);
						Xlable.remove(0);
						list.remove(0);
					}
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setStyle(Style.STROKE);
		paint.setTextSize(16);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		// 绘制X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制Y轴刻度文字
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 20, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 50, Ypoint - i * Yheight, paint);
		}
		if (MainActivity.getdata >= 100) {
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint - 20, Ypoint
					- 5 * Yheight, paint);
			canvas.drawText(String.valueOf(MainActivity.getdata), Xpoint - 50,
					Ypoint - 5 * Yheight, paint);
		}
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.size() <= 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
		}
	}
}