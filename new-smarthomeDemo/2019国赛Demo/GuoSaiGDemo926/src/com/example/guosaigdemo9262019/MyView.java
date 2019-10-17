package com.example.guosaigdemo9262019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/*
 * @文件名：MyView.java
 * @描述：饼状图绘制
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-26
 */
public class MyView extends View {
	private List<Float> data = new ArrayList<Float>();
	private RectF oval, oval2;
	/*
	 * @方法名：handler
	 * 
	 * @功 能：刷新界面
	 * 
	 * @时 间：下午4:06:54
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
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
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
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
		paint.setAntiAlias(true);// 抗锯齿
		paint.setColor(Color.BLUE);
		// oval = new RectF(left, top, right, bottom);
		oval = new RectF(150, 70, 350, 270);
		oval2 = new RectF(200, 120, 300, 220);
		canvas.drawArc(oval, 0, DrawActivity.getdata, true, paint);
		paint.setColor(Color.WHITE);
		canvas.drawArc(oval2, 0, 360, true, paint);
		Log.e("ParBarView", "扇形图绘制完成");
		paint.setColor(Color.RED);
		paint.setTextSize(30);
		canvas.drawText(String.valueOf(DrawActivity.getdata), 350, 250, paint);
		canvas.drawText("单位：Lx", 500, 250, paint);
		canvas.drawText("图例", 500, 100, paint);
		super.onDraw(canvas);
	}
}
