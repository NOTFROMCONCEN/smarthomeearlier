package com.example.guosaibdemo922;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.View;

/*
 * @文件名：MyView.java
 * @描述：绘制折线图
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-22
 */
public class MyView extends View {
	private int Xpoint = 100;// X点
	private int Ypoint = 400;// Y点
	private int Xheight = 100;// X间隔
	private int Yheight = 15;// Y间隔
	private int Xline = 800;// X长度
	private int Yline = 300;// Y长度
	private Paint paint = new Paint();// 画笔
	private List<String> Xlable = new ArrayList<String>();
	private List<Float> data = new ArrayList<Float>();
	// 强制刷新界面
	Handler handler = new Handler() {

		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (RoomTemp.getdata != 0 && RoomTemp.draw_state) {
					MyView.this.invalidate();
				}
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
					if (data.size() >= 7) {
						data.remove(0);
					}
					if (RoomTemp.getdata != 0 && RoomTemp.draw_state) {
						data.add(RoomTemp.getdata);
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
		paint.setAntiAlias(true);// 抗锯齿
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(3);
		paint.setStyle(Style.STROKE);

		// 绘制X轴
		canvas.drawLine(Xpoint + 80, Ypoint, Xpoint + Xline, Ypoint, paint);
		// 绘制Y轴
		canvas.drawLine(Xpoint + 100, Ypoint + 20, Xpoint + 100,
				Ypoint - Yline, paint);
		// 绘制折线图
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 15 && data.get(i) <= 15) {
					Paint paintsPaint = new Paint();
					paintsPaint.setColor(Color.BLACK);
					paintsPaint.setStrokeWidth(3);
					paintsPaint.setAntiAlias(true);
					paintsPaint.setStyle(Style.STROKE);
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paintsPaint);
				}
				if (data.get(i - 1) <= 15 && data.get(i) > 15) {
					Paint paintsPaint = new Paint();
					paintsPaint.setColor(Color.BLACK);
					paintsPaint.setStrokeWidth(3);
					paintsPaint.setStyle(Style.STROKE);
					paintsPaint.setAntiAlias(true);

					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paintsPaint);
				}
				if (data.get(i - 1) > 15 && data.get(i) > 15) {
					Paint paintsPaint = new Paint();
					paintsPaint.setColor(Color.RED);
					paintsPaint.setStrokeWidth(3);
					paintsPaint.setStyle(Style.STROKE);
					paintsPaint.setAntiAlias(true);

					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paintsPaint);
				}
				if (data.get(i - 1) > 15 && data.get(i) <= 15) {
					Paint paintsPaint = new Paint();
					paintsPaint.setColor(Color.BLACK);
					paintsPaint.setStrokeWidth(3);
					paintsPaint.setStyle(Style.STROKE);
					paintsPaint.setAntiAlias(true);

					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paintsPaint);
				}
			}
		}
	}
}
