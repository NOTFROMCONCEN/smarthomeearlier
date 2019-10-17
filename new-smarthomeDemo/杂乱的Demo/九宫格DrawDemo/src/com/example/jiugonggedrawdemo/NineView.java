package com.example.jiugonggedrawdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author Administrator
 * @year 2019
 * @Todo TODO自定义View完成九宫格
 * @package_name com.example.jiugonggedrawdemo
 * @project_name 九宫格DrawDemo
 * @file_name NineView.java
 */
public class NineView extends View {
	static boolean ojbk = false;

	public NineView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	// 构造方法
	public NineView(Context context) {
		super(context);
	}

	// 计时
	int num = 0;
	// 密码
	List<Integer> result = new ArrayList<Integer>();
	int width = 70;
	Paint paintsrc = new Paint();
	Paint line_color = new Paint();
	// 点在哪里
	float currX, currY;
	// 最终输出密码
	String pass;

	// 绘制
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paintsrc.setStrokeWidth(2);
		paintsrc.setAntiAlias(true);
		line_color.setAntiAlias(true);
		// 划线
		if (result.size() > 0) {
			// 点
			int x = result.get(result.size() - 1) % 3 + 1;
			int y = result.get(result.size() - 1) / 3 + 1;
			paintsrc.setStrokeWidth(1);
			// 绘制连线
			canvas.drawLine(x * width, y * width, currX, currY, paintsrc);
			if (result.size() > 1) {
				// 防止越界
				for (int i = 0; i < result.size() - 1; i++) { // 1 2 3 <=2
					// 需要取当前的i和下一个i
					// 按住的前一个点
					int x1 = result.get(i) % 3 + 1;
					int y1 = result.get(i) / 3 + 1;
					// 按住的后一个点
					int x2 = result.get(i + 1) % 3 + 1;
					int y2 = result.get(i + 1) / 3 + 1;
					paintsrc.setStrokeWidth(2);
					canvas.drawLine(x1 * width, y1 * width, x2 * width, y2
							* width, line_color);
				}
			}
		}
		// 9个点
		paintsrc.setStyle(Paint.Style.FILL);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				canvas.drawCircle((i + 1) * width, (j + 1) * width, 3, paintsrc);
				// canvas.drawCircle(cx, cy, radius, paint)
			}
		}
		// 9个外圈
		line_color.setStyle(Paint.Style.STROKE);
		for (Integer integer : result) {
			// i j ; // 8 2 2
			int j = integer / 3;
			int i = integer % 3;
			canvas.drawCircle((i + 1) * width, (j + 1) * width, 20, line_color);
		}
	}

	// 点击屏幕监听
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			ojbk = false;
			if (result != null) {
				result.clear();
			}
			invalidate();
			// 勾股定理
			int i = isConnPoint(x, y);
			// 只要在圆内
			if (i != -1) {
				result.add(i);
				currX = x;
				currY = y;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			ojbk = false;
			currX = x;
			currY = y;
			// 移动到其他的圆中，那么接着去添加result
			int point = isConnPoint(x, y);
			if (point != -1 && !result.contains((Integer) point)) {
				result.add(point);
				System.err.println(result);
			}
			break;
		case MotionEvent.ACTION_UP:
			System.out.println(result);
			pass = "";
			for (Integer integer : result) {
				pass += integer;
			}
			System.out.println(pass);
			DiyToast.showToast(getContext(), pass);
			if (pass.equals("012") || pass.equals("14358")) {
				handler.post(timeRunnable);
				line_color.setColor(Color.BLUE);
				num = 0;
			} else {
				line_color.setColor(Color.RED);
				handler.post(timeRunnable);
				num = 0;
			}
			// 刷新
			invalidate();
			break;
		}
		invalidate();
		return true;
	}

	// 判断是不是在“圈”上
	public int isConnPoint(float x, float y) {
		// 9 width,width width
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (pointOnCircle(x, y, (j + 1) * width, (i + 1) * width)) {
					return i * 3 + j; // 0-8
				}
			}
		}
		return -1;
	}

	// 辅助判断是不是在“圈”上
	public boolean pointOnCircle(float x, float y, int cx, int cy) {// true
		float i = ((cx - x) * (cx - x) + (cy - y) * (cy - y));
		float j = ((float) width / 3f) * ((float) width / 3f);
		return i < j;
	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what > 1) {
				ojbk = true;
				result.clear();
				invalidate();
				line_color.setColor(Color.BLACK);
			}
			handler.postDelayed(timeRunnable, 500);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			num++;
			Message msg = handler.obtainMessage();
			msg.what = num;
			if (msg.what > 2) {
				handler.removeCallbacks(timeRunnable);
			} else {
				handler.sendMessage(msg);
			}
		}
	};
}