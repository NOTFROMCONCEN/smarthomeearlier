package com.example.j0826;

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

public class Myview extends View{
	int xpoint=60;
	int ypoint=180;
	int xscale=40;
	int yscale=30;
	int xlength=240;
	int ylength=180;
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
					data.add(JibenActivity.ill);
					if (data.size()>7) {
						data.remove(0);
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
		Paint paint=new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		
		canvas.drawLine(xpoint, ypoint-ylength, xpoint, ypoint+30, paint);
		for (int i = 1; i < 5; i++) {
			canvas.drawLine(xpoint, ypoint-i*yscale, xpoint+3,ypoint-i*yscale, paint);
			canvas.drawText(ylable[i],xpoint-35, ypoint-i*yscale, paint);
		}
		canvas.drawLine(xpoint+xlength, ypoint, xpoint-40, ypoint, paint);
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 7; i++) {
			canvas.drawCircle(xpoint+i*xscale,ypoint,3, paint);
		}
		if (data.size()>1) {
			for (int i = 1; i < data.size(); i++) {
				canvas.drawLine(xpoint+(i-1)*xscale, ypoint-data.get(i-1)*yscale/200, xpoint+i*xscale, ypoint-data.get(i)*yscale/200, paint);
			}
			for (int i = 1; i < data.size(); i++) {
				canvas.drawCircle(xpoint+i*xscale, ypoint-data.get(i-1)*yscale/200, 3, paint);
				canvas.drawText(data.get(i).toString(),xpoint+i*xscale,ypoint-data.get(i-1)*yscale/200-10, paint);
			}
		}
	}
	

}
