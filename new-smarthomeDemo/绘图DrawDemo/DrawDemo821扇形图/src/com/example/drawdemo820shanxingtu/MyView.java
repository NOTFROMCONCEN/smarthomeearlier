package com.example.drawdemo820shanxingtu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/*
 * @文件名：MyView.java
 * @描述：完成绘图功能
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-8-21
 */
public class MyView extends View {
	static HashMap<String, String> map = new HashMap<String, String>();
	static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private List<Float> data = new ArrayList<Float>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private int num = 1;
	private RectF oval1, oval2;
	private Paint paint = new Paint();// 新建画笔
	// 刷新界面
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
									Float.toString(DrawActivity.getdata) });// 插入数据库
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				if (data.size() > 7) {
					list.remove(0);
				}
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
					data.add(DrawActivity.getdata);
					map = new HashMap<String, String>();
					map.put("num", DrawActivity.num + num);
					map.put("data", Float.toString(DrawActivity.getdata));
					list.add(map);
					num++;
				}
				handler.sendEmptyMessage(0x1234);
			}
		}).start();
	}

	// 绘图部分
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		oval1 = new RectF(150, 50, 450, 350);
		oval2 = new RectF(200, 100, 400, 300);
		canvas.drawArc(oval1, 0, DrawActivity.getdata, true, paint);
	}
}
