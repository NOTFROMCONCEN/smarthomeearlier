package com.example.b1008;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class Myview extends View {
	int xpoint=60;
	int ypoint=250;
	int xscale=40;
	int yscale=30;
	int xlength=480;
	int ylength=200;
	String[] ylable=new String[5];
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
		for (int i = 1; i < 5; i++) {
			ylable[i]=Integer.toString(i*200);
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
						}
						if (data.size()>7) {
							data.remove(0);
						}
						data.add(JibenActivity.ill);
						handler.sendEmptyMessage(0x1234);
					}
				}
			}).start();
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint=new Paint();
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
		
		canvas.drawLine(xpoint, ypoint-ylength,xpoint,ypoint+30, paint);
		canvas.drawLine(xpoint+xlength,ypoint,xpoint-35, ypoint, paint);
		
		if (data.size()>1) {
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i)>=15) {
					paint.setColor(Color.RED);
					canvas.drawLine(xpoint+(i-1)*xscale, ypoint-data.get(i-1)*yscale/20, xpoint+i*xscale, ypoint-data.get(i)*yscale/20, paint);
				}else if (data.get(i)<15) {
					paint.setColor(Color.BLACK);
					canvas.drawLine(xpoint+(i-1)*xscale, ypoint-data.get(i-1)*yscale/20, xpoint+i*xscale, ypoint-data.get(i)*yscale/20, paint);
				}
			}
		}
	}

}
