package com.example.drawdemo824;

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
	private int Xscale = 50;
	private int Yscale = 30;
	private int Xlength = 400;
	private int Ylength = 150;
	private List<Float> data = new ArrayList<Float>();
	private List<String> XLable = new ArrayList<String>();
	private String[] YLable = new String[5];
	private int num = 1;
	private MyDataBaseHelper dbhelper;
	private SQLiteDatabase db;
	public static HashMap<String, String> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

	Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				// Toast.makeText(getContext(),
				// Float.toString(DrawActivity.getdata),
				// Toast.LENGTH_SHORT).show();
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
					MyView.this.invalidate();

					db = dbhelper.getWritableDatabase();
					db.execSQL(
							"insert into data (num,data) values (?,?)",
							new String[] { DrawActivity.num + num,
									Float.toString(DrawActivity.getdata) });
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);

		// TODO Auto-generated constructor stub
		dbhelper = new MyDataBaseHelper(getContext(), "info.db", null, 1);
		for (int i = 0; i < 5; i++) {
			YLable[i] = Integer.toString(i * 20);
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
					if (data.size() >= 7 && DrawActivity.draw_state) {
						data.remove(0);
						XLable.remove(0);
						list.remove(0);
					}
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
						XLable.add(DrawActivity.num + num);

						map = new HashMap<String, String>();
						map.put("num", DrawActivity.num + num);
						map.put("data", Float.toString(DrawActivity.getdata));
						list.add(map);
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
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);

		Paint[] paints = new Paint[7];
		int[] colors = { Color.BLUE, Color.CYAN, Color.DKGRAY, Color.GRAY,
				Color.GREEN, Color.LTGRAY, Color.MAGENTA };
		for (int i = 0; i < colors.length; i++) {
			paints[i] = new Paint();
			paints[i].setColor(colors[i]);
		}

		// Y轴
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Ylength, paint);
		// Y轴刻度和文字
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yscale, Xpoint + 5, Ypoint - i
					* Yscale, paint);
			canvas.drawText(YLable[i], Xpoint - 26, Ypoint - i * Yscale, paint);
		}

		// X轴
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xlength, Ypoint, paint);

		paint.setStyle(Style.FILL);
		if (data.size() > 1) {
			for (int i = 0; i < data.size(); i++) {

				// 如果值小于等100，则画柱 状图，否则大于100，则写Y轴最上面的刻度和标签并画柱状图
				if (data.get(i) <= 100) {
					canvas.drawText(Float.toString(data.get(i)), Xpoint + i
							* Xscale + 20, Ypoint - data.get(i) * Yscale / 20
							- 10, paints[i]);
					canvas.drawRect(Xpoint + i * Xscale + 20,
							Ypoint - data.get(i) * Yscale / 20, Xpoint
									+ (i + 1) * Xscale, Ypoint, paints[i]);
				} else {
					canvas.drawText(Float.toString(data.get(i)), Xpoint + i
							* Xscale + 20, Ypoint - Yscale * 5 - 10, paints[i]);

					canvas.drawRect(Xpoint + i * Xscale + 20, Ypoint - Yscale
							* 5, Xpoint + (i + 1) * Xscale, Ypoint, paints[i]);
				}
				// 画X轴标签
				canvas.drawText(XLable.get(i), Xpoint + i * Xscale + 20,
						Ypoint + 26, paint);
			}
			// 如果传感器的值大于100，画Y轴刻度和标签
			if (DrawActivity.getdata > 100) {
				canvas.drawText(Float.toString(DrawActivity.getdata),
						Xpoint - 56, Ypoint - 5 * Yscale, paint);
				canvas.drawLine(Xpoint, Ypoint - 5 * Yscale, Xpoint + 5, Ypoint
						- 5 * Yscale, paint);
			}

		}
	}

}
