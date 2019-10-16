package com.example.shengsaiddemo2017921;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/*
 * @�ļ�����MyView.java
 * @��������ͼ
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-21
 */
public class MyView extends View {
	public static HashMap<String, String> map;
	public static List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 405;// X��
	private int Ypoint = 405;// Y��
	private int Xline = 490;// X��
	private int Yline = 350;// Y��
	private int Xheight = 70;// X����
	private int Yheight = 70;// Y����
	private int num = 1;// �ۼ�����
	private List<Float> data = new ArrayList<Float>();// Float��ֵ(��ǰ����������)
	private String[] Ylable = new String[5];// Y��̶ȱ���
	private List<String> Xlable = new ArrayList<String>();// X������
	private Paint paint = new Paint();// �½�����
	// ���ݿ�
	MyDataBaseHelper dbHelper;
	SQLiteDatabase db;
	// ˢ�½������
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
					MyView.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (number,data)values(?,?)",
							new String[] { DrawActivity.num + num,
									String.valueOf(DrawActivity.getdata) });
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		for (int i = 0; i < 5; i++) {
			Ylable[i] = String.valueOf(i * 400);
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
					if (data.size() >= 7) {
						data.remove(0);
						Xlable.remove(0);
						list.remove(0);
					}
					if (DrawActivity.draw_state && DrawActivity.getdata != 0) {
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawActivity.num + num);
						map.put("data", String.valueOf(DrawActivity.getdata));
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
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(1);
		// ����X��
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint + 70, Ypoint - i * Yheight, Xpoint + Xline,
					Ypoint - i * Yheight, paint);
		}
		// ����Y��
		for (int i = 1; i < 8; i++) {
			canvas.drawLine(Xpoint + i * Xheight, Ypoint, Xpoint + i * Xheight,
					Ypoint - Yline + 70, paint);
		}
		// ����Y������
		for (int i = 1; i < 5; i++) {
			canvas.drawText(Ylable[i], Xpoint, Ypoint - i * Yheight, paint);
		}
		// ��������ͼ
		paint.setColor(Color.RED);// ��ɫ
		paint.setStrokeWidth(5);
		if (data.size() > 0) {// data.size > 1����ֵʱ
			for (int i = 1; i < data.size(); i++) {// ����data��С��ѭ��
				if (data.get(i - 1) <= 1600 && data.get(i) <= 1600) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 400, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 400, paint);
				}
				if (data.get(i - 1) <= 1600 && data.get(i) > 1600) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 400, Xpoint
									+ (i + 1) * Xheight, Ypoint - 4 * Yheight,
							paint);
				}
				if (data.get(i - 1) > 1600 && data.get(i) > 1600) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 4 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - 4 * Yheight,
							paint);
				}
				if (data.get(i - 1) > 1600 && data.get(i) <= 1600) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - 4 * Yheight,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 400, paint);
				}
			}
			paint.setStyle(Style.FILL);
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 1600) {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 400, 6, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint - 4
							* Yheight, 6, paint);
				}
			}
		}
	}
}
