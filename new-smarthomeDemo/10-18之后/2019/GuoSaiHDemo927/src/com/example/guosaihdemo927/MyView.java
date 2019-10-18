package com.example.guosaihdemo927;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.provider.ContactsContract.Contacts.Data;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	// 定义控件
	private int Xpoint = 50;
	private int Ypoint = 150;
	private int Xheight = 100;
	private int Yheight = 20;
	private int Yline = 100;
	private int Xline = 300;
	private String[] Ylable = new String[5];
	private List<Float> temp = new ArrayList<Float>();
	private List<Float> hum = new ArrayList<Float>();
	private List<Float> smo = new ArrayList<Float>();
	private List<Float> max = new ArrayList<Float>();
	// 刷新界面
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			MyView.this.invalidate();
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 5; i++) {
			Ylable[i] = Float.toString(i * 20);
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
					if (temp.size() >= 1) {
						temp.remove(0);
					}
					if (hum.size() >= 1) {
						hum.remove(0);
					}
					if (smo.size() >= 1) {
						smo.remove(0);
					}
					// 设置当参数超过100时固定的数值；方便绘制柱状图；
					// 数据采集使用随机数，方便测试
					max.add(Float.parseFloat("100"));
					if (IndexActivity.temp != 0) {// 温度
						temp.add(IndexActivity.temp);
					}
					if (IndexActivity.hum != 0) {// 湿度
						hum.add(IndexActivity.hum);
					}
					if (IndexActivity.smo != 0) {// 烟雾
						smo.add(IndexActivity.smo);
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
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		paint.setTextSize(15);
		// 不绘制Y轴、Y轴刻度
		// for (int i = 0; i < 5; i++) {
		// canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 5, Ypoint
		// - i * Yheight, paint);
		// canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight, paint);
		// }
		canvas.drawLine(Xpoint, Ypoint, Xpoint + Xline, Ypoint, paint);
		String[] argStrings = { "温度", "烟雾", "湿度" };// X轴刻度
		for (int i = 0; i < argStrings.length; i++) {
			canvas.drawText(String.valueOf(argStrings[i]), Xpoint + i * Xheight
					+ 40, Ypoint + 20, paint);
		}
		SweepGradient sweepGradient = new SweepGradient((float) 0.5, 0,
				0x00FFFF, 0xFD0000);
		Paint painttest = new Paint();
		painttest.setShader(sweepGradient);
		painttest.setStyle(Style.FILL);
		painttest.setAntiAlias(true);
		canvas.drawRect(Xpoint + 40, Ypoint - 50 * Yheight / 20, Xpoint + 68,
				Ypoint, painttest);
		/**
		 * 顶部： 如果参数小于100，顶部设置为参数数值；
		 */
		/**
		 * 如果参数大于100，顶部设置为固定值，并在顶部绘制数值参数
		 */
		// 温度
		if (temp.size() > 0) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) <= 100) {
					// canvas.drawRect(left, top, right, bottom, paint)
					canvas.drawRect(Xpoint + 40, Ypoint - temp.get(i) * Yheight
							/ 20, Xpoint + 68, Ypoint, paint);
					Paint paint2 = new Paint();
					paint2.setAntiAlias(true);
					paint2.setColor(Color.WHITE);
					DecimalFormat df = new DecimalFormat("0");
					String get_temp = df.format(temp.get(i));
					canvas.drawText(get_temp, Xpoint + 50, Ypoint - temp.get(i)
							* Yheight / 20 - 10, paint2);
				} else {
					canvas.drawRect(Xpoint + 40, Ypoint - 5 * Yheight,
							Xpoint + 68, Ypoint, paint);
					canvas.drawText("100", Xpoint + 55, Ypoint - 5 * Yheight,
							paint);
				}
			}
		}
		// 烟雾
		if (smo.size() > 0) {
			for (int i = 0; i < smo.size(); i++) {
				if (smo.get(i) <= 100) {
					// canvas.drawRect(left, top, right, bottom, paint)
					canvas.drawRect(Xpoint + 140, Ypoint - smo.get(i) * Yheight
							/ 20, Xpoint + 168, Ypoint, paint);
					Paint paint2 = new Paint();
					paint2.setAntiAlias(true);
					paint2.setColor(Color.WHITE);
					DecimalFormat df = new DecimalFormat("0");
					String get_temp = df.format(smo.get(i));
					canvas.drawText(get_temp, Xpoint + 150, Ypoint - smo.get(i)
							* Yheight / 20 - 10, paint2);
				} else {
					canvas.drawRect(Xpoint + 140, Ypoint - 5 * Yheight,
							Xpoint + 168, Ypoint, paint);
					canvas.drawText("100", Xpoint + 155, Ypoint - 5 * Yheight,
							paint);
				}
			}
		}
		// 湿度
		if (hum.size() > 0) {
			for (int i = 0; i < hum.size(); i++) {
				if (hum.get(i) <= 100) {
					// canvas.drawRect(left, top, right, bottom, paint)
					canvas.drawRect(Xpoint + 240, Ypoint - hum.get(i) * Yheight
							/ 20, Xpoint + 268, Ypoint, paint);
					Paint paint2 = new Paint();
					paint2.setAntiAlias(true);
					paint2.setColor(Color.WHITE);
					DecimalFormat df = new DecimalFormat("0");
					String get_temp = df.format(hum.get(i));
					canvas.drawText(get_temp, Xpoint + 250, Ypoint - hum.get(i)
							* Yheight / 20 - 10, paint2);
				} else {
					canvas.drawRect(Xpoint + 240, Ypoint - 5 * Yheight,
							Xpoint + 268, Ypoint, paint);
					canvas.drawText("100", Xpoint + 255, Ypoint - 5 * Yheight,
							paint);
				}
			}
		}
	}
}
