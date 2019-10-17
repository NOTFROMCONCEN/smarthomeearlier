package com.example.guosaiedemo22019;

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
	private List<Float> data = new ArrayList<Float>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	private int num = 1;
	private RectF oval, oval2;

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();
				Log.e("ˢ�½���", "ˢ�³ɹ�");
				db = dbHelper.getWritableDatabase();
				Log.e("����������", Float.toString(ModeActivity.getdata));
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
					if (ModeActivity.getdata != 0) {
						data.add(ModeActivity.getdata);
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
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLUE);
		// oval = new RectF(left, top, right, bottom)
		oval = new RectF(100, 10, 240, 150);
		// canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint)
		canvas.drawArc(oval, 0, ModeActivity.getdata, true, paint);
		// canvas.drawArc(oval, 0, 170, true, paint);

		paint.setColor(Color.WHITE);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		canvas.drawText(Float.toString(ModeActivity.getdata), 280, 210, paint);
		// ����һ�������ԲȦ����Բ��
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.DKGRAY);
		// X�ᣬY�ᣬ��С
		canvas.drawCircle(169, 80, 70, paint);
		Log.e("ParBarView", "����ͼ�������");
		super.onDraw(canvas);
	}
}
