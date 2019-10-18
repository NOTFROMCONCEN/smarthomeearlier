package com.example.guosaicdemo923;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	private int Xpoint = 300;
	private int Ypoint = 300;
	private int Xheight = 105;
	private int Yheight = 30;
	private int Xline = 400;
	private int Yline = 150;
	private int num = 1;
	private List<Float> data_temp = new ArrayList<Float>();
	private List<Float> data_ill = new ArrayList<Float>();
	private List<String> XLable = new ArrayList<String>();
	private String[] YLable = new String[5];
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.getdata_ill != 0
						&& DrawActivity.getdata_temp != 0) {
					MyView.this.invalidate();
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 5; i++) {
			YLable[i] = String.valueOf(i * 100);
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
					if (data_ill.size() >= 4) {
						data_ill.remove(0);
					}
					if (data_temp.size() >= 4) {
						data_temp.remove(0);
					}
					if (XLable.size() >= 7) {
						XLable.remove(0);
					}
					if (DrawActivity.getdata_ill != 0
							&& DrawActivity.getdata_temp != 0) {
						data_ill.add(DrawActivity.getdata_ill);
						data_temp.add(DrawActivity.getdata_temp);
						XLable.add(String.valueOf(num));
						num++;
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
		Paint paint = new Paint();
		paint.setStyle(Style.FILL_AND_STROKE);
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		paint.setTextSize(20);
		// YÖáÎÄ×Ö
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(YLable[i], Xpoint - 50, Ypoint - i * Yheight, paint);
		}
		// »æÖÆXÖá
		for (int i = 0; i < 5; i++) {
			paint.setColor(Color.GRAY);
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + Xline,
					Ypoint - i * Yheight, paint);
		}
		// canvas.drawRect(Xpoint + i * Xheight + 60, Ypoint - Yheight
		// * 5, Xpoint + (i + 1) * Xheight - 15, Ypoint,
		// paintscolor[i]);
		// canvas.drawRect(Xpoint + i * Xheight + 60, Ypoint - Yheight
		// * 5, Xpoint + (i + 1) * Xheight - 15, Ypoint,
		// paintscolor[i]);
		paint.setStyle(Style.FILL);
		paint.setColor(Color.BLUE);
		if (data_temp.size() > 1) {
			for (int i = 0; i < data_temp.size(); i++) {
				if (data_temp.get(i) <= 100) {
					canvas.drawRect(Xpoint + i * Xheight + 20, Ypoint
							- data_temp.get(i) * Yheight / 100, Xpoint + i
							* Xheight + 40, Ypoint, paint);
				}
				if (data_temp.get(i) > 100) {
					canvas.drawRect(Xpoint + i * Xheight + 20, Ypoint - 5
							* Yheight, Xpoint + i * Xheight + 40, Ypoint, paint);
				}
				canvas.drawText(XLable.get(i), Xpoint + i * Xheight + 35,
						Ypoint + 26, paint);
			}
		}
		paint.setStyle(Style.FILL);
		paint.setColor(Color.RED);
		if (data_ill.size() > 1) {
			for (int i = 0; i < data_ill.size(); i++) {
				if (data_ill.get(i) <= 100) {
					canvas.drawRect(Xpoint + i * Xheight + 50, Ypoint
							- data_ill.get(i) * Yheight / 100, Xpoint + i
							* Xheight + 70, Ypoint, paint);
				}
				if (data_ill.get(i) > 100) {
					canvas.drawRect(Xpoint + i * Xheight + 50, Ypoint - 5
							* Yheight, Xpoint + i * Xheight + 70, Ypoint, paint);
				}
			}
		}
	}
}
