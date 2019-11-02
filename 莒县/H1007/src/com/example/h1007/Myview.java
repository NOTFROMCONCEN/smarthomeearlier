package com.example.h1007;

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
	int ypoint=160;
	int xscale=40;
	int yscale=30;
	int xlength=240;
	int ylength=150;
	String[] xlable=new String[5];
	List<Float> datatemp=new ArrayList<Float>();
	List<Float> datasmoke=new ArrayList<Float>();
	List<Float> datahum=new ArrayList<Float>();
	
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
		for (int i = 0; i < 4; i++) {
			xlable[i]=Integer.toString(i*1);
		}
		datatemp.add(60f);
		datahum.add(80f);
		datasmoke.add(20f);
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
					datatemp.add(ZhuActivity.temp);
					datasmoke.add(ZhuActivity.smoke);
					datahum.add(ZhuActivity.hum);
				}
			}
		});
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint=new Paint();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		
		Paint painttemp=new Paint();
		painttemp.setColor(Color.GREEN);
		painttemp.setStyle(Style.FILL);
		painttemp.setStrokeWidth(15);
		
		Paint painthum=new Paint();
		painthum.setColor(Color.GREEN);
		painthum.setStyle(Style.FILL);
		painthum.setStrokeWidth(15);
		
		Paint paintsmoke=new Paint();
		paintsmoke.setColor(Color.GREEN);
		paintsmoke.setStyle(Style.FILL);
		paintsmoke.setStrokeWidth(15);
		
		canvas.drawLine(xpoint+xlength, ypoint, xpoint-40, ypoint, paint);
		for (int i = 1; i < datatemp.size(); i++) {
			if (datatemp.size()>1) {
				canvas.drawLine(xpoint+i*xscale, ypoint-datatemp.get(i)*yscale, xpoint+i*xscale, ypoint-datatemp.get(i)*yscale, painttemp);
			}
		}
		for (int i = 1; i < datasmoke.size(); i++) {
			if (datasmoke.size()>1) {
				canvas.drawLine(xpoint+i*xscale, ypoint-datasmoke.get(i)*yscale, xpoint+i*xscale, ypoint-datasmoke.get(i)*yscale, paintsmoke);
			}
		}
		for (int i = 1; i < datahum.size(); i++) {
			if (datahum.size()>1) {
				canvas.drawLine(xpoint+i*xscale, ypoint-datahum.get(i)*yscale, xpoint+i*xscale, ypoint-datahum.get(i)*yscale, painthum);
			}
		}
	}
}
