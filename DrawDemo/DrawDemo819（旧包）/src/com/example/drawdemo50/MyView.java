package com.example.drawdemo50;

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

public class MyView extends View {
	static HashMap<String, String> map = new HashMap<String, String>();
	static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xheight = 100;
	private int Yheight = 40;
	private int Xline = 800;
	private int Yline = 200;
	private int num = 1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();
	private String[] Ylable = new String[5];
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
					MyView.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (num,data)values(?,?)",
							new String[] { DrawActivity.num + num,
									Float.toString(DrawActivity.getdata) });
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
					if (data.size() >= 7) {
						data.remove(0);
						Xlable.remove(0);
						if (DrawActivity.draw_state) {
							list.remove(0);
						}
					}
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("num", DrawActivity.num + num);
						map.put("data", Float.toString(DrawActivity.getdata));
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
		paint.setColor(Color.DKGRAY);
		paint.setTextSize(16);
		paint.setAntiAlias(true);
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴刻度
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 10, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight, paint);
		}
		// 绘制X轴点
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		// 绘制顶部
		if (DrawActivity.getdata > 100) {
			canvas.drawText(Float.toString(DrawActivity.getdata), Xpoint - 56,
					Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Yheight
					- 5 * Yheight, paint);
		} else {
			canvas.drawText("", Xpoint - 56, Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		}
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Yheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				} else if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint
							- Yheight * 5, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight, Ypoint - Yheight * 5 - 10, paint);
				}
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight,
						Ypoint + 26, paint);
			}
		}
	}
}
