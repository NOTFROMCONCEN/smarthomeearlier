package com.example.guosaihdemo914;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.SweepGradient;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class MyView extends View {
	// ����ؼ�
	private int Xpoint = 50;
	private int Ypoint = 150;
	private int Xheight = 100;
	private int Yheight = 20;
	private int Yline = 100;
	private int Xline = 300;
	private String[] Ylable = new String[5];
	private List<Float> temp = new ArrayList<Float>();
	private List<Float> hum = new ArrayList<Float>();
	private List<Float> smo = new ArrayList<Float>();
	private List<Float> max = new ArrayList<Float>();
	// ˢ�½���
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			MyView.this.invalidate();
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		for (int i = 0; i < 5; i++) {
			Ylable[i] = Float.toString(i * 20);
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
					if (temp.size() >= 1) {
						temp.remove(0);
					}
					if (hum.size() >= 1) {
						hum.remove(0);
					}
					if (smo.size() >= 1) {
						smo.remove(0);
					}
					// ���õ���������100ʱ�̶�����ֵ�����������״ͼ��
					max.add(Float.parseFloat("100"));
					/**
					 * ���Բ���
					 */
					// temp.add(Float.parseFloat("100"));
					// hum.add(Float.parseFloat("200"));
					// smo.add(Float.parseFloat("300"));
					if (IndexActivity.temp != 0) {
						temp.add(IndexActivity.temp);
					}
					if (IndexActivity.hum != 0) {
						hum.add(IndexActivity.hum);
					}
					if (IndexActivity.smo != 0) {
						smo.add(IndexActivity.smo);
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
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.STROKE);
		paint.setAntiAlias(true);
		paint.setTextSize(15);
		// ������Y�ᡢY��̶�
		// for (int i = 0; i < 5; i++) {
		// canvas.drawLine(Xpoint, Ypoint - i * Yheight, Xpoint - 5, Ypoint
		// - i * Yheight, paint);
		// canvas.drawText(Ylable[i], Xpoint - 40, Ypoint - i * Yheight, paint);
		// }
		canvas.drawLine(Xpoint - 20, Ypoint, Xpoint + Xline, Ypoint, paint);
		String[] argStrings = { "�¶�", "����", "ʪ��" };// X��̶�
		for (int i = 0; i < argStrings.length; i++) {
			canvas.drawText(String.valueOf(argStrings[i]), Xpoint + i * Xheight
					+ 20, Ypoint + 20, paint);
		}
		/**
		 * ��״ͼ���Ʋ����� ��ȣ�30 �����100
		 */
		/**
		 * ������ �������С��100����������Ϊ������ֵ��
		 */
		/**
		 * �����������100����������Ϊ�̶�ֵ�����ڶ���������ֵ����
		 */
		paint.setStyle(Style.FILL);// ���÷��Ϊ��䣻
		paint.setShader(new SweepGradient(Ypoint, Yline, Color.GREEN,
				Color.YELLOW));
		int[] color = {};
		/**
		 * �����¶���״ͼ����20��50��30��
		 */
		if (temp.size() > 0) {
			for (int i = 0; i < temp.size(); i++) {
				if (temp.get(i) < 100) {
					canvas.drawText(temp.get(i).toString(), Xpoint + 20, Ypoint
							- temp.get(i) * Yheight / 20 - 10, paint);
					canvas.drawRect(Xpoint + 20, Ypoint - temp.get(i) * Yheight
							/ 20, Xpoint + 50, Ypoint, paint);
				} else {
					canvas.drawText(Float.toString(IndexActivity.temp),
							Xpoint + 20, Ypoint - max.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 20, Ypoint - max.get(i) * Yheight
							/ 20, Xpoint + 50, Ypoint, paint);
				}
			}
		}
		/**
		 * ����ʪ����״ͼ����120��150��30��
		 */
		if (hum.size() > 0) {
			for (int i = 0; i < hum.size(); i++) {
				if (hum.get(i) < 100) {
					canvas.drawText(Float.toString(IndexActivity.hum),
							Xpoint + 220, Ypoint - hum.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 220, Ypoint - hum.get(i) * Yheight
							/ 20, Xpoint + 250, Ypoint, paint);
				} else {
					canvas.drawText(Float.toString(IndexActivity.hum),
							Xpoint + 220, Ypoint - max.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 220, Ypoint - max.get(i) * Yheight
							/ 20, Xpoint + 250, Ypoint, paint);
				}
			}
		}

		/**
		 * ����������״ͼ����520��550��30��
		 */
		if (smo.size() > 0) {
			for (int i = 0; i < smo.size(); i++) {
				if (smo.get(i) < 100) {
					canvas.drawText(Float.toString(IndexActivity.smo),
							Xpoint + 120, Ypoint - smo.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 120, Ypoint - smo.get(i) * Yheight
							/ 20, Xpoint + 150, Ypoint, paint);
				} else {
					canvas.drawText(Float.toString(IndexActivity.smo),
							Xpoint + 120, Ypoint - max.get(i) * Yheight / 20
									- 10, paint);
					canvas.drawRect(Xpoint + 120, Ypoint - max.get(i) * Yheight
							/ 20, Xpoint + 150, Ypoint, paint);
				}
			}
		}
	}
}
