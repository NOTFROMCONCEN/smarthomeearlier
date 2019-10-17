package com.example.drawdemo53;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {
	static HashMap<String, String> map = new HashMap<String, String>();
	static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private List<Float> data = new ArrayList<Float>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private int num = 1;
	private RectF oval, oval2;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
					MyView.this.invalidate();
					Log.e("ˢ�½���", "ˢ�³ɹ�");
					db = dbHelper.getWritableDatabase();
					db.execSQL("insert into data (num,data)values(?,?)",
							new String[] {
									"�豸��ţ�" + DrawActivity.num + "��ǰ������" + num,
									Float.toString(DrawActivity.getdata) });
					Log.e("����������", Float.toString(DrawActivity.getdata)
							+ DrawActivity.num);
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
				while (true) {
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
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		// oval = new RectF(left, top, right, bottom)
		oval = new RectF(150, 50, 450, 350);
		oval2 = new RectF(200, 100, 400, 300);
		// canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint)
		canvas.drawLine(300, 200, 450, 200, paint);
		canvas.drawArc(oval, 0, DrawActivity.getdata, true, paint);
		paint.setColor(Color.WHITE);
		canvas.drawArc(oval2, 0, 360, true, paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		canvas.drawText(Float.toString(DrawActivity.getdata), 280, 210, paint);
		// ����һ�������ԲȦ����Բ��
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.DKGRAY);
		// X�ᣬY�ᣬ��С
		canvas.drawCircle(300, 200, 150, paint);
		// canvas.drawLine(startX, startY, stopX, stopY, paint)
		Log.e("ParBarView", "����ͼ�������");
		super.onDraw(canvas);
	}
}
