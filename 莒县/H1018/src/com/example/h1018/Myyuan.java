package com.example.h1018;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class Myyuan extends View{

	public Myyuan(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint=new Paint();
	//	paint.setColor(R.drawable.hong);
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);
		
		RectF rectF=new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
		canvas.drawArc(rectF, 0, 360, true, paint);
	}

}
