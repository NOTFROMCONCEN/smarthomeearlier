package com.example.e1015;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class Myview extends View {
	List<Float> illilldata = new ArrayList<Float>();
	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				Myview.this.invalidate();
			}
		}
	};

	public Myview(Context context, AttributeSet attrs) {
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
					}
					if (illilldata.size() > 7) {
						illilldata.remove(0);
					}
					illilldata.add(Float.valueOf(85));
					illilldata.add(Float.valueOf(14));
					illilldata.add(Float.valueOf(75));
					illilldata.add(Float.valueOf(66));
					illilldata.add(Float.valueOf(93));
					illilldata.add(Float.valueOf(76));
					illilldata.add(Float.valueOf(33));
					illilldata.add(JibenActivity.ill);
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		float jiaodu = 0, zong = 0;
		for (int i = 0; i < illilldata.size(); i++) {
			zong = zong + illilldata.get(i);
		}

		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		RectF rectF = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());

		paint.setColor(Color.WHITE);
		canvas.drawArc(rectF, jiaodu, illilldata.get(1) * 360 / zong, true,
				paint);
		paint.setColor(Color.RED);
		jiaodu = jiaodu + illilldata.get(0) * 360 / zong;
		canvas.drawArc(rectF, jiaodu, illilldata.get(2) * 360 / zong, true,
				paint);
		paint.setColor(Color.BLACK);
		jiaodu = jiaodu + illilldata.get(1) * 360 / zong;
		canvas.drawArc(rectF, jiaodu, illilldata.get(3) * 360 / zong, true,
				paint);
		paint.setColor(Color.GREEN);
		jiaodu = jiaodu + illilldata.get(2) * 360 / zong;
		canvas.drawArc(rectF, jiaodu, illilldata.get(4) * 360 / zong, true,
				paint);
		paint.setColor(Color.GRAY);
		jiaodu = jiaodu + illilldata.get(3) * 360 / zong;
		canvas.drawArc(rectF, jiaodu, illilldata.get(5) * 360 / zong, true,
				paint);
		paint.setColor(Color.BLUE);
		jiaodu = jiaodu + illilldata.get(4) * 360 / zong;
		canvas.drawArc(rectF, jiaodu, illilldata.get(6) * 360 / zong, true,
				paint);
		paint.setColor(Color.YELLOW);
		jiaodu = jiaodu + illilldata.get(5) * 360 / zong;
		canvas.drawArc(rectF, jiaodu, illilldata.get(7) * 360 / zong, true,
				paint);
	}

}
