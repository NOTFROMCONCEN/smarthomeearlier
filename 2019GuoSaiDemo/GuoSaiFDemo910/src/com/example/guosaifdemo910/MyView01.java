package com.example.guosaifdemo910;

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

//’€œﬂÕº
public class MyView01 extends View {
	private int Xpoint = 400;
	private int Ypoint = 250;
	private int Xline = 300;
	private int Yline = 150;
	private int Xheight = 35;
	private int Yheight = 30;
	private int num = 1;
	private MydataBaseHelper dbHelper;
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
		dbHelper = new MydataBaseHelper(getContext(), "info.db", null, 2);
		for (int i = 0; i < 6; i++) {
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
		paint.setTextSize(16);
		paint.setColor(Color.WHITE);
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		canvas.drawText("’€œﬂÕº", 0, 20, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		for (int i = 0; i < 6; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 26, Ypoint - i * Yheight, paint);
		}
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		if (BaseActivity.getdata > 100) {
			canvas.drawText(Float.toString(BaseActivity.getdata), Xpoint - 56,
					Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		} else {
			canvas.drawText("", Xpoint - 56, Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		}
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);

				} else if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i - 1) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				} else if (data.get(i - 1) > 100 && data.get(i - 1) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				}
			}
			for (int i = 1; i < data.size(); i++) {
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