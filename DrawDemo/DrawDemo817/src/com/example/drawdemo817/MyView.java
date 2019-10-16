package com.example.drawdemo817;

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

public class MyView extends View {
	public static HashMap<String, String> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	private int Xpoint = 100;// X��λ��
	private int Ypoint = 300;// Y��λ��
	private int Xheight = 50;// X����
	private int Yheight = 40;// Y����
	private int Yline = 200;// Y�᳤��
	private int Xline = 400;// X�᳤��
	private int num = 1;
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	private List<Float> data = new ArrayList<Float>();
	private List<String> Xlable = new ArrayList<String>();// X��̶�
	private String[] Ylable = new String[5];// Y��̶�
	private Paint paint = new Paint();// �½�����

	/*
	 * @��������handler
	 * 
	 * @�� �ܣ�ˢ�½���
	 * 
	 * @ʱ �䣺����10:24:11
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
							new String[] { DrawActivity.num + num,
									Float.toString(DrawActivity.getdata) });
				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		dbHelper = new MyDataBaseHelper(getContext(), "info.db", null, 2);
		// ����Y��̶�����
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
						// ���data��������7���Ƴ���ǰ�˵�
						data.remove(0);
						Xlable.remove(0);
						list.remove(0);
					}
					if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {
						// ������ر��򿪲��һ�ȡ�����ݲ�Ϊ��
						data.add(DrawActivity.getdata);
						Xlable.add(DrawActivity.num + num);
						map = new HashMap<String, String>();
						map.put("number", DrawActivity.num + num);
						map.put("data", Float.toString(DrawActivity.getdata));
						list.add(map);
						num++;
					}
					handler.sendEmptyMessage(0x1234);
				}
			}
		}).start();
	}

	/*
	 * @��������onDraw(Canvas canvas)
	 * 
	 * @�� �ܣ���������ͼ
	 * 
	 * @�� ����canvas
	 * 
	 * @ʱ �䣺����10:49:02
	 */
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
		// ����Y��̶ȡ�����
		for (int i = 0; i < 5; i++) {
			canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 5, Ypoint
					- i * Yheight, paint);
			canvas.drawText(Ylable[i], Xpoint - 35, Ypoint - i * Yheight, paint);
		}
		// ����X��㡢�̶�
		paint.setStyle(Style.FILL);
		for (int i = 1; i < 8; i++) {
			canvas.drawCircle(Xpoint + i * Xheight, Ypoint, 4, paint);
		}
		/**
		 * ����Y�ᶥ��
		 */
		if (DrawActivity.getdata > 100) {// �����õ�����ֵ����100
			canvas.drawText(Float.toString(DrawActivity.getdata), Xpoint - 56,
					Ypoint - 5 * Yheight, paint);// ��������
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);// ��������
		} else {
			// �������
			canvas.drawText("", Xpoint - 56, Ypoint - 5 * Yheight, paint);
			canvas.drawLine(Xpoint, Ypoint - 5 * Yheight, Xpoint + 5, Ypoint
					- 5 * Yheight, paint);
		}
		/**
		 * ���Ƶ㲢����
		 */
		if (data.size() > 1) {
			// ���data����ֵ
			for (int i = 1; i < data.size(); i++) {
				if (data.get(i - 1) <= 100 && data.get(i) <= 100) {
					// ���㶼������100
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				} else if (data.get(i - 1) <= 100 && data.get(i) > 100) {
					// ǰһ��С��100����һ�����100
					canvas.drawLine(Xpoint + i * Xheight,
							Ypoint - data.get(i - 1) * Yheight / 20, Xpoint
									+ (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) > 100) {
					// ȫ������100
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - Yheight * 5,
							paint);
				} else if (data.get(i - 1) > 100 && data.get(i) <= 100) {
					// ǰһ�����100����һ��С��100
					canvas.drawLine(Xpoint + i * Xheight, Ypoint - Yheight * 5,
							Xpoint + (i + 1) * Xheight, Ypoint - data.get(i)
									* Yheight / 20, paint);
				}
			}
			// ����Y��ʵ�ĵ�
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) <= 100) {
					// �����ֵС��100
					canvas.drawCircle(Xpoint + (i + 1) * Xheight,
							Ypoint - data.get(i) * Yheight / 20, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight,
							Ypoint - data.get(i) * Yheight / 20 - 10, paint);// ����ʵ�ĵ��ϵ�����
				} else {
					canvas.drawCircle(Xpoint + (i + 1) * Xheight, Ypoint
							- Yheight * 5, 3, paint);
					canvas.drawText(data.get(i).toString(), Xpoint + (i + 1)
							* Xheight, Ypoint - Yheight * 5 - 10, paint);
				}
				canvas.drawText(Xlable.get(i), Xpoint + (i + 1) * Xheight - 10,
						Ypoint + 26, paint);
			}
		}
	}
}