package com.example.parbardemo1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

@SuppressLint({ "DrawAllocation", "DrawAllocation" })
public class ParBarView extends View {
	private int total = 100;
	private int[] arr = { 40, 10, 2, 2, 48 };
	private float beginAngle = 0;
	private float endAng;
	private float beginAng;

	public ParBarView(Context context) {
		super(context);
	}

	public ParBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		int parsedColor[] = { Color.parseColor("#ffEEE685"),
				Color.parseColor("#ff000000"), Color.parseColor("#ff666666"),
				Color.parseColor("#ff8000FF"), Color.parseColor("#ffFF0000"),
				Color.parseColor("#ffEEE685"), Color.parseColor("#ff000000") };
		float[] bili = new float[arr.length];
		Paint paint = new Paint();
		paint.setStrokeWidth(1);
		paint.setAntiAlias(true);
		RectF oval = new RectF(100, 100, 300, 300);
		int left = 90;
		for (int i = 0; i < arr.length; i++) {
			bili[i] = (float) (arr[i] * 1.0 / total * 1.0);
		}
		for (int i = 0; i < bili.length; i++) {
			paint.setColor(parsedColor[i]);

			if (i == 1) {
				beginAngle = 360 * bili[i - 1];
			} else if (i > 1) {
				beginAngle = 360 * bili[i - 1] + beginAngle;
			}
			beginAng = i - 1 < 0 ? 0 : beginAngle;
			endAng = 360 * bili[i];

			if (bili[i] * 100 < 5) {
				System.out.println(bili[i] * 100);
				oval = new RectF(left, 100, 300, 300);
				left -= 5;
			} else {
				oval = new RectF(100, 100, 300, 300);
			}
			canvas.drawArc(oval, beginAng, endAng, true, paint);

		}
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.DKGRAY);
		canvas.drawCircle(200, 200, 100, paint);
		super.onDraw(canvas);
	}

}
