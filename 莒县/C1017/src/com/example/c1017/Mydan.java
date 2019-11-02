package com.example.c1017;

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

public class Mydan extends View{
	int xpoint=60;
	int ypoint=200;
	int xscale=40;
	int yscale=30;
	int xlength=240;
	int ylength=180;
	List<Float> data=new ArrayList<Float>();
	
	Handler handler=new Handler(){
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what==0x1234) {
				Mydan.this.invalidate();
			}
		}
	};

	public Mydan(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

		data.add(100f);
		data.add(200f);
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
					if (data.size()>1) {
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
		paint.setColor(Color.RED);
		paint.setStyle(Style.FILL);
		
		Paint paintill=new Paint();
		paintill.setColor(Color.RED);
		paintill.setStyle(Style.FILL);
		paintill.setStrokeWidth(25);
		
		canvas.drawLine(xpoint+xlength, ypoint, xpoint, ypoint, paint);
		if (data.size()>1) {
			for (int i = 0; i < data.size(); i++) {
				canvas.drawLine(xpoint+40, ypoint-data.get(i)*yscale/100, xpoint+40, ypoint, paintill);
				canvas.drawText(data.get(i).toString(), xpoint+30, ypoint-data.get(i)*yscale/100-10, paintill);
			}
		}
	}

}
