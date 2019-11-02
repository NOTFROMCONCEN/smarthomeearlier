package com.example.tu;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.R.style;
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

public class Myshuzhu extends View{
	int xpoint=60;
	int ypoint=180;
	int xscale=40;
	int yscale=30;
	int xlength=180;
	int ylength=240;
	List<Float> dataill=new ArrayList<Float>();
	List<Float> dataco2=new ArrayList<Float>();
	List<Float> datatemp=new ArrayList<Float>();
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==0x1234) {
				Myshuzhu.this.invalidate();
			}
		}
	};
	public Myshuzhu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
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
						if (dataill.size()>1) {
							dataill.remove(0);
						}
						dataill.add(MainActivity.ill);
						if (dataco2.size()>1) {
							dataco2.remove(0);
						}
						dataco2.add(MainActivity.co2);
						if (datatemp.size()>1) {
							datatemp.remove(0);
						}
						datatemp.add(MainActivity.temp);
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
		
		Paint paintill=new Paint();
		paintill.setColor(Color.RED);
		paintill.setStyle(Style.FILL);
		paintill.setStrokeWidth(20);
		
		Paint paintco2=new Paint();
		paintco2.setColor(Color.GREEN);
		paintco2.setStyle(Style.FILL);
		paintco2.setStrokeWidth(20);
		
		Paint painttemp=new Paint();
		painttemp.setColor(Color.WHITE);
		painttemp.setStyle(Style.FILL);
		painttemp.setStrokeWidth(20);
		LinearGradient gradient=new LinearGradient(0, 0, 0, 300, Color.YELLOW, Color.GREEN, TileMode.CLAMP);
		painttemp.setShader(gradient);
		
		canvas.drawLine(xpoint+xlength, ypoint, xpoint, ypoint, paint);
		
		if (dataill.size()>1) {
			for (int i = 1; i < dataill.size(); i++) {
				canvas.drawLine(xpoint+40,ypoint-dataill.get(i)*yscale/200,xpoint+40,ypoint, paintill);
				canvas.drawText(dataill.get(i).toString(), xpoint+30,ypoint-dataill.get(i)*yscale/200-10, paintill);
				canvas.drawText("¹âÕÕ",xpoint+28,ypoint+15, paint);
			}
		}
		if (dataco2.size()>1) {
			for (int i = 1; i < dataco2.size(); i++) {
				canvas.drawLine(xpoint+80,ypoint-dataco2.get(i)*yscale/200,xpoint+80,ypoint, paintco2);
				canvas.drawText(dataco2.get(i).toString(), xpoint+65,ypoint-dataco2.get(i)*yscale/200-10, paintco2);
				canvas.drawText("Co2",xpoint+68,ypoint+15, paint);
			}
		}
		if (datatemp.size()>1) {
			for (int i = 1; i < datatemp.size(); i++) {
				canvas.drawLine(xpoint+120,ypoint-datatemp.get(i)*yscale/200,xpoint+120,ypoint, painttemp);
				canvas.drawText(datatemp.get(i).toString(), xpoint+110,ypoint-datatemp.get(i)*yscale/200-10, painttemp);
				canvas.drawText("ÎÂ¶È",xpoint+108,ypoint+15, paint);
			}
		}
		
	}

}
