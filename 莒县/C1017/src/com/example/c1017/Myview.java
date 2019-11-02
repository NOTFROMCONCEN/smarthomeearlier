package com.example.c1017;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.R.integer;
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
	int xxpoint=80;
	int ypoint=200;
	int xscale=40;
	int xxcale=50;
	int yscale=30;
	int xlength=320;
	int ylength=160;
	String[] xlable=new String[5];
	String[] ylable=new String[5];
	List<Float> datatemp=new ArrayList<Float>();
	List<Float> dataill=new ArrayList<Float>();

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
			xlable[i]=Integer.toString(i*1);
		}
		for (int i = 0; i < 5; i++) {
			ylable[i]=Integer.toString(i*100);
		}
//		datatemp.add(100f);
//		datatemp.add(160f);
//		datatemp.add(250f);
//		datatemp.add(60f);
//		
//		dataill.add(50f);
//		dataill.add(150f);
//		dataill.add(250f);
//		dataill.add(40f);
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
					if (datatemp.size()>5) {
						datatemp.remove(0);
					}
					datatemp.add(JibenActivity.temp);
					if (dataill.size()>5) {
						dataill.remove(0);
					}
					dataill.add(JibenActivity.ill);
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
		paint.setStyle(Style.FILL);
		
		Paint painttemp=new Paint();
		painttemp.setColor(Color.BLUE);
		painttemp.setStyle(Style.FILL);
		painttemp.setStrokeWidth(20);
		
		Paint paintill=new Paint();
		paintill.setColor(Color.RED);
		paintill.setStyle(Style.FILL);
		paintill.setStrokeWidth(20);
		

		canvas.drawLine(xpoint+xlength, ypoint, xpoint, ypoint,paint);
		
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(xpoint, ypoint-i*yscale,xpoint+320, ypoint-i*yscale,paint);
			canvas.drawText(ylable[i],xpoint-35,ypoint-i*yscale, paint);
		}
		for (int i = 1; i < 5; i++) {
			canvas.drawText(xlable[i],xpoint+i*xxcale,ypoint+20, paint);
		}
		if (datatemp.size()>1) {
			for (int i =1; i < datatemp.size(); i++) {
				canvas.drawLine(xpoint+i*xxcale, ypoint-datatemp.get(i)*yscale/100, xpoint+i*xxcale, ypoint, painttemp);
			}
		}
		if (dataill.size()>1) {
			for (int i = 1; i < dataill.size(); i++) {
				canvas.drawLine(xxpoint+i*xxcale, ypoint-dataill.get(i)*yscale/100,xxpoint+i*xxcale,ypoint, paintill);
			}
		}
	}

}
