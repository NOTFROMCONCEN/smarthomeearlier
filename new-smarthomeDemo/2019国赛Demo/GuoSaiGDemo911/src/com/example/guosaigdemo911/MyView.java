package com.example.guosaigdemo911;

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

public class MyView extends View {
	private List<Float> data = new ArrayList<Float>();
	private RectF oval, oval2;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
					MyView.this.invalidate();
					Log.e("界面刷新功能", "刷新成功");
					Log.e("获得数据", Float.toString(DrawActivity.getdata)
							+ "------" + DrawActivity.num);
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
		paint.setColor(Color.BLUE);
		// oval = new RectF(left, top, right, bottom);
		oval = new RectF(150, 70, 350, 270);
		oval2 = new RectF(200, 120, 300, 220);
		canvas.drawArc(oval, 0, DrawActivity.getdata, true, paint);
		paint.setColor(Color.WHITE);
		canvas.drawArc(oval2, 0, 360, true, paint);
		Log.e("ParBarView", "扇形图绘制完成");
		super.onDraw(canvas);
	}
}
