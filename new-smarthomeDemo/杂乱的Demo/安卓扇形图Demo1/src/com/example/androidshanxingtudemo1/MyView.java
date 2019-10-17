package com.example.androidshanxingtudemo1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class MyView extends View {
	private int total = 100;
	private float start_Angle = 0;
	private float end_Angle = 15;
	private float begin_Angle = 0;
	private int num = 1;
	float last_draw = 0;
	private List<String> Xlable = new ArrayList<String>();
	private List<Float> data = new ArrayList<Float>();
	private MyDataBaseHelper dbHelper;
	private SQLiteDatabase db;
	public static HashMap<String, String> map;
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (MainActivity.draw_state && MainActivity.getdata != 0) {
					MyView.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data(number,data)values(?,?)",
							new String[] { MainActivity.num + num,
									String.valueOf(MainActivity.getdata) });
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
					if (MainActivity.getdata != 0 && MainActivity.draw_state) {
						data.add(MainActivity.getdata);
						Xlable.add(MainActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", MainActivity.num + num);
						map.put("data", String.valueOf(MainActivity.getdata));
						list.add(map);
						num++;
						// Log.e("data¸³Öµ", data.toString());
					}
					if (data.size() >= 7) {
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
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setStyle(Style.STROKE);
		paint.setTextSize(16);
		paint.setColor(Color.BLACK);
		RectF oval = new RectF(100, 100, 300, 300);
		int color[] = { Color.parseColor("#FFEEE685"),
				Color.parseColor("#FF000000"), Color.parseColor("#FF666666"),
				Color.parseColor("#FF8000FF"), Color.parseColor("#FFEEE685"),
				Color.parseColor("#FFEEE685"), Color.parseColor("#FF000000") };
		canvas.drawCircle(200, 200, 100, paint);
		paint.setStyle(Style.FILL_AND_STROKE);
		int left = 90;
		int[] arr = { 50, 50, 50, 50, 50, 50, 50 };
		float[] bili = new float[arr.length];
		for (int i = 0; i < arr.length; i++) {
			bili[i] = (float) (arr[i] * 1.0 / total * 1.0);
		}
		for (int i = 0; i < bili.length; i++) {
			paint.setColor(color[i]);
			if (i == 1) {
				begin_Angle = 360 * bili[i - 1];
			} else if (i > 1) {
				begin_Angle = 360 * bili[i - 1] + begin_Angle;
			}
			begin_Angle = i - 1 < 0 ? 0 : begin_Angle;
			end_Angle = 360 * bili[i];
			if (bili[i] * 100 < 5) {
				System.out.println(bili[i] * 100);
				oval = new RectF(left, 100, 300, 300);
				left -= 5;
			} else {
				oval = new RectF(100, 100, 300, 300);
			}
			canvas.drawArc(oval, begin_Angle, end_Angle, true, paint);
		}

		// canvas.drawArc(oval, startAngle, sweepAngle, useCenter, paint)
		//
		// if (data.size() > 1) {
		// for (int i = 1; i < data.size(); i++) {
		// if (data.get(i - 1) <= 90 && data.get(i) <= 90) {
		// paint.setColor(color[i]);
		// paint.setStyle(Style.FILL_AND_STROKE);
		// paint.setStrokeWidth(2);
		// RectF oval = new RectF(100, 100, 300, 300);
		// int startAngle = 0;
		// float sweepAngle = MainActivity.getdata;
		// canvas.drawArc(oval, 0 + (i + 1), sweepAngle, true, paint);
		//
		// }
		// }
		// }

		// int left = 90;
		// if (data.size() > 1) {
		// for (int i = 1; i < data.size(); i++) {
		// if (data.get(i - 1) <= 90 && data.get(i) <= 90) {
		// paint.setColor(Color.RED);
		// paint.setStyle(Style.FILL_AND_STROKE);
		// paint.setStrokeWidth(2);
		// RectF oval = new RectF(100, 100, 300, 300);
		// int startAngle = 0;
		// float sweepAngle = MainActivity.getdata;
		// canvas.drawArc(oval, startAngle + (i + 1), sweepAngle,
		// true, paint);
		// }
		// }
		// }

		// int left = 90;
		// for (int i = 0; i < data.size(); i++) {
		// paint.setColor(color[i]);
		// if (i == 1) {
		// begin_Angle = 360 * data.get(i - 1);
		// } else if (i > 1) {
		// begin_Angle = 360 * data.get(i - 1) + begin_Angle;
		// }
		// begin_Angle = i - 1 < 0 ? 0 : begin_Angle;
		// end_Angle = 360 * data.get(i);
		// }
	}
}
