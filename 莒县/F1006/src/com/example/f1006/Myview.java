package com.example.f1006;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
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
	int ypoint=200;
	int xscale=40;
	int yscale=30;
	int ylength=200;
	int xlength=340;
	String[] xlable=new String[9];
	String[] ylable=new String[6];
	List<Float> datatemp=new ArrayList<Float>();
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Myview.this.invalidate();
		}
	};

	public Myview(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 1; i < 9; i++) {
			xlable[i]=Integer.toString(i*1);
		}
		for (int i = 1; i < 6; i++) {
			ylable[i]=Integer.toString(i*20);
		}
		datatemp.add(10f);
		datatemp.add(100f);
		datatemp.add(40f);
		datatemp.add(70f);
		datatemp.add(50f);
		datatemp.add(20f);
		datatemp.add(80f);
		datatemp.add(10f);
		datatemp.add(10f);
		datatemp.add(100f);
		datatemp.add(40f);
		datatemp.add(70f);
		datatemp.add(50f);
		datatemp.add(20f);
		datatemp.add(80f);
		datatemp.add(10f);
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
					if (datatemp.size()>8) {
						datatemp.remove(0);
					}
					datatemp.add(JIbenActivity.temp);
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
		paint.setStyle(Style.FILL);
		
		canvas.drawLine(xpoint, ypoint-ylength, xpoint, ypoint+30, paint);
		canvas.drawLine(xpoint+xlength, ypoint, xpoint-40, ypoint, paint);
		for (int i = 1; i <9 ; i++) {
			canvas.drawLine(xpoint+i*xscale,ypoint,xpoint+i*xscale,ypoint-3, paint);
			canvas.drawText(xlable[i],xpoint+i*xscale,ypoint+20, paint);
		}
		for (int i = 1; i < 6; i++) {
			canvas.drawLine(xpoint, ypoint-i*yscale,xpoint+3, ypoint-i*yscale,paint);
			canvas.drawText(ylable[i],xpoint-35,ypoint-i*yscale, paint);
		}
		if (datatemp.size()>1) {
			for (int i = 1; i < datatemp.size(); i++) {
				canvas.drawLine(xpoint+(i-1)*xscale, ypoint-datatemp.get(i-1)*yscale/20, xpoint+i*xscale, ypoint-datatemp.get(i)*yscale/20, paint);
			}
			for (int i = 1; i < datatemp.size(); i++) {
				canvas.drawText(datatemp.get(i).toString(), xpoint+i*xscale, ypoint-datatemp.get(i)*yscale/20-10, paint);
				canvas.drawCircle(xpoint+i*xscale, ypoint-datatemp.get(i)*yscale/20, 3, paint);
			}
		}
	}

}
