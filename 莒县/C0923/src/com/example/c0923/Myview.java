package com.example.c0923;

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
	int xxpoint=80;
	int ypoint=240;
	int xscale=40;
	int xxscale=60;
	int yscale=30;
	int xlength=300;
	int ylength=180;
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
		for (int i = 0; i < 5; i++) {
			ylable[i]=Integer.toString(i*100);
		}
		for (int i = 1; i < 5; i++) {
			xlable[i]=Integer.toString(i*1);
		}
		datatemp.add(150f);
		datatemp.add(50f);
		datatemp.add(350f);
		datatemp.add(50f);
		
		dataill.add(90f);
		dataill.add(290f);
		dataill.add(350f);
		dataill.add(190f);
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
					datatemp.add(JibenActivity.temp);
					if (datatemp.size()>4) {
						handler.removeMessages(0);
					}
					dataill.add(JibenActivity.ill);
					if (dataill.size()>4) {
						handler.removeMessages(0);
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
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.STROKE);
		
		Paint painttemp=new Paint();
		painttemp.setColor(Color.BLUE);
		painttemp.setStyle(Style.FILL);
		painttemp.setStrokeWidth(20);
		
		Paint paintill=new Paint();
		paintill.setColor(Color.RED);
		paintill.setStyle(Style.FILL);
		paintill.setStrokeWidth(20);
		
		canvas.drawLine(xpoint, ypoint-ylength, xpoint, ypoint+30, paint);
		canvas.drawLine(xpoint+xlength, ypoint, xpoint-30, ypoint,paint);
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(xpoint, ypoint-i*yscale,xpoint+3, ypoint-i*yscale,paint);
			canvas.drawText(ylable[i],xpoint-35,ypoint-i*yscale, paint);
		}
		for (int i = 1; i < 5; i++) {
			canvas.drawText(xlable[i],xpoint+i*xxscale,ypoint+20, paint);
		}
		if (datatemp.size()>1) {
			for (int i =1; i < datatemp.size(); i++) {
				canvas.drawLine(xpoint+i*xxscale, ypoint-datatemp.get(i)*yscale/100, xpoint+i*xxscale, ypoint, painttemp);
			}
		}
		if (dataill.size()>1) {
			for (int i = 1; i < dataill.size(); i++) {
				canvas.drawLine(xxpoint+i*xxscale, ypoint-dataill.get(i)*yscale/100,xxpoint+i*xxscale,ypoint, paintill);
			}
		}
	}

}
