package com.example.drawdemo1003.myview;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

import com.example.drawdemo1003.fragment.DrawFragment;
import com.example.drawdemo1003.mysql.MyDataBaseHelper;

public class MyView extends View {
	public static HashMap<String, String> map = new HashMap<String, String>();
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xheight = 50;
	private int Yheight = 50;
	private int Xline = 500;
	private int Yline = 250;
	private int num = 1;
	private List<Float> data = new ArrayList<Float>();
	private String[] Ylable = new String[5];
	private List<String> Xlable = new ArrayList<String>();
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	private Paint paint = new Paint();

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();
				if (DrawFragment.getdata != 0 && DrawFragment.draw_state) {
					db = dbHelper.getWritableDatabase();
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
							"HH:mm:ss");
					simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
					db.execSQL(
							"insert into data (number,data,time)values(?,?,?)",
							new String[] {
									DrawFragment.num + num,
									String.valueOf(DrawFragment.getdata),
									simpleDateFormat.format(
											new java.util.Date()).toString() });// 插入数据库

				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		for (int i = 0; i < 5; i++) {
			Ylable[i] = Integer.toString(i * 20);
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
					if (DrawFragment.getdata != 0 && DrawFragment.draw_state) {
						System.out.println("启用");
						data.add(DrawFragment.getdata);
						Xlable.add(DrawFragment.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawFragment.num + num);
						map.put("data", String.valueOf(DrawFragment.getdata));
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
		paint.setAntiAlias(true);
		paint.setTextSize(16);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		for (int i = 0; i < 5; i++) {
			canvas.drawText(Ylable[i], Xpoint - 30, Ypoint - i * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 10, Ypoint
					- i * Yheight, paint);
		}
		for (int i = 1; i < 10; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
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
				if (data.get(i - 1) > 100 & data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 5 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - 5 * Yheight,
							paint);
				}
				if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - 5 * Yheight,
							paint);
				}
			}
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
				}
				if (data.get(i) > 100) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint - 5
							* Yheight, 3, paint);
				}
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight,
						Ypoint + 20, paint);
			}
		}
	}
}
