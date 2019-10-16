package com.example.guosaihdemo914;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.SweepGradient;
import android.os.Handler;
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
					max.add(Float.parseFloat("100"));
					/**
					 * 测试参数
					 */
					// temp.add(Float.parseFloat("100"));
					// hum.add(Float.parseFloat("200"));
					// smo.add(Float.parseFloat("300"));
					if (IndexActivity.temp != 0) {
						temp.add(IndexActivity.temp);
					}
					if (IndexActivity.hum != 0) {
						hum.add(IndexActivity.hum);
					}
					if (IndexActivity.smo != 0) {
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
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		String[] argStrings = { "温度", "烟雾", "湿度" };// X轴刻度
		for (int i = 0; i < argStrings.length; i++) {
			canvas.drawText(String.valueOf(argStrings[i]), Xpoint + i * Xheight
					+ 20, Ypoint + 20, paint);
		}
		/**
		 * 柱状图绘制参数： 宽度：30 间隔：100
		 */
		/**
		 * 顶部： 如果参数小于100，顶部设置为参数数值；
		 */
		/**
		 * 如果参数大于100，顶部设置为固定值，并在顶部绘制数值参数
		 */
		paint.setStyle(Style.FILL);// 设置风格为填充；
		paint.setShader(new SweepGradient(Ypoint, Yline, Color.GREEN,
				Color.YELLOW));
		int[] color = {};
		/**
		 * 绘制温度柱状图（左20右50宽30）
		 */
		if (temp.size() > 0) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) < 100) {
					canvas.drawText(temp.get(i).toString(), Xpoint + 20, Ypoint
							- temp.get(i) * Yheight / 20 - 10, paint);
					canvas.drawRect(Xpoint + 20, Ypoint - temp.get(i) * Yheight
							/ 20, Xpoint + 50, Ypoint, paint);
				} else {
					canvas.drawText(Float.toString(IndexActivity.temp),
							Xpoint + 20, Ypoint - max.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 20, Ypoint - max.get(i) * Yheight
							/ 20, Xpoint + 50, Ypoint, paint);
				}
			}
		}
		/**
		 * 绘制湿度柱状图（左120右150宽30）
		 */
		if (hum.size() > 0) {
			for (int i = 0; i < hum.size(); i++) {
				if (hum.get(i) < 100) {
					canvas.drawText(Float.toString(IndexActivity.hum),
							Xpoint + 220, Ypoint - hum.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 220, Ypoint - hum.get(i) * Yheight
							/ 20, Xpoint + 250, Ypoint, paint);
				} else {
					canvas.drawText(Float.toString(IndexActivity.hum),
							Xpoint + 220, Ypoint - max.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 220, Ypoint - max.get(i) * Yheight
							/ 20, Xpoint + 250, Ypoint, paint);
				}
			}
		}

		/**
		 * 绘制烟雾柱状图（左520右550宽30）
		 */
		if (smo.size() > 0) {
			for (int i = 0; i < smo.size(); i++) {
				if (smo.get(i) < 100) {
					canvas.drawText(Float.toString(IndexActivity.smo),
							Xpoint + 120, Ypoint - smo.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 120, Ypoint - smo.get(i) * Yheight
							/ 20, Xpoint + 150, Ypoint, paint);
				} else {
					canvas.drawText(Float.toString(IndexActivity.smo),
							Xpoint + 120, Ypoint - max.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 120, Ypoint - max.get(i) * Yheight
							/ 20, Xpoint + 150, Ypoint, paint);
				}
			}
		}
	}
}
