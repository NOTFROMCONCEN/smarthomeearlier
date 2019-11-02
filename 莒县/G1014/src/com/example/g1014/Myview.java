package com.example.g1014;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class Myview extends View{
	List<Float> data=new ArrayList<Float>();
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==0x1234) {
				Myview.this.invalidate();
			}
		}
	};

	public Myview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		data.add(100f);
		data.add(50f);
		data.add(70f);
		data.add(180f);
		data.add(80f);
		data.add(200f);
		data.add(10f);
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
					if (data.size()>7) {
						data.remove(0);
					}
					data.add(JibenActivity.ill);
					handler.sendEmptyMessage(0x1234);
				}
			}
		});
}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint=new Paint();
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.FILL);
		paint.setStrokeWidth(2);
		float zong=0,jiaodu=0;
		
		for (int i = 0; i <data.size(); i++) {
			zong=zong+data.get(i);
		}
		
		RectF rectF=new RectF(0,0,getMeasuredWidth(),getMeasuredHeight());
		

		paint.setColor(Color.WHITE);
		canvas.drawArc(rectF, jiaodu, data.get(0)*360/zong, true, paint);
		paint.setColor(Color.RED);
		jiaodu=jiaodu+data.get(0)*360/zong;
		canvas.drawArc(rectF, jiaodu, data.get(1)*360/zong,true, paint);
		paint.setColor(Color.BLACK);
		jiaodu=jiaodu+data.get(1)*360/zong;
		canvas.drawArc(rectF, jiaodu, data.get(2)*360/zong,true, paint);
		paint.setColor(Color.GREEN);
		jiaodu=jiaodu+data.get(2)*360/zong;
		canvas.drawArc(rectF, jiaodu, data.get(3)*360/zong,true, paint);
		paint.setColor(Color.GRAY);
		jiaodu=jiaodu+data.get(3)*360/zong;
		canvas.drawArc(rectF, jiaodu, data.get(4)*360/zong,true, paint);
		paint.setColor(Color.BLUE);
		jiaodu=jiaodu+data.get(4)*360/zong;
		canvas.drawArc(rectF, jiaodu, data.get(5)*360/zong,true, paint);
		paint.setColor(Color.YELLOW);
		jiaodu=jiaodu+data.get(5)*360/zong;
		canvas.drawArc(rectF, jiaodu, data.get(6)*360/zong,true, paint);
	
	}

}
