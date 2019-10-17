package com.example.shengsaiyangtidemo9282019;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private List<Float> data = new ArrayList<Float>();
	private RectF oval, oval2;
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			MyView.this.invalidate();
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
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.GRAY);
		oval = new RectF(100, 10, 240, 150);
		canvas.drawArc(oval, 0, BaseActivity.ill / 7, true, paint);
		paint.setStyle(Style.STROKE);
		paint.setColor(Color.BLACK);
		oval = new RectF(100, 10, 240, 150);
		canvas.drawArc(oval, 0, 360, true, paint);
		canvas.drawText(String.valueOf(BaseActivity.ill), 150, 240, paint);
	}
}
