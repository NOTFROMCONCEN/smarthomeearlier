package com.example.tu;

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

public class MyZhu extends View{
	int xpoint=60;
	int xxpoint=80;
	int ypoint=200;
	int xscale=40;
	int xxscale=50;
	int yscale=30;
	int xlength=300;
	int ylength=200;
	List<Float> dataill=new ArrayList<Float>();
	List<Float> datatemp=new ArrayList<Float>();
	String[] ylable=new String[7];
	String[] xlable=new String[5];

	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==0x1234) {
				MyZhu.this.invalidate();
			}
		}
	};

	public MyZhu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 1; i < 5; i++) {
			xlable[i]=Integer.toString(i*1);
		}
		for (int i = 0; i < 7; i++) {
			ylable[i]=Integer.toString(i*100);
		}
		//		datatemp.add(100f);
		//		datatemp.add(10f);
		//		datatemp.add(200f);
		//		datatemp.add(70f);
		//		datatemp.add(500f);
		//
		//		dataill.add(60f);
		//		dataill.add(160f);
		//		dataill.add(360f);
		//		dataill.add(260f);

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
					datatemp.add(MainActivity.temp);
					if (dataill.size()>5) {
						dataill.remove(0);
					}
					dataill.add(MainActivity.ill);
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
		paint.setStyle(Style.FILL);

		Paint painttemp=new Paint();
		painttemp.setColor(Color.RED);
		painttemp.setStyle(Style.FILL);
		painttemp.setStrokeWidth(20);

		Paint paintill=new Paint();
		paintill.setColor(Color.BLUE);
		paintill.setStyle(Style.FILL);
		paintill.setStrokeWidth(20);


		canvas.drawLine(xpoint, ypoint-ylength, xpoint,ypoint+30, paint);
		canvas.drawLine(xpoint+xlength, ypoint, xpoint-30, ypoint, paint);

		for (int i = 1; i <5; i++) {
			canvas.drawText(xlable[i],xpoint+i*xxscale,ypoint+20, paint);
		}
		for (int i = 0; i < 7; i++) {
			canvas.drawLine(xpoint, ypoint-i*yscale,xpoint+3,ypoint-i*yscale,paint);
			canvas.drawText(ylable[i],xpoint-35,ypoint-i*yscale+5,paint);
		}
		if (datatemp.size()>1) {
			for (int i = 1; i < datatemp.size(); i++) {
				canvas.drawLine(xpoint+i*xxscale, ypoint-datatemp.get(i)*yscale/100, xpoint+i*xxscale, ypoint, painttemp);
			}
		}
		if (dataill.size()>1) {
			for (int i = 1; i < dataill.size(); i++) {
				canvas.drawLine(xxpoint+i*xxscale, ypoint-dataill.get(i)*yscale/100, xxpoint+i*xxscale, ypoint, paintill);
			}
		}

	}


}
