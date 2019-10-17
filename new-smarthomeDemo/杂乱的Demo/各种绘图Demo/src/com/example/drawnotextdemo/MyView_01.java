package com.example.drawnotextdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/*
 * @文件名：MyView_01.java
 * @描述：绘制柱状图，指定布局：@+id/re_zhuzhuangtu
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-6
 */
public class MyView_01 extends View {
	public static HashMap<String, String> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private Paint paint = new Paint();// 新建画笔
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();
	private String[] Ylable = new String[5];
	private int num = 1;
	private int Xpoint = 100;
	private int Ypoint = 300;
	private int Xheight = 100;
	private int Yheight = 50;
	private int Xline = 800;
	private int Yline = 250;
	// 数据库
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				db = dbHelper.getWritableDatabase();
				db.execSQL(
						"insert into data (number,data)values(?,?)",
						new String[] { MainActivity.number + num,
								String.valueOf(MainActivity.getdata) });// 插入数据库
				MyView_01.this.invalidate();
			}
		}
	};

	public MyView_01(Context context, AttributeSet attrs) {
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
						map.put("data", Float.toString(MainActivity.getdata));
						list.add(map);
						num++;
					}
					if (data.size() > 7) {
						data.remove(0);
						Xlable.remove(0);
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
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制Y轴刻度、文字
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 15, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 35, Ypoint - i * Yheight, paint);
		}
		// 绘制X轴文字、刻度
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		// 绘制当data数值大于100时的事件
		if (MainActivity.getdata > 100) {
			canvas.drawText(String.valueOf(MainActivity.getdata), Xpoint - 60,
					Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint - 15, Ypoint
					- 5 * Yheight, paint);
		}
		// data大于1，也是程序开始绘图
		if (data.size() > 1) {
			// 循环绘制折线图，循环次数为data的大小数量
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				}
				if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				}
				if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			// 绘制X轴实心点和文字
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
							* Xheight, Ypoint - 5 * Yheight - 10, paint);
				}
				canvas.drawText(
						Xlable.get(i) + "---" + String.valueOf(data.get(i)),
						Xpoint + (i + 1) * Xheight, Ypoint + 26, paint);
			}
		}
	}
}