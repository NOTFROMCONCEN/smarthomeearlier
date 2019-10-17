package com.example.shengsaiddemo2017921;

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
 * @时间：2019-9-21
 */
public class MyView extends View {
	public static HashMap<String, String> map;
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 405;// X点
	private int Ypoint = 405;// Y点
	private int Xline = 490;// X线
	private int Yline = 350;// Y线
	private int Xheight = 70;// X轴间隔
	private int Yheight = 70;// Y轴间隔
	private int num = 1;// 累计数据
	private List<Float> data = new ArrayList<Float>();// Float数值(当前传感器数据)
	private String[] Ylable = new String[5];// Y轴刻度倍数
	private List<String> Xlable = new ArrayList<String>();// X轴数据
	private Paint paint = new Paint();// 新建画笔
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// 刷新界面进程
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
					MyView.this.invalidate();
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
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		for (int i = 0; i < 5; i++) {
			Ylable[i] = String.valueOf(i * 400);
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
					if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawActivity.num + num);
						map.put("data", String.valueOf(DrawActivity.getdata));
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
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
		// 绘制X轴
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint + 70, Ypoint - i * Yheight, Xpoint + Xline,
					Ypoint - i * Yheight, paint);
		}
		// 绘制Y轴
		for (int i = 1; i < 8; i++) {
			canvas.drawLine(Xpoint + i * Xheight, Ypoint, Xpoint + i * Xheight,
					Ypoint - Yline + 70, paint);
		}
		// 绘制Y轴文字
		for (int i = 1; i < 5; i++) {
			canvas.drawText(Ylable[i], Xpoint, Ypoint - i * Yheight, paint);
		}
		// 绘制折线图
		paint.setColor(Color.RED);// 红色
		paint.setStrokeWidth(5);
		if (data.size() > 0) {// data.size > 1有数值时
			for (int i = 1; i < data.size(); i++) {// 绘制data大小的循环
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
				if (data.get(i - 1) > 1600 && data.get(i) > 1600) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 4 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - 4 * Yheight,
							paint);
				}
				if (data.get(i - 1) > 1600 && data.get(i) <= 1600) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 4 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 400, paint);
				}
			}
			paint.setStyle(Style.FILL);
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 1600) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 400, 6, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint - 4
							* Yheight, 6, paint);
				}
			}
		}
	}
}
