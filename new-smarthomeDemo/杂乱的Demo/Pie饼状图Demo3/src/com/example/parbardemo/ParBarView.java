package com.example.parbardemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

@SuppressLint({ "DrawAllocation", "DrawAllocation" })
public class ParBarView extends View {
	private RectF oval;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				// ˢ�»�ͼ����
				ParBarView.this.invalidate();
				Log.e("ˢ�½���", "ˢ�³ɹ�");
				Log.e("�����Ͳ�����", Float.toString(MainActivity.getnum));
			}
		}
	};

	public ParBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		Log.e("ParBarView", "������ɣ����ڻ�ͼ");
		new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(10);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// �½�paint����
		Paint paint = new Paint();
		paint.setStrokeWidth(5);
		paint.setAntiAlias(true);

		paint.setColor(Color.BLUE);
		// ���ϣ��ң���
		// newF = new RectF(left, top, right, bottom)
		oval = new RectF(150, 50, 450, 350);

		// ����һ��Arc
		canvas.drawArc(oval, 0, MainActivity.getnum, true, paint);
		Log.e("���ƴ�С",
				oval + "-----0-" + "---" + Float.toString(MainActivity.getnum));
		// ����һ��Text����
		paint.setColor(Color.DKGRAY);
		canvas.drawText(Float.toString(MainActivity.getnum), 350, 450, paint);

		// ����һ�������ԲȦ����Բ��
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.DKGRAY);
		// X�ᣬY�ᣬ��С
		canvas.drawCircle(300, 200, 150, paint);
		// ������Բ0.0.0.0���
		canvas.drawLine(300, 200, 450, 200, paint);
		// canvas.drawLine(startX, startY, stopX, stopY, paint)
		Log.e("ParBarView", "����ͼ�������");
		super.onDraw(canvas);
	}
}
