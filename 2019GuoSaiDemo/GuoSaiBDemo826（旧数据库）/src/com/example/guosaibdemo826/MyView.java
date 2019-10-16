package com.example.guosaibdemo826;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private int Xpoint = 70;
	private int Ypoint = 440;
	private int Xline = 800;
	private int Yline = 370;
	private int Xheight = 70;
	private int Yheight = 50;
	private List<Float> temp = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();
	private int num;

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
					if (temp.size() >= 7) {
						temp.remove(0);
						Xlable.remove(0);
					}
					if (MenuActivity.temp != 0) {
						temp.add(MenuActivity.temp);
						// temp.add(Float.valueOf("50"));
						Xlable.add(Float.toString(num));
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
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		paint.setStrokeWidth(5);
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		if (temp.size() > 1) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i - 1) <= 15 && temp.get(i) <= 15) {
					paint.setColor(Color.RED);
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - temp.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - temp.get(i)
									* Yheight / 20, paint);
				} else if (temp.get(i - 1) > 15 && temp.get(i) <= 15) {
					paint.setColor(Color.RED);
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - temp.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - temp.get(i)
									* Yheight / 20, paint);
				} else if (temp.get(i - 1) > 15 && temp.get(i) > 15) {
					paint.setColor(Color.BLACK);
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - temp.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - temp.get(i)
									* Yheight / 20, paint);
				} else if (temp.get(i - 1) <= 15 && temp.get(i) > 15) {
					paint.setColor(Color.BLACK);
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - temp.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - temp.get(i)
									* Yheight / 20, paint);
				}
			}
		}
	}
}
