package com.example.shengsaic9192017;

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
 * @描述：绘图
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-20
 */
public class MyView extends View {
	static HashMap<String, String> map = new HashMap<String, String>();
	static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;
	private int Ypoint = 250;
	private int Xheight = 50;
	private int Yheight = 40;
	private int Xline = 400;
	private int Yline = 200;
	private int num = 1;
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();
	private String[] Ylable = new String[5];

	// 刷新界面、插入数据库进程
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
					MyView.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (number,data)values(?,?)",
							new String[] { DrawActivity.num + num,
									Float.toString(DrawActivity.getdata) });
					System.out.println(Float.toString(DrawActivity.getdata));
					System.out.println("MyView进程启动");
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		// 设置数据库
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
					System.out.println("Thread进程开启");
					// 如果data大于7，删除最早的数值
					if (data.size() >= 7) {
						data.remove(0);
						Xlable.remove(0);
						list.remove(0);
					}
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawActivity.num + num);
						map.put("data", Float.toString(DrawActivity.getdata));
						list.add(map);
						num++;
						System.out.println(num);
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
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);
		paint.setAntiAlias(true);

		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制Y轴文字、刻度
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 10, Ypoint
					- i * Yheight, paint);// 绘制刻度线
			canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight, paint);// 绘制文字
		}
		// 绘制X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制X轴刻度、点
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		// 绘制顶部
		if (DrawActivity.getdata > 100) {
			// 如果获取到的数值大于100
			canvas.drawText(Float.toString(DrawActivity.getdata), Xpoint - 56,
					Ypoint - 5 * Yheight, paint);// 绘制顶部文字
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);// 绘制顶部刻度
		} else {
			canvas.drawText("", Xpoint - 56, Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		}
		if (data.size() > 1) {
			// 如果data数值大于1
			for (int i = 1; i < data.size(); i++) {
				// 绘制线位置
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
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			// 绘制点位置
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					// 如果data大于100，绘制X轴点位置
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);// 绘制点
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);// 绘制点上的文字
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
