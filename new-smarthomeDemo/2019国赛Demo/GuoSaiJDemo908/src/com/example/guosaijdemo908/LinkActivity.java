package com.example.guosaijdemo908;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;

/*
 * @文件名：LinkActivity.java
 * @描述：联动、模式、绘图
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-8
 */
public class LinkActivity extends Activity {
	private CheckBox cb_shangban_mode;// 上班模式
	private CheckBox cb_xiaban_mode;// 下班模式
	private CheckBox cb_shuimian_mode;// 睡眠模式
	private RelativeLayout re_draw;
	public static float getdata;
	public static String num;
	public static boolean draw_state = false, invis_state = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_link);
		re_draw = (RelativeLayout) findViewById(R.id.re_draw);
		cb_shangban_mode = (CheckBox) findViewById(R.id.cb_shangban_mode);
		cb_shuimian_mode = (CheckBox) findViewById(R.id.cb_shuimian_mode);
		cb_xiaban_mode = (CheckBox) findViewById(R.id.cb_xiaban_mode);
		if (cb_shangban_mode.isChecked()) {
			invis_state = true;
			// cb_shangban_mode.setChecked(false);
			cb_shuimian_mode.setChecked(false);
			cb_xiaban_mode.setChecked(false);
		}
		if (cb_shuimian_mode.isChecked()) {
			invis_state = true;
			cb_shangban_mode.setChecked(false);
			// cb_shuimian_mode.setChecked(false);
			cb_xiaban_mode.setChecked(false);
		}
		if (cb_xiaban_mode.isChecked()) {
			invis_state = true;
			cb_shangban_mode.setChecked(false);
			cb_shuimian_mode.setChecked(false);
			// cb_xiaban_mode.setChecked(false);
		}
		// 激活进程
		handler.post(timeRunnable);

	}

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			getdata = BaseActivity.gas;
			draw_state = true;
			if (invis_state) {
				re_draw.setVisibility(View.INVISIBLE);
			} else {
				re_draw.setVisibility(View.VISIBLE);
			}
			handler.postDelayed(timeRunnable, 1000);
		}
	};
	Runnable timeRunnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message msg = handler.obtainMessage();
			handler.sendMessage(msg);
		}
	};
}