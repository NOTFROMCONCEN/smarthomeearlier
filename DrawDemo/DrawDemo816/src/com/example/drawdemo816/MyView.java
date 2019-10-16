package com.example.drawdemo816;

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
 * @��������ͼ����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-16
 */
public class MyView extends View {
	public static HashMap<String, String> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;// X��λ��
	private int Ypoint = 300;// Y��λ��
	private int Xline = 400;// X�᳤��
	private int Yline = 200;// Y�᳤��
	private int Yheight = 40;// Y����
	private int Xheight = 50;// X����
	private int num = 1;// ����
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();// X��̶�
	private String[] Ylable = new String[5];// Y��̶�
	private Paint paint = new Paint();// �½�����

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�ˢ�½��桢�������ݿ�
	 * 
	 * @ʱ �䣺����3:07:39
	 */
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			if (msg.what == 0x1234) {
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
					MyView.this.invalidate();
					db = dbHelper.getWritableDatabase();
					db.execSQL(
							"insert into data (number,data)values(?,?)",
							new String[] { DrawActivity.number + num,
									Float.toString(DrawActivity.getdata) });
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		for (int i = 0; i < 5; i++) {
			Ylable[i] = Integer.toString(i * 20);
		}
		// �������ݿ⡢ˢ��
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
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.number + num);

						map = new HashMap<String, String>();
						map.put("number", DrawActivity.number + num);
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
		paint.setStyle(Style.STROKE);// ���û��ʷ��
		paint.setColor(Color.BLACK);// ���û�����ɫ
		paint.setAntiAlias(true);
		paint.setTextSize(16);// ���û������ִ�С
		// ����Y��
		canvas.drawLine(Xpoint, Ypoint + 20, Xpoint, Ypoint - Yline, paint);
		// ����X��
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		// ����Y�����֡��̶�
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint + 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 26, Ypoint - i * Yheight, paint);
		}
		// ����X�����֡��̶�
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 3, paint);
		}
		/**
		 * ����Y�ᶥ�����ֹ���
		 */
		// �����õ�ֵ����100��д��Y�����Ϸ������֣�С��100����ո�����
		if (DrawActivity.getdata > 100) {
			canvas.drawText(Float.toString(DrawActivity.getdata), Xpoint - 56,
					Ypoint - 5 * Yheight, paint);// ��������
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);// ��������
		} else {
			canvas.drawText("", Xpoint - 56, Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		}
		/**
		 * ���Ƶ��Լ����߹���
		 */
		if (data.size() > 1) {
			for (int i = 1; i < data.size(); i++) {
				// ǰ��������ֵ��������100����������
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}// ���ǰһ����ֵС��100����һ�����ڻ����100��������ֵȫ�������Y�����
				else if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				}// ������ֵ������100�������߻�����Y�����
				else if (data.get(i - 1) > 100 && data.get(i) > 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			// ����ʵ�ĵ�
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					// ����ʵ�ĵ�
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
					// ���Ƶ����������
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint
							- Yheight * 5, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight, Ypoint - Yheight * 5 - 10, paint);
				}
				// ����X������
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight,
						Ypoint + 26, paint);
			}
		}
	}
}