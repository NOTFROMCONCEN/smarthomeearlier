package com.example.parbardemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

@SuppressLint({ "DrawAllocation", "DrawAllocation" })
public class ParBarView extends View {
	private RectF oval;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				// 刷新绘图界面
				ParBarView.this.invalidate();
				Log.e("刷新界面", "刷新成功");
				Log.e("跨活动传送参数：", Float.toString(MainActivity.getnum));
			}
		}
	};

	public ParBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.e("ParBarView", "加载完成，正在绘图");
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// 新建paint画笔
		Paint paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);

		paint.setColor(Color.BLUE);
		// 左，上，右，底
		// newF = new RectF(left, top, right, bottom)
		oval = new RectF(150, 50, 450, 350);

		// 绘制一个Arc
		canvas.drawArc(oval, 0, MainActivity.getnum, true, paint);
		Log.e("绘制大小",
				oval + "-----0-" + "---" + Float.toString(MainActivity.getnum));
		// 绘制一个Text文字
		paint.setColor(Color.DKGRAY);
		canvas.drawText(Float.toString(MainActivity.getnum), 350, 450, paint);

		// 绘制一个无填充圆圈（外圆）
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.DKGRAY);
		// X轴，Y轴，大小
		canvas.drawCircle(300, 200, 150, paint);
		// 绘制内圆0.0.0.0标点
		canvas.drawLine(300, 200, 450, 200, paint);
		// canvas.drawLine(startX, startY, stopX, stopY, paint)
		Log.e("ParBarView", "扇形图绘制完成");
		super.onDraw(canvas);
	}
}
