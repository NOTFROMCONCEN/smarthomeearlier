package com.example.drawdemo815;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

/*
 * @�ļ�����MyView.java
 * @����������ͼ�θ�����
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-8-15
 */
public class MyView extends View {
	private int Xpoint = 100;// X��
	private int Ypoint = 100;// Y��
	private int Xline = 100;// X��
	private int Yline = 100;// Y��
	private int Xheight = 100;// X���
	private int Yheight = 100;// Y���
	private MyDataBaseHelper dbHelper;// ���ݿ�
	private SQLiteDatabase db;// ���ݿ�
	public static List<HashMap<String, String>> map;
	public static ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			super.handleMessage(msg);
			// ���½��桢��ȡ���ݽ���
			if (msg.what == 0x1234) {
				if (DrawActivity.getdata != 0 && DrawActivity.draw_state) {

				}
			}
		}
	};

	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub

	}

	// ���ƹ��ܲ��ִ���
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setTextSize(16);

	}
}
