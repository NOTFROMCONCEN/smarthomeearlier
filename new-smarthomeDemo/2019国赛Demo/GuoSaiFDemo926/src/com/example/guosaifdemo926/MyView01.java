package com.example.guosaifdemo926;

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
 * @文件名：MyView01.java
 * @描述：折线图
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class MyView01 extends View {
	private int Xpoint = 560;
	private int Ypoint = 250;
	private int Xline = 300;
	private int Yline = 150;
	private int Xheight = 35;
	private int Yheight = 30;
	private int num = 1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();
	private String[] Ylable = new String[6];
	static HashMap<String, String> map = new HashMap<String, String>();
	static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (BaseActivity.getdata != 0) {
					MyView01.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (num,data)values(?,?)",
							new String[] { BaseActivity.num + num,
									Float.toString(BaseActivity.getdata) });
				}
			}
		}
	};

	public MyView01(Context context, AttributeSet attrs) {
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
					if (data.size() >= 7) {
						data.remove(0);
						Xlable.remove(0);
						list.remove(0);
					}
					if (BaseActivity.getdata != 0) {
						data.add(BaseActivity.getdata);
						Xlable.add(BaseActivity.num + num);
						map = new HashMap<String, String>();
						map.put("num", BaseActivity.num + num);
						map.put("data", Float.toString(BaseActivity.getdata));
						list.add(map);
						num++;
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
		paint.setColor(Color.WHITE);
		paint.setTextSize(16);
		paint.setAntiAlias(true);
		canvas.drawText("折线图", 0, 30, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 26, Ypoint - i * Yheight, paint);
		}
		// 绘制X轴刻度(点)
		for (int i = 1; i < 8; i++) {
			paint.setStyle(Style.FILL);
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		if (BaseActivity.getdata >= 100) {
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint - 15, Ypoint
					- 5 * Yheight, paint);
			canvas.drawText(String.valueOf(BaseActivity.getdata), Xpoint - 60,
					Ypoint - 5 * Yheight, paint);
		}
		for (int i = 0; i < data.size(); i++) {
			canvas.drawText(Xlable.get(i), 50 + (i + 1) * 50, Ypoint + 80,
					paint);
			canvas.drawText(data.get(i).toString(), 50 + (i + 1) * 50,
					Ypoint + 100, paint);
		}

		// 绘制折线图
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					// 错误废弃代码
					// canvas.drawLine(Xpoint + i * Xheight, Ypoint - (i)
					// * Yheight / 20, Xpoint + (i + 1) * Xheight, Ypoint
					// - (i + 1) * Yheight / 20, paint);
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 5 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
				if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - 5 * Yheight,
							paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 5 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - 5 * Yheight,
							paint);
				}
			}
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) > 100) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint - 5
							* Yheight, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight, Ypoint - 5 * Yheight - 20, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);
				}
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight,
						Ypoint + 20, paint);
			}
		}
	}
}
