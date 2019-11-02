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

public class Mydanzhu extends View{
	int xpoint=60;
	int ypoint=180;
	int xscale=40;
	int yscale=30;
	int xlength=280;
	int ylength=150;
	String[] xlable=new String[7];
	String[] ylable=new String[5];
	List<Float> data=new ArrayList<Float>();
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==0x1234) {
				Mydanzhu.this.invalidate();
			}
		}
	};

	public Mydanzhu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	    for (int i = 1; i < 7; i++) {
			xlable[i]=Integer.toString(i*1);
		}
	    for (int i = 0; i <5; i++) {
			ylable[i]=Integer.toString(i*200);
		}
//	    data.add(100f);
//	    data.add(610f);
//	    data.add(50f);
//	    data.add(170f);
//	    data.add(240f);
//	    data.add(640f);
//	    data.add(440f);
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
					if (data.size()>5) {
						data.remove(0);
					}
					data.add(MainActivity.ill);
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
    	for (int i = 0; i < 5; i++) {
			canvas.drawLine(xpoint, ypoint-i*yscale, xpoint+3,  ypoint-i*yscale, paint);
			canvas.drawText(ylable[i],xpoint-40,ypoint-i*yscale ,paint);
		}
    	for (int i = 1; i < 6; i++) {
			canvas.drawLine(xpoint+i*xscale,ypoint-5,xpoint+i*xscale,ypoint, paint);
			canvas.drawCircle(xpoint+i*xscale,ypoint,2, paint);
			canvas.drawText(xlable[i], xpoint+i*xscale-5,ypoint+20, paint);
		}
    	paint.setColor(Color.RED);
    	paint.setStrokeWidth(20);
    	if (data.size()>1) {
			for (int i = 1; i < data.size(); i++) {
				canvas.drawLine(xpoint+i*xscale, ypoint-data.get(i)*yscale/200, xpoint+i*xscale, ypoint, paint);
			}
		}
    }

}
