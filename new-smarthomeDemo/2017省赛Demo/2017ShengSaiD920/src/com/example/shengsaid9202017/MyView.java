package com.example.shengsaid9202017;

import java.net.PasswordAuthentication;
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
	public static HashMap<String, String> map;
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 400;
	private int Ypoint = 350;
	private int Xheight = 70;
	private int Yheight = 50;
	private int Xline = 560;
	private int Yline = 200;
	private int num = 1;
	private Paint paint = new Paint();// 新建画笔
	private List<String> Xlable = new ArrayList<String>();
	private List<Float> data = new ArrayList<Float>();
	private String[] Ylable = new String[5];
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (number,data)values(?,?)",
							new String[] { DrawActivity.num + num,
									String.valueOf(DrawActivity.getdata) });
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "smarthome.db", null, 2);
		for (int i = 0; i < 5; i++) {
			Ylable[i] = String.valueOf(i * 400);
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawActivity.num + num);
						map.put("data", String.valueOf(DrawActivity.getdata));
						list.add(map);
						num++;
					}
					if (data.size() >= 9) {
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
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		paint.setTextSize(16);
		// 绘制X轴
		canvas.drawLine(Xpoint + Xheight, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴
		for (int i = 1; i <= 8; i++) {
			canvas.drawLine(Xpoint + i * Xheight, Ypoint, Xpoint + i * Xheight,
					Ypoint - Yline, paint);
		}
		// 绘制X轴
		for (int i = 0; i <= 4; i++) {
			canvas.drawLine(Xpoint + Xheight, Ypoint - i * Yheight, Xpoint
					+ Xline, Ypoint - i * Yheight, paint);
		}
		// 绘制Y轴刻度
		for (int i = 0; i < 5; i++) {
			canvas.drawText(Ylable[i], Xpoint, Ypoint - Yheight * i, paint);
		}
		// 绘制折线图
		paint.setColor(Color.RED);
		if (data.size() > 0) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 1600 && data.get(i) <= 1600) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 400, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 400, paint);
				}
				if (data.get(i - 1) <= 1600 && data.get(i) > 1600) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 400, Xpoint
									+ (i + 1) * Xheight, Ypoint - 4 * Yheight,
							paint);
				}
				if (data.get(i - 1) > 1600 && data.get(i) <= 1600) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 4 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 400, paint);
				}
				if (data.get(i - 1) > 1600 && data.get(i) > 1600) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 4 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - 4 * Yheight,
							paint);
				}
			}
			// 绘制实心点
			paint.setStyle(Style.FILL);
			paint.setColor(Color.RED);
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 1600) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 400, 5, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint
							- Yheight * 4, 5, paint);

				}
			}
		}
	}
}
