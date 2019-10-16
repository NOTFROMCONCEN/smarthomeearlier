package com.example.drawdemo817;

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
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;// X点位置
	private int Ypoint = 300;// Y轴位置
	private int Xheight = 50;// X轴间隔
	private int Yheight = 40;// Y轴间隔
	private int Yline = 200;// Y轴长度
	private int Xline = 400;// X轴长度
	private int num = 1;
	private MyDataBaseHelper dbHelper;// 数据库
	private SQLiteDatabase db;// 数据库
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();// X轴刻度
	private String[] Ylable = new String[5];// Y轴刻度
	private Paint paint = new Paint();// 新建画笔

	/*
	 * @方法名：handler
	 * 
	 * @功 能：刷新界面
	 * 
	 * @时 间：上午10:24:11
	 */
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
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		// 设置Y轴刻度数字
		for (int i = 0; i < 5; i++) {
			Ylable[i] = Integer.toString(i * 20);
		}
		// 插入数据库、刷新
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
						// 如果data数量大于7，移除最前端的
						data.remove(0);
						Xlable.remove(0);
						list.remove(0);
					}
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						// 如果开关被打开并且获取的数据不为空
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawActivity.num + num);
						map.put("data", Float.toString(DrawActivity.getdata));
						list.add(map);
						num++;
					}
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	/*
	 * @方法名：onDraw(Canvas canvas)
	 * 
	 * @功 能：绘制折线图
	 * 
	 * @参 数：canvas
	 * 
	 * @时 间：上午10:49:02
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setStyle(Style.STROKE);// 设置画笔风格
		paint.setColor(Color.BLACK);// 设置画笔颜色
		paint.setAntiAlias(true);
		paint.setTextSize(16);// 设置画笔文字大小
		// 绘制Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// 绘制X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴刻度、文字
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 35, Ypoint - i * Yheight, paint);
		}
		// 绘制X轴点、刻度
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 4, paint);
		}
		/**
		 * 绘制Y轴顶端
		 */
		if (DrawActivity.getdata > 100) {// 如果获得到的数值大于100
			canvas.drawText(Float.toString(DrawActivity.getdata), Xpoint - 56,
					Ypoint - 5 * Yheight, paint);// 绘制文字
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);// 绘制连线
		} else {
			// 否则清空
			canvas.drawText("", Xpoint - 56, Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		}
		/**
		 * 绘制点并连线
		 */
		if (data.size() > 1) {
			// 如果data有数值
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					// 两点都不超过100
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				} else if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					// 前一点小于100，后一点大于100
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) > 100) {
					// 全部大于100
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					// 前一点大于100，后一点小于100
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			// 绘制Y轴实心点
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					// 如果数值小于100
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);// 绘制实心点上的文字
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint
							- Yheight * 5, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight, Ypoint - Yheight * 5 - 10, paint);
				}
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight - 10,
						Ypoint + 26, paint);
			}
		}
	}
}