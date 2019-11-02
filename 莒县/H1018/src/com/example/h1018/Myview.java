package com.example.h1018;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

public class Myview extends View{
	int xpoint=60;
	int ypoint=150;
	int xscale=40;
	int yscale=30;
	int xlength=240;
	int ylength=150;
	List<Float> datatemp=new ArrayList<Float>();
	List<Float> dataill=new ArrayList<Float>();
	List<Float> datahum=new ArrayList<Float>();
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
//		datatemp.add(100f);
//		datatemp.add(100f);
//		dataill.add(100f);
//		dataill.add(100f);
//		datahum.add(100f);
//		datahum.add(100f);
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
					if (datatemp.size()>1) {
						datatemp.remove(0);
					}
					datatemp.add(ZhuActivity.temp);
					
					if (dataill.size()>1) {
						dataill.remove(0);
					}
					dataill.add(ZhuActivity.ill);
					
					if (datahum.size()>1) {
						datahum.remove(0);
					}
					datahum.add(ZhuActivity.hum);
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
		
		Paint painttemp=new Paint();
		
//		LinearGradient mshadeGradient= new LinearGradient(0, 0, 0,300, Color.YELLOW, Color.GREEN, TileMode.CLAMP);
//		painttemp.setShader(mshadeGradient);
		LinearGradient gradient=new LinearGradient(0, 0, 0, 300, Color.YELLOW, Color.GREEN, TileMode.CLAMP);
		painttemp.setShader(gradient);
		painttemp.setStyle(Style.FILL);
		painttemp.setStrokeWidth(20);
		
		canvas.drawLine(xpoint+xlength, ypoint, xpoint, ypoint, paint);
		
		if (datatemp.size()>1) {
			for (int i = 1; i < datatemp.size(); i++) {
				canvas.drawLine(xpoint+30, ypoint-datatemp.get(i)*yscale/100, xpoint+30, ypoint, painttemp);
				canvas.drawText("ÎÂ¶È", xpoint+15, ypoint+20, paint);
				canvas.drawText(datatemp.get(i).toString(), xpoint+20, ypoint-datatemp.get(i)*yscale/100-5, paint);
			}		
		}
		if (dataill.size()>1) {
			for (int i = 1; i < dataill.size(); i++) {
				canvas.drawLine(xpoint+110, ypoint-dataill.get(i)*yscale/100, xpoint+110, ypoint, painttemp);
				canvas.drawText("¹âÕÕ", xpoint+95, ypoint+20, paint);
				canvas.drawText(dataill.get(i).toString(), xpoint+100, ypoint-dataill.get(i)*yscale/100-5, paint);
			}
		}
		if (datahum.size()>1) {
			for (int i = 1; i < datahum.size(); i++) {
				canvas.drawLine(xpoint+180, ypoint-datahum.get(i)*yscale/100, xpoint+180, ypoint, painttemp);
				canvas.drawText("Êª¶È", xpoint+165, ypoint+20, paint);
				canvas.drawText(datahum.get(i).toString(), xpoint+170, ypoint-datahum.get(i)*yscale/100-5, paint);
			}
		}
		
	}

}
