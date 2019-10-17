package com.example.guosaicdemo905;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.color;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.provider.CalendarContract.Colors;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class MyView extends View {
	private int Xpoint = 60;
	private int Ypoint = 180;
	private int Xheight = 105;
	private int Yheight = 30;
	private int Xline = 400;
	private int Yline = 150;
	private List<Float> data = new ArrayList<Float>();
	private List<String> XLable = new ArrayList<String>();
	private String[] YLable = new String[5];
	public static HashMap<String, String> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				MyView.this.invalidate();
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// TODO Auto-generated constructor stub
		for (int i = 0; i < 5; i++) {
			YLable[i] = Integer.toString(i * 100);
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
					if (data.size() >= 4 && DrawActivity.draw_state) {
						XLable.remove(0);
						list.remove(0);
						data.remove(0);
					}
					if (DrawActivity.getdata_ill != 0
							&& DrawActivity.draw_state) {
						data.add(DrawActivity.getdata_ill);
						XLable.add(DrawActivity.num_ill);
					}
					if (DrawActivity.getdata_temp != 0
							&& DrawActivity.draw_state) {
						data.add(DrawActivity.getdata_temp);
						XLable.add(DrawActivity.num_temp);
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
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);
		Paint paintscolor = new Paint();
		paintscolor.setColor(R.drawable.drawableback);
		// YÖáÎÄ×Ö
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(YLable[i], Xpoint - 26, Ypoint - i * Yheight, paint);
		}

		// »æÖÆXÖá
		for (int i = 0; i < 5; i++) {
			paint.setColor(Color.GRAY);
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + Xline,
					Ypoint - i * Yheight, paint);
		}

		paint.setStyle(Style.FILL);
		if (data.size() > 1) {
			for (int i = 0; i < data.size(); i++) {
				// canvas.drawRect(left, top, right, bottom, paintscolor)
				if (data.get(i) <= 100) {
					canvas.drawText(Float.toString(data.get(i)), Xpoint + i
							* Xheight + 20, Ypoint - data.get(i) * Yheight / 20
							- 10, paintscolor);
					canvas.drawRect(Xpoint + i * Xheight + 20, Ypoint - Yheight
							* 5, Xpoint + (i + 1) * Xheight - 55, Ypoint,
							paintscolor);
					canvas.drawRect(Xpoint + i * Xheight + 60, Ypoint - Yheight
							* 5, Xpoint + (i + 1) * Xheight - 15, Ypoint,
							paintscolor);
				} else {
					canvas.drawText(Float.toString(data.get(i)), Xpoint + i
							* Xheight + 20, Ypoint - Yheight * 5 - 10,
							paintscolor);
					canvas.drawRect(Xpoint + i * Xheight + 20, Ypoint - Yheight
							* 5, Xpoint + (i + 1) * Xheight - 55, Ypoint,
							paintscolor);
					canvas.drawRect(Xpoint + i * Xheight + 60, Ypoint - Yheight
							* 5, Xpoint + (i + 1) * Xheight - 15, Ypoint,
							paintscolor);
				}
			}
		}
	}
}
