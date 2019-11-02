package com.example.j1009;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.HapticFeedbackConstants;
import android.view.MotionEvent;
import android.view.View;

public class Jiugongge extends View{
	Context context;
	int count=3;
	int index_point=0;
	int zhi=0;
	String string=" ";
	float density,raiues,minraiues;
	List<Point> listcircle;
	List<RectF> listrectf;
	LinkedHashSet<Integer> setpoint;
	Point movPoint;
	Paint paint,minPaint;


	public Jiugongge(Context context){
		this(context, null);
	}
	public Jiugongge(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context=context;
		density=getContext().getResources().getDisplayMetrics().density;
		listcircle=new ArrayList<Point>();
		listrectf=new ArrayList<RectF>();
		setpoint=new LinkedHashSet<Integer>();

		paint=new Paint();
		paint.setAntiAlias(true);
		paint.setStrokeWidth(3);
		paint.setColor(Color.RED);
		paint.setStyle(Style.STROKE);

		minPaint=new Paint();
		minPaint.setAntiAlias(true);
		minPaint.setStrokeWidth(3);
		minPaint.setColor(Color.BLACK);
		minPaint.setStyle(Style.FILL);
		minraiues=5;

	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		drawALL_CirCle(canvas);
		draw_Lin(canvas);
	}
	private void draw_Lin(Canvas canvas) {
		// TODO Auto-generated method stub
		Point p1=null;
	    Point p2=null;
		for (int index:setpoint) {
			if (p1==null) {
				p1=listcircle.get(index);
			}else if (p2==null) {
				p2=listcircle.get(index);
				canvas.drawLine(p1.x, p1.y,p2.x, p2.y, paint);
				p1=p2;
			}else {
				p2=listcircle.get(index);
				canvas.drawLine(p1.x, p1.y,p2.x, p2.y, paint);
				p1=p2;
			}
			canvas.drawCircle(p1.x, p1.y, raiues, paint);
		}
		if (movPoint!=null&&p1 !=null) {
			canvas.drawLine(p1.x, p1.y, movPoint.x, movPoint.y,paint);
		}

	}
	private void drawALL_CirCle(Canvas canvas) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listcircle.size(); i++) {
			Point point=listcircle.get(i);
			if (listcircle.contains(i)) {
				canvas.drawCircle(point.x, point.y, minraiues, minPaint);
			}else {
				canvas.drawCircle(point.x, point.y, minraiues, minPaint);
			}
		}
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		raiues=getMeasuredWidth()*1.00f/14;
		float rectwh=raiues*2;

		listcircle.clear();
		listrectf.clear();
		for (int i = 0; i < count; i++) {
			for (int j = 0; j < count; j++) {
				RectF rectF=new RectF((2*j+1)*rectwh,(2*i+1)*rectwh,(2*j+1)*rectwh,(2*i+1)*rectwh);
				listrectf.add(rectF);
				Point point=new Point((3*j+4)*(int)raiues,(3*i+4)*(int)raiues);
				listcircle.add(point);
			}
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		float point_x=event.getX();
		float point_y=event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			if (++zhi==0) {
				ShujuActivity.login=true;
			}
			string=" ";
			setpoint.clear();
			movPoint=null;
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			int index=ontouch(point_x,point_y);
			if (movPoint==null) {
				movPoint=new Point((int)point_x,(int)point_y);
			}else {
				movPoint.set((int)point_x,(int)point_y);
			}
			if (index !=-1) {
				string=string.trim()+String.valueOf(index);
				setpoint.add(index);
				if (index_point !=setpoint.size()) {
					index_point=setpoint.size();
					performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
				}
				if (string.matches("^0+1+2+3+4+5+$")) {
					paint.setColor(Color.BLUE);
					ShujuActivity.btngflag=true;
					zhi=0;
				}else if (string.matches("^6+7+8+$")) {
					paint.setColor(Color.BLUE);
					ShujuActivity.jiben=true;
					zhi=0;
				}else {
					paint.setColor(Color.RED);
				}
			}
			invalidate();
			break;

		default:
			break;
		}
		return true;
	}
	private int ontouch(float x, float y) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listcircle.size(); i++) {
			Point point=listcircle.get(i);
			if (iscoll(x,y,point.x,point.y,raiues)) {
				return i;
			}
		}
		return -1;
	}
	private boolean iscoll(float x1, float y1, float x2, float y2, float raiues) {
		// TODO Auto-generated method stub
		if (Math.sqrt(Math.pow(x1 -x2, 2)+Math.pow(y1 -y2, 2))<=raiues) {
			  return true;
			}
			return false;
	}
}
