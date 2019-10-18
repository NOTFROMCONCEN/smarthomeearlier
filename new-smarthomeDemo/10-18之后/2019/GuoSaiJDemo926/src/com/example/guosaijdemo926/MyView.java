package com.example.guosaijdemo926;

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
	private int Xpoint = 200;
	private int Ypoint = 200;
	private int Xline = 240;
	private int Yline = 100;
	private int Xheight = 30;
	private int Yheight = 20;
	private int num = 1;
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();
	private String[] Ylable = new String[5];
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	public static HashMap<String, String> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	// 刷新界面
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
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
					}
					if (DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
						Xlable.add(Float.toString(DrawActivity.getdata));
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
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setTextSize(16);
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint, Xpoint, Ypoint - Yline, paint);
		// 绘制X轴
		canvas.drawLine(Xpoint, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴文字
		for (int i = 1; i < 5; i++) {
			canvas.drawText(Ylable[i], Xpoint - 30, Ypoint - i * Yheight, paint);
		}
		// X轴刻度
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 2, paint);
		}
		// 数值大于100写Y轴最高
		if (DrawActivity.getdata > 100) {
			canvas.drawText(Float.toString(DrawActivity.getdata), Xpoint - 30,
					Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		} else {
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
			canvas.drawText("", Xpoint - 30, Ypoint - 5 * Yheight, paint);
		}
		// 如果data有数值
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				// 两点均小于100
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
				} else if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Ypoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			// 绘制实心点
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 2, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint
							- Yheight * 5, 2, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight, Ypoint - Yheight * 5 - 10, paint);
				}
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight,
						Ypoint + 26, paint);
			}
		}
	}
}
